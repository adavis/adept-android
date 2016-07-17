package info.adavis.adeptandroid.data;

import org.greenrobot.eventbus.EventBus;

import java.util.Collections;
import java.util.List;

import info.adavis.adeptandroid.features.shared.events.BookDeletedSuccessfulEvent;
import info.adavis.adeptandroid.features.shared.events.BookLoadedSuccessfulEvent;
import info.adavis.adeptandroid.features.shared.events.BookLoadingErrorEvent;
import info.adavis.adeptandroid.features.shared.events.BookSubmissionErrorEvent;
import info.adavis.adeptandroid.features.shared.events.BookSubmissionSuccessfulEvent;
import info.adavis.adeptandroid.features.shared.events.BooksLoadedSuccessfulEvent;
import info.adavis.adeptandroid.features.shared.events.BooksLoadingErrorEvent;
import info.adavis.adeptandroid.models.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * @author Annyce Davis
 */
public class BookService
{
    private static final String TITLE = "title";

    private final AdeptAndroidApi api;
    private final EventBus bus;

    public BookService (AdeptAndroidApi api, EventBus bus)
    {
        this.api = api;
        this.bus = bus;
    }

    public void updateBook (Book book, String title)
    {
        api.updateBookValue( book.getId(), Collections.singletonMap( TITLE, title ) )
           .enqueue( new Callback<Book>()
           {
               @Override
               public void onResponse (Call<Book> call, Response<Book> response)
               {
                   if ( response.isSuccessful() )
                   {
                       bus.post( new BookSubmissionSuccessfulEvent( response.body() ) );
                       Timber.i( "Book data was successfully updated in the API." );
                   }
                   else
                   {
                       bus.post( new BookSubmissionErrorEvent() );
                   }
               }

               @Override
               public void onFailure (Call<Book> call, Throwable t)
               {
                   bus.post( new BookSubmissionErrorEvent() );
                   Timber.e( t, "Unable to update the book in the API." );
               }
           } );
    }

    public void loadBooks ()
    {
        api.getBooks().enqueue( new Callback<List<Book>>()
        {
            @Override
            public void onResponse (Call<List<Book>> call, Response<List<Book>> response)
            {
                if ( response.isSuccessful() )
                {
                    bus.post( new BooksLoadedSuccessfulEvent( response.body() ) );
                    Timber.i( "Books data was loaded from API." );
                }
            }

            @Override
            public void onFailure (Call<List<Book>> call, Throwable t)
            {
                bus.post( new BooksLoadingErrorEvent() );
                Timber.e( t, "Unable to load the books data from API." );
            }
        } );
    }

    public void loadBook (final long id)
    {
        api.getBook( id ).enqueue( new Callback<Book>()
        {
            @Override
            public void onResponse (Call<Book> call, Response<Book> response)
            {
                if ( response.isSuccessful() )
                {
                    bus.post( new BookLoadedSuccessfulEvent( response.body() ) );
                    Timber.i( "Book data was loaded from API." );
                }
            }

            @Override
            public void onFailure (Call<Book> call, Throwable t)
            {
                bus.post( new BookLoadingErrorEvent( id ) );
                Timber.e( t, "Unable to load the book data from API." );
            }
        } );
    }

    public void removeBook (long id)
    {
        api.deleteBook( id ).enqueue( new Callback<Void>()
        {
            @Override
            public void onResponse (Call<Void> call, Response<Void> response)
            {
                Timber.i( "Removed the book from API." );
                if ( response.isSuccessful() )
                {
                    bus.post( new BookDeletedSuccessfulEvent() );
                }
            }

            @Override
            public void onFailure (Call<Void> call, Throwable t)
            {
                bus.post( new BookSubmissionErrorEvent() );
                Timber.e( t, "Unable to remove the book from the API." );
            }
        } );
    }

    public void saveBook (Book book)
    {
        api.saveBook( book ).enqueue( new Callback<Book>()
        {
            @Override
            public void onResponse (Call<Book> call, Response<Book> response)
            {
                if ( response.isSuccessful() )
                {
                    bus.post( new BookSubmissionSuccessfulEvent( response.body() ) );
                    Timber.i( "Book data was successfully saved to the API." );
                }
                else
                {
                    bus.post( new BookSubmissionErrorEvent() );
                }
            }

            @Override
            public void onFailure (Call<Book> call, Throwable t)
            {
                bus.post( new BookSubmissionErrorEvent() );
                Timber.e( t, "Unable to save the book data to the API." );
            }
        } );
    }

}
