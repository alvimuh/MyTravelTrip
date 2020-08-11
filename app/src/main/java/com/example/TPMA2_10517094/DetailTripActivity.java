package com.example.TPMA2_10517094;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailTripActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    TextView text1, text2, text3, text4, text5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trip);
        dbHelper = new DataHelper(this);
        text1 = (TextView) findViewById(R.id.title);
        text2 = (TextView) findViewById(R.id.location);
        text3 = (TextView) findViewById(R.id.date);
        text4 = (TextView) findViewById(R.id.story);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM "+DataHelper.MyColumns.namaTabel+" WHERE "+DataHelper.MyColumns.id_trip+" = '" +
                getIntent().getStringExtra(DataHelper.MyColumns.id_trip) + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(1).toString());
            text2.setText(cursor.getString(2).toString());
            text3.setText(cursor.getString(3).toString());
            text4.setText(cursor.getString(4).toString());
        }
    }
    public void intentUpdate(View view){
        String selection = getIntent().getStringExtra(DataHelper.MyColumns.id_trip);
        Intent u = new Intent(getApplicationContext(),UpdateTripActivity.class);
        u.putExtra(DataHelper.MyColumns.id_trip, selection);
        startActivity(u);
    }
}
