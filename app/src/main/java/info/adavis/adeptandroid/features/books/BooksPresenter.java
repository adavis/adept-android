package info.adavis.adeptandroid.features.books;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import info.adavis.adeptandroid.data.BookService;
import info.adavis.adeptandroid.features.shared.events.BookDeletedSuccessfulEvent;
import info.adavis.adeptandroid.features.shared.events.BookSubmissionErrorEvent;
import info.adavis.adeptandroid.features.shared.events.BooksLoadedSuccessfulEvent;
import info.adavis.adeptandroid.features.shared.events.BooksLoadingErrorEvent;

class BooksPresenter
{
    private final BookService bookService;
    private final EventBus bus;

    private BooksContract.View booksView;

    BooksPresenter (BookService bookService, EventBus bus)
    {
        this.bookService = bookService;
        this.bus = bus;
    }

    void attachView (BooksContract.View booksView)
    {
        this.booksView = booksView;

        bus.register( this );
    }

    void detachView ()
    {
        this.booksView = null;

        bus.unregister( this );
    }

    void initDataSet ()
    {
        bookService.loadBooks();
    }

    void removeBook (long id)
    {
        bookService.removeBook( id );
    }

    @Subscribe
    public void onEvent (BooksLoadedSuccessfulEvent event)
    {
        booksView.showBooks( event.getBooks() );
    }

    @Subscribe
    public void onEvent (BooksLoadingErrorEvent event)
    {
        booksView.showErrorMessage();
    }

    @Subscribe
    public void onEvent (BookDeletedSuccessfulEvent event)
    {
        booksView.refresh();
    }

    @Subscribe
    public void onEvent (BookSubmissionErrorEvent event)
    {
        booksView.showErrorMessage();
    }

}