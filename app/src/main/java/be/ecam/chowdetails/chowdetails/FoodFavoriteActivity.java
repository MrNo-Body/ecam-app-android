package be.ecam.chowdetails.chowdetails;

import android.app.ListActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yas on 25/04/2017.
 */

public class FoodFavoriteActivity extends AppCompatActivity implements ItemAdapter.ItemAdapterOnClickHandler {

    private RecyclerView resultView;
    private ItemAdapter itemAdapter;
    Context context = this;
    Class destinationClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_favorite);

        //to display back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resultView = (RecyclerView) findViewById(R.id.resultView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        resultView.setLayoutManager(layoutManager);
        resultView.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(this);
        resultView.setAdapter(itemAdapter);


        //To fix the bug it's better to put these lign in the foodmain activity class
        //When whe push the button to go to the favorite page
        /*FoodDBHelper food_db = new FoodDBHelper(this);
        FoodList.setFoods(food_db.getFoods());
        */
        itemAdapter.setData(FoodList.getFoods());
    }
        
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        inflater.inflate(R.menu.search_menu,menu);
        //inflater.inflate(R.menu.refresh_menu,menu);
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
            case R.id.Option:
                Class destinationClass = FoodPreferenceActivity.class;
                Intent intent = new Intent(this, destinationClass);
                startActivity(intent);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.Refresh:
                //A optimiser
                FoodDBHelper food_db = new FoodDBHelper(this);
                FoodList.setFoods(food_db.getFoods());
                destinationClass = FoodFavoriteActivity.class;
                Intent intent1 = new Intent(context, destinationClass);
                startActivity(intent1);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(int index) {
        Context context = this;
        Class destinationClass = FoodDetailsActivity.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra(Intent.EXTRA_INDEX, index);
        startActivity(intent);

    }
}
