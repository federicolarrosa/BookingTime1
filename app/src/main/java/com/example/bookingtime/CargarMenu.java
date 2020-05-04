package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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

    private EditText nomMenu, descMenu,PrecioM;
    private String tipomenu;
    private TextView tipocomida;
    private Spinner SpinnerMenu,Spinnertipo;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_menu);

        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);

        mStorageRef= FirebaseStorage.getInstance().getReference();
        nomMenu=findViewById(R.id.NomMenu);
        PrecioM=findViewById(R.id.precioM);
        descMenu=findViewById(R.id.DescMenu);
        SpinnerMenu=findViewById(R.id.spinnerMenu);
        Spinnertipo=findViewById(R.id.Spinnertipo);
        tipocomida=findViewById(R.id.tipComida);
        //tipomenu=findViewById(R.id.tipoMenu);
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
                tipomenu=adapter1.getItem(i).toString();
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
                String tMenu=tipomenu;
                String Preciomenu=PrecioM.getText().toString();



                switch (tMenu){

                    case "Postres":

                        if (nomMenu.getText().toString().isEmpty()){
                            Toast.makeText(CargarMenu.this,"Debe Agregar Nombre.", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (descMenu.getText().toString().isEmpty()){
                            Toast.makeText(CargarMenu.this,"Debe agregar una descripcion. ", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (tipocomida.getText().toString().isEmpty()) {
                            Toast.makeText(CargarMenu.this,"Eliga tipo comida.", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (PrecioM.getText().toString().isEmpty()){
                            Toast.makeText(CargarMenu.this,"Debe agregar precio.", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference();
                        Map<String, Object> datosRestaurante= new HashMap<>();
                        datosRestaurante.put("Nombre", NombreMenu);
                        datosRestaurante.put("TipoComida", tcomida);
                        datosRestaurante.put("Descripcion", DescripcionMenu);
                        datosRestaurante.put("Precio", Preciomenu);

                        myRef.child("Menu").child("Postres").push().setValue(datosRestaurante).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(CargarMenu.this,"Cargado con exito.", Toast.LENGTH_SHORT).show();
                                nomMenu.setText("");
                                descMenu.setText("");
                                tipocomida.setText("");
                                PrecioM.setText("");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CargarMenu.this,"Ups.. hubo un problema Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                            }
                        });


                        break;

                    case "Bebidas":
                        if (nomMenu.getText().toString().isEmpty()){
                            Toast.makeText(CargarMenu.this,"Debe Agregar Nombre.", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        if (tipocomida.getText().toString().isEmpty()) {
                            Toast.makeText(CargarMenu.this,"Eliga tipo comida.", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (PrecioM.getText().toString().isEmpty()){
                            Toast.makeText(CargarMenu.this,"Debe agregar precio.", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                        DatabaseReference myRef1 = database1.getReference();
                        Map<String, Object> datosRestaurante1= new HashMap<>();
                        datosRestaurante1.put("Nombre", NombreMenu);
                        datosRestaurante1.put("Precio", Preciomenu);


                        myRef1.child("Menu").child("Bebidas").push().setValue(datosRestaurante1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(CargarMenu.this,"Cargado con exito.", Toast.LENGTH_SHORT).show();
                                nomMenu.setText("");
                                descMenu.setText("");
                                tipocomida.setText("");
                                PrecioM.setText("");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CargarMenu.this,"Ups.. hubo un problema Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                            }
                        });


                        break;

                    case "Menu":
                        if (nomMenu.getText().toString().isEmpty()){
                            Toast.makeText(CargarMenu.this,"Debe Agregar Nombre.", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (descMenu.getText().toString().isEmpty()){
                            Toast.makeText(CargarMenu.this,"Debe agregar una descripcion. ", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (tipocomida.getText().toString().isEmpty()) {
                            Toast.makeText(CargarMenu.this,"Eliga tipo comida.", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (PrecioM.getText().toString().isEmpty()){
                            Toast.makeText(CargarMenu.this,"Debe agregar precio.", Toast.LENGTH_SHORT).show();
                            break;
                        }


                        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                        DatabaseReference myRef2 = database2.getReference();
                        Map<String, Object> datosRestaurante2= new HashMap<>();
                        datosRestaurante2.put("Nombre", NombreMenu);
                        datosRestaurante2.put("TipoComida", tcomida);
                        datosRestaurante2.put("Descripcion", DescripcionMenu);
                        datosRestaurante2.put("Precio", Preciomenu);

                        myRef2.child("Menu").child("Menus").push().setValue(datosRestaurante2).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(CargarMenu.this,"Cargado con exito.", Toast.LENGTH_SHORT).show();
                                nomMenu.setText("");
                                descMenu.setText("");
                                tipocomida.setText("");
                                PrecioM.setText("");
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
