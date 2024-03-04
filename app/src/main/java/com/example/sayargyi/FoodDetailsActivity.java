package com.example.sayargyi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FoodDetailsActivity extends AppCompatActivity {

    private TextView textViewFoodName, textViewDescription, textViewPrice, textViewQuantity;
    private Button buttonMinus, buttonPlus, buttonAddToCart;

    private int quantity = 1;

    DatabaseHelper1 databaseHelper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        // Initialize views
        textViewFoodName = findViewById(R.id.textViewFoodName);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewQuantity = findViewById(R.id.textViewQuantity);

        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonAddToCart = findViewById(R.id.buttonAddToCart);

        // Initialize DatabaseHelper
        databaseHelper1 = new DatabaseHelper1(this);

        // Get selected food item details from intent
        String foodName = getIntent().getStringExtra("foodName");
        String description = getIntent().getStringExtra("description");
        double price = getIntent().getDoubleExtra("price", 0.0);

        // Set details to TextViews
        textViewFoodName.setText(foodName);
        textViewDescription.setText(description);
        textViewPrice.setText(String.format("Price: $%.2f", price));

        // Set initial quantity
        textViewQuantity.setText(String.valueOf(quantity));

        // Minus button click listener
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    textViewQuantity.setText(String.valueOf(quantity));
                }
            }
        });

        // Plus button click listener
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                textViewQuantity.setText(String.valueOf(quantity));
            }
        });

        // Add to cart button click listener
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract the numeric part of the price string and parse it into a double
                String priceText = textViewPrice.getText().toString();
                String numericPrice = priceText.replace("Price: $", "");
                double itemPrice = Double.parseDouble(numericPrice);

                // Retrieve item details from UI elements
                String itemName = textViewFoodName.getText().toString();
                // Assuming description and price are not dynamic in this example
                String itemDescription = textViewDescription.getText().toString();
                int quantity = Integer.parseInt(textViewQuantity.getText().toString());

                // Create a new FoodItem object
                FoodItem item = new FoodItem(-1, itemName, itemDescription, itemPrice); // -1 is placeholder for ID

                // Add the item to the cart
                databaseHelper1.addItemToCart(item, quantity);

                // Show a toast message to indicate that the item has been added to the cart
                Toast.makeText(FoodDetailsActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView buttonBackToHome = findViewById(R.id.buttonBackToHome);
        buttonBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finish the activity and return to the previous activity (home page)
            }
        });
    }
}
