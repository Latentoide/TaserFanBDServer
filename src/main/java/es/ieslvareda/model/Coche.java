package es.ieslvareda.model;

import java.sql.Date;

public class Coche extends Vehiculo{
    private float numPlazas;
    private float numPuertas;

    public Coche(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, String estado, int idCarnet, Date date, Tablas tablas, float numPlazas, float numPuertas) {
        super(matricula, precioHora, marca, descripcion, color, bateria, estado, idCarnet, date, tablas);
        this.numPlazas = numPlazas;
        this.numPuertas = numPuertas;
    }

    public float getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(float numPlazas) {
        this.numPlazas = numPlazas;
    }

    public float getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(float numPuertas) {
        this.numPuertas = numPuertas;
    }

    @Override
    public String toString() {
        return super.toString() + "({Coche: "+
                ", numPlazas=" + numPlazas +
                ", numPuertas=" + numPuertas +
                "})";
    }
}
