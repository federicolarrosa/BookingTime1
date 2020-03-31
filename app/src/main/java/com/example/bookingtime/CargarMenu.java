package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class CargarMenu extends AppCompatActivity {

    private EditText nomMenu, descMenu;
    private TextView tipocomida,tipomenu;
    private Spinner SpinnerMenu,Spinnertipo;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_menu);

        mStorageRef= FirebaseStorage.getInstance().getReference();
        nomMenu=findViewById(R.id.NomMenu);
        descMenu=findViewById(R.id.DescMenu);
        SpinnerMenu=findViewById(R.id.spinnerMenu);
        Spinnertipo=findViewById(R.id.Spinnertipo);
        tipocomida=findViewById(R.id.tipComida);
        tipomenu=findViewById(R.id.tipoMenu);
        Button btnCargarMenu=findViewById(R.id.btnCargarMenu);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.TipoComida,R.layout.spinner_item_bookingtime);
        SpinnerMenu.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.TipoMenu,R.layout.spinner_item_bookingtime);
        Spinnertipo.setAdapter(adapter1);

        SpinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                tipocomida.setText(adapter.getItem(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Spinnertipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipomenu.setText(adapter1.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCargarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NombreMenu=nomMenu.getText().toString();
                String DescripcionMenu=descMenu.getText().toString();
                String tcomida=tipocomida.getText().toString();
                String tMenu=tipomenu.getText().toString();


                switch (tMenu){

                    case "Postres":
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference();
                        Map<String, Object> datosRestaurante= new HashMap<>();
                        datosRestaurante.put("Nombre", NombreMenu);
                        datosRestaurante.put("TipoComida", tcomida);
                        datosRestaurante.put("Descripcion", DescripcionMenu);

                        myRef.child("Restaurantes").child("Postres").push().setValue(datosRestaurante).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(CargarMenu.this,"Cargado con exito.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CargarMenu.this,"Ups.. hubo un problema Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                            }
                        });


                        break;

                    case "Bebidas":
                        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                        DatabaseReference myRef1 = database1.getReference();
                        Map<String, Object> datosRestaurante1= new HashMap<>();
                        datosRestaurante1.put("Nombre", NombreMenu);


                        myRef1.child("Restaurantes").child("Bebidas").push().setValue(datosRestaurante1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(CargarMenu.this,"Cargado con exito.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CargarMenu.this,"Ups.. hubo un problema Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                            }
                        });


                        break;

                    case "Menu":
                        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                        DatabaseReference myRef2 = database2.getReference();
                        Map<String, Object> datosRestaurante2= new HashMap<>();
                        datosRestaurante2.put("Nombre", NombreMenu);
                        datosRestaurante2.put("TipoComida", tcomida);
                        datosRestaurante2.put("Descripcion", DescripcionMenu);

                        myRef2.child("Restaurantes").child("Menu").push().setValue(datosRestaurante2).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(CargarMenu.this,"Cargado con exito.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CargarMenu.this,"Ups.. hubo un problema Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                            }
                        });

                    break;
                }

            }
        });

    }
}
