package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Asus on 2016-09-12.
 */
public class ActivityLocationAddAutoComplete extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_auto_activity);
        Intent intent = new Intent(getApplicationContext(),ActivityLocationAdd.class);
        startActivity(intent);
    }
}
