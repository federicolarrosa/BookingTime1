package com.example.bookingtime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Registro extends AppCompatActivity {
    private static final String TAG = "BookingTime";
    private FirebaseAuth mAuth;
    private EditText EmailField;
    private EditText PasswordField;
    private EditText mNombre;
    private EditText mApellido;
    private EditText mNumero;
    private EditText mDireccion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Intanciar con layout
        setContentView(R.layout.activity_registro);
        mNombre = findViewById(R.id.Name);
        mApellido = findViewById(R.id.Lastname);
        mNumero = findViewById(R.id.Phone);
        mDireccion = findViewById(R.id.direccion);
        EmailField= findViewById(R.id.regEmail);
        PasswordField= findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();


        //Escribir en la base de datos Firebase

        Button btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre=mNombre.getText().toString();
                int telefono=Integer.parseInt( mNumero.getText().toString());
                String direccion=mDireccion.getText().toString();
                String apellido=mApellido.getText().toString();
                String password=PasswordField.getText().toString();
                String email=EmailField.getText().toString();

                cargardatos(nombre, telefono, direccion, apellido, password, email);
                registrarse();
            }
        });

           }

    private void cargardatos(String nombre, int telefono, String direccion, String apellido, String rpassword, String remail) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Map<String, Object> datosUsuario= new HashMap<>();
        datosUsuario.put("Nombre", nombre);
        datosUsuario.put("Telefono", telefono);
        datosUsuario.put("Direccion", direccion);
        datosUsuario.put("Apellido", apellido);
        datosUsuario.put("Password", rpassword);
        datosUsuario.put("Email", remail);


       myRef.child("Usuarios").push().setValue(datosUsuario);
    }


    private void registrarse() {

         String email=EmailField.getText().toString();
         String password=PasswordField.getText().toString();

         mAuth.createUserWithEmailAndPassword(email, password)
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {
                             // Sign in success, update UI with the signed-in user's information
                             Log.d(TAG, "createUserWithEmail:success");
                             FirebaseUser user = mAuth.getCurrentUser();
                             updateUI(user);
                         } else {
                             // If sign in fails, display a message to the user.
                             Log.w(TAG, "createUserWithEmail:failure", task.getException());
                             Toast.makeText(Registro.this, "Authentication failed.",
                                     Toast.LENGTH_SHORT).show();
                             updateUI(null);
                         }


                     }
                 });
     }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            Toast.makeText(this,"No se a podido registrar intente mas tarde",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,principalActivity.class));
        }

    }
}

