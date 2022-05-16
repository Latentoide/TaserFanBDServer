package es.ieslvareda.server.model.moto;

import java.sql.Date;

public interface IMotoService {
    void createCoche(String matricula, float precioHora, String marca, String descripcion, String color, int bateria, Date fechaadq, String estado, float idCarnet, float numPlazas, float numPuertas);
    void updateCoche(String matricula, float precioHora, String marca, String descripcion, String color, int bateria, Date fechaadq, String estado, float idCarnet, float numPlazas, float numPuertas);
    void deleteCoche(String matricula);
    void consultarCoche(String matricula, float precioHora, String marca, String descripcion, String color, int bateria, Date fechaadq, String estado, float idCarnet, float numPlazas, float numPuertas);
}
