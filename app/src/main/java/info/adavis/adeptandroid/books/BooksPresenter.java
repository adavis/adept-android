package info.adavis.adeptandroid.books;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import info.adavis.adeptandroid.models.Book;

public class BooksPresenter {

    public static final String CHARSET_NAME = "UTF-8";

    private final BooksContract.View booksView;

    public BooksPresenter(BooksContract.View booksView) {
        this.booksView = booksView;
    }

    public void initDataSet(InputStream is) throws IOException {
        Writer writer =new StringWriter();
        String json;

        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, CHARSET_NAME));
            int n;
            char[] buffer = new char[1024];
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null)
                is.close();
        }

        json = writer.toString();
        

        if (json !=null){
            if (booksView != null) {
                List<Book> books = new Gson().fromJson(new StringReader(json), new TypeToken<List<Book>>() {
                }.getType());
                booksView.showBooks(books);
            }
        }
    }
}