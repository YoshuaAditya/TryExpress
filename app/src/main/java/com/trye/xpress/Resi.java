package com.trye.xpress;

import android.telephony.PhoneNumberUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Resi extends BaseObservable {
    String lokasi;
    String tujuan;
    String berat;
    String tanggal;
    String status;
    String tarif;

    public Resi(String lokasi, String berat, String tujuan, String tarif) {
        this.lokasi = lokasi;
        this.berat = berat;
        this.tujuan = tujuan;
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault());
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formattedDate = df.format(c);
        this.tanggal = formattedDate;
        this.status = "Dalam Pengiriman";
        this.tarif = tarif;
    }

    @Bindable
    public String getLokasi() {
        return lokasi;
    }
    @Bindable
    public String getBerat() {
        return berat;
    }
    @Bindable
    public String getTujuan() {
        return tujuan;
    }
    @Bindable
    public String getTanggal() {
        return tanggal;
    }
    @Bindable
    public String getStatus() {
        return status;
    }
    @Bindable
    public String getTarif() {
        return tarif;
    }

    public String buatNoResi(){
        return lokasi.concat(tujuan);
    }

}
