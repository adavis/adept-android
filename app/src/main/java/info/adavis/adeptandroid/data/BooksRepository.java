package info.adavis.adeptandroid.data;

import android.support.annotation.NonNull;

import java.util.List;

import info.adavis.adeptandroid.models.Book;

public interface BooksRepository {

    interface LoadBooksCallback {

        void onBooksLoaded(List<Book> books);

    }

    void getBooks(@NonNull LoadBooksCallback callback);
}
