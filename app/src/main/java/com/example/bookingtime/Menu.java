package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Menu extends AppCompatActivity {
    private TextView Selperson, nRestaurantes;
    //private Spinner Spinnerperson;
    private Button BtnPrePago;
    String id, Menu, Bebidas, Postre, hora, Mesa, ultpersList, Precio, BebPrecio, PosPrecio;
    int SeleccionPersona1, ultipersList1;
    ScrollView scroll;
    Map<String, Object> ListaSelect = new HashMap<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    DatabaseReference reference;
    RecyclerView Menu_recyclerView, Bebidas_recyclerView, Postres_recyclerView;
    ArrayList<MenuRestaurante> MenuLista;
    ArrayList<MenuRestaurante> BebidasLista;
    ArrayList<MenuRestaurante> PostreLista;

    AdaptadorMenu AdaptadorMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);


        // Spinnerperson=findViewById(R.id.spinnerPerson);
        Selperson = findViewById(R.id.SelPerson);
        nRestaurantes = findViewById(R.id.nRestaurantes);
        BtnPrePago = findViewById(R.id.btnPrePago);

        scroll = findViewById(R.id.scroll);
        Menu_recyclerView = findViewById(R.id.MenuRecyclerView);
        Bebidas_recyclerView = findViewById(R.id.BebidasRecyclerView);
        Postres_recyclerView = findViewById(R.id.PostresRecyclerView);

        // Agrega divisores al recyclerView
        Menu_recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        Bebidas_recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        Postres_recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // Agrego linearlayout al recycler
        Menu_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Bebidas_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Postres_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // agrego lista al recyclerView
        MenuLista = new ArrayList<MenuRestaurante>();
        BebidasLista = new ArrayList<MenuRestaurante>();
        PostreLista = new ArrayList<MenuRestaurante>();

        // obtengo datos de otra activity
        String personSel = getIntent().getStringExtra("personas");
        String nomRestaurant = getIntent().getStringExtra("Nombre");
        hora = getIntent().getStringExtra("Hora");
        Mesa = getIntent().getStringExtra("Mesa");

        id = getIntent().getStringExtra("id");
        nRestaurantes.setText(nomRestaurant);



        // convierte en int a el numero de personas los lo itera y lo pasa al spinner
        int personSel1 = Integer.parseInt(personSel);
        ArrayList<String> PersonList = new ArrayList<>();
        for (int i = 1; i <= personSel1; i++) {
            String i1 = Integer.toString(i);
            PersonList.add(i1);
        }

        //cambio personList y SeleccionPersona  a int
        ultpersList = PersonList.get(PersonList.size() - 1);
        ultipersList1 = Integer.parseInt(ultpersList);
        SeleccionPersona1 = 1;
        if (ultipersList1==1){
            BtnPrePago.setText("Hacer Pedido");
        }
        Selperson.setText(String.valueOf(SeleccionPersona1));

        switch (BtnPrePago.getText().toString()) {

            case "Siguiente":
                BtnPrePago.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {





                        //Sube hasta arriba cuando le das a siguiente
                        scroll.smoothScrollTo(0, 0);

                        for (MenuRestaurante menu : MenuLista) {
                            if (menu.getSeleccion()) {
                                Menu = menu.getNombre();
                                Precio = menu.getPrecio();
                                ListaSelect.put("Persona", SeleccionPersona1);
                                ListaSelect.put("MenuSel", Menu);
                                ListaSelect.put("MenPrecio", Precio);
                                Log.d("resultado menu", String.valueOf(ListaSelect));
                            }
                        }


                        for (MenuRestaurante bebida : BebidasLista) {
                            if (bebida.getSeleccion()) {
                                Bebidas = bebida.getNombre();
                                BebPrecio = bebida.getPrecio();
                                ListaSelect.put("Persona", SeleccionPersona1);
                                ListaSelect.put("BebidasSel", Bebidas);
                                ListaSelect.put("BebPrecio", BebPrecio);

                            }
                        }
                        Log.d("resultado bebida", String.valueOf(ListaSelect));
                        for (MenuRestaurante postre : PostreLista) {
                            if (postre.getSeleccion()) {

                                Postre = postre.getNombre();
                                PosPrecio = postre.getPrecio();
                                ListaSelect.put("Persona", SeleccionPersona1);
                                ListaSelect.put("PostreSel", Postre);
                                ListaSelect.put("PosPrecio", PosPrecio);

                            }

                        }


                        //mandar datos de seleccion a firebase
                        myRef.child("Usuarios").child("SeleccionMenu").push().setValue(ListaSelect).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(Menu.this, "Cargado con exito.", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Menu.this, "Ups.. hubo un problema Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        SeleccionPersona1++;
                        Selperson.setText(String.valueOf(SeleccionPersona1));
                          if (SeleccionPersona1==ultipersList1) {
                              BtnPrePago.setText("Hacer Pedido");
                              Selperson.setText(String.valueOf(SeleccionPersona1));
                              BtnPrePago.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {


                                      for (MenuRestaurante menu : MenuLista) {
                                          if (menu.getSeleccion()) {
                                              Menu = menu.getNombre();
                                              Precio = menu.getPrecio();
                                              ListaSelect.put("Persona", SeleccionPersona1);
                                              ListaSelect.put("MenuSel", Menu);
                                              ListaSelect.put("MenPrecio", Precio);

                                          }
                                      }

                                      Log.d("resultado", String.valueOf(ListaSelect));
                                      for (MenuRestaurante bebida : BebidasLista) {
                                          if (bebida.getSeleccion()) {
                                              Bebidas = bebida.getNombre();
                                              BebPrecio = bebida.getPrecio();
                                              ListaSelect.put("BebidasSel", Bebidas);
                                              ListaSelect.put("BebPrecio", BebPrecio);
                                              // Log.d("resultado", String.valueOf(BebidasListaSelect));
                                          }
                                      }
                                      Log.d("resultado", String.valueOf(ListaSelect));
                                      for (MenuRestaurante Postres : PostreLista) {
                                          if (Postres.getSeleccion()) {

                                              Postre = Postres.getNombre();
                                              PosPrecio = Postres.getPrecio();
                                              ListaSelect.put("PostresSel", Postre);
                                              ListaSelect.put("PosPrecio", PosPrecio);
                                          }
                                      }
                                      Log.d("resultado", String.valueOf(ListaSelect));
                                      //mandar datos de seleccion a firebase
                                      myRef.child("Usuarios").child("SeleccionMenu").push().setValue(ListaSelect).addOnCompleteListener(new OnCompleteListener<Void>() {
                                          @Override
                                          public void onComplete(@NonNull Task<Void> task) {

                                              Toast.makeText(Menu.this, "Cargado con exito.", Toast.LENGTH_SHORT).show();
                                          }
                                      }).addOnFailureListener(new OnFailureListener() {
                                          @Override
                                          public void onFailure(@NonNull Exception e) {
                                              Toast.makeText(Menu.this, "Ups.. hubo un problema Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                                          }
                                      });

                                      Intent intent = new Intent(Menu.this, prePago.class);
                                      intent.putExtra("Nombre", nRestaurantes.getText().toString());
                                      intent.putExtra("Hora", hora);
                                      intent.putExtra("Mesa", Mesa);
                                      intent.putExtra("id", id);
                                      startActivity(intent);
                                      finish();
                                  }
                              });
                          }
                    }
                });
                break;

            case "Hacer Pedido":

                Selperson.setText(String.valueOf(SeleccionPersona1));
                BtnPrePago.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        for (MenuRestaurante menu : MenuLista) {
                            if (menu.getSeleccion()) {
                                Menu = menu.getNombre();
                                Precio = menu.getPrecio();
                                ListaSelect.put("Persona", SeleccionPersona1);
                                ListaSelect.put("MenuSel", Menu);
                                ListaSelect.put("MenPrecio", Precio);

                            }
                        }

                        Log.d("resultado", String.valueOf(ListaSelect));
                        for (MenuRestaurante bebida : BebidasLista) {
                            if (bebida.getSeleccion()) {
                                Bebidas = bebida.getNombre();
                                BebPrecio = bebida.getPrecio();
                                ListaSelect.put("BebidasSel", Bebidas);
                                ListaSelect.put("BebPrecio", BebPrecio);
                                // Log.d("resultado", String.valueOf(BebidasListaSelect));
                            }
                        }
                        Log.d("resultado", String.valueOf(ListaSelect));
                        for (MenuRestaurante Postres : PostreLista) {
                            if (Postres.getSeleccion()) {

                                Postre = Postres.getNombre();
                                PosPrecio = Postres.getPrecio();
                                ListaSelect.put("PostresSel", Postre);
                                ListaSelect.put("PosPrecio", PosPrecio);
                            }
                        }
                        Log.d("resultado", String.valueOf(ListaSelect));
                        //mandar datos de seleccion a firebase
                        myRef.child("Usuarios").child("SeleccionMenu").push().setValue(ListaSelect).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(Menu.this, "Cargado con exito.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Menu.this, "Ups.. hubo un problema Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                            }
                        });

                        Intent intent = new Intent(Menu.this, prePago.class);
                        intent.putExtra("Nombre", nRestaurantes.getText().toString());
                        intent.putExtra("Hora", hora);
                        intent.putExtra("Mesa", Mesa);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();
                    }
                });
                break;
        }

                      cargardatosfirebase(Menu_recyclerView);
                      cargardatosfirebase(Bebidas_recyclerView);
                      cargardatosfirebase(Postres_recyclerView);


}
    private void cargardatosfirebase(View view) {

        switch (view.getId()) {

            case R.id.MenuRecyclerView:

                // Referencia al DB Restaurantes/menu en Firebase y agregar los menus al array menuLista
                reference = FirebaseDatabase.getInstance().getReference().child("Menu").child("Menus");
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
                reference = FirebaseDatabase.getInstance().getReference().child("Menu").child("Bebidas");
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
                reference = FirebaseDatabase.getInstance().getReference().child("Menu").child("Postres");
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


