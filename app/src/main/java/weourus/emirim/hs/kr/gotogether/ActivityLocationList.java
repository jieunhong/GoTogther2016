package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityLocationList extends Activity {

    private static final String TAG = "ActivityLocationList";
    Button add_location_auto;
    ListView listview ;
    LocationListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list_activity);

        add_location_auto = (Button)findViewById(R.id.add_location_auto);


        add_location_auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityLocationAddAutoComplete.class);
                startActivity(intent);
            }
        });
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<TravelLocation> tmpLocs = new ArrayList<TravelLocation>();
        tmpLocs.add(new TravelLocation( new LatLng(37.4664393,126.9305103), "미림여자정보과학고"));
        TravelService.travels.add( new Travel( "신림동 여행", 1, tmpLocs));
        TravelService.travels.add( new Travel( "고시촌 맛집 여행", 2, null));
        TravelService.travels.add( new Travel( "대학동 여행", 3, null));
        TravelService.travels.add( new Travel( "서원동 여행", 1, null));
        TravelService.travels.add( new Travel( "신림동1 여행", 1, null));
        TravelService.travels.add( new Travel( "신림동3 2여행", 1, null));
        TravelService.travels.add( new Travel( "신림동34 여행", 1, null));

        Log.d(TAG, "***Travels 저장된 값 출력 ***");
        for(int i = 0 ; i < TravelService.travels.size(); i++){
            Log.d(TAG, ""+ i + "번째 : " + TravelService.travels.get(i).toString());
        }
        // Adapter 생성
        adapter = new LocationListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.travel_list);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        for(int i = 0 ; i < TravelService.travels.size(); i++){
            adapter.addItem(TravelService.travels.get(i).getName(),TravelService.travels.get(i).getDay()) ;
        }
    }*/
}
