package com.example.myapplicqtion;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tvLogin;
    TextView tvPassword;
    EditText etLogin;
    EditText etPassword;
    Button btnValider;
    String login;
    String password;
    Intent intent0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cache l'ActionBar pour ne pas afficher le nom de l'application
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Appliquer le padding selon les barres système
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation des vues
        tvLogin = findViewById(R.id.tvLogin);
        tvPassword = findViewById(R.id.tvPassword);
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        btnValider = findViewById(R.id.btnValider);

        // Gestion des événements
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = etLogin.getText().toString();
                password = etPassword.getText().toString();
                if (login.equals("aicha") && password.equals("mehdisaaydi50")) {
                    intent0 = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent0);
                } else if (login.length() != 0 && password.length() != 0) {
                    Toast.makeText(MainActivity.this, "Enter valid information", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Empty login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}