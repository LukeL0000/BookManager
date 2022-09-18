package ui.toolbar;

import model.Bookshelf;
import ui.CollectionManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//The MoveBook class contains the button and functionality to move a book and refresh the collection.
public class MoveBook extends ToolBar {

    //Constructor.
    public MoveBook(CollectionManager source, JComponent buttonPanel) {
        super(source, buttonPanel);
    }

    //MODIFIES: this.
    //EFFECTS: creates button for moving a book.
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Move Book");
        addToParent(parent);
    }

    //EFFECTS: opens pop-up, moves book according to specifications.
    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Bookshelf collection = collectionManager.getCollection();
                movingTargetsPrompt(collection);
            }
        });
    }

    //EFFECTS: opens pop-up, calls function that moves book according to specifications.
    private void movingTargetsPrompt(Bookshelf collection) {
        JFrame popup = new JFrame();
        JPanel moveQuery = new JPanel();
        JTextField originField = new JTextField("Enter name of book to move: ", 24);
        JTextField destField = new JTextField("Enter name of shelf to move to: ", 24);
        JLabel userInstructions = new JLabel("Press confirm to move the specified book.");
        JButton moveShelves = new JButton("Confirm");
        moveQuery.add(originField);
        moveQuery.add(destField);
        moveQuery.add(userInstructions);
        moveQuery.add(moveShelves);
        popup.setSize(400, 300);
        popup.add(moveQuery);
        popup.setVisible(true);
        processMove(moveShelves, collection, originField, destField, userInstructions);
    }

    //MODIFIES: CollectionManager.
    //EFFECTS: moves shelf according to specifications.
    private void processMove(JButton moveShelves, Bookshelf collection, JTextField originField, JTextField destField,
                             JLabel userInstructions) {
        moveShelves.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookName = originField.getText();
                String destinationShelf = destField.getText();
                try {
                    collection.moveBook(bookName, destinationShelf);
                    userInstructions.setText("Book successfully moved. Enter more names to continue moving.");
                    collectionManager.refresh(collection);
                } catch (IndexOutOfBoundsException exp) {
                    userInstructions.setText("Please enter a valid book and shelf name.");
                }
            }
        });
    }
}
