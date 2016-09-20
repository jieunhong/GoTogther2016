package weourus.emirim.hs.kr.gotogether;

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
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;

import java.util.ArrayList;

public class ActivityTravel extends FragmentActivity implements OnMapReadyCallback,OnMapLongClickListener  {
    private GoogleMap mMap;
    PolylineOptions polylineOptions;
    ArrayList<LatLng> arrayPoints;

    double latitude = 37.554752;
    double longitude = 126.970631;

    public final int MY_LOCATION_REQUEST_CODE = 101;//임의로 정한 값(바뀌어도 상관 없음)

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
        //처음 지도 실행 화면으로 서울역을 가리킴

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //마시멜로우 이전 방식을 사용할 때 허가가 된 상태이면 실행
            mMap.setMyLocationEnabled(true);
        } else {
            //허가를 요청
            Toast.makeText(ActivityTravel.this, "앱을 사용하기 위해 승인을 받아야 합니다.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_REQUEST_CODE);
        }

        arrayPoints = new ArrayList<LatLng>();

        printLine();
        mMap.setOnMapLongClickListener(this);


    }

    //허가요청이 떨어졌을 때 안드로이드에 의해 호출되는 메서드
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //noinspection MissingPermission
                mMap.setMyLocationEnabled(true);
                //현재 위치를 지도에 나타낼지에 대한 여부
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }

    private boolean chkGpsService() {
        //앱을 실행했을 때 GPS가 켜져있는지 확인하고 꺼져있다면 설정창을 띄울 수 있게 하는 기능
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
    public void printLine(){
        //
        double arrLatLng[][]={{37.555,126.9},{37.559,126.9711},{37.8,127.5}};

        if(arrLatLng[0][0]!=0) {
            //DB에 좌표값이 저장되어 있을 때 실행
            mMap.clear();
            for (int i = 0; i < arrLatLng.length; i++) {
                final LatLng Loc = new LatLng(arrLatLng[i][0], arrLatLng[i][1]);
                if (i == 0) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Loc, 16));
                    //DB에 좌표값이 있을 경우 여행의 시작지점으로 지도를 다시 셋팅
                }
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
