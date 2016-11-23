package info.adavis.adeptandroid.di;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import info.adavis.adeptandroid.AdeptAndroid;
import info.adavis.adeptandroid.BuildConfig;
import info.adavis.adeptandroid.Constants;
import info.adavis.adeptandroid.data.AdeptAndroidApi;
import info.adavis.adeptandroid.data.BookService;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

public class Injector
{
    private static BookService bookService;
    private static EventBus eventBus;

    private static Retrofit provideRetrofit (String baseUrl)
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
                .cache( provideCache() )
                .build();
    }

    private static Cache provideCache ()
    {
        Cache cache = null;
        try
        {
            cache = new Cache( new File( AdeptAndroid.getInstance().getCacheDir(), "http-cache" ),
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

    public static BookService provideBookService ()
    {
        if ( bookService == null )
        {
            bookService = new BookService( provideApi(), provideEventBus() );
        }
        return bookService;
    }

    private static AdeptAndroidApi provideApi ()
    {
        return provideRetrofit( Constants.BASE_URL ).create( AdeptAndroidApi.class );
    }

    public static EventBus provideEventBus ()
    {
        if ( eventBus == null )
        {
            eventBus = EventBus.builder()
                               .logNoSubscriberMessages( BuildConfig.DEBUG )
                               .sendNoSubscriberEvent( BuildConfig.DEBUG )
                               .build();
        }
        return eventBus;
    }

}
