package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.glxn.qrgen.android.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class reserva extends AppCompatActivity {

    private static final int CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO = 1;
    private static final int ALTURA_CODIGO = 500, ANCHURA_CODIGO = 500;

    private TextView etTextoParaCodigo;
    private TextView horafin;
    private TextView personfin;

    Button btnGenerador,btnGuardar;
    private boolean tienePermisoParaEscribir = true; // Para los permisos en tiempo de ejecución

    private String obtenerTextoParaCodigo() {
        etTextoParaCodigo.setError(null);
        String posibleTexto = etTextoParaCodigo.getText().toString();
        if (posibleTexto.isEmpty()) {
            etTextoParaCodigo.setError("Escribe el texto del código QR");
            etTextoParaCodigo.requestFocus();
        }
        return posibleTexto;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        personfin=findViewById(R.id.personfinal);
        horafin=findViewById(R.id.horafinal);
        etTextoParaCodigo = findViewById(R.id.mesa);
        String mesahora=getIntent().getStringExtra("MesaHora");
        String mesapers=getIntent().getStringExtra("MesaPerson");
        String mesa=getIntent().getStringExtra("Mesa");

        etTextoParaCodigo.setText(mesa);
        horafin.setText(mesahora);
        personfin.setText(mesapers);

        final ImageView imagenCodigo = findViewById(R.id.ivCodigoGenerado);

        btnGenerador = findViewById(R.id.btnGenerador);
        btnGuardar = findViewById(R.id.btnGuardar);


        btnGenerador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = obtenerTextoParaCodigo();
                if (texto.isEmpty()) return;

                Bitmap bitmap = QRCode.from(texto).withSize(ANCHURA_CODIGO, ALTURA_CODIGO).bitmap();
                imagenCodigo.setImageBitmap(bitmap);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = obtenerTextoParaCodigo();
                if (texto.isEmpty()) return;
                if (!tienePermisoParaEscribir) {
                    noTienePermiso();
                    return;
                }
                // Crear stream del código QR
                ByteArrayOutputStream byteArrayOutputStream = QRCode.from(texto).withSize(ANCHURA_CODIGO, ALTURA_CODIGO).stream();
                // E intentar guardar
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/codigo.png");
                    byteArrayOutputStream.writeTo(fos);
                    Toast.makeText(reserva.this, "Código guardado", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /*
         * Debería pedirse cuando se está a punto de realizar la acción, no
         * al inicio; pero ese no es el propósito de este código
         * */
        verificarYPedirPermisos();

    }


    private void verificarYPedirPermisos() {
        if (
                ContextCompat.checkSelfPermission(
                        reserva.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        ==
                        PackageManager.PERMISSION_GRANTED
        ) {
            // En caso de que haya dado permisos ponemos la bandera en true
            tienePermisoParaEscribir = true;
        } else {
            // Si no, entonces pedimos permisos
            ActivityCompat.requestPermissions(reserva.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO);
        }
    }

    private void noTienePermiso() {
        Toast.makeText(reserva.this, "No has dado permiso para escribir", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // SÍ dieron permiso
                    tienePermisoParaEscribir = true;

                } else {
                    // NO dieron permiso
                    noTienePermiso();
                }
        }
    }
}

