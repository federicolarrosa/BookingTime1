package com.example.bookingtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class principalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
       // Button btnReservaFecha= findViewById(R.id.reservaFecha);
        Button btnSalir = findViewById(R.id.btnSalir);
        Button btnrestaurantes = findViewById(R.id.btnRestaurantes);
        Button btnLector = findViewById(R.id.btnLector);
        Button btnrestaurant= findViewById(R.id.btnrestaurant);
        Button btnCargarMenu= findViewById(R.id.btnCargarMenu);

        btnCargarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CargarMenu();
            }
        });
      /*  btnReservaFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservaHoraMesa();
            }*//*
        });*/
        btnrestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarResturant();
            }
        });

        btnLector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lector();
            }
        });

        btnrestaurantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurante();
                            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(principalActivity.this,MainActivity.class));
            }
        });


        }

   private void restaurante(){
       startActivity(new Intent(this,Main2Activity.class));
   }
    private void lector(){
        startActivity(new Intent(this,lectorQR.class));
    }
    private void cargarResturant(){
        startActivity(new Intent(this,cargarRestaurantes.class));
    }
    private void reservaHoraMesa(){
        startActivity(new Intent(this,reservaHoraMesa.class));
}
    private void CargarMenu(){
        startActivity(new Intent(this,CargarMenu.class));
    }
}



