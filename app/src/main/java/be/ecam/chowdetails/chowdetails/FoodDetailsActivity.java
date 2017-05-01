package be.ecam.chowdetails.chowdetails;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
    private CheckBox favorite;
    Context context = this;
    Class destinationClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        //to display back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        // to save favorite
        favorite = (CheckBox) findViewById(R.id.favorite);
        food_db = new FoodDBHelper(this);
        favorite.setChecked(food_db.isInDB(food.getId()));
        favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query){
                destinationClass = FoodSearchActivity.class;
                Intent intent = new Intent(context, destinationClass);

                // Pass info to the FoodSearchActivity
                intent.putExtra("SEARCH_TERM", query.toString());

                startActivity(intent);
                return false;
            }
            public boolean onQueryTextChange(String newText){

                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Option must be before home to avoid bug
            case R.id.Option:
                Class destinationClass = FoodPreferenceActivity.class;
                Intent intent = new Intent(this, destinationClass);
                startActivity(intent);
                return true;
            case android.R.id.home:
                //onBackPressed();
                //Intent result = new Intent();

                setResult(RESULT_OK, new Intent());

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
