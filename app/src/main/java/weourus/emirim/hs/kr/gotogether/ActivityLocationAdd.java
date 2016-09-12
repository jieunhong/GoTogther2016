package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityLocationAdd extends Activity {
    Button cancel_location_add;
    Button add_location_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_add_activity);
        cancel_location_add = (Button)findViewById(R.id.cacel_location_add);
        add_location_list = (Button)findViewById(R.id.add_location_list);

        cancel_location_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityLocationAddAutoComplete.class);
                startActivity(intent);
            }
        });

        add_location_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityLocationList.class);
                startActivity(intent);
            }
        });
    }
}
