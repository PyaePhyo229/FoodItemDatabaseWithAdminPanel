package com.example.sayargyi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDashboardActivity extends AppCompatActivity {
    private Button adminBtn, orderBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        adminBtn = findViewById(R.id.buttonAdmin);
        orderBtn = findViewById(R.id.buttonOrder);
        homeBtn = findViewById(R.id.buttonToHome);

        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the admin home activity
                Intent intent = new Intent(AdminDashboardActivity.this, AddFoodItem.class);
                startActivity(intent);
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the admin home activity
                Intent intent = new Intent(AdminDashboardActivity.this, Order.class);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, home.class);
                startActivity(intent);
            }
        });
    }
}