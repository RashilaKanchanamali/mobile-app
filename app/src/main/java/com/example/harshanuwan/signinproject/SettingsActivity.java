package com.example.harshanuwan.signinproject;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private CircleImageView settingsProfile;
    private TextView settingsDisplayName;
    private Button settingsChangeProfileImage;

    private final static int Gallary_image = 1;
    private StorageReference storeProfileImageStorageReference;

    private DatabaseReference getUserDataReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();
        String online_user_id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        getUserDataReference = FirebaseDatabase.getInstance().getReference().child("Users").child(online_user_id);//connect to users unique id in firebase
        storeProfileImageStorageReference = FirebaseStorage.getInstance().getReference().child("Profile_images");


        settingsProfile = (CircleImageView) findViewById(R.id.settings_profile);
        settingsDisplayName = (TextView) findViewById(R.id.settings_user_name);
        settingsChangeProfileImage = (Button) findViewById(R.id.settings_edit_profile_botton);

        getUserDataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //get data values from database and store in these objects names. eg:name
                String name = Objects.requireNonNull(dataSnapshot.child("user_name").getValue()).toString();
                String image = Objects.requireNonNull(dataSnapshot.child("user_image").getValue()).toString();
                String thumb_image = Objects.requireNonNull(dataSnapshot.child("user_thumb_image").getValue()).toString();

                //call those objects
                settingsDisplayName.setText(name);
                Picasso.with(SettingsActivity.this).load(image).into(settingsProfile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        settingsChangeProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    //create to goto gallary in phone
                Intent gallery = new Intent();
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery, Gallary_image );
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallary_image && resultCode==RESULT_OK &&data!=null){
            Uri ImageUri = data.getData();
            CropImage.activity()   //set image cropping function
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) { //get the crop image uri and send it to firebase storage

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();

                String user_id = mAuth.getCurrentUser().getUid();
                StorageReference filePath = storeProfileImageStorageReference.child(user_id + ".jpg");
                filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SettingsActivity.this, "Saving profile image to firebase Storage ", Toast.LENGTH_LONG).show();

                            String downloadUrl = task.getResult().getUploadSessionUri().toString();
                            getUserDataReference.child("user_image").setValue(downloadUrl)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(SettingsActivity.this,
                                                    "Image Updated...", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                        }
                        else {
                            Toast.makeText(SettingsActivity.this, "Someting went wrong...", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
}
