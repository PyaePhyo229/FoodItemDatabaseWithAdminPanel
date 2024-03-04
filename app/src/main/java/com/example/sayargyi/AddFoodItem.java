package com.example.sayargyi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddFoodItem extends AppCompatActivity {

    private EditText editTextName, editTextDescription, editTextPrice;
    private Button buttonAdd, homeBtn, dashboardBtn;
    private DatabaseHelper1 dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);

        dbHelper = new DatabaseHelper1(this);

        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        buttonAdd = findViewById(R.id.buttonAdd);
        homeBtn = findViewById(R.id.buttonHome);
        dashboardBtn = findViewById(R.id.buttonAdmin);



        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFoodItem();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the HomeActivity
                Intent intent = new Intent(AddFoodItem.this, home.class);
                startActivity(intent);
            }
        });

        dashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFoodItem.this, AdminDashboardActivity.class);
                startActivity(intent);
            }
        });



    }

    private void addFoodItem() {
        String name = editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        double price = Double.parseDouble(editTextPrice.getText().toString().trim());

        FoodItem foodItem = new FoodItem(name, description, price);
        dbHelper.addFoodItem(foodItem);

        Toast.makeText(this, "Food item added successfully", Toast.LENGTH_SHORT).show();

        // Clear input fields
        editTextName.setText("");
        editTextDescription.setText("");
        editTextPrice.setText("");
    }





}

