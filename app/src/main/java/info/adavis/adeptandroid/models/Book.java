package info.adavis.adeptandroid.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable
{
    private long id;
    private String title;
    private String author;
    private String description;
    private String bookUrl;
    private String imageUrl;
    private String displayDate;
    private int numberOfPages;

    public Book (String title, String author, int numberOfPages, String description)
    {
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.description = description;
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

    @Override
    public int describeContents ()
    {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags)
    {
        dest.writeLong( this.id );
        dest.writeString( this.title );
        dest.writeString( this.author );
        dest.writeString( this.description );
        dest.writeString( this.bookUrl );
        dest.writeString( this.imageUrl );
        dest.writeString( this.displayDate );
        dest.writeInt( this.numberOfPages );
    }

    protected Book (Parcel in)
    {
        this.id = in.readLong();
        this.title = in.readString();
        this.author = in.readString();
        this.description = in.readString();
        this.bookUrl = in.readString();
        this.imageUrl = in.readString();
        this.displayDate = in.readString();
        this.numberOfPages = in.readInt();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>()
    {
        @Override
        public Book createFromParcel (Parcel source)
        {
            return new Book( source );
        }

        @Override
        public Book[] newArray (int size)
        {
            return new Book[size];
        }
    };
}
