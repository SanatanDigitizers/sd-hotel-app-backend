package com.sanatandigitizers.plustworoomsadmin.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sanatandigitizers.plustworoomsadmin.R;
import java.util.List;


public class BookingRecyclerAdapter extends RecyclerView.Adapter<BookingRecyclerAdapter.MyViewHolder> {




    //<_-------non UI Variables--------->
    private List<Booking> bookingList;

    Context context;


    public BookingRecyclerAdapter(Context context, List<Booking> bookingList){

        this.context=context;
        this.bookingList=bookingList;

    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView userNameTv,phoneTv,emailTv,bookingIdTv,hotelNameTv,roomIdTv,fromDateTv,uptoDateTv,addressTv,priceTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            emailTv=(TextView)itemView.findViewById(R.id.tv_user_email);
            phoneTv=(TextView)itemView.findViewById(R.id.tv_phone);
            bookingIdTv=(TextView)itemView.findViewById(R.id.tv_booking_id);
            hotelNameTv=(TextView)itemView.findViewById(R.id.tv_hotel_name);
            roomIdTv=(TextView)itemView.findViewById(R.id.tv_room_id);
            fromDateTv=(TextView)itemView.findViewById(R.id.tv_date_from);
            uptoDateTv=(TextView)itemView.findViewById(R.id.tv_date_upto);
            addressTv=(TextView)itemView.findViewById(R.id.tv_address);
            priceTv=(TextView)itemView.findViewById(R.id.tv_price);
            userNameTv=(TextView)itemView.findViewById(R.id.tv_user_name);
    }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_row_booking, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Booking b = bookingList.get(position);
        holder.emailTv.setText(b.getEmail());
        holder.phoneTv.setText(String .valueOf(b.getPhone()));
        holder.bookingIdTv.setText(String.valueOf(b.getId()));
        holder.fromDateTv.setText(String.valueOf(b.getFromTime()));
        holder.uptoDateTv.setText(String.valueOf(b.getUptoTime()));
        holder.hotelNameTv.setText(b.getRoom().getHotel().getName());


    }


    @Override
    public int getItemCount() {
        return bookingList.size();
    }


}
