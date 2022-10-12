package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] qstns={"Java was introduced in 1987?", "Java supports OOP structure?", "Java was World's first programming language?", "Java was created using Python?", "Java supports interfaces?"};
    private  boolean[] ans={false, true,false,false,true};
    private int index=0, score=0;
    Button tr, fl;
    TextView qs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qs=findViewById(R.id.q);
        tr=findViewById(R.id.yes);
        fl=findViewById(R.id.no);
        qs.setText(qstns[index]);

        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index >= qstns.length - 1) {
                    if (ans[qstns.length - 1])
                        score++;
                    Toast.makeText(MainActivity.this, "Your score: " + score, Toast.LENGTH_SHORT).show();
                } else if (ans[index]) {
                    score++;
                    qs.setText(qstns[++index]);
                } else
                    qs.setText(qstns[++index]);
            }
        });
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index>=qstns.length-1){
                    if(ans[qstns.length-1])
                        score++;
                    Toast.makeText(MainActivity.this, "Your score: "+score, Toast.LENGTH_SHORT).show();
                }
                else if(!ans[index]) {
                    score++;
                    qs.setText(qstns[++index]);
                }
                else
                    qs.setText(qstns[++index]);
            }
        });
    }
}