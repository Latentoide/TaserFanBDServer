package es.ieslvareda.server.model.bici;

import es.ieslvareda.model.Bicicleta;
import es.ieslvareda.model.Coche;
import es.ieslvareda.model.Result;

import java.sql.Date;

public interface IBiciService {
    Result<Bicicleta> createBicicleta(Bicicleta b);
    Result<Bicicleta> updateBicicleta(Bicicleta b);
    Result<Bicicleta> deleteBicicleta(String matricula);
    Result<Bicicleta> consultarBicicleta(String matricula);
}
