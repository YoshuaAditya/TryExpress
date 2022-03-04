package com.trye.xpress;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sdsmdg.tastytoast.TastyToast;
import com.trye.xpress.databinding.FragmentLokasiBinding;

public class LokasiFragment extends Fragment  {


    public LokasiFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        FragmentLokasiBinding binding= DataBindingUtil.inflate(inflater,R.layout.fragment_lokasi, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        binding.setResi(Objek.resi);

        binding.nomorResi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase firebase=new Firebase(getContext());
                firebase.retrofitGetResi(binding.pertama,binding.kedua);
            }
        });

        binding.cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MapsActivity.class));
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("http://maps.google.com/maps?saddr="+-7.956664951555159 +","+112.61859310706939+"&daddr="+-7.976914043894019+","+112.62378397810541));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addCategory(Intent.CATEGORY_LAUNCHER );
//                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
//                startActivity(intent);
            }
        });

        return view;
    }

}
