package es.ieslvareda.server;

import es.ieslvareda.server.controllers.EmpleadoController;
import es.ieslvareda.server.controllers.VehiculoController;
import es.ieslvareda.server.model.JsonTransformer;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        post(API.AUTENTICAR, EmpleadoController::getEmpleado, new JsonTransformer<>());
        get(API.ALL_COCHES, VehiculoController::getAllCoches,new JsonTransformer<>());
        get(API.ALL_PATINETES, VehiculoController::getAllPatinete,new JsonTransformer<>());
        get(API.ALL_MOTOS, VehiculoController::getAllMotos,new JsonTransformer<>());
        get(API.ALL_BICILETAS, VehiculoController::getAllBiciletas,new JsonTransformer<>());
    }
}


