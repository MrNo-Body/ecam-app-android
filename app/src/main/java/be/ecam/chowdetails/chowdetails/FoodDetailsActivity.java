package be.ecam.chowdetails.chowdetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FoodDetailsActivity extends AppCompatActivity {
    /* id
    name
    brand
    categories
    URL_picture
    ingredients
     */
    //To use toggle button
    private ToggleButton toggleButton2;
    private Button btnDisplay;


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

        //To Use Toggle button

        addListenerOnButton();
    }
    //Listen the button's state
    public void addListenerOnButton() {

        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer result = new StringBuffer();
                result.append("\ntoggleButton2 : ").append(toggleButton2.getText());

                Toast.makeText(FoodDetailsActivity.this, result.toString(),
                        Toast.LENGTH_SHORT).show();

            }

        });

    }
}
