package be.ecam.chowdetails.chowdetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.MenuItem;
import android.view.View.OnClickListener;

public class FoodMainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main);

        Button butfavoris= (Button) findViewById(R.id.butfavoris);
        butfavoris.setOnClickListener(this);
        Button butfind= (Button) findViewById(R.id.butfind);
        butfind.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Option:
                Class destinationClass = FoodPreferenceActivity.class;
                Intent intent = new Intent(this, destinationClass);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View view)
    {
        Context context = this;
        Class destinationClass;
        if (view.getId() == R.id.butfind)
        {
            EditText search = (EditText) findViewById(R.id.search);
            destinationClass = FoodSearchActivity.class;
            Intent intent = new Intent(context, destinationClass);

            // Pass info to the FoodSearchActivity
            intent.putExtra("SEARCH_TERM", search.getText().toString());

            startActivity(intent);
        }
        if (view.getId() == R.id.butfavoris)
        {
            destinationClass = FoodFavoriteActivity.class;
            Intent intent = new Intent(context, destinationClass);

            //Test pour afficher un aliment dans favoris a partir de la recherche
            EditText search = (EditText) findViewById(R.id.search);
            intent.putExtra("SEARCH_TERM", search.getText().toString());
            startActivity(intent);
        }
    }


}
