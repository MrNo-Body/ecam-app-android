package be.ecam.chowdetails.chowdetails;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodSearchActivity extends AppCompatActivity implements ItemAdapter.ItemAdapterOnClickHandler {
    public boolean blue, red, white, green, photomode, nightmode = false;
    private static final String API_BASE_URL = "https://world.openfoodfacts.org/cgi/search.pl";

    private RecyclerView resultView;
    private ItemAdapter itemAdapter;
    Context context = this;
    Class destinationClass;
    @Override
    //Using search widget
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the options menu from XML
        //get the SearchView and set the searchable configuration
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //SearchView searchView = (SearchView) menu.findItem(R.id.activity_food_search).getActionView();
        //assumes current activity is the searchable activity
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default;

        // Search in the toolbar
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

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //------------------COLOR THE WORLD!!!-------------------
        themeUtils.onActivityCreateSetTheme(this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        nightmode = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBoxNight", false);
        photomode = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBoxPhoto", false);
        red = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("redColor", false);
        blue = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("blueColor", false);
        green = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("greenColor", false);
        if(nightmode)
        {
            themeUtils.onActivityCreateSetTheme(this, themeUtils.BLACK);
        }
        else if (blue)
        {
            themeUtils.onActivityCreateSetTheme(this, themeUtils.BLUE);
        }
        else if (red)
        {
            themeUtils.onActivityCreateSetTheme(this, themeUtils.RED);
        }
        else if (green)
        {
            themeUtils.onActivityCreateSetTheme(this, themeUtils.GREEN);
        }
        else
        {}
        //----------------------------------------------------------
        setContentView(R.layout.activity_food_search);

        //to display back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resultView = (RecyclerView) findViewById(R.id.resultView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        resultView.setLayoutManager(layoutManager);
        resultView.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(this);
        resultView.setAdapter(itemAdapter);

        Bundle extras = getIntent().getExtras();
        String search_term;
        if(extras != null) {
            search_term = extras.getString("SEARCH_TERM");
        } else {
            search_term = "";
        }

        Toast.makeText(FoodSearchActivity.this, "Searching...", Toast.LENGTH_SHORT).show();
        new QueryTask().execute(API_BASE_URL + "?search_terms=" + search_term  +
                "&search_simple=1&action=process&json=1");


        //Get the intent, verify the action and get the query
        /*
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            //doMySearch(query);
        }
        */
    }

    @Override
    public void onClick(int index) {
        Context context = this;
        Class destinationClass = FoodDetailsActivity.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra(Intent.EXTRA_INDEX, index);
        startActivity(intent);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /*
     * Query Class
     */
    public class QueryTask extends AsyncTask<String, Void, ArrayList<Food>> {
        @Override
        protected ArrayList<Food> doInBackground(String... params) {
            String searchUrl = params[0];
            String json = null;
            ArrayList<Food> queryResults = null;
            try {
                json = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                //Log.w("chowdetails", json);
                FoodList.parse(json);
                //Log.w("chowdetails", FoodList.toText());

                queryResults = FoodList.getFoods();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return queryResults;
        }

        @Override
        protected void onPostExecute(ArrayList<Food> queryResults) {
            if (queryResults != null) {
                itemAdapter.setData(queryResults);
            } else {
                Log.w("chowdetails", "Empty request !");
            }
        }
    }
}