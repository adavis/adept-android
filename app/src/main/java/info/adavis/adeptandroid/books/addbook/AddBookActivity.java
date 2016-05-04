package info.adavis.adeptandroid.books.addbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.books.book.BookActivity;
import info.adavis.adeptandroid.di.Injector;
import info.adavis.adeptandroid.models.Book;

/**
 * @author Annyce Davis
 */
public class AddBookActivity extends AppCompatActivity implements AddBookContract.View
{
    @Bind( R.id.titleText )
    EditText titleText;

    @Bind( R.id.authorText )
    EditText authorText;

    @Bind( R.id.descriptionText )
    EditText descriptionText;

    private AddBookPresenter presenter;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_book );

        ButterKnife.bind( this );

        presenter = new AddBookPresenter( this, Injector.provideBookService() );

        setSupportActionBar( (Toolbar) ButterKnife.findById( this, R.id.toolbar ) );
    }

    @OnClick( R.id.save_book_fab)
    public void fabClicked()
    {
        presenter.saveBook( titleText.getText().toString(), authorText.getText().toString(),
                descriptionText.getText().toString() );
    }

    @Override
    public void showErrorMessage ()
    {
        Toast.makeText( this, R.string.book_saving_unsuccessful, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBook (Book book)
    {
        Intent intent = new Intent( this, BookActivity.class );
        intent.putExtra( BookActivity.EXTRA_BOOK_ID, book.getId() );

        startActivity( intent );
        finish();
    }
}
