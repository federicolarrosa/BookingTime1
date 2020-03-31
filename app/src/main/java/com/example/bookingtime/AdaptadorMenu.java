package com.example.bookingtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorMenu extends RecyclerView.Adapter<AdaptadorMenu.ViewHolderMenu> {
    Context context;
    ArrayList<MenuRestaurante> MenuRestaurante;

    public AdaptadorMenu(Context context, ArrayList<com.example.bookingtime.MenuRestaurante> menuRestaurante) {
        this.context = context;
        this.MenuRestaurante = menuRestaurante;
    }




    @NonNull
    @Override
    public ViewHolderMenu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);

        return new ViewHolderMenu(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMenu holder, int position) {

        holder.agregarDatos(MenuRestaurante.get(position));

    }
    //cantidad elementos lista
    @Override
    public int getItemCount() {
        return MenuRestaurante.size();
    }
     // Intanciar objetos con el layout item_menu
    public class ViewHolderMenu extends RecyclerView.ViewHolder {
        CheckBox nMenu;
        TextView tComida;
        TextView tDescMenu;

        public ViewHolderMenu(@NonNull View itemView) {
            super(itemView);
            nMenu=itemView.findViewById(R.id.nmenu);
            tComida= itemView.findViewById(R.id.tComida);
            tDescMenu= itemView.findViewById(R.id.tDesc);
        }

         public void agregarDatos(com.example.bookingtime.MenuRestaurante menuRestaurante) {
             nMenu.setText(menuRestaurante.getNombre());
             tComida.setText(menuRestaurante.getTipoComida());
             tDescMenu.setText(menuRestaurante.getDescripcion());
         }
     }
}
