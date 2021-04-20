package com.example.fiskekort;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextfullname, editTextEmail, editTextPassword, editTextPhone;
    private Button registerButton;
    private TextView returnToLogin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextfullname = findViewById(R.id.textName);
        editTextEmail = findViewById(R.id.textEmail);
        editTextPassword = findViewById(R.id.passWordText);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
        returnToLogin = findViewById(R.id.textReturnToLogin);
        returnToLogin.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
    }

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
        String playerName = editTextfullname.getText().toString().trim();

        if (playerName.isEmpty()) {
            editTextfullname.setError("please provide you full name");
            editTextfullname.requestFocus();
            return;
        }

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
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User has been register successfully", Toast.LENGTH_LONG).show();
                            finish();
                            progressBar.setVisibility(View.INVISIBLE);
                        }else {
                            Toast.makeText(Register.this,"Failed to register try again!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                    }
                }
    });
}


}


