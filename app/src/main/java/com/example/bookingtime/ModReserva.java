package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ModReserva extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView reserva_recyclerView;
    ArrayList<pojo> reservaLista;
    ArrayList<pojo> BuscadorList;
    AdaptadorReserva adaptadorReserva;
    String id;
    SearchView Buscador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_reserva);

        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);

        reserva_recyclerView = findViewById(R.id.reserva_recyclerView);
        reserva_recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        reserva_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reservaLista = new ArrayList<pojo>();




        //
        reference = FirebaseDatabase.getInstance().getReference().child("Usuarios").child("Mesa Reservada");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    pojo reservas = dataSnapshot1.getValue(pojo.class);
                    reservaLista.add(reservas);

                }
                // Agrega los restaurantes al RecyclerView

                adaptadorReserva = new AdaptadorReserva(ModReserva.this,reservaLista);
                adaptadorReserva.notifyDataSetChanged();

                reserva_recyclerView.setAdapter(adaptadorReserva);


           }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ModReserva.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });

       /* Buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Buscar(s);
                return true;
            }
        });*/


    }

    private void Buscar(String s) {
        BuscadorList = new ArrayList<pojo>();

        Log.d("size", String.valueOf(reservaLista.size()));
        for (pojo reserv : reservaLista) {
            if (reservaLista != null) {
                if (reserv == null) {
                    Log.d("nulllll", String.valueOf(reserv));
                }
                if (reserv != null) {
                    final String search = String.valueOf(s);
                    Log.d("s-->", search);


                    Log.d("nombre", reserv.getNombreR());

                    Log.d("s lower-->", search.toLowerCase());
                    if (reserv.getNombreR().toLowerCase().contains(search.toLowerCase())) {
                        BuscadorList.add(reserv);
                    } else {
                        Toast.makeText(ModReserva.this, "No hay ninguna reserva", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(ModReserva.this, "No hay reservas Echas ", Toast.LENGTH_SHORT).show();
            }
        }

        // Agrega las reservas al RecyclerView
        adaptadorReserva = new AdaptadorReserva(ModReserva.this,BuscadorList);
        reserva_recyclerView.setAdapter(adaptadorReserva);

    }
}

