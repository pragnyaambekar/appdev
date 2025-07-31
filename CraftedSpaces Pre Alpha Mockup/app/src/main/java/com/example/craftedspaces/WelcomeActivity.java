package com.example.craftedspaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    MaterialButton btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Link button from XML
        btnStart = findViewById(R.id.btnStart);

        // Set click listener to move to Login screen
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Optional: Prevent back press to return to Welcome screen
            }
        });
    }
}
