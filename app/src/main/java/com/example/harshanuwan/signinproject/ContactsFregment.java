package com.example.harshanuwan.signinproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFregment extends Fragment {
    private View ContactsView;
    private RecyclerView contactsList;


    public ContactsFregment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ContactsView = inflater.inflate(R.layout.fregment_contacts, container, false);

        contactsList = (RecyclerView) ContactsView.findViewById(R.id.contact_list);
        contactsList.setLayoutManager(new LinearLayoutManager(getContext()));
        return ContactsView;
    }


}
