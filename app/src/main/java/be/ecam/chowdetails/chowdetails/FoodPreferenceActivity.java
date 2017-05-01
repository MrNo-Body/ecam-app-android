package be.ecam.chowdetails.chowdetails;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
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
import android.app.Activity;
import android.view.View.OnClickListener;


/*
* Created by Neil
* */
public class FoodPreferenceActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.preference);

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
//Apply Listener on the spinner
        ColorSpin.setOnItemSelectedListener(this);
        TpoliceSpin.setOnItemSelectedListener(this);
        AllergieSpin.setOnItemSelectedListener(this);
    }


//----------------Menu-----------------
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
        switch (parent.getItemAtPosition(pos).toString()){

            case "Petit":

                themeUtils.changeToTheme(this, themeUtils.Petit);
                break;
            case "Normal":
                /
                //themeUtils.changeToTheme(this, themeUtils.Normal);
                break;
            case "Grand":

                themeUtils.changeToTheme(this, themeUtils.Grand);
                break;
            case "Blanc":

                //themeUtils.changeToTheme(this, themeUtils.WHITE);
                break;
            case "Bleu":
                themeUtils.changeToTheme(this, themeUtils.BLUE);
                break;
            case "Rouge":
                themeUtils.changeToTheme(this, themeUtils.RED);
                break;
            case "Vert":
                themeUtils.changeToTheme(this, themeUtils.GREEN);
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

                themeUtils.changeToTheme(this, themeUtils.BLACK);
            }
            else
            {
                themeUtils.changeToTheme(this, themeUtils.WHITE);


            }
            break;

        case R.id.checkBoxPhoto:
            if (checked)
            {


            }
            else
            {

            }

            break;
    }
}




    public void onClick(View view) {

        if (view.getId() == R.id.button02) {
            //todo querry to BD
            Toast.makeText(this, "black", Toast.LENGTH_SHORT).show();
        }
    }
}
