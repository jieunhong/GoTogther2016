package weourus.emirim.hs.kr.gotogether;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by Asus on 2016-09-21.
 */
public class ActivityLogin extends Activity {

    private static String OAUTH_CLIENT_ID = "Btkp9vVHvjNc3Ml8fhmW";
    private static String OAUTH_CLIENT_SECRET = "HGqLNMGNk5";
    private static String OAUTH_CLIENT_NAME = "네이버 아이디로 로그인";

    private static OAuthLogin mOAuthLoginInstance;
    private static Context mContext;

    static String mEmail = "";
    static String mName = "";

    String email = "";
    String nickname = "";
    String enc_id = "";
    String profile_image = "";
    String age = "";
    String gender = "";
    String id = "";
    String name = "";
    String birthday = "";

    String accessToken = "";
    String tokenType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();

        setContentView(R.layout.login_activity);

        mOAuthLoginInstance = OAuthLogin.getInstance();

        mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID,
                OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);

        initSetting();
    }


    private void initSetting() {

        final OAuthLoginButton btn_naver = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);


        btn_naver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mOAuthLoginInstance.startOauthLoginActivity(ActivityLogin.this,
                        mOAuthLoginHandler);
            }
        });

    }

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                accessToken = mOAuthLoginInstance.getAccessToken(mContext);
                String refreshToken = mOAuthLoginInstance
                        .getRefreshToken(mContext);
                long expiresAt = mOAuthLoginInstance.getExpiresAt(mContext);
                tokenType = mOAuthLoginInstance.getTokenType(mContext);

                Log.d("myLog", "accessToken  " + accessToken);
                Log.d("myLog", "refreshToken  " + refreshToken);
                Log.d("myLog",
                        "String.valueOf(expiresAt)  "
                                + String.valueOf(expiresAt));
                Log.d("myLog", "tokenType  " + tokenType);
                Log.d("myLog",
                        "mOAuthLoginInstance.getState(mContext).toString()  "
                                + mOAuthLoginInstance.getState(mContext)
                                .toString());


                new RequestApiTask().execute(); //로그인이 성공하면  네이버에 계정값들을 가져온다.
                Toast.makeText(ActivityLogin.this, "로그인이 성공 하였습니다.!",
                        Toast.LENGTH_SHORT).show();


            } else {
                String errorCode = mOAuthLoginInstance.getLastErrorCode(
                        mContext).getCode();
                String errorDesc = mOAuthLoginInstance
                        .getLastErrorDesc(mContext);
                // Toast.makeText(mContext, "errorCode:" + errorCode +
                // ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();

                Toast.makeText(ActivityLogin.this, "로그인이 취소/실패 하였습니다.!",
                        Toast.LENGTH_SHORT).show();
            }
        }

        ;
    };

    private class RequestApiTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            String url = "https://openapi.naver.com/v1/nid/getUserProfile.xml";
            String at = mOAuthLoginInstance.getAccessToken(mContext);
            Pasingversiondata(mOAuthLoginInstance.requestApi(mContext, at, url));

            return null;
        }

        protected void onPostExecute(Void content) {
            Log.d("myLog", "email " + email);
            Log.d("myLog", "name " + name);
            Log.d("myLog", "nickname " + nickname);


            if (email == null) {
                Toast.makeText(ActivityLogin.this,
                        "로그인 실패하였습니다.  잠시후 다시 시도해 주세요!!", Toast.LENGTH_SHORT)
                        .show();
            } else {

                ActivityLogin.mEmail = email;
                ActivityLogin.mName = name;
                Toast.makeText(getApplicationContext(), "이메일" +ActivityLogin.mEmail + " 이름 " + ActivityLogin.mName, Toast.LENGTH_LONG).show();
                /*profile_email.setText(email);
                profile_name.setText(nickname+"("+name+")");*/
                /*profile_email.append(email);
                profile_name.append(name);*/
                Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
                Toast.makeText(getApplicationContext(), "이메일 " + email + " 이름 " + name, Toast.LENGTH_LONG).show();
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("nick", nickname);
                intent.putExtra("image", profile_image);
                intent.putExtra("birth", birthday);
                intent.putExtra("gender", gender);
                intent.putExtra("age", age);
                startActivity(intent);


            }

        }

        private void Pasingversiondata(String data) { // xml 파싱
            String f_array[] = new String[9];

            try {
                XmlPullParserFactory parserCreator = XmlPullParserFactory
                        .newInstance();
                XmlPullParser parser = parserCreator.newPullParser();
                InputStream input = new ByteArrayInputStream(
                        data.getBytes("UTF-8"));
                parser.setInput(input, "UTF-8");

                int parserEvent = parser.getEventType();
                String tag;
                boolean inText = false;
                boolean lastMatTag = false;

                int colIdx = 0;

                while (parserEvent != XmlPullParser.END_DOCUMENT) {
                    switch (parserEvent) {
                        case XmlPullParser.START_TAG:
                            tag = parser.getName();
                            if (tag.compareTo("xml") == 0) {
                                inText = false;
                            } else if (tag.compareTo("data") == 0) {
                                inText = false;
                            } else if (tag.compareTo("result") == 0) {
                                inText = false;
                            } else if (tag.compareTo("resultcode") == 0) {
                                inText = false;
                            } else if (tag.compareTo("message") == 0) {
                                inText = false;
                            } else if (tag.compareTo("response") == 0) {
                                inText = false;
                            } else {
                                inText = true;

                            }
                            break;
                        case XmlPullParser.TEXT:
                            tag = parser.getName();
                            if (inText) {
                                if (parser.getText() == null) {
                                    f_array[colIdx] = "";
                                } else {
                                    f_array[colIdx] = parser.getText().trim();
                                }

                                colIdx++;
                            }
                            inText = false;
                            break;
                        case XmlPullParser.END_TAG:
                            tag = parser.getName();
                            inText = false;
                            break;

                    }

                    parserEvent = parser.next();

                }
            } catch (Exception e) {
                Log.e("dd", "Error in network call", e);
            }
            email = f_array[0];
            nickname = f_array[1];
            enc_id = f_array[2];
            profile_image = f_array[3];
            age = f_array[4];
            gender = f_array[5];
            id = f_array[6];
            name = f_array[7];
            birthday = f_array[8];

        }
    }
}