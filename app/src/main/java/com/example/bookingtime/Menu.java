package com.example.bookingtime;

import androidx.annotation.NonNull;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Menu extends AppCompatActivity{
    private TextView Selperson, nRestaurantes;
    private Spinner Spinnerperson;
    private Button BtnPrePago;
    String id,Menu,Bebidas,Postre,hora,Mesa,SeleccionPersona,ultpersList;
    int SeleccionPersona1;
    Map<String,String> MenuListaSelect = new HashMap<>();
    Map<String,String> BebidasListaSelect = new HashMap<>();
    Map<String,String> PostreListaSelect = new HashMap<>();


    DatabaseReference reference;
    RecyclerView Menu_recyclerView,Bebidas_recyclerView,Postres_recyclerView;
    ArrayList<MenuRestaurante> MenuLista;
    ArrayList<MenuRestaurante> BebidasLista;
    ArrayList<MenuRestaurante> PostreLista;

    AdaptadorMenu AdaptadorMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Spinnerperson=findViewById(R.id.spinnerPerson);
        Selperson=findViewById(R.id.SelPerson);
        nRestaurantes=findViewById(R.id.nRestaurantes);
        BtnPrePago=findViewById(R.id.btnPrePago);

        Menu_recyclerView=findViewById(R.id.MenuRecyclerView);
        Bebidas_recyclerView=findViewById(R.id.BebidasRecyclerView);
        Postres_recyclerView=findViewById(R.id.PostresRecyclerView);

        // Agrega divisores al recyclerView
        Menu_recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        Bebidas_recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        Postres_recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        // Agrego linearlayout al recycler
        Menu_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Bebidas_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Postres_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // agrego lista al recyclerView
        MenuLista= new ArrayList<MenuRestaurante>();
        BebidasLista= new ArrayList<MenuRestaurante>();
        PostreLista= new ArrayList<MenuRestaurante>();

        // obtengo datos de otra activity
        String personSel=getIntent().getStringExtra("personas");
        String nomRestaurant=getIntent().getStringExtra("Nombre");
        hora=getIntent().getStringExtra("Hora");
        Mesa=getIntent().getStringExtra("Mesa");

        id=getIntent().getStringExtra("id");
        nRestaurantes.setText(nomRestaurant);


       // convierte en int a el numero de personas los lo itera y lo pasa al spinner
        int personSel1=Integer.parseInt(personSel);
        ArrayList<String> PersonList= new ArrayList<>();
        for(int i =1; i <= personSel1; i ++)
        {
           String i1=Integer.toString(i);
            PersonList.add(i1);
        }

        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(this,R.layout.spinner_item_bookingtime,PersonList);
        Spinnerperson.setAdapter(adapter);
        Spinnerperson.setSelection(1);
        Spinnerperson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!TextUtils.isEmpty(SeleccionPersona) && TextUtils.isDigitsOnly(SeleccionPersona)) {
                    SeleccionPersona="1";
                } else {

                    SeleccionPersona=adapter.getItem(i).toString();
                    Selperson.setText(adapter.getItem(i));


             }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ultpersList=PersonList.get(PersonList.size()-1);
        int ultipersList1=Integer.parseInt(ultpersList);
        int SeleccionPersona1=Integer.parseInt(SeleccionPersona);

        Log.d("resultado", String.valueOf(ultipersList1));
        Log.d("resultado", String.valueOf(SeleccionPersona1));
        if(ultipersList1!=SeleccionPersona1){
            BtnPrePago.setText("Siguiente");

        BtnPrePago.setOnClickListener(new View.OnClickListener() {
            @Override
            // al hacer click manda datos a activity prepago
            public void onClick(View view) {
              /*  for(int i =1; i <= personSel1; i ++)
                {
                    String i1=Integer.toString(i);
                    PersonList.add(i1);
                }*/

                for (MenuRestaurante menu : MenuLista) {
                    if (menu.getSeleccion()) {
                        Menu = menu.getNombre();
                        MenuListaSelect.put(SeleccionPersona,Menu);

                    }
                }

                Log.d("resultado", String.valueOf(MenuListaSelect));
                Log.d("Ultimo resultado",ultpersList);
                for (MenuRestaurante bebida : BebidasLista) {
                    if (bebida.getSeleccion()) {
                        Bebidas = bebida.getNombre();
                        BebidasListaSelect.put(SeleccionPersona,Bebidas);
                       // Log.d("resultado", String.valueOf(BebidasListaSelect));
                    }
                }
                Log.d("resultado", String.valueOf(BebidasListaSelect));
                for (MenuRestaurante menu : PostreLista) {
                    if (menu.getSeleccion()) {

                        Postre = menu.getNombre();
                        PostreListaSelect.put(SeleccionPersona,Postre);

                    }
                }
                Log.d("resultado", String.valueOf(PostreListaSelect));

            }
        });

        }else{
            BtnPrePago.setText("Hacer Pedido");
            BtnPrePago.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (MenuRestaurante menu : MenuLista) {
                        if (menu.getSeleccion()) {
                            Menu = menu.getNombre();
                            MenuListaSelect.put(SeleccionPersona,Menu);

                        }
                    }

                    Log.d("resultado", String.valueOf(MenuListaSelect));
                    for (MenuRestaurante bebida : BebidasLista) {
                        if (bebida.getSeleccion()) {
                            Bebidas = bebida.getNombre();
                            BebidasListaSelect.put(SeleccionPersona,Bebidas);
                            // Log.d("resultado", String.valueOf(BebidasListaSelect));
                        }
                    }
                    Log.d("resultado", String.valueOf(BebidasListaSelect));
                    for (MenuRestaurante menu : PostreLista) {
                        if (menu.getSeleccion()) {

                            Postre = menu.getNombre();
                            PostreListaSelect.put(SeleccionPersona,Postre);

                        }
                    }
                    Log.d("resultado", String.valueOf(PostreListaSelect));



                    Intent intent = new Intent(Menu.this, prePago.class);
                    intent.putExtra("Nombre",nRestaurantes.getText().toString());
                    intent.putExtra("SelMenu", (Serializable) MenuListaSelect);
                    intent.putExtra("SelBebida", (Serializable) BebidasListaSelect);
                    intent.putExtra("Postre", (Serializable) PostreListaSelect);
                    intent.putExtra("Hora",hora);
                    intent.putExtra("Mesa",Mesa);
                    intent.putExtra("id",id);
                    startActivity(intent);

                }
            });


        }



            cargardatosfirebase(Menu_recyclerView);
            cargardatosfirebase(Bebidas_recyclerView);
            cargardatosfirebase(Postres_recyclerView);


    }// no funciona esta parte yo estaba probando
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
