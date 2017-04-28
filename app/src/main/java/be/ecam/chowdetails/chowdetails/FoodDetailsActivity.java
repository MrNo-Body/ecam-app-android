package be.ecam.chowdetails.chowdetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FoodDetailsActivity extends AppCompatActivity {

    private Food food;
    private FoodDBHelper food_db;
    //To use toggle button
    private ToggleButton favorite_button;

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
        food = FoodList.find(intent.getIntExtra(Intent.EXTRA_INDEX, 0));

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

        // To Use Toggle button for favorite
        favorite_button = (ToggleButton) findViewById(R.id.favorite_button);
        food_db = new FoodDBHelper(this);

        favorite_button.setChecked(food_db.isInDB(food.getId()));

        favorite_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Record the favorite
                    food_db.insert(food);
                    Log.w("chowdetails:", "Insert food in db.");
                } else {
                    // Delete the favorite
                    food_db.delete(food.getId());
                    Log.w("chowdetails:", "Delete food in db.");
                }
            }
        });
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
