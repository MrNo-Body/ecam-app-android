package be.ecam.chowdetails.chowdetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 11033 on 18-04-17.
 */

//parse et donne les infos d'un produit (je recois le json d'une recherche)
public class Food {
    private String id;
    private String name;
    private String brand;
    private ArrayList<String> categories;
    private String URL_picture;
    private String ingredients;

    public static void parse(String json) throws JSONException {
        JSONObject jsonFood = new JSONObject(json);
        JSONArray jsonCategories = jsonFood.getJSONObject("product").getJSONArray("categories_prev_hierarchy");

        String id = jsonFood.getJSONObject("product").getString("_id");
        String name = jsonFood.getJSONObject("product").getString("generic_name_fr");
        String brand = jsonFood.getJSONObject("product").getString("brands_debug_tags");
        String URL_picture = jsonFood.getJSONObject("product").getString("image_front_url");
        String ingredients = jsonFood.getJSONObject("product").getString("ingredients_text_debug");

        ArrayList<String> categories = new ArrayList<>();
        for (int i=0; i<jsonCategories.length(); i++) {
            categories.add(jsonCategories.getString(i));
        }
    }

    public Food(String id, String name, String brand, ArrayList<String> categories,
                String URL_picture, String ingredients) {
        this.id = id;
        this.name = name;
        this.brand= brand;
        this.categories = categories;
        this.ingredients = ingredients;
        this.URL_picture = URL_picture;
    }

    //Getters

    public String getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public String getURL_picture() {
        return URL_picture;
    }

    public String getId() {
        return id;
    }
}
