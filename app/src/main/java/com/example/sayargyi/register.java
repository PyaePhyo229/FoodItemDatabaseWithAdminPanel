package com.example.sayargyi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    Button signUpButton;
    Button signInPageButton;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUpButton = findViewById(R.id.sign_up);
        signInPageButton = findViewById(R.id.sign_in_page);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = username.getText().toString().trim();
                String enteredEmail = email.getText().toString().trim();
                String enteredPassword = password.getText().toString().trim();

                if (!enteredUsername.isEmpty() && !enteredEmail.isEmpty() && !enteredPassword.isEmpty()) {
                    // Check if the user already exists
                    if (!dbHelper.checkUserExists(enteredUsername)) {
                        dbHelper.addUser(enteredUsername, enteredEmail, enteredPassword);
                        Toast.makeText(register.this, "User signed up successfully", Toast.LENGTH_SHORT).show();
                        // Optionally, you can redirect the user to the login page here
                    } else {
                        Toast.makeText(register.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signInPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });
    }
}
