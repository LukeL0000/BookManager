package model;

import org.json.JSONObject;
import model.persistence.Writer;

import java.util.ArrayList;

//The Book class deals directly with the book including contents and the such.
public class Book implements Writer {

    private String title;
    private String author;
    private String genre;
    private int pages;
    private ProgressTracker progress;

    //Constructor for making book
    public Book(String name, String author, String genre, int pages) {
        updateTitle(name);
        updateAuthor(author);
        updateGenre(genre);
        updatePages(pages);
        progress = new ProgressTracker(pages);
    }

    //MODIFIES: ProgressTracker
    //EFFECTS: calls ProgressTracker and changes status of reading to true.
    public void readBook() {
        progress.startReading();
    }

    //MODIFIES: ProgressTracker
    //EFFECTS: calls ProgressTracker and updates number of pages read on book
    public void updateProgress(int pages) {
        progress.updateProgress(pages);
    }

    //REQUIRES: non-empty string.
    //MODIFIES: this.
    //EFFECTS: updates book title.
    public void updateTitle(String title) {
        this.title = title;
    }

    //REQUIRES: non-empty string.
    //MODIFIES: this.
    //EFFECTS: updates book author.
    public void updateAuthor(String author) {
        this.author = author;
    }

    //REQUIRES: non-empty string.
    //MODIFIES: this.
    //EFFECTS: updates book genre.
    public void updateGenre(String genre) {
        this.genre = genre;
    }

    //REQUIRES: pages > 0
    //MODIFIES: this.
    //EFFECTS: updates book pages.
    public void updatePages(int pages) {
        this.pages = pages;
    }

    //EFFECTS: returns information on book as array list.
    public ArrayList returnBookInfo() {
        ArrayList<String> bookInfo = new ArrayList<>();
        bookInfo.add("Title: " + title);
        bookInfo.add("Author: " + author);
        bookInfo.add("Genre: " + genre);
        bookInfo.add("Pages: " + pages);
        return bookInfo;
    }

    //EFFECTS: returns object information as JSON object.
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("genre", genre);
        json.put("pages", pages);
        return json;
    }

    //EFFECTS: returns book title
    public String getTitle() {
        return title;
    }

    //EFFECTS: returns book author
    public String getAuthor() {
        return author;
    }

    //EFFECTS: returns book genre
    public String getGenre() {
        return genre;
    }

    //EFFECTS: returns number of pages in book
    public int getPages() {
        return pages;
    }

    public String getStatus() {
        return progress.getStatus();
    }

    //EFFECTS: returns current status (reading, finished, or unread)
    public String getProgress() {
        return progress.getStatus();
    }

//    //EFFECTS: returns number of pages read so far
//    private int getPagesRead() {
//        return progress.getPagesRead();
//    }
}
