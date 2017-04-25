package be.ecam.chowdetails.chowdetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;
import android.widget.Button;
import android.app.Activity;
import android.view.View.OnClickListener;



public class FoodMainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main);

        Button butfavoris= (Button) findViewById(R.id.butfavoris);
        butfavoris.setOnClickListener(this);
        Button butfind= (Button) findViewById(R.id.butfind);
        butfind.setOnClickListener(this);

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
    public void onClick(View view)
    {
        if (view.getId() == R.id.butfind)
        {
            Toast.makeText(this, "page de ismael", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_food_search);
        }
        if (view.getId() == R.id.butfavoris)
        {
            Toast.makeText(this, "page de favoris", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_food_favorite);
        }
    }
}
