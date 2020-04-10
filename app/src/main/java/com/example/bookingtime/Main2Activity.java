package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView restaurante_recyclerView;
    ArrayList<Restaurante> restaurantesLista;
    Adaptador adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        restaurante_recyclerView = findViewById(R.id.restaurantes_recyclerView);
        restaurante_recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        restaurante_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantesLista = new ArrayList<Restaurante>();

        // Referencia al DB Restaurantes en Firebase y agregar los restaurantes al array restaurantesLista
        reference = FirebaseDatabase.getInstance().getReference().child("Restaurantes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Restaurante restaurante = dataSnapshot1.getValue(Restaurante.class);
                    restaurantesLista.add(restaurante);
                }
                // Agrega los restaurantes al RecyclerView
                adaptador = new com.example.bookingtime.Adaptador(Main2Activity.this, restaurantesLista);
                restaurante_recyclerView.setAdapter(adaptador);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main2Activity.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
