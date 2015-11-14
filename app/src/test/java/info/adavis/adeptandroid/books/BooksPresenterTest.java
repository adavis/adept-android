package info.adavis.adeptandroid.books;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import info.adavis.adeptandroid.models.Book;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class BooksPresenterTest {

    @Test
    public void emptyListOfBooksShouldHaveSizeZero() {
        List<Book> books = Collections.emptyList();

        int result = books.size();

        assertEquals("the size should be 0", 0, result);
    }

}