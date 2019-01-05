package com.example.harshanuwan.signinproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.harshanuwan.signinproject.MessageActivity;
import com.example.harshanuwan.signinproject.R;
import com.example.harshanuwan.signinproject.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUsers;


    public UserAdapter(Context mContext, List<User> mUsers) {
        this.mUsers =mUsers;
        this.mContext =mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.all_users_profile, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //send values to database

        final User user = mUsers.get(position);
        holder.username.setText(user.getUser_name());

        //if (user.getImageURL().equals("default")){
            //holder.all_users_profile_image.setImageResource(R.drawable.person);
        //} else {
            //Glide.with(mContext).load(user.getImageURL()).into(holder.all_users_profile_image);
        //}

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent =new Intent(mContext, MessageActivity.class);
                intent.putExtra("user_id", user.getUser_id());
                intent.putExtra("user_name", user.getUser_name());
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {

        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username;
        public CircleImageView profile_image;

        public ViewHolder(View itemView) {
            super(itemView);

            username =itemView.findViewById(R.id.allUsersUsername);
            profile_image =itemView.findViewById(R.id.all_users_profile_image);
        }
    }


}
