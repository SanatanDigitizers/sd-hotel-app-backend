package com.sanatandigitizers.plustworoomsadmin.activity;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.model.Address;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import com.sanatandigitizers.plustworoomsadmin.model.HotelImage;
import com.sanatandigitizers.plustworoomsadmin.model.RoomImage;
import com.sanatandigitizers.plustworoomsadmin.networkUtil.NetworkService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Registration extends AppCompatActivity {

    //UI variables
    EditText cityEt,imagePathEt, countryCodeEt, hotelNameEt, contactNameEt, streetEt, pincodeEt, longitudeEt, emailEt, phoneEt, passwordEt,latitudeEt, phone2Et, descriptionEt;
    Spinner stateSpinner, countrySpinner;
    Button locationBtn, registerBtn,imagePathBtn;
    FloatingActionButton locationFbtn;
    ImageView imageViewIv;

    //NonUI variables
    public static int temp=0;
    String state,country;
    int item;
    List<RoomImage>image;
    private static final int SELECT_PHOTO = 100;
    Uri selectedImage;
    private FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //UI initialization
        stateSpinner =(Spinner)findViewById(R.id.spinner_state);
        countrySpinner =(Spinner)findViewById(R.id.spinner_country);
        final ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.State,android.R.layout.simple_list_item_1);
        stateSpinner.setAdapter(adapter);
        ArrayAdapter adapter2= ArrayAdapter.createFromResource(this,R.array.Country,android.R.layout.simple_list_item_1);
        countrySpinner.setAdapter(adapter2);
        registerBtn =(Button)findViewById(R.id.btn_register);
        imagePathEt=(EditText)findViewById(R.id.imagepath_et);
       // locationBtn =(Button)findViewById(R.id.location);
        imageViewIv=(ImageView)findViewById(R.id.image_iv);
        imagePathBtn=(Button)findViewById(R.id.select_image_path_btn);
        cityEt =(EditText)findViewById(R.id.et_contact_city);
        countryCodeEt =(EditText)findViewById(R.id.et_countrycode);
        hotelNameEt =(EditText)findViewById(R.id.et_hotel_name);
        contactNameEt =(EditText)findViewById(R.id.et_contact_name);
        streetEt =(EditText)findViewById(R.id.et_street);
        pincodeEt =(EditText)findViewById(R.id.et_pin);
        longitudeEt =(EditText)findViewById(R.id.longitude_et);
        emailEt =(EditText)findViewById(R.id.et_contact_email);
        phoneEt =(EditText)findViewById(R.id.et_contact_phone);
        passwordEt =(EditText)findViewById(R.id.et_password);
        phone2Et =(EditText)findViewById(R.id.et_contact_phone2);
        descriptionEt =(EditText)findViewById(R.id.et_desc);
        latitudeEt=(EditText)findViewById(R.id.latitude_et);
        locationFbtn=(FloatingActionButton)findViewById(R.id.fbtn_getlocation) ;
        // <--- End of UI Initialization  -->

        if(temp!=0){
            latitudeEt.setText(getIntent().getExtras().getString("Latitude"));
            longitudeEt.setText(getIntent().getExtras().getString("Longitude"));
        }

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAction();
                clear();
            }
        });

        locationFbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        imagePathBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 selectImage(v);
            }
        });

    }
    public void selectImage(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(Registration.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage = imageReturnedIntent.getData();
                    File imagePath = new File(getRealPathFromURI(selectedImage));
                try {
                    fis = new FileInputStream(imagePath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                    Toast.makeText(Registration.this,""+fis,Toast.LENGTH_SHORT).show();

                Bitmap bm = BitmapFactory.decodeStream(fis);

               // imageViewIv.setImageBitmap(bm);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100 , baos);
                byte[] b = baos.toByteArray();
               String encImage = Base64.encodeToString(b, Base64.DEFAULT);
               Toast.makeText(Registration.this,""+encImage,Toast.LENGTH_SHORT).show();
                }
        }
    }
    private boolean validateFields(){
        if(hotelNameEt.getText().toString().isEmpty() || hotelNameEt.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter name",Toast.LENGTH_SHORT).show();
            hotelNameEt.requestFocus();
            return false;
        }
        if(contactNameEt.getText().toString().isEmpty() || contactNameEt.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter contact name",Toast.LENGTH_SHORT).show();
            contactNameEt.requestFocus();
            return false;
        }
        if(countryCodeEt.getText().toString().isEmpty() || countryCodeEt.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter country code",Toast.LENGTH_SHORT).show();
            countryCodeEt.requestFocus();
            return false;
        }
        if(phoneEt.getText().toString().isEmpty() || phoneEt.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter mobile number",Toast.LENGTH_SHORT).show();
            phoneEt.requestFocus();
            return false;
        }
        if(phoneEt.getText().toString().length()>10){
            Toast.makeText(Registration.this,"please enter valid number",Toast.LENGTH_SHORT).show();
            phoneEt.requestFocus();
            return false;
        }
        if(phone2Et.getText().toString().isEmpty() || phone2Et.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter mobile number",Toast.LENGTH_SHORT).show();
            phone2Et.requestFocus();
            return false;
        }
        if(phone2Et.getText().toString().length()>10){
            Toast.makeText(Registration.this,"please enter valid number",Toast.LENGTH_SHORT).show();
            phone2Et.requestFocus();
            return false;
        }
        if(emailEt.getText().toString().isEmpty() || emailEt.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter email",Toast.LENGTH_SHORT).show();
            emailEt.requestFocus();
            return false;
        }
        if(descriptionEt.getText().toString().isEmpty() || descriptionEt.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter description",Toast.LENGTH_SHORT).show();
            descriptionEt.requestFocus();
            return false;
        }
        if(cityEt.getText().toString().isEmpty() || cityEt.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter city",Toast.LENGTH_SHORT).show();
            cityEt.requestFocus();
            return false;
        }
        if(streetEt.getText().toString().isEmpty() || streetEt.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter Street",Toast.LENGTH_SHORT).show();
            streetEt.requestFocus();
            return false;
        }
        if(pincodeEt.getText().toString().isEmpty() || pincodeEt.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter pincode",Toast.LENGTH_SHORT).show();
            pincodeEt.requestFocus();
            return false;
        }
        if(stateSpinner.getSelectedItem()==null){
            Toast.makeText(Registration.this,"please select state",Toast.LENGTH_SHORT).show();
            stateSpinner.requestFocus();
        }
        if(countrySpinner.getSelectedItem()==null){
            Toast.makeText(Registration.this,"please select country",Toast.LENGTH_SHORT).show();
            countrySpinner.requestFocus();
        }
        if(passwordEt.getText().toString().isEmpty() || passwordEt.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter password",Toast.LENGTH_SHORT).show();
            passwordEt.requestFocus();
            return false;
        }
//        String confPass=confPasswordEt.getText().toString();
//        if(confPass == null || confPass!= passwordEt.getText().toString()){
//            Toast.makeText(Registration.this,"password didn't matched try again!!",Toast.LENGTH_SHORT).show();
//            confPasswordEt.requestFocus();
//        }

        if(longitudeEt.getText().toString().isEmpty() || longitudeEt.getText().toString()==null){
            Toast.makeText(Registration.this,"please enter location coordinates",Toast.LENGTH_SHORT).show();
            longitudeEt.requestFocus();
            return false;
        }
        return true;
    }

    private Hotel getValueFromFields(){

        if(validateFields()){
            Hotel hotel=new Hotel();

            Long phone1=Long.parseLong(phoneEt.getText().toString());
            Long phone2=Long.parseLong(phone2Et.getText().toString());
            Integer pin= Integer.parseInt(pincodeEt.getText().toString());

            hotel.setPassword(passwordEt.getText().toString());
            hotel.setName(hotelNameEt.getText().toString());
            hotel.setContactPerson(contactNameEt.getText().toString());
            hotel.setDescription(descriptionEt.getText().toString());
            hotel.setPhone1(phone1);
            hotel.setPhone2(phone2);
            hotel.setEmail(emailEt.getText().toString());
            hotel.setCountryCode(countryCodeEt.getText().toString());
            hotel.setImageLink("not yet implemented");
            hotel.setRatings(0);
            // hotel.setImages();

            final Address address=new Address();
            address.setCity(cityEt.getText().toString());
            address.setStreet(streetEt.getText().toString());
            address.setPincode(pin);
            address.setState((String) stateSpinner.getSelectedItem());
            address.setCountry(country);
            address.setLatitude(latitudeEt.getText().toString());
            address.setLongitude(longitudeEt.getText().toString());
            hotel.setAddress(address);
            HotelImage hotelImage=new HotelImage();
            hotelImage.setId(10);
            hotelImage.setUrl("");
            hotelImage.setHotel(hotel);

            return hotel;

        }
        return null;
    }

    private void saveAction(){
        Hotel  hotel=getValueFromFields();
        NetworkService.getInstance()
                .getJSONApi()
                .postHotelData(hotel)
                .enqueue(new Callback<Hotel>() {
                    @Override
                    public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                        Toast.makeText(Registration.this,"registration successfull!!"+response,Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<Hotel> call, Throwable t) {

                    }
                });
    }
    private void clear(){
        pincodeEt.setText("");
        contactNameEt.setText("");
        contactNameEt.setText("");
        countryCodeEt.setText("");
        phone2Et.setText("");
        phoneEt.setText("");
        emailEt.setText("");
        descriptionEt.setText("");
        cityEt.setText("");
        streetEt.setText("");
        passwordEt.setText("");
        longitudeEt.setText("");
        latitudeEt.setText("");
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(Registration.this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
}
