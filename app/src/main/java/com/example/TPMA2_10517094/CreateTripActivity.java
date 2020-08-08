package com.example.TPMA2_10517094;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class CreateTripActivity extends AppCompatActivity {
    DataHelper dbHelper;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String mSpinnerLabel = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
    }
    public void add(View view) {
        dbHelper = new DataHelper(this);
        EditText etTitle = findViewById(R.id.title);
        EditText etLocation = (EditText) findViewById(R.id.location);
        EditText etDate = (EditText) findViewById(R.id.date);
        EditText etStory = findViewById(R.id.story);

        if ((etTitle != null) && (etLocation != null)&& (etDate != null)&& (etStory != null)) {

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("INSERT INTO "+DataHelper.MyColumns.namaTabel+"("+DataHelper.MyColumns.title+", "+DataHelper.MyColumns.location+", "+DataHelper.MyColumns.date_visit+", "+DataHelper.MyColumns.short_story+") VALUES('" +
                    etTitle.getText().toString() +"','"+
                    etLocation.getText().toString() +"','"+
                    etDate.getText().toString() +"','"+
                    etStory.getText().toString()  + "')");

            Toast.makeText(getApplicationContext(), "Riwayat trip telah ditambahkan!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Gagal menambahkan, harap perikas kembali data yang diisikan!",Toast.LENGTH_LONG).show();
        }
        MainActivity.ma.RefreshList();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_read) {
            Intent iread = new Intent(this, MainActivity.class);
            startActivity(iread);
            return true;
        } else if (id == R.id.action_create) {
            Intent icreate = new Intent(this, CreateTripActivity.class);
            startActivity(icreate);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
