package com.example.iterec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Upload extends AppCompatActivity {
    EditText s_name, sem_no, s_sgpa, s_cgpa, result;
    Button upload;
    FirebaseAuth auth;
    public static String post_uid;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        auth=FirebaseAuth.getInstance();
        post_uid=auth.getCurrentUser().getUid();
        firestore=FirebaseFirestore.getInstance();

        s_name=findViewById(R.id.name_to_enter);
        sem_no=findViewById(R.id.sem_no);
        s_sgpa=findViewById(R.id.sgpa);
        s_cgpa=findViewById(R.id.cgpa);
        result=findViewById(R.id.result);
        upload=findViewById(R.id.upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=s_name.getText().toString();
                String sem=sem_no.getText().toString();
                String sgpa=s_sgpa.getText().toString();
                String cgpa=s_cgpa.getText().toString();
                String res=result.getText().toString();

                if(name.isEmpty() || sem.isEmpty() || sgpa.isEmpty() || cgpa.isEmpty() || res.isEmpty()){
                    Toast.makeText(Upload.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    DocumentReference documentReference=firestore.collection("Result").document(post_uid);
                    HashMap<String,Object> map=new HashMap<>();
                    map.put("Student name", name);
                    map.put("Semester", sem);
                    map.put("SGPA", sgpa);
                    map.put("CGPA", cgpa);
                    map.put("Result",res);

                    documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Upload.this, "Result uploaded!!!", Toast.LENGTH_SHORT).show();
                            s_name.setText("");
                            sem_no.setText("");
                            s_sgpa.setText("");
                            s_cgpa.setText("");
                            result.setText("");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Upload.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}