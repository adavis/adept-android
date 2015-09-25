package info.adavis.adeptandroid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import info.adavis.adeptandroid.models.Book;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.adapters.BooksAdapter;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private static final String CHARSET_NAME = "UTF-8";

    private List<Book> books;

    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initDataSet();
        configureLayout();
    }

    private void configureLayout() {
        setSupportActionBar((Toolbar) ButterKnife.findById(this, R.id.toolbar));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new BooksAdapter(this, books));
    }

    private void initDataSet() {
        String json = null;

        try {
            json = getJsonData();
        }
        catch (Exception e) {
            Timber.e("an exception occurred", e);
        }

        if (json != null) {
            books = new Gson().fromJson(new StringReader(json), new TypeToken<List<Book>>() { }.getType());
        }
    }

    private String getJsonData() throws Exception {
        Writer writer = new StringWriter();
        InputStream is = null;
        try {
            is = getResources().openRawResource(R.raw.sample_data);
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
