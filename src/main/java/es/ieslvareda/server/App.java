package es.ieslvareda.server;

import es.ieslvareda.server.controllers.EmpleadoController;
import es.ieslvareda.server.model.JsonTransformer;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        post(API.AUTENTICAR, EmpleadoController::getEmpleado, new JsonTransformer<>());
        get(API.ALL_EMPLEADO,,new JsonTransformer<>());
    }
}


