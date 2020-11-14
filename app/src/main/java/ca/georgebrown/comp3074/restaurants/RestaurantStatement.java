package ca.georgebrown.comp3074.restaurants;


import android.provider.BaseColumns;

public final class RestaurantStatement {

    private RestaurantStatement() {}

    public static class Queries implements BaseColumns {

        public static final String TABLE_NAME = "shopping";
        public static final String COLUMN_NAME_ID = _ID;
        public static final String COLUMN_NAME_SHOPPING_ITEM = "shopping_item";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_WEBSITE = "website";
        public static final String COLUMN_RATE = "rate";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_OTHER_TAGS = "other_tags";

        public static final String SQL_CREATE = "CREATE TABLE "+
                TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME_SHOPPING_ITEM + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_WEBSITE + " TEXT, " +
                COLUMN_RATE + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_OTHER_TAGS + " TEXT )";

    /*
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME +
                " ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_SHOPPING_ITEM + " TEXT )";
    */

        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
