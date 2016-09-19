package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;

import java.util.ArrayList;

/**
 * Created by Asus on 2016-09-19.
 */
public class ActivityTravel extends FragmentActivity implements OnMapReadyCallback,OnMapClickListener,OnMapLongClickListener  {
    private GoogleMap mMap;
    PolylineOptions polylineOptions;
    ArrayList<LatLng> arrayPoints;

    double latitude = 37.554752;
    double longitude = 126.970631;

    public final int MY_LOCATION_REQUEST_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        chkGpsService();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng Loc = new LatLng(latitude, longitude);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Loc, 16));

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)//마시멜로우 이전방식에서 허가되어있으면 실행
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            Toast.makeText(ActivityTravel.this, "You have to accept to enjoy all app's services!", Toast.LENGTH_LONG).show();
            //허가요청하는 거
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_REQUEST_CODE);
        }

        arrayPoints = new ArrayList<LatLng>();

        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
    }

    //허가요청이 떨어졌을 때 안드로이드에 의해 호출되는 메서드
    @Override
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

    private boolean chkGpsService() {
        String gps = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(!(gps.matches(".*gps.*")&&gps.matches(".*network.*"))) {

            // GPS OFF 일때 Dialog 표시
            AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
            gsDialog.setTitle("위치 서비스 설정");
            gsDialog.setMessage("GPS기능을 사용해야 정확한 위치 서비스가 가능합니다.\n위치 서비스 기능을 설정하시겠습니까?");
            gsDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // GPS설정 화면으로 이동
                    Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivity(intent);
                }
            }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            }).create().show();
            return false;
        }
        else {
            return true;
        }

    }
    public void onMapClick(LatLng latLng){
        double arrLatLng[][]={{37.555,126.9},{37.559,126.9711},{37.8,127.5}};

        if(arrLatLng[0][0]!=0) {
            mMap.clear();
            for (int i = 0; i < arrLatLng.length; i++) {
                final LatLng Loc = new LatLng(arrLatLng[i][0], arrLatLng[i][1]);
                if (i == 0) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Loc, 16));
                }
                //선택한 장소에 핀 꽂기
                MarkerOptions marker = new MarkerOptions();
                marker.position(Loc);
                mMap.addMarker(marker);

                polylineOptions = new PolylineOptions();
                polylineOptions.color(Color.RED);
                polylineOptions.width(5);
                arrayPoints.add(Loc);
                polylineOptions.addAll(arrayPoints);
                mMap.addPolyline(polylineOptions);
            }
        }
    }

    public void onMapLongClick(LatLng point){
        mMap.clear();
        arrayPoints.clear();
    }
}
