package es.ieslvareda.server.model.bici;

import es.ieslvareda.model.Bicicleta;
import es.ieslvareda.model.Coche;
import es.ieslvareda.model.Result;

import java.sql.Date;

public interface IBiciService {
    Result<Bicicleta> createBicicleta(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, Date fechaadq, String estado, String idCarnet, String tipo);
    Result<Bicicleta> updateBicicleta(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, Date fechaadq, String estado, String idCarnet, String tipo);
    Result<Bicicleta> deleteBicicleta(String matricula);
    Result<Bicicleta> consultarBicicleta(String matricula);
}
