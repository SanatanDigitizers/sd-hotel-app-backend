package com.sanatandigitizers.plustworoomsadmin.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import org.parceler.Parcels;

public class ViewRoomActivity extends AppCompatActivity  {
    //<----UI  variables----->
    private RecyclerView recyclerView;
    private ViewRoomRecyclerAdapter adapter;
    FloatingActionButton addRommFBtn;

    private  Hotel hotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_room);

        recyclerView=(RecyclerView)findViewById(R.id.recycle_view);
        addRommFBtn = (FloatingActionButton)findViewById(R.id.add_hotel_rooms_fbtn);

         hotel= Parcels.unwrap(getIntent().getParcelableExtra("hotel"));

         addRommFBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(ViewRoomActivity.this,AddRoomActivity.class);
                 startActivity(intent);

             }
         });


        loadRooms();
    }


    private void loadRooms() {

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewRoomActivity.this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new ViewRoomRecyclerAdapter(ViewRoomActivity.this, hotel);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

    }
}

