package info.adavis.adeptandroid.models;


public class Book {

    private long id;
    private String title;
    private String author;
    private String description;
    private String bookUrl;
    private String imageUrl;
    private String displayDate;
    private int numberOfPages;

    public Book (String title, String author, String description)
    {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription ()
    {
        return description;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                '}';
    }
}
