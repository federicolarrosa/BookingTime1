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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "BookingTime";
    private FirebaseAuth mAuth;
    private EditText mEmailField, mPasswordField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEmailField= findViewById(R.id.email);
        mPasswordField= findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();



        Button btnlog = findViewById(R.id.btnlog);
        Button btnreg = findViewById(R.id.btnreg);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });



        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUsuario();
            }
        });



    }





    private void updateUI(FirebaseUser user) {
        if(user != null){
            Toast.makeText(MainActivity.this,"Sesión Iniciada",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,principalActivity.class));
        }else{
            Toast.makeText(MainActivity.this,"Usuario No Registrado",Toast.LENGTH_LONG).show(); }

    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void loginUsuario(){

        String email=mEmailField.getText().toString();
        String password=mPasswordField.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });


    }

    private void registro(){
        startActivity(new Intent(this,Registro.class));
    }



}

