package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityLocationAddAutoCompleteEdit extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("이동 : ","TravelAutoEdit");
        setContentView(R.layout.location_auto_activity);
        Intent intent = new Intent(getApplicationContext(),ActivityLocationAddEdit.class);
        startActivity(intent);
    }
}
