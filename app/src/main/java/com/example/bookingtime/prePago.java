package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class prePago extends AppCompatActivity {
    RecyclerView eleRecyclerView;
    TextView nRest,txtHora,nmesa,suma;
    Button btnpagar;
    String id;
    ArrayList<MostrarPago> ListaFinal;
    int MenPrecio;
    int BebPrecio;
    int PosPrecio;
    int smt=0,sm2,smt1;
    AdaptadorPago AdaptadorPago;
    DatabaseReference reference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
   // final int precioTotal = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_pago);

        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);

        suma=findViewById(R.id.sumaSel);
       /* nRest=findViewById(R.id.nrest);
        txtHora=findViewById(R.id.txtHora);
        nmesa=findViewById(R.id.nmesa);*/
        btnpagar=findViewById(R.id.btnPagar);
        eleRecyclerView=findViewById(R.id.eleRecyclerView);

        eleRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        eleRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        ListaFinal= new ArrayList<MostrarPago>();


       /* String nomRestaurant=getIntent().getStringExtra("Nombre");

        String mesa=getIntent().getStringExtra("Mesa");*/
        id=getIntent().getStringExtra("id");
        String hora=getIntent().getStringExtra("Hora");



      /*   nRest.setText(nomRestaurant);
       txtHora.setText(hora);
        nmesa.setText(mesa);
        nRest.setText(nomRestaurant);*/




        // Referencia al DB Restaurantes/menu en Firebase y agregar los menus al array Listafinal
        reference = FirebaseDatabase.getInstance().getReference().child("Usuarios").child("SeleccionMenu");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    MostrarPago mostrarPago = dataSnapshot1.getValue(MostrarPago.class);

                    ListaFinal.add(mostrarPago);
                }
                ArrayList<Integer>listasmt=new ArrayList<>();
                for (MostrarPago sumaTotal : ListaFinal){
                    BebPrecio=Integer.parseInt(sumaTotal.getBebPrecio());
                    MenPrecio=Integer.parseInt(sumaTotal.getMenPrecio());
                    PosPrecio=Integer.parseInt(sumaTotal.getPosPrecio());
                    smt=BebPrecio+MenPrecio+PosPrecio;
                    listasmt.add(smt);
                }
                Log.d("lod", String.valueOf(listasmt.size()));
                if (listasmt.size()>1) {
                    for (Integer lista : listasmt) {
                         smt1 = lista;
                        sm2 = smt1 + smt1;
                    }
                }else{
                    sm2= smt;
                }
                // Agrega los restaurantes al RecyclerView
                AdaptadorPago = new AdaptadorPago(prePago.this, ListaFinal);
                eleRecyclerView.setAdapter(AdaptadorPago);
                suma.setText(String.valueOf(sm2));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(prePago.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });



        btnpagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // borra la seleccion guardada de firebase al darle click al boton pagar
                myRef.child("Usuarios").child("SeleccionMenu").setValue(null);


                Intent intent = new Intent(prePago.this, pagar.class);
                intent.putExtra("id",id);
                intent.putExtra("hora",hora);
                intent.putExtra("suma",suma.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }

}
