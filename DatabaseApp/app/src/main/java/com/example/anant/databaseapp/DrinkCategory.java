package com.example.anant.databaseapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class DrinkCategory extends AppCompatActivity {

    private  ListView listView;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        Intent intent=getIntent();
        id=intent.getIntExtra("position",0);
        listView=findViewById(R.id.drink_category);


//        SQLiteOpenHelper starBucksHelper = new StarbucksDatabaseHelper(this);
//        try{
//            SQLiteDatabase db = starBucksHelper.getReadableDatabase();
//            String[] DATABASE_NAMES={"DRINK","FOOD"};
//            long count = DatabaseUtils.queryNumEntries(db, DATABASE_NAMES[id]);
//            String[] strings=new String[(int)count];
//            Cursor cursor=db.query(DATABASE_NAMES[id],new String[] {"_id","NAME"},
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null);
//            if(cursor.moveToFirst()){
//
//
//                SimpleCursorAdapter cursorAdapter=new SimpleCursorAdapter(this,R.layout.drinks,cursor,new String[]{"NAME"},new int[] {R.id.drink},0);
//                listView.setAdapter(cursorAdapter);
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Intent intent=new Intent(DrinkCategory.this,DetailActivity.class);
//                        intent.putExtra("position",id);
//                        intent.putExtra("product",i);
//                        startActivity(intent);
//                    }
//                });
//            }
//
//
//        }catch (SQLiteException e){
//            Toast toast=Toast.makeText(this,"Database Unavailable",Toast.LENGTH_SHORT);
//            toast.show();
//        }
        fetchData getData= new fetchData();
        getData.execute();

    }



    private class fetchData extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            SQLiteOpenHelper starBucksHelper = new StarbucksDatabaseHelper(DrinkCategory.this);
            try{
                SQLiteDatabase db = starBucksHelper.getReadableDatabase();
                String[] DATABASE_NAMES={"DRINK","FOOD"};
                long count = DatabaseUtils.queryNumEntries(db, DATABASE_NAMES[id]);
                String[] strings=new String[(int)count];
                Cursor cursor=db.query(DATABASE_NAMES[id],new String[] {"_id","NAME"},
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                if(cursor.moveToFirst()){


                    SimpleCursorAdapter cursorAdapter=new SimpleCursorAdapter(DrinkCategory.this,R.layout.drinks,cursor,new String[]{"NAME"},new int[] {R.id.drink},0);
                    listView.setAdapter(cursorAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent=new Intent(DrinkCategory.this,DetailActivity.class);
                            intent.putExtra("position",id);
                            intent.putExtra("product",i);
                            startActivity(intent);
                        }
                    });
                }


            }catch (SQLiteException e){
                Toast toast=Toast.makeText(DrinkCategory.this,"Database Unavailable",Toast.LENGTH_SHORT);
                toast.show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
