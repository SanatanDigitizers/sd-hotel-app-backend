package com.sanatandigitizers.plustworoomsadmin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.model.AppSession;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import com.sanatandigitizers.plustworoomsadmin.model.User;
import com.sanatandigitizers.plustworoomsadmin.model.UserRecyclerAdapter;
import com.sanatandigitizers.plustworoomsadmin.networkUtil.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {


    private RecyclerView recyclerView;
    private UserRecyclerAdapter userRecyclerAdapter;
    private List<User> userList;
    private UserRecyclerAdapter adapter;
    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.recycler_user_view);

       // loadHotels();


        loadUsers();
        return v;

    }
   // private void loadHotels()


    private void loadUsers() {
//        if(!NetworkConnection.isConnected(getContext())){
//            Toast.makeText(getActivity(),"Network Not Connected",Toast.LENGTH_SHORT).show();
//            return;
//        }
        // progressBar.setVisibility(View.GONE);
        if(!AppSession.isAppAdmin) {
        NetworkService.getInstance()
                .getJSONApi()
                    .getAllUsers()
                    .enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            userList = response.body();

                            if (userList != null) {
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                recyclerView.setLayoutManager(layoutManager);
                                adapter = new UserRecyclerAdapter(getActivity(), userList);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(adapter);
                            } else {
                                Toast.makeText(getActivity(), "No data found!!!!", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {

                        }
                    });
        }else{
            return;
        }
    }
}





