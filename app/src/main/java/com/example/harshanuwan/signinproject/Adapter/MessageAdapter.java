package com.example.harshanuwan.signinproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.harshanuwan.signinproject.Model.Chat;
import com.example.harshanuwan.signinproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final RecyclerView.ViewHolder MSG_TIPE_FEFT = 0;
    public static final RecyclerView.ViewHolder MSG_TIPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mChat;
    private String imageurl;

    FirebaseUser firebaseUser;

    //public UserAdapter(Context mContext, List<User> mUsers){
    //this.mUsers =mUsers;
    //this.mContext =mContext;
    //}

    public MessageAdapter(Context mContext, List<Chat> mChat, String imageurl) {
        this.mChat =mChat;
        this.mContext =mContext;
        this.imageurl = imageurl;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TIPE_RIGHT) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
        return new MessageAdapter.ViewHolder(view);
    } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }







    @Override
    public int getItemViewType(int posi) {

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat chat = mChat.get(position);

        holder.show_message.setText(chat.getMessage());

        if (imageurl.equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(mContext).load(imageurl).into(holder.profile_image);
        }


    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mChat.get(position).getSender().equals(firebaseUser.getUid())){
            return MSG_TIPE_RIGHT;
        } else {
            return MSG_TIPE_FEFT;
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView show_message;
        public CircleImageView profile_image;

        public ViewHolder(View itemView) {
            super(itemView);

            show_message =itemView.findViewById(R.id.show_message);
            profile_image =itemView.findViewById(R.id.all_users_profile_image);
        }
    }

