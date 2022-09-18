package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONArray;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookshelfTest {

    private int long_pages = 600;
    private int mid_pages = 400;
    private int short_pages = 200;
    Shelf shelf1 = new Shelf("Shelf 1");
    Shelf shelf2 = new Shelf("Shelf 2");
    Shelf shelf3 = new Shelf("Shelf 3");
    Shelf shelf4 = new Shelf("Shelf 4");
    Shelf shelf5 = new Shelf("Shelf 5");
    Book shortBook = new Book("short book", "short book author", "short book genre", short_pages);
    Book midBook = new Book("mid book", "mid book author", "mid book genre", mid_pages);
    Book longBook = new Book("long book", "long book author", "long book genre", long_pages);
    Bookshelf emptyBookshelf;
    Bookshelf smallBookshelf;
    Bookshelf largeBookshelf;

    @BeforeEach
    public void setup() {
        emptyBookshelf = new Bookshelf();
        smallBookshelf = new Bookshelf();
        smallBookshelf.addShelf("Shelf 1");
        smallBookshelf.addShelf("Shelf 2");
        smallBookshelf.addShelf("Shelf 3");
        smallBookshelf.addShelf("Shelf 4");
        largeBookshelf = new Bookshelf();
        largeBookshelf.addShelf("Shelf 1");
        largeBookshelf.addShelf("Shelf 2");
        largeBookshelf.addShelf("Shelf 3");
        largeBookshelf.addShelf("Shelf 4");
        largeBookshelf.addShelf("Shelf 5");
        largeBookshelf.addShelf("Shelf 6");
        largeBookshelf.addShelf("Shelf 7");
        largeBookshelf.addShelf("Shelf 8");
        largeBookshelf.addShelf("Shelf 9");

    }

    @Test
    public void addShelfTest() {
        ArrayList<Shelf> emptyExpect = emptyBookshelf.getShelves();
        ArrayList<Shelf> smallExpect = smallBookshelf.getShelves();

        //case with shelf added to empty bookshelf
        emptyExpect.add(new Shelf("Added shelf"));
        emptyBookshelf.addShelf("Added shelf");
        assertEquals(emptyExpect, emptyBookshelf.getShelves());
        //case with shelf added to normal bookshelf
        smallExpect.add(new Shelf("Added shelf"));
        smallBookshelf.addShelf("Added shelf");
        assertEquals(smallExpect, smallBookshelf.getShelves());
        //repeated addition
        smallExpect.add(new Shelf("Another shelf"));
        smallBookshelf.addShelf("Another shelf");
        assertEquals(smallExpect, smallBookshelf.getShelves());
    }

    @Test
    public void moveShelfTest() {
        ArrayList smallExpect = new ArrayList<Shelf>();
        smallExpect.add(smallBookshelf.getShelves().get(0));
        smallExpect.add(smallBookshelf.getShelves().get(2));
        smallExpect.add(smallBookshelf.getShelves().get(1));
        smallExpect.add(smallBookshelf.getShelves().get(3));
        ArrayList repeatExpect = new ArrayList<Shelf>();
        repeatExpect.add(smallBookshelf.getShelves().get(3));
        repeatExpect.add(smallBookshelf.getShelves().get(0));
        repeatExpect.add(smallBookshelf.getShelves().get(2));
        repeatExpect.add(smallBookshelf.getShelves().get(1));

        //standard usage
        smallBookshelf.moveShelf("Shelf 3", "Shelf 2");
        assertEquals(smallExpect, smallBookshelf.getShelves());
        //repeated usage
        smallBookshelf.moveShelf("Shelf 4", "Shelf 1");
        assertEquals(repeatExpect, smallBookshelf.getShelves());
        //move up
        smallExpect.remove(2);
        smallExpect.add(smallBookshelf.getShelves().get(3));
        smallBookshelf.moveShelf("Shelf 4", "Shelf 2");
        assertEquals(smallExpect, smallBookshelf.getShelves());
    }

    @Test
    public void addBookTest() {
        ArrayList shelf1Expect = shelf1.getBooks();
        ArrayList shelf2Expect = shelf2.getBooks();
        //empty shelf case
        shelf1Expect.add(shortBook);
        smallBookshelf.addBook("short book", "short book author", "short book genre", short_pages,
                "Shelf 1");
        assertEquals(shelf1Expect, shelf1.getBooks());
        assertTrue(smallBookshelf.findBook("short book").containsAll(shortBook.returnBookInfo()));
        //repeated usage - same shelf
        shelf1Expect.add(midBook);
        smallBookshelf.addBook("mid book", "mid book author", "mid book genre", mid_pages,
                "Shelf 1");
        assertEquals(shelf1Expect, shelf1.getBooks());
        assertTrue(smallBookshelf.findBook("mid book").containsAll(midBook.returnBookInfo()));
        //repeated usage - different shelf
        shelf2Expect.add(longBook);
        smallBookshelf.addBook("long book", "long book author", "long book genre", long_pages,
                "Shelf 2");
        assertEquals(shelf2Expect, shelf2.getBooks());
        assertTrue(smallBookshelf.findBook("long book").containsAll(longBook.returnBookInfo()));
    }

    @Test
    public void moveBookTest() {
        ArrayList shelf1Expect = shelf1.getBooks();
        ArrayList shelf2Expect = shelf2.getBooks();
        ArrayList shelf3Expect = shelf3.getBooks();
        ArrayList shelf4Expect = shelf4.getBooks();

        //move to empty shelf
        smallBookshelf.addBook("short book", "short book author", "short book genre", short_pages,
                "Shelf 1");
        smallBookshelf.addBook("mid book", "mid book author", "mid book genre", mid_pages,
                "Shelf 1");
        smallBookshelf.addBook("long book", "long book author", "long book genre", long_pages,
                "Shelf 3");
        shelf1Expect.add(midBook);
        shelf2Expect.add(shortBook);
        smallBookshelf.moveBook("short book", "Shelf 2");
        assertEquals(shelf1Expect, shelf1.getBooks());
        assertEquals(shelf2Expect, shelf2.getBooks());
        assertTrue(smallBookshelf.findBook("short book").containsAll(shortBook.returnBookInfo()));
        assertEquals("Location: Shelf 2", smallBookshelf.findBook("short book").get(4));
        //move to shelf with books
        shelf3Expect.add(midBook);
        shelf1Expect.remove(0);
        smallBookshelf.moveBook("mid book", "Shelf 3");
        assertEquals(shelf3Expect, shelf3.getBooks());
        assertTrue(smallBookshelf.findBook("mid book").containsAll(midBook.returnBookInfo()));
        assertEquals("Location: Shelf 3", smallBookshelf.findBook("mid book").get(4));
        //move same book twice
        shelf4Expect.add(shortBook);
        shelf2Expect.remove(0);
        smallBookshelf.moveBook("short book", "Shelf 4");
        assertEquals(shelf4Expect, shelf4.getBooks());
        assertTrue(smallBookshelf.findBook("short book").containsAll(shortBook.returnBookInfo()));
        assertEquals("Location: Shelf 4", smallBookshelf.findBook("short book").get(4));
    }

    @Test
    public void findBookTest() {
        ArrayList<String> expectedReturn = longBook.returnBookInfo();
        ArrayList<String> emptyList = new ArrayList<>();
        expectedReturn.add("Location: Shelf 3");

        //case where book found
        smallBookshelf.addBook("long book", "long book author", "long book genre", long_pages,
                "Shelf 3");
        assertEquals(expectedReturn, smallBookshelf.findBook("long book"));
        //case where book not found
        assertEquals(emptyList, smallBookshelf.findBook("the guide to sus"));
    }

    @Test
    public void displayBookshelfTest() {
        ArrayList<String> expectedList = new ArrayList<>();

        smallBookshelf.addBook("short book", "short book author", "short book genre", short_pages,
                "Shelf 1");
        smallBookshelf.addBook("mid book", "mid book author", "mid book genre", mid_pages,
                "Shelf 2");
        smallBookshelf.addBook("long book", "long book author", "long book genre", long_pages,
                "Shelf 3");

        expectedList.add("Shelf 1: " + smallBookshelf.getShelves().get(0).getBookList());
        expectedList.add("Shelf 2: " + smallBookshelf.getShelves().get(1).getBookList());
        expectedList.add("Shelf 3: " + smallBookshelf.getShelves().get(2).getBookList());
        expectedList.add("Shelf 4: " + smallBookshelf.getShelves().get(3).getBookList());

        //normal usage case
        assertEquals(expectedList, smallBookshelf.displayBookshelf());
    }

    @Test
    public void convertToJsonTest() {
        JSONObject json = smallBookshelf.convertToJson();
        JSONArray jArray = json.getJSONArray("shelves");
        assertEquals(jArray.length(), smallBookshelf.getShelves().size());
        assertEquals(jArray.getJSONObject(1).getString("shelfName"),
                smallBookshelf.getShelves().get(1).getShelfName());
    }
}
