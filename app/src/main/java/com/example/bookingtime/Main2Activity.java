package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class Main2Activity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView restaurante_recyclerView;
    ArrayList<Restaurante> restaurantesLista;
    ArrayList<Restaurante> BuscadorList;
    Adaptador adaptador;

    SearchView Buscador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);



        Buscador=findViewById(R.id.Buscador);
        handleIntent(getIntent());
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
                adaptador = new Adaptador(Main2Activity.this,restaurantesLista);
                restaurante_recyclerView.setAdapter(adaptador);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main2Activity.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });
        Buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Buscar(s);
                return true;
            }
        });

    }


    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Buscar(query);
        }
    }
    private void Buscar(String s) {
        BuscadorList = new ArrayList<Restaurante>();


        for (Restaurante restaurante : restaurantesLista) {
            if (restaurantesLista != null) {
                if (restaurante == null) {

                }
                if (restaurante != null) {
                    final String search = String.valueOf(s);

                    if (restaurante.getNombre().toLowerCase().contains(search.toLowerCase())) {
                        BuscadorList.add(restaurante);
                    } else {
                        Toast.makeText(Main2Activity.this, "No hay ningun Restaurante", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(Main2Activity.this, "No hay ningun Restaurante Cargado", Toast.LENGTH_SHORT).show();
            }
        }

        // Agrega los restaurantes al RecyclerView
        adaptador = new Adaptador(Main2Activity.this,BuscadorList);
        restaurante_recyclerView.setAdapter(adaptador);

    }
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_busqueda_reserva,menu);

        return true;
    }

}

