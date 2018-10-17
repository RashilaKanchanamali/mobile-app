package com.example.harshanuwan.signinproject;

import android.support.v4.app.Fragment;
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
                RequestsFragment requestsFragment = new RequestsFragment();
                return requestsFragment;
            case 1:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;
            case 2:
                ContactsFregment contactsFregment = new ContactsFregment();
                return contactsFregment;
            default:
                return null;


        }
    }

    @Override
    public int getCount() {

        return 3;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Chat";

            case 1:
                return "Contacts";

            case 2:
                return "Requests";

            default:
                return null;
        }
    }

}
