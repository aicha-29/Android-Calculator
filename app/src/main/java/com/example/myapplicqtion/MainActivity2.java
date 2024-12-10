package com.example.myapplicqtion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    Button btnWeb;
    Button btn3;
    Intent intent;
    Intent intent2, intent3;
    Button btnCall;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cacher la barre d'action (ActionBar)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Rendre l'activité en pleine largeur et pleine hauteur
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Gérer les marges avec EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn4), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Instatiations
        btnWeb = findViewById(R.id.btnWeb);
        btnCall = findViewById(R.id.btnCall);
        btn3 = findViewById(R.id.btn3);


        //Events
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.ensaf.ac.ma";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });


        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:0677504714");
                intent2 = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent3);
            }
        });


    }


}