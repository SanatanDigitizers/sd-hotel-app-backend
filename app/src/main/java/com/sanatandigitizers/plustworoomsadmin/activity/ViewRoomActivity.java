package com.sanatandigitizers.plustworoomsadmin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import com.sanatandigitizers.plustworoomsadmin.networkUtil.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRoomActivity extends AppCompatActivity  {
    //<----UI  variables----->
    RecyclerView recyclerView;
    ViewRoomRecyclerAdapter adapter;

    private List<Hotel> hotel;
    int []image={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_room);

        recyclerView=(RecyclerView)findViewById(R.id.recycle_view);

        loadHotels();
    }


    private void loadHotels() {

        NetworkService.getInstance()
                .getJSONApi()
                .getAllHotel()
                .enqueue(new Callback<List<Hotel>>() {
                    @Override
                    public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                        hotel = response.body();
//                        ArrayAdapter<Hotel> adapter = new ArrayAdapter<Hotel>(AddRoomActivity.this, android.R.layout.simple_spinner_item, hotel);
//                        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
//                        hotelSpinner.setAdapter(adapter);
                      //  Log.d("Hotel",hotel.get(1).getName());

                        adapter = new ViewRoomRecyclerAdapter(ViewRoomActivity.this,image,hotel);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ViewRoomActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(Call<List<Hotel>> call, Throwable t) {

                    }
                });
    }

}
