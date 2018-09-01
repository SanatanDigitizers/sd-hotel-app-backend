package com.sanatandigitizers.plustworoomsadmin.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanatandigitizers.plustworoomsadmin.R;

import java.util.List;

public class UserListRecyclerAdapter extends RecyclerView.Adapter<UserListRecyclerAdapter.MyViewHolder>{
    Context context;
    private List<User>userList;
    public UserListRecyclerAdapter(Context cntext,List<User>userList){

        this.context=cntext;
        this.userList=userList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{




        public MyViewHolder(View itemView){

            super(itemView);

        }
    }
    @NonNull
    @Override
    public UserListRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_row_user, parent, false);
        return new UserListRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserListRecyclerAdapter.MyViewHolder holder, int position) {

        final User user=userList.get(position);
        //  Picasso.with(context).load(hotel.getImageLink()).into(holder.hotelImageIv);
//        holder.hotelNameTv.setText(hotel.getName());
//        holder.hotelPhoneTv.setText(String.valueOf(hotel.getPhone1()));
//        holder.hotelAddressTv.setText(hotel.getAddress().toString());
        //   holder.numberOfRoomsTv.setText(hotel.getRooms().size());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
