package com.sanatandigitizers.plustworoomsadmin.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sanatandigitizers.plustworoomsadmin.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
        ,GoogleMap.OnMarkerDragListener
        ,GoogleMap.OnInfoWindowClickListener {

    LatLng latLng;
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    private TextView resutText,locality,adminArea,countryCode,featureName,countryName,subAdminArea,subLocality,postalCode,premises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        resutText = (TextView) findViewById(R.id.drag_result);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // configureCameraIdle();

        adminArea=(TextView)findViewById(R.id.adminarea);
        subAdminArea=(TextView)findViewById(R.id.subadminarea);
        countryCode=(TextView)findViewById(R.id.ccode);
        countryName=(TextView)findViewById(R.id.cname);
        subLocality=(TextView)findViewById(R.id.sublocality);
        premises=(TextView)findViewById(R.id.premises);
        postalCode=(TextView)findViewById(R.id.postalcode);
        featureName=(TextView)findViewById(R.id.featurename);
        locality=(TextView)findViewById(R.id.locality);

    }

//    private void configureCameraIdle() {
//        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
//            @Override
//            public void onCameraIdle() {
//
//                LatLng latLng = mMap.getCameraPosition().target;
//                Geocoder geocoder = new Geocoder(MapsActivity.this);
//
//                try {
//                    List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
//                    if (addressList != null && addressList.size() > 0) {
//                        String locality = addressList.get(0).getAddressLine(0);
//                        String country = addressList.get(0).getCountryName();
//                        if (!locality.isEmpty() && !country.isEmpty())
//                             resutText.setText(""+locality+""+country);
//                            Toast.makeText(MapsActivity.this, locality + "  " + country, Toast.LENGTH_LONG).show();
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(26.157056,91.7334035));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);

        addMarker(mMap, 26.157056,91.7334035, R.string.majestic, R.string.Guwahati);
        mMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerDragListener(this);

    }

    private void addMarker(GoogleMap map, double lat, double lon,
                           int title, int snippet) {
        map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
                .title(getString(title))
                .snippet(getString(snippet))
                .draggable(true));


    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        LatLng position=marker.getPosition();

        Log.d(getClass().getSimpleName(), String.format("Drag from %f:%f",
                position.latitude,
                position.longitude));

        Toast.makeText(MapsActivity.this,String.format("Drag from %f:%f",
                position.latitude,
                position.longitude),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        LatLng position=marker.getPosition();

        Log.d(getClass().getSimpleName(),
                String.format("Dragging to %f:%f", position.latitude,
                        position.longitude));

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        LatLng position = marker.getPosition();

        Log.d(getClass().getSimpleName(), String.format("Dragged to %f:%f",
                position.latitude,
                position.longitude));


        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());


        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();

            resutText.setText(""+address+""+city);
            postalCode.setText(addresses.get(0).getPostalCode());
            countryName.setText(addresses.get(0).getCountryName());
            countryCode.setText(addresses.get(0).getCountryCode());
            premises.setText(addresses.get(0).getPremises());
            featureName.setText(addresses.get(0).getFeatureName());
            subLocality.setText(addresses.get(0).getSubLocality());
            subAdminArea.setText(addresses.get(0).getSubAdminArea());
            adminArea.setText(addresses.get(0).getAdminArea());
            locality.setText(addresses.get(0).getLocality());
            Toast.makeText(MapsActivity.this, "Address: " +
                    address + " " + city, Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    private void setLocation(View view){
//        Intent intent=new Intent(MapsActivity.this,Registration.class);
//        intent.putExtra("locality",locality.getText().toString());
//
//
//    }
}
