package info.adavis.adeptandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.models.Book;
import info.adavis.adeptandroid.utils.PaymentsManager;
import timber.log.Timber;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private WeakReference<Context> context;
    private List<Book> books;
    private PaymentsManager paymentsManager;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.titleTextView) TextView titleTextView;
        @Bind(R.id.authorTextView) TextView authorTextView;
        @Bind(R.id.publishedTextView) TextView publishedTextView;
        @Bind(R.id.pagesTextView) TextView pagesTextView;
        @Bind(R.id.imageView) ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            paymentsManager.purchaseBook(books.get(getAdapterPosition()));
        }

    }

    public BooksAdapter(Context context, List<Book> books, PaymentsManager paymentsManager) {
        this.context = new WeakReference<>(context);
        this.books = books;
        this.paymentsManager = paymentsManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Timber.d("Element " + position + " set.");

        Book book = books.get(position);

        viewHolder.titleTextView.setText(book.getTitle());
        viewHolder.authorTextView.setText(book.getAuthor());

        Context contextLocal = context.get();
        if (contextLocal != null) {
            viewHolder.publishedTextView.setText(book.getDisplayDate());
            viewHolder.pagesTextView.setText(
                    String.format(contextLocal.getString(R.string.pages_label), book.getNumberOfPages()));

            Picasso.with(contextLocal)
                    .load(book.getImageUrl())
                    .resize(80, 108)
                    .centerInside()
                    .into(viewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

}
