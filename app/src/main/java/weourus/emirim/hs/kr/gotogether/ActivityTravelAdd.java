package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityTravelAdd extends Activity {
    EditText travel_name;
    EditText travel_day;
    Button location_add;
    Button add_travel_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_add_activity);
        location_add = (Button)findViewById(R.id.add_location);
        add_travel_list = (Button)findViewById(R.id.add_travel_list);
        travel_day = (EditText)findViewById(R.id.travel_day);
        travel_name = (EditText)findViewById(R.id.travel_name);

        add_travel_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelService new_travel = new TravelService();
                new_travel.travels.add(new Travel(travel_name.getText().toString(),Integer.parseInt(travel_day.getText().toString()),null));
                Intent intent = new Intent(getApplicationContext(),ActivityTravelList.class);
                startActivity(intent);
            }
        });
        location_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityLocationList.class);
                startActivity(intent);
            }
        });
    }
}
