package com.sanatandigitizers.plustworoomsadmin.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sanatandigitizers.plustworoomsadmin.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
        ,GoogleMap.OnMarkerDragListener
        ,GoogleMap.OnInfoWindowClickListener {

    LatLng latLng;
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    private TextView resutText,latitudeTv,longitudeTv;
    private Button latLongBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        resutText = (TextView) findViewById(R.id.drag_result);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // configureCameraIdle();

        latitudeTv = (TextView)findViewById(R.id.latitude_tv);
        longitudeTv = (TextView)findViewById(R.id.longitude_tv);
        latLongBtn = (Button)findViewById(R.id.latlong_btn);

        latLongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MapsActivity.this,Registration.class);
                intent.putExtra("Latitude",latitudeTv.getText().toString());
                intent.putExtra("Longitude",longitudeTv.getText().toString());
                Registration.temp=1;
                startActivity(intent);
                finish();
            }
        });

    }

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
            latitudeTv.setText(String.valueOf(addresses.get(0).getLatitude()));
            longitudeTv.setText(String.valueOf(addresses.get(0).getLongitude()));

            Toast.makeText(MapsActivity.this, "Address: " +
                    address + " " + city, Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
