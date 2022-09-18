package ui.legacy;

import model.Bookshelf;
import model.persistence.JsonReader;
import model.persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.omg.CORBA.ORB.init;

//The UserConsole class deals with UI. (Legacy console.)
public class UserConsole {
    private static final String PERSISTENCE = "./data/Bookshelf.json";
    private Bookshelf collection;
    private JsonWriter jsonSaver;
    private JsonReader jsonLoader;
    private Scanner userInput;
    private String showCollectionCommand = "SC";
    private String findBookCommand = "FB";
    private String addBookCommand = "AB";
    private String addShelfCommand = "AS";
    private String updateBookCommand = "UB";
    private String updateShelfCommand = "US";
    private String moveBookCommand = "MB";
    private String moveShelfCommand = "MS";
    private String saveCollectionCommand = "SAVE";
    private String loadCollectionCommand = "LOAD";
    private String exitCommand = "EXIT";

    //EFFECTS: starts the console
    public UserConsole() {
        startConsole();
    }

    //MODIFIES: this.
    //EFFECTS: runs other methods based on user input
    private void startConsole() {
        boolean runConsole = true;
        String userCommand = null;
        init();

        while (runConsole) {
            userMenu();
            userCommand = userInput.next();
            userCommand = userCommand.toUpperCase();

            if (userCommand.equals(exitCommand)) {
                runConsole = false;
                showSavePrompt();
            } else {
                executeCommand(userCommand);
            }
        }

        System.out.println("Application finished.");

        //TODO - UI.
        //implement find and display book
        //implement display book collection
        //implement add book
        //implement add shelf
        //implement move book
        //implement move shelf
        //implement saving
        //implement loading
        //implement save prompt
        //implement load prompt
        //TODO implement update book info
        //TODO implement update shelf info
        //TODO implement read book
        //TODO implement display book progress
    }

    //MODIFIES: this.
    //EFFECTS: instantiates necessary variables.
    private void init() {
        collection = new Bookshelf();
        jsonSaver = new JsonWriter(PERSISTENCE);
        jsonLoader = new JsonReader(PERSISTENCE);
        userInput = new Scanner(System.in);
        userInput.useDelimiter("\n");
    }

    //EFFECTS: displays valid commands
    private void userMenu() {
        System.out.println("Please select an operation: ");
        System.out.println(showCollectionCommand + " - show entire collection.");
        System.out.println(findBookCommand + " - find a book in the collection.");
        System.out.println(addBookCommand + " - add a book into the collection.");
        System.out.println(addShelfCommand + " - add a shelf into the collection.");
//        System.out.println(updateBookCommand + " - update information about a book in the collection.");
//        System.out.println(updateShelfCommand + " - update information about a shelf in the collection.");
        System.out.println(moveBookCommand + " - move a book to a different shelf in the collection.");
        System.out.println(moveShelfCommand + " - move a shelf to a different location in the collection.");
        System.out.println(saveCollectionCommand + " - save the current book collection.");
        System.out.println(loadCollectionCommand + " - load a previously saved book collection.");
        System.out.println(exitCommand + " - close the application.");
    }

    //EFFECTS: displays a prompt for user to save.
    private void showSavePrompt() {
        System.out.println("Save collection? yes/no");
        String input = userInput.next();

        if (input.equalsIgnoreCase("YES")) {
            saveCollection();
        } else if (!input.equalsIgnoreCase("NO")) {
            System.out.println("Please enter either yes or no. \n");
            showSavePrompt();
        }
    }

    //EFFECTS: displays a prompt for user to load.
    private void showLoadPrompt() {
        System.out.println("Try loading a collection? yes/no");
        String input = userInput.next();

        if (input.equalsIgnoreCase("YES")) {
            loadCollection();
        } else if (!input.equalsIgnoreCase("NO")) {
            System.out.println("Please enter either yes or no. \n");
            showLoadPrompt();
        } else {
            System.out.println("Okay, then try adding a few shelves, and then some books. \n");
        }
    }

    //MODIFIES: this.
    //EFFECTS: executes the user's command, or returns false if it doesn't exist.
    private void executeCommand(String command) {
        if (command.equals(showCollectionCommand)) {
            showCollection();
        } else if (command.equals(findBookCommand)) {
            findBook();
        } else if (command.equals(addBookCommand)) {
            addBook();
        } else if (command.equals(addShelfCommand)) {
            addShelf();
        } else if (command.equals(moveBookCommand)) {
            moveBook();
        } else if (command.equals(moveShelfCommand)) {
            moveShelf();
        } else if (command.equals(saveCollectionCommand)) {
            saveCollection();
        } else if (command.equals(loadCollectionCommand)) {
            loadCollection();
        } else {
            System.out.println("Please enter a valid command. \n");
        }
    }

    //EFFECTS: shows the entire book collection.
    private void showCollection() {

        if (collection.displayBookshelf().size() == 0) {
            System.out.println("Your collection is empty!");
            showLoadPrompt();
        } else {
            System.out.println("Your book collection: \n");
            System.out.println(collection.displayBookshelf());
            System.out.println();
        }
    }

    //EFFECTS: finds and displays book information, returns "Book not found" if book doesn't exist.
    private void findBook() {
        String bookTitle;

        System.out.println("Enter book title: ");
        bookTitle = userInput.next();

        try {
            if (collection.findBook(bookTitle).size() == 0) {
                System.out.println("Book not found. \n");
            } else {
                System.out.println(collection.findBook(bookTitle));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Book not found. \n");
        }
    }

    //REQUIRES: bookPages valid integer, all strings are non-empty, no books with same title already in collection.
    //MODIFIES: this.
    //EFFECTS: adds a book into the collection at specified shelf, returns "shelf does not exist" if it doesn't exist.
    private void addBook() {
        String bookTitle;
        String bookAuthor;
        String bookGenre;
        int bookPages = 0; //TODO - try-catch this later.
        String destinationShelf;

        System.out.println("Enter book title: ");
        bookTitle = userInput.next();
        System.out.println("Enter book author: ");
        bookAuthor = userInput.next();
        System.out.println("Enter book genre: ");
        bookGenre = userInput.next();
        System.out.println("Enter number of pages: ");
        bookPages = userInput.nextInt();
        System.out.println("Enter name of shelf where book will be added: ");
        destinationShelf = userInput.next();
        try {
            collection.addBook(bookTitle, bookAuthor, bookGenre, bookPages, destinationShelf);
            System.out.println("Book successfully added to: " + destinationShelf + "\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Bookshelf does not exist. \n");
        }
    }

    //REQUIRES: non-empty string.
    //MODIFIES: this.
    //EFFECTS: adds a shelf with given name.
    private void addShelf() {
        String shelfName; //TODO - try-catch for string input

        System.out.println("Enter name of shelf to add: ");
        shelfName = userInput.next();

        collection.addShelf(shelfName);

        System.out.println("Shelf successfully added. \n");
    }

    //REQUIRES: specified book and shelf must exist. //TODO - resolve null pointer exception
    //MODIFIES: this.
    //EFFECTS: moves book to specified location.
    private void moveBook() {
        String bookName; //TODO - try-catch for invalid inputs.
        String destinationShelf;

        System.out.println("Enter name of book to move: ");
        bookName = userInput.next();
        System.out.println("Enter destination shelf: ");
        destinationShelf = userInput.next();

        collection.moveBook(bookName, destinationShelf);

        System.out.println("Book successfully moved to: " + destinationShelf + "\n");
    }

    //Requires: both shelves must exist.
    //MODIFIES: this.
    //EFFECTS: moves specified shelf to location of another specified shelf.
    private void moveShelf() {
        String movedShelf; //TODO - try-catch for invalid inputs.
        String locationShelf;

        System.out.println("Enter shelf to move: ");
        movedShelf = userInput.next();
        System.out.println("Enter the location shelf name: ");
        locationShelf = userInput.next();

        collection.moveShelf(movedShelf, locationShelf);

        System.out.println("Shelf successfully moved next to: " + locationShelf + "\n");
    }

    //MODIFIES: Bookshelf.json
    //EFFECTS: saves the book collection.
    private void saveCollection() {
        try {
            jsonSaver.openPrintWriter();
            jsonSaver.writeToFile(collection);
            jsonSaver.closeWriter();
            System.out.println("Collection saved successfully. \n");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save - save location invalid.");
        }

    }

    //MODIFIES: this.
    //EFFECTS: loads the previously saved collection.
    private void loadCollection() {
        try {
            collection = jsonLoader.readFile();
            System.out.println("Collection loaded successfully. \n");
        } catch (IOException e) {
            System.out.println("Unable to load - load location invalid.");
        }

    }
}
