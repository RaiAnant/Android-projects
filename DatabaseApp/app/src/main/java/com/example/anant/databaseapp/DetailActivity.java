package com.example.anant.databaseapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        int id=intent.getIntExtra("position",0);
        int productNo=intent.getIntExtra("product",0);
        Products products;
        if(id==0) {
            products = Products.drinks[productNo];
        }
        else{
            products = Products.foods[productNo];
        }


        String[] DATABASE_NAMES={"DRINK","FOOD"};

        SQLiteOpenHelper starBucksHelper = new StarbucksDatabaseHelper(this);

        try{
            SQLiteDatabase db = starBucksHelper.getReadableDatabase();

            Cursor cursor=db.query(DATABASE_NAMES[id],
                    new String[] {"NAME", "DESCRIPTION","IMAGE_RESOURCE_ID"},
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
            }
            else{
                Toast toast=Toast.makeText(this,"Unable to move cursor",Toast.LENGTH_SHORT);
                toast.show();
            }
        }catch(SQLiteException e){
            Toast toast=Toast.makeText(this,"Database Unavailable",Toast.LENGTH_SHORT);
            toast.show();
            ImageView imageView= findViewById(R.id.product_image);
            TextView textView = findViewById(R.id.Discription);
            textView.setText(products.getDiscription());
            imageView.setImageResource(R.drawable.latte);
        }

    }
}
