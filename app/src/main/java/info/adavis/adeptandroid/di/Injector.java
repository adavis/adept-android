package info.adavis.adeptandroid.di;

import java.io.File;
import java.io.IOException;

import info.adavis.adeptandroid.AdeptAndroid;
import info.adavis.adeptandroid.BuildConfig;
import info.adavis.adeptandroid.Constants;
import info.adavis.adeptandroid.data.BookService;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * @author Annyce Davis
 */
public class Injector
{
    private static final String CACHE_CONTROL = "Cache-Control";

    public static Retrofit provideRetrofit (String baseUrl)
    {
        return new Retrofit.Builder()
                .baseUrl( baseUrl )
                .client( provideOkHttpClient() )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
    }

    private static OkHttpClient provideOkHttpClient ()
    {
        return new OkHttpClient.Builder()
                .addInterceptor( provideHttpLoggingInterceptor() )
                .addInterceptor( provideOfflineCacheInterceptor() )
                .addNetworkInterceptor( provideRewriteCacheInterceptor() )
                .cache( provideCache() )
                .build();
    }

    private static Cache provideCache ()
    {
        Cache cache = null;
        try
        {
            cache = new Cache( new File( AdeptAndroid.getInstance().getCacheDir(), "http" ),
                               10 * 1024 * 1024 ); // 10 MB
        }
        catch (Exception e)
        {
            Timber.e( e, "Could not create Cache!" );
        }
        return cache;
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor ()
    {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger()
                {
                    @Override
                    public void log (String message)
                    {
                        Timber.d( message );
                    }
                } );
        httpLoggingInterceptor.setLevel( BuildConfig.DEBUG ? BODY : NONE );
        return httpLoggingInterceptor;
    }

    public static Interceptor provideRewriteCacheInterceptor ()
    {
        return new Interceptor()
        {
            @Override
            public Response intercept (Chain chain) throws IOException
            {
                Response response = chain.proceed( chain.request() );

                // re-write response header to force use of cache
                return response.newBuilder()
                        .header( CACHE_CONTROL, "public, max-age=" + 60 * 2 ) // 2 minutes
                        .build();
            }
        };
    }

    public static Interceptor provideOfflineCacheInterceptor ()
    {
        return new Interceptor()
        {
            @Override
            public Response intercept (Chain chain) throws IOException
            {
                Request request = chain.request();

                // Add Cache Control only for GET methods
                if ( request.method().equals( "GET" ) )
                {
                    if ( !AdeptAndroid.hasNetwork() )
                    {
                        // 1 week stale
                        request = request.newBuilder()
                                .header( CACHE_CONTROL, "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7 )
                                .build();
                    }
                }

                return chain.proceed( request );
            }
        };
    }

    public static BookService provideBookService ()
    {
        return provideRetrofit( Constants.BASE_URL ).create( BookService.class );
    }

}
