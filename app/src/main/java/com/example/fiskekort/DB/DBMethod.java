package com.example.fiskekort.DB;

import android.content.Context;

import android.widget.Toast;

import com.example.fiskekort.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class DBMethod {
    private static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Context context;
    private FirebaseUser fUser;

    public DBMethod(String email, String password, String name, String phoneNumber, Context context, FirebaseUser fUser) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.context = context;
        this.fUser = fUser;
    }


}
