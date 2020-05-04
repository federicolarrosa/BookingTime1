package com.example.bookingtime;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pagar extends AppCompatActivity {

    Button btnPagar;
    String id;
    TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar);


        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);


        btnPagar=findViewById(R.id.btnPagar);
        total=findViewById(R.id.total);
        id=getIntent().getStringExtra("id");
        String hora=getIntent().getStringExtra("hora");
        String suma=getIntent().getStringExtra("suma");
        total.setText("$ "+suma);
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pagar.this, reserva.class);
                intent.putExtra("id",id);
                intent.putExtra("hora",hora);
                startActivity(intent);
                finish();
            }
        });
    }
}
