package model;

import org.json.JSONArray;
import org.json.JSONObject;
import model.persistence.Writer;

import java.util.ArrayList;

//Bookshelf class is primarily associated with user interaction component, and deals with higher-level organization.
//It adds and sorts shelves, and initiates the search for a given book. Also accessed to move books between shelves.
public class Bookshelf implements Writer {

    private ArrayList<Shelf> shelves;

    //Constructor
    public Bookshelf() {
        shelves = new ArrayList<Shelf>();
    }

    //REQUIRES: non-empty string.
    //MODIFIES: this.
    //EFFECTS: adds a new shelf.
    public void addShelf(String name) {
        Shelf newShelf = new Shelf(name);
        shelves.add(newShelf);
        EventLog.getInstance().logEvent(new Event("Added a shelf named " + name + " to the collection."));
    }

    //REQUIRES: location is within boundaries of list of shelves, there are at least 2 shelves, both exist.
    //MODIFIES: this.
    //EFFECTS: moves a shelf to specified location
    public void moveShelf(String shelf, String location) {
        int moveFromIndex = findShelfIndex(shelf);
        int moveToIndex = findShelfIndex(location);
        shelves.add(moveToIndex, shelves.get(moveFromIndex));
        if (moveFromIndex > moveToIndex) {
            shelves.remove(moveFromIndex + 1);
        } else {
            shelves.remove(moveFromIndex);
        }
        EventLog.getInstance().logEvent(new Event("Moved shelf named " + shelf + " to " + location + "."));

    }

    //REQUIRES: non-empty string inputs and non-zero integer, location must be a valid shelf. book must not be in shelf.
    //MODIFIES: this, Book.
    //EFFECTS: makes a book and adds it to the specified shelf.
    public void addBook(String title, String author, String genre, int pages, String location) {
        int insertLocation = findShelfIndex(location);
        Book addedBook = new Book(title, author, genre, pages);
        shelves.get(insertLocation).getBooks().add(addedBook);
        EventLog.getInstance().logEvent(new Event("Book with title " + title + " added to collection."));
    }

    //REQUIRES: integer within bounds of array index, book with string name exists.
    //MODIFIES: this.
    //EFFECTS: moves a single book to indicated shelf. call using shelf to move to correct shelf location.
    public void moveBook(String name, String position) {
        int targetShelf = findShelfIndex(position);
        Shelf originShelf = shelves.get(findShelfWithBook(name));
        Book targetBook = originShelf.bookMover(name);
        shelves.get(targetShelf).addBook(targetBook);
        EventLog.getInstance().logEvent(new Event("Book with title " + name + " moved to " + position + "."));
    }

    //EFFECTS: calls Shelf to find and display book with given title.
    public ArrayList<String> findBook(String title) {
        ArrayList bookInformation = new ArrayList<String>();
        int targetIndex = findShelfWithBook(title);
        Shelf targetShelf = shelves.get(targetIndex);
        bookInformation = targetShelf.findBook(title);
        if (bookInformation.size() == 4) {
            bookInformation.add("Location: " + targetShelf.getShelfName());
        }
        EventLog.getInstance().logEvent(new Event("Found book with title " + title
                + " in " + targetShelf.getShelfName()));
        return bookInformation;
    }

    //EFFECTS: returns object information as JSON object.
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        JSONArray arrayShelf = new JSONArray();
        for (int i = 0; i < shelves.size(); i++) {
            arrayShelf.put(shelves.get(i).convertToJson());
        }
        json.put("shelves", arrayShelf);
        return json;
    }

    //REQUIRES: bookshelf contains at least one shelf.
    //EFFECTS: displays contents of entire bookshelf.
    public ArrayList<String> displayBookshelf() {
        ArrayList displayContent = new ArrayList<String>();
        for (int i = 0; i < shelves.size(); i++) {
            displayContent.add(shelves.get(i).getShelfName() + ": " + shelves.get(i).getBookList());
        }
        return displayContent;
    }

    //EFFECTS: returns list of shelves in bookshelf.
    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

    //REQUIRES: shelfName must be actual name of a shelf
    //EFFECTS: returns the array index position of shelf with given name
    private int findShelfIndex(String shelfName) {
        int shelfIndex = 0;
        for (int i = 0; i < shelves.size(); i++) {
            if ((shelves.get(i).getShelfName()).equals(shelfName)) {
                shelfIndex = i;
                break;
            }
        }
        return shelfIndex;
    }

    //REQUIRES: bookName is name of existing book
    //EFFECTS: returns shelf index that contains book
    private int findShelfWithBook(String bookName) {
        int shelfIndex = 0;

        for (int i = 0; i < shelves.size(); i++) {
            if (shelves.get(i).getBookList().contains(bookName)) {
                shelfIndex = i;
                break;
            }
        }
        return shelfIndex;
    }
}
