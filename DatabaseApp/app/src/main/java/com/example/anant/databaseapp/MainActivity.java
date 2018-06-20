package com.example.anant.databaseapp;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

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
}
