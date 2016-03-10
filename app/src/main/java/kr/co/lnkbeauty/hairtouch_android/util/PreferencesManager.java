package kr.co.lnkbeauty.hairtouch_android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import kr.co.lnkbeauty.hairtouch_android.model.CommonCodeModel;
import kr.co.lnkbeauty.hairtouch_android.model.DesignerModel;

/**
 * Created by leetaejun on 2016. 2. 14..
 */
public class PreferencesManager {

    // SHAREDPREFERENCES NAME
    private static final String PREF_NAME = "kr.co.lnkbeauty.hairtouch_android.SHAREDPREFERENCES";

    // PROPERTY
    private static final String KEY_COMMON_CODES = "kr.co.lnkbeauty.hairtouch_android.KEY_COMMON_CODES";
    private static final String KEY_ACCESSTOKEN = "kr.co.lnkbeauty.hairtouch_android.KEY_ACCESSTOKEN";
    private static final String KEY_DESIGNER = "kr.co.lnkbeauty.hairtouch_android.KEY_DESIGNER";

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

    public void setCommonCodes(List<CommonCodeModel> commonCodes) {
        Gson gson = new Gson();
        String commonCodesString = gson.toJson(commonCodes);
        mPref.edit()
                .putString(KEY_COMMON_CODES, commonCodesString)
                .commit();
    }

    public List<CommonCodeModel> getCommonCodes() {
        Gson gson = new Gson();
        String commonCodesString = mPref.getString(KEY_COMMON_CODES, null);
        Type type = new TypeToken<List<CommonCodeModel>>() {}.getType();

        if (commonCodesString != null) {
            return gson.fromJson(commonCodesString, type);
        } else {
            return null;
        }
    }

    public void setAccessToken(String accessToken) {
        mPref.edit()
                .putString(KEY_ACCESSTOKEN, accessToken)
                .commit();
    }

    public String getAccessToken() {
        return mPref.getString(KEY_ACCESSTOKEN, null);
    }

    public void setDesigner(DesignerModel designer) {
        Gson gson = new Gson();
        String designerString = gson.toJson(designer);
        mPref.edit()
                .putString(KEY_DESIGNER, designerString)
                .commit();
    }

    public DesignerModel getDesigner() {
        Gson gson = new Gson();
        String deisngerString = mPref.getString(KEY_DESIGNER, null);
        if (deisngerString != null) {
            return gson.fromJson(deisngerString, DesignerModel.class);
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
