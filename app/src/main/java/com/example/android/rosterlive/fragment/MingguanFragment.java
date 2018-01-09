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
import com.example.android.rosterlive.models.Jadwal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MingguanFragment extends Fragment {

    @BindView(R.id.rv_senin)
    RecyclerView rvSenin;

    List<Jadwal> jadwalList;
    JadwalMingguanAdapter adapter;

    public MingguanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mingguan, container, false);
        ButterKnife.bind(this, view);

        jadwalList = new ArrayList<>();
        jadwalList.add(new Jadwal("10:00","Pemograman Mobile","106","B","ROM","3"));
        adapter = new JadwalMingguanAdapter(jadwalList);
        adapter.notifyDataSetChanged();
        rvSenin.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rvSenin.setLayoutManager(layoutManager);

        return view;
    }

}
