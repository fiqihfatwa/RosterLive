package com.example.android.rosterlive.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.rosterlive.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fiqh on 13/12/17.
 */

public class JadwalViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rootview)
    public View rootview;

    @BindView(R.id.tv_jam)
    public TextView tvJam;

    @BindView(R.id.tv_status_jadwal)
    public TextView tvStatusJadwal;

    @BindView(R.id.tv_matakuliah)
    public TextView tvMataKuliah;

    @BindView(R.id.tv_ruangan)
    public TextView tvRuangan;

    @BindView(R.id.tv_kom)
    public TextView tvKom;

    @BindView(R.id.tv_dosen)
    public TextView tvDosen;

    @BindView(R.id.tv_sks)
    public TextView tvSks;

    public JadwalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
