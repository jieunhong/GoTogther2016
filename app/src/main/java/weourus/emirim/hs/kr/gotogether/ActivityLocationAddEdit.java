package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityLocationAddEdit extends Activity {
    Button cancel_location_add;
    Button add_location_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("이동 : ","TravelLocationAdd");
        setContentView(R.layout.location_add_activity);
        cancel_location_add = (Button)findViewById(R.id.cacel_location_add);
        add_location_list = (Button)findViewById(R.id.add_location_list);

        cancel_location_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityLocationAddAutoCompleteEdit.class);
                startActivity(intent);
            }
        });

        add_location_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*location_list  = new ArrayList<TravelLocation>();

                location_list.add(new TravelLocation( new LatLng(37.4664393,126.9305103), "미림여자정보과학고"));
                location_list.add(new TravelLocation( new LatLng(37.4664393,126.9305103), "신림 포도몰 롯데시네마"));
                location_list.add(new TravelLocation( new LatLng(37.4664393,126.9305103), "큰엄마네 떡볶이"));
                location_list.add(new TravelLocation( new LatLng(37.4664393,126.9305103), "선린인터넷고등학교"));*/
                if (TravelService.tmpTravel.mLocations==null) TravelService.tmpTravel.mLocations = new ArrayList<TravelLocation>();
                TravelService.tmpTravel.mLocations.add(new TravelLocation( new LatLng(37.4664393,126.9305103), "미림여자정보과학고"));
                Intent intent = new Intent(getApplicationContext(),ActivityLocationListEdit.class);
                startActivity(intent);
            }
        });
    }
}
