package be.ecam.chowdetails.chowdetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FoodMainActivity extends AppCompatActivity {

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
}
