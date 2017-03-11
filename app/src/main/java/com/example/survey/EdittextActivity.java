package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EdittextActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private ArrayAdapter<String> arr_adapter;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);

        Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
        index = Integer.parseInt(bundle.getString("num"));//getString()返回指定key的值
        System.out.println("num: "+index);

        textView = (TextView) findViewById(R.id.textView_edit);
        editText = (EditText) findViewById(R.id.editText);
        if(index == 5){
            textView.setText(getResources().getString(R.string.Q5));
        }
        if(index == 6){
            textView.setText(getResources().getString(R.string.Q6));
        }
    }

    public void button_edit(View v){
        int number = Integer.parseInt(editText.getText().toString());
        if(number >= 0 && number <= 10){
            if(index == 5){
                int pos;
                if(number == 0)pos = 0;
                else if(number <= 4)pos = 1;
                else if(number == 5)pos = 2;
                else if(number <= 9)pos = 3;
                else pos = 4;
                String[] arr_result = getResources().getStringArray(R.array.R5);
                ListviewActivity.writeTxtToFile(arr_result[pos], Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
                Intent intent = new Intent(EdittextActivity.this, EdittextActivity.class);
                intent.putExtra("num", ""+(index+1));//point out which question should be loading in ListviewActivity.java
                startActivity(intent);
            }
            if(index == 6){
                int pos;
                if(number <= 1)pos = 0;
                else if(number <= 6)pos = 1;
                else pos = 2;
                String[] arr_result = getResources().getStringArray(R.array.R6);
                ListviewActivity.writeTxtToFile(arr_result[pos], Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
                Intent intent = new Intent(EdittextActivity.this, ListviewActivity.class);
                intent.putExtra("num", ""+(index+1));//point out which question should be loading in ListviewActivity.java
                startActivity(intent);
            }
        }else{
            Toast.makeText(getApplicationContext(), "Please choose a number ranging from 0 to 10!", Toast.LENGTH_SHORT).show();
            editText.setText("");
            editText.setFocusable(true);
        }
    }
}
