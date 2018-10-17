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

public class SiginUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etFirstname, etLastname, etUsername, etEmail, etPassword, etVpassword;
    Button bSignup;
    //initialize variables
    private FirebaseAuth mAuth;

    private ProgressDialog loadingBar;
    //private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_up);
        mAuth = FirebaseAuth.getInstance();//Connect firebasee

        //mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setTitle("Sign Up");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

                String firstName =etFirstname.getText().toString();
                String lastName =etLastname.getText().toString();
                String username =etUsername.getText().toString();
                String email =etEmail.getText().toString();
                String password =etPassword.getText().toString();
                String vpassword =etVpassword.getText().toString();
                //Toast.makeText(SiginUpActivity.this, "clicked button Action listner", Toast.LENGTH_SHORT).show();
                registerUser();
            }
        });
    }

        private void registerUser(){
        String firstName = etFirstname.getText().toString().trim();
        String lastName = etLastname.getText().toString().trim();
        String userName = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String vpassword = etVpassword.getText().toString().trim();

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

                    Intent mainIntent = new Intent(SiginUpActivity.this,MainPageActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
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
