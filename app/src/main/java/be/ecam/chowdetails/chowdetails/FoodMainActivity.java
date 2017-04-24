package be.ecam.chowdetails.chowdetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class FoodMainActivity extends AppCompatActivity implements OnClickListener{
    FoodPreference foodpref = new FoodPreference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main);

        Button button01 = (Button) findViewById(R.id.button01);

        button01.setOnClickListener(this);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Option:
                setContentView(R.layout.preference);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onClick(View view) {
        if (view.getId() == R.id.button01) {
            //Toast.makeText(this, "Bouton 1", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.preference);
        }
    }
}
