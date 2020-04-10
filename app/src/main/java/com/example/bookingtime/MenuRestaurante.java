package com.example.bookingtime;



public class MenuRestaurante {
private String Nombre;
private String TipoComida;
private String Descripcion;
private Boolean Seleccion ;
public MenuRestaurante() {
}



    public MenuRestaurante(String Nombre, String TipoComida, String Descripcion, Boolean seleccion ) {
    this.Nombre = Nombre;
    this.TipoComida = TipoComida;
    this.Descripcion = Descripcion;
    this.Seleccion = seleccion;
    }


    public String getNombre() {
    return Nombre;
  }



public void setNombre(String Nombre) {
    Nombre = Nombre;
}

public String getTipoComida() {
    return TipoComida;
}

 public void setTipoComida(String TipoComida) {
    TipoComida = TipoComida;
 }

 public String getDescripcion() {
    return Descripcion;
 }

 public void setDescripcion(String Descripcion) {
    Descripcion = Descripcion;
 }

 public Boolean getSeleccion() {
       if(Seleccion==null){
           Seleccion = false;
       }
        return Seleccion;
 }

 public void setSeleccion(Boolean seleccion) {
        Seleccion = seleccion;
 }
}
