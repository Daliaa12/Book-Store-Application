package model;

import java.time.LocalDate;
import java.util.Date;

public class EBook {
    private Long id;
    private String author;
    private String title;
    private String format;
    private LocalDate publishedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

  @Override
public String toString(){
    return String.format("Book author: %s | title: %s | Format: %s | Published Date: %s.", author, title,format, publishedDate);
}
}
