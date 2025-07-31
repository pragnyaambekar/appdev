package com.example.craftedspaces;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewProjectActivity extends AppCompatActivity {

    private EditText editProjectName, editProjectDescription;
    private Button btnSaveProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        // Linking XML to Java
        editProjectName = findViewById(R.id.editProjectName);
        editProjectDescription = findViewById(R.id.editProjectDescription);
        btnSaveProject = findViewById(R.id.btnSaveProject);

        btnSaveProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editProjectName.getText().toString().trim();
                String description = editProjectDescription.getText().toString().trim();

                if (!name.isEmpty() && !description.isEmpty()) {
                    Toast.makeText(NewProjectActivity.this, "Project Saved!", Toast.LENGTH_SHORT).show();
                    // 🔥 You can add DB or navigation logic here next
                } else {
                    Toast.makeText(NewProjectActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
