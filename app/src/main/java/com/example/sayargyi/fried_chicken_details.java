package com.example.sayargyi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class fried_chicken_details extends AppCompatActivity {

    private ImageView plusBtn, minusBtn;
    private TextView itemChoice;
    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fried_chicken_details);

        plusBtn = findViewById(R.id.imageView10);
        minusBtn = findViewById(R.id.imageView11);
        itemChoice = findViewById(R.id.textView13);

        plusBtn.setOnClickListener(v -> {
            counter++;
            itemChoice.setText(Integer.toString(counter));
        });

        minusBtn.setOnClickListener(v -> {
            if (counter > 0)
            {
                counter--;
                itemChoice.setText(Integer.toString(counter));
            }
        });
    }
}