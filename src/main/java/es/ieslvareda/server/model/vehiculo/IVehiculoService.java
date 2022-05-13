package es.ieslvareda.server.model.vehiculo;

import es.ieslvareda.model.*;

import java.util.List;

public interface IVehiculoService {
    List<Vehiculo> getAll(Tablas tablas);
}
