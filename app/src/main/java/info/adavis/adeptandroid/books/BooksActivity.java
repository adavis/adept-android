package info.adavis.adeptandroid.books;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.models.Book;

public class BooksActivity extends AppCompatActivity implements BooksContract.View {

    private BooksPresenter booksPresenter;
    private BooksAdapter booksAdapter;

    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        booksPresenter = new BooksPresenter(this);
        booksAdapter = new BooksAdapter(this, new ArrayList<Book>(0));

        booksPresenter.initDataSet(getResources().openRawResource(R.raw.sample_data));

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

}
