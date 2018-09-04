package com.example.harshanuwan.signinproject;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_up);
        mAuth = FirebaseAuth.getInstance();//Connect firebasee

        final EditText etFirstname = (EditText) findViewById(R.id.etFirstname); // initialize variables in signup page
        final EditText etLastname = (EditText) findViewById(R.id.etLastname);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etVpassword = (EditText) findViewById(R.id.etVpassword);
        final Button bSignup = (Button) findViewById(R.id.bSignup);

        bSignup.setOnClickListener(new View.OnClickListener() { // when click signup botton
            @Override
            public void onClick(View v) {
                Toast.makeText(SiginUpActivity.this, "clicked button Action listner", Toast.LENGTH_SHORT).show();
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
        if(password!=vpassword){
            Toast.makeText(SiginUpActivity.this,"not Equal passowrd",Toast.LENGTH_SHORT).show();
        }

        if(password.length()<6){
            Toast.makeText(SiginUpActivity.this,"Password greater than 6 characters",Toast.LENGTH_SHORT).show();
            etPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Registered successfull", Toast.LENGTH_SHORT).show();
//                    Intent loginIntent = new Intent(SiginUpActivity.this,LoginActivity.class);
//                    startActivity(loginIntent);
                }

            }
        });
        }
    @Override
    public void onClick(View view) {

    }



}
