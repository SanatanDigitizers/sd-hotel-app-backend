package com.sanatandigitizers.plustworoomsadmin.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class PermissionUtil extends Activity {
    private Activity mContext;
    public PermissionUtil() {
    }
    public PermissionUtil(Activity activity) {
        this.mContext = activity;
    }

    public static boolean isPermissionSupportivePlatform(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public boolean isPermissionAllowed(PermissionType pt) {
        int result = ContextCompat.checkSelfPermission(mContext, pt.getName());
        Toast.makeText(mContext,""+result,Toast.LENGTH_SHORT).show();
        return (result == PackageManager.PERMISSION_GRANTED);
    }

    public void requestPermission(PermissionType pt) {
        boolean permissionStatus = isPermissionAllowed(pt);
        if (!permissionStatus) {
            Toast.makeText(mContext,""+pt.getName(),Toast.LENGTH_SHORT).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(mContext, pt.getName())) {
                //If the user has denied the permission previously your code will come to this block
                //Here you can explain why you need this permission
                //Explain here why you need this permission
            }
            ActivityCompat.requestPermissions(mContext, new String[]{pt.getName()}, 1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(mContext,"Permission granted now you can WRITE the storage",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(mContext,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
        }
    }

    public enum PermissionType {
        WRITE_EXTERNAL_STORAGE(60), READ_EXTERNAL_STORAGE(61),
        WRITE_CALENDAR(62),READ_CALENDAR(63),
        CAMERA(64),
        WRITE_CONTACTS(65),READ_CONTACTS(66),GET_ACCOUNTS(67),
        ACCESS_FINE_LOCATION(68),ACCESS_COARSE_LOCATION(69),
        RECORD_AUDIO(70),
        READ_PHONE_STATE(71),READ_PHONE_NUMBERS(72),CALL_PHONE(73),ANSWER_PHONE_CALLS(74),READ_CALL_LOG(75),WRITE_CALL_LOG(76),ADD_VOICEMAIL(77),USE_SIP(78),PROCESS_OUTGOING_CALLS(79),
        BODY_SENSORS(80),
        SEND_SMS(81),RECEIVE_SMS(82),READ_SMS(83),RECEIVE_MMS(84),RECEIVE_WAP_PUSH(85);

        private final int code;
        private PermissionType(int code){
            this.code = code;
        }

        public int getCode () {
            return code;
        }

        public String getName(){
            return "android.permission."+name();
        }


        public static PermissionType getValue(int code){
            switch(code){
                case 60 : return WRITE_EXTERNAL_STORAGE;
                case 61:  return READ_EXTERNAL_STORAGE;
                case 62:  return WRITE_CALENDAR;
                case 63:  return READ_CALENDAR;
                case 64:  return  CAMERA;
                case 65:  return WRITE_CONTACTS;
                case 66:  return READ_CONTACTS;
                case 67:  return GET_ACCOUNTS;
                case 68:  return ACCESS_FINE_LOCATION;
                case 69:  return ACCESS_COARSE_LOCATION;
                case 70:  return RECORD_AUDIO;
                case 71:  return READ_PHONE_STATE;
                case 72:  return READ_PHONE_NUMBERS;
                case 73:  return CALL_PHONE;
                case 74:  return ANSWER_PHONE_CALLS;
                case 75:  return READ_CALL_LOG;
                case 76:  return WRITE_CALL_LOG;
                case 77:  return ADD_VOICEMAIL;
                case 78:  return USE_SIP;
                case 79:  return PROCESS_OUTGOING_CALLS;
                case 80:  return BODY_SENSORS;
                case 81:  return SEND_SMS;
                case 82:  return RECEIVE_SMS;
                case 83:  return READ_SMS;
                case 84:  return RECEIVE_MMS;
                case 85:  return RECEIVE_WAP_PUSH;
                default: return WRITE_EXTERNAL_STORAGE;
            }
        }

    }

}
