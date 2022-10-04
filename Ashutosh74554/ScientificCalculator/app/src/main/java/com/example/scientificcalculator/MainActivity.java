package com.example.scientificcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openBasic(View v){
        Toast.makeText(this, "Going to basic calculator", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this,Basic.class);
        startActivity(intent);
    }
    public void openSci(View v1){
        Toast.makeText(this, "Going to scientific calculator", Toast.LENGTH_SHORT).show();
        Intent intent1= new Intent(this, Scientific.class);
        startActivity(intent1);
    }
}