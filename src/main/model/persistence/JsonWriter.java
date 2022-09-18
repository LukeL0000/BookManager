package model.persistence;

import model.*;
import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//The JsonWriter class takes a bookshelf object and writes its information into a json file.
public class JsonWriter {

    private PrintWriter printWriter;
    private int spacing = 4;
    private String destination;

    //EFFECTS: constructs a JsonWriter that writes to destination.
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this.
    //EFFECTS: creates new instance of PrintWriter
    public void openPrintWriter() throws FileNotFoundException {
        printWriter = new PrintWriter(new File(destination));
    }

    //MODIFIES: this.
    //EFFECTS: writes object's json representation to file.
    public void writeToFile(Bookshelf bookshelf) {
        JSONObject jsonData = bookshelf.convertToJson();
        saveData(jsonData.toString());
        EventLog.getInstance().logEvent(new Event("Saved book collection."));
    }

    // MODIFIES: this
    // EFFECTS: sends the string to destination file
    private void saveData(String json) {
        printWriter.print(json);
    }

    //MODIFIES: this.
    //EFFECTS: closes the writer.
    public void closeWriter() {
        printWriter.close();
    }
}
