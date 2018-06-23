package com.example.anant.databaseapp;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private String[] TABLE_NAMES={"DRINK","FOOD"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=findViewById(R.id.product_list);
        String[] strings={"Drinks","Food","Stores"};
        ArrayList<String> arrayList=new <String>ArrayList(Arrays.asList(strings));
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.list_items,R.id.list_text_view,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,DrinkCategory.class);
                intent.putExtra("position",i);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        SQLiteOpenHelper dbHelper= new StarbucksDatabaseHelper(this);
        try{
            ListView listView = findViewById(R.id.favourite_list);
            final SQLiteDatabase db=dbHelper.getReadableDatabase();
            final ArrayList<String> fav= new ArrayList<String>();
            final ArrayList<Integer> _id=new ArrayList<Integer>();
            final ArrayList<Integer> type=new ArrayList<Integer>();
            Cursor cursor;
            for(int i=0;i<2;i++){
                cursor=db.query(TABLE_NAMES[i],new String[]{"_id","NAME"},"FAVOURITE=?",new String[]{Integer.toString(1)},null,null,null);
                if(cursor.moveToFirst()){
                    do {
                        fav.add(cursor.getString(1));
                        type.add(i);
                        _id.add(cursor.getInt(0));
                    }while (cursor.moveToNext());
                }
            }
            final ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.list_items,R.id.list_text_view,fav);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    adapter.getItem(i);
                    Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                    intent.putExtra("position",type.get(i));
                    intent.putExtra("product",_id.get(i)-1);
                    startActivity(intent);
                }
            });
        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this,"Unable to display favourites",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
