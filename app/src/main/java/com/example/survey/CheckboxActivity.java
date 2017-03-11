package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CheckboxActivity extends AppCompatActivity{
    private List<CheckBox> checkBoxList = new ArrayList<CheckBox>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);
        CheckBox checkBox;
        checkBox = (CheckBox) findViewById(R.id.checkBox_1);
        checkBoxList.add(checkBox);
        checkBox = (CheckBox) findViewById(R.id.checkBox_2);
        checkBoxList.add(checkBox);
        checkBox = (CheckBox) findViewById(R.id.checkBox_3);
        checkBoxList.add(checkBox);
        checkBox = (CheckBox) findViewById(R.id.checkBox_4);
        checkBoxList.add(checkBox);
        checkBox = (CheckBox) findViewById(R.id.checkBox_5);
        checkBoxList.add(checkBox);
        checkBox = (CheckBox) findViewById(R.id.checkBox_6);
        checkBoxList.add(checkBox);
        checkBox = (CheckBox) findViewById(R.id.checkBox_7);
        checkBoxList.add(checkBox);
        checkBox = (CheckBox) findViewById(R.id.checkBox_8);
        checkBoxList.add(checkBox);
        checkBox = (CheckBox) findViewById(R.id.checkBox_9);
        checkBoxList.add(checkBox);
        checkBox = (CheckBox) findViewById(R.id.checkBox_10);
        checkBoxList.add(checkBox);
        checkBox = (CheckBox) findViewById(R.id.checkBox_11);
        checkBoxList.add(checkBox);
        try {
            File urlFile = new File(Environment.getExternalStorageDirectory().getPath()+"/survey/result.txt");
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            int i = 0;
            String mimeTypeLine = null ;
            while ((mimeTypeLine = br.readLine()) != null && i < 11) {
                checkBoxList.get(i).setText(mimeTypeLine);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void button_last(View v){
        StringBuffer sb = new StringBuffer();
        //遍历集合中的checkBox,判断是否选择，获取选中的文本
        for (CheckBox checkbox : checkBoxList) {
            if (checkbox.isChecked()){
                sb.append(checkbox.getText().toString() + "\n");
            }
        }
        ListviewActivity.writeTxtToFile("\nThe accurate options:\n"+sb, Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
        Intent intent = new Intent(CheckboxActivity.this, LastActivity.class);
        startActivity(intent);
    }
}
