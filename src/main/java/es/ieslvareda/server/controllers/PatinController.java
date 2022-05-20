package es.ieslvareda.server.controllers;

import es.ieslvareda.model.Coche;
import es.ieslvareda.model.Patin;
import es.ieslvareda.model.Result;
import es.ieslvareda.server.model.JsonTransformer;
import es.ieslvareda.server.model.patin.IPatinService;
import es.ieslvareda.server.model.patin.ImpPatinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class PatinController {
    static Logger logger = LoggerFactory.getLogger(PatinController.class);
    private static IPatinService service = new ImpPatinService();
    private static JsonTransformer<Patin> jsonTransformer = new JsonTransformer<>();

    public static Result insertarPatin(Request req, Response res){
        logger.info("Peticion para añadir patinete");
        String body = req.body();

        Patin c = jsonTransformer.getObject(body, Patin.class);
        Result result = service.createPatinete(c);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result updatePatin(Request req, Response res){
        logger.info("Peticion para modificar patinete");
        String body = req.body();

        Patin c = jsonTransformer.getObject(body, Patin.class);
        Result result = service.updatePatinete(c);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result deletePatin(Request req, Response res){
        logger.info("Peticion para borrar patinete");
        String body = req.body();

        Patin c = jsonTransformer.getObject(body, Patin.class);
        Result result = service.deletePatinete(c.getMatricula());
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }


    public static Result<Coche> consultarPatin(Request req, Response res){
        logger.info("Peticion para consultar patinete");
        String matricula = req.queryParams("matricula");

        Result result = service.consultarPatinete(matricula);


        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }
}
