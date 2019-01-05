package com.example.harshanuwan.signinproject;

import  android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class TabspagerAdapter extends FragmentPagerAdapter{
    public TabspagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;

            case 1:
                ContactsFragment contactsFragment = new ContactsFragment();
                return contactsFragment;

            default:
                return null;


        }
    }

    @Override
    public int getCount() {

        return 2;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Chat";

            case 1:
                return "Contacts";


            default:
                return null;
        }
    }

}
