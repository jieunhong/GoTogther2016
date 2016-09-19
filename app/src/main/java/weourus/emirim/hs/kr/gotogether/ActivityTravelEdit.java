package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
    static int position;
    LocationListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("이동 : ","TravelEdit");

        setContentView(R.layout.travel_add_activity);
        travel_day = (EditText) findViewById(R.id.travel_day);
        travel_name = (EditText) findViewById(R.id.travel_name);
        travel_location_list = (ListView) findViewById(R.id.travel_location_list);
        add_location = (Button) findViewById(R.id.add_location);
        add_travel_edit = (Button) findViewById(R.id.add_travel_list);

        try {
            position = getIntent().getExtras().getInt("position");
        }catch (Exception e){}

        travel_name.setText(""+TravelService.tmpTravel.getName());
        travel_day.setText(""+TravelService.tmpTravel.getDay());

        add_travel_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    TravelService.travels.get(position).setName(travel_name.getText().toString());
                    TravelService.travels.get(position).setDay(Integer.parseInt(travel_day.getText().toString()));
                    TravelService.travels.get(position).setLocations(TravelService.tmpTravel.getLocations());
                    TravelService.tmpTravel.setLocations(null);
                    TravelService.tmpTravel.setName(null);
                    TravelService.tmpTravel.setDay(0);
                    Intent intent = new Intent(getApplicationContext(), ActivityTravelDetail.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }catch (Exception e){

                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityTravelEdit.this);     // 여기서 this는 Activity의 this
                    // 여기서 부터는 알림창의 속성 설정
                    builder.setTitle("필수 입력사항 경고창") // 제목 설정
                            .setMessage("여행 제목과 여행 일정, 경로를 작성해주세요!")        // 메세지 설정
                            .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                            .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                                // 확인 버튼 클릭시 설정
                                public void onClick(DialogInterface dialog, int whichButton){

                                }
                            });
                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();    // 알림창 띄우기
                }
            }
        });
        add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TravelService.tmpTravel!=null) {
                   try {
                        TravelService.tmpTravel.setName(travel_name.getText().toString());
                        TravelService.tmpTravel.setDay(Integer.parseInt(travel_day.getText().toString()));
                        Intent intent = new Intent(getApplicationContext(),ActivityLocationListEdit.class);
                        startActivity(intent);
                    }catch (Exception e){
                        Log.d("예외 : ",""+e);
                        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityTravelEdit.this);     // 여기서 this는 Activity의 this
                        // 여기서 부터는 알림창의 속성 설정
                        builder.setTitle("필수 입력사항 경고창")        // 제목 설정
                                .setMessage("여행 제목과 여행 일정을 작성해주세요!")        // 메세지 설정
                                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                                .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                                    // 확인 버튼 클릭시 설정
                                    public void onClick(DialogInterface dialog, int whichButton){
                                    }
                                });
                        AlertDialog dialog = builder.create();    // 알림창 객체 생성
                        dialog.show();    // 알림창 띄우기
                    }
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new LocationListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        travel_location_list = (ListView) findViewById(R.id.travel_location_list);
        travel_location_list.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        if(TravelService.tmpTravel.getLocations()!=null) {
            for (int i = 0; i < TravelService.tmpTravel.getLocations().size(); i++) {
                adapter.addItem(TravelService.tmpTravel.getLocations().get(i).getTitle(), TravelService.tmpTravel.getLocations().get(i).getLatLng());
            }
        }
    }
        /*travel_name.setText(TravelService.travels.get(position).getName());
        travel_day.setText(String.valueOf(TravelService.travels.get(position).getDay()));
        if(TravelService.tmpTravel.getLocations()!=null) {
            TravelService.travels.get(position).setLocations(TravelService.tmpTravel.getLocations());
        }

        add_travel_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelService.travels.get(position).setName(travel_name.getText().toString());
                TravelService.travels.get(position).setDay(Integer.parseInt(travel_day.getText().toString()));
                TravelService.travels.get(position).setLocations(TravelService.tmpTravel.getLocations());
                Intent intent = new Intent(getApplicationContext(),ActivityTravelDetail.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelService.tmpTravel.setLocations(TravelService.travels.get(position).getLocations());
                Intent intent = new Intent(getApplicationContext(),ActivityLocationList.class);
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
    }*/
}
