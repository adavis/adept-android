package info.adavis.adeptandroid.di;

import info.adavis.adeptandroid.Constants;
import info.adavis.adeptandroid.data.BookService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Annyce Davis
 */
public class Injector
{

    public static Retrofit provideRetrofit(String baseUrl)
    {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static BookService provideBookService()
    {
        return provideRetrofit(Constants.BASE_URL).create(BookService.class);
    }

}
