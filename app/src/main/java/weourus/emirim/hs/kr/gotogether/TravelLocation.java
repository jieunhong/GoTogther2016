package weourus.emirim.hs.kr.gotogether;

import android.view.LayoutInflater;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Asus on 2016-09-13.
 */
public class TravelLocation {
    private LatLng mLatlng;
    private String mTitle;


    public TravelLocation(){
    }
    public TravelLocation(LatLng latlng, String title){
        this.mLatlng = latlng;
        this.mTitle = title;
    }
    public LatLng getLatLng(){
        return mLatlng;
    }
    public void setLatLng(LatLng latlng){
        this.mLatlng = latlng;
    }
    public String getTitle(){
        return mTitle;
    }
    public void setTitle(String title){
        mTitle = title;
    }

    @Override
    public String toString() {
        return "TravelLocation{" +
                "mLatlng=" + mLatlng +
                ", mTitle='" + mTitle + '\'' +
                '}';
    }
}
