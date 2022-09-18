package ui.toolbar;

import ui.CollectionManager;

import javax.swing.*;
import java.awt.event.MouseEvent;

//This is an abstract class that facilitates creation of buttons.
public abstract class ToolBar {

    // Creates a new button.
    protected JButton button;

    protected CollectionManager collectionManager;

    //Constructor
    public ToolBar(CollectionManager source, JComponent buttonPanel) {
        this.collectionManager = source;
        createButton(buttonPanel);
        addToParent(buttonPanel);
        addListener();
    }

    //MODIFIES: this.
    //EFFECTS: creates a button
    protected abstract void createButton(JComponent parent);

    //MODIFIES: this.
    //EFFECTS: result on button press
    protected abstract void addListener();

    //MODIFIES: CollectionManager
    //EFFECTS: adds button to menu
    public void addToParent(JComponent parent) {
        parent.add(button);
    }
}

