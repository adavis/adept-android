package info.adavis.adeptandroid.features.addbook;

import android.os.Bundle;

import butterknife.OnClick;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.di.Injector;
import info.adavis.adeptandroid.features.shared.BaseBookActivity;

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

        presenter = new AddBookPresenter( Injector.provideBookService(),
                                          Injector.provideEventBus() );
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

    @OnClick( R.id.save_book_fab)
    public void fabClicked()
    {
        presenter.saveBook( titleText.getText().toString(),
                            authorText.getText().toString(),
                            numPagesText.getText().toString(),
                            descriptionText.getText().toString() );
    }

}
