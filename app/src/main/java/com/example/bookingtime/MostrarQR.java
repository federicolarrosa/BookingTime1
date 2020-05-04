package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.IOException;

public class MostrarQR extends AppCompatActivity {

    private ImageView ImagenCodigo;
    private Button btnVolver;
    DatabaseReference reference;
    String id,id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_q_r);

        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);


        ImagenCodigo = findViewById(R.id.imagenCodigo);
        btnVolver=findViewById(R.id.btnVolver);
        id=getIntent().getStringExtra("id");
        id1="M"+id;
        Bitmap bitmap = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(Environment.getExternalStorageDirectory()+ "/"+id1+".png");
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (IOException io){
            io.printStackTrace();
        }

        ImagenCodigo.setImageBitmap(bitmap);


        btnVolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MostrarQR.this,ModReserva.class));
                finish();
            }
        });

    }


}
