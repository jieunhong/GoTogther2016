package weourus.emirim.hs.kr.gotogether;

import io.realm.RealmObject;

/**
 * Created by Asus on 2016-09-21.
 */
public class DBMember extends RealmObject {
    private String mName;
    private String mGender;
    private String mEmail;
    private String mAccessToken;

    public DBMember() {
    }

    public DBMember(String mName, String mGender, String mEmail, String mAccessToken) {
        this.mName = mName;
        this.mGender = mGender;
        this.mEmail = mEmail;
        this.mAccessToken = mAccessToken;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmAccessToken() {
        return mAccessToken;
    }

    public void setmAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }
}
