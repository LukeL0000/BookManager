package ui.toolbar;

import model.Bookshelf;
import ui.CollectionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//The FindBook class contains the button and necessary functionality for finding and displaying info on a book.
public class FindBook extends ToolBar {

    //Constructor
    public FindBook(CollectionManager source, JComponent buttonPanel) {
        super(source, buttonPanel);
    }

    //MODIFIES: this.
    //EFFECTS: creates a new button to find a book.
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Find Book");
        addToParent(parent);
    }

    //EFFECTS: opens a pop-up to get name of book to find, and tries to find and display the book.
    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Bookshelf collection = collectionManager.getCollection();
                bookNamePrompt(collection);
            }
        });
    }

    //EFFECTS: gets title of book to find and tries to find it.
    private void bookNamePrompt(Bookshelf collection) {
        JFrame popup = new JFrame();
        JPanel textQuery = new JPanel();
        JTextField titleField = new JTextField("Enter book title: ", 24);
        JLabel userInstructions = new JLabel("Press confirm to find book.");
        JButton submitInfo = new JButton("Confirm");
        textQuery.add(titleField);
        textQuery.add(userInstructions);
        textQuery.add(submitInfo);
        popup.setSize(350, 200);
        popup.add(textQuery);
        popup.setVisible(true);
        processButton(submitInfo, titleField, userInstructions, collection);
    }

    //EFFECTS: tries to find book with given title, will open pop-up with information if found.
    private void processButton(JButton submitInfo, JTextField titleField, JLabel userInstructions,
                               Bookshelf collection) {
        submitInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTitle = titleField.getText();

                try {
                    ArrayList<String> searchResult = collection.findBook(searchTitle);
                    if (searchResult.size() == 0) {
                        userInstructions.setText("Book not found. Title does not exist.");
                    } else {
                        showInfo(searchResult);
                        userInstructions.setText("Book found. Use boxes to continue finding.");
                    }
                } catch (IndexOutOfBoundsException exc) {
                    userInstructions.setText("Book not found. Title does not exist.");
                }
            }
        });
    }

    //EFFECTS: displays a pop-up with book's info.
    private void showInfo(ArrayList<String> bookinfo) {
        JFrame infoFrame = new JFrame();
        JPanel infoPanel = new JPanel();

        ArrayList<JLabel> labels = new ArrayList<>();

        for (String info : bookinfo) {
            JLabel label = new JLabel(info + "\n");
            label.setFont(new Font("Arial", Font.BOLD, 14));
            labels.add(label);
        }

        for (JLabel label : labels) {
            infoPanel.add(label);
        }

        infoFrame.add(infoPanel);
        infoFrame.setSize(300, 400);
        infoFrame.setVisible(true);
    }
}
