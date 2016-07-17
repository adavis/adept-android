package info.adavis.adeptandroid.features.shared.events;

import java.util.List;

import info.adavis.adeptandroid.models.Book;

/**
 * @author Annyce Davis
 */
public class BooksLoadedSuccessfulEvent
{
    private List<Book> books;

    public BooksLoadedSuccessfulEvent (List<Book> books)
    {
        this.books = books;
    }

    public List<Book> getBooks ()
    {
        return books;
    }
}
