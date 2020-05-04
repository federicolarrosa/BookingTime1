package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class cargarRestaurantes extends AppCompatActivity {
    private EditText mNombre;
    private EditText mtipoComida;
    private EditText mResumen;
    private EditText mDireccion;
    private EditText mEmail;
    private EditText mTelefono;
    private CheckBox tarjetas;
    private CheckBox estacionamiento;
    private CheckBox fumar;
    private CheckBox wifi;

    private StorageReference mStorageRef;
    private ImageView imagen;
    private final static int GALLERY_INTENT = 1;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_restaurantes);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);

        mNombre = findViewById(R.id.Name);
        mtipoComida = findViewById(R.id.tipoComida);
        mResumen = findViewById(R.id.Resumen);
        mDireccion = findViewById(R.id.Rdireccion);
        mEmail = findViewById(R.id.Remail);
        mTelefono = findViewById(R.id.Rtelefono);
        imagen = findViewById(R.id.imagen);
        tarjetas = findViewById(R.id.tarjetas);
        estacionamiento = findViewById(R.id.estacionamiento);
        fumar = findViewById(R.id.fumar);
        wifi = findViewById(R.id.wifi);

        Button btnRegRes= findViewById(R.id.btnRegRes);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });
        btnRegRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre=mNombre.getText().toString();
                String tipoComida=mtipoComida.getText().toString();
                String Resumen=mResumen.getText().toString();
                String Direccion=mDireccion.getText().toString();
                String Email=mEmail.getText().toString();
                String Telefono=mTelefono.getText().toString();
                boolean Tarjetas = tarjetas.isChecked();
                boolean Estacionamiento = estacionamiento.isChecked();
                boolean Fumar = fumar.isChecked();
                boolean Wifi = wifi.isChecked();


                cargardatos(nombre, tipoComida, Resumen, Direccion, Email, Telefono, filePath, Tarjetas, Fumar, Estacionamiento, Wifi);
            }
        });
    }

    private void cargardatos(String nombre, String tipoComida, String Resumen, String Direccion, String Email, String Telefono, Uri filePath, boolean Tarjetas, boolean Fumar, boolean Estacionamiento, boolean Wifi) {

         if (mNombre.getText().toString().isEmpty()) {
            Toast.makeText(cargarRestaurantes.this, "Debe Agregar Nombre De Resturante.", Toast.LENGTH_SHORT).show();

        } else if (mtipoComida.getText().toString().isEmpty()) {
            Toast.makeText(cargarRestaurantes.this, "Eliga tipo comida.", Toast.LENGTH_SHORT).show();

        } else if (mDireccion.getText().toString().isEmpty()) {
            Toast.makeText(cargarRestaurantes.this, "Debe agregar una direccion.", Toast.LENGTH_SHORT).show();

        } else if (mEmail.getText().toString().isEmpty()) {
            Toast.makeText(cargarRestaurantes.this, "Debe agregar un correo electronico.", Toast.LENGTH_SHORT).show();

        } else if (mTelefono.getText().toString().isEmpty()) {
            Toast.makeText(cargarRestaurantes.this, "Debe agregar un numero de Telefono.", Toast.LENGTH_SHORT).show();

        } else if (mResumen.getText().toString().isEmpty()) {
            Toast.makeText(cargarRestaurantes.this, "Debe agregar un Resumen del Resturante.", Toast.LENGTH_SHORT).show();

        }


            if (filePath != null) {
                StorageReference fotoref = mStorageRef.child("imagenes").child(filePath.getLastPathSegment());
                fotoref.putFile(filePath).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw new Exception();
                        }
                        return fotoref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadLink = task.getResult();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference();
                            Map<String, Object> datosRestaurante = new HashMap<>();
                            datosRestaurante.put("Nombre", nombre);
                            datosRestaurante.put("tipoComida", tipoComida);
                            datosRestaurante.put("Resumen", Resumen);
                            datosRestaurante.put("Direccion", Direccion);
                            datosRestaurante.put("Email", Email);
                            datosRestaurante.put("Telefono", Telefono);
                            datosRestaurante.put("Tarjetas", Tarjetas);
                            datosRestaurante.put("Fumar", Fumar);
                            datosRestaurante.put("Estacionamiento", Estacionamiento);
                            datosRestaurante.put("Wifi", Wifi);
                            datosRestaurante.put("Imagen", downloadLink.toString());

                            myRef.child("Restaurantes").push().setValue(datosRestaurante).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    Toast.makeText(cargarRestaurantes.this, "Cargado con exito.", Toast.LENGTH_SHORT).show();
                                    mDireccion.setText("");
                                    mEmail.setText("");
                                    mNombre.setText("");
                                    mResumen.setText("");
                                    mTelefono.setText("");
                                    mtipoComida.setText("");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(cargarRestaurantes.this, "Ups.. hubo un problema Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }else{
                Toast.makeText(cargarRestaurantes.this, "Debe agregar una imagen.", Toast.LENGTH_SHORT).show();
            }

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePath= data.getData();
            try {
                Bitmap bitmapImagen= MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imagen.setImageBitmap(bitmapImagen);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
