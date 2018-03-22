package com.ryan.utilslibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by ryan on 18-2-28.
 * Email: Ryan_chan01212@yeah.net
 */

public class SharePrefUtils {
    private static final String TAG = SharePrefUtils.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public SharePrefUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences("bind_wifi", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    //暂存WiFi账号和密码
    public void saveBindWifi(String key, String value) {
        if (!EmptyCheckUtils.isEmptyOrNull(key)) {
            throw new IllegalArgumentException("key 值不能为空");
        }
        mEditor.putString(key, value);
        mEditor.commit();
        Log.d("Franck", "saveBindWifi: ");
    }

    public String getBindWifi(String key) {
        if (!EmptyCheckUtils.isEmptyOrNull(key)) {
            throw new IllegalArgumentException("key 值不能为空");
        }
        String result = mSharedPreferences.getString(key, null);
        Log.i(TAG, "getBindWifi: key = " + key + ",value = " + result);
        return result;
    }

    //暂存WiFi是否开启和权限是否获取的状态
    public void saveWiFiPermissionAndOnOff(String key, boolean value) {
        if (!EmptyCheckUtils.isEmptyOrNull(key)) {
            throw new IllegalArgumentException("key 值不能为空");
        }
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public boolean getWiFiPermissionAndOnOff(String key) {
        if (!EmptyCheckUtils.isEmptyOrNull(key)) {
            throw new IllegalArgumentException("key 值不能为空");
        }
        return mSharedPreferences.getBoolean(key, false);
    }

    //暂存WiFi是否绑定成功的状态
    public void savaBindSuccess(String key, boolean isBindSuccess) {
        if (!EmptyCheckUtils.isEmptyOrNull(key)) {
            throw new IllegalArgumentException("key 值不能为空");
        }
        mEditor.putBoolean(key, isBindSuccess);
        mEditor.commit();
    }

    public boolean getBindSuccess(String key) {
        if (!EmptyCheckUtils.isEmptyOrNull(key)) {
            throw new IllegalArgumentException("key 值不能为空");
        }
        return mSharedPreferences.getBoolean(key, false);
    }

    public void save(String key, boolean value) {
        if (!EmptyCheckUtils.isEmptyOrNull(key)) {
            throw new IllegalArgumentException("key 值不能为空");
        }
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public boolean getValue(String key) {
        if (!EmptyCheckUtils.isEmptyOrNull(key)) {
            throw new IllegalArgumentException("key 值不能为空");
        }
        return mSharedPreferences.getBoolean(key, true);
    }

    public void release() {
        mSharedPreferences = null;
        mEditor = null;
    }
}
