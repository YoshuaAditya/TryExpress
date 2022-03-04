package com.trye.xpress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;


public class HomeActivity extends FragmentActivity {

    FrameLayout frameLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frameLayout = findViewById(R.id.frameLayout);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        Fragment fragment = new HomeFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
// get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = new HomeFragment();
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new HomeFragment();
                        tab.setIcon(getResources().getDrawable(R.drawable.rumah2));
                        break;
                    case 1:
                        fragment = new DuitFragment();
                        tab.setIcon(getResources().getDrawable(R.drawable.duit2));
                        break;
                    case 2:
                        fragment = new LokasiFragment();
                        tab.setIcon(getResources().getDrawable(R.drawable.lokasi2));
                        break;
                    case 3:
                        fragment = new ProfilFragment();
                        tab.setIcon(getResources().getDrawable(R.drawable.user2));
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(getResources().getDrawable(R.drawable.rumah));
                        break;
                    case 1:
                        tab.setIcon(getResources().getDrawable(R.drawable.duit));
                        break;
                    case 2:
                        tab.setIcon(getResources().getDrawable(R.drawable.lokasi));
                        break;
                    case 3:
                        tab.setIcon(getResources().getDrawable(R.drawable.user));
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
