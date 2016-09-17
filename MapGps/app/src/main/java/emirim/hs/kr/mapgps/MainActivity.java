package emirim.hs.kr.mapgps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    MapFragment mapFr;
    EditText editText;
    Button btn;

    double latitude=37.554752;
    double longitude=126.970631;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init(){
        mapFr=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFr.getMapAsync(this);
        editText=(EditText)findViewById(R.id.editText);
        btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String place=editText.getText().toString();
                searchPlace(place);
            }
        });

    }
    public void onMapReady(GoogleMap googleMap){
        map=googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                &&(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED))
        {
            map.setMyLocationEnabled(true);
        }
        UiSettings uiSettings=map.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        final LatLng Loc=new LatLng(latitude,longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Loc,16));

        MarkerOptions options =new MarkerOptions();
        options.position(Loc);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        map.addMarker(options);
        Marker mk1=map.addMarker(options);
        mk1.showInfoWindow();
    }

    void searchPlace(String place) {
        Geocoder gc = new Geocoder(this, Locale.KOREAN);
        try {
            List<android.location.Address> addr = gc.getFromLocationName(place, 5);
            if (addr != null) {
                latitude = addr.get(0).getLatitude();
                longitude = addr.get(0).getLongitude();
                final LatLng Loc = new LatLng(latitude, longitude);
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(Loc, 16));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}