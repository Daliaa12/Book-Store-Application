package model;

import java.time.LocalDate;
import java.util.Date;

public class AudioBook {
    private Long id;
    private String author;
    private String title;
    private int runTime;
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

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
    @Override
    public String toString(){
        return String.format("Book author: %s | title: %s | RunTime: %s | Published Date: %s .", author, title,runTime, publishedDate);
    }
}
