package es.ieslvareda.server.controllers;

import es.ieslvareda.model.*;
import es.ieslvareda.server.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class EmpleadoController {

    static Logger logger = LoggerFactory.getLogger(EmpleadoController.class);
    private static IEmpleadoService service = new ImpEmpeladoService();
    private static JsonTransformer<Authentification> jsonTransformer = new JsonTransformer<>();

    public static Result<Empleado> getEmpleado(Request req, Response res){
        // http://localhost:4567/person?dni=1111

        //System.out.println(res);
        String body = req.body();
        System.out.println(body);
        Authentification a = jsonTransformer.getObjet(body, Authentification.class);
        Result result = service.autenticar(a);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }
}
