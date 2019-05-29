package info.adavis.adeptandroid.book;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.di.Injector;
import info.adavis.adeptandroid.models.Book;

public class BookActivity extends AppCompatActivity implements BookContract.View
{
    public static final String EXTRA_BOOK_ID = "EXTRA_BOOK_ID";

    @BindView(R.id.titleText) TextView titleText;
    @BindView(R.id.authorText) TextView authorText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        ButterKnife.bind(this);

        BookPresenter bookPresenter = new BookPresenter(this, Injector.provideBookService());
        bookPresenter.retrieveBook(getIntent().getLongExtra(EXTRA_BOOK_ID, 1));

        configureLayout();
    }

    @Override
    public void showBook(Book book)
    {
        titleText.setText(book.getTitle());
        authorText.setText(book.getAuthor());
    }

    @Override
    public void showErrorMessage()
    {
        Toast.makeText(this, R.string.book_loading_unsuccessful, Toast.LENGTH_SHORT).show();
    }

    private void configureLayout() {
        setSupportActionBar(findViewById(R.id.toolbar));
    }

}
