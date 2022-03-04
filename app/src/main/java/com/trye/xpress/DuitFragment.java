package com.trye.xpress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class DuitFragment extends Fragment  {


    public DuitFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_duit, container, false);

        EditText editTextLokasi=view.findViewById(R.id.editTextLokasi);
        EditText editTextTujuan=view.findViewById(R.id.editTextTujuan);
        EditText editTextBerat=view.findViewById(R.id.editTextBerat);
        TextView textViewCek=view.findViewById(R.id.cekTarif);
        TextView textViewBerat=view.findViewById(R.id.angkaBerat);
        TextView textViewTarif=view.findViewById(R.id.angkaTarif);
        textViewCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String berat=""+editTextBerat.getText().toString();
                textViewBerat.setText(berat);
                String tarif="Rp. "+ Integer.decode(editTextBerat.getText().toString())*10000;
                textViewTarif.setText(tarif);

                Resi resi=new Resi(editTextLokasi.getText().toString(),berat,editTextTujuan.getText().toString(),tarif);
                Objek.resi=resi;
                Firebase firebase=new Firebase(getContext());
                firebase.retrofitPut(resi);

            }
        });

        return view;
    }

}
