package info.adavis.adeptandroid.books.shared;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.books.book.BookActivity;
import info.adavis.adeptandroid.models.Book;

/**
 * @author Annyce Davis
 */
public class BaseBookActivity extends AppCompatActivity implements BookContract.View
{
    @BindView(R.id.titleText)
    protected EditText titleText;

    @BindView(R.id.authorText)
    protected EditText authorText;

    @BindView(R.id.numPagesText)
    protected EditText numPagesText;

    @BindView(R.id.descriptionText)
    protected EditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        ButterKnife.bind(this);

        setSupportActionBar((Toolbar) ButterKnife.findById(this, R.id.toolbar));
    }

    @Override
    public void showErrorMessage()
    {
        Toast.makeText(this, R.string.book_saving_unsuccessful, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBook(Book book)
    {
        Intent intent = new Intent(this, BookActivity.class);
        intent.putExtra(BookActivity.EXTRA_BOOK_ID, book.getId());

        startActivity(intent);
        finish();
    }

}
