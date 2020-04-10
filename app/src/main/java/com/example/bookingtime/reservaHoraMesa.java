package com.example.bookingtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class reservaHoraMesa extends AppCompatActivity  implements View.OnClickListener{

    Button M1,M2,M3,M4,M5,M6,M7,M8,M9,M10,M11,M12,M13,M14,M15,M16,M17,M18;
    Spinner horaRes,personRes;
    TextView horaSel,personSel;
    TextView nRestaurant;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_hora_mesa);

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


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Hora,R.layout.spinner_item_bookingtime);
        horaRes.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.Personas,R.layout.spinner_item_bookingtime);
        personRes.setAdapter(adapter1);

     horaRes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

             horaSel.setText("Hora: "+adapter.getItem(i).toString());

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
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
     });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.M1:
                enviarDatosIntent(M1);
                modificar("M1",M1);
                break;

            case R.id.M2:
                enviarDatosIntent(M2);
                modificar("M2",M2);
                break;

            case R.id.M3:
                enviarDatosIntent(M3);
                modificar("M3",M3);
                break;

            case R.id.M4:
                enviarDatosIntent(M4);
                modificar("M4",M4);
                break;

            case R.id.M5:
                enviarDatosIntent(M5);
                modificar("M4",M5);
                break;

            case R.id.M6:
                enviarDatosIntent(M6);
                modificar("M6",M6);
                break;

            case R.id.M7:
                enviarDatosIntent(M7);
                modificar("M7",M7);
                break;

            case R.id.M8:
                enviarDatosIntent(M8);
                modificar("M8",M8);
                break;

            case R.id.M9:
                enviarDatosIntent(M9);
                modificar("M9",M9);
                break;

            case R.id.M10:
                enviarDatosIntent(M10);
                modificar("M10",M10);
                break;

            case R.id.M11:
                enviarDatosIntent(M11);
                modificar("M11",M11);
                break;

            case R.id.M12:
                enviarDatosIntent(M12);
                modificar("M12",M12);
                break;

            case R.id.M13:
                enviarDatosIntent(M13);
                modificar("M13",M13);
                break;

            case R.id.M14:
                enviarDatosIntent(M14);
                modificar("M14",M14);
                break;

            case R.id.M15:
                enviarDatosIntent(M15);
                modificar("M15",M15);
                break;

            case R.id.M16:
                enviarDatosIntent(M16);
                modificar("M16",M16);
                break;

            case R.id.M17:
                enviarDatosIntent(M17);
                modificar("M17",M17);
                break;

            case R.id.M18:
                enviarDatosIntent(M18);
                modificar("M18",M18);
                break;
        }
    }

    private void modificar(String M,Button Btn){
        id=getIntent().getStringExtra("id");

        if (M.equals(id)){
            Btn.isEnabled();
        }


    }
    private void enviarDatosIntent(Button M){

    Intent intent = new Intent(reservaHoraMesa.this, preMenu.class);
    intent.putExtra("MesaHora",horaSel.getText().toString());
    intent.putExtra("MesaPerson",personSel.getText().toString());
    intent.putExtra("Mesa",M.getText().toString());
    intent.putExtra("Nombre",nRestaurant.getText().toString());
    intent.putExtra("id","M"+M);
    this.startActivity(intent);
    M.setEnabled(false);
    M.setText("Reservado");
}
}
