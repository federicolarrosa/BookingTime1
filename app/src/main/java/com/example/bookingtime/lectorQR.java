package com.example.bookingtime;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class lectorQR extends AppCompatActivity {

    EditText txCodigo;
    Button btnEsc,btnRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_q_r);

        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);


        txCodigo= findViewById(R.id.txcodigo);
        btnEsc= findViewById(R.id.btnEscan);
      //  btnRes= findViewById(R.id.btnRes);
        // boton generar
      /*  btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reserva();
            }
        });*/


        // Accion boton escanear
        btnEsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escaner();
            }
        });
    }
    /*public void reserva(){
        startActivity(new Intent(this,reserva.class));
    }*/

    // Metodo para escanear
    public void escaner(){

        new IntentIntegrator(this).initiateScan();
       /* IntentIntegrator intent = new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);

        intent.setPrompt("ESCANEAR CODIGO");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.setOrientationLocked(false);
        intent.initiateScan();*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this,"Canselaste Escaneo", Toast.LENGTH_LONG).show();
            }else{
                txCodigo.setText(result.getContents().toString());
            }

        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}


