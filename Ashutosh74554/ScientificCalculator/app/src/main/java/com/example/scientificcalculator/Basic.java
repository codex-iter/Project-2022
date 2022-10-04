package com.example.scientificcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Basic extends AppCompatActivity {
    private EditText input1, input2;
    private TextView result;
    private Button add, sub, mul, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        input1=findViewById(R.id.input1);
        input2=findViewById(R.id.input2);
        result=findViewById(R.id.result);
        add=findViewById(R.id.add);
        sub=findViewById(R.id.sub);
        mul=findViewById(R.id.mul);
        div=findViewById(R.id.div);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1=Double.parseDouble(input1.getText().toString());
                double n2=Double.parseDouble(input2.getText().toString());
                String s=""+(n1+n2);
                result.setText(s);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1=Double.parseDouble(input1.getText().toString());
                double n2=Double.parseDouble(input2.getText().toString());
                String s=""+(n1-n2);
                result.setText(s);
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1=Double.parseDouble(input1.getText().toString());
                double n2=Double.parseDouble(input2.getText().toString());
                String s=""+(n1*n2);
                result.setText(s);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1=Double.parseDouble(input1.getText().toString());
                double n2=Double.parseDouble(input2.getText().toString());
                String s=""+(n1/n2);
                result.setText(s);
            }
        });
    }
}