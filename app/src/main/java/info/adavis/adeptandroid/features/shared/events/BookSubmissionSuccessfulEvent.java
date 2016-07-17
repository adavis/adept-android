package info.adavis.adeptandroid.features.shared.events;

import info.adavis.adeptandroid.models.Book;

/**
 * @author Annyce Davis
 */
public class BookSubmissionSuccessfulEvent
{
    private final Book book;

    public BookSubmissionSuccessfulEvent (Book book)
    {
        this.book = book;
    }

    public Book getBook ()
    {
        return book;
    }
}
