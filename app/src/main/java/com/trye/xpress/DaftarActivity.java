package com.trye.xpress;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DaftarActivity extends Activity {

    private EditText editTextUsername, editTextEmail, editTextUmur, editTextNoTelp,
            editTextNama, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextNama = findViewById(R.id.editTextNama);
        editTextNoTelp = findViewById(R.id.editTextNoTelp);

        Button daftar = findViewById(R.id.buttonDaftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User(editTextNama.getText().toString(),
                        editTextNoTelp.getText().toString(), editTextEmail.getText().toString(), editTextUsername.getText().toString(),
                        Objek.enkripsiMD5(editTextPassword.getText().toString()));
                Firebase firebase=new Firebase(DaftarActivity.this);
                firebase.daftar(user);
            }
        });

    }

}
