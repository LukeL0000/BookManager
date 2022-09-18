package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ShelfTest {

    private int long_pages = 600;
    private int mid_pages = 400;
    private int short_pages = 200;
    Book shortBook = new Book("short book", "short book author", "short book genre", short_pages);
    Book midBook = new Book("mid book", "mid book author", "mid book genre", mid_pages);
    Book longBook = new Book("long book", "long book author", "long book genre", long_pages);
    Shelf emptyShelf;
    Shelf smallShelf;
    Shelf packedShelf;


    @BeforeEach
    public void setup() {
        emptyShelf = new Shelf("Empty Shelf");
        smallShelf = new Shelf("Small Shelf");
        packedShelf = new Shelf("Packed Shelf");
    }

    @Test
    public void findBookTest() {
        ArrayList<String> emptyList = new ArrayList<>();
        smallShelf.addBook(shortBook);
        smallShelf.addBook(midBook);
        //book findable
        assertEquals(shortBook.returnBookInfo(), smallShelf.findBook("short book"));
        //book not findable
        assertEquals(emptyList, smallShelf.findBook("long book"));

    }

    @Test
    public void addBookTest() {
        ArrayList<Book> emptyExpect = new ArrayList<>();

        //empty shelf
        emptyExpect.add(shortBook);
        emptyShelf.addBook(shortBook);
        assertEquals(emptyExpect, emptyShelf.getBooks());
        //repeated additions
        emptyExpect.add(longBook);
        emptyExpect.add(midBook);
        emptyShelf.addBook(longBook);
        emptyShelf.addBook(midBook);
        assertEquals(emptyExpect, emptyShelf.getBooks());
    }

    @Test
    public void sortShelfTest() {
        //TODO
        //case where shelf has only one or less elements
        //case where the shelf won't need sorting
        //case where shelf gets sorted
    }

    @Test
    public void renameShelfTest() {
        //case where name remains the same
        smallShelf.renameShelf("Small Shelf");
        assertEquals("Small Shelf", smallShelf.getShelfName());
        //case where capitalization different
        emptyShelf.renameShelf("empty shelf");
        assertEquals("empty shelf", emptyShelf.getShelfName());
        //case where name different
        packedShelf.renameShelf("A big shelf");
        assertEquals("A big shelf", packedShelf.getShelfName());
    }

    @Test
    public void bookMoverTest() { //TODO - review case coverage
        ArrayList expectedBooks = new ArrayList<Book>();
        expectedBooks.add(shortBook);
        expectedBooks.add(longBook);

        //case on shelf with many books
        smallShelf.addBook(shortBook);
        smallShelf.addBook(midBook);
        smallShelf.addBook(longBook);
        assertEquals(midBook, smallShelf.bookMover("mid book"));
        assertEquals(expectedBooks, smallShelf.getBooks());
        //repeated usage
        expectedBooks.remove(0);
        assertEquals(shortBook, smallShelf.bookMover("short book"));
        assertEquals(expectedBooks, smallShelf.getBooks());
        //case where used on shelf with one book
        expectedBooks.remove(0);
        assertEquals(longBook, smallShelf.bookMover("long book"));
        assertEquals(expectedBooks, smallShelf.getBooks());
        //case where book not found
        try {
            smallShelf.addBook(shortBook);
            smallShelf.addBook(midBook);
            smallShelf.bookMover("Curveball");
            //Implement failure later - robustness. fail("Exception expected.");
        } catch (Exception e) {
            //pass
        }
    }

    @Test
    public void convertToJsonTest() {
        JSONObject json = smallShelf.convertToJson();
        JSONArray jArray = json.getJSONArray("books");
        assertEquals(json.getString("shelfName"), smallShelf.getShelfName());
        assertEquals(jArray.length(), smallShelf.getBooks().size());
    }
}
