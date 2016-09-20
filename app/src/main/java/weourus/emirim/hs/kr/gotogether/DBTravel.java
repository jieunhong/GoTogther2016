package weourus.emirim.hs.kr.gotogether;

/**
 * Created by Asus on 2016-09-19.
 */

import io.realm.RealmList;
import io.realm.RealmObject;

public class DBTravel extends RealmObject{
    private String TravelName;
    private int TravelDay;
    private RealmList<DBTravelLocation> TravelLocations;

    public String getTravelName() { return TravelName; }
    public void setTravelName(String travelName) { TravelName = travelName; }
    public int getTravelDay() { return TravelDay; }
    public void setTravelDay(int travelDay) { TravelDay = travelDay; }
    public RealmList<DBTravelLocation> getTravelLocations() { return TravelLocations; }
    public void setTravelLocations(RealmList<DBTravelLocation> travelLocations) { TravelLocations = travelLocations; }

    public DBTravel() {}

    public DBTravel(String travelName, int travelDay, RealmList<DBTravelLocation> travelLocations) {
        TravelName = travelName;
        TravelDay = travelDay;
        TravelLocations = travelLocations;
    }
}

