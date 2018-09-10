package com.sanatandigitizers.plustworoomsadmin.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.codec.RoomCategory;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import com.sanatandigitizers.plustworoomsadmin.model.Room;
import com.sanatandigitizers.plustworoomsadmin.model.RoomImage;
import com.sanatandigitizers.plustworoomsadmin.networkUtil.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddRoomActivity extends AppCompatActivity {
    ////UI variables
    private CheckBox wifiCb,tvcb,acCb,cleantoiletCb,servicecb,familyCb,coupleCb;
    private EditText discEt;
    private Switch discountSwitch;
    private Button saveBtn;
    private EditText priceEt,discountEt,nameEt,noOfPersonEt;
    private Spinner hotelSpinner, catSpinner;


    ////Non-ui variables
    private List<Hotel> hotel;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        ///UI initialization started
        discountEt=(EditText)findViewById(R.id.et_discount);
        nameEt=(EditText)findViewById(R.id.et_name);
        priceEt=(EditText)findViewById(R.id.et_price);
        noOfPersonEt=(EditText)findViewById(R.id.et_no_person);

        discountSwitch=(Switch)findViewById(R.id.switch_disc);
        saveBtn=(Button)findViewById(R.id.btn_save);

        wifiCb=(CheckBox)findViewById(R.id.cb_wifi);
        tvcb=(CheckBox)findViewById(R.id.cb_tv);
        acCb=(CheckBox)findViewById(R.id.cb_ac);
        servicecb=(CheckBox)findViewById(R.id.cb_service);
        cleantoiletCb=(CheckBox)findViewById(R.id.cb_clean_toilet);

        familyCb=(CheckBox)findViewById(R.id.cb_family);
       // singleCb=(CheckBox)findViewById(R.id.cb_single);
        coupleCb=(CheckBox)findViewById(R.id.cb_couple);
        hotelSpinner=(Spinner)findViewById(R.id.spinner_hotel);
        catSpinner = (Spinner)findViewById(R.id.spinner_category);
        ///UI initialization end

        ArrayAdapter<RoomCategory> adapter = new ArrayAdapter<RoomCategory>(AddRoomActivity.this, android.R.layout.simple_spinner_item, RoomCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        catSpinner.setAdapter(adapter);



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveAction();
               // clear();
             }
        });


        loadHotels();


    }

    private boolean validateFields(){
        if(hotelSpinner.getSelectedItem() == null){
            Toast.makeText(AddRoomActivity.this,"Please select hotel",Toast.LENGTH_SHORT).show();
            hotelSpinner.requestFocus();
            return false;
        }
        if(catSpinner.getSelectedItem() == null){
            Toast.makeText(AddRoomActivity.this,"Please select category",Toast.LENGTH_SHORT).show();
            catSpinner.requestFocus();
            return false;
        }
        String price = priceEt.getText().toString();
        if(price == null || price.isEmpty()){
            Toast.makeText(AddRoomActivity.this,"Please enter price",Toast.LENGTH_SHORT).show();
            priceEt.requestFocus();
            return false;
        }
        String name=nameEt.getText().toString();
        if(name == null || name.isEmpty()){
            Toast.makeText(AddRoomActivity.this,"Please enter price",Toast.LENGTH_SHORT).show();
            nameEt.requestFocus();
            return false;
        }

        String noOfPerson=discountEt.getText().toString();
        if(noOfPerson == null || name.isEmpty()){
            Toast.makeText(AddRoomActivity.this,"Please enter price",Toast.LENGTH_SHORT).show();
            noOfPersonEt.requestFocus();
            return false;
        }
        return true;
    }

    private Room getValueFromFields(){
       if(validateFields()){
           Room room=new Room();
           Hotel hotel=new Hotel();
           hotel.setId(((Hotel)hotelSpinner.getSelectedItem()).getId());
           id=hotel.getId();
           Toast.makeText(AddRoomActivity.this, ""+hotel.getId(), Toast.LENGTH_SHORT).show();
           room.setHotel(hotel);
           room.setActive(true);
           room.setName(nameEt.getText().toString());
           room.setNoOfPersons(Integer.parseInt(noOfPersonEt.getText().toString()));
           room.setAc(acCb.isChecked());
           room.setWifi(wifiCb.isChecked());
           room.setCleanToilet(cleantoiletCb.isChecked());
           room.setPrice(Double.parseDouble(priceEt.getText().toString()));
           room.setCoupleAllowed(coupleCb.isChecked());
           room.setFamilyAllowed(familyCb.isChecked());
           room.setService24_7(servicecb.isChecked());
           room.setDiscountValue(Double.parseDouble(discountEt.getText().toString()));
           room.setDicountInPercentage(discountSwitch.isChecked());
           room.setCategory((RoomCategory) catSpinner.getSelectedItem());
           RoomImage roomImage =new RoomImage();
           roomImage.setId(15);
           roomImage.setUrl("");
           roomImage.setRoomId(room);
           return room;
       }
       return null;
    }

    private void saveAction(){

        Room room = getValueFromFields();
        if(null != room){
            NetworkService.getInstance()
                    .getJSONApi()
                    .postHotelData(room,id)
                    .enqueue(new Callback<Room>() {
                        @Override
                        public void onResponse(Call<Room> call, Response<Room> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(AddRoomActivity.this, "successfull!!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(AddRoomActivity.this, "Failed!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Room> call, Throwable t) {
                            Log.d("error",t.getMessage());
                        }
                    });
        }
    }

    private void loadHotels() {
        NetworkService.getInstance()
                .getJSONApi()
                .getAllHotel()
                .enqueue(new Callback<List<Hotel>>() {
                    @Override
                    public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                        hotel = response.body();
                        ArrayAdapter<Hotel> adapter = new ArrayAdapter<Hotel>(AddRoomActivity.this, android.R.layout.simple_spinner_item, hotel);
                        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        hotelSpinner.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Hotel>> call, Throwable t) {

                    }
                });
    }

    private void clear(){
        nameEt.setText("");
        discountEt.setText("");
        priceEt.setText("");
        noOfPersonEt.setText("");
        wifiCb.setChecked(false);
        tvcb.setChecked(false);
        cleantoiletCb.setChecked(false);
        acCb.setChecked(false);
        familyCb.setChecked(false);
        coupleCb.setChecked(false);
        servicecb.setChecked(false);
    }

}

