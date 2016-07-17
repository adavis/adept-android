package info.adavis.adeptandroid.features.updatebook;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import info.adavis.adeptandroid.data.BookService;
import info.adavis.adeptandroid.features.shared.BookContract;
import info.adavis.adeptandroid.features.shared.events.BookSubmissionErrorEvent;
import info.adavis.adeptandroid.features.shared.events.BookSubmissionSuccessfulEvent;
import info.adavis.adeptandroid.models.Book;

class UpdateBookPresenter
{
    private final BookService bookService;
    private final EventBus bus;

    private BookContract.View bookView;
    private Book book;

    UpdateBookPresenter (BookService bookService, EventBus bus)
    {
        this.bus = bus;
        this.bookService = bookService;
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

    void updateBook (String title)
    {
        bookService.updateBook( book, title );
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

    public Book getBook ()
    {
        return book;
    }

    public void setBook (Book book)
    {
        this.book = book;
    }

}