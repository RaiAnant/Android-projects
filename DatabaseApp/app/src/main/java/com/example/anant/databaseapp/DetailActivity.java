package com.example.anant.databaseapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;
    private SQLiteOpenHelper starBucksHelper;
    private CheckBox checkBox;
    private int id;
    private int productNo;
    private String[] TABLE_NAMES={"DRINK","FOOD"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        id=intent.getIntExtra("position",0);
        productNo=intent.getIntExtra("product",0);
        checkBox=findViewById(R.id.favourite);


        starBucksHelper = new StarbucksDatabaseHelper(this);

        try{
            db = starBucksHelper.getReadableDatabase();

            cursor=db.query(TABLE_NAMES[id],
                    new String[] {"NAME", "DESCRIPTION","IMAGE_RESOURCE_ID","FAVOURITE"},
                    "_id = ?",
                    new String[]{Integer.toString(productNo+1)},
                    null,
                    null,
                    null);
            if(cursor.moveToFirst()) {
                ImageView imageView = findViewById(R.id.product_image);
                TextView textView1 = findViewById(R.id.name);
                TextView textView2 = findViewById(R.id.Discription);
                textView1.setText(cursor.getString(0));
                textView2.setText(cursor.getString(1));
                imageView.setImageResource(cursor.getInt(2));
                checkBox.setChecked(1==cursor.getInt(3));
            }
            else{
                Toast toast=Toast.makeText(this,"Unable to move cursor",Toast.LENGTH_SHORT);
                toast.show();
            }
        }catch(SQLiteException e){
            Toast toast=Toast.makeText(this,"Database Unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void favourite(View view){
        try {
            db = starBucksHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            if(checkBox.isChecked()) {
                contentValues.put("FAVOURITE", 1);
            }
            else{
                contentValues.put("FAVOURITE", 0);
            }
            db.update(TABLE_NAMES[id],contentValues,"_id=?",new String[] {Integer.toString(productNo+1)});
        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this,"Unable to add favourite, some error occoured",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
