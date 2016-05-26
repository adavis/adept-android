package info.adavis.adeptandroid.books.books;

import java.util.List;

import info.adavis.adeptandroid.data.BookRepository;
import info.adavis.adeptandroid.data.BookService;
import info.adavis.adeptandroid.models.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class BooksPresenter
{
    private final BooksContract.View booksView;
    private final BookService service;
    private final BookRepository bookRepository;

    public BooksPresenter (BooksContract.View booksView, BookService service,
                           BookRepository bookRepository)
    {
        this.booksView = booksView;
        this.service = service;
        this.bookRepository = bookRepository;
    }

    public void initDataSet ()
    {
        List<Book> books = bookRepository.query();
        if ( !books.isEmpty() )
        {
            booksView.showBooks( books );
            Timber.i( "Books data was loaded from database." );
            return;
        }

        service.getBooks().enqueue( new Callback<List<Book>>()
        {
            @Override
            public void onResponse (Call<List<Book>> call, Response<List<Book>> response)
            {
                if ( response.isSuccessful() )
                {
                    List<Book> books = response.body();
                    booksView.showBooks( books );
                    bookRepository.add( books );

                    Timber.i( "Books data was loaded from API." );
                }
            }

            @Override
            public void onFailure (Call<List<Book>> call, Throwable t)
            {
                booksView.showErrorMessage();
                Timber.e( t, "Unable to load the books data from API." );
            }
        } );
    }

}