package com.trye.xpress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends Activity {

    private EditText editTextUsername, editTextNoTelp,
            editTextNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextUsername.setText(Objek.user.username);
        editTextNama = findViewById(R.id.editTextNama);
        editTextNama.setText(Objek.user.nama);
        editTextNoTelp = findViewById(R.id.editTextNoTelp);
        editTextNoTelp.setText(Objek.user.noTelp);

        Button daftar = findViewById(R.id.buttonDaftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase firebase=new Firebase(UpdateActivity.this);
                firebase.update(editTextUsername.getText().toString());
                Objek.user.username=editTextUsername.getText().toString();
                Objek.user.noTelp=editTextNoTelp.getText().toString();
                Objek.user.nama=editTextNama.getText().toString();
                firebase.retrofitPutUser(Objek.user);
                Intent i = new Intent(UpdateActivity.this, HomeActivity.class);        // Specify any activity here e.g. home or splash or login etc
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("EXIT", true);
                startActivity(i);
            }
        });

    }

}
