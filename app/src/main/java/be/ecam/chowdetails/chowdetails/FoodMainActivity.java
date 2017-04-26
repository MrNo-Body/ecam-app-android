package be.ecam.chowdetails.chowdetails;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;
import android.app.Activity;
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
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.name).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Option:
                Class destinationClass = FoodPreference.class;
                Intent intent = new Intent(this, destinationClass);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View view)
    {
        if (view.getId() == R.id.butfind)
        {
            Toast.makeText(this, "page de ismael", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_food_search);
        }
        if (view.getId() == R.id.butfavoris)
        {
            Toast.makeText(this, "page de favoris", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_food_favorite);
        }
    }
}
