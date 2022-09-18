package model;

import org.json.JSONObject;
import model.persistence.Writer;

import java.util.ArrayList;

//Shelf extends bookshelf, and deals more directly with the organization of the books. In essence, each shelf is a
// "row" of books while the bookshelf is a collection of said rows.
public class Shelf extends Bookshelf implements Writer {

    private String shelfName;
    private ArrayList<Book> books;

    public Shelf(String name) {
        this.shelfName = name;
        books = new ArrayList<>();
    }

    //EFFECTS: finds and displays book with given title and information, and location in bookshelf.
    public ArrayList<String> findBook(String title) {
        ArrayList<String> foundBook = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (title.equals(currentBook.getTitle())) {
                foundBook = currentBook.returnBookInfo();
                break;
            }
        }
        return foundBook;
    }

    //MODIFIES: this.
    //EFFECTS: adds a book to the shelf.
    public void addBook(Book book) {
        this.books.add(book);
    }

//    //REQUIRES: string is a valid metric.
//    //MODIFIES: this.
//    //EFFECTS: sorts the shelf according to specified method.
//    public void sortShelf(String metric) {
//        //TODO
//    }

    //REQUIRES: non-empty string.
    //MODIFIES: this.
    //EFFECTS: changes name to specified value.
    public void renameShelf(String name) {
        this.shelfName = name;
    }

    //REQUIRES: book must exist in shelf.
    //MODIFIES: this.
    //EFFECTS: finds and returns the book with specified title, and removes it from the shelf.
    public Book bookMover(String title) {
        Book targetBook = null;
        Book currentBook = null;
        int targetIndex = 0;
        for (int i = 0; i < books.size(); i++) {
            currentBook = books.get(i);
            if (title.equals(currentBook.getTitle())) {
                targetBook = currentBook;
                targetIndex = i;
                break;
            }
        }
        books.remove(targetIndex);
        return targetBook;
    }

    //EFFECTS: returns object information as JSON object.
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("shelfName", shelfName);
        json.put("books", books);
        return json;
    }

    //EFFECTS: returns list of contents in shelf
    public ArrayList<Book> getBooks() {
        return books;
    }

    //EFFECTS: returns name of shelf
    public String getShelfName() {
        return shelfName;
    }

    //EFFECTS: returns list of books in shelf
    public ArrayList<String> getBookList() {
        ArrayList<String> bookList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            bookList.add(books.get(i).getTitle());
        }
        return bookList;
    }
}
