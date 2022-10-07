package com.example.iterec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore firestore;
    EditText email, password;
    Button login;
    TextView signup, forgot;
    LayoutInflater inflater;
    AlertDialog.Builder reset_alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email_to_login);
        password=findViewById(R.id.pass_to_login);
        login=findViewById(R.id.login_btn);
        signup=findViewById(R.id.signup_page);
        forgot=findViewById(R.id.forgot);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firestore=FirebaseFirestore.getInstance();

        reset_alert=new AlertDialog.Builder(Login.this);

        signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(Login.this, SignUp.class));
             }
        });

        inflater=this.getLayoutInflater();
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v= inflater.inflate(R.layout.reset_popup, null);
                reset_alert.setTitle("Reset Password")
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText reset_email=v.findViewById(R.id.reset_mail_id);
                                String mail=reset_email.getText().toString();
                                if(mail.isEmpty()){
                                    reset_email.setError("Required Field");
                                    return;
                                }
                                auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Login.this, "Reset link sent!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .setView(v)
                        .create().show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=email.getText().toString();
                String pass=password.getText().toString();

                if(mail.isEmpty() || pass.isEmpty()){
                    if(mail.isEmpty()){
                        email.setError("Email can't be empty");
                    }
                    if(pass.isEmpty()){
                        password.setError("Password can't be empty");
                    }
                }
                else{
                    auth.signInWithEmailAndPassword(mail, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            user=auth.getCurrentUser();
                            if(user!=null && user.isEmailVerified()) {
                                Toast.makeText(Login.this, "Logging in...", Toast.LENGTH_SHORT).show();
                                String uid=user.getUid();
                                try{
                                    firestore.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if(task.isSuccessful()){
                                                if(task.getResult().exists()){
                                                    String role=task.getResult().getString("Role");
                                                    if(role.equalsIgnoreCase("Teacher")){
                                                        startActivity(new Intent(Login.this,MainTeacher.class));
                                                        finish();
                                                    }
                                                    else if(role.equalsIgnoreCase("Student")){
                                                        startActivity(new Intent(Login.this,MainActivity.class));
                                                        finish();
                                                    }
                                                }
                                            }
                                        }
                                    });
                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                                Toast.makeText(Login.this, "Please verify your E-mail first", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}