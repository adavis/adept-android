package info.adavis.adeptandroid.books.book;

import info.adavis.adeptandroid.books.shared.BookContract;
import info.adavis.adeptandroid.data.BookService;
import info.adavis.adeptandroid.models.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class BookPresenter
{
    private final BookContract.View bookView;
    private final BookService service;

    public BookPresenter (BookContract.View bookView, BookService service)
    {
        this.bookView = bookView;
        this.service = service;
    }

    public void retrieveBook (long id)
    {
        service.getBook( id ).enqueue( new Callback<Book>()
        {
            @Override
            public void onResponse (Call<Book> call, Response<Book> response)
            {
                if ( response.isSuccessful() )
                {
                    bookView.showBook( response.body() );
                    Timber.i( "Book data was loaded from API." );
                }
            }

            @Override
            public void onFailure (Call<Book> call, Throwable t)
            {
                bookView.showErrorMessage();
                Timber.e( t, "Unable to load the book data from API." );
            }
        } );
    }

}