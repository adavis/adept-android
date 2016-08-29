package info.adavis.adeptandroid.features.updatebook;

import android.os.Bundle;

import org.parceler.Parcels;

import butterknife.OnClick;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.di.Injector;
import info.adavis.adeptandroid.features.shared.BaseBookActivity;
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

        presenter = new UpdateBookPresenter( Injector.provideBookService(),
                                             Injector.provideEventBus() );
        presenter.setBook( (Book) Parcels.unwrap( getIntent().getParcelableExtra( EXTRA_BOOK ) ) );

        configureLayout( presenter.getBook() );
    }

    @Override
    protected void onResume ()
    {
        super.onResume();

        presenter.attachView( this );
    }

    @Override
    protected void onPause ()
    {
        presenter.detachView();

        super.onPause();
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
        presenter.updateBook( titleText.getText().toString());
    }

}
