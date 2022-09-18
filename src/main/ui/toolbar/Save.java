package ui.toolbar;

import model.Bookshelf;
import model.persistence.JsonWriter;
import ui.CollectionManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

//The Save class contains the functionality to save the current information into a file to be loaded later.
public class Save extends ToolBar {

    private static final String PERSISTENCE = "./data/Bookshelf.json";
    private JsonWriter jsonSaver = new JsonWriter(PERSISTENCE);

    //Constructor
    public Save(CollectionManager source, JComponent buttonPanel) {
        super(source, buttonPanel);
    }

    //MODIFIES: this.
    //EFFECTS: creates a save button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save");
        addToParent(parent);
    }

    //MODIFIES: Bookshelf.json
    //EFFECTS: saves the current collection onto json file.
    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Bookshelf collection = collectionManager.getCollection();
                try {
                    jsonSaver.openPrintWriter();
                    jsonSaver.writeToFile(collection);
                    jsonSaver.closeWriter();
                } catch (FileNotFoundException fnfe) {
                    System.out.println("Unable to save - save location invalid.");
                }
            }
        });
    }
}
