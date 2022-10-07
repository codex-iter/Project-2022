package com.example.iterec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    EditText username, mail, pass, regdNo;
    Button signup;
    CheckBox check;
    FirebaseAuth auth;
    StorageReference storageReference;
    FirebaseFirestore firestore;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username=findViewById(R.id.name_to_signup);
        mail=findViewById(R.id.email_to_signup);
        pass=findViewById(R.id.pass_to_signup);
        regdNo=findViewById(R.id.regd_no_to_signup);

        signup=findViewById(R.id.signup_btn);
        check=findViewById(R.id.check);

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mail.getText().toString();
                String password=pass.getText().toString();
                String regd=regdNo.getText().toString();
                String name=username.getText().toString();
                if(email.isEmpty() || password.isEmpty() || regd.isEmpty() || name.isEmpty()) {
                    if (email.isEmpty()) {
                        mail.setError("Required field");
                    }
                    if (password.isEmpty()) {
                        pass.setError("Required field");
                    }
                    if(regd.isEmpty()){
                        regdNo.setError("Required field");
                    }
                    if(name.isEmpty()){
                        username.setError("Required field");
                    }
                }
                else {
                    auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(SignUp.this, "Verification mail sent", Toast.LENGTH_SHORT).show();
                                    uid= auth.getCurrentUser().getUid();

                                    DocumentReference documentReference=firestore.collection("Users").document(uid);
                                    HashMap<String,Object> map=new HashMap<>();
                                    map.put("Name",name);
                                    map.put("Regd",regd);
                                    if(check.isChecked())
                                        map.put("Role","Teacher");
                                    else
                                        map.put("Role","Student");

                                    documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(SignUp.this, "Account created successfully!!!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(SignUp.this,Login.class));
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}