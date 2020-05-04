package com.example.bookingtime;

public class pojo {
    private Boolean Reservado;
    private String id;
    private String HoraR;
    private String Persona;
    private String NombreR;
    private String Dia;
    private String key;



    public pojo() {}
    public pojo(String id,Boolean Reservado, String HoraR,String Persona,String NombreR,String Dia,String key){
        this.Reservado=Reservado;
        this.id=id;
        this.HoraR=HoraR;
        this.Persona=Persona;
        this.NombreR=NombreR;
        this.Dia=Dia;
        this.key=key;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String dia) {
        Dia = dia;
    }

    public String getNombreR() {
        return NombreR;
    }

    public void setNombreR(String NombreR) {
        NombreR = NombreR;
    }

    public String getPersona() {
        return Persona;
    }

    public void setPersona(String persona) {
        Persona = persona;
    }

    public String getHoraR() {
        return HoraR;
    }

    public void setHoraR(String HoraR) {
        HoraR = HoraR;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Boolean getReservado() {
        return Reservado;
    }
    public void setReservado(Boolean reservado) {
        this.Reservado = reservado;
    }


}
