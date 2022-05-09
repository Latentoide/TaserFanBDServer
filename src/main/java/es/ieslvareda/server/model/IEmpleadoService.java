package es.ieslvareda.server.model;

import es.ieslvareda.model.Authentification;
import es.ieslvareda.model.Empleado;
import es.ieslvareda.model.Result;

import java.util.List;

public interface IEmpleadoService {
    Result<Empleado> autenticar(Authentification a);
    List<Empleado> getAll();
}
