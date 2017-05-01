package be.ecam.chowdetails.chowdetails;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;


public class FoodMainActivity extends AppCompatActivity implements OnClickListener {
    public boolean blue, red, white, green, photomode, nightmode = false;

    Context context = this;
    Class destinationClass;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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


        setContentView(R.layout.activity_food_main);

        Button butfavoris= (Button) findViewById(R.id.butfavoris);
        butfavoris.setOnClickListener(this);
        ImageButton butfind= (ImageButton) findViewById(R.id.butfind);
        butfind.setOnClickListener(this);
        Button scanBtn = (Button)findViewById(R.id.scan_button);
        scanBtn.setOnClickListener(this);

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
            FoodDBHelper food_db = new FoodDBHelper(this);
            FoodList.setFoods(food_db.getFoods());
            destinationClass = FoodFavoriteActivity.class;
            Intent intent = new Intent(context, destinationClass);
            startActivity(intent);
        }
        if(view.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    //////////////////////////////////////
    //Allow to read a barcode but the search can't find the food with that for the moment
    //Because the API don't allow that
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        com.google.zxing.integration.android.IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();

            EditText search = (EditText) findViewById(R.id.search);
            search.setText(scanContent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
