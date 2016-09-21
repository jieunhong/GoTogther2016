package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityTravelDetail extends Activity{

    TextView travel_info_name;
    TextView travel_info_day;
    ListView travel_info_list;
    LocationListViewAdapter adapter;
    Button delete;
    Button edit;
    Button back;
    Button travel;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("이동 : ","TraveDetail");
        setContentView(R.layout.travel_detail_activity);

        travel_info_day = (TextView)findViewById(R.id.travel_info_day);
        travel_info_name = (TextView)findViewById(R.id.travel_info_name);
        travel_info_list = (ListView)findViewById(R.id.travel_info_list);
        delete = (Button)findViewById(R.id.travel_delete);
        edit = (Button)findViewById(R.id.travel_edit);
        back = (Button)findViewById(R.id.back_travel_list);
        travel = (Button)findViewById(R.id.travel_travel);

    }

    @Override
    protected void onResume() {
        super.onResume();

        position = getIntent().getExtras().getInt("position");


        travel_info_name.setText(TravelService.travels.get(position).getName());
        travel_info_day.setText(TravelService.travels.get(position).getDay()+"일차 일정입니다!");


        adapter = new LocationListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        travel_info_list.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        if(TravelService.travels.get(position).mLocations != null) {
            for (int i = 0; i < TravelService.travels.get(position).mLocations.size(); i++) {
                adapter.addItem(TravelService.travels.get(position).mLocations.get(i).getTitle(), TravelService.travels.get(position).mLocations.get(i).getLatLng());
            }
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelService.travels.remove(position);
                Intent intent = new Intent(getApplicationContext(),ActivityTravelList.class);
                startActivity(intent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelService.tmpTravel.setLocations(TravelService.travels.get(position).getLocations());
                TravelService.tmpTravel.setName(TravelService.travels.get(position).getName());
                TravelService.tmpTravel.setDay(TravelService.travels.get(position).getDay());
                Intent intent = new Intent(getApplicationContext(),ActivityTravelEdit.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityTravelList.class);
                startActivity(intent);
            }
        });
        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("들어감","ghgh");
                Intent intent = new Intent(getApplicationContext(),ActivityTravel.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),ActivityTravelList.class);
        startActivity(intent);
    }
}
