package com.example.harshanuwan.signinproject.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mAllmessages;

    FirebaseUser firebaseUser;

    public MessageAdapter(Context context, List<Chat> chat){
        mContext = context;
        mAllmessages = chat;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right , parent , false);
            return new MessageAdapter.MessageViewHolder(v);
        }else{
            View v = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left , parent , false);
            return new MessageAdapter.MessageViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        Chat message = mAllmessages.get(position);



        holder.show_message.setText(message.getMessage());
    }

    @Override
    public int getItemViewType(int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mAllmessages.get(position).getSender().equals(firebaseUser.getUid())) {
            return MSG_TYPE_RIGHT;
        }else{
            return MSG_TYPE_LEFT;
        }

    }

    @Override
    public int getItemCount() {
        return mAllmessages.size();
    }

    //public UserAdapter(Context mContext, List<User> mUsers){
    //this.mUsers =mUsers;
    //this.mContext =mContext;
    //}


    public class MessageViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        @SuppressLint("ResourceType")
        public MessageViewHolder(View itemView){
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);

        }
    }


}


