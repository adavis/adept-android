package info.adavis.adeptandroid.features.shared.events;

import info.adavis.adeptandroid.models.Book;

/**
 * @author Annyce Davis
 */
public class BookLoadedSuccessfulEvent
{
    private Book book;

    public BookLoadedSuccessfulEvent (Book book)
    {
        this.book = book;
    }

    public Book getBook ()
    {
        return book;
    }
}
