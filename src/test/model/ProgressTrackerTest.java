package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgressTrackerTest {

    private int long_pages = 600;
    private int mid_pages = 400;
    private int short_pages = 200;
    private int midProgress = 150;
    private int shortProgress = 50;
    private ProgressTracker shortBook;
    private ProgressTracker midBook;
    private ProgressTracker longBook;

    @BeforeEach
    public void setup() {
        shortBook = new ProgressTracker(short_pages);
        midBook = new ProgressTracker(mid_pages);
        longBook = new ProgressTracker(long_pages);
    }

    @Test
    public void startReadingTest() {
        //Unread book
        assertEquals("Unread", shortBook.getStatus());
        shortBook.startReading();
        assertEquals("Reading", shortBook.getStatus());
        //Already reading
        shortBook.startReading();
        assertEquals("Reading", shortBook.getStatus());
    }

    @Test
    public void updateProgressTest() {
        //Less than page count
        assertEquals("Unread", shortBook.getStatus());
        shortBook.startReading();
        shortBook.updateProgress(shortProgress);
        assertEquals("Reading", shortBook.getStatus());
        //Hits page count
        shortBook.updateProgress(short_pages);
        assertEquals("Finished", shortBook.getStatus());
        //Repeated updates
        longBook.startReading();
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
    public void updatePagesTest() {
        //More pages
        shortBook.updatePages(250);
        assertEquals(250, shortBook.getTotalPages());
        //Same page amount
        midBook.updatePages(mid_pages);
        assertEquals(mid_pages, midBook.getTotalPages());
        //Repeated decreases
        longBook.updatePages(420);
        assertEquals(420, longBook.getTotalPages());
        longBook.updatePages(320);
        assertEquals(320, longBook.getTotalPages());
        //Edge case
        shortBook.updatePages(1);
        assertEquals(1, shortBook.getTotalPages());
    }

    @Test
    public void convertToJsonTest() {
        JSONObject json = shortBook.convertToJson();
        assertEquals(json.getInt("totalPages"), shortBook.getTotalPages());
    }
}

