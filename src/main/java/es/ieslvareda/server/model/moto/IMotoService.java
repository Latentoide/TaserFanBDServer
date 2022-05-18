package es.ieslvareda.server.model.moto;

import es.ieslvareda.model.Coche;
import es.ieslvareda.model.Moto;
import es.ieslvareda.model.Result;

import java.sql.Date;

public interface IMotoService {
    Result<Moto> createMoto(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, Date fechaadq, String estado, String idCarnet, float velocidadMax, float cilindrada);
    Result<Moto> updateMoto(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, Date fechaadq, String estado, String idCarnet, float velocidadMax, float cilindrada);
    Result<Moto> deleteMoto(String matricula);
    Result<Moto> consultarMoto(String matricula);
}
