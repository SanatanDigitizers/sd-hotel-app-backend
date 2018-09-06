package com.sanatandigitizers.plustworoomsadmin.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
    private int READ_STORAGE_PERMISSION_CODE = 22;

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
                if(isWriteStorageAllowed()) {
                    Intent intent = new Intent(getActivity(), Registration.class);
                    startActivity(intent);
                }
                else{
                    requestWriteStoragePermission();
                }
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
    private boolean isWriteStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestWriteStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},READ_STORAGE_PERMISSION_CODE);
    }
    //This method will be called when the user will tap on allow or deny

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        if(requestCode == READ_STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Intent intent = new Intent(getActivity(), Registration.class);
                startActivity(intent);
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(getActivity(),"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }

}
