package info.adavis.adeptandroid.books;

import java.util.List;

import info.adavis.adeptandroid.data.BookService;
import info.adavis.adeptandroid.models.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

class BooksPresenter
{
    private final BooksContract.View booksView;
    private final BookService service;

    BooksPresenter(BooksContract.View booksView, BookService service)
    {
        this.booksView = booksView;
        this.service = service;
    }

    void initDataSet()
    {
        service.getBooks().enqueue(new Callback<List<Book>>()
        {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response)
            {
                if (response.isSuccessful())
                {
                    booksView.showBooks(response.body());
                    Timber.i("Books data was loaded from API.");
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t)
            {
                booksView.showErrorMessage();
                Timber.e(t, "Unable to load the books data from API.");
            }
        });
    }

}