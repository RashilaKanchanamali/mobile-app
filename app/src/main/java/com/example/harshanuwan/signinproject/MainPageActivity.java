package com.example.harshanuwan.signinproject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.harshanuwan.signinproject.R.menu.main_menu;

public class MainPageActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mToolbar;
    private FirebaseAuth mAuth;

    private ViewPager mainViewPager;
    private TabLayout mainTabLayout;
    private TabspagerAdapter mainTabsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mAuth = FirebaseAuth.getInstance();

        //MainPageActivity tabs
        mToolbar =(android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("mobi Chat");

       //mainViewPager = findViewById(R.id.main_tab_pager);
        mainTabsPagerAdapter = new TabspagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mainTabsPagerAdapter);
       //mainTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mainTabLayout.setupWithViewPager(mainViewPager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null)
        {
             SendUserToLoginActivity();
        }
    }

    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(MainPageActivity.this,LoginActivity.class);
        startActivity(loginIntent);
    }

    private void LogOutUser() {

        Intent startPageIntent = new Intent(MainPageActivity.this,MainActivity.class);
        startPageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity(startPageIntent);
        finish();
    }

    private void sendUserToLoginActivity() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.settings_button:{
                break;
            }


            case R.id.logout_button:{
                break;
            }


        }
        return false;
    }
}
