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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premenu);


        Button Modificar=findViewById(R.id.btnMod);
        Button Siguiente=findViewById(R.id.sigMenu);
        personfinal=findViewById(R.id.personfinal);
        horafinal=findViewById(R.id.horafinal);
        mesal = findViewById(R.id.mesa);
        ide=findViewById(R.id.id);

        String mesahora=getIntent().getStringExtra("MesaHora");
        String mesapers=getIntent().getStringExtra("MesaPerson");
        String mesa=getIntent().getStringExtra("Mesa");
        String id=getIntent().getStringExtra("id");

        ide.setText(id);
        mesal.setText(mesa);
        horafinal.setText(mesahora);
        personfinal.setText(mesapers);

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
        intent.putExtra("personas",personfinal.getText().toString());
        this.startActivity(intent);

    }
    private void modificar(){
        startActivity(new Intent(this, reservaHoraMesa.class));
       //revisar como colocar enabled solo el elegido
        ide.setEnabled(true);
    }
}
