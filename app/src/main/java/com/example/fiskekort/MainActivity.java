package com.example.fiskekort;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPassword;
    private TextView registration;
    private Button button;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

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

    }//Onclicklistner =)

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.loginButton) {
            login();
        } else if (v.getId() == R.id.textNoAccount) {
            registration();
        }

        /*
        switch (v.getId()) {
            // this is the buttons for the login page
            case R.id.loginButton:
                login();
                break;

            case R.id.textNoAccount:
                registration();
                break;
                }
         */


//login method
    }

    private void login() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (email.isEmpty()) {
            errorMessage(editTextEmail, "please provide valid email");

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage(editTextEmail, "Please provide valid email");
        } else if (password.isEmpty()) {

            errorMessage(editTextPassword, "Please provide Password");
        } else if (password.length() < 8) {
            errorMessage(editTextPassword, "min password length should be 8 characters");
        } else {

            //progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                   /*
                    Toast.makeText(MainActivity.this, "you have successful login!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, Fishing_card_activity.class));
                    progressBar.setVisibility(View.INVISIBLE);
                    finish();
                    */
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (firebaseUser != null) {
                        if (firebaseUser.isEmailVerified()) {
                            startActivity(new Intent(this, Fishing_card_activity.class));
                            finish();
                        } else {
                            firebaseUser.sendEmailVerification();
                            createToast("Check your inputEmail to verify your account!");
                        }
                    }

                } else {
                    try {
                        throw Objects.requireNonNull(task.getException());
                    }
                    // if user enters wrong email.
                    catch (FirebaseAuthInvalidUserException invalidEmail) {

                        createToast("Check the Email or register yourself.");
                    }
                    // if user enters wrong password.
                    catch (FirebaseAuthInvalidCredentialsException wrongPassword) {
                        createToast("Wrong Password! Try again.");

                    } catch (Exception e) {

                        createToast("Failed to login! Please check your Email and Password");
                    }
                }

            });
        }
    }//method to pass over to el registration

    public void registration() {
        // register new user
        Intent registration = new Intent(this, Register.class);
        registration.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(registration, 0);
    }


    public void errorMessage(EditText editText, String text) {
        editText.setError(text);
        editText.requestFocus();
    }

    public void createToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}