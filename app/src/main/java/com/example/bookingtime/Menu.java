package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    private TextView Selperson;
    DatabaseReference reference;
    RecyclerView Menu_recyclerView,Bebidas_recyclerView,Postres_recyclerView;
    ArrayList<MenuRestaurante> MenuLista,BebidasLista,PostreLista;
    AdaptadorMenu AdaptadorMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Selperson=findViewById(R.id.SelPerson);

        Menu_recyclerView=findViewById(R.id.MenuRecyclerView);
        Bebidas_recyclerView=findViewById(R.id.BebidasRecyclerView);
        Postres_recyclerView=findViewById(R.id.PostresRecyclerView);

        Menu_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Bebidas_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Postres_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MenuLista= new ArrayList<MenuRestaurante>();
        BebidasLista= new ArrayList<MenuRestaurante>();
        PostreLista= new ArrayList<MenuRestaurante>();

        String personSel=getIntent().getStringExtra("personas");
        Selperson.setText(personSel);
       // for(int i =0; i <= i; i = i ++)
        //{

     //   }



            cargardatosfirebase(Menu_recyclerView);
            cargardatosfirebase(Bebidas_recyclerView);
            cargardatosfirebase(Postres_recyclerView);


    }
    private void cargardatosfirebase(View view){

        switch (view.getId()){

            case R.id.MenuRecyclerView:
                // Referencia al DB Restaurantes/menu en Firebase y agregar los menus al array menuLista
                reference = FirebaseDatabase.getInstance().getReference().child("Restaurantes").child("Menu");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            MenuRestaurante menuRestaurante = dataSnapshot1.getValue(MenuRestaurante.class);
                            MenuLista.add(menuRestaurante);
                        }
                        // Agrega los restaurantes al RecyclerView
                        AdaptadorMenu = new AdaptadorMenu(Menu.this, MenuLista);
                        Menu_recyclerView.setAdapter(AdaptadorMenu);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Menu.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.BebidasRecyclerView:
                // Referencia al DB Restaurantes/menu en Firebase y agregar los menus al array menuLista
                reference = FirebaseDatabase.getInstance().getReference().child("Restaurantes").child("Bebidas");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            MenuRestaurante menuRestaurante = dataSnapshot1.getValue(MenuRestaurante.class);
                            BebidasLista.add(menuRestaurante);
                        }
                        // Agrega los restaurantes al RecyclerView
                        AdaptadorMenu = new AdaptadorMenu(Menu.this, BebidasLista);
                        Bebidas_recyclerView.setAdapter(AdaptadorMenu);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Menu.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

                case R.id.PostresRecyclerView:
                    // Referencia al DB Restaurantes/menu en Firebase y agregar los menus al array menuLista
                    reference = FirebaseDatabase.getInstance().getReference().child("Restaurantes").child("Postres");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                MenuRestaurante menuRestaurante = dataSnapshot1.getValue(MenuRestaurante.class);
                                PostreLista.add(menuRestaurante);
                            }
                            // Agrega los restaurantes al RecyclerView
                            AdaptadorMenu = new AdaptadorMenu(Menu.this, PostreLista);
                            Postres_recyclerView.setAdapter(AdaptadorMenu);

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(Menu.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
        }
    }
}
