package com.sanatandigitizers.plustworoomsadmin.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sanatandigitizers.plustworoomsadmin.R;

public class LoginAcitivity extends AppCompatActivity {

    //<--------UI Variables-------->
   private Button loginBtn,googleLoginBtn;
   private TextView forgotPasswordTv;
   private EditText emailEt,passwordEt;

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

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginAcitivity.this,HomeActivity.class);
                startActivity(intent);
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
}
