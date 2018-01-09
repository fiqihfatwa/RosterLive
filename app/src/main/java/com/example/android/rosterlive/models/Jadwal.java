package com.example.android.rosterlive.models;

/**
 * Created by fiqh on 13/12/17.
 */

public class Jadwal {

    private String jam;
    private String statusJadwal;
    private String status;
    private String mataKuliah;
    private String ruangan;
    private String kom;
    private String dosen;
    private String sks;

    public Jadwal(String jam, String statusJadwal, String status, String mataKuliah, String ruangan, String kom, String dosen, String sks){
        this.jam = jam;
        this.statusJadwal = statusJadwal;
        this.mataKuliah = mataKuliah;
        this.ruangan = ruangan;
        this.kom = kom;
        this.dosen = dosen;
        this.sks = sks;
        this.status = status;
    }

    public Jadwal(String jam, String mataKuliah, String ruangan, String kom, String dosen, String sks){
        this.jam = jam;
        this.mataKuliah = mataKuliah;
        this.ruangan = ruangan;
        this.kom = kom;
        this.dosen = dosen;
        this.sks = sks;
    }

    public  String getStatus(){return status;}
    public void setStatus(String status){ this.status = status;}

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getStatusJadwal() {
        return statusJadwal;
    }

    public void setStatusJadwal(String statusJadwal) {
        this.statusJadwal = statusJadwal;
    }

    public String getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getKom() {
        return kom;
    }

    public void setKom(String kom) {
        this.kom = kom;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }
}
