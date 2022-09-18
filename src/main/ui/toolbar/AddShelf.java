package ui.toolbar;

import model.Bookshelf;
import ui.CollectionManager;
import ui.Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//The AddShelf class contains the button and functionality for adding a shelf to the collection and refreshing it.
public class AddShelf extends ToolBar {

    //Constructor
    public AddShelf(CollectionManager source, JComponent buttonPanel) {
        super(source, buttonPanel);
    }

    //MODIFIES: this.
    //EFFECTS: creates add shelf button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Shelf");
        addToParent(parent);
    }

    //EFFECTS: adds a shelf onto the collection and renders again.
    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Bookshelf collection = collectionManager.getCollection();
                shelfNamePrompt(collection);
            }
        });
    }

    //EFFECTS: opens a pop-up window for user to type shelf name, returns user input.
    private void shelfNamePrompt(Bookshelf collection) {
        JFrame popup = new JFrame();
        JPanel textQuery = new JPanel();
        JTextField textField = new JTextField(16);
        JLabel userInstructions = new JLabel("Press confirm to add shelf with current text.");
        JButton submitName = new JButton("Confirm");
        textQuery.add(textField);
        textQuery.add(userInstructions);
        textQuery.add(submitName);
        processButton(submitName, textField, userInstructions, collection);
        popup.setSize(400, 300);
        popup.add(textQuery);
        popup.setVisible(true);
    }

    //MODIFIES: CollectionManager.
    //EFFECTS: adds shelf, updates CollectionManager, and re-renders VIA Interface.
    private void processButton(JButton submitName, JTextField textField, JLabel userInstructions,
                               Bookshelf collection) {
        submitName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("")) {
                    collection.addShelf(textField.getText());
                    textField.setText("");
                    userInstructions.setText("Shelf added. Enter name and press confirm to add more shelves.");
                    collectionManager.refresh(collection);
                } else {
                    userInstructions.setText("Please enter a valid string.");
                }
            }
        });
    }
}
