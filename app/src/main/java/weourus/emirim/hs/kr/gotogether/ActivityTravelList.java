package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityTravelList extends Activity{

    private static final String TAG = "함께가요:ActivityTravelList";
    Button add_travel;
    ListView listview ;
    TravelListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_list_activity);
        add_travel = (Button)findViewById(R.id.add_travel);

        add_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityTravelAdd.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        // 더미 데이터
        // 나중에 여기는 디비에서 데이터를 받와서 넣음

        ArrayList<TravelLocation> tmpLocs = new ArrayList<TravelLocation>();
       /* tmpLocs.add(new TravelLocation( new LatLng(37.4664393,126.9305103), "미림여자정보과학고"));
        TravelService.travels.add( new Travel( "신림동 여행", 1, tmpLocs));
        TravelService.travels.add( new Travel( "고시촌 맛집 여행", 2, null));
        TravelService.travels.add( new Travel( "대학동 여행", 3, null));
        TravelService.travels.add( new Travel( "서원동 여행", 1, null));
        TravelService.travels.add( new Travel( "신림동1 여행", 1, null));
        TravelService.travels.add( new Travel( "신림동3 2여행", 1, null));
        TravelService.travels.add( new Travel( "신림동34 여행", 1, null));*/

        Log.d(TAG, "***Travels 저장된 값 출력 ***");
        for(int i = 0 ; i < TravelService.travels.size(); i++){
            Log.d(TAG, ""+ i + "번째 : " + TravelService.travels.get(i).toString());
        }
        // Adapter 생성
        adapter = new TravelListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.travel_list);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        for(int i = 0 ; i < TravelService.travels.size(); i++){
            adapter.addItem(TravelService.travels.get(i).getName(),TravelService.travels.get(i).getDay()) ;
        }
    }
}
