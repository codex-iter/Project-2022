package com.example.iterec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainTeacher extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    String uid;
    Toolbar toolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        fab=findViewById(R.id.fab);
        toolbar=findViewById(R.id.toolbar_1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ITERec: Update Records");

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        uid=auth.getCurrentUser().getUid();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainTeacher.this, Upload.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.sign_out){
            auth.signOut();
            Toast.makeText(MainTeacher.this, "Logging out...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }
        else if(item.getItemId()==R.id.update){
            startActivity(new Intent(MainTeacher.this,UpdateProfile.class));
        }
        return super.onOptionsItemSelected(item);
    }
}