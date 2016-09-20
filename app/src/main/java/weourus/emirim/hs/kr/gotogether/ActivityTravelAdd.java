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
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityTravelAdd extends Activity {
    LocationListViewAdapter adapter;
    ListView listview;
    EditText travel_name;
    EditText travel_day;
    Button location_add;
    Button add_travel_list;

    private Realm realm;
    private RealmConfiguration realmConfig;
    ArrayList<TravelLocation> tmp_tls = new ArrayList<TravelLocation>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_add_activity);
        location_add = (Button)findViewById(R.id.add_location);
        add_travel_list = (Button)findViewById(R.id.add_travel_list);
        travel_day = (EditText)findViewById(R.id.travel_day);
        travel_name = (EditText)findViewById(R.id.travel_name);
        realmConfig = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfig);

        if(TravelService.tmpTravel.getName()!=null) travel_name.setText(""+TravelService.tmpTravel.getName());
        if(TravelService.tmpTravel.getDay()!=0)travel_day.setText(""+TravelService.tmpTravel.getDay());

        add_travel_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    TravelService new_travel = new TravelService();
                    new_travel.travels.add(new Travel(travel_name.getText().toString(), Integer.parseInt(travel_day.getText().toString()), TravelService.tmpTravel.getLocations()));
                    basicCRUD(realm);
                    /*realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            DBTravel travel = realm.createObject(DBTravel.class);
                            travel.setTravelName(travel_name.getText().toString());
                            travel.setTravelDay(Integer.parseInt(travel_day.getText().toString()));
                            travel.setTravelLocations(new RealmList<DBTravelLocation>());

                            DBTravelLocation tl;
                            for (int i = 0; i < TravelService.tmpTravel.mLocations.size(); i++){
                                tl = realm.createObject(DBTravelLocation.class);
                                tl.add(TravelService.tmpTravel.mLocations.get(i).getTitle(), TravelService.tmpTravel.mLocations.get(i).getLatLng().latitude,TravelService.tmpTravel.mLocations.get(i).getLatLng().longitude);
                                travel.getTravelLocations().add(tl);
                            }
                        }
                    });

                    DBTravel travel = realm.where(DBTravel.class).findFirstAsync();
                    Log.d("확인", "" + travel.getTravelName());
                    for (int i = 0; i < travel.getTravelLocations().size(); i++)
                        Log.d("확인", "" + travel.getTravelLocations().get(i).getTLName());
                    */
                    TravelService.tmpTravel.setLocations(null);
                    TravelService.tmpTravel.setName(null);
                    TravelService.tmpTravel.setDay(0);
                    Intent intent = new Intent(getApplicationContext(), ActivityTravelList.class);
                    startActivity(intent);

                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityTravelAdd.this);     // 여기서 this는 Activity의 this
                    // 여기서 부터는 알림창의 속성 설정
                     builder.setTitle("필수 입력사항 경고창") // 제목 설정
                            .setMessage("여행 제목과 여행 일정, 경로를 작성해주세요!"+e.getMessage())        // 메세지 설정
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
        location_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TravelService.tmpTravel!=null) {
                    try {

                        TravelService.tmpTravel.setName(travel_name.getText().toString());
                        TravelService.tmpTravel.setDay(Integer.parseInt(travel_day.getText().toString()));
                        Intent intent = new Intent(getApplicationContext(), ActivityLocationList.class);
                        startActivity(intent);
                    }catch (Exception e){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityTravelAdd.this);     // 여기서 this는 Activity의 this
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

    private void basicCRUD(Realm realm) {
        //   RealmList<TravelLocation> travellist = new

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                DBTravel person = realm.createObject(DBTravel.class);
                person.setTravelName(TravelService.tmpTravel.getName());
                person.setTravelDay(TravelService.tmpTravel.getDay());
                /*person.setTravelName("부산여행");
                person.setTravelDay(1);*/
                person.setTravelLocations(new RealmList<DBTravelLocation>());

                DBTravelLocation tl;
                /*tl = realm.createObject(DBTravelLocation.class);
                tl.setTLName("신림역");
                tl.setTLLat(4.131592);
                tl.setTLLog(12.934);
                person.getTravelLocations().add(tl);*/
                for (int i = 0; i < TravelService.tmpTravel.mLocations.size(); i++){
                    tl = realm.createObject(DBTravelLocation.class);
                    tl.setTLName(TravelService.tmpTravel.mLocations.get(i).getTitle());
                    tl.setTLLat(TravelService.tmpTravel.mLocations.get(i).getLatLng().latitude);
                    tl.setTLLog(TravelService.tmpTravel.mLocations.get(i).getLatLng().longitude);
                    person.getTravelLocations().add(tl);
                }


            }
        });

        DBTravel person = realm.where(DBTravel.class).findFirst();
        //(person.getName() + ":" + person.getAge());
        Toast.makeText(ActivityTravelAdd.this, person.getTravelName() + " : " + person.getTravelDay(), Toast.LENGTH_SHORT).show();
        for (int i = 0; i < person.getTravelLocations().size(); i++){
            Toast.makeText(this, "" + i + " / " + person.getTravelLocations().get(i).getTLName() , Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        adapter = new LocationListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.travel_location_list);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        if(TravelService.tmpTravel.getLocations()!=null) {
            for (int i = 0; i < TravelService.tmpTravel.getLocations().size(); i++) {
                adapter.addItem(TravelService.tmpTravel.getLocations().get(i).getTitle(), TravelService.tmpTravel.getLocations().get(i).getLatLng());
            }
        }
    }
}
