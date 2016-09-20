package weourus.emirim.hs.kr.gotogether;

/**
 * Created by Asus on 2016-09-19.
 */

import io.realm.RealmObject;

public class DBTravelLocation extends RealmObject {
    private String TLName;
    private double TLLat;
    private double TLLog;

    public String getTLName() {
        return TLName;
    }
    public void setTLName(String TLName) {
        this.TLName = TLName;
    }
    public double getTLLat() {
        return TLLat;
    }
    public void setTLLat(double TLLat) {
        this.TLLat = TLLat;
    }
    public double getTLLog() {
        return TLLog;
    }
    public void setTLLog(double TLLog) {
        this.TLLog = TLLog;
    }

    public DBTravelLocation() {
    }
    void add(String TLName, double TLLat, double TLLog) {
        this.TLName = TLName;
        this.TLLat = TLLat;
        this.TLLog = TLLog;
    }
}