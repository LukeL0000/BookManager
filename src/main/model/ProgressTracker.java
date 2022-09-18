package model;

import org.json.JSONObject;
import model.persistence.Writer;

//The ProgressTracker class is separate, and deals with tracking the progress of its respective book.
public class ProgressTracker implements Writer {

    private boolean finished;
    private boolean reading;
    private boolean unread;
    private int totalPages;
    private int pagesRead;

    public ProgressTracker(int totalPages) {
        this.totalPages = totalPages;
        unread = true;
    }

    //REQUIRES: book not already finished.
    //MODIFIES: this.
    //EFFECTS: changes reading status to true.
    public void startReading() {
        reading = true;
        unread = false;
    }

    //REQUIRES: pagesTotal >= pages, book not already finished.
    //MODIFIES: this.
    //EFFECTS: changes number of pages read.
    public void updateProgress(int pages) {
        this.pagesRead = pages;
        setFinished();
    }

    //REQUIRES: pages <= totalPages
    private void setFinished() {
        if (pagesRead >= totalPages) {
            finished = true;
            reading = false;
        }
    }

    //REQUIRES: pages > 0
    //MODIFIES: this.
    //EFFECTS: updates number of pages in book to specified value.
    public void updatePages(int pages) {
        this.totalPages = pages;
    }

    //EFFECTS: returns object information as JSON object.
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("finished", finished);
        json.put("reading", reading);
        json.put("unread", unread);
        json.put("totalPages", totalPages);
        json.put("pagesRead", pagesRead);
        return json;
    }

    //EFFECTS: returns current status of book
    public String getStatus() {
        if (unread) {
            return "Unread";
        } else if (reading) {
            return "Reading";
        } else {
            return "Finished";
        }
    }

    //EFFECTS: returns total number of pages
    public int getTotalPages() {
        return totalPages;
    }

//    //EFFECTS: returns current number of pages read
//    public int getPagesRead() {
//        return pagesRead;
//    }
}


