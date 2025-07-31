package com.example.craftedspaces;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProjectsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        // 🔽 Receive data
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String userType = intent.getStringExtra("userType");
        String location = intent.getStringExtra("location");

        // 🔽 Set data to views
        TextView welcomeText = findViewById(R.id.welcomeText);
        TextView roleText = findViewById(R.id.roleText);
        TextView locationText = findViewById(R.id.locationText);

        welcomeText.setText("Welcome, " + username + " 👷");
        roleText.setText("Role: " + userType);
        locationText.setText("Location: " + location);

        Button btnStartProject = findViewById(R.id.btnStartProject);
        btnStartProject.setOnClickListener(v -> {
            Intent newIntent = new Intent(ProjectsActivity.this, NewProjectActivity.class);
            startActivity(newIntent);
        });
    }
}
