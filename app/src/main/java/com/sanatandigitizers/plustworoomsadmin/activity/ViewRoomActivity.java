package com.sanatandigitizers.plustworoomsadmin.activity;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.model.AppSession;
import com.sanatandigitizers.plustworoomsadmin.model.BookingRecyclerAdapter;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import com.sanatandigitizers.plustworoomsadmin.networkUtil.NetworkService;

import org.parceler.Parcels;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRoomActivity extends AppCompatActivity  {
    //<----UI  variables----->
    private RecyclerView recyclerView;
    private ViewRoomRecyclerAdapter adapter;

    Hotel hotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_room);

        recyclerView=(RecyclerView)findViewById(R.id.recycle_view);

        //Bundle bundle=getIntent().getExtras();
         hotel= Parcels.unwrap(getIntent().getParcelableExtra("hotel"));
         Log.d("Response",hotel.getName());


        loadRooms();
    }


    private void loadRooms(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewRoomActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ViewRoomRecyclerAdapter(ViewRoomActivity.this, hotel);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}

