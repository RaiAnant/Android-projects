package com.example.anantrai.multiple_activ;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView=(TextView)findViewById(R.id.display);
        Intent intent=getIntent();
        textView.setText(intent.getStringExtra("Message"));
    }
}
