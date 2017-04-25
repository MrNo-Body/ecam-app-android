package be.ecam.chowdetails.chowdetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.app.Activity;
import android.view.View.OnClickListener;

/**
 * Created by 10047 on 18-04-17.
 */

public class FoodPreference extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference);

       // Intent intent = getIntent();

        Button button02 = (Button) findViewById(R.id.button02);
        button02.setOnClickListener(this);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onClick(View view) {

         if (view.getId() == R.id.button02) {
          Toast.makeText(this, "Bouton 02", Toast.LENGTH_SHORT).show();
          }
    }

}
