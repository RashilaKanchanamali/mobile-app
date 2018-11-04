package com.example.harshanuwan.signinproject;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SiginUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etFirstname, etLastname, etUsername, etEmail, etPassword, etVpassword;
    Button bSignup;
    //initialize variables
    private FirebaseAuth mAuth;
    private DatabaseReference storeUserDefaltDataReference;
    String firstName;
    String lastName;
    String userName ;
    String email;
    String password;
    String vpassword;

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_up);
        mAuth = FirebaseAuth.getInstance();//Connect firebase

        //setup toolbar with name of this intent and back arrow
        Toolbar mToolbar = (Toolbar) findViewById(R.id.sign_in_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etFirstname = (EditText) findViewById(R.id.etFirstname);
        etLastname = (EditText) findViewById(R.id.etLastname);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etVpassword = (EditText) findViewById(R.id.etVpassword);
        bSignup = (Button) findViewById(R.id.bSignup);
        loadingBar = new ProgressDialog(this);

        bSignup.setOnClickListener(new View.OnClickListener() { // when click signup botton
            @Override
            public void onClick(View v) {

                registerUser();
            }
        });
    }

        private void registerUser(){
            firstName = etFirstname.getText().toString().trim();
            lastName = etLastname.getText().toString().trim();
            userName = etUsername.getText().toString().trim();
            email = etEmail.getText().toString().trim();
            password = etPassword.getText().toString().trim();
            vpassword = etVpassword.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty() || userName.isEmpty() || vpassword.isEmpty()){
            Toast.makeText(SiginUpActivity.this,"Fill black textviews",Toast.LENGTH_SHORT).show();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){ // error msg for invalid email address
            Toast.makeText(SiginUpActivity.this,"Email is invalied",Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return;
        }
        if(!password.equals(vpassword)){
            Toast.makeText(SiginUpActivity.this,"Not Equal passowrd",Toast.LENGTH_SHORT).show();
        }

        if(password.length()<6){
            Toast.makeText(SiginUpActivity.this,"Password greater than 6 characters",Toast.LENGTH_SHORT).show();
            etPassword.requestFocus();
            return;
        }
        else {
            loadingBar.setTitle("New user acccount creating");
            loadingBar.setMessage("Please wait");
            loadingBar.show();
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Registered successfull", Toast.LENGTH_SHORT).show();

                    String current_user_id = mAuth.getCurrentUser().getUid ();
                    storeUserDefaltDataReference = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user_id);//create reference and store inside storeUserDefaltDataReference
                                                                                                                   //pass the uid to users

                    //set values to database
                    storeUserDefaltDataReference.child("user_id").setValue(current_user_id);
                    storeUserDefaltDataReference.child("user_name").setValue(userName);
                    storeUserDefaltDataReference.child("user_image").setValue("defalte_profile");
                    storeUserDefaltDataReference.child("user_thumb_image").setValue("default_image")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent mainIntent = new Intent(SiginUpActivity.this,MainPageActivity.class);
                                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(mainIntent);
                                        finish();
                                    }

                                }
                            });


                }
                else {
                    Toast.makeText(SiginUpActivity.this,"Something wrong, Try again...",
                            Toast.LENGTH_SHORT).show();
                }

                loadingBar.dismiss();

            }
        });
        }
    @Override
    public void onClick(View view) {

    }



}
