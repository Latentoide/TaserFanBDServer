package es.ieslvareda.server.model.coche;

import es.ieslvareda.model.Coche;
import es.ieslvareda.model.Result;
import es.ieslvareda.model.Tablas;

import java.sql.Date;

public interface ICocheService {
    Result<Coche> createCoche(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, Date fechaadq, String estado, int idCarnet, float numPlazas, float numPuertas);
    Result<Coche> updateCoche(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, Date fechaadq, String estado, int idCarnet, float numPlazas, float numPuertas);
    Result<Coche> deleteCoche(String matricula);
    Result<Coche> consultarCoche(String matricula);
}
