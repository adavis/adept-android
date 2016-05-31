package info.adavis.adeptandroid.data;

import java.util.List;

import info.adavis.adeptandroid.models.Book;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Annyce Davis
 */
public interface BookService
{
    @GET( "books" )
    Call<List<Book>> getBooks();

    @GET( "books" )
    Call<List<Book>> search (@Query( "q" ) String query);

    @GET( "books/{id}" )
    Call<Book> getBook (@Path( "id" ) Long id);

    @POST( "books" )
    Call<Book> saveBook (@Body Book book);

    @PUT( "books/{id}" )
    Call<Book> updateBook (@Path( "id" ) Long id, @Body Book book);

    @DELETE( "books/{id}")
    Call<Void> deleteBook (@Path( "id" ) Long id);
}
