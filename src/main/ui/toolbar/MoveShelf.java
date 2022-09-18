package ui.toolbar;

import model.Bookshelf;
import ui.CollectionManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//The MoveShelf class contains the functionality to move a shelf and refresh the collection.
public class MoveShelf extends ToolBar {

    //Constructor.
    public MoveShelf(CollectionManager source, JComponent buttonPanel) {
        super(source, buttonPanel);
    }

    //MODIFIES: this.
    //EFFECTS: creates button for moving shelf.
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Move Shelf");
        addToParent(parent);
    }

    //EFFECTS: moves the shelf to new location and re-renders book collection.
    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Bookshelf collection = collectionManager.getCollection();
                movingTargetsPrompt(collection);
            }
        });
    }

    //EFFECTS: opens pop-up, calls function that moves shelf according to specifications.
    private void movingTargetsPrompt(Bookshelf collection) {
        JFrame popup = new JFrame();
        JPanel moveQuery = new JPanel();
        JTextField originField = new JTextField("Enter name of shelf to move: ", 24);
        JTextField destField = new JTextField("Enter name of shelf to move to: ", 24);
        JLabel userInstructions = new JLabel("Press confirm to move the specified shelf.");
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
                String movedShelf = originField.getText();
                String locationShelf = destField.getText();
                collection.moveShelf(movedShelf, locationShelf);
                userInstructions.setText("To move more shelves, enter shelf names and press confirm.");
                collectionManager.refresh(collection);
            }
        });
    }
}
