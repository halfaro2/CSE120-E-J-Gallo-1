package com.example.ejgallodts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference users;
    ProgressBar progBar;

    EditText editPassword, editEmail;
    Button signupButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progBar = findViewById(R.id.progressBar);
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        editEmail = (EditText) findViewById(R.id.userEmail);
        editPassword = (EditText) findViewById(R.id.password);

        signupButton = (Button) findViewById(R.id.signupbutton);
        loginButton = (Button) findViewById(R.id.loginbutton);

        signupButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            progBar.setVisibility(View.VISIBLE);

            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please Enter Credentials:", Toast.LENGTH_SHORT);
            } else if (email.isEmpty()) {
                editEmail.setError("Please Enter Email:");
                editEmail.requestFocus();
                progBar.setVisibility(View.INVISIBLE);
            } else if (password.isEmpty()) {
                editPassword.setError("Please Enter Password:");
                editPassword.requestFocus();
                progBar.setVisibility(View.INVISIBLE);
            } else if (!email.isEmpty() && !password.isEmpty()) {

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            progBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Sign Up Unsuccessful", Toast.LENGTH_SHORT);
                        } else {
                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            progBar.setVisibility(View.INVISIBLE);
                            startActivity(i);
                        }
                    }
                });
            }
        }
    });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progBar.setVisibility(View.VISIBLE);

                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    progBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Please Fill In All Fields", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                                progBar.setVisibility(View.INVISIBLE);
                                startActivity(i);
                            } else {
                                progBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

}

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null) {
            return;
        }


    }

}