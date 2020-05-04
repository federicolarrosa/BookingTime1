package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class reservaHoraMesa extends AppCompatActivity  implements View.OnClickListener{

    Button M1,M2,M3,M4,M5,M6,M7,M8,M9,M10,M11,M12,M13,M14,M15,M16,M17,M18;
    Spinner horaRes,personRes;
    TextView horaSel,personSel;
    TextView nRestaurant;
    String id,seleccionHora,Dia;
    Boolean completo=false,Modificar;
    DatabaseReference reference,reference1,reference2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    List<HorasSpinner> ListaHora = new ArrayList<>();
    ArrayList<pojo>ListaReserva=new ArrayList<>();
    ArrayList<String> Conteo=new ArrayList<>();
    Map<String,Object> estadoBtn = new HashMap<>();
    Map<String, Object> ListaReserva1= new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_hora_mesa);

        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);

        M1=findViewById(R.id.M1);
        M2=findViewById(R.id.M2);
        M3=findViewById(R.id.M3);
        M4=findViewById(R.id.M4);
        M5=findViewById(R.id.M5);
        M6=findViewById(R.id.M6);
        M7=findViewById(R.id.M7);
        M8=findViewById(R.id.M8);
        M9=findViewById(R.id.M9);
        M10=findViewById(R.id.M10);
        M11=findViewById(R.id.M11);
        M12=findViewById(R.id.M12);
        M13=findViewById(R.id.M13);
        M14=findViewById(R.id.M14);
        M15=findViewById(R.id.M15);
        M16=findViewById(R.id.M16);
        M17=findViewById(R.id.M17);
        M18=findViewById(R.id.M18);
        horaRes=findViewById(R.id.horaRes);
        personRes=findViewById(R.id.personRes);
        horaSel=findViewById(R.id.horaSel);
        personSel=findViewById(R.id.personSel);
        nRestaurant=findViewById(R.id.nRest);

        String nomRestaurant=getIntent().getStringExtra("Nombre");
        nRestaurant.setText(nomRestaurant);
        Dia=getIntent().getStringExtra("dia");
       // Modificar=getIntent().getBooleanExtra("Modificar",true);

        M1.setOnClickListener(this);
        M2.setOnClickListener(this);
        M3.setOnClickListener(this);
        M4.setOnClickListener(this);
        M5.setOnClickListener(this);
        M6.setOnClickListener(this);
        M7.setOnClickListener(this);
        M8.setOnClickListener(this);
        M9.setOnClickListener(this);
        M10.setOnClickListener(this);
        M11.setOnClickListener(this);
        M12.setOnClickListener(this);
        M13.setOnClickListener(this);
        M14.setOnClickListener(this);
        M15.setOnClickListener(this);
        M16.setOnClickListener(this);
        M17.setOnClickListener(this);
        M18.setOnClickListener(this);


        obtenerDatosFirebase();

        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.Personas,R.layout.spinner_item_bookingtime);
        personRes.setAdapter(adapter1);
        CargarSpinnerFirebase();
        horaRes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                seleccionHora=adapterView.getItemAtPosition(i).toString();


                if (Conteo.contains(seleccionHora) && Conteo.size()==18){
                    horaSel.setText("0 mesas disponibles");
                    horaSel.setEnabled(false);
                    completo=true;
                }else{
                    horaSel.setText("Hora: "+seleccionHora);
                    horaSel.setEnabled(true);
                    completo=false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        personRes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

             personSel.setText(adapter1.getItem(i).toString());

         }

         @Override
         public void onNothingSelected (AdapterView < ? > adapterView){

         }


     });


/*

        reserva(M1);
        reserva(M2);
        reserva(M3);
        reserva(M4);
        reserva(M5);
        reserva(M6);
        reserva(M7);
        reserva(M8);
        reserva(M9);
        reserva(M10);
        reserva(M11);
        reserva(M12);
        reserva(M13);
        reserva(M14);
        reserva(M15);
        reserva(M16);
        reserva(M17);
        reserva(M18);
*/



         }


         @Override
    public void onClick(@NonNull View view) {
        switch (view.getId()){

            case R.id.M1:
               // obtenerDatosFirebase(M1,horaseleccionada,completo);
                enviarDatosIntent(M1);
                modificar("M1",M1);
                break;

            case R.id.M2:
                // obtenerDatosFirebase(M2,horaseleccionada,completo);
                enviarDatosIntent(M2);
                modificar("M2",M2);
                break;

            case R.id.M3:
                //obtenerDatosFirebase(M3,horaseleccionada,completo);
                enviarDatosIntent(M3);
                modificar("M3",M3);
                break;

            case R.id.M4:
                //obtenerDatosFirebase(M4,horaseleccionada,completo);
                enviarDatosIntent(M4);
                modificar("M4",M4);
                break;

            case R.id.M5:
                // obtenerDatosFirebase(M5,horaseleccionada,completo);
                enviarDatosIntent(M5);
                modificar("M4",M5);
                break;

            case R.id.M6:
                // obtenerDatosFirebase(M6,horaseleccionada,completo);
                enviarDatosIntent(M6);
                modificar("M6",M6);
                break;

            case R.id.M7:
                // obtenerDatosFirebase(M7,horaseleccionada,completo);
                enviarDatosIntent(M7);
                modificar("M7",M7);
                break;

            case R.id.M8:
                //obtenerDatosFirebase(M8,horaseleccionada,completo);
                enviarDatosIntent(M8);
                modificar("M8",M8);
                break;

            case R.id.M9:
                //obtenerDatosFirebase(M9,horaseleccionada,completo);
                enviarDatosIntent(M9);
                modificar("M9",M9);
                break;

            case R.id.M10:
                //obtenerDatosFirebase(M10,horaseleccionada,completo);
                enviarDatosIntent(M10);
                modificar("M10",M10);
                break;

            case R.id.M11:
                //obtenerDatosFirebase(M11,horaseleccionada,completo);
                enviarDatosIntent(M11);
                modificar("M11",M11);
                break;

            case R.id.M12:
                //obtenerDatosFirebase(M12,horaseleccionada,completo);
                enviarDatosIntent(M12);
                modificar("M12",M12);
                break;

            case R.id.M13:
                //obtenerDatosFirebase(M13,horaseleccionada,completo);
                enviarDatosIntent(M13);
                modificar("M13",M13);
                break;

            case R.id.M14:
                //obtenerDatosFirebase(M14,horaseleccionada,completo);
                enviarDatosIntent(M14);
                modificar("M14",M14);
                break;

            case R.id.M15:
                //obtenerDatosFirebase(M15,horaseleccionada,completo);
                enviarDatosIntent(M15);
                modificar("M15",M15);
                break;

            case R.id.M16:
                //obtenerDatosFirebase(M16,horaseleccionada,completo);
                enviarDatosIntent(M16);
                modificar("M16",M16);
                break;

            case R.id.M17:
                //obtenerDatosFirebase(M17,horaseleccionada,completo);
                enviarDatosIntent(M17);
                modificar("M17",M17);
                break;

            case R.id.M18:
                //obtenerDatosFirebase(M18,horaseleccionada,completo);
                enviarDatosIntent(M18);
                modificar("M18",M18);
                break;
        }
    }

    private void modificar(String M,Button Btn){



        String nomRestaurante=getIntent().getStringExtra("Nombre");
        nRestaurant.setText(nomRestaurante);
        id=getIntent().getStringExtra("id");

        //key=getIntent().getStringExtra("key");

        if (M.equals(id)) {
            Btn.setEnabled(true);

        }
    }



    private void obtenerDatosFirebase() {

        // Referencia al DB Restaurantes/menu en Firebase y agregar los menus al array Listafinal
        reference = FirebaseDatabase.getInstance().getReference().child("Usuarios").child("Mesa Reservada");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    pojo reserva = dataSnapshot1.getValue(pojo.class);

                    EstadoBtn(M1,reserva);
                    EstadoBtn(M2,reserva);
                    EstadoBtn(M3,reserva);
                    EstadoBtn(M4,reserva);
                    EstadoBtn(M5,reserva);
                    EstadoBtn(M6,reserva);
                    EstadoBtn(M7,reserva);
                    EstadoBtn(M8,reserva);
                    EstadoBtn(M9,reserva);
                    EstadoBtn(M10,reserva);
                    EstadoBtn(M11,reserva);
                    EstadoBtn(M12,reserva);
                    EstadoBtn(M13,reserva);
                    EstadoBtn(M14,reserva);
                    EstadoBtn(M15,reserva);
                    EstadoBtn(M16,reserva);
                    EstadoBtn(M17,reserva);
                    EstadoBtn(M18,reserva);
                    ListaReserva.add(reserva);
                }


            }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(reservaHoraMesa.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void reserva(Button Mbtn){
        for (pojo re:ListaReserva) {
            Log.d("lista1", String.valueOf(re.getReservado()));
            if (!Mbtn.isEnabled()) {
                       Mbtn.setText("Reservado");
                    if (!Mbtn.isEnabled()) {
                        Conteo.add(re.getHoraR());

                }
            } else {
                if (!re.getHoraR().contains(seleccionHora)) {
                    Mbtn.setText("disponible");
                    if (Mbtn.getText().toString().contains("Reservado")) {
                        Log.d("if", "se cumple");
                        Mbtn.setText("disponible");
                    }
                }
            }
        }
                }


    private void CargarSpinnerFirebase(){

        reference2 = FirebaseDatabase.getInstance().getReference().child("DatosSpinner").child("Horas");
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        //Este Id no es el mismo que el de la reserva este es el id de la hora
                        String Id = dataSnapshot2.getKey();
                        String hora = dataSnapshot2.child("hora").getValue().toString();
                        ListaHora.add(new HorasSpinner(Id, hora));

                    }

                    ArrayAdapter<HorasSpinner> arrayadapter = new ArrayAdapter<>(reservaHoraMesa.this, R.layout.support_simple_spinner_dropdown_item, ListaHora);
                    horaRes.setAdapter(arrayadapter);
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){
                Toast.makeText(reservaHoraMesa.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });

    }





    private void enviarDatosIntent(Button M){

            String key = myRef.child("Usuarios").child("Mesa Reservada").push().getKey();
            M.setEnabled(false);
            String person=personSel.getText().toString();
            String nombrerest=nRestaurant.getText().toString();
            String Diasel=Dia;
            Boolean me = M.isEnabled();
            String Boton1 = M.getText().toString();
            String horaSeleccionada = seleccionHora;
            ListaReserva1.put("Reservado", me);
            ListaReserva1.put("id", Boton1);
            ListaReserva1.put("HoraR", horaSeleccionada);
            ListaReserva1.put("Persona",person);
            ListaReserva1.put("NombreR",nombrerest);
            ListaReserva1.put("dia",Diasel);
            ListaReserva1.put("key",key);


        myRef.child("Usuarios").child("Mesa Reservada").push().setValue(ListaReserva1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(reservaHoraMesa.this,"Reservado.", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(reservaHoraMesa.this,"Ups.. hubo un problema Intenta nuevamente.", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = new Intent(reservaHoraMesa.this, preMenu.class);
        intent.putExtra("MesaHora",horaSel.getText().toString());
        intent.putExtra("MesaPerson",personSel.getText().toString());
        intent.putExtra("Mesa",M.getText().toString());
        intent.putExtra("Nombre",nRestaurant.getText().toString());
        intent.putExtra("id","M"+M.getText().toString());
        intent.putExtra("Dia",Dia);

        this.startActivity(intent);
        finish();
  }
    private void EstadoBtn(Button M,pojo reserva) {

        String referencia = myRef.child("EstadosBtn").push().getKey();


        if (true) {

                if (!reserva.getReservado() && reserva.getId().contains(M.getText().toString())) {
                    M.setText("Reservado");
                    if (M.getText().toString().equals("Reservado")) {
                        M.setEnabled(false);

                    }
                    Boolean me = M.isEnabled();
                    String Boton1 = M.getText().toString();
                    estadoBtn.put("IDBtn", Boton1);
                    estadoBtn.put("EstadoBtn", me);
                } else if(reserva.getId().contains(M.getText().toString())){

                    M.setEnabled(true);

                    Boolean me = M.isEnabled();
                    String Boton1 = M.getText().toString();
                    estadoBtn.put("IDBtn", Boton1);
                    estadoBtn.put("EstadoBtn", me);


              }
            }
            myRef.child("EstadosBtn").child(M.getText().toString()).setValue(estadoBtn);
        }
    }

