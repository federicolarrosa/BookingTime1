package com.example.bookingtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class preMenu extends AppCompatActivity {

    private TextView mesal;
    private TextView horafinal;
    private TextView personfinal;
    private TextView ide;
    private TextView nRestaurant;
    String nomRestaurante;
    String id;
    String mesahora;
    String mesapers;
    String mesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premenu);


        Button Modificar=findViewById(R.id.btnMod);
        Button Siguiente=findViewById(R.id.sigMenu);
        nRestaurant=findViewById(R.id.nRestaurant);
        personfinal=findViewById(R.id.personfinal);
        horafinal=findViewById(R.id.horafinal);
        mesal = findViewById(R.id.mesa);
        ide=findViewById(R.id.id);

        mesahora=getIntent().getStringExtra("MesaHora");
        mesapers=getIntent().getStringExtra("MesaPerson");
        mesa=getIntent().getStringExtra("Mesa");
        id=getIntent().getStringExtra("id");
        nomRestaurante=getIntent().getStringExtra("Nombre");

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
        intent.putExtra("id",id);
        this.startActivity(intent);
    }
    private void modificar(){
        Intent intent = new Intent(preMenu.this, reservaHoraMesa.class);
        intent.putExtra("id",id);
        this.startActivity(intent);
    }
}
