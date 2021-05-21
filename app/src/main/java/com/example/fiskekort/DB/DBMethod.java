package com.example.fiskekort.DB;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class DBMethod {
    private static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final String email;
    private final String password;
    private final String name;
    private final String phoneNumber;
    private final Context context;
    private final FirebaseUser fUser;

    public DBMethod(String email, String password, String name, String phoneNumber, Context context, FirebaseUser fUser) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.context = context;
        this.fUser = fUser;
    }


}
