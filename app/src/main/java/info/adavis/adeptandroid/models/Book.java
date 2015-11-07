package info.adavis.adeptandroid.models;

import java.math.BigDecimal;

public class Book {

    String title;
    String author;
    String bookUrl;
    String imageUrl;
    String displayDate;
    BigDecimal price;
    int numberOfPages;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
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
        return "Book{" + "title='" + title + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Book book = (Book) o;
        return !title.equals(book.title);
    }

}
