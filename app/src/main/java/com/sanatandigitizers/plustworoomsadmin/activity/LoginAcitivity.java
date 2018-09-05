package com.sanatandigitizers.plustworoomsadmin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.model.AppSession;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import com.sanatandigitizers.plustworoomsadmin.model.HotelRecyclerAdapter;
import com.sanatandigitizers.plustworoomsadmin.networkUtil.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAcitivity extends AppCompatActivity {

    //<--------UI Variables-------->
   private Button loginBtn,googleLoginBtn;
   private TextView forgotPasswordTv;
   private EditText emailEt,passwordEt;

   private List<Hotel>hotelList;
   String id,password;
   private boolean isUserClickedBackButton=false;
   View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //<-----Ui initialization--------->
        loginBtn=(Button)findViewById(R.id.login_btn);
        forgotPasswordTv=(TextView)findViewById(R.id.tv_forgot_password);
        forgotPasswordTv.setPaintFlags(forgotPasswordTv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        emailEt=(EditText)findViewById(R.id.et_email);
        passwordEt=(EditText)findViewById(R.id.et_password);

        loadData();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int id = Integer.parseInt(emailEt.getText().toString());
                    NetworkService.getInstance()
                            .getJSONApi()
                            .getHotel(id)
                            .enqueue(new Callback<Hotel>() {
                                @Override
                                public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                                    Hotel h = response.body();
                                    String pass = passwordEt.getText().toString();

                                    if(h.getPassword().equals(pass)){
                                        AppSession.hotel = h;
                                        AppSession.isAppAdmin = false;

                                        saveData("email",emailEt.getText().toString());
                                        saveData("password",passwordEt.getText().toString());

                                               Intent intent=new Intent(LoginAcitivity.this,HomeActivity.class);
                                                startActivity(intent);
                                                finish();
                                    }else{
                                        Toast.makeText(LoginAcitivity.this, "Wrong password", Toast.LENGTH_LONG).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Hotel> call, Throwable t) {
                                    Toast.makeText(LoginAcitivity.this,"No such hotel id found!!!!",Toast.LENGTH_SHORT).show();
                                }
                            });
                }catch(NumberFormatException ex){

                    String fixedLoginEmail = "xyz@abc.com";

                }


            }
        });
    }

    private boolean validateFields(){
        if(emailEt.getText().toString() == null || emailEt.getText().toString().isEmpty()){
            Toast.makeText(LoginAcitivity.this,"please enter valid email",Toast.LENGTH_SHORT).show();
            emailEt.requestFocus();
            return false;
        }
        if(passwordEt.getText().toString() == null || passwordEt.getText().toString().isEmpty()){
            Toast.makeText(LoginAcitivity.this,"please enter valid password",Toast.LENGTH_SHORT).show();
            passwordEt.requestFocus();
            return false;
        }
        return true;
    }


    private void saveData(String key, String value){
        SharedPreferences sharedPreferences=getSharedPreferences("userData",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,value);

        editor.commit();

    }
    private void loadData(){
        SharedPreferences sharedPreferences=getSharedPreferences("userData",MODE_PRIVATE);
        id=sharedPreferences.getString("email","");
        password=sharedPreferences.getString("password","");
        emailEt.setText(id);
        passwordEt.setText(password);
    }

    @Override
    public void onBackPressed() {
        if (!isUserClickedBackButton)
        {
            v=findViewById(android.R.id.content);
            Snackbar.make(v,"Press back again to exit"+" "+ Html.fromHtml("&#9995"),Snackbar.LENGTH_SHORT).show();
            isUserClickedBackButton=true;
        }
        else {
            super.onBackPressed();
        }
        new CountDownTimer(3000,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                isUserClickedBackButton=false;
            }
        }.start();
    }

}
