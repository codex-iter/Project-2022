package com.example.scientificcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Inverse extends AppCompatActivity {
    private EditText in;
    private TextView resu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inverse);
        in=findViewById(R.id.in);
        resu=findViewById(R.id.resu);
        Button asin=findViewById(R.id.asin);
        asin.setText(Html.fromHtml("<small>sin <sup>-1</sup></small>"));
        Button acos=findViewById(R.id.acos);
        acos.setText(Html.fromHtml("<small>cos <sup>-1</sup></small>"));
        Button atan=findViewById(R.id.atan);
        atan.setText(Html.fromHtml("<small>tan <sup>-1</sup></small>"));
        Button cuber=findViewById(R.id.cuber);
        cuber.setText(Html.fromHtml("<big>&#8731;</big>"));
        Button sinh=findViewById(R.id.sinh);
        Button cosh=findViewById(R.id.cosh);
        Button tanh=findViewById(R.id.tanh);
        Button cube=findViewById(R.id.cube);
        cube.setText(Html.fromHtml("<small>x <sup>3</sup></small>"));
        Button sinhi=findViewById(R.id.sinhi);
        sinhi.setText(Html.fromHtml("<small>sinh <sup>-1</sup></small>"));
        Button coshi=findViewById(R.id.coshi);
        coshi.setText(Html.fromHtml("<small>cosh <sup>-1</sup></small>"));
        Button tanhi=findViewById(R.id.tanhi);
        tanhi.setText(Html.fromHtml("<small>tanh <sup>-1</sup></small>"));
        Button twopowx=findViewById(R.id.twopowx);
        twopowx.setText(Html.fromHtml("2 <sup>x</sup>"));
        asin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=Math.asin(n);
                String s=""+n1;
                resu.setText(s);
            }
        });
        acos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=Math.acos(n);
                String s=""+n1;
                resu.setText(s);
            }
        });
        atan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=Math.atan(n);
                String s=""+n1;
                resu.setText(s);
            }
        });
        cuber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=Math.cbrt(n);
                String s=""+n1;
                resu.setText(s);
            }
        });
        sinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=Math.sinh(n);
                String s=""+n1;
                resu.setText(s);
            }
        });
        cosh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=Math.cosh(n);
                String s=""+n1;
                resu.setText(s);
            }
        });
        tanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=Math.tanh(n);
                String s=""+n1;
                resu.setText(s);
            }
        });
        cube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=Math.pow(n,3);
                String s=""+n1;
                resu.setText(s);
            }
        });
        sinhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=Math.log(n+Math.sqrt(n*n +1.0));
                String s=""+n1;
                resu.setText(s);
            }
        });
        coshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=Math.log(n+Math.sqrt(n*n -1.0));
                String s=""+n1;
                resu.setText(s);
            }
        });
        tanhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=0.5* Math.log((n+1.0)/(n-1.0));
                String s=""+n1;
                resu.setText(s);
            }
        });
        twopowx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(in.getText().toString());
                double n1=Math.pow(2,n);
                String s=""+n1;
                resu.setText(s);
            }
        });
    }
}