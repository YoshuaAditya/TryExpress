package com.trye.xpress;

import android.telephony.PhoneNumberUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class User extends BaseObservable {
    String nama;
    String email;
    String noTelp;
    String username;
    String password;

    public User(String nama, String noTelp, String email, String username, String password) {
        this.nama = nama;
        this.noTelp = noTelp;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Bindable
    public String getNama() {
        return nama;
    }
    @Bindable
    public String getNoTelp() {
        String formattedNumber = PhoneNumberUtils.formatNumber(noTelp);
        return formattedNumber;
    }
    @Bindable
    public String getEmail() {
        return email;
    }
    @Bindable
    public String getUsername() {
        return username;
    }
    @Bindable
    public String getPassword() {
        return password;
    }


}
