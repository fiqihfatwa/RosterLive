
package com.example.android.rosterlive.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "jam",
    "hari",
    "tanggal",
    "status_jadwal",
    "mata_kuliah",
    "ruangan",
    "kom",
    "dosen",
    "sks"
})
public class JadwalResponse {

    @JsonProperty("jam")
    private String jam;
    @JsonProperty("hari")
    private String hari;
    @JsonProperty("tanggal")
    private int tanggal;
    @JsonProperty("status_jadwal")
    private String statusJadwal;
    @JsonProperty("mata_kuliah")
    private String mataKuliah;
    @JsonProperty("ruangan")
    private String ruangan;
    @JsonProperty("kom")
    private String kom;
    @JsonProperty("dosen")
    private String dosen;
    @JsonProperty("sks")
    private String sks;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("jam")
    public String getJam() {
        return jam;
    }

    @JsonProperty("jam")
    public void setJam(String jam) {
        this.jam = jam;
    }

    @JsonProperty("hari")
    public String getHari() {
        return hari;
    }

    @JsonProperty("hari")
    public void setHari(String hari) {
        this.hari = hari;
    }

    @JsonProperty("tanggal")
    public int getTanggal() {
        return tanggal;
    }

    @JsonProperty("tanggal")
    public void setTanggal(int tanggal) {
        this.tanggal = tanggal;
    }

    @JsonProperty("status_jadwal")
    public String getStatusJadwal() {
        return statusJadwal;
    }

    @JsonProperty("status_jadwal")
    public void setStatusJadwal(String statusJadwal) {
        this.statusJadwal = statusJadwal;
    }

    @JsonProperty("mata_kuliah")
    public String getMataKuliah() {
        return mataKuliah;
    }

    @JsonProperty("mata_kuliah")
    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    @JsonProperty("ruangan")
    public String getRuangan() {
        return ruangan;
    }

    @JsonProperty("ruangan")
    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    @JsonProperty("kom")
    public String getKom() {
        return kom;
    }

    @JsonProperty("kom")
    public void setKom(String kom) {
        this.kom = kom;
    }

    @JsonProperty("dosen")
    public String getDosen() {
        return dosen;
    }

    @JsonProperty("dosen")
    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    @JsonProperty("sks")
    public String getSks() {
        return sks;
    }

    @JsonProperty("sks")
    public void setSks(String sks) {
        this.sks = sks;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
