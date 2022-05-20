package es.ieslvareda.server.controllers;

import es.ieslvareda.model.*;
import es.ieslvareda.server.model.JsonTransformer;
import es.ieslvareda.server.model.coche.ICocheService;
import es.ieslvareda.server.model.coche.ImpCocheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class CocheController{
    static Logger logger = LoggerFactory.getLogger(CocheController.class);
    private static ICocheService service = new ImpCocheService();
    private static JsonTransformer<Coche> jsonTransformer = new JsonTransformer<>();

    public static Result insertarCoche(Request req, Response res){
        logger.info("Peticion para añadir coche");
        String body = req.body();

        Coche c = jsonTransformer.getObject(body, Coche.class);
        Result result = service.createCoche(c);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result updateCoche(Request req, Response res){
        logger.info("Peticion para modificar coche");
        String body = req.body();

        Coche c = jsonTransformer.getObject(body, Coche.class);
        Result result = service.updateCoche(c);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result deleteCoche(Request req, Response res){
        logger.info("Peticion para borrar coche");
        String body = req.body();

        Coche c = jsonTransformer.getObject(body, Coche.class);
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
        logger.info("Peticion para consultar coche");
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
