package com.kel1.kouveepetshop.Notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.kel1.kouveepetshop.View.Admin.pengadaanMain;

/**
 * Created by delaroy on 10/8/17.
 */

public class TokenManager {
    private static final String SHARED_PREF_NAME = "FCMSharedPref";
    private static final String TAG_TOKEN = "tagtoken";

    private static TokenManager mInstance;
    private static Context mCtx;

    private TokenManager(Context context) {
        mCtx = context;
    }

    public static synchronized TokenManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new TokenManager(context);
        }
        return mInstance;
    }

    //this method will save the device token to shared preferences
    public boolean saveDeviceToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKEN, token);
        editor.apply();
        return true;
    }

    //this method will fetch the device token from shared preferences
    public String getDeviceToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_TOKEN, null);
    }

}
