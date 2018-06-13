package com.example.anantrai.multiple_activ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //public EditText editText=(EditText) findViewById(R.id.message);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findMessage(View view){
        EditText editText=(EditText)findViewById(R.id.message);
        Intent intent=new Intent(this,Main2Activity.class);
        intent.putExtra("Message",editText.getText().toString());
        startActivity(intent);
    }

    public void sendMessage(View view){
        EditText editText=(EditText)findViewById(R.id.message);
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,editText.getText().toString());
        Intent chosenIntent=intent.createChooser(intent,getString(R.string.chooser));
        startActivity(chosenIntent);
    }
}
