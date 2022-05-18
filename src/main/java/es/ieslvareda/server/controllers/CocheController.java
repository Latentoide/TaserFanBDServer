package es.ieslvareda.server.controllers;

import es.ieslvareda.model.*;
import es.ieslvareda.server.model.JsonTransformer;
import es.ieslvareda.server.model.coche.ICocheService;
import es.ieslvareda.server.model.coche.ImpCocheService;
import es.ieslvareda.server.model.vehiculo.IVehiculoService;
import es.ieslvareda.server.model.vehiculo.ImpVehiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.sql.*;

public class CocheController{
    static Logger logger = LoggerFactory.getLogger(CocheController.class);
    private static ICocheService service = new ImpCocheService();
    private static JsonTransformer<Coche> jsonTransformer = new JsonTransformer<>();

    public static Result insertarCoche(Request req, Response res){
        logger.info("Peticion para añadir coche");
        String body = req.body();

        Coche c = jsonTransformer.getObjet(body, Coche.class);
        Result result = service.createCoche(c.getMatricula(), c.getPrecioHora(), c.getMarca(), c.getDescripcion(), c.getColor(), c.getBateria(), c.getDate(), c.getEstado(), c.getIdCarnet(), c.getNumPlazas(), c.getNumPuertas());
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result updateCoche(Request req, Response res){
        String body = req.body();

        Coche c = jsonTransformer.getObjet(body, Coche.class);
        Result result = service.updateCoche(c.getMatricula(), c.getPrecioHora(), c.getMarca(), c.getDescripcion(), c.getColor(), c.getBateria(), c.getDate(), c.getEstado(), c.getIdCarnet(), c.getNumPlazas(), c.getNumPuertas());
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result deleteCoche(Request req, Response res){
        String body = req.body();

        Coche c = jsonTransformer.getObjet(body, Coche.class);
        Result result = service.deleteCoche(c.getMatricula());
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }


    public static Result<Coche> consultarCoche(Request req, Response res){

        String matricula = req.queryParams("matricula");

        Result result = service.consultarCoche(matricula);


        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }
}
