package com.example.fiskekort.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fiskekort.Fishing_card_activity;
import com.example.fiskekort.R;
import com.example.fiskekort.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private EditText editTextEmail, editTextPassword;
    private TextView registration;
    private Button button;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        editTextEmail = root.findViewById(R.id.loginEmail);
        editTextPassword =root. findViewById(R.id.loginPassword);
        button = root.findViewById(R.id.loginButton);
        button.setOnClickListener(this);
        registration = root.findViewById(R.id.textNoAccount);
        registration.setOnClickListener(this);
        progressBar = root.findViewById(R.id.progressBarLogin);
        mAuth = FirebaseAuth.getInstance();
        return root;
    }
    //Onclicklistner =)

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
                    Toast.makeText(getContext(), "you have successful login!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent( getContext(), Fishing_card_activity.class));
                    progressBar.setVisibility(View.INVISIBLE);


                } else {
                    Toast.makeText(getContext(), "could not login check your login details", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        });
    }//method to pass over to el registration

   public void registration() {
       // register new user
       Intent registration = new Intent(getContext(), Register.class);
       registration.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
       startActivity(registration);
   }
}
