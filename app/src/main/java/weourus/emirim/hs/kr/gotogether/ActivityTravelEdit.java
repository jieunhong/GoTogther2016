package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityTravelEdit extends Activity {

    EditText travel_name;
    EditText travel_day;
    ListView travel_location_list;
    Button add_location;
    Button add_travel_edit;
    int position;
    LocationListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_add_activity);
        travel_day = (EditText)findViewById(R.id.travel_day);
        travel_name = (EditText)findViewById(R.id.travel_name);
        travel_location_list = (ListView)findViewById(R.id.travel_location_list);
        add_location = (Button)findViewById(R.id.add_location);
        add_travel_edit = (Button)findViewById(R.id.add_travel_list);

        position = getIntent().getExtras().getInt("position");

        travel_name.setText(TravelService.travels.get(position).getName());
        travel_day.setText(String.valueOf(TravelService.travels.get(position).getDay()));


        add_travel_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelService.travels.get(position).setName(travel_name.getText().toString());
                TravelService.travels.get(position).setDay(Integer.parseInt(travel_day.getText().toString()));
                Intent intent = new Intent(getApplicationContext(),ActivityTravelDetail.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new LocationListViewAdapter() ;
        // 리스트뷰 참조 및 Adapter달기
        travel_location_list.setAdapter(adapter);
        // 첫 번째 아이템 추가.
        if(TravelService.travels.get(position).mLocations != null) {
            for (int i = 0; i < TravelService.travels.get(position).mLocations.size(); i++) {
                adapter.addItem(TravelService.travels.get(position).mLocations.get(i).getTitle(), TravelService.travels.get(position).mLocations.get(i).getLatLng());
            }
        }
    }
}
