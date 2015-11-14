package info.adavis.adeptandroid.data;

import android.support.annotation.NonNull;

import com.google.common.base.Strings;
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

import info.adavis.adeptandroid.AdeptAndroid;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.models.Book;
import timber.log.Timber;

import static com.google.common.base.Preconditions.checkNotNull;

public class BooksRepositoryImpl implements BooksRepository {

    public static final String CHARSET_NAME = "UTF-8";

    @Override
    public void getBooks(@NonNull LoadBooksCallback callback) {
        checkNotNull(callback);

        String json = null;
        InputStream is = AdeptAndroid.app.getResources().openRawResource(R.raw.sample_data);

        try {
            json = getJsonData(is);
        } catch (Exception e) {
            Timber.e("an exception occurred", e);
        }

        if (!Strings.isNullOrEmpty(json)) {
            List<Book> books = new Gson().fromJson(new StringReader(json),
                                                    new TypeToken<List<Book>>() {}.getType());

            callback.onBooksLoaded(books);
        }
    }

    private String getJsonData(InputStream is) throws Exception {
        checkNotNull(is);

        Writer writer = new StringWriter();
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, CHARSET_NAME));
            int n;
            char[] buffer = new char[1024];
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }

        return writer.toString();
    }
}
