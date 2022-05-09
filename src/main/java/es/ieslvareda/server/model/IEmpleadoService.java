package es.ieslvareda.server.model;

import es.ieslvareda.model.Authentification;
import es.ieslvareda.model.Empleado;
import es.ieslvareda.model.Result;

public interface IEmpleadoService {
    Result<Empleado> autenticar(Authentification a);

}
