package info.adavis.adeptandroid.books.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.books.addbook.AddBookActivity;
import info.adavis.adeptandroid.books.book.BookActivity;
import info.adavis.adeptandroid.di.Injector;
import info.adavis.adeptandroid.models.Book;
import timber.log.Timber;

public class BooksActivity extends AppCompatActivity implements BooksContract.View
{
    private BooksAdapter booksAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private BooksPresenter booksPresenter;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        ButterKnife.bind( this );

        booksPresenter = new BooksPresenter( this, Injector.provideBookService() );
        booksAdapter = new BooksAdapter( this, new ArrayList<Book>( 0 ), itemListener );

        configureLayout();
    }

    @Override
    protected void onResume ()
    {
        super.onResume();
        booksPresenter.initDataSet();
    }

    @OnClick(R.id.add_book_fab)
    public void fabClicked()
    {
        startActivity( new Intent( this, AddBookActivity.class ) );
    }

    @Override
    public void showBooks (List<Book> books)
    {
        booksAdapter.updateBooks( books );
    }

    @Override
    public void showErrorMessage ()
    {
        Toast.makeText( this, R.string.books_loading_unsuccessful, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refresh ()
    {
        booksPresenter.initDataSet();
    }

    private void configureLayout ()
    {
        setSupportActionBar( (Toolbar) ButterKnife.findById( this, R.id.toolbar ) );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( booksAdapter );
        recyclerView.setHasFixedSize( true );
    }

    private BooksAdapter.BookItemListener itemListener = new BooksAdapter.BookItemListener()
    {

        @Override
        public void onBookClick (long id)
        {
            Timber.d( "Book clicked with id: %d", id );

            Intent intent = new Intent( BooksActivity.this, BookActivity.class );
            intent.putExtra( BookActivity.EXTRA_BOOK_ID, id );
            startActivity(intent);
        }

        @Override
        public void onBookLongClick (long id)
        {
            Timber.d( "Book clicked with id: %d", id );

            booksPresenter.removeBook( id );
        }
    };

}
