package es.ieslvareda.server.model.moto;

import es.ieslvareda.model.Coche;
import es.ieslvareda.model.Moto;
import es.ieslvareda.model.Result;

import java.sql.Date;

public interface IMotoService {
    Result<Moto> createMoto(Moto m);
    Result<Moto> updateMoto(Moto m);
    Result<Moto> deleteMoto(String matricula);
    Result<Moto> consultarMoto(String matricula);
}
