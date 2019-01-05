package com.example.harshanuwan.signinproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar2;
    private FirebaseAuth mAuth;
    private EditText etUsername;
    private EditText etPassword;
    private Button bLogin;
    private EditText vtRegisterLink;
    private ProgressDialog loadingBar;
    private Toolbar mtoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.tvRegisterLink).setOnClickListener(this);

        mtoolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Log In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView RegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        loadingBar = new ProgressDialog(this);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = etUsername.getText().toString();
                String Password = etPassword.getText().toString();

                LoginUserAccount(Username,Password);

            }
        });

        RegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, SiginUpActivity.class);
                startActivity(registerIntent);
            }
        });

    }

    private void LoginUserAccount(String email, String password) {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(LoginActivity.this,"Please enter your email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(LoginActivity.this,"Please enter your password", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait until Login...");
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Intent mainpage = new Intent(LoginActivity.this,MainPageActivity.class);
                                mainpage.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainpage);
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this,
                                        "Again write your correct email and password",Toast.LENGTH_SHORT).show();
                            }

                            loadingBar.dismiss();

                        }
                    });

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bLogin:

//                startActivity(new Intent(this, SiginUpActivity.class));

                break;
        }
    }
}
