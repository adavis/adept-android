package info.adavis.adeptandroid.books;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import info.adavis.adeptandroid.models.Book;
import timber.log.Timber;

public class BooksPresenter {

    public static final String CHARSET_NAME = "UTF-8";

    private final BooksContract.View booksView;

    public BooksPresenter(BooksContract.View booksView) {
        this.booksView = booksView;
    }

    public void initDataSet(InputStream is) {
        String json = null;

        try {
            json = getJsonData(is);
        } catch (Exception e) {
            Timber.e("an exception occurred", e);
        }

        if (json != null) {
            List<Book> books = new Gson().fromJson(new StringReader(json), new TypeToken<List<Book>>() {
            }.getType());

            booksView.showBooks(books);
        }
    }

    private String getJsonData(InputStream is) throws Exception {
        Writer writer = new StringWriter();
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, CHARSET_NAME));
            int n;
            char[] buffer = new char[1024];
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return writer.toString();
    }
}