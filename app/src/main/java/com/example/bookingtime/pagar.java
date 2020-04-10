package com.example.bookingtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pagar extends AppCompatActivity {

    Button btnPagar;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar);

        btnPagar=findViewById(R.id.btnPagar);
        id=getIntent().getStringExtra("id");
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pagar.this, reserva.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}
