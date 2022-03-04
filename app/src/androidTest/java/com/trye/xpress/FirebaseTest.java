package com.trye.xpress;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sdsmdg.tastytoast.TastyToast;

import junit.framework.TestCase;

public class FirebaseTest extends TestCase {

    //test email yg belum diverifikasi user
    public void testMasuk() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("test@gmail.com", Objek.enkripsiMD5("test"))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            assertFalse(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified());
                        }
                    }
                });
    }
}