package es.ieslvareda.server;

import es.ieslvareda.server.controllers.CocheController;
import es.ieslvareda.server.controllers.EmpleadoController;
import es.ieslvareda.server.controllers.VehiculoController;
import es.ieslvareda.server.model.JsonTransformer;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        post(API.AUTENTICAR, EmpleadoController::getEmpleado, new JsonTransformer<>());

        //Vehiculo
        get(API.ALL, VehiculoController::getAllVehicles,new JsonTransformer<>());

        //Coches
        post(API.COCHE, CocheController::insertarCoche, new JsonTransformer<>());
        get(API.COCHE, CocheController::consultarCoche, new JsonTransformer<>());
        put(API.COCHE, CocheController::updateCoche, new JsonTransformer<>());
        delete(API.COCHE, CocheController::deleteCoche, new JsonTransformer<>());
    }
}


