package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.RandomAccessFile;

public class ListviewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private TextView textView;
    private ListView listView;
    private ArrayAdapter<String> arr_adapter;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
        index = Integer.parseInt(bundle.getString("num"));//getString()返回指定key的值
        System.out.println("num: "+index);
        textView = (TextView) findViewById(R.id.textView_list);
        listView = (ListView) findViewById(R.id.listView);
        String[] arr_data = getResources().getStringArray(R.array.A1);
        if(index == 1)textView.setText(getResources().getString(R.string.Q1));
        if(index == 2){
            arr_data = getResources().getStringArray(R.array.A2);
            textView.setText(getResources().getString(R.string.Q2));
        }
        if(index == 3){
            arr_data = getResources().getStringArray(R.array.A3);
            textView.setText(getResources().getString(R.string.Q3));
        }
        if(index == 4){
            arr_data = getResources().getStringArray(R.array.A4);
            textView.setText(getResources().getString(R.string.Q4));
        }
        if(index == 7){
            arr_data = getResources().getStringArray(R.array.A7);
            textView.setText(getResources().getString(R.string.Q7));
        }
        if(index == 8){
            arr_data = getResources().getStringArray(R.array.A8);
            textView.setText(getResources().getString(R.string.Q8));
        }
        if(index == 9){
            arr_data = getResources().getStringArray(R.array.A9);
            textView.setText(getResources().getString(R.string.Q9));
        }
        if(index == 10){
            arr_data = getResources().getStringArray(R.array.A10);
            textView.setText(getResources().getString(R.string.Q10));
        }
        if(index == 11){
            arr_data = getResources().getStringArray(R.array.A11);
            textView.setText(getResources().getString(R.string.Q11));
        }
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_data);
        listView.setAdapter(arr_adapter);
        listView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        if(index == 1){
            File file = new File(Environment.getExternalStorageDirectory().getPath()+"/survey/result.txt");
            if (file.exists())
                file.delete();
            String[] arr_result = getResources().getStringArray(R.array.R1);
            writeTxtToFile(arr_result[position], Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
        }
        if(index == 2){
            String[] arr_result = getResources().getStringArray(R.array.R2);
            writeTxtToFile(arr_result[position], Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
        }
        if(index == 3){
            String[] arr_result = getResources().getStringArray(R.array.R3);
            writeTxtToFile(arr_result[position], Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
        }
        if(index == 4){
            String[] arr_result = getResources().getStringArray(R.array.R4);
            writeTxtToFile(arr_result[position], Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
        }
        if(index == 7){
            String[] arr_result = getResources().getStringArray(R.array.R7);
            writeTxtToFile(arr_result[position], Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
        }
        if(index == 8){
            String[] arr_result = getResources().getStringArray(R.array.R8);
            writeTxtToFile(arr_result[position], Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
        }
        if(index == 9){
            String[] arr_result = getResources().getStringArray(R.array.R9);
            writeTxtToFile(arr_result[position], Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
        }
        if(index == 10){
            String[] arr_result = getResources().getStringArray(R.array.R10);
            writeTxtToFile(arr_result[position], Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
        }
        if(index == 11){
            String[] arr_result = getResources().getStringArray(R.array.R11);
            writeTxtToFile(arr_result[position], Environment.getExternalStorageDirectory().getPath()+"/survey/", "result.txt");
        }
        if((index > 0 && index < 4) || (index >= 7 && index < 11)) {
            Intent intent = new Intent(ListviewActivity.this, ListviewActivity.class);
            intent.putExtra("num", ""+(index+1));//point out which question should be loading in ListviewActivity.java
            startActivity(intent);
        }
        if(index == 4){
            Intent intent = new Intent(ListviewActivity.this, EdittextActivity.class);
            intent.putExtra("num", ""+(index+1));//point out which question should be loading in ListviewActivity.java
            startActivity(intent);
        }
        if(index == 11){
            Intent intent = new Intent(ListviewActivity.this, CheckboxActivity.class);
            startActivity(intent);
        }
    }

    // 将字符串写入到文本文件中
    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath+fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

    // 生成文件
    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e+"");
        }
    }
}
