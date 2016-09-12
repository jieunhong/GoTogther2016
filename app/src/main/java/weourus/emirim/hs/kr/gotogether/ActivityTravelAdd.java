package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityTravelAdd extends Activity {
    Button location_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_add_activity);
        location_add = (Button)findViewById(R.id.add_location);

        location_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityLocationList.class);
                startActivity(intent);
            }
        });
    }
}
