package be.ecam.chowdetails.chowdetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;



/**
 * Created by remy on 28/04/17.
 */

public class FoodDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "openfood.db";
    public static final int DB_VERSION = 1;

    public FoodDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FOOD_TABLE =
                "CREATE TABLE " + FoodContract.FoodEntry.TABLE_NAME + "(" +
                        FoodContract.FoodEntry.ID + " INTEGER PRIMARY KEY, " +
                        FoodContract.FoodEntry.NAME + " TEXT, " +
                        FoodContract.FoodEntry.BRAND + " TEXT, " +
                        FoodContract.FoodEntry.URL_PICTURE + " TEXT, " +
                        FoodContract.FoodEntry.INGREDIENTS + " TEXT" +
                        ");";
        db.execSQL(SQL_CREATE_FOOD_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + FoodContract.FoodEntry.TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Food> getFoods() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                FoodContract.FoodEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                FoodContract.FoodEntry.NAME
        );

        ArrayList<Food> foods = new ArrayList<>();

        while(cursor.moveToNext()) {
            String name = cursor.getString(
                    cursor.getColumnIndex(FoodContract.FoodEntry.NAME)
            );
            String id = cursor.getString(
                    cursor.getColumnIndex(FoodContract.FoodEntry.ID)
            );
            String brand = cursor.getString(
                    cursor.getColumnIndex(FoodContract.FoodEntry.BRAND)
            );
            String url_picture = cursor.getString(
                    cursor.getColumnIndex(FoodContract.FoodEntry.URL_PICTURE)
            );
            String ingredients = cursor.getString(
                    cursor.getColumnIndex(FoodContract.FoodEntry.INGREDIENTS)
            );

            foods.add(new Food(id, name, brand, null, url_picture, ingredients));
        }
        cursor.close();

        return foods;
    }

    public boolean insert(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FoodContract.FoodEntry.ID, food.getId());
        cv.put(FoodContract.FoodEntry.NAME, food.getName());
        cv.put(FoodContract.FoodEntry.BRAND, food.getBrand());
        cv.put(FoodContract.FoodEntry.INGREDIENTS, food.getIngredients());
        cv.put(FoodContract.FoodEntry.URL_PICTURE, food.getURL_picture());

        return db.insert(FoodContract.FoodEntry.TABLE_NAME, null, cv) > 0;
    }

    public boolean delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(
                FoodContract.FoodEntry.TABLE_NAME, FoodContract.FoodEntry.ID + " = \"" + id + "\"", null
        ) > 0;
    }

    public boolean isInDB(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                FoodContract.FoodEntry.TABLE_NAME,
                null,
                FoodContract.FoodEntry.ID + " = ?",
                new String[] { id },
                null,
                null,
                FoodContract.FoodEntry.NAME
        );

        int result = cursor.getCount();
        cursor.close();
        return result > 0;

    }
}
