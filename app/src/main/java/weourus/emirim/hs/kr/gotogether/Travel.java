package weourus.emirim.hs.kr.gotogether;

import java.util.ArrayList;

/**
 * Created by Asus on 2016-09-13.
 */
public class Travel {

    private String mName;
    private int mDay;
    private ArrayList<TravelLocation> mLocations = new ArrayList<TravelLocation>();

    public Travel() {
    }

    public Travel(String mName, int mDay, ArrayList<TravelLocation> mLocations) {
        this.mName = mName;
        this.mDay = mDay;
        this.mLocations = mLocations;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int mDay) {
        this.mDay = mDay;
    }

    public ArrayList<TravelLocation> getLocations() {
        return mLocations;
    }

    public void setLocations(ArrayList<TravelLocation> mLocations) {
        this.mLocations = mLocations;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "mName='" + mName + '\'' +
                ", mDay=" + mDay +
                ", mLocations=" + mLocations +
                '}';
    }
}
