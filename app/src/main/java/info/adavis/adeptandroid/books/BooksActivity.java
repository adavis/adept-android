package info.adavis.adeptandroid.books;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.data.BooksRepositoryImpl;
import info.adavis.adeptandroid.models.Book;
import timber.log.Timber;

public class BooksActivity extends AppCompatActivity implements BooksContract.View {

    private BooksPresenter booksPresenter;
    private BooksAdapter booksAdapter;

    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        booksPresenter = new BooksPresenter(new BooksRepositoryImpl(), this);
        booksAdapter = new BooksAdapter(this, Collections.<Book>emptyList(), itemListener);

        booksPresenter.initDataSet();

        configureLayout();
    }

    @Override
    public void showBooks(List<Book> books) {
        booksAdapter.updateBooks(books);
    }

    private void configureLayout() {
        setSupportActionBar((Toolbar) ButterKnife.findById(this, R.id.toolbar));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(booksAdapter);
    }

    private BooksAdapter.BookItemListener itemListener = new BooksAdapter.BookItemListener() {
        @Override
        public void onBookClick(Book clickedBook) {
            Timber.d("book clicked: " + clickedBook);
            Uri bookUrl = Uri.parse(clickedBook.getBookUrl());
            startActivity(new Intent(Intent.ACTION_VIEW, bookUrl));
        }
    };

}
