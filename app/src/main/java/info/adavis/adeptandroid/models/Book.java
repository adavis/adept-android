package info.adavis.adeptandroid.models;

import org.parceler.Parcel;
import org.parceler.Parcel.Serialization;
import org.parceler.ParcelConstructor;

import timber.log.Timber;

@Parcel( Serialization.BEAN )
public class Book
{
    private long id;
    private String title;
    private String author;
    private String description;
    private String bookUrl;
    private String imageUrl;
    private String displayDate;
    private int numberOfPages;

    public Book ()
    {
        Timber.i( "using the default constructor" );
    }

    @ParcelConstructor
    public Book (String title, String author, int numberOfPages, String description)
    {
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.description = description;

        Timber.i( "using the 4 parameter constructor" );
    }

    public long getId ()
    {
        return id;
    }

    public void setId (long id)
    {
        this.id = id;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getBookUrl ()
    {
        return bookUrl;
    }

    public void setBookUrl (String bookUrl)
    {
        this.bookUrl = bookUrl;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getDisplayDate ()
    {
        return displayDate;
    }

    public void setDisplayDate (String displayDate)
    {
        this.displayDate = displayDate;
    }

    public String getImageUrl ()
    {
        return imageUrl;
    }

    public void setImageUrl (String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public int getNumberOfPages ()
    {
        return numberOfPages;
    }

    public void setNumberOfPages (int numberOfPages)
    {
        this.numberOfPages = numberOfPages;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                '}';
    }

}
