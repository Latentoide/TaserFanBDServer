package es.ieslvareda.server.model.patin;

import es.ieslvareda.model.Bicicleta;
import es.ieslvareda.model.Patin;
import es.ieslvareda.model.Result;

import java.sql.Date;

public interface IPatinService {
    Result<Patin> createPatinete(Patin p);
    Result<Patin> updatePatinete(Patin p);
    Result<Patin> deletePatinete(String matricula);
    Result<Patin> consultarPatinete(String matricula);
}
