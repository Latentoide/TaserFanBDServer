package es.ieslvareda.server.controllers;

import es.ieslvareda.model.Coche;
import es.ieslvareda.model.Patin;
import es.ieslvareda.model.Result;
import es.ieslvareda.server.model.JsonTransformer;
import es.ieslvareda.server.model.coche.ICocheService;
import es.ieslvareda.server.model.coche.ImpCocheService;
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
        String body = req.body();

        Patin c = jsonTransformer.getObjet(body, Patin.class);
        Result result = service.createPatinete(c.getMatricula(), c.getPrecioHora(), c.getMarca(), c.getDescripcion(), c.getColor(), c.getBateria(), c.getDate(), c.getEstado(), c.getIdCarnet(), c.getNumRuedas(), c.getTamanyo());
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result updatePatin(Request req, Response res){
        String body = req.body();

        Patin c = jsonTransformer.getObjet(body, Patin.class);
        Result result = service.updatePatinete(c.getMatricula(), c.getPrecioHora(), c.getMarca(), c.getDescripcion(), c.getColor(), c.getBateria(), c.getDate(), c.getEstado(), c.getIdCarnet(), c.getNumRuedas(), c.getTamanyo());
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result deletePatin(Request req, Response res){
        String body = req.body();

        Patin c = jsonTransformer.getObjet(body, Patin.class);
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
