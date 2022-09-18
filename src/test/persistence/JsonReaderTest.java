package persistence;

import model.Bookshelf;
import model.persistence.JsonReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void nonExistentDataTest() {
        JsonReader reader = new JsonReader("./data/unicornfile.json");
        try {
            Bookshelf bookshelf = reader.readFile();
            fail("Exception expected.");
        } catch(Exception e) {
            //passes.
        }
    }

    @Test
    void emptyDataTest() {
        JsonReader reader = new JsonReader("./data/testReadingEmptyBookshelf.json");
        try {
            Bookshelf bookshelf = reader.readFile();
            assertEquals(0, bookshelf.getShelves().size());
        } catch(Exception e) {
            fail("No exceptions expected.");
        }
    }

    @Test
    void normalDataTest() {
        JsonReader reader = new JsonReader("./data/testReadingNormalBookshelf.json");
        try {
            Bookshelf bookshelf = reader.readFile();
            assertEquals(4, bookshelf.getShelves().size());
            assertEquals("shelf1", bookshelf.getShelves().get(0).getShelfName());
            assertEquals(3, bookshelf.getShelves().get(0).getBooks().size());
            assertEquals("shelf4", bookshelf.getShelves().get(3).getShelfName());
            assertEquals(0, bookshelf.getShelves().get(3).getBooks().size());
        } catch(Exception e) {
            fail("No exceptions expected.");
        }
    }
}
