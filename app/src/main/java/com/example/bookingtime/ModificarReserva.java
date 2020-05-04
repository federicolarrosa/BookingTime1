package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import net.glxn.qrgen.android.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ModificarReserva extends AppCompatActivity implements View.OnClickListener {
    EditText editFecha;
    Spinner spinnerHora,spinnerMesa;
    Button btnMM;
    int dia,mes,ano;
    String id,key,Mesas,seleccionHora,diaActual;
    Map<String,Object>Modreserva=new HashMap<>();
    DatabaseReference reference2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    List<HorasSpinner> ListaHora = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_reserva);

        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);

        editFecha=findViewById(R.id.editFecha);
        spinnerHora=findViewById(R.id.spinnerHora);
        spinnerMesa=findViewById(R.id.spinnerMesa);
        btnMM=findViewById(R.id.btnMM);
        editFecha.setOnClickListener(this);
        btnMM.setOnClickListener(this);

        id=getIntent().getStringExtra("id");
        key=getIntent().getStringExtra("key");


        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.Mesas,R.layout.spinner_item_bookingtime);
        spinnerMesa.setAdapter(adapter1);

        spinnerMesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Mesas= adapter1.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        CargarSpinnerFirebase();
        spinnerHora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionHora=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editFecha:
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            diaActual = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

            DatePickerDialog datepickerdialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    editFecha.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                }
            },ano,mes,dia);
            datepickerdialog.show();
            break;
            case R.id.btnMM:
                String editFecha1=editFecha.getText().toString();
                String seleccionarHora1=seleccionHora;
                String Mesas1=Mesas;
                String ID="M"+id;
                String QR= ID+" "+seleccionHora;

                Modreserva.put("dia",editFecha1);
                Modreserva.put("HoraR",seleccionarHora1);
                Modreserva.put("id",Mesas1);
                Query modificarMesa= myRef.child("Usuarios").child("Mesa Reservada").orderByChild("id").equalTo(id);
                modificarMesa.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot MesaSnapshot: dataSnapshot.getChildren()) {
                             MesaSnapshot.getRef().updateChildren(Modreserva);
                        }
                        Toast.makeText(ModificarReserva.this, "Se modifico su Reserva", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ModificarReserva.this,ModReserva.class));
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(ModificarReserva.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
                    }
                });
                final int ALTURA_CODIGO = 500, ANCHURA_CODIGO = 500;
                ByteArrayOutputStream byteArrayOutputStream = QRCode.from(QR).withSize(ANCHURA_CODIGO, ALTURA_CODIGO).stream();
                // E intentar guardar
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/"+ID+".png");
                    byteArrayOutputStream.writeTo(fos);
                    Toast.makeText(ModificarReserva.this, "Código guardado", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
                break;
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

                    ArrayAdapter<HorasSpinner> arrayadapter = new ArrayAdapter<>(ModificarReserva.this, R.layout.support_simple_spinner_dropdown_item, ListaHora);
                    spinnerHora.setAdapter(arrayadapter);
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){
                Toast.makeText(ModificarReserva.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
