package com.sanatandigitizers.plustworoomsadmin.model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.activity.ViewRoomActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

public class HotelRecyclerAdapter extends RecyclerView.Adapter <HotelRecyclerAdapter.MyViewHolder>{

    Context context;
    private List<Hotel>hotelList;
  public HotelRecyclerAdapter(Context cntext,List<Hotel>hotelList){

      this.context=cntext;
      this.hotelList=hotelList;
  }


    public class MyViewHolder extends RecyclerView.ViewHolder{

     public final TextView hotelNameTv,hotelPhoneTv,hotelAddressTv,numberOfRoomsTv;
     public final ImageView hotelImageIv;

      public MyViewHolder(View itemView){

          super(itemView);
          numberOfRoomsTv=(TextView)itemView.findViewById(R.id.number_of_rooms_tv);
          hotelNameTv=(TextView)itemView.findViewById(R.id.hotel_name_tv);
          hotelPhoneTv=(TextView)itemView.findViewById(R.id.hotel_phone_tv);
          hotelAddressTv=(TextView)itemView.findViewById(R.id.hoteladdress_tv);
          hotelImageIv=(ImageView)itemView.findViewById(R.id.hotel_image);

      }
  }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_row_hotel, parent, false);
        return new HotelRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final Hotel hotel = hotelList.get(position);
        Picasso.with(context).load(hotel.getImageLink()).fit().centerCrop().into(holder.hotelImageIv);
        holder.hotelNameTv.setText(hotel.getName());
        holder.hotelPhoneTv.setText(String.valueOf(hotel.getPhone1()));
        holder.hotelAddressTv.setText(hotel.getAddress().toString());
        holder.numberOfRoomsTv.setText(String.valueOf(hotel.getRooms().size()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hotel hotels=hotelList.get(position);
                Intent intent=new Intent(context, ViewRoomActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("hotel", Parcels.wrap(hotels));
                intent.putExtras( bundle);
                context.startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return hotelList.size();
    }


}
