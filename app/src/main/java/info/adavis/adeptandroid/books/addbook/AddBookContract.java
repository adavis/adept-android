package info.adavis.adeptandroid.books.addbook;

import info.adavis.adeptandroid.models.Book;

/**
 * The contract between the view and presenter
 */
public interface AddBookContract
{
    interface View
    {
        void showErrorMessage ();

        void showBook (Book book);
    }

}
