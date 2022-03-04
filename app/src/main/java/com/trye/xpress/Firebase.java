package com.trye.xpress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sdsmdg.tastytoast.TastyToast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Firebase {

    //classpath 'com.google.gms:google-services:4.3.10' di  project
    //apply plugin: 'com.google.gms.google-services' di module
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private final String TAG = "Firebase";
    private Context context;

    public Firebase(Context context) {
        this.context = context;
    }

    public FirebaseUser checkUser() {
        FirebaseApp.initializeApp(context);
        mAuth = FirebaseAuth.getInstance();
        return mAuth.getCurrentUser();
    }

    public void daftar(User user) {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://tryexpress-1d6b6-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        mAuth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            TastyToast.makeText(context, "Cek email untuk aktivasi", TastyToast.LENGTH_LONG, TastyToast.SUCCESS).show();
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(user.username)
                                    .build();
                            firebaseUser.updateProfile(profileUpdates);
                            String userId = firebaseUser.getUid();
                            mDatabase.child("users").child(userId).setValue(user);
                            firebaseUser.sendEmailVerification();
                            ((Activity) context).onBackPressed();
                        } else {
                            Log.e(TAG, "createUserWithEmail:failure", task.getException());
                            TastyToast.makeText(context, "Error", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();
                        }
                    }
                });
    }

    public void masuk(String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://tryexpress-1d6b6-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        if (email.equals("") || password.equals(""))
            TastyToast.makeText(context, "Isi semua data", TastyToast.LENGTH_LONG, TastyToast.WARNING).show();
        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                if (firebaseUser.isEmailVerified()) {
                                    context.startActivity(new Intent(context, HomeActivity.class));
                                    retrofitGet();
                                    TastyToast.makeText(context, "Selamat datang " + firebaseUser.getDisplayName(), TastyToast.LENGTH_LONG, TastyToast.SUCCESS).show();
                                } else {
                                    TastyToast.makeText(context, "Harap verifikasi akun melalui email", TastyToast.LENGTH_LONG, TastyToast.WARNING).show();
                                }
                            } else {
                                Log.e(TAG, "signin:failure", task.getException());
                                TastyToast.makeText(context, task.getException().getMessage(), TastyToast.LENGTH_LONG, TastyToast.ERROR).show();
                            }
                        }
                    });
        }
    }

    public void keluar() {
        Intent i = new Intent(context, MasukActivity.class);        // Specify any activity here e.g. home or splash or login etc
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("EXIT", true);
        context.startActivity(i);
        FirebaseAuth.getInstance().signOut();
    }

    public void update(String displayName){
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .build();
        firebaseUser.updateProfile(profileUpdates);
    }

    public void retrofitGet() {
        String url = "https://tryexpress-1d6b6-default-rtdb.asia-southeast1.firebasedatabase.app/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)//url of firebase app
                .addConverterFactory(GsonConverterFactory.create())//use for convert JSON file into object
                .build();
        Api api = retrofit.create(Api.class);
        Call<HashMap<String, User>> call2 = api.getData();
        call2.enqueue(new Callback<HashMap<String, User>>() {
            @Override
            public void onResponse(Call<HashMap<String, User>> call, Response<HashMap<String, User>> response) {
                Objek.user.nama = response.body().get(mAuth.getCurrentUser().getUid()).nama;
                Objek.user.email = response.body().get(mAuth.getCurrentUser().getUid()).email;
                Objek.user.noTelp = response.body().get(mAuth.getCurrentUser().getUid()).noTelp;
                Objek.user.username = response.body().get(mAuth.getCurrentUser().getUid()).username;
                Objek.user.password = response.body().get(mAuth.getCurrentUser().getUid()).password;
            }

            @Override
            public void onFailure(Call<HashMap<String, User>> call, Throwable t) {
                Log.e("Error", "error");
            }
        });
    }

    public void retrofitGetResi(TextView v1, TextView v2) {
        mAuth=FirebaseAuth.getInstance();
        String url = "https://tryexpress-1d6b6-default-rtdb.asia-southeast1.firebasedatabase.app/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)//url of firebase app
                .addConverterFactory(GsonConverterFactory.create())//use for convert JSON file into object
                .build();
        Api api = retrofit.create(Api.class);
        Call<HashMap<String, Resi>> call2 = api.getResi();
        call2.enqueue(new Callback<HashMap<String, Resi>>() {
            @Override
            public void onResponse(Call<HashMap<String, Resi>> call, Response<HashMap<String, Resi>> response) {
                Resi resi=new Resi(
                        response.body().get(mAuth.getCurrentUser().getUid()).lokasi,
                        response.body().get(mAuth.getCurrentUser().getUid()).berat,
                        response.body().get(mAuth.getCurrentUser().getUid()).tujuan,
                        response.body().get(mAuth.getCurrentUser().getUid()).tarif);
                Objek.resi=resi;
                String teks="- Paket dikirim dari "+ Objek.resi.lokasi;
                v1.setText(teks);
                teks="- Paket diterima di "+ Objek.resi.tujuan;
                v2.setText(teks);
                TastyToast.makeText(context,"Nomor resi: "+ Objek.resi.buatNoResi(), TastyToast.LENGTH_LONG, TastyToast.INFO).show();
            }

            @Override
            public void onFailure(Call<HashMap<String, Resi>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    public void retrofitPutUser(User user) {
        String url = "https://tryexpress-1d6b6-default-rtdb.asia-southeast1.firebasedatabase.app/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)//url of firebase app
                .addConverterFactory(GsonConverterFactory.create())//use for convert JSON file into object
                .build();
        Api api = retrofit.create(Api.class);
        Call<User> call2 = api.putUser(mAuth.getCurrentUser().getUid(),user);
        call2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                TastyToast.makeText(context, "Sukses", TastyToast.LENGTH_LONG, TastyToast.SUCCESS).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error", "error");
            }
        });
    }


    public void retrofitPut(Resi resi) {
        String url = "https://tryexpress-1d6b6-default-rtdb.asia-southeast1.firebasedatabase.app/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)//url of firebase app
                .addConverterFactory(GsonConverterFactory.create())//use for convert JSON file into object
                .build();
        Api api = retrofit.create(Api.class);
        Call<Resi> call2 = api.putResi(FirebaseAuth.getInstance().getUid(),resi);
        call2.enqueue(new Callback<Resi>() {
            @Override
            public void onResponse(Call<Resi> call, Response<Resi> response) {
                TastyToast.makeText(context, "Sukses", TastyToast.LENGTH_LONG, TastyToast.SUCCESS).show();
            }

            @Override
            public void onFailure(Call<Resi> call, Throwable t) {
                Log.e("Error", "error");
            }
        });
    }
}
