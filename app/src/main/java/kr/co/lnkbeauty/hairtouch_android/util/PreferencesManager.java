package kr.co.lnkbeauty.hairtouch_android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import kr.co.lnkbeauty.hairtouch_android.model.UserModel;

/**
 * Created by leetaejun on 2016. 2. 14..
 */
public class PreferencesManager {

    // SHAREDPREFERENCES NAME
    private static final String PREF_NAME = "kr.co.lnkbeauty.hairtouch_android.SHAREDPREFERENCES";

    // PROPERTY
    private static final String KEY_ACCESSTOKEN = "kr.co.lnkbeauty.hairtouch_android.KEY_ACCESSTOKEN";
    private static final String KEY_USER = "kr.co.lnkbeauty.hairtouch_android.KEY_USER";

    // VAR
    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setAccessToken(String accessToken) {
        mPref.edit()
                .putString(KEY_ACCESSTOKEN, accessToken)
                .commit();
    }

    public String getAccessToken() {
        return mPref.getString(KEY_ACCESSTOKEN, null);
    }

    public void setUser(UserModel user) {
        Gson gson = new Gson();
        String userString = gson.toJson(user);
        mPref.edit()
                .putString(KEY_USER, userString)
                .commit();
    }

    public UserModel getUser() {
        Gson gson = new Gson();
        String userString = mPref.getString(KEY_USER, null);
        if (userString != null) {
            return gson.fromJson(userString, UserModel.class);
        } else {
            return null;
        }
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }
}
