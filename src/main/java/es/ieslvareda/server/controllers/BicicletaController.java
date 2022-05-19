package es.ieslvareda.server.controllers;

import es.ieslvareda.model.Bicicleta;
import es.ieslvareda.model.Coche;
import es.ieslvareda.model.Result;
import es.ieslvareda.server.model.JsonTransformer;
import es.ieslvareda.server.model.bici.IBiciService;
import es.ieslvareda.server.model.bici.ImpBiciService;
import es.ieslvareda.server.model.coche.ICocheService;
import es.ieslvareda.server.model.coche.ImpCocheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class BicicletaController {
    static Logger logger = LoggerFactory.getLogger(BicicletaController.class);
    private static IBiciService service = new ImpBiciService();
    private static JsonTransformer<Bicicleta> jsonTransformer = new JsonTransformer<>();

    public static Result insertarBicicleta(Request req, Response res){
        logger.info("Peticion para añadir bicicleta");
        String body = req.body();

        Bicicleta c = jsonTransformer.getObjet(body, Bicicleta.class);
        Result result = service.createBicicleta(c);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result updateBicicleta(Request req, Response res){
        logger.info("Peticion para modificar bicicleta");
        String body = req.body();

        Bicicleta c = jsonTransformer.getObjet(body, Bicicleta.class);
        Result result = service.updateBicicleta(c);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result deleteBicicleta(Request req, Response res){
        logger.info("Peticion para borrar bicicleta");
        String body = req.body();

        Bicicleta c = jsonTransformer.getObjet(body, Bicicleta.class);
        Result result = service.deleteBicicleta(c.getMatricula());
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }


    public static Result<Coche> consultarBicicleta(Request req, Response res){
        logger.info("Peticion para consultar bicicleta");
        String matricula = req.queryParams("matricula");

        Result result = service.consultarBicicleta(matricula);


        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }
}
