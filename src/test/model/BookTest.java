package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    private int long_pages = 600;
    private int mid_pages = 400;
    private int short_pages = 200;
    private int midProgress = 150;
    private int shortProgress = 50;
    Book shortBook;
    Book midBook;
    Book longBook;

    @BeforeEach
    public void setup() {
        shortBook = new Book("short book", "short book author", "short book genre", short_pages);
        midBook = new Book("mid book", "mid book author", "mid book genre", mid_pages);
        longBook = new Book("long book", "long book author", "long book genre", long_pages);
    }

    @Test
    public void startReadingTest() {
        //Unread book
        assertEquals("Unread", shortBook.getStatus());
        shortBook.readBook();
        assertEquals("Reading", shortBook.getStatus());
        //Already reading
        shortBook.readBook();
        assertEquals("Reading", shortBook.getStatus());
    }

    @Test
    public void updateProgressTest() {
        //Less than page count
        assertEquals("Unread", shortBook.getStatus());
        shortBook.readBook();
        shortBook.updateProgress(shortProgress);
        assertEquals("Reading", shortBook.getStatus());
        //Hits page count
        shortBook.updateProgress(short_pages);
        assertEquals("Finished", shortBook.getStatus());
        //Repeated updates
        longBook.readBook();
        assertEquals("Reading", longBook.getStatus());
        longBook.updateProgress(midProgress);
        longBook.updateProgress(long_pages - midProgress);
        assertEquals("Reading", longBook.getStatus());
        //Borders page count
        longBook.updateProgress(long_pages - 1);
        assertEquals("Reading", longBook.getStatus());
        longBook.updateProgress(long_pages);
        assertEquals("Finished", longBook.getStatus());
    }

    @Test
    public void updateTitleTest() {
        //Different title
        assertEquals("short book", shortBook.getTitle());
        shortBook.updateTitle("New Title short book");
        assertEquals("New Title short book", shortBook.getTitle());
        shortBook.updateTitle("Another title short book");
        assertEquals("Another title short book", shortBook.getTitle());
        //Different capitalization
        assertEquals("mid book", midBook.getTitle());
        midBook.updateTitle("Mid Book");
        assertEquals("Mid Book", midBook.getTitle());
        //Same title as before
        longBook.updateTitle("long book");
        assertEquals("long book", longBook.getTitle());
    }

    @Test
    public void updateAuthorTest() {
        //Different author
        assertEquals("short book author", shortBook.getAuthor());
        shortBook.updateAuthor("New short book author");
        assertEquals("New short book author", shortBook.getAuthor());
        shortBook.updateAuthor("Another short book author");
        assertEquals("Another short book author", shortBook.getAuthor());
        //Different capitalization
        assertEquals("mid book author", midBook.getAuthor());
        midBook.updateAuthor("Mid Book Author");
        assertEquals("Mid Book Author", midBook.getAuthor());
        //Same author as before
        longBook.updateAuthor("long book author");
        assertEquals("long book author", longBook.getAuthor());
    }

    @Test
    public void updateGenreTest() {
        //Different genre
        assertEquals("short book genre", shortBook.getGenre());
        shortBook.updateGenre("New genre short book");
        assertEquals("New genre short book", shortBook.getGenre());
        shortBook.updateGenre("Another genre short book");
        assertEquals("Another genre short book", shortBook.getGenre());
        //Different capitalization
        assertEquals("mid book genre", midBook.getGenre());
        midBook.updateGenre("Mid Book Genre");
        assertEquals("Mid Book Genre", midBook.getGenre());
        //Same genre as before
        longBook.updateTitle("long book genre");
        assertEquals("long book genre", longBook.getGenre());
    }

    @Test
    public void updatePagesTest() {
        //Pages are less
        longBook.updatePages(420);
        assertEquals(420, longBook.getPages());
        //Pages are equal
        shortBook.updatePages(short_pages);
        assertEquals(short_pages, shortBook.getPages());
        //Pages are more
        midBook.updatePages(long_pages);
        assertEquals(long_pages, midBook.getPages());
        //Edge case: only 1 page
        shortBook.updatePages(1);
        assertEquals(1, shortBook.getPages());
    }

    @Test
    public void returnBookInfoTest() {
        ArrayList<String> shortExpectedInfo = new ArrayList<>();
        shortExpectedInfo.add("Title: " + shortBook.getTitle());
        shortExpectedInfo.add("Author: " + shortBook.getAuthor());
        shortExpectedInfo.add("Genre: " + shortBook.getGenre());
        shortExpectedInfo.add("Pages: " + shortBook.getPages());

        assertEquals(shortExpectedInfo, shortBook.returnBookInfo());
    }

    @Test
    public void convertToJsonTest() {
        JSONObject json = midBook.convertToJson();
        assertEquals(json.getString("title"), midBook.getTitle());
        assertEquals(json.getString("author"), midBook.getAuthor());
        assertEquals(json.getString("genre"), midBook.getGenre());
        assertEquals(json.getInt("pages"), midBook.getPages());
    }
}

