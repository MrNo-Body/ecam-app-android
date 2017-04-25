package be.ecam.chowdetails.chowdetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class FoodMainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main);
      
        /* test des differents widget
        TextView text = new TextView(this);
        text.setText("poopy");
        //text.setTextSize(20)
        setContentView(text);

        EditText editText = new EditText(this);
        editText.setHint(" taper le nom de l'aliment");
        editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        editText.setLines(5);
        setContentView(editText);

        Button button = new Button(this);
        button.setText("button");
        setContentView(button);*/
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
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

    public void onClick(View view) {
    }
}

