package com.sanatandigitizers.plustworoomsadmin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.activity.AddRoomActivity;
import com.sanatandigitizers.plustworoomsadmin.activity.Registration;
import com.sanatandigitizers.plustworoomsadmin.activity.ViewRoomActivity;
import com.sanatandigitizers.plustworoomsadmin.model.AppSession;
import com.sanatandigitizers.plustworoomsadmin.model.Booking;
import com.sanatandigitizers.plustworoomsadmin.model.BookingRecyclerAdapter;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import com.sanatandigitizers.plustworoomsadmin.model.HotelRecyclerAdapter;
import com.sanatandigitizers.plustworoomsadmin.networkUtil.NetworkService;
import com.sanatandigitizers.plustworoomsadmin.util.NetworkConnection;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelFragment extends Fragment {


    //<---------UI variables---------->
    private RecyclerView recyclerView;
    private FloatingActionButton addHotelFAB;
    private ProgressBar progressBar;

    //<----Non UI variables------>
    private List<Hotel>hotelList;
    private HotelRecyclerAdapter adapter;

    public HotelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hotel, container, false);

        //--------->UI Initialization--------->
        recyclerView=(RecyclerView)v.findViewById(R.id.recycler_hotel_holder);
        addHotelFAB=(FloatingActionButton)v.findViewById(R.id.add_hotel_fbtn);
        progressBar=(ProgressBar)v.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        addHotelFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Registration.class);
                startActivity(intent);
            }
        });
        loadHotels();
        return v;
    }
    private void loadHotels() {
//        if(!NetworkConnection.isConnected(getContext())){
//            Toast.makeText(getActivity(),"Network Not Connected",Toast.LENGTH_SHORT).show();
//            return;
//        }
        progressBar.setVisibility(View.GONE);
        if(AppSession.isAppAdmin){
            NetworkService.getInstance()
                    .getJSONApi()
                    .getAllHotel()
                    .enqueue(new Callback<List<Hotel>>() {
                        @Override
                        public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                            hotelList = response.body();
                            if(hotelList != null) {
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                recyclerView.setLayoutManager(layoutManager);
                                adapter = new HotelRecyclerAdapter(getActivity(),hotelList);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(adapter);
                            }
                            else{
                                Toast.makeText(getActivity(),"No data found!!!!",Toast.LENGTH_SHORT).show();
                            }

                        }
                        @Override
                        public void onFailure(Call<List<Hotel>> call, Throwable t) {

                        }
                    });
        }else{
            hotelList = new ArrayList<>();
            hotelList.add(AppSession.hotel);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new HotelRecyclerAdapter(getActivity(),hotelList);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
    }

}
