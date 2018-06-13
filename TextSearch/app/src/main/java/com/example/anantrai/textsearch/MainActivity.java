package com.example.anantrai.textsearch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;

    String string=new String();
    String str[]={"transformers","beyblade","pokemon","batman","Red Toy Wagon",
            "Chemistry Set",
            "Yo-Yo",
            "Pop-Up Book",
            "Generic Uncopyrighted Mouse",
            "Finger Paint",
            "Sock Monkey",
            "Microscope Set",
            "Beach Ball",
            "BB Gun",
            "Green Soldiers",
            "Bubbles",
            "Spring Toy",
            "Fortune Telling Ball",
            "Plastic Connecting Blocks",
            "Water Balloon",
            "Paint-by-Numbers Kit",
            "Tuber Head",
            "Cool Ball with Holes in It",
            "Toy Truck",
            "Flying Disc",
            "Two-Handed Pogo Stick",
            "Toy Hoop",
            "Dysmorphia Doll",
            "Toy Train",
            "Fake Vomit",
            "Toy Telephone",
            "Barrel of Primates",
            "Remote Control Car",
            "Square Puzzle Cube",
            "Football",
            "Intergalactic Electronic Phasers",
            "Baby Horse Dolls",
            "Machines that turn into other Machines",
            "Teddy Bears",
            "Shaved Ice Maker",
            "Glow Stick",
            "Squirt Guns",
            "Miniature Replica Animals Stuffed with Beads that you swore to your parents would be worth lots of money one day",
            "Creepy Gremlin Doll",
            "Neodymium-Magnet Toy"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text);
        editText = findViewById(R.id.search);
        textView.setText("");
        fullList();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                string=editText.getText().toString();
                if(string.isEmpty()){
                    textView.setText("");
                    fullList();
                }
                else{
                    textView.setText("");
                    searchText(string);
                }
            }
        });
        //searchThread();
    }

    public void fullList(){
        for(int i=0;i<str.length;i++)
        {
            textView.append(str[i]+"\n\n\n");
        }
    }


    public void searchText(String string){
        char ch1[]=string.toCharArray();
        for(int i=0;i<str.length;i++){
                if(str[i].indexOf(string)!= -1) {


                    textView.append(str[i] + "\n\n\n");

                }
        }
    }


    /*public void searchThread(){
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                string=(editText.getText()).toString();
                Log.v("String to search", string);
                if(string.isEmpty()){
                    textView.setText("");
                    fullList();
                }
                else{
                    textView.setText("");
                    searchText(string);
                }
                handler.postDelayed(this,10);
            }
        });

    }*/
}
