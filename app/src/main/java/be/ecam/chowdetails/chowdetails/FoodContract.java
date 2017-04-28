package be.ecam.chowdetails.chowdetails;

import android.provider.BaseColumns;

/**
 * Created by remy on 28/04/17.
 */

public class FoodContract {
    public static final class FoodEntry implements BaseColumns {
        public static final String TABLE_NAME = "food";
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String BRAND = "brand";
        public static final String URL_PICTURE = "url_picture";
        public static final String INGREDIENTS = "ingredients";
    }
}
