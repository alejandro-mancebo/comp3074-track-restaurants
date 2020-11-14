package ca.georgebrown.comp3074.restaurants;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RestaurantDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "restaurants.db";
    public static final int DATABASE_VERSION = 1;

    public RestaurantDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RestaurantStatement.Queries.SQL_CREATE);
        Log.d("DATABASE", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(RestaurantStatement.Queries.SQL_DROP);
        Log.d("DATABASE", "Database dropped");
        onCreate(db);
    }

    public long insert(SQLiteDatabase db, RestaurantManager.Restaurant restaurant){

        ContentValues values = new ContentValues();
        // values.put(RestaurantStatement.Queries.COLUMN_NAME_SHOPPING_ITEM, restaurant.content);
        values.put(RestaurantStatement.Queries.COLUMN_NAME, restaurant.getName());
        values.put(RestaurantStatement.Queries.COLUMN_TYPE, restaurant.getType());
        values.put(RestaurantStatement.Queries.COLUMN_ADDRESS, restaurant.getAddress());
        values.put(RestaurantStatement.Queries.COLUMN_PHONE, restaurant.getPhone());
        values.put(RestaurantStatement.Queries.COLUMN_WEBSITE, restaurant.getWebsite());
        values.put(RestaurantStatement.Queries.COLUMN_RATE, restaurant.getRate());
        values.put(RestaurantStatement.Queries.COLUMN_PRICE, restaurant.getPrice());
        values.put(RestaurantStatement.Queries.COLUMN_OTHER_TAGS, restaurant.getOtherTags());

        return db.insert(RestaurantStatement.Queries.TABLE_NAME, null, values);
    }


    public void delete(SQLiteDatabase db, String getID) {
        db.execSQL("DELETE FROM " + RestaurantStatement.Queries.TABLE_NAME + " WHERE _ID = '" + getID + "'");
    }

    public Cursor getAllRestaurantItems(SQLiteDatabase db){

        String[] projection = {
                RestaurantStatement.Queries._ID,
                RestaurantStatement.Queries.COLUMN_NAME,
                RestaurantStatement.Queries.COLUMN_TYPE,
                RestaurantStatement.Queries.COLUMN_PRICE,
                RestaurantStatement.Queries.COLUMN_ADDRESS,
                RestaurantStatement.Queries.COLUMN_PHONE,
                RestaurantStatement.Queries.COLUMN_WEBSITE,
                RestaurantStatement.Queries.COLUMN_RATE,
                RestaurantStatement.Queries.COLUMN_OTHER_TAGS,
        };

        return db.query(RestaurantStatement.Queries.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
    }

    public  Cursor getAllItems(SQLiteDatabase db){
        return db.rawQuery("SELECT * FROM " + RestaurantStatement.Queries.TABLE_NAME, null );
    }
}
