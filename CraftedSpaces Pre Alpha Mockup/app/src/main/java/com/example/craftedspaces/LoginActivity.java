package com.example.craftedspaces;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.*;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private RadioGroup radioUserType;
    private Button btnLogin;
    private TextView textSignupLink;

    private SharedPreferences sharedPreferences;
    public static final String PREF_NAME = "CraftedSpacesLogin";

    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // ✅ Link UI elements
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        radioUserType = findViewById(R.id.radioUserType);
        btnLogin = findViewById(R.id.btnLogin);
        textSignupLink = findViewById(R.id.textSignupLink);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // ✅ Location setup
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create()
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(5000);

        checkLocationPermission();

        // ✅ Login button logic
        btnLogin.setOnClickListener(v -> handleLogin());

        // ✅ Navigate to Signup screen
        textSignupLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private void handleLogin() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        int selectedId = radioUserType.getCheckedRadioButtonId();

        if (username.isEmpty() || password.isEmpty() || selectedId == -1) {
            Toast.makeText(LoginActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadio = findViewById(selectedId);
        String userType = selectedRadio.getText().toString();

        // ✅ Save login info in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("userType", userType);
        editor.apply();

        // ✅ Fetch location then redirect
        getCurrentLocationAndProceed(userType, username);
    }

    private void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    101
            );
        }
    }

    private void getCurrentLocationAndProceed(String userType, String username) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                    .addOnSuccessListener(this, location -> {
                        String locationStr = "Unavailable";
                        if (location != null) {
                            double lat = location.getLatitude();
                            double lon = location.getLongitude();
                            locationStr = lat + ", " + lon;
                            Toast.makeText(LoginActivity.this,
                                    "Location: " + locationStr, Toast.LENGTH_SHORT).show();
                        }

                        // ✅ Redirect based on user type
                        Intent intent = userType.equalsIgnoreCase("Professional")
                                ? new Intent(LoginActivity.this, ProjectsActivity.class)
                                : new Intent(LoginActivity.this, DashboardActivity.class);

                        intent.putExtra("username", username);
                        intent.putExtra("userType", userType);
                        intent.putExtra("location", locationStr);

                        startActivity(intent);
                        finish();
                    });

        } else {
            Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101 &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Location permission granted ✅", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Location permission denied ❌", Toast.LENGTH_SHORT).show();
        }
    }
}
