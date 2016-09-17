package emirim.hs.kr.mapgps3;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapClickListener, OnMapLongClickListener, OnMapReadyCallback {
    SupportMapFragment fm;
    GoogleMap mMap;
    public final int MY_LOCATION_REQUEST_CODE = 101;

    double latitude=37.554752;
    double longitude=126.970631;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        fm.getMapAsync(this);

    }

    public void onMapClick(LatLng point){
        MarkerOptions marker = new MarkerOptions();
        marker.position(point);
        mMap.addMarker(marker);
        CircleOptions circle=new CircleOptions();
        circle.center(point).radius(1000);
        mMap.addCircle(circle);
    }

    public void onMapLongClick(LatLng point){
        mMap.clear();
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        mMap = gMap;

        final LatLng Loc=new LatLng(latitude,longitude);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Loc,16));
//        gMap.setMyLocationEnabled(true);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            gMap.setMyLocationEnabled(true);
        } else {
            Toast.makeText(MainActivity.this, "You have to accept to enjoy all app's services!", Toast.LENGTH_LONG).show();
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_LOCATION_REQUEST_CODE);
                gMap.setMyLocationEnabled(true);
            }
        }
        gMap.setOnMapClickListener(this);
        gMap.setOnMapLongClickListener(this);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }
}
