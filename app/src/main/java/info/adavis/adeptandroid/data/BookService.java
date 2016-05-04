package info.adavis.adeptandroid.data;

import java.util.List;

import info.adavis.adeptandroid.models.Book;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    Call<List<Book>> search( @Query( "q" ) String query );

    @GET( "books/{id}" )
    Call<Book> getBook( @Path( "id" ) Long id );

    @POST( "books" )
    Call<Book> saveBook ( @Body Book book );
}
