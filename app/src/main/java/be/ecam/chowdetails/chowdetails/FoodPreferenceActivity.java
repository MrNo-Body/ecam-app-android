package be.ecam.chowdetails.chowdetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;


/*
* Created by Neil
* */
public class FoodPreferenceActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemSelectedListener {
    public boolean blue, red, white, green, photomode, nightmode = false;
    public int spinnerColorPos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeUtils.onActivityCreateSetTheme(this);

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
            spinnerColorPos = 0;
            ThemeUtils.onActivityCreateSetTheme(this, ThemeUtils.BLACK);
        }
        else if (blue)
        {
            spinnerColorPos = 1;
            ThemeUtils.onActivityCreateSetTheme(this, ThemeUtils.BLUE);
        }
        else if (red)
        {
            spinnerColorPos = 2;
            ThemeUtils.onActivityCreateSetTheme(this, ThemeUtils.RED);
        }
        else if (green)
        {
            spinnerColorPos = 3;
            ThemeUtils.onActivityCreateSetTheme(this, ThemeUtils.GREEN);
        }

        else
        {

        }

        setContentView(R.layout.activity_food_preference);



        //---------pref settings
        CheckBox checkBoxPhoto = (CheckBox) findViewById(R.id.checkBoxPhoto);
        CheckBox checkBoxNight = (CheckBox) findViewById(R.id.checkBoxNight);

        checkBoxPhoto.setChecked(photomode);
        checkBoxNight.setChecked(nightmode);

        //--------------------------

        //to display back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Listener on Button
        Button button02 = (Button) findViewById(R.id.button02);
        button02.setOnClickListener(this);

        //set spinner
        Spinner ColorSpin = (Spinner) findViewById(R.id.colorSpin);
        Spinner TpoliceSpin = (Spinner) findViewById(R.id.tpoliceSpin);
        Spinner AllergieSpin = (Spinner) findViewById(R.id.allergieSpin);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> ColorAdapter = ArrayAdapter.createFromResource(this,
                R.array.ColorSpinArray, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> PoliceAdapter = ArrayAdapter.createFromResource(this,
                R.array.TpoliceSpinArray, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> AllergieAdapter = ArrayAdapter.createFromResource(this,
                R.array.allergieSpinArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears

        ColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PoliceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AllergieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        ColorSpin.setAdapter(ColorAdapter);
        TpoliceSpin.setAdapter(PoliceAdapter);
        AllergieSpin.setAdapter(AllergieAdapter);

        ColorSpin.setSelection(spinnerColorPos);
//Apply Listener on the spinner
        ColorSpin.setOnItemSelectedListener(this);
        TpoliceSpin.setOnItemSelectedListener(this);
        AllergieSpin.setOnItemSelectedListener(this);
    }


//----------------Menu-----------------
public void onBackPressed() {
    startActivity(new Intent(this, FoodMainActivity.class));
}
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Option:
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//----------------Spinner-----------------
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (parent.getItemAtPosition(pos).toString()) {

            case "Petit":

                ThemeUtils.changeToTheme(this, ThemeUtils.Petit);
                break;
            case "Normal":
                break;
            case "Grand":

                ThemeUtils.changeToTheme(this, ThemeUtils.Grand);
                break;
            case "Blanc":

                if ((red || blue || green || nightmode) && (spinnerColorPos != 0)) {
                    blue = false;
                    white = false;
                    green = false;
                    red = false;
                    nightmode = false;
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("checkBoxNight", nightmode).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("redColor", red).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("whiteColor", white).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("blueColor", blue).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("greenColor", green).commit();
                    ThemeUtils.changeToTheme(this, ThemeUtils.WHITE);
                }

                break;
            case "Bleu":
                if (spinnerColorPos != 1) {
                    blue = true;
                    white = false;
                    green = false;
                    red = false;
                    nightmode = false;
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("checkBoxNight", nightmode).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("redColor", red).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("whiteColor", white).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("blueColor", blue).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("greenColor", green).commit();

                    ThemeUtils.changeToTheme(this, ThemeUtils.BLUE);
                }
                break;
            case "Rouge":
                if (spinnerColorPos != 2) {
                    red = true;
                    white = false;
                    blue = false;
                    green = false;
                    nightmode = false;

                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("checkBoxNight", nightmode).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("redColor", red).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("whiteColor", white).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("blueColor", blue).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("greenColor", green).commit();
                    ThemeUtils.changeToTheme(this, ThemeUtils.RED);
                }

                break;
            case "Vert":
                if(spinnerColorPos!=3) {
                    green = true;
                    white = false;
                    blue = false;
                    red = false;
                    nightmode = false;
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("checkBoxNight", nightmode).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("redColor", red).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("whiteColor", white).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("blueColor", blue).commit();
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("greenColor", green).commit();
                    ThemeUtils.changeToTheme(this, ThemeUtils.GREEN);
                }

                break;
            case "Arachide":

                break;
            case "Lactose":

                break;
            case "Gluten":

                break;
            default:

        }


    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

//----------------CheckBox-----------------
public void onCheckboxClicked(View view) {
    boolean checked = ((CheckBox) view).isChecked();

    switch(view.getId()) {
        case R.id.checkBoxNight:
            if (checked)
            {
                nightmode = true;
                red= false;
                green = false;
                white= false;
                blue = false;
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("checkBoxNight", nightmode).commit();
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("redColor", red).commit();
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("greenColor", green).commit();
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("whiteColor", white).commit();
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("blueColor", blue).commit();


                ThemeUtils.changeToTheme(this, ThemeUtils.BLACK);
            }
            else
            {

                ThemeUtils.changeToTheme(this, ThemeUtils.WHITE);

                nightmode = false;
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("checkBoxNight", nightmode).commit();
                red= false;
                green = false;
                white= false;
                blue = false;
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("redColor", red).commit();
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("greenColor", green).commit();
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("whiteColor", white).commit();
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("blueColor", blue).commit();
                if(!(red||blue||green||white)) {
                    ThemeUtils.changeToTheme(this, ThemeUtils.WHITE);
                }

            }
            break;

        case R.id.checkBoxPhoto:
            if (checked)
            {
                photomode = true;
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("checkBoxPhoto", photomode).commit();
            }
            else
            {

                photomode= false;
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("checkBoxPhoto", photomode).commit();
            }

            break;
    }
}
    public void onClick(View view) {

        if (view.getId() == R.id.button02) {
            //todo querry to BD
            Toast.makeText(this, "Downloading...", Toast.LENGTH_SHORT).show();
            Class destinationClass = FoodSearchActivity.class;
            Intent intent = new Intent(this, destinationClass);
            intent.putExtra("SEARCH_TERM","");
        }
    }
}
