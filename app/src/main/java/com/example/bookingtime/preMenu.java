package com.example.bookingtime;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class preMenu extends AppCompatActivity {

    private TextView mesal;
    private TextView horafinal;
    private TextView personfinal;
    private TextView ide;
    private TextView nRestaurant;
    private TextView Dia;
    String nomRestaurante,id,mesahora,mesapers,mesa,key,dia;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premenu);

        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);


        Button Modificar=findViewById(R.id.btnMod);
        Button Siguiente=findViewById(R.id.sigMenu);
        nRestaurant=findViewById(R.id.nRestaurant);
        personfinal=findViewById(R.id.personfinal);
        horafinal=findViewById(R.id.horafinal);
        mesal = findViewById(R.id.mesa);
        ide=findViewById(R.id.id);
        Dia=findViewById(R.id.dia);

        dia=getIntent().getStringExtra("Dia");
        mesahora=getIntent().getStringExtra("MesaHora");
        mesapers=getIntent().getStringExtra("MesaPerson");
        mesa=getIntent().getStringExtra("Mesa");
        id=getIntent().getStringExtra("id");
        nomRestaurante=getIntent().getStringExtra("Nombre");
        key=getIntent().getStringExtra("key");

        Dia.setText(dia);
        ide.setText(id);
        mesal.setText(mesa);
        horafinal.setText(mesahora);
        personfinal.setText(mesapers);
        nRestaurant.setText(nomRestaurante);

        Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar();
            }
        });
        Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siguiente();
            }
        });

    }
    private void siguiente() {
        Intent intent = new Intent(preMenu.this, Menu.class);
        intent.putExtra("personas",mesapers);
        intent.putExtra("Nombre",nomRestaurante);
        intent.putExtra("Hora",mesahora);
        intent.putExtra("Mesa",mesa);
        intent.putExtra("id",ide.getText().toString());
        this.startActivity(intent);
        finish();
    }
    private void BorrarDF(String M){
        // borra la seleccion guardada de firebase al darle click al boton pagar

       Query BorrarMesa= myRef.child("Usuarios").child("Mesa Reservada").orderByChild("id").equalTo(M);
        BorrarMesa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot MesaSnapshot: dataSnapshot.getChildren()) {
                    MesaSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(preMenu.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void modificar(){
        BorrarDF(mesa);
        Intent intent = new Intent(preMenu.this, reservaHoraMesa.class);
        intent.putExtra("Nombre",nomRestaurante);
        intent.putExtra("id",id);
        this.startActivity(intent);
        finish();
    }

}
