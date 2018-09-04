package com.sanatandigitizers.plustworoomsadmin.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import com.sanatandigitizers.plustworoomsadmin.model.Room;
import com.squareup.picasso.Picasso;

public class ViewRoomRecyclerAdapter extends RecyclerView.Adapter<ViewRoomRecyclerAdapter.MyViewHolder> {


    private final Context context;
    Hotel h;

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

            Room r = h.getRooms().get(position);
            Picasso.with(context).load(r.getImageLink()).fit().centerCrop().into(holder.imageView);
            holder.roomCategoryTv.setText(r.getCategory().toString());
            holder.roomNameTv.setText(r.getName().toString());



        }

        @Override
        public int getItemCount() {
           return h.getRooms().size();
        }

    }
