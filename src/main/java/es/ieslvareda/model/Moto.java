package es.ieslvareda.model;

import java.sql.Date;

public class Moto extends Vehiculo{
    private float velMax;
    private float cilindrada;

    public Moto(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, String estado, float idCarnet, Date date, Tablas tablas, float velMax, float cilindrada) {
        super(matricula, precioHora, marca, descripcion, color, bateria, estado, idCarnet, date, tablas);
        this.velMax = velMax;
        this.cilindrada = cilindrada;
    }

    public float getVelMax() {
        return velMax;
    }

    public void setVelMax(float velMax) {
        this.velMax = velMax;
    }

    public float getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(float cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public String toString() {
        return super.toString() + "(Moto{" +
                ", velMax=" + velMax +
                ", cilindrada=" + cilindrada +
                "}";
    }
}
