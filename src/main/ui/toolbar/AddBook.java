package ui.toolbar;

import model.Bookshelf;
import ui.CollectionManager;
import ui.Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//The AddBook class contains the button and functionality for adding a book into the collection and refreshing it.
public class AddBook extends ToolBar {

    public AddBook(CollectionManager source, JComponent buttonPanel) {
        super(source, buttonPanel);
    }

    //MODIFIES: this.
    //EFFECTS: creates the button.
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Book");
        addToParent(parent);
    }

    //MODIFIES: this.
    //EFFECTS: adds a book into the collection and renders again.
    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Bookshelf collection = collectionManager.getCollection();
                bookInfoPrompt(collection);
            }
        });
    }

    //EFFECTS: opens a pop-up to get info, and then calls a function that adds the book.
    private void bookInfoPrompt(Bookshelf collection) {
        JFrame popup = new JFrame();
        JPanel textQuery = new JPanel();
        JTextField titleField = new JTextField("Enter book title: ", 24);
        JTextField authorField = new JTextField("Enter book author: ", 24);
        JTextField genreField = new JTextField("Enter book genre: ", 24);
        JTextField pagesField = new JTextField("Enter number (integer) number of pages: ", 24);
        JTextField targetField = new JTextField("Enter name of shelf to add book: ", 24);
        JLabel userInstructions = new JLabel("Press confirm to add book with given information.");
        JButton submitInfo = new JButton("Confirm");
        textQuery.add(titleField);
        textQuery.add(authorField);
        textQuery.add(genreField);
        textQuery.add(pagesField);
        textQuery.add(targetField);
        textQuery.add(userInstructions);
        textQuery.add(submitInfo);
        popup.setSize(450, 350);
        popup.add(textQuery);
        popup.setVisible(true);
        processButton(submitInfo, titleField, authorField, genreField, pagesField,
                targetField, userInstructions, collection);
    }

    //MODIFIES: CollectionManager
    //EFFECTS: adds book to collection and then re-renders it.
    private void processButton(JButton submitInfo, JTextField titleField, JTextField authorField,
                               JTextField genreField, JTextField pagesField, JTextField targetField,
                               JLabel userInstructions, Bookshelf collection) {
        submitInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = titleField.getText();
                String bookAuthor = authorField.getText();
                String bookGenre = genreField.getText();
                String destinationShelf = targetField.getText();
                int bookPages = 0;
                try {
                    bookPages = Integer.parseInt(pagesField.getText());
                } catch (Exception exc) {
                    pagesField.setText("Please enter valid integer.");
                }
                try {
                    collection.addBook(bookTitle, bookAuthor, bookGenre, bookPages, destinationShelf);
                    userInstructions.setText("Book successfully added. Use boxes to keep adding.");
                } catch (IndexOutOfBoundsException exc2) {
                    System.out.println("Bookshelf does not exist. \n");
                }
                collectionManager.refresh(collection);
            }
        });
    }
}
