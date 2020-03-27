package com.example.bookingtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.darwindeveloper.onecalendar.clases.Day;
import com.darwindeveloper.onecalendar.views.OneCalendarView;

import java.util.ArrayList;


public class reservafecha extends AppCompatActivity {

    private OneCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservafecha);
        // recuperar datos Intent
        String Nombre = getIntent().getStringExtra("Nombre");


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
        calendarView = (OneCalendarView) findViewById(R.id.calendarView);

        //el siguiente fragmento puede ser usado para capturar los swipes en el calendar
        calendarView.setOnCalendarChangeListener(new OneCalendarView.OnCalendarChangeListener() {




            /**
             * notifica al usuario que el calendario a cambiado al mes anterior
             */
            @Override
            public void prevMonth() {
                //hacer algo aqui

            }

            /**
             * notifica al usuario que el calendario a cambiado al mes siguiente
             */
            @Override
            public void nextMonth() {
                //hacer algo aqui
            }
        });


        //el siguiente fragmento de codigo muestra como obtener los datos de un dia en el calendario
        //ademas de realizar otras acciones
        calendarView.setOneCalendarClickListener(new OneCalendarView.OneCalendarClickListener() {

            /**
             * cuando se da click en un dia en el calendario mostrado
             *
             * @param day      un Objeto de tipo Day del cual podemos llara a su metodo getDate() para recuperar una fecha
             * @param position posicion desde 0-41, que ocupa en el calendario actual
             */

            @Override
            public void dateOnClick(Day day, int position) {

                //recuerde que en java los meses inician desde 0

                if (!calendarView.isDaySelected(position)) {
                    startActivity(new Intent(reservafecha.this,reserva.class));

                } else {
                   calendarView.removeDaySeleted(position);
                }
            }


            /**
             * cuando se da click prolongado en un dia en el calendario mostrado
             *
             * @param day      un Objeto de tipo Day del cual podemos llara a su metodo getDate() para recuperar una fecha
             * @param position posicion desde 0-41, que ocupa en el calendario actual
             */
            @Override
            public void dateOnLongClick(Day day, int position) {

            }
        });

    }

}
