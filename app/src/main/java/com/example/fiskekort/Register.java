package com.example.fiskekort;


import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiskekort.DB.DBMethod;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.example.fiskekort.R;

import java.util.Objects;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextfullname, editTextEmail, editTextPassword, editTextPhone;
    private Button registerButton;
    private TextView returnToLogin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private DBMethod dbMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextfullname = findViewById(R.id.textName);
        editTextEmail = findViewById(R.id.textEmail);
        editTextPassword = findViewById(R.id.passWordText);
        editTextPhone = findViewById(R.id.phoneNr);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
        returnToLogin = findViewById(R.id.textReturnToLogin);
        returnToLogin.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.registerButton:
                registerUser();
                break;
            case R.id.textReturnToLogin:
                finish();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextfullname.getText().toString().trim();
        String phoneNumber = editTextPhone.getText().toString().trim();

        if (name.isEmpty()) {
            errorMessage(editTextfullname, "please provide you full name");
            return;
        }

        if (email.isEmpty()) {

            errorMessage(editTextEmail, "please provide valid email");
            return;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage(editTextEmail, "Please provide valid email");
            return;
        }

        if (password.isEmpty()) {
            errorMessage(editTextPassword, "Please provide Password");
            return;
        }

        if (password.length() < 8) {
            errorMessage(editTextPassword, "min password length should be 8 characters");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();

                        if (fUser != null) {
                            User user = new User(name, email, password, phoneNumber);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(fUser.getUid())
                                    .setValue(user).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    createToast("User has been registered successfully", Register.this);
                                    finish();
                                    progressBar.setVisibility(View.INVISIBLE);
                                } else {
                                    createToast("Failed to register! Try again", Register.this);
                                }
                            });
                        }
                    } else {
                        try {
                            throw Objects.requireNonNull(task.getException());
                        } catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                            createToast("Wrong Password! Try again", Register.this);

                        } catch (FirebaseAuthUserCollisionException existEmail) {

                            createToast("This Email already exists", Register.this);


                        } catch (Exception e) {
                            createToast("Failed to register! Try again", Register.this);

                        }

                    }
                });
    }

    public void createToast(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public void errorMessage(EditText editText, String text) {
        editText.setError(text);
        editText.requestFocus();
    }
}


