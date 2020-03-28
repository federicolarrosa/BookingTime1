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
    private TextView tipocomida;
    private Spinner SpinnerMenu;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_menu);

        mStorageRef= FirebaseStorage.getInstance().getReference();
        nomMenu=findViewById(R.id.NomMenu);
        descMenu=findViewById(R.id.DescMenu);
        SpinnerMenu=findViewById(R.id.spinnerMenu);
        tipocomida=findViewById(R.id.tipComida);
        Button btnCargarMenu=findViewById(R.id.btnCargarMenu);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.TipoComida,android.R.layout.simple_spinner_item);
        SpinnerMenu.setAdapter(adapter);

        SpinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                tipocomida.setText(adapter.getItem(i).toString());

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

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                Map<String, Object> datosRestaurante= new HashMap<>();
                datosRestaurante.put("Nombre", NombreMenu);
                datosRestaurante.put("Tipo Comida", tcomida);
                datosRestaurante.put("Descripcion", DescripcionMenu);

                myRef.child("Restaurantes").child("Menu").push().setValue(datosRestaurante).addOnCompleteListener(new OnCompleteListener<Void>() {
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
            }
        });

    }
}
