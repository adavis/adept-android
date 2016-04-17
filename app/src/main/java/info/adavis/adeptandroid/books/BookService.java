package info.adavis.adeptandroid.books;

import java.util.List;

import info.adavis.adeptandroid.models.Book;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Annyce Davis
 */
public interface BookService
{
    @GET( "api/books" )
    Call<List<Book>> getBooks();
}
