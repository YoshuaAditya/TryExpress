package com.trye.xpress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends Activity {

    int delay = 2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase firebase=new Firebase(this);
        FirebaseUser firebaseUser=firebase.checkUser();
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(firebaseUser==null||!firebaseUser.isEmailVerified())
                    startActivity(new Intent(SplashScreenActivity.this, IntroActivity.class));
                else{
                    firebase.retrofitGet();
                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                }
                onBackPressed();
            }
        }, delay * 1000);
    }

}
