package com.example.android.rosterlive.service;

import com.example.android.rosterlive.response.GantiJadwalResponse;
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
    public String baseUrl = "http://192.168.1.101/rosterlive/";

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
            @Field("password") String password,
            @Field("token") String token
    );

    @POST("jadwal_ganti")
    @FormUrlEncoded
    Call<GantiJadwalResponse> responseGantiJadwal(
            @Field("matkul_id") String matkul_id,
            @Field("kom") String kom,
            @Field("ruangan") String ruangan,
            @Field("tgl_sebelum") String tgl_sebelum,
            @Field("tgl_setelah") String tgl_setelah,
            @Field("jam") String jam,
            @Field("nim") String nim
    );
}
