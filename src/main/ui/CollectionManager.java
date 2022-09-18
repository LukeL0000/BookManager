package ui;

import model.Bookshelf;
import model.EventLog;
import model.persistence.JsonWriter;
import ui.toolbar.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

//The CollectionManager class is the primary user interface that accesses buttons and renderings.
public class CollectionManager extends JFrame {

    private static int SCREEN_WIDTH = 800;
    private static int SCREEN_HEIGHT = 1000;
    private static int BUTTON_HEIGHT = 100;
    private static int BUTTON_WIDTH = 205;

    private ArrayList<ToolBar> buttons;

    private static final String PERSISTENCE = "./data/Bookshelf.json";
    private JsonWriter jsonSaver = new JsonWriter(PERSISTENCE);

    private Bookshelf collection;

    private JPanel renderer;

    //Constructor for Interface.
    public CollectionManager() {
        collection = new Bookshelf();
        buttons = new ArrayList<>();
        renderer = new Interface(collection);
        setLayout(new BorderLayout());
        setTitle("Collection Manager");
        setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createTools();
        render((Interface) renderer);
        setVisible(true);
    }

    //MODIFIES: this.!
    //EFFECTS: refreshes info on collection and makes a new rendering.
    public void refresh(Bookshelf collection) {
        this.collection = collection;
        render(new Interface(collection));
    }

    //EFFECTS: renders the entire frame again.
    private void render(Interface renderer) {
        renderer.removeAll();
        getContentPane().add(renderer);
        renderer.setLayout(null);
        setVisible(true);
    }

    //MODIFIES: this.
    //EFFECTS: creates necessary buttons.
    private void createTools() {
        JPanel area = new JPanel();
        area.setLayout(new GridLayout(2,4));
        area.setSize(new Dimension(SCREEN_WIDTH, BUTTON_HEIGHT));
        add(area, BorderLayout.SOUTH);

        Load loadButton = new Load(this, area);
        buttons.add(loadButton);
        Save saveButton = new Save(this, area);
        buttons.add(saveButton);
        AddShelf addShelfButton = new AddShelf(this, area);
        buttons.add(addShelfButton);
        AddBook addBookButton = new AddBook(this, area);
        buttons.add(addBookButton);
        MoveShelf moveShelfButton = new MoveShelf(this, area);
        buttons.add(moveShelfButton);
        MoveBook moveBookButton = new MoveBook(this, area);
        buttons.add(moveBookButton);
        FindBook findBookButton = new FindBook(this, area);
        buttons.add(findBookButton);
        Exit exitButton = new Exit(this, area);
        buttons.add(exitButton);
    }

    //EFFECTS: returns book collection
    public Bookshelf getCollection() {
        return collection;
    }

    //Main method
    public static void main(String[] args) {
        new CollectionManager();
    }
}
