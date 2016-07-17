package info.adavis.adeptandroid.features.addbook;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import info.adavis.adeptandroid.data.BookService;
import info.adavis.adeptandroid.features.shared.BookContract;
import info.adavis.adeptandroid.features.shared.events.BookSubmissionErrorEvent;
import info.adavis.adeptandroid.features.shared.events.BookSubmissionSuccessfulEvent;
import info.adavis.adeptandroid.models.Book;

class AddBookPresenter
{
    private final BookService bookService;
    private final EventBus bus;

    private BookContract.View bookView;

    AddBookPresenter (BookService bookService, EventBus bus)
    {
        this.bookService = bookService;
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

    void saveBook (String title, String author, String numPages, String description)
    {
        Book book = new Book( title,
                              author,
                              getNumberOfPages( numPages ),
                              description );

        bookService.saveBook( book );
    }

    private int getNumberOfPages (String numPages)
    {
        return !numPages.isEmpty() ? Integer.parseInt( numPages ) : 0;
    }

    @Subscribe
    public void onEvent (BookSubmissionSuccessfulEvent event)
    {
        bookView.showBook( event.getBook() );
    }

    @Subscribe
    public void onEvent (BookSubmissionErrorEvent event)
    {
        bookView.showErrorMessage();
    }
}