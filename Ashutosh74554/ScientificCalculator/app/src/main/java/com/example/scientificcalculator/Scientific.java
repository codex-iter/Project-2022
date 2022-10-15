package com.example.scientificcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Scientific extends AppCompatActivity {
    private EditText input, in2;
    private TextView resultsci;
    private Button sin,cos,tan,log,fact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific);
        Button square = findViewById(R.id.square);
        square.setText((Html.fromHtml("x <sup> 2 </sup>")));
        Button epowx=findViewById(R.id.epowx);
        epowx.setText((Html.fromHtml("e <sup><small> x </small></sup>")));
        Button xpowy=findViewById(R.id.xpowy);
        xpowy.setText(Html.fromHtml("x <sup><small> y </small></sup>"));
        Button pi=findViewById(R.id.pi);
        pi.setText("\u03C0");
        Button root=findViewById(R.id.root);
        root.setText("\u221a");
        Button ed=findViewById(R.id.ed);
        ed.setText("e");
        sin=findViewById(R.id.sin);
        cos=findViewById(R.id.cos);
        tan=findViewById(R.id.tan);
        log=findViewById(R.id.log);
        fact=findViewById(R.id.fact);
        resultsci=findViewById(R.id.resultsci);
        input=findViewById(R.id.input);
        in2=findViewById(R.id.in2);

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(input.getText().toString());
                double n1=Math.sin(n);
                String s=""+n1;
                resultsci.setText(s);
            }
        });
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(input.getText().toString());
                double n1=Math.cos(n);
                String s=""+n1;
                resultsci.setText(s);
            }
        });
        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(input.getText().toString());
                double n1=Math.tan(n);
                String s=""+n1;
                resultsci.setText(s);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(input.getText().toString());
                double n1=Math.log(n);
                String s=""+n1;
                resultsci.setText(s);
            }
        });
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(input.getText().toString());
                double n1=Math.pow(n,2);
                String s=""+n1;
                resultsci.setText(s);
            }
        });
        epowx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(input.getText().toString());
                double n1=Math.exp(n);
                String s=""+n1;
                resultsci.setText(s);
            }
        });
        xpowy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1=Double.parseDouble(input.getText().toString());
                double n2=Double.parseDouble(in2.getText().toString());
                double n=Math.pow(n1,n2);
                String s=""+n;
                resultsci.setText(s);
            }
        });
        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(input.getText().toString());
                double n1=Math.PI*n;
                String s=""+n1;
                resultsci.setText(s);
            }
        });
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(input.getText().toString());
                double n1=Math.sqrt(n);
                String s=""+n1;
                resultsci.setText(s);
            }
        });
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n=Double.parseDouble(input.getText().toString());
                double n1=Math.exp(1)*n;
                String s=""+n1;
                resultsci.setText(s);
            }
        });
        fact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long n=Long.parseLong(input.getText().toString());
                long n1=1;
                for(long i=n;i>0;i--){
                    n1*=i;
                }
                String s=""+n1;
                resultsci.setText(s);
            }
        });
    }
    public void changing(View v){
        Intent int1=new Intent(this,Inverse.class);
        startActivity(int1);
    }

}