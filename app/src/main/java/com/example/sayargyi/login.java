package com.example.sayargyi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button signInButton;
    Button signUpPageButton;
    DatabaseHelper dbHelper; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signInButton = findViewById(R.id.sign_in);
        signUpPageButton = findViewById(R.id.sign_up_page);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();

                // Check if the username and password match in the database
                if (enteredUsername.equals("admin") && enteredPassword.equals("1234")) {
                    // If the username and password are admin and 1234, redirect to admin dashboard
                    Intent intent = new Intent(login.this, AdminDashboardActivity.class);
                    startActivity(intent);
                } else if (dbHelper.checkUserCredentials(enteredUsername, enteredPassword)) {
                    // If match, login successful
                    Intent intent = new Intent(login.this, home.class);
                    startActivity(intent);
                } else {
                    // If not match, login failed
                    Toast.makeText(login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }
}
