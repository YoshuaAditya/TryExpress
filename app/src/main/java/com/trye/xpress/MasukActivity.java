package com.trye.xpress;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;

public class MasukActivity extends Activity {

    SharedPreferences appSharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        appSharedPrefs= PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());

        final EditText editTextUsername=findViewById(R.id.editTextUsername);
        final EditText editTextPassword=findViewById(R.id.editTextPassword);

        Button clear = findViewById(R.id.buttonClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPassword.setText("");
                editTextUsername.setText("");
            }
        });

        Button masuk = findViewById(R.id.buttonMasuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password=editTextPassword.getText().toString();
                String username=editTextUsername.getText().toString();
                Firebase firebase=new Firebase(MasukActivity.this);
                firebase.masuk(username,Objek.enkripsiMD5(password));
            }
        });

        TextView daftar = findViewById(R.id.daftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MasukActivity.this, DaftarActivity.class));
            }
        });

    }


}
