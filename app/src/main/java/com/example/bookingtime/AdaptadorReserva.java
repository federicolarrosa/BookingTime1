package com.example.bookingtime;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class AdaptadorReserva extends RecyclerView.Adapter<AdaptadorReserva.ReservaViewHolder>  {
    Context context;
    ArrayList<pojo> Reservas;

    public AdaptadorReserva(Context context, ArrayList<pojo> reservas) {
        this.context = context;
        this.Reservas = reservas;
    }

    @NonNull
    @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reserva_carview, parent, false);
        return new ReservaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder reservaViewHolder, int position) {
        reservaViewHolder.agregardatos(Reservas.get(position));

        reservaViewHolder.setOnClickListeners();
        reservaViewHolder.setIsRecyclable(false);

    }

    @Override
    public int getItemCount() {
        return Reservas.size();
    }



    public static class ReservaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         Context context;
        private TextView nombre_ReservaCardview,Dia_ReservaCardview,Hora_ReservaCardview,Mesa_ReservaCardview,PerReserva_ReservaCardview;
        private Button btnModificar,btnQR,btnEliminar;
        String id,key;
        public ReservaViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            nombre_ReservaCardview= itemView.findViewById(R.id.nombre_ReservaCardview);
            Dia_ReservaCardview = itemView.findViewById(R.id.Dia_ReservaCardView);
            Hora_ReservaCardview = itemView.findViewById(R.id.Hora_ReservaCardView);
            Mesa_ReservaCardview = itemView.findViewById(R.id.Mesa_ReservaCardView);
            PerReserva_ReservaCardview = itemView.findViewById(R.id.PerReserva_ReservaCardView);
            btnQR=itemView.findViewById(R.id.btnQR);
            btnModificar=itemView.findViewById(R.id.btnModificar);
            btnEliminar=itemView.findViewById(R.id.btnEliminar);

        }
      void setOnClickListeners(){
           btnQR.setOnClickListener(this);
           btnModificar.setOnClickListener(this);
           btnEliminar.setOnClickListener(this);
      }

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.btnQR:
                    intent= new Intent(context, MostrarQR.class);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                    ((Activity)context).finish();

                    break;
                case R.id.btnModificar:

                 intent=new Intent(context,ModificarReserva.class);
                 intent.putExtra("id",id);
                    intent.putExtra("key",key);
                 context.startActivity(intent);

                    break;
                case R.id.btnEliminar:
                    int pos=getAdapterPosition();

                    mostrarDialogo();

                    break;

            }

        }
    public void agregardatos(pojo reservas) {
        nombre_ReservaCardview.setText(reservas.getNombreR());
        Dia_ReservaCardview.setText("Dia: " + reservas.getDia());
        Hora_ReservaCardview.setText("Hora: " + reservas.getHoraR());
        Mesa_ReservaCardview.setText("Mesa: " + reservas.getId());
        PerReserva_ReservaCardview.setText("Personas por Mesa: " + reservas.getPersona());
        id=reservas.getId();
        key=reservas.getKey();





        }

        private void mostrarDialogo(){
            AlertDialog.Builder builder= new AlertDialog.Builder(context);
            builder.setTitle("Eliminar");

            builder.setMessage("¿Estas seguro de Eliminar la reserva?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    // borra la seleccion guardada de firebase al darle click al boton pagar

                    Query BorrarMesa= myRef.child("Usuarios").child("Mesa Reservada").orderByChild("id").equalTo(id);
                    BorrarMesa.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot MesaSnapshot: dataSnapshot.getChildren()) {
                                MesaSnapshot.getRef().removeValue();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(context, "Algo salio mal", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Toast.makeText(context,"Reserva eliminada",Toast.LENGTH_SHORT).show();


                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(context,"Cancelado",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }).setCancelable(false).show();
        }

        private void mostrarDialogoModificar(){
            AlertDialog.Builder builder= new AlertDialog.Builder(context);
            builder.setTitle("Modificar");
            builder.setMessage("¿Estas seguro de modificar la reserva?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    // borra la seleccion guardada de firebase al darle click al boton pagar

                    Query BorrarMesa= myRef.child("Usuarios").child("Mesa Reservada").orderByChild("id").equalTo(id);
                    BorrarMesa.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot MesaSnapshot: dataSnapshot.getChildren()) {
                                MesaSnapshot.getRef().removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(context, "Algo salio mal", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(context,"Reserva eliminada",Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(context,"Cancelado",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }).setCancelable(false).show();

        }

    }

}
