package es.ieslvareda.server.controllers;

import es.ieslvareda.model.Coche;
import es.ieslvareda.model.Moto;
import es.ieslvareda.model.Result;
import es.ieslvareda.server.model.JsonTransformer;
import es.ieslvareda.server.model.coche.ICocheService;
import es.ieslvareda.server.model.coche.ImpCocheService;
import es.ieslvareda.server.model.moto.IMotoService;
import es.ieslvareda.server.model.moto.ImpMotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class MotoController {
    static Logger logger = LoggerFactory.getLogger(MotoController.class);
    private static IMotoService service = new ImpMotoService();
    private static JsonTransformer<Moto> jsonTransformer = new JsonTransformer<>();

    public static Result insertarMoto(Request req, Response res){
        logger.info("Peticion para añadir moto");
        String body = req.body();

        Moto c = jsonTransformer.getObjet(body, Moto.class);
        Result result = service.createMoto(c);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result updateMoto(Request req, Response res){
        logger.info("Peticion para modificar moto");
        String body = req.body();

        Moto c = jsonTransformer.getObjet(body, Moto.class);
        Result result = service.updateMoto(c);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result deleteMoto(Request req, Response res){
        logger.info("Peticion para borrar moto");
        String body = req.body();

        Moto c = jsonTransformer.getObjet(body, Moto.class);
        Result result = service.deleteMoto(c.getMatricula());
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }


    public static Result<Coche> consultarMoto(Request req, Response res){
        logger.info("Peticion para consultar moto");
        String matricula = req.queryParams("matricula");

        Result result = service.consultarMoto(matricula);


        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }
}
