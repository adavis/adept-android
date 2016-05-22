package info.adavis.adeptandroid.books.updatebook;

import android.os.Bundle;

import java.util.Locale;

import butterknife.OnClick;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.books.shared.BaseBookActivity;
import info.adavis.adeptandroid.di.Injector;
import info.adavis.adeptandroid.models.Book;

/**
 * @author Annyce Davis
 */
public class UpdateBookActivity extends BaseBookActivity
{
    public static final String EXTRA_BOOK = "EXTRA_BOOK";

    private UpdateBookPresenter presenter;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );

        presenter = new UpdateBookPresenter( this, Injector.provideBookService() );
        presenter.setBook( (Book) getIntent().getParcelableExtra( EXTRA_BOOK ) );

        configureLayout( presenter.getBook() );
    }

    private void configureLayout (Book book)
    {
        titleText.setText( book.getTitle() );
        authorText.setText( book.getAuthor() );
        numPagesText.setText( String.valueOf( book.getNumberOfPages() ) );
        descriptionText.setText( book.getDescription() );
    }

    @OnClick( R.id.save_book_fab)
    public void fabClicked()
    {
        presenter.updateBook( titleText.getText().toString(),
                              authorText.getText().toString(),
                              numPagesText.getText().toString(),
                              descriptionText.getText().toString() );
    }

}
