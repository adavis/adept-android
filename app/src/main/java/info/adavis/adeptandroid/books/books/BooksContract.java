package info.adavis.adeptandroid.books.books;

import java.util.List;

import info.adavis.adeptandroid.models.Book;

/**
 * The contract between the view and presenter
 */
public interface BooksContract
{

    interface View
    {

        void showBooks (List<Book> books);

        void showErrorMessage ();

        void refresh ();
    }

}
