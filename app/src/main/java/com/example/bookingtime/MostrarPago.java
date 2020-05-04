package com.example.bookingtime;

public class MostrarPago {

    private int Persona;
    private String MenuSel;
    private String BebidasSel;
    private String PostresSel;
    private String MenPrecio;
    private String BebPrecio;
    private String PosPrecio;

    public MostrarPago(){}



    public MostrarPago(int persona, String MenuSel, String BebidasSel, String PostresSel, String MenPrecio, String BebPrecio, String PosPrecio, int MCantUn, int BCantUn, int PCantUn) {
        this.Persona=persona;
        this.MenuSel=MenuSel;
        this.BebidasSel=BebidasSel;
        this.PostresSel=PostresSel;
        this.MenPrecio=MenPrecio;
        this.BebPrecio=BebPrecio;
        this.PosPrecio=PosPrecio;



    }
    public int getPersona() {
        return Persona;
    }

    public void setPersona(int persona) {
        Persona = persona;
    }

    public String getMenuSel() {
        return MenuSel;
    }

    public void setMenuSel(String menuSel) {
        MenuSel = menuSel;
    }

    public String getBebidasSel() {
        return BebidasSel;
    }

    public void setBebidasSel(String bebidasSel) {
        BebidasSel = bebidasSel;
    }

    public String getPostresSel() {
        return PostresSel;
    }

    public void setPostresSel(String postresSel) {
        PostresSel = postresSel;
    }

    public String getMenPrecio() {
        return MenPrecio;
    }

    public void setMenuPrecio(String menPrecio) {
        MenPrecio = menPrecio;
    }

    public String getBebPrecio() {
        return BebPrecio;
    }

    public void setBebPrecio(String bebPrecio) {
        BebPrecio = bebPrecio;
    }

    public String getPosPrecio() {
        return PosPrecio;
    }

    public void setPosPrecio(String posPrecio) {
        PosPrecio = posPrecio;
    }


}
