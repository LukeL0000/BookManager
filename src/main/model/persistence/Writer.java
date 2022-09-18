package model.persistence;

import org.json.JSONObject;

public interface Writer {

    //EFFECTS: returns object information as JSON object.
    JSONObject convertToJson();

}
