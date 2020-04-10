package com.example.bookingtime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class prePago extends AppCompatActivity {
    RecyclerView eleRecyclerView;
    TextView nRest,txtHora;
    TextView nmesa;
    Button btnpagar;
    String id;
    Map<String,String> SelMenu;
    Map<String,String> SelBebida;
    Map<String,String> SelPostre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_pago);

        //prueba=findViewById(R.id.prueba);
        nRest=findViewById(R.id.nrest);
        txtHora=findViewById(R.id.txtHora);
        nmesa=findViewById(R.id.nmesa);
        btnpagar=findViewById(R.id.btnPagar);
        eleRecyclerView=findViewById(R.id.eleRecyclerView);

        eleRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        eleRecyclerView.setLayoutManager(new LinearLayoutManager(this));





        String nomRestaurant=getIntent().getStringExtra("Nombre");
        SelMenu=(Map<String,String>)getIntent().getSerializableExtra("SelMenu");
        SelBebida=(Map<String,String>)getIntent().getSerializableExtra("SelBebida");
        SelPostre=(Map<String, String>) getIntent().getSerializableExtra("SelPostre");
        String hora=getIntent().getStringExtra("Hora");
        String mesa=getIntent().getStringExtra("Mesa");
        id=getIntent().getStringExtra("id");

      //  prueba.setText(SelMenu);
        nRest.setText(nomRestaurant);
        txtHora.setText(hora);
        nmesa.setText(mesa);
        nRest.setText(nomRestaurant);
        btnpagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(prePago.this, pagar.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}
