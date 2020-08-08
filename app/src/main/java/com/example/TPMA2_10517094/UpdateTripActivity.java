package com.example.TPMA2_10517094;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateTripActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DataHelper dbHelper;
    // Define TAG for logging.
    private static final String TAG = MainActivity.class.getSimpleName();
    // Define mSpinnerLabel for the label (the spinner item that the user chooses).
    private String mSpinnerLabel = "";
    protected Cursor cursor;
    Button tonSave;
    EditText textTitle, textLocation, textDate, textStory;
    private int idTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_trip);


        dbHelper = new DataHelper(this);
        textTitle = (EditText) findViewById(R.id.title);
        textLocation = (EditText) findViewById(R.id.location);
        textDate = (EditText) findViewById(R.id.date);
        textStory = (EditText) findViewById(R.id.story);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM "+DataHelper.MyColumns.namaTabel+" WHERE "+DataHelper.MyColumns.id_trip+" = '" + getIntent().getStringExtra(DataHelper.MyColumns.id_trip) + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            idTrip = Integer.parseInt(cursor.getString(0).toString());
            textTitle.setText(cursor.getString(1).toString());
            textLocation.setText(cursor.getString(2).toString());
            textDate.setText(cursor.getString(3).toString());
            textStory.setText(cursor.getString(4).toString());
        }
        tonSave = (Button) findViewById(R.id.button_main);

        tonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if ((textTitle != null) && (textLocation != null)) {

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("UPDATE "+DataHelper.MyColumns.namaTabel+" set "+DataHelper.MyColumns.title+"='" +
                            textTitle.getText().toString() +"', "+DataHelper.MyColumns.location+"='" +
                            textLocation.getText().toString()+"', "+DataHelper.MyColumns.date_visit+"='"+
                            textDate.getText().toString() +"', "+DataHelper.MyColumns.short_story+"='" +
                            textStory.getText().toString()+"' where "+DataHelper.MyColumns.id_trip+"='" +
                            idTrip+"'");
                    Toast.makeText(getApplicationContext(), "Success",
                            Toast.LENGTH_LONG).show();
                }
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long
            id) {
        mSpinnerLabel = adapterView.getItemAtPosition(pos).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.i(TAG, getString(R.string.nothing_selected));
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
