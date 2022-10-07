package com.example.iterec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    FirebaseUser user;
    String uid, post_uid;
    TextView user_name, user_regd;
    CircleImageView user_image;
    Toolbar toolbar;
    CardView c1,c2,c3,c4,c5,c6,c7,c8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        user_image=findViewById(R.id.user_pic);
        user_name=findViewById(R.id.user_name);
        user_regd=findViewById(R.id.user_regd);
        c1=findViewById(R.id.sem_1);
        c2=findViewById(R.id.sem_2);
        c3=findViewById(R.id.sem_3);
        c4=findViewById(R.id.sem_4);
        c5=findViewById(R.id.sem_5);
        c6=findViewById(R.id.sem_6);
        c7=findViewById(R.id.sem_7);
        c8=findViewById(R.id.sem_8);



        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firestore=FirebaseFirestore.getInstance();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ITERec: See Your Record");

        if(user!=null) {
            this.post_uid=Upload.post_uid;
            uid = user.getUid();

            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,Result.class));
                }
            });

            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Results are not uploaded yet", Toast.LENGTH_SHORT).show();
                }
            });
            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Results are not uploaded yet", Toast.LENGTH_SHORT).show();
                }
            });
            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Results are not uploaded yet", Toast.LENGTH_SHORT).show();
                }
            });
            c5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Results are not uploaded yet", Toast.LENGTH_SHORT).show();
                }
            });
            c6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Results are not uploaded yet", Toast.LENGTH_SHORT).show();
                }
            });
            c7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Results are not uploaded yet", Toast.LENGTH_SHORT).show();
                }
            });
            c8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Results are not uploaded yet", Toast.LENGTH_SHORT).show();
                }
            });

            firestore.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        if(task.getResult().exists()){
                            String name=task.getResult().getString("Name");
                            String regd=task.getResult().getString("Regd");
                            String pro_pic=task.getResult().getString("Image");

                            user_name.setText(name);
                            user_regd.setText(regd);
                            Uri image=Uri.parse(pro_pic);
                            Glide.with(MainActivity.this).load(image).into(user_image);
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.sign_out){
            auth.signOut();
            Toast.makeText(MainActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }
        else if(item.getItemId()==R.id.update){
            startActivity(new Intent(MainActivity.this,UpdateProfile.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= auth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(MainActivity.this,Login.class));
            finish();
        }
        else{
            uid=user.getUid();
            firestore.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        if(task.getResult().getString("Image")==null){
                            startActivity(new Intent(getApplicationContext(),UpdateProfile.class));
                            finish();
                        }
                        if(task.getResult().getString("Role").equalsIgnoreCase("Teacher")){
                            startActivity(new Intent(getApplicationContext(),MainTeacher.class));
                            finish();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}