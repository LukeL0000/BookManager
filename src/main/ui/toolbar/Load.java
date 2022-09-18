package ui.toolbar;

import model.Bookshelf;
import model.persistence.JsonReader;
import ui.CollectionManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//The Load class contains the button and functionality to load information into the program and update it.
public class Load extends ToolBar {

    private Bookshelf collection;

    private static final String PERSISTENCE = "./data/Bookshelf.json";
    private JsonReader jsonLoader = new JsonReader(PERSISTENCE);

    //Constructor
    public Load(CollectionManager source, JComponent buttonPanel) {
        super(source, buttonPanel);
    }

    //MODIFIES: this.
    //EFFECTS: creates a button for loading
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load");
        addToParent(parent);
    }

    //MODIFIES: CollectionManager
    //EFFECTS: once button pressed, the book collection will be loaded and rendered.
    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    collection = jsonLoader.readFile();
                    collectionManager.refresh(collection);
                } catch (IOException ioe) {
                    System.out.println("Unable to load - load location invalid.");
                }
            }
        });
    }
}

