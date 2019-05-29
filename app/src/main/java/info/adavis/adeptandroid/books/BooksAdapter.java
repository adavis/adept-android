/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package info.adavis.adeptandroid.books;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.adavis.adeptandroid.R;
import info.adavis.adeptandroid.models.Book;
import timber.log.Timber;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder>
{

    private WeakReference<Context> context;
    private List<Book> books;
    private BookItemListener itemListener;

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener
    {

        @BindView(R.id.titleTextView) TextView titleTextView;
        @BindView(R.id.authorTextView) TextView authorTextView;
        @BindView(R.id.publishedTextView) TextView publishedTextView;
        @BindView(R.id.pagesTextView) TextView pagesTextView;
        @BindView(R.id.imageView) ImageView imageView;

        BookItemListener itemListener;

        ViewHolder(View v, BookItemListener itemListener)
        {
            super(v);
            ButterKnife.bind(this, v);

            this.itemListener = itemListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick (View v)
        {
            Book book = getItem(getAdapterPosition());
            this.itemListener.onBookClick( book.getId() );
        }
    }

    BooksAdapter(Context context, List<Book> books, BookItemListener itemListener)
    {
        this.context = new WeakReference<>(context);
        this.books = books;
        this.itemListener = itemListener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int viewType)
    {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_row_item, viewGroup, false);

        return new ViewHolder(v, this.itemListener);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, final int position)
    {
        Timber.d("Element " + position + " set.");

        Book book = books.get(position);

        viewHolder.titleTextView.setText(book.getTitle());
        viewHolder.authorTextView.setText(book.getAuthor());

        Context contextLocal = context.get();
        if (contextLocal != null) {
            viewHolder.publishedTextView.setText(book.getDisplayDate());
            viewHolder.pagesTextView.setText(
                    String.format(contextLocal.getString(R.string.pages_label), book.getNumberOfPages()));

            Picasso.get()
                    .load(book.getImageUrl())
                    .resize(80, 108)
                    .centerInside()
                    .into(viewHolder.imageView);
        }
    }

    @Override
    public int getItemCount()
    {
        return books.size();
    }

    void updateBooks(List<Book> books)
    {
        this.books = books;
        notifyDataSetChanged();
    }

    private Book getItem(int adapterPosition)
    {
        return books.get(adapterPosition);
    }

    public interface BookItemListener
    {
        void onBookClick(long id);
    }
}
