package com.bhaire.digiboard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button btnPref,btnExit,btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        btnStart = findViewById(R.id.btn_start_drawing);
        btnPref = findViewById(R.id.btn_preferences);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sIntent = new Intent(MainActivity.this,DrawBoard.class);
                startActivity(sIntent);
            }
        });

        btnPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Load preferences", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();
        String[] permissions ={"android.permission.WRITE_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE"};
        requestPermissions(permissions,123);

    }
}
