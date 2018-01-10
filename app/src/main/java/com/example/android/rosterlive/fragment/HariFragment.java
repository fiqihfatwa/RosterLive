package com.example.android.rosterlive.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.rosterlive.R;
import com.example.android.rosterlive.adapters.JadwalAdapter;
import com.example.android.rosterlive.helper.SQLiteHandler;
import com.example.android.rosterlive.models.Jadwal;
import com.example.android.rosterlive.response.JadwalHarianResponse;
import com.example.android.rosterlive.service.JadwalService;
import com.example.android.rosterlive.utilities.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HariFragment extends Fragment {

    @BindView(R.id.rv_jadwal_harian)
    RecyclerView rvJadwalHarian;

    @BindView(R.id.swipeRefreshJadwal)
    SwipeRefreshLayout swipeRefreshJadwal;

    @BindView(R.id.tv_status_hari)
    TextView tvStatusHari;

    @BindColor(R.color.red)
    int red;

    @BindColor(R.color.yellow)
    int yellow;

    @BindColor(R.color.green)
    int green;

    List<Jadwal> jadwalList;
    JadwalAdapter adapter;

    private String hari;
    private int tanggal;

    private SQLiteHandler db;
    private SessionManager session;

    public HariFragment() {
        // Required empty public constructor
    }

    public HariFragment(String hari, int tanggal){
        this.hari = hari;
        this.tanggal = tanggal;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_hari, container, false);
        ButterKnife.bind(this, view);

        swipeRefreshJadwal.setColorSchemeColors(red,yellow,green);
        swipeRefreshJadwal.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(view);
            }
        });

        jadwalList = new ArrayList<>();
        adapter = new JadwalAdapter(jadwalList);
        rvJadwalHarian.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rvJadwalHarian.setLayoutManager(layoutManager);

        // session manager
        session = new SessionManager(view.getContext());

        loadData(view);

        return view;
    }

    private void loadData(final View view){

        // SqLite database handler
        db = new SQLiteHandler(view.getContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        if (!swipeRefreshJadwal.isRefreshing()){
            swipeRefreshJadwal.setRefreshing(true);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JadwalService.baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        JadwalService service = retrofit.create(JadwalService.class);

        /*service.listJadwal().enqueue(new Callback<List<JadwalResponse>>() {
            @Override
            public void onResponse(Call<List<JadwalResponse>> call, Response<List<JadwalResponse>> response) {
                List<JadwalResponse> hasil = response.body();

                jadwalList.clear();
                for (JadwalResponse jadwal : hasil){
                    if(jadwal.getTanggal() == tanggal && jadwal.getHari().equals(hari)) {
                        jadwalList.add(new Jadwal(jadwal.getJam(), jadwal.getStatusJadwal(), jadwal.getMataKuliah(), jadwal.getRuangan(), jadwal.getKom(), jadwal.getDosen(), jadwal.getSks()));
                    }
                }

                adapter.notifyDataSetChanged();
                swipeRefreshJadwal.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<JadwalResponse>> call, Throwable t) {
                //Log.d("HariFragment",t.toString());
                Toast.makeText(view.getContext(),"No Connection", Toast.LENGTH_SHORT).show();
                swipeRefreshJadwal.setRefreshing(false);
            }
        });*/

        service.jadwalHarian(user.get("username")).enqueue(new Callback<List<JadwalHarianResponse>>() {
            @Override
            public void onResponse(Call<List<JadwalHarianResponse>> call, Response<List<JadwalHarianResponse>> response) {
                List<JadwalHarianResponse> hasil = response.body();

                jadwalList.clear();
                for (JadwalHarianResponse jadwal : hasil){
                    if(Integer.parseInt(jadwal.getTanggal()) == tanggal) {
                        jadwalList.add(new Jadwal(jadwal.getMatkulId(), jadwal.getJamMatkul(), jadwal.getJadwal(), jadwal.getStatus(), jadwal.getMatkulName(), jadwal.getRuangan(), jadwal.getKelas(), jadwal.getKodeDosen(), jadwal.getSks(), jadwal.getDate(), jadwal.getMhsPengganti()));

                        if(jadwal.getStatus().equals("tidak masuk") || jadwal.getStatus().equals("libur")){
                            rvJadwalHarian.setVisibility(View.GONE);
                            tvStatusHari.setText("Tidak Ada Kelas Hari Ini");
                        }
                    }
                }

                adapter.notifyDataSetChanged();
                swipeRefreshJadwal.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<JadwalHarianResponse>> call, Throwable t) {
                //Log.d("HariFragment",t.toString());
                rvJadwalHarian.setVisibility(View.GONE);
                tvStatusHari.setText("Tidak dapat terhubung");
                swipeRefreshJadwal.setRefreshing(false);
            }
        });
    }

}
