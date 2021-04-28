package com.example.fiskekort;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiskekort.license.Licence;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.satouf.fiskekort.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPassword;
    private TextView registration;
    private Button button, license;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = findViewById(R.id.loginEmail);
        editTextPassword = findViewById(R.id.loginPassword);
        button = findViewById(R.id.loginButton);
        button.setOnClickListener(this);
        registration = findViewById(R.id.textNoAccount);
        registration.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBarLogin);
        mAuth = FirebaseAuth.getInstance();

        testLicense();
    }//Onclicklistner =)

    private void testLicense() {
        license = findViewById(R.id.main_open_license);
        license.setOnClickListener(v -> {
            Intent intent = new Intent(this, Licence.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // this is the buttons for the login page
            case R.id.loginButton:
                login();
                break;

            case R.id.textNoAccount:
                registration();
                break;

        }

//login method
    }

    private void testLicense() {
        license = findViewById(R.id.main_open_license);
        license.setOnClickListener(v -> {
            Intent intent = new Intent(this, Licence.class);
            startActivity(intent);
        });
    }

    private void login() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (email.isEmpty()) {
            editTextEmail.setError("please provide valid email");
            editTextEmail.requestFocus();
            return;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Please provide Password");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 8) {
            editTextPassword.setError("min password length should be 8 characters");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()) {
                    Toast.makeText(MainActivity.this, "you have successful login!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, Fishing_card_activity.class));
                    progressBar.setVisibility(View.INVISIBLE);


                } else {
                    Toast.makeText(MainActivity.this, "could not login check your login details", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        });
    }//method to pass over to el registration

    public void registration() {
        // register new user
        Intent registration = new Intent(this, Register.class);
        registration.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(registration, 0);


    }


}