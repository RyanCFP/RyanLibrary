package com.ryan.utilslibrary.utils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 使用okhttp请求tokeh和调用服务
 */
public class HttpUtil {
    private static final String TAG = "Franck";
    static OkHttpClient client = new OkHttpClient();
    private static String uuid = "1707888014";
    private static String secret = "5zZSJ9IQgT33hvpVn5vIJcHzEV2O0E3VuOrEsHbf";

    public static void getToken(Callback callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("client_uuid", uuid)
                .add("client_secret", secret)
                .add("grant_type", "client_credentials")
                .build();

        final Request request = new Request.Builder()
                .url("https://iot.flnet.com/api/oauth/token")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //解析验证码

    public static String getCode(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String confirmCode = jsonObject.getString("confirm_code");
            return confirmCode;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解析 返回token值
   /* public static String getAccess_token(String jsonData){
        Gson gson = new Gson();
        Token token = gson.fromJson(jsonData,Token.class);
        Log.d(TAG, "parseJSONWithJSONObject: " + token.getAccess_token());
        return token.getAccess_token();
    }*/

    //解析 返回登录 result_code值
    public static int getLoginResult_code(String jsonData) {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonData);
            int resultCode = jsonObject.getInt("result_code");
            return resultCode;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
//        Gson gson = new Gson();
//        LoginResult_code resultCode = gson.fromJson(jsonData,LoginResult_code.class);
//        Log.d(TAG, "parseJSONWithJSONObject: " + resultCode.getResult_code());
//        return resultCode.getResult_code();
    }


    //获取验证码 post

    public static void sendSms(String mobile, String type, okhttp3.Callback callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("client_uuid", uuid)
                .add("client_secret", secret)
                .add("type", type)
                .add("mobile_country_code", "+86")
                .build();

        final Request request = new Request.Builder()
                .url("https://iot.flnet.com/api/sms/codes/" + mobile)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //验证验证码

    public static void checkSum(String name, String code, okhttp3.Callback callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("client_uuid", uuid)
                .add("client_secret", secret)
                .build();

        final Request request = new Request.Builder()
                .url("https://iot.flnet.com/api/sms/codes/" + name + "/validation" + code)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //注册
    public static void register(String username, String confirm_code, String mail, String pass, String mGender, okhttp3.Callback callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("client_uuid", uuid)
                .add("client_secret", secret)
                .add("username", username)
                .add("mobile_country_code", "+86")
                .add("mobile_confirm_code", confirm_code)
                .add("password", pass)
                .add("email", mail)
                .add("gender", mGender)
                .build();

        final Request request = new Request.Builder()
                .url("https://iot.flnet.com/api/auth/register")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //登录
    public static void login(String username, String pass, okhttp3.Callback callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("client_uuid", uuid)
                .add("client_secret", secret)
                .add("username", username)
                .add("password", pass)
                .build();

        final Request request = new Request.Builder()
                .url("https://iot.flnet.com/api/auth/login")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //忘记密码
    public static void amend(String name, String pass, okhttp3.Callback callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("client_uuid", uuid)
                .add("client_secret", secret)
                .add("mobile", name)
                .add("password", pass)
                .build();

        final Request request = new Request.Builder()
                .url("https://iot.flnet.com/api/auth/passwords/reset")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //获取用户信息
    public static void getUser(String user_uuid, okhttp3.Callback callback) {
        final Request request = new Request.Builder()
                .url("https://iot.flnet.com/api/users/" + user_uuid + "?client_uuid=" + uuid + "&client_secret=" + secret)
                .get()
                .build();
        client.newCall(request).enqueue(callback);
    }

    //修改密码
    public static void changePassWord(String user_uuid, String old_pass, String new_pass, okhttp3.Callback callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("client_uuid", uuid)
                .add("client_secret", secret)
                .add("user_uuid", user_uuid)
                .add("old_password", old_pass)
                .add("new_password", new_pass)
                .build();

        final Request request = new Request.Builder()
                .url("https://iot.flnet.com/api/auth/passwords/change")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
