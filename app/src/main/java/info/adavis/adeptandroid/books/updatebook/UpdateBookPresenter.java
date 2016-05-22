package info.adavis.adeptandroid.books.updatebook;

import java.io.IOException;

import info.adavis.adeptandroid.books.shared.BookContract;
import info.adavis.adeptandroid.data.BookService;
import info.adavis.adeptandroid.models.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class UpdateBookPresenter
{
    private final BookContract.View bookView;
    private final BookService service;

    private Book book;

    public UpdateBookPresenter (BookContract.View bookView, BookService service)
    {
        this.bookView = bookView;
        this.service = service;
    }

    public void updateBook (String title, String author, String numPages, String description)
    {
        book.setTitle( title );
        book.setAuthor( author );
        book.setNumberOfPages( Integer.parseInt( numPages ) );
        book.setDescription( description );

        service.updateBook( book.getId(), book ).enqueue( new Callback<Book>()
        {
            @Override
            public void onResponse (Call<Book> call, Response<Book> response)
            {
                if ( response.isSuccessful() )
                {
                    bookView.showBook( response.body() );
                    Timber.i( "Book data was successfully updated in the API." );
                }
                else
                {
                    try
                    {
                        Timber.i( "The response failed: %s", response.errorBody().string() );
                        bookView.showErrorMessage();
                    }
                    catch ( IOException ignored )
                    {
                        // no op
                    }
                }
            }

            @Override
            public void onFailure (Call<Book> call, Throwable t)
            {
                bookView.showErrorMessage();
                Timber.e( t, "Unable to update the book in the API." );
            }
        } );
    }

    public Book getBook ()
    {
        return book;
    }

    public void setBook (Book book)
    {
        this.book = book;
    }
}