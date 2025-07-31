package com.example.craftedspaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

public class DashboardActivity extends AppCompatActivity {

    TextView welcomeText, roleText, locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        View mainContainer = findViewById(R.id.main_container);
        if (mainContainer != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainContainer, (v, insets) -> insets);
        }

        // ✅ Bind views
        welcomeText = findViewById(R.id.welcomeText);
        roleText = findViewById(R.id.roleText);
        locationText = findViewById(R.id.locationText);

        Button btnProjects = findViewById(R.id.btnProjects);
        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnLogout = findViewById(R.id.btnLogout);

        // ✅ Retrieve data from LoginActivity
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String userType = intent.getStringExtra("userType");
        double latitude = intent.getDoubleExtra("latitude", 0.0);
        double longitude = intent.getDoubleExtra("longitude", 0.0);

        // ✅ Set values to TextViews
        welcomeText.setText("Welcome, " + username + "!");
        roleText.setText("Role: " + userType);
        locationText.setText("Location: " + latitude + ", " + longitude);

        // ✅ Button actions
        btnProjects.setOnClickListener(view -> {
            Intent projectIntent = new Intent(DashboardActivity.this, ProjectsActivity.class);
            startActivity(projectIntent);
        });

        btnProfile.setOnClickListener(view -> {
            // Future enhancement: Add profile activity here
        });

        btnLogout.setOnClickListener(view -> {
            Intent logoutIntent = new Intent(DashboardActivity.this, LoginActivity.class);
            logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(logoutIntent);
            finish();
        });
    }
}
