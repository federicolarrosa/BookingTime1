package com.example.bookingtime;


public class Restaurante {
    private String Direccion;
    private Boolean Estacionamiento;
    private Boolean Fumar;
    private String horarios;
    private String Nombre;
    private Boolean Tarjetas;
    private String Telefono;
    private String tipoComida;
    private Boolean Wifi;
    private String Email;
    private String Resumen;
    private String Imagen;

    public Restaurante (){}

    public Restaurante(String Direccion, Boolean Estacionamiento, Boolean Fumar, String horarios, String Nombre,
                       Boolean Tarjetas, String Telefono, String tipoComida, Boolean Wifi, String Email, String Resumen, String Imagen) {
        this.Direccion = Direccion;
        this.Estacionamiento = Estacionamiento;
        this.Fumar = Fumar;
        this.horarios = horarios;
        this.Nombre = Nombre;
        this.Tarjetas = Tarjetas;
        this.Telefono = Telefono;
        this.tipoComida = tipoComida;
        this.Wifi = Wifi;
        this.Email = Email;
        this.Resumen = Resumen;
        this.Imagen = Imagen;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = Direccion;
    }

    public Boolean getEstacionamiento() {
        return Estacionamiento;
    }

    public void setEstacionamiento(Boolean Estacionamiento) {
        this.Estacionamiento = Estacionamiento;
    }

    public Boolean getFumar() {
        return Fumar;
    }

    public void setFumar(Boolean Fumar) {
        this.Fumar = Fumar;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Boolean getTarjetas() {
        return Tarjetas;
    }

    public void setTarjetas(Boolean Tarjetas) {
        this.Tarjetas = Tarjetas;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public Boolean getWifi() {
        return Wifi;
    }

    public void setWifi(Boolean Wifi) {
        this.Wifi = Wifi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getResumen() {
        return Resumen;
    }

    public void setResumen(String Resumen) {
        this.Resumen = Resumen;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

}

