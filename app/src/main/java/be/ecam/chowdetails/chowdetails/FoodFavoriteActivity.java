package be.ecam.chowdetails.chowdetails;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

/**
 * Created by yas on 25/04/2017.
 */

public class FoodFavoriteActivity extends ListActivity {

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


}
