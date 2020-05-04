package com.example.bookingtime;

public class HorasSpinner {
    private String Id;
    private String hora;


    public HorasSpinner() {}
    public HorasSpinner(String Id,String hora) {
        this.Id = Id;
        this.hora = hora;

    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getId() {
        return Id;
    }

    public void setKey(String Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return hora;
    }
}
