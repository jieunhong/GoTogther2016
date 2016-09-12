package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityLocationList extends Activity {
    Button add_location_auto;
    Button add_travel_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list_activity);

        add_location_auto = (Button)findViewById(R.id.add_location_auto);
        add_travel_list = (Button)findViewById(R.id.add_travel_list);

        add_location_auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityLocationAddAutoComplete.class);
                startActivity(intent);
            }
        });
        add_travel_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityTravelList.class);
                startActivity(intent);
            }
        });

    }
}
