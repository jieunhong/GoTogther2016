package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityTravelList extends Activity{

    Button add_travel;
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
}
