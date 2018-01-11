package com.example.android.rosterlive.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.rosterlive.R;
import com.example.android.rosterlive.adapters.JadwalMingguanAdapter;
import com.example.android.rosterlive.helper.SQLiteHandler;
import com.example.android.rosterlive.models.Jadwal;
import com.example.android.rosterlive.response.JadwalHarianResponse;
import com.example.android.rosterlive.response.JadwalMigguanResponse;
import com.example.android.rosterlive.service.JadwalService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
public class MingguanFragment extends Fragment {

    @BindView(R.id.rv_senin)
    RecyclerView rvSenin;

    @BindView(R.id.rv_selasa)
    RecyclerView rvSelasa;

    @BindView(R.id.rv_rabu)
    RecyclerView rvRabu;

    @BindView(R.id.rv_kamis)
    RecyclerView rvKamis;

    @BindView(R.id.rv_jumat)
    RecyclerView rvJumat;

    @BindView(R.id.rv_sabtu)
    RecyclerView rvSabtu;

    @BindView(R.id.rv_minggu)
    RecyclerView rvMinggu;

    List<Jadwal> jadwalListSenin, jadwalListSelasa, jadwalListRabu, jadwalListKamis, jadwalListJumat, jadwalListSabtu, jadwalListMinggu;
    JadwalMingguanAdapter adapter;
    private SQLiteHandler db;
    public MingguanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mingguan, container, false);
        ButterKnife.bind(this, view);

        jadwalListSenin = new ArrayList<>();
        jadwalListSelasa = new ArrayList<>();
        jadwalListRabu = new ArrayList<>();
        jadwalListKamis = new ArrayList<>();
        jadwalListJumat = new ArrayList<>();
        jadwalListSabtu = new ArrayList<>();
        jadwalListMinggu = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        loadData(view);

        adapter = new JadwalMingguanAdapter(jadwalListSenin);
        rvSenin.setAdapter(adapter);
        rvSenin.setLayoutManager(layoutManager);

        adapter = new JadwalMingguanAdapter(jadwalListSelasa);
        rvSelasa.setAdapter(adapter);
        rvSelasa.setLayoutManager(layoutManager);

        adapter = new JadwalMingguanAdapter(jadwalListRabu);
        rvRabu.setAdapter(adapter);
        rvRabu.setLayoutManager(layoutManager);

        adapter = new JadwalMingguanAdapter(jadwalListKamis);
        rvKamis.setAdapter(adapter);
        rvKamis.setLayoutManager(layoutManager);

        adapter = new JadwalMingguanAdapter(jadwalListJumat);
        rvJumat.setAdapter(adapter);
        rvJumat.setLayoutManager(layoutManager);

        adapter = new JadwalMingguanAdapter(jadwalListSabtu);
        rvSabtu.setAdapter(adapter);
        rvSabtu.setLayoutManager(layoutManager);

        adapter = new JadwalMingguanAdapter(jadwalListMinggu);
        rvMinggu.setAdapter(adapter);
        rvMinggu.setLayoutManager(layoutManager);

        return view;
    }

    private void loadData(final View view){

        // SqLite database handler
        db = new SQLiteHandler(view.getContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();


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

        service.jadwalMingguan(user.get("username")).enqueue(new Callback<List<JadwalMigguanResponse>>() {
            @Override
            public void onResponse(Call<List<JadwalMigguanResponse>> call, Response<List<JadwalMigguanResponse>> response) {
                List<JadwalMigguanResponse> jadwalMigguanResponses = response.body();

                for(JadwalMigguanResponse hasil : jadwalMigguanResponses){
                    jadwalListSenin.add(new Jadwal(hasil.getJam(),hasil.getMataKuliah(),hasil.getRuangan(),hasil.getKom(),hasil.getKodeDosen(),hasil.getSks()));
                }
            }

            @Override
            public void onFailure(Call<List<JadwalMigguanResponse>> call, Throwable t) {

            }
        });
    }

}
