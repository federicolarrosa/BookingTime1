package com.example.bookingtime;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorPago extends RecyclerView.Adapter<AdaptadorPago.ViewHolderPago>{
    Context context;
    ArrayList<MostrarPago> PagoRestaurante;
    public AdaptadorPago(Context context,ArrayList<MostrarPago>pagoRestaurante){
        this.context = context;
        this.PagoRestaurante = pagoRestaurante;
    }


    @NonNull
    @Override
    public AdaptadorPago.ViewHolderPago onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seleccion_pago,parent,false);

        return new ViewHolderPago(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPago.ViewHolderPago holder, int position) {
        holder.agregardatos(PagoRestaurante.get(position));


    }

    @Override
    public int getItemCount() {
        return PagoRestaurante.size();
    }

    public class ViewHolderPago extends RecyclerView.ViewHolder {
        TextView Persona;
        TextView MenuSel;
        TextView BebidasSel;
        TextView PostresSel;
        TextView MenPrecio;
        TextView BebPrecio;
        TextView PosPrecio;
       /* TextView MCantUn;
        TextView BCantUn;
        TextView PCantUn;*/

        public ViewHolderPago(@NonNull View itemView) {
            super(itemView);
            Persona=itemView.findViewById(R.id.txtPersona);
            MenuSel=itemView.findViewById(R.id.txtMenu);
            BebidasSel=itemView.findViewById(R.id.txtBebida);
            PostresSel=itemView.findViewById(R.id.txtPostre);
            MenPrecio=itemView.findViewById(R.id.txtMprecio);
            BebPrecio=itemView.findViewById(R.id.txtBprecio);
            PosPrecio=itemView.findViewById(R.id.txtPprecio);
          /*  MCantUn=itemView.findViewById(R.id.txtMcant);
            BCantUn=itemView.findViewById(R.id.txtBcant);
            PCantUn=itemView.findViewById(R.id.txtPcant);*/


        }

        public void agregardatos(MostrarPago mostrarPago) {
             Persona.setText(String.valueOf(mostrarPago.getPersona()));
             MenuSel.setText(mostrarPago.getMenuSel());
             BebidasSel.setText(mostrarPago.getBebidasSel());
             PostresSel.setText(mostrarPago.getPostresSel());
             MenPrecio.setText(mostrarPago.getMenPrecio());
             BebPrecio.setText(mostrarPago.getBebPrecio());
             PosPrecio.setText(mostrarPago.getPosPrecio());
            /* MCantUn.setText(String.valueOf(mostrarPago.getMCantUn()));
             BCantUn.setText(String.valueOf(mostrarPago.getBCantUn()));
             PCantUn.setText(String.valueOf(mostrarPago.getPCantUn());*/





                }
            }




        }


