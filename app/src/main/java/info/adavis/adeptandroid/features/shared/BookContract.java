package info.adavis.adeptandroid.features.shared;

import info.adavis.adeptandroid.models.Book;

/**
 * The contract between the view and presenter
 */
public interface BookContract
{
    interface View
    {
        void showErrorMessage ();

        void showBook (Book book);
    }

}
