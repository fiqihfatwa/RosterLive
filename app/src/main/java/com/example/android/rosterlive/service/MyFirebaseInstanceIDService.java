package com.example.android.rosterlive.service;

import android.util.Log;

import com.example.android.rosterlive.helper.SQLiteHandler;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by isadadi on 09/01/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();
    private SQLiteHandler db;

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token  : "+token);
        registerToken(token);
    }


    private void registerToken(String token){
        db = new SQLiteHandler(getApplicationContext());

        boolean login = db.cek_user();
        if(login) {
            HashMap<String, String> user = db.getUserDetails();
            String nim = user.get("username");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("token", token)
                    .add("nim", nim)
                    .build();

            Request request = new Request.Builder()
                    .url("http://192.168.1.101/rosterlive/mahasiswa/update_token")
                    .post(body)
                    .build();

            try {
                client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
