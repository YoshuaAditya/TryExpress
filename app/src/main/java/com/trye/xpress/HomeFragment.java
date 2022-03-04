package com.trye.xpress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.trye.xpress.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment  {


    public HomeFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        FragmentHomeBinding binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        View view = binding.getRoot();

        binding.setUser(Objek.user);
        Objek.user.username=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        binding.keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase firebase=new Firebase(getContext());
                firebase.keluar();
            }
        });

        return view;
    }

}
