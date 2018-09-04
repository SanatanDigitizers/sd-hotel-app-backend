package com.sanatandigitizers.plustworoomsadmin.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.model.AppSession;
import com.sanatandigitizers.plustworoomsadmin.model.Booking;
import com.sanatandigitizers.plustworoomsadmin.model.BookingRecyclerAdapter;
import com.sanatandigitizers.plustworoomsadmin.networkUtil.NetworkService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingFragment extends Fragment {

    //<------Ui Variables----->

    RecyclerView recyclerView;


    //<-----non UI Variables------>
    private List<Booking> bookingList;
    private BookingRecyclerAdapter adapter;
    private boolean available;

    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_booking, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);



        loadBookings();
        return view;
    }



    private void loadBookings() {
        NetworkService.getInstance()
                .getJSONApi()
                .getAllBookings()
                .enqueue(new Callback<List<Booking>>() {
                    @Override
                    public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                        bookingList = response.body();
                        if(!AppSession.isAppAdmin){
                            List<Booking> bookings = new ArrayList<>();
                            for(Booking b : bookingList){
                                if(b.getRoom().getHotel().getId()== AppSession.hotel.getId()){
                                    bookings.add(b);
                                }
                            }
                            bookingList = bookings;
                        }
                        if (bookingList != null) {
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            adapter = new BookingRecyclerAdapter(getActivity(), bookingList);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);

                        }
                        else{
                            Toast.makeText(getActivity(),"No data found!!!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Booking>> call, Throwable t) {

                    }
                });
    }
}
