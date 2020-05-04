package com.example.bookingtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RestauranteDetalle extends AppCompatActivity {

      String Nombre;
      String Imagen;
      Button btnRegistrarFecha;
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    @Override

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurantre_detalle);

//Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);


        btnRegistrarFecha= findViewById(R.id.btnReservarFecha);
        TextView nombre_detalle = findViewById(R.id.nombre_relativeLayout);
        TextView tipoComida_detalle = findViewById(R.id.tipoComida_relativeLayout);
        TextView resumen_detalle = findViewById(R.id.resumen_relativeLayout);
        ImageView ImagenRes = findViewById(R.id.imagen_relativeLayout);

        CheckBox tarjetas_detalle = findViewById(R.id.tarjetas_checkbox);
        CheckBox estacionamiento_detalle = findViewById(R.id.estacionamiento_checkbox);
        CheckBox wifi_detalle = findViewById(R.id.wifi_checkbox);
        CheckBox fumar_detalle = findViewById(R.id.fumar_checkbox);

        boolean tarjetas = getIntent().getBooleanExtra("Tarjetas",true);
        boolean estacionamiento = getIntent().getBooleanExtra("Estacionamiento",true);
        boolean wifi = getIntent().getBooleanExtra("Wifi",true);
        boolean fumar = getIntent().getBooleanExtra("Fumar",true);

        Nombre = getIntent().getStringExtra("Nombre");
        String tipoComida = getIntent().getStringExtra("tipoComida");
        String Resumen = getIntent().getStringExtra("Resumen");
        Imagen = getIntent().getStringExtra("Imagen");



        nombre_detalle.setText(Nombre);
        tipoComida_detalle.setText(tipoComida);
        resumen_detalle.setText(Resumen);

        // mostrar checked de los checkbox
        tarjetas_detalle.setChecked(tarjetas);
        estacionamiento_detalle.setChecked(estacionamiento);
        wifi_detalle.setChecked(wifi);
        fumar_detalle.setChecked(fumar);

        //mostrar Imagen
        Glide
                .with(RestauranteDetalle.this)
                .load(Imagen)
                .fitCenter()
                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .into(ImagenRes);


        btnRegistrarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mandardatos();
            }
        });







    }
private void mandardatos(){
    // mandar datos a reservafecha
    Intent intent = new Intent(RestauranteDetalle.this, reservafecha.class);
    intent.putExtra("Nombre",getNombre());
    intent.putExtra("Imagen", getImagen());
    this.startActivity(intent);
    finish();
    }
}
