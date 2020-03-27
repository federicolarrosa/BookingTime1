package com.example.bookingtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.RestauranteViewHolder> {

    Context context;
    ArrayList<com.example.bookingtime.Restaurante> restaurantes;
    Activity activity;

    public Adaptador (Context context, ArrayList<com.example.bookingtime.Restaurante> restaurante)
    {
        this.context = context;
        this.restaurantes = restaurante;
        this.activity = activity;
    }

    //Inflar el layout y lo pasara al viewHolder para que el obtenga los views.
    @NonNull
    @Override
    public RestauranteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        return new RestauranteViewHolder(v);
    }


    // Asocia cada elemento de la lista con cada view.
    @Override
    public void onBindViewHolder(@NonNull RestauranteViewHolder restauranteViewHolder, int position) {

        final Restaurante restaurante = restaurantes.get(position);


        restauranteViewHolder.nombre_cardview.setText(restaurante.getNombre());
        restauranteViewHolder.email_cardview.setText(restaurante.getEmail());
        restauranteViewHolder.direccion_cardview.setText(restaurante.getDireccion());
        restauranteViewHolder.telefono_cardview.setText(restaurante.getTelefono());
        restauranteViewHolder.tipoComida_cardview.setText(restaurante.getTipoComida());
        restauranteViewHolder.resumen_cardview.setText(restaurante.getResumen());
        Glide
                .with(context)
                .load( restaurante.getImagen())
                .fitCenter()
                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .into(restauranteViewHolder.imagen_cardview);





        //restauranteViewHolder.imagen_cardview

        restauranteViewHolder.nombre_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, restaurante.getNombre(), Toast.LENGTH_SHORT).show();
                 // mandar datos a restauranteDetalle
                Intent intent = new Intent(context, RestauranteDetalle.class);
                intent.putExtra("Nombre", restaurante.getNombre());
                intent.putExtra("tipoComida", restaurante.getTipoComida());
                intent.putExtra("Resumen", restaurante.getResumen());
                intent.putExtra("Direccion", restaurante.getDireccion());
                intent.putExtra("Email", restaurante.getEmail());
                intent.putExtra("Telefono", restaurante.getTelefono());
                intent.putExtra("Imagen", restaurante.getImagen());
                intent.putExtra("Tarjetas", restaurante.getTarjetas());
                intent.putExtra("Estacionamiento", restaurante.getEstacionamiento());
                intent.putExtra("Wifi", restaurante.getWifi());
                intent.putExtra("Fumar", restaurante.getFumar());
                context.startActivity(intent);


            }
        });
    }

    //Cantidad de elementos que contiene la lista
    @Override
    public int getItemCount() {
        return restaurantes.size();
    }

    public static class RestauranteViewHolder extends RecyclerView.ViewHolder
    {

        private TextView nombre_cardview;
        private TextView telefono_cardview;
        private TextView email_cardview;
        private TextView direccion_cardview;
        private TextView tipoComida_cardview;
        private TextView resumen_cardview;
        private ImageView imagen_cardview;

        public RestauranteViewHolder(View itemView) {
            super(itemView);
            nombre_cardview = itemView.findViewById(R.id.nombre_cardView);
            direccion_cardview = itemView.findViewById(R.id.ubicacion_cardView);
            telefono_cardview = itemView.findViewById(R.id.telefono_cardView);
            email_cardview = itemView.findViewById(R.id.email_cardView);
            tipoComida_cardview = itemView.findViewById(R.id.tipoComida_cardView);
            resumen_cardview = itemView.findViewById(R.id.resumen_cardView);
            imagen_cardview = itemView.findViewById(R.id.imagen_cardView);

        }

    }

}
