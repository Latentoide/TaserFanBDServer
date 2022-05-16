package es.ieslvareda.model;

import java.sql.Date;

public class Bicicleta extends Vehiculo{
    private String tipo;

    public Bicicleta(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, String estado, int idCarnet, Date date, Tablas tablas, String tipo) {
        super(matricula, precioHora, marca, descripcion, color, bateria, estado, idCarnet, date, tablas);
        this.tipo = tipo;
    }

    public String getTipoBic() {
        return tipo;
    }

    public void setTipoBic(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return  super.toString() + "(Bicicleta{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
