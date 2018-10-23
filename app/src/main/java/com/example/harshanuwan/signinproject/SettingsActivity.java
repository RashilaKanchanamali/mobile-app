package com.example.harshanuwan.signinproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private CircleImageView settingsProfile;
    private TextView settingsDisplayName;
    private Button settingsChangeProfileImage;

    private DatabaseReference getUserDataReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();
        String online_user_id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        getUserDataReference = FirebaseDatabase.getInstance().getReference().child("Users").child(online_user_id);//connect to users unique id in firebase

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


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
