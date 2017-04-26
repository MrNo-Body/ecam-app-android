package be.ecam.chowdetails.chowdetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FoodDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        TextView name = (TextView) findViewById(R.id.name);
        TextView brand = (TextView) findViewById(R.id.brand);
        TextView categories = (TextView) findViewById(R.id.categories);
        TextView ingredients = (TextView) findViewById(R.id.ingredients);

        // Get the food object
        Intent intent = getIntent();
        Food food = FoodList.find(intent.getIntExtra(Intent.EXTRA_INDEX, 0));

        // Fill in the textviews
        ArrayList<String> categoriesList = food.getCategories();
        String categoriesString = "";

        name.setText("Name: " + food.getName());
        brand.setText("Brand: " + food.getBrand());
        if (categoriesList != null) {
            for (int i = 0; i < categoriesList.size(); i++) {
                categoriesString += categoriesList.get(i);
                if (i < categoriesList.size()) {
                    categoriesString += ", ";
                }
            }
        }
        categories.setText("Categories: " + categoriesString);
        ingredients.setText("Ingredients: " + food.getIngredients());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.Favorite) {
            // Mark this product as a favorite one.
        }
        return super.onOptionsItemSelected(item);
    }

}
