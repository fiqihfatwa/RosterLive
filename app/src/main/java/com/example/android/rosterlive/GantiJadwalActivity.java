package com.example.android.rosterlive;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GantiJadwalActivity extends AppCompatActivity {


    @BindView(R.id.toolbar_ganti_jadwal)
    Toolbar toolbarJadwalGanti;

    @BindView(R.id.et_tanggal_ganti)
    EditText etTanggal;

    @BindView(R.id.spinner_jam_ganti)
    Spinner spinnerJamGanti;

    @BindView(R.id.spinner_ruangan_ganti)
    Spinner spinnerRuanganGanti;

    @BindView(R.id.b_save_jadwal_ganti)
    Button bSaveJadwalGanti;
    List<String> listJam, listRuangan;
    String tanggalGanti, jamGanti, ruanganGanti;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_jadwal);
        ButterKnife.bind(this);

        this.setSupportActionBar(toolbarJadwalGanti);
        this.setTitle("Ganti Jadwal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Calendar calendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        etTanggal.setText("" + dateFormatter.format(calendar.getTime()));

        listJam = new ArrayList<>();
        listJam.add("08:00");
        listJam.add("08:50");
        listJam.add("09:40");
        listJam.add("10:30");
        listJam.add("11:20");
        listJam.add("12:10");
        listJam.add("13:00");
        listJam.add("13:50");
        listJam.add("14:40");

        ArrayAdapter<String> jamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listJam);
        jamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJamGanti.setAdapter(jamAdapter);

        spinnerJamGanti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jamGanti = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listRuangan = new ArrayList<>();
        listRuangan.add("101");
        listRuangan.add("102");
        listRuangan.add("103");
        listRuangan.add("104");
        listRuangan.add("105");
        listRuangan.add("106");

        ArrayAdapter<String> ruanganAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listRuangan);
        ruanganAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRuanganGanti.setAdapter(ruanganAdapter);

        spinnerRuanganGanti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ruanganGanti = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @OnClick(R.id.et_tanggal_ganti)
    public void etTanggalGantiClick() {
        showDateDialog();
    }


    private void showDateDialog() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                etTanggal.setText("" + dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    @OnClick(R.id.b_save_jadwal_ganti)
    public void saveJadwalGantiClick() {
        tanggalGanti = etTanggal.getText().toString();
        Toast.makeText(this, tanggalGanti + " " + jamGanti + " " + ruanganGanti, Toast.LENGTH_SHORT).show();
    }
}
