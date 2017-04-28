package be.ecam.chowdetails.chowdetails;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yas on 25/04/2017.
 */

public class FoodFavoriteActivity extends AppCompatActivity implements ItemAdapter.ItemAdapterOnClickHandler {

    private RecyclerView resultView;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_favorite);

        resultView = (RecyclerView) findViewById(R.id.resultView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        resultView.setLayoutManager(layoutManager);
        resultView.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(this);
        resultView.setAdapter(itemAdapter);

        FoodDBHelper food_db = new FoodDBHelper(this);
        FoodList.setFoods(food_db.getFoods());

        itemAdapter.setData(FoodList.getFoods());
    }

    @Override
    public void onClick(int index) {
        Context context = this;
        Class destinationClass = FoodDetailsActivity.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra(Intent.EXTRA_INDEX, index);
        startActivity(intent);
    }
}
