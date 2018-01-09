package com.example.android.rosterlive.service;

import com.example.android.rosterlive.response.JadwalHarianResponse;
import com.example.android.rosterlive.response.JadwalResponse;
import com.example.android.rosterlive.response.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by fiqh on 14/12/17.
 */

public interface JadwalService {
    public String baseUrl = "http://rosterlive.pdankdisdiksu.com/";

    @GET("api.php")
    Call<List<JadwalResponse>> listJadwal();

    @POST("mahasiswa/harian")
    @FormUrlEncoded
    Call<List<JadwalHarianResponse>> jadwalHarian(
            @Field("nim") String nim
    );

    @POST("mahasiswa")
    @FormUrlEncoded
    Call<LoginResponse> dataLogin(
            @Field("username") String username,
            @Field("password") String password
    );
}