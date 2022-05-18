package es.ieslvareda.server;

import es.ieslvareda.server.controllers.*;
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

        //Motos
        post(API.MOTOS, MotoController::insertarMoto, new JsonTransformer<>());
        get(API.MOTOS, MotoController::consultarMoto, new JsonTransformer<>());
        put(API.MOTOS, MotoController::updateMoto, new JsonTransformer<>());
        delete(API.MOTOS, MotoController::deleteMoto, new JsonTransformer<>());

        //Bicicleta
        post(API.BICICLETAS, BicicletaController::insertarBicicleta, new JsonTransformer<>());
        get(API.BICICLETAS, BicicletaController::consultarBicicleta, new JsonTransformer<>());
        put(API.BICICLETAS, BicicletaController::updateBicicleta, new JsonTransformer<>());
        delete(API.BICICLETAS, BicicletaController::deleteBicicleta, new JsonTransformer<>());

        //Patinete
        post(API.PATINETES, PatinController::insertarPatin, new JsonTransformer<>());
        get(API.PATINETES, PatinController::consultarPatin, new JsonTransformer<>());
        put(API.PATINETES, PatinController::updatePatin, new JsonTransformer<>());
        delete(API.PATINETES, PatinController::deletePatin, new JsonTransformer<>());
    }
}


