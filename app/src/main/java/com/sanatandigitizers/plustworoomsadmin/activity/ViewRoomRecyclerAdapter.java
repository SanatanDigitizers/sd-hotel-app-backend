package com.sanatandigitizers.plustworoomsadmin.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;

import org.w3c.dom.Text;

import java.util.List;

public class ViewRoomRecyclerAdapter extends RecyclerView.Adapter<ViewRoomRecyclerAdapter.MyViewHolder> {
//        private final String[] name;
//        private final String[] bill;
//        private final String[] date;
//        private final String[] total;
        private final int[]image;

        private List<Hotel>hotel;
        private Context context;


        public ViewRoomRecyclerAdapter(Context context, int[] image, List<Hotel> hotel) {

            this.context=context;
//            this.name=name;
//            this.bill=bill;
           this.image=image;
           this.hotel=hotel;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
           // public TextView cname, billnumber,billDate,total,due;
            public ImageView imageView;
            public TextView tvName;


            public MyViewHolder(View view) {
                super(view);
                imageView=view.findViewById(R.id.img_view);
                tvName=view.findViewById(R.id.tv_hotel_name);
//                cname = view.findViewById(R.id.tvname);
//                billnumber = view.findViewById(R.id.billnumber);
//                billDate = view.findViewById(R.id.billdate);
//                total=view.findViewById(R.id.total_amount);
//                due=view.findViewById(R.id.due_amount);
            }


        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_row, parent, false);

            return new MyViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
           // holder.imageView.setImageResource(image[position]);
           // holder.tvName.setText(hotel.get(position).getName());
           //Log.d("Hotel",""+hotel.get(0).getName());

            holder.tvName.setText("name:"+hotel.get(position).getName());

        }

        @Override
        public int getItemCount() {
          //  return hotel.size();

           return image.length;
        }

    }
