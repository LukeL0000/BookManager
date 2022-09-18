package ui.toolbar;

import model.Bookshelf;
import model.Event;
import model.EventLog;
import ui.CollectionManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

//The Exit class contains the button and functionality to output the event log onto console and exit the program.
public class Exit extends ToolBar {

    //Constructor.
    public Exit(CollectionManager source, JComponent buttonPanel) {
        super(source, buttonPanel);
    }

    //MODIFIES: this.
    //EFFECTS: creates an exit button.
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Exit");
        addToParent(parent);
    }

    //EFFECTS: outputs event log, exits program.
    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Iterator<Event> eventLog = EventLog.getInstance().iterator();
                while (eventLog.hasNext()) {
                    System.out.println(eventLog.next().toString() + "\n");
                }
                System.exit(0);
            }
        });
    }
}
