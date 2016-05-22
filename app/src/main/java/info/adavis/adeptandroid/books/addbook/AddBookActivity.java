package info.adavis.adeptandroid.books.addbook;

import android.os.Bundle;

import butterknife.OnClick;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.books.shared.BaseBookActivity;
import info.adavis.adeptandroid.di.Injector;

/**
 * @author Annyce Davis
 */
public class AddBookActivity extends BaseBookActivity
{
    private AddBookPresenter presenter;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );

        presenter = new AddBookPresenter( this, Injector.provideBookService() );
    }

    @OnClick( R.id.save_book_fab)
    public void fabClicked()
    {
        presenter.saveBook( titleText.getText().toString(),
                            authorText.getText().toString(),
                            numPagesText.getText().toString(),
                            descriptionText.getText().toString() );
    }

}
