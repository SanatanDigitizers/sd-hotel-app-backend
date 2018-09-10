package com.sanatandigitizers.plustworoomsadmin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import com.sanatandigitizers.plustworoomsadmin.model.Room;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

public class ViewRoomRecyclerAdapter extends RecyclerView.Adapter<ViewRoomRecyclerAdapter.MyViewHolder> {


    private final Context context;
    private Hotel h;
    private Room r;
        public ViewRoomRecyclerAdapter(Context context, Hotel h) {

            this.context=context;
            this.h=h;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private final ImageView imageView;
            private final TextView roomNameTv,roomCategoryTv;


            public MyViewHolder(View view) {
                super(view);
                imageView=view.findViewById(R.id.img_view);
                roomNameTv=view.findViewById(R.id.room_name_tv);
                roomCategoryTv=view.findViewById(R.id.room_category_tv);
            }


        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_row_rooms, parent, false);

            return new MyViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

             r = h.getRooms().get(position);

//                Picasso.with(context).load(r.getImageLink()).fit().centerCrop().into(holder.imageView);
                holder.roomCategoryTv.setText(r.getCategory().toString());
                holder.roomNameTv.setText(r.getName().toString());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Room room=h.getRooms().get(position);
                    Intent intent = new Intent(context,AddImageActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("Room", Parcels.wrap(room));
                    intent.putExtras( bundle);
                    context.startActivity(intent);


                }
            });

        }


        @Override
        public int getItemCount() {
           return h.getRooms().size();
        }

    }
