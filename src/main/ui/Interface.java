package ui;

import model.Book;
import model.Bookshelf;
import model.Shelf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//The Interface class draws the entire book collection and some associated info.
public class Interface extends JPanel {

    private static int SHELF_HEIGHT = 100;
    private static int SHELF_WIDTH = 600;
    private static int BOOK_HEIGHT = 65;
    private static int BOOK_WIDTH = 25;
    private static int RENDER_VERT = 100;
    private static int RENDER_HORZ = 90;

    private ArrayList<Shelf> shelves;

    //Constructor
    public Interface(Bookshelf collection) {
        shelves = collection.getShelves();
    }

    //EFFECTS: renders the entire book collection.
    @Override
    protected void paintComponent(Graphics g) {
        int vertLocation = RENDER_VERT;
        int horzLocation = RENDER_HORZ;

        super.paintComponent(g);
        if (!shelves.isEmpty()) {
            for (Shelf shelf : shelves) {
                drawShelf(g, vertLocation, horzLocation, shelf);
                vertLocation += SHELF_HEIGHT;
            }
        } // TODO - empty message or loading prompt should be here.

    }

    //EFFECTS: draws a shelf
    private void drawShelf(Graphics g, int vertLocation, int horzLocation, Shelf shelf) {
        g.drawRect(RENDER_HORZ, vertLocation, SHELF_WIDTH, SHELF_HEIGHT);
        g.drawString(shelf.getShelfName(), RENDER_HORZ + 5, vertLocation + SHELF_HEIGHT - 5);
        drawBookList(g, vertLocation, horzLocation, shelf);
        drawBooks(g, vertLocation, horzLocation, shelf);
    }

    //EFFECTS: draws the booklist.
    private void drawBookList(Graphics g, int vertLocation, int horzLocation, Shelf shelf) {
        ArrayList<String> bookList = shelf.getBookList();
        String listText = "";
        String listTextRow2 = "";
        int padding = 55;
        g.drawString("Books: ", horzLocation + 5, vertLocation + 15);
        for (String title : bookList) {
            if (listText.length() < 90) {
                listText = listText + title + ", ";
            } else {
                listTextRow2 = listTextRow2 + title + ", ";
            }

        }
        g.drawString(listText, horzLocation + padding, vertLocation + 15);
        g.drawString(listTextRow2, horzLocation + padding, vertLocation + 30);
    }

    //EFFECTS: starts to draw a list of books in shelf
    private void drawBooks(Graphics g, int vertLocation, int horzLocation, Shelf shelf) {
        ArrayList<Book> books = shelf.getBooks();
        for (Book book : books) {
            drawBook(g, vertLocation, horzLocation, book);
            horzLocation += BOOK_WIDTH;
        }
        horzLocation = RENDER_HORZ;
    }

    //EFFECTS: draws a book
    private void drawBook(Graphics g, int vertLocation, int horzLocation, Book book) {
        int vertCoords = vertLocation + SHELF_HEIGHT - BOOK_HEIGHT;
        g.drawRect(horzLocation, vertCoords, BOOK_WIDTH, BOOK_HEIGHT);
    }
}
