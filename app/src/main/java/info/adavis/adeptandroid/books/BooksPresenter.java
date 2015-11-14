package info.adavis.adeptandroid.books;

import java.util.List;

import info.adavis.adeptandroid.data.BooksRepository;
import info.adavis.adeptandroid.models.Book;

import static com.google.common.base.Preconditions.checkNotNull;

public class BooksPresenter {

    private final BooksRepository booksRepository;
    private final BooksContract.View booksView;

    public BooksPresenter(BooksRepository booksRepository, BooksContract.View booksView) {
        this.booksRepository = checkNotNull(booksRepository);
        this.booksView = checkNotNull(booksView);
    }

    public void initDataSet() {
        booksRepository.getBooks(new BooksRepository.LoadBooksCallback() {
            @Override
            public void onBooksLoaded(List<Book> books) {
                booksView.showBooks(books);
            }
        });
    }

}