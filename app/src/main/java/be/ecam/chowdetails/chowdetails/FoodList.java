package be.ecam.chowdetails.chowdetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 11033 on 18-04-17.
 */

//Si On recoit plusieurs produit
public class FoodList {
    private ArrayList<Food> foods;

    public void parse(String json) throws JSONException {
        JSONObject jsonraw = new JSONObject(json);
        JSONArray jsonFoods = jsonraw.getJSONArray("products");

        for (int i=0; i<jsonFoods.length(); i++) {
            JSONObject jsonFood = jsonFoods.getJSONObject(i);
            Food food = new Food();
            food.parse(jsonFood.toString());
        }
    }

    public FoodList() {
        this.foods = new ArrayList<>();
    }
}
