package info.adavis.adeptandroid.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.book.BookActivity;
import info.adavis.adeptandroid.di.Injector;
import info.adavis.adeptandroid.models.Book;
import timber.log.Timber;

public class BooksActivity extends AppCompatActivity implements BooksContract.View
{

    private BooksAdapter booksAdapter;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        BooksPresenter booksPresenter = new BooksPresenter(this, Injector.provideBookService());
        booksAdapter = new BooksAdapter(this, new ArrayList<>(0), itemListener);

        booksPresenter.initDataSet();

        configureLayout();
    }

    @Override
    public void showBooks(List<Book> books)
    {
        booksAdapter.updateBooks(books);
    }

    @Override
    public void showErrorMessage()
    {
        Toast.makeText(this, R.string.books_loading_unsuccessful, Toast.LENGTH_SHORT).show();
    }

    private void configureLayout()
    {
        setSupportActionBar(findViewById(R.id.toolbar));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(booksAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private BooksAdapter.BookItemListener itemListener = id -> {
        Timber.d("book clicked id: %d", id);
        Intent intent = new Intent(BooksActivity.this, BookActivity.class);
        intent.putExtra(BookActivity.EXTRA_BOOK_ID, id);
        startActivity(intent);
    };

}
