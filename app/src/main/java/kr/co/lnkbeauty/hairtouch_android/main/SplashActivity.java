package kr.co.lnkbeauty.hairtouch_android.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import kr.co.lnkbeauty.hairtouch_android.R;
import kr.co.lnkbeauty.hairtouch_android.model.CommonCodeModel;
import kr.co.lnkbeauty.hairtouch_android.network.APIRequest;
import kr.co.lnkbeauty.hairtouch_android.network.APIService;
import kr.co.lnkbeauty.hairtouch_android.sign.SignInActivity;
import kr.co.lnkbeauty.hairtouch_android.util.PreferencesManager;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                APIService service = APIRequest.getInstacne().getService();
                service.loadCommonCodes().enqueue(new Callback<List<CommonCodeModel>>() {
                    @Override
                    public void onResponse(Response<List<CommonCodeModel>> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            PreferencesManager.getInstance().setCommonCodes(response.body());

                            Intent intent;

                            if (PreferencesManager.getInstance().getAccessToken() == null) {
                                intent = new Intent(SplashActivity.this, SignInActivity.class);
                            } else {
                                intent = new Intent(SplashActivity.this, MainActivity.class);
                            }

                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(SplashActivity.this, "API 요청에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(SplashActivity.this, "API 요청에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(SplashActivity.this, "로딩중입니다... 잠시만 기다려주세요", Toast.LENGTH_SHORT).show();
    }
}
