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

import java.util.Objects;

import static com.example.harshanuwan.signinproject.R.menu.main_menu;

public class MainPageActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private ViewPager mFragmentpager;//fragments working area
    private TabLayout mFragmentstab;//include only fragments names
    private TabspagerAdapter mTabsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mAuth = FirebaseAuth.getInstance();

        //add fragments
        mFragmentpager = (ViewPager) findViewById(R.id.fragments_pager);
        mTabsPagerAdapter = new TabspagerAdapter(getSupportFragmentManager());
        mFragmentpager.setAdapter(mTabsPagerAdapter);
        mFragmentstab = (TabLayout) findViewById(R.id.fragments_tab);
        mFragmentstab.setupWithViewPager(mFragmentpager);

        //add toolbar with intent name and back arrow
        android.support.v7.widget.Toolbar mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("mobi Chat");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null)
        {
             LogOutUser();
        }
    }

    private void LogOutUser() {

        Intent startPageIntent = new Intent(MainPageActivity.this,MainActivity.class);
        startPageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity(startPageIntent);
        finish();
    }


    @Override //create main menu three dots
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.logout_button){ //work on logout button
            mAuth.signOut();
            LogOutUser();
        }

        if (item.getItemId() ==(R.id.settings_button)){ //work on settings activity
            Intent intentSettings = new Intent(MainPageActivity.this, SettingsActivity.class);
            startActivity(intentSettings);
        }

        return true;
    }
}
