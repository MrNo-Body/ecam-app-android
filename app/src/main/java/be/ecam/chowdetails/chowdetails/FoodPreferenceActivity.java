package be.ecam.chowdetails.chowdetails;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public boolean nightmode;
    public boolean photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference);

        CheckBox checkBoxPhoto=(CheckBox)findViewById(R.id.checkBoxPhoto);
        CheckBox checkBoxNight=(CheckBox)findViewById(R.id.checkBoxNight);

        nightmode = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBoxNight", false);
        photo = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBoxPhoto", false);

        checkBoxNight.setChecked(nightmode);
        checkBoxPhoto.setChecked(photo);

        //Todo set le colorbackground de chaque view
        /*
        if(CheckBD) set  la couleur de BD
        this.getWindow().getDecorView().setBackgroundColor(Valeur BD);
        else
        rien
*/
        // Intent intent = getIntent();
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//----------------Spinner-----------------
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (parent.getItemAtPosition(pos).toString()){

            case "Petit":
                //TODO set le text en petit
                break;
            case "Normal":
                //TODO set le text en normal
                break;
            case "Grand":
                //TODO set le text en grand
                break;
            case "Blanc":
                //TODO setBackgroundColor dans chaque view dans son OnCreate
                this.getWindow().getDecorView().setBackgroundColor(0xFFFFFFFF);
                break;
            case "Bleu":
                this.getWindow().getDecorView().setBackgroundColor(0xFF9696FF);
                break;
            case "Rouge":
                this.getWindow().getDecorView().setBackgroundColor(0xFFFF4E4A);
                break;
            case "Vert":
                this.getWindow().getDecorView().setBackgroundColor(0xFF6EE89B);
                break;
            case "Arachide":
                //todo set allergie Archadie
                break;
            case "Lactose":
                //todo set allergie Lactose
                break;
            case "Gluten":
                //todo set allergie Gluten
                break;
            default:
                //Todo charger les données de la base de données
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
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                    .putBoolean("checkBoxNight", nightmode).commit();
            if (checked)
            {
                //Todo Enregistrer dans BD
                Toast.makeText(this, "Checked", Toast.LENGTH_SHORT).show();
            }
            else
            {
                //todo enregistrer dans BD
                Toast.makeText(this, "Not Checked", Toast.LENGTH_SHORT).show();
            }
            break;

        case R.id.checkBoxPhoto:
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                    .putBoolean("checkBoxPhoto", photo).commit();
            if (checked)
            {
                //todo enregistrer dans BD
                Toast.makeText(this, "Checked", Toast.LENGTH_SHORT).show();
            }
            else
            {
                //todo enregistrer dans BD
                Toast.makeText(this, "Not Checked", Toast.LENGTH_SHORT).show();
            }

            break;
    }
}




    public void onClick(View view) {

         if (view.getId() == R.id.button02) {
          Toast.makeText(this, "Bouton 02", Toast.LENGTH_SHORT).show();
          }
    }



}
