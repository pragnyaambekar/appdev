package com.example.craftedspaces;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button btnSignup;
    private TextView tvLoginRedirect;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Firebase Auth instance
        mAuth = FirebaseAuth.getInstance();

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnSignup = findViewById(R.id.btnSignup);
        tvLoginRedirect = findViewById(R.id.tvLoginRedirect);

        btnSignup.setOnClickListener(v -> registerUser());
        tvLoginRedirect.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            editEmail.setError("Email is required");
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            editPassword.setError("Password must be at least 6 characters");
            return;
        }

        btnSignup.setEnabled(false);

        // Firebase signup
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    btnSignup.setEnabled(true);
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(SignupActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();

                        // Redirect to login after signup
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(SignupActivity.this, "Signup failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
