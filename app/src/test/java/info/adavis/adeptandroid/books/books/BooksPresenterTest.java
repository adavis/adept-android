package info.adavis.adeptandroid.books.books;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import info.adavis.adeptandroid.data.BookService;
import info.adavis.adeptandroid.models.Book;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Annyce Davis
 */
public class BooksPresenterTest
{
    private BooksPresenter booksPresenter;
    private List<Book> books;

    @Mock
    BooksContract.View booksView;

    @Mock
    BookService bookService;

    @Mock
    Call<List<Book>> mockCall;

    @Mock
    ResponseBody responseBody;

    @Captor
    ArgumentCaptor<Callback<List<Book>>> argumentCapture;

    @Before
    public void setUp ()
    {
        MockitoAnnotations.initMocks( this );

        booksPresenter = new BooksPresenter( booksView, bookService );
        books = Collections.singletonList( new Book() );
    }

    @Test
    public void initDataSet_shouldGetBooks ()
    {
        when( bookService.getBooks() ).thenReturn( mockCall );
        Response<List<Book>> res = Response.success( books );

        booksPresenter.initDataSet();

        verify( mockCall ).enqueue( argumentCapture.capture() );
        argumentCapture.getValue().onResponse( null, res );

        verify( booksView ).showBooks( books );
    }

    @Test
    public void initDataSet_shouldDoNothing_whenBadRequest ()
    {
        when( bookService.getBooks() ).thenReturn( mockCall );
        Response<List<Book>> res = Response.error( 500, responseBody );

        booksPresenter.initDataSet();

        verify( mockCall ).enqueue( argumentCapture.capture() );
        argumentCapture.getValue().onResponse( null, res );

        verifyZeroInteractions( booksView );
    }

    @Test
    public void initDataSet_shouldShowError_whenFailedRequest ()
    {
        when( bookService.getBooks() ).thenReturn( mockCall );
        Throwable throwable = new Throwable ( new RuntimeException ( ) );

        booksPresenter.initDataSet();

        verify( mockCall ).enqueue( argumentCapture.capture() );
        argumentCapture.getValue().onFailure( null, throwable );

        verify( booksView ).showErrorMessage();
    }

}