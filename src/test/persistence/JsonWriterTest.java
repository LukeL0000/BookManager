package persistence;

import model.Bookshelf;
import model.persistence.JsonReader;
import model.persistence.JsonWriter;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void invalidFileTest() {
        Bookshelf bookshelf = new Bookshelf();
        JsonWriter jsonWriter = new JsonWriter("./data/pinkfluffy:_:unicorn!!!!!.json");
        try {
            jsonWriter.openPrintWriter();
            jsonWriter.writeToFile(bookshelf);
            jsonWriter.closeWriter();
            fail("Exception expected.");
        } catch (FileNotFoundException e) {
        }
    }

    @Test
    void emptyDataTest() {
        Bookshelf bookshelf = new Bookshelf();
        JsonWriter jsonWriter = new JsonWriter("./data/testWritingEmptyBookshelf.json");
        try {
            jsonWriter.openPrintWriter();
            jsonWriter.writeToFile(bookshelf);
            jsonWriter.closeWriter();
        } catch (FileNotFoundException e) {
            fail("No exceptions expected.");
        }

        JsonReader jsonReader = new JsonReader("./data/testWritingEmptyBookshelf.json");
        Bookshelf expectBookshelf = new Bookshelf();
        try {
            Bookshelf readBookshelf = jsonReader.readFile();
            assertEquals(expectBookshelf.getShelves().size(), readBookshelf.getShelves().size());
        } catch (IOException e) {
            fail("No exceptions expected.");
        }

    }

    @Test
    void normalDataTest() {
        Bookshelf bookshelf = new Bookshelf();
        JsonWriter jsonWriter = new JsonWriter("./data/testWritingNormalBookshelf.json");
        JsonReader reader = new JsonReader("./data/testWritingNormalBookshelf.json");

        bookshelf.addShelf("shelf1");
        bookshelf.addShelf("shelf2");
        bookshelf.addShelf("shelf3");
        bookshelf.addShelf("shelf4");
        bookshelf.addBook("Eighty Six", "Asato Asato", "Sci-Fi", 200, "shelf1");
        bookshelf.addBook("1984", "George Orwell", "Sci-Fi", 300, "shelf1");
        bookshelf.addBook("All Quiet On the Western Front", "Erich Maria Remarque",
                "Historical Fiction", 250, "shelf 1");
        bookshelf.addBook("I Want to Eat Your Pancreas", "Yoru Sumino",
                "Romance",250, "shelf2");
        bookshelf.addBook("Xenocide", "Orson Scott Card", "Sci-Fi", 600, "shelf2");
        bookshelf.addBook("Legend of the Galactic Heroes - Dawn", "Yoshiki Tanaka",
                "Sci-Fi", 300, "shelf3");

        try {
            jsonWriter.openPrintWriter();
            jsonWriter.writeToFile(bookshelf);
            jsonWriter.closeWriter();
        } catch (FileNotFoundException e) {
            fail("No exceptions expected.");
        }

        try {
            Bookshelf readBookshelf = reader.readFile();
            assertEquals(4, readBookshelf.getShelves().size());
            assertEquals("shelf1", readBookshelf.getShelves().get(0).getShelfName());
            assertEquals(3, readBookshelf.getShelves().get(0).getBooks().size());
            assertEquals("shelf4", readBookshelf.getShelves().get(3).getShelfName());
            assertEquals(0, readBookshelf.getShelves().get(3).getBooks().size());
        } catch(Exception e) {
            fail("No exceptions expected.");
        }
    }
}
