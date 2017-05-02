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
    private static ArrayList<Food> foods;
    private static String json;

    public static void parse(String json) throws JSONException {
        foods = new ArrayList<>();
        FoodList.json = json;
        JSONObject jsonraw = new JSONObject(json);
        JSONArray jsonFoods = jsonraw.getJSONArray("products");

        for (int i=0; i<jsonFoods.length(); i++) {
            JSONObject jsonFood = jsonFoods.getJSONObject(i);
            Food food = new Food();
            food.parse(jsonFood.toString());
            foods.add(food);
        }
    }

    public static Food find(int i) {
        return foods.get(i);
    }

    public static ArrayList<Food> getFoods() {
        return foods;
    }

    public static void setFoods(ArrayList<Food> foods) {
        FoodList.foods = foods;
    }

    public static String toText() {
        String str = "";
        if (foods != null) {
            for (int i = 0; i < foods.size(); i++) {
                str += foods.get(i).getName() + "\n";
            }
        }
        return str;
    }
}
