package com.mingle.chatappcs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.mingle.chatappcs.ChatScreenActivity;
import com.mingle.chatappcs.R;
import com.mingle.chatappcs.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdepter extends RecyclerView.Adapter<UserAdepter.viewHolder> {

    ArrayList<Users> list;
    Context context;

    public UserAdepter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_show_users,parent,false);

        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    Users users = list.get(position);
        Picasso.get().load(users.getProfilePic()).placeholder(R.drawable.user).into(holder.image);
        holder.uname.setText(users.getUserName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,ChatScreenActivity.class);
                i.putExtra("userId",users.getUserId());
                i.putExtra("profilePic",users.getProfilePic());
                i.putExtra("userName",users.getUserName());
                context.startActivity(i);


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends ViewHolder{

        ImageView image;
        TextView uname,lastmess;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.profile_image);
            uname = itemView.findViewById(R.id.tvUserName);
            lastmess = itemView.findViewById(R.id.tvLastMess);

        }
    }
}
