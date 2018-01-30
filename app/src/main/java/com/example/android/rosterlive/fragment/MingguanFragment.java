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
import com.example.android.rosterlive.response.JadwalMingguanResponse;
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

//import com.example.android.rosterlive.response.JadwalMigguanResponse;

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
    JadwalMingguanAdapter adapterSenin, adapterSelasa, adapterRabu, adapterKamis, adapterJumat, adapterSabtu, adapterMinggu;
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

        loadData(view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterSenin = new JadwalMingguanAdapter(jadwalListSenin);
        rvSenin.setAdapter(adapterSenin);
        rvSenin.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterSelasa = new JadwalMingguanAdapter(jadwalListSelasa);
        rvSelasa.setAdapter(adapterSelasa);
        rvSelasa.setLayoutManager(layoutManager1);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterRabu = new JadwalMingguanAdapter(jadwalListRabu);
        rvRabu.setAdapter(adapterRabu);
        rvRabu.setLayoutManager(layoutManager2);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterKamis = new JadwalMingguanAdapter(jadwalListKamis);
        rvKamis.setAdapter(adapterKamis);
        rvKamis.setLayoutManager(layoutManager3);

        LinearLayoutManager layoutManager4 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterJumat = new JadwalMingguanAdapter(jadwalListJumat);
        rvJumat.setAdapter(adapterJumat);
        rvJumat.setLayoutManager(layoutManager4);

        LinearLayoutManager layoutManager5 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterSabtu = new JadwalMingguanAdapter(jadwalListSabtu);
        rvSabtu.setAdapter(adapterSabtu);
        rvSabtu.setLayoutManager(layoutManager5);

        LinearLayoutManager layoutManager6 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterMinggu = new JadwalMingguanAdapter(jadwalListMinggu);
        rvMinggu.setAdapter(adapterMinggu);
        rvMinggu.setLayoutManager(layoutManager6);

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

        service.jadwalMingguan(user.get("username")).enqueue(new Callback<List<JadwalMingguanResponse>>() {
            @Override
            public void onResponse(Call<List<JadwalMingguanResponse>> call, Response<List<JadwalMingguanResponse>> response) {
                List<JadwalMingguanResponse> hasil = response.body();

                for (JadwalMingguanResponse jadwalMingguan : hasil) {
                    if (jadwalMingguan.getHari().equals("Senin")) {
                        jadwalListSenin.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Selasa")) {
                        jadwalListSelasa.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Rabu")) {
                        jadwalListRabu.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Kamis")) {
                        jadwalListKamis.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Jumat")) {
                        jadwalListJumat.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Sabtu")) {
                        jadwalListSabtu.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Minggu")) {
                        jadwalListMinggu.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    }
                }

                adapterSenin.notifyDataSetChanged();
                adapterSelasa.notifyDataSetChanged();
                adapterRabu.notifyDataSetChanged();
                adapterKamis.notifyDataSetChanged();
                adapterJumat.notifyDataSetChanged();
                adapterSabtu.notifyDataSetChanged();
                adapterMinggu.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<JadwalMingguanResponse>> call, Throwable t) {

            }
        });
    }

}
