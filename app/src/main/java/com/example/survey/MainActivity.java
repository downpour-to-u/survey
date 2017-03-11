package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button_read(View v){
        Intent intent=new Intent(MainActivity.this, ListviewActivity.class);
        intent.putExtra("num","1");//point out which question should be loading in ListviewActivity.java
        startActivity(intent);
    }
}
