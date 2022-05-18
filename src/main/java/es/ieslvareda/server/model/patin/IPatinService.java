package es.ieslvareda.server.model.patin;

import es.ieslvareda.model.Bicicleta;
import es.ieslvareda.model.Patin;
import es.ieslvareda.model.Result;

import java.sql.Date;

public interface IPatinService {
    Result<Patin> createPatinete(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, Date fechaadq, String estado, String idCarnet, float numruedas, float tamanyo);
    Result<Patin> updatePatinete(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, Date fechaadq, String estado, String idCarnet, float numruedas, float tamanyo);
    Result<Patin> deletePatinete(String matricula);
    Result<Patin> consultarPatinete(String matricula);
}
