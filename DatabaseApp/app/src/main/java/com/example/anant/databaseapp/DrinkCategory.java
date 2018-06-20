package com.example.anant.databaseapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class DrinkCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        Intent intent=getIntent();
        final int id=intent.getIntExtra("position",0);
        ListView listView=findViewById(R.id.drink_category);
//        Products[] products;
//        if(id==0) {
//            products = Products.drinks;
//        }
//        else{
//            products = Products.foods;
//        }
//        int len=products.length;
//        String[] strings=new String[len];
//        for(int i=0;i<len;i++){
//            strings[i]=products[i].getName();
//        }

        SQLiteOpenHelper starBucksHelper = new StarbucksDatabaseHelper(this);
        try{
            SQLiteDatabase db = starBucksHelper.getReadableDatabase();
            String[] DATABASE_NAMES={"DRINK","FOOD"};
            long count = DatabaseUtils.queryNumEntries(db, DATABASE_NAMES[id]);
            String[] strings=new String[(int)count];
            Cursor cursor=db.query(DATABASE_NAMES[id],new String[] {"NAME",
                    "DESCRIPTION","IMAGE_RESOURCE_ID"},
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            if(cursor.moveToFirst()){

                for(int i=0;i<strings.length;i++){
                    strings[i]=cursor.getString(0);
                    if(cursor.moveToNext()){
                        continue;
                    }
                    break;
                }
            }


            ArrayList<String> arrayList=new <String>ArrayList(Arrays.asList(strings));

            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.drinks,R.id.drink,arrayList);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent=new Intent(DrinkCategory.this,DetailActivity.class);
                    intent.putExtra("position",id);
                    intent.putExtra("product",i);
                    startActivity(intent);
                }
            });
        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this,"Database Unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }




    }

}
