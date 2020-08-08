package com.example.TPMA2_10517094;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
public class DataHelper extends SQLiteOpenHelper {

    static abstract  class MyColumns implements BaseColumns {
        static final String namaTabel = "trip";
        static final String id_trip = "id_trip";
        static final String title = "title";
        static final String location = "location";
        static final String date_visit = "date_visit";
        static final String short_story = "short_story";
    }

    private static final String DATABASE_NAME = "travel_trip.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "CREATE TABLE IF NOT EXISTS "+ MyColumns.namaTabel +
                "("+MyColumns.id_trip+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MyColumns.title+" TEXT, " +
                MyColumns.location +" TEXT, " +
                MyColumns.date_visit +" TEXT NULL, " +
                MyColumns.short_story +" TEXT NULL);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO "+MyColumns.namaTabel+" (title, location, date_visit, short_story) " +
                "VALUES ('Trip Lebaran', 'Pangandaran', '19-07-2020', 'Liburan santai dengan tetap social distancing');";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}