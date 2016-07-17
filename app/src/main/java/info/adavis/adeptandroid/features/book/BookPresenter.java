package info.adavis.adeptandroid.features.book;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import info.adavis.adeptandroid.data.BookService;
import info.adavis.adeptandroid.features.shared.BookContract;
import info.adavis.adeptandroid.features.shared.events.BookLoadedSuccessfulEvent;
import info.adavis.adeptandroid.features.shared.events.BookLoadingErrorEvent;

class BookPresenter
{
    private final BookService service;
    private final EventBus bus;

    private BookContract.View bookView;

    BookPresenter (BookService service, EventBus bus)
    {
        this.service = service;
        this.bus = bus;
    }

    void attachView (BookContract.View bookView)
    {
        this.bookView = bookView;

        bus.register( this );
    }

    void detachView ()
    {
        this.bookView = null;

        bus.unregister( this );
    }

    void retrieveBook (long id)
    {
        service.loadBook( id );
    }

    @Subscribe
    public void onEvent (BookLoadedSuccessfulEvent event)
    {
        bookView.showBook( event.getBook() );
    }

    @Subscribe
    public void onEvent (BookLoadingErrorEvent event)
    {
        bookView.showErrorMessage();
    }

}