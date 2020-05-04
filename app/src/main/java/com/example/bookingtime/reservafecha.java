package com.example.bookingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.darwindeveloper.onecalendar.clases.Day;
import com.darwindeveloper.onecalendar.views.OneCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class reservafecha extends AppCompatActivity {

    private CalendarView calendarView;
    String diaActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservafecha);
        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);

        // recuperar datos Intent
        String Nombre = getIntent().getStringExtra("Nombre");
        //Boolean Modificar=getIntent().getBooleanExtra("Modificar",true);

        TextView nombreRestaurant = findViewById(R.id.Nombre);


        nombreRestaurant.setText(Nombre);
        /*Glide
                .with(reservafecha.this)
                .load(Imagen)
                .fitCenter()
                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .into(ImagenRest);
*/
        // Calendario
        calendarView =findViewById(R.id.calendarView);
        diaActual = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        //convertir fecha en millesegundos
        String myDate = diaActual;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timeInMillis = date.getTime();
        calendarView.setMinDate(timeInMillis);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {




                String dia = dayOfMonth + "/" + (month +1)+ "/" + year;
                Log.d("dia", dia);

                // mandar datos a reservaHoraMEsa

                    Intent intent = new Intent(reservafecha.this, reservaHoraMesa.class);
                    intent.putExtra("Nombre",Nombre);
                    intent.putExtra("dia",dia);
                    //intent.putExtra("Modificar",Modificar);
                    startActivity(intent);
                    finish();



            }

        });
    }

}



