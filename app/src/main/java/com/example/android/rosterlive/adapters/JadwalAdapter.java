package com.example.android.rosterlive.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.rosterlive.GantiJadwalActivity;
import com.example.android.rosterlive.R;
import com.example.android.rosterlive.models.Jadwal;
import com.example.android.rosterlive.viewholder.JadwalViewHolder;

import java.util.List;

/**
 * Created by fiqh on 13/12/17.
 */

public class JadwalAdapter extends RecyclerView.Adapter<JadwalViewHolder> {

    private List<Jadwal> listJadwal;

    public JadwalAdapter(List<Jadwal> listJadwal){
        this.listJadwal = listJadwal;
    }

    @Override
    public JadwalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);

        return new JadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JadwalViewHolder holder, int position) {
        final Jadwal data = listJadwal.get(position);

        if(data.getStatus()=="ujian"){
            holder.tvStatusJadwal.setText(data.getStatus());
        }else{
            holder.tvStatusJadwal.setText(data.getStatusJadwal());
            if(data.getStatusJadwal().equals("diganti")){
                holder.tvStatusJadwal.setBackgroundResource(R.color.red);
            }else if(data.getStatusJadwal().equals("pengganti")){
                holder.tvStatusJadwal.setBackgroundResource(R.color.orange);
            }
        }
        holder.tvJam.setText(data.getJam());
        holder.tvStatusJadwal.setText(data.getStatusJadwal());
        holder.tvMataKuliah.setText(data.getMataKuliah());
        holder.tvRuangan.setText(data.getRuangan());
        holder.tvKom.setText(data.getKom());
        holder.tvDosen.setText(data.getDosen());
        holder.tvSks.setText(data.getSks());

        holder.bGanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GantiJadwalActivity.class);
                intent.putExtra("ID", data.getMataKuliah());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listJadwal.size();
    }
}
