package entity;

public class BicycleEntity {
    public int Id;
    public String Cikkszam;
    public int MarkaID;
    public int VazmeretID;
    public int FelniAtmeroID;
    public int ValtoTipus;
    public int TipusID;
    public int Ar;
    public String URL;

    public BicycleEntity() {
    }

    public BicycleEntity(int id, String cikkszam, int markaID, int vazmeretID, int felniAtmeroID, int valtoTipus, int tipusID, int ar, String URL) {
        Id = id;
        Cikkszam = cikkszam;
        MarkaID = markaID;
        VazmeretID = vazmeretID;
        FelniAtmeroID = felniAtmeroID;
        ValtoTipus = valtoTipus;
        TipusID = tipusID;
        Ar = ar;
        this.URL = URL;
    }

   /* public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCikkszam() {
        return Cikkszam;
    }

    public void setCikkszam(String cikkszam) {
        Cikkszam = cikkszam;
    }

    public int getMarkaID() {
        return MarkaID;
    }

    public void setMarkaID(int markaID) {
        MarkaID = markaID;
    }

    public int getVazmeretID() {
        return VazmeretID;
    }

    public void setVazmeretID(int vazmeretID) {
        VazmeretID = vazmeretID;
    }

    public int getFelniAtmeroID() {
        return FelniAtmeroID;
    }

    public void setFelniAtmeroID(int felniAtmeroID) {
        FelniAtmeroID = felniAtmeroID;
    }

    public int getValtoTipus() {
        return ValtoTipus;
    }

    public void setValtoTipus(int valtoTipus) {
        ValtoTipus = valtoTipus;
    }

    public int getTipusID() {
        return TipusID;
    }

    public void setTipusID(int tipusID) {
        TipusID = tipusID;
    }

    public int getAr() {
        return Ar;
    }

    public void setAr(int ar) {
        Ar = ar;
    }*/
}
