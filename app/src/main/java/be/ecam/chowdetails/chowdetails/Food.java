package be.ecam.chowdetails.chowdetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 11033 on 18-04-17.
 */

public class Food {
    private String id;
    private String name;
    private String brand;
    private String category;
    private String URL_picture;
    private String ingredients;

    public static void parse(String json) throws JSONException {
        JSONObject jsonFood = new JSONObject(json);
        String city = jsonFood.getJSONObject("city").getString("name");

}
