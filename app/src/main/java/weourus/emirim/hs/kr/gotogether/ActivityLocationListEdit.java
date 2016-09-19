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
public class ActivityLocationListEdit extends Activity  implements View.OnClickListener{

    private static final String TAG = "ActivityLocationList";
    Button add_location_auto;
    Button next;
    ListView listview ;
    LocationListViewAdapter adapter;
    ArrayList<TravelLocation> location_list;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("이동 : ","TraveLocationListEdit");
        setContentView(R.layout.location_list_activity);

        add_location_auto = (Button)findViewById(R.id.add_location_auto);
        add_location_auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityLocationAddAutoCompleteEdit.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Adapter 생성
        adapter = new LocationListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.location_list);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        if(TravelService.tmpTravel.mLocations != null) {
            for (int i = 0; i < TravelService.tmpTravel.mLocations.size(); i++) {
                adapter.addItem(TravelService.tmpTravel.getLocations().get(i).getTitle(), TravelService.tmpTravel.getLocations().get(i).getLatLng());
            }
        }

        next = (Button)findViewById(R.id.next_page);
        next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),ActivityTravelEdit.class);
            startActivity(intent);
    }
}
