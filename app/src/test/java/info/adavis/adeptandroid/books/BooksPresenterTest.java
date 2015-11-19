package info.adavis.adeptandroid.books;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import info.adavis.adeptandroid.data.BooksRepository;
import info.adavis.adeptandroid.models.Book;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

public class BooksPresenterTest {

    @Mock BooksContract.View booksView;
    @Mock BooksRepository booksRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validPresenterShouldNotBeNull() {
        BooksPresenter presenter = new BooksPresenter(booksRepository, booksView);

        assertNotNull(presenter);
    }

    @Test(expected = NullPointerException.class)
    public void nullRepositoryShouldThrowException() {
        BooksPresenter presenter = new BooksPresenter(null, new BooksContractViewStub());
    }

    @Test(expected = NullPointerException.class)
    public void nullViewShouldThrowException() {
        BooksPresenter presenter = new BooksPresenter(new BooksRepositoryStub(), null);
    }

    @Test
    public void callbackCompletedShouldShowBooks() {
        BooksContractViewStub view = new BooksContractViewStub();
        BooksPresenter presenter = new BooksPresenter(new BooksRepositoryStub(), view);

        presenter.initDataSet();

        assertTrue("show books should have been called", view.isShowBooksCalled());
    }

    @Test
    public void repositoryShouldTriggerCallback() {
        BooksRepositoryStub repository = new BooksRepositoryStub();
        BooksPresenter presenter = new BooksPresenter(repository, new BooksContractViewStub());

        presenter.initDataSet();

        assertTrue("get books should have been called", repository.isGetBooksCalled());
    }

    public class BooksContractViewStub implements BooksContract.View {

        private boolean showBooksCalled = false;

        @Override
        public void showBooks(List<Book> books) {
            showBooksCalled = true;
        }

        public boolean isShowBooksCalled() {
            return showBooksCalled;
        }
    }

    public class BooksRepositoryStub implements BooksRepository {

        private boolean getBooksCalled = false;

        @Override
        public void getBooks(@NonNull LoadBooksCallback callback) {
            callback.onBooksLoaded(Collections.<Book>emptyList());
            getBooksCalled = true;
        }

        public boolean isGetBooksCalled() {
            return getBooksCalled;
        }
    }

}