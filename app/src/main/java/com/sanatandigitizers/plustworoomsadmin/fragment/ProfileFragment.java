package com.sanatandigitizers.plustworoomsadmin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import com.sanatandigitizers.plustworoomsadmin.networkUtil.NetworkService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    // <=======UI variables=======>
    private TextView adminNameTv,adminNumberTv,emailTv,otherNumberTv;
    private ImageView editImage;
    private EditText editNumberEt,editNumberEt2;


    //<-----Non Ui Variables------>
    private List<Hotel> hotel;
    private String name,email,phone1,phone2;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

             View view=   inflater.inflate(R.layout.fragment_profile, container, false);
             adminNameTv=(TextView)view.findViewById(R.id.tv_admin_name);
             adminNumberTv=(TextView)view.findViewById(R.id.tv_admin_phone_number);
             otherNumberTv=(TextView)view.findViewById(R.id.tv_admin_phone_number2);
             emailTv=(TextView)view.findViewById(R.id.tv_email);
             editImage=(ImageView) view.findViewById(R.id.img_edit);
             loadProfile();


             editImage.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                     View view= LayoutInflater.from(v.getContext()).inflate(R.layout.edit_profile,null);
                     editNumberEt=(EditText)view.findViewById(R.id.et_edit_number);
                     editNumberEt2=(EditText)view.findViewById(R.id.et_edit_number2);
                     editNumberEt.setText(adminNumberTv.getText().toString());
                     editNumberEt2.setText(otherNumberTv.getText().toString());
                     builder.setView(view);
                     builder.show();
                 }
             });
             return view;
    }
    private void loadProfile() {
        NetworkService.getInstance()
                .getJSONApi()
                .getAllHotel()
                .enqueue(new Callback<List<Hotel>>() {
                    @Override
                    public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                        hotel = response.body();
                        name=hotel.get(0).getContactPerson();
                        email=hotel.get(0).getEmail();
                        phone1=String .valueOf(hotel.get(0).getPhone1());
                        phone2=String.valueOf(hotel.get(0).getPhone2());

                        adminNameTv.setText(name);
                        emailTv.setText(email);
                        adminNumberTv.setText(phone1);
                        otherNumberTv.setText(phone2);

                    }

                    @Override
                    public void onFailure(Call<List<Hotel>> call, Throwable t) {

                    }
                });
    }
}
