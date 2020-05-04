package com.example.bookingtime;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import ahmed.easyslider.EasySlider;
import ahmed.easyslider.SliderItem;

public class principalActivity extends AppCompatActivity {
    EasySlider easyslider;
    Button btnIrRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Mostrar icono app en el actionbar
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();

        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher1);
        actionBar.setDisplayShowHomeEnabled(true);

        btnIrRes=findViewById(R.id.btnIrRes);
        easyslider=findViewById(R.id.sliderId);

        List<SliderItem>easySliders=new ArrayList<>();
        easySliders.add(new SliderItem("",R.drawable.foto1));
        easySliders.add(new SliderItem("",R.drawable.foto2));
        easySliders.add(new SliderItem("",R.drawable.foto3));
        easySliders.add(new SliderItem("",R.drawable.foto4));
        easySliders.add(new SliderItem("",R.drawable.foto5));
        easySliders.add(new SliderItem("",R.drawable.foto6));
        easySliders.add(new SliderItem("",R.drawable.foto7));
        easySliders.add(new SliderItem("",R.drawable.foto8));
        easyslider.setPages(easySliders);


        btnIrRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurante();
            }
        });














        }

   private void restaurante(){
       startActivity(new Intent(this,Main2Activity.class));
   }
    private void lector(){
        startActivity(new Intent(this,lectorQR.class));
    }
    private void cargarResturant(){
        startActivity(new Intent(this,cargarRestaurantes.class));
    }
    private void reservaHoraMesa(){
        startActivity(new Intent(this,reservaHoraMesa.class));
}
    private void CargarMenu(){
        startActivity(new Intent(this,CargarMenu.class));
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
       MenuInflater inflater= getMenuInflater();
       inflater.inflate(R.menu.menu_bookingtime,menu);
        // Associate searchable configuration with the SearchView
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem itemMenu) {

       switch (itemMenu.getItemId()){
           case R.id.ModificarReserva:
               startActivity(new Intent(principalActivity.this,ModReserva.class));
               finish();
               break;
           case R.id.RegistrarRestaurante:
               cargarResturant();
               break;
           case R.id.CargarMenu:
               CargarMenu();
               break;
           case R.id.LectorQR:
               lector();
               break;
           case R.id.CerrarSesion:
               FirebaseAuth.getInstance().signOut();
               startActivity(new Intent(principalActivity.this,MainActivity.class));
               finish();
               break;

       }
             return super.onOptionsItemSelected(itemMenu);
    }
}



