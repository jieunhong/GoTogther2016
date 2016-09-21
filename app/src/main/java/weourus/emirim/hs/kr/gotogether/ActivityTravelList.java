package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityTravelList extends Activity{

    private static final String TAG = "함께가요:ActivityTravelList";
    Button add_travel;
    Button home;
    ListView listview ;
    TravelListViewAdapter adapter;
    private Realm realm;
    private RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_list_activity);
        add_travel = (Button)findViewById(R.id.add_travel);
        home = (Button)findViewById(R.id.home);
        listview = (ListView)findViewById(R.id.travel_list);
        realmConfig = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfig);

        add_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelService.tmpTravel = new Travel("",0,null);
                Intent intent = new Intent(getApplicationContext(),ActivityTravelAdd.class);
                startActivity(intent);
            }
        });
        try {
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("성공", ": 디테일 ");
                    Log.d("성공!"," : "+position);
                    Intent intent = new Intent(getApplicationContext(),ActivityTravelDetail.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                 //   Toast.makeText(ActivityTravelList.this, position, Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Log.d("에러"," : 디테일");
        }
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityMain.class);
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

        DBTravel person = realm.where(DBTravel.class).findFirst();
        //(person.getName() + ":" + person.getAge());
        // 첫 번째 아이템 추가.
        if(person != null) {
            adapter.addItem(person.getTravelName(), person.getTravelDay());
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),ActivityMain.class);
        startActivity(intent);
    }
}
