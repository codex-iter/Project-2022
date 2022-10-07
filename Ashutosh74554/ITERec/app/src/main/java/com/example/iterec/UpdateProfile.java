package com.example.iterec;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfile extends AppCompatActivity {
    CircleImageView image;
    Button save;
    ProgressBar progressBar;
    FirebaseAuth auth;
    Uri imageUri;
    final static int PICK_IMAGE=1;
    boolean isPhotoSelected=false;
    StorageReference storageReference;
    FirebaseFirestore firestore;
    String uid, name="",regd="",role="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        image=findViewById(R.id.update_pic);
        save=findViewById(R.id.btn_update);
        progressBar=findViewById(R.id.progress);

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();
        uid=auth.getCurrentUser().getUid();

        progressBar.setVisibility(View.INVISIBLE);

        firestore.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        name=task.getResult().getString("Name");
                        regd=task.getResult().getString("Regd");
                        role=task.getResult().getString("Role");
                        try {
                            String uri_existing = task.getResult().getString("Image");
                            imageUri = Uri.parse(uri_existing);
                            Glide.with(UpdateProfile.this).load(uri_existing).into(image);
                        }catch(Exception e){

                        }
                    }
                }
                else{
                    Toast.makeText(UpdateProfile.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery=new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                StorageReference imagerRef=storageReference.child("Profile Pic").child(uid+".jpg");
                if(isPhotoSelected) {
                    if (imageUri != null) {
                        imagerRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()) {
                                    imagerRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            saveToFirestore(task, uri,name,regd,role);                    // TODO: CHECK EXCEPTION
                                        }
                                    });
                                    Toast.makeText(UpdateProfile.this, "Profile updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(UpdateProfile.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(UpdateProfile.this, "Please Select Picture", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(UpdateProfile.this, "Please Select Picture", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveToFirestore(Task<UploadTask.TaskSnapshot> task, Uri downloadUri, String name, String regd, String role) {
        HashMap<String, Object> map=new HashMap<>();
        map.put("Image", downloadUri.toString());
        map.put("Name",name);
        map.put("Regd",regd);
        map.put("Role",role);
        firestore.collection("Users").document(uid).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(UpdateProfile.this, "Profile photo updated!!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateProfile.this, MainActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
                imageUri = data.getData();
                image.setImageURI(imageUri);

                isPhotoSelected = true;
            }
        }catch (Exception e) {
            Toast.makeText(this,e.getMessage() , Toast.LENGTH_SHORT).show();
        }
    }
}