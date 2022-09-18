package model.persistence;

import model.*;
import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//The JsonReader class takes a json from the data folder and parses it onto a bookshelf object.
public class JsonReader {

    private String source;
    private Bookshelf bookshelf;

    //EFFECTS: constructs a JsonReader pointed to source location
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads json file and returns it as bookshelf object.
    public Bookshelf readFile() throws IOException {
        bookshelf = new Bookshelf();
        JSONObject jsonData = readJson(source);
        EventLog.getInstance().logEvent(new Event("Loaded JSON data for parsing."));
        return parseShelf(jsonData, bookshelf);
    }

    //EFFECTS: parses the json object by adding shelves and books and returns a bookshelf.
    private Bookshelf parseShelf(JSONObject jsonObject, Bookshelf bookshelf) {
        JSONArray shelfList = jsonObject.getJSONArray("shelves");

        for (Object json : shelfList) {
            JSONObject shelf = (JSONObject) json; //casted to json object
            JSONArray books = shelf.getJSONArray("books");

            String shelfName = shelf.getString("shelfName");
            bookshelf.addShelf(shelfName);

            parseBooks(books, bookshelf, shelfName);
        }
        return bookshelf;
    }

    //EFFECTS: parses the json object by adding books to the current shelf.
    private void parseBooks(JSONArray books, Bookshelf bookshelf, String shelf) {

        for (Object json : books) {
            JSONObject book = (JSONObject) json; //casted to json object
            String title = book.getString("title");
            String author = book.getString("author");
            String genre = book.getString("genre");
            int pages = book.getInt("pages");

            bookshelf.addBook(title, author, genre, pages, shelf);
        }
    }

    //EFFECTS: returns a JSON object from file path specified by source.
    private JSONObject readJson(String source) throws IOException {
        Path filename = Paths.get(source);
        String content = new String(Files.readAllBytes(filename));
        return new JSONObject(content);
    }

}
