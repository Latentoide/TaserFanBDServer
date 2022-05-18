package es.ieslvareda.server.model.coche;

import com.mysql.cj.xdevapi.Type;
import es.ieslvareda.model.Coche;
import es.ieslvareda.model.MyDataSource;
import es.ieslvareda.model.Result;
import es.ieslvareda.model.Tablas;
import es.ieslvareda.server.controllers.CocheController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ImpCocheService implements ICocheService{
    static Logger logger = LoggerFactory.getLogger(ImpCocheService.class);
    @Override
    public Result<Coche> createCoche(Coche c) {

        String sql = "{call GESTIONVEHICULOS.insertarCoche(?,?,?,?,?,?,?,?,?,?,?)}";
        boolean resultado = false;
        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,c.getMatricula());
            cs.setFloat(2,c.getPrecioHora());
            cs.setString(3,c.getMarca());
            cs.setString(4, c.getDescripcion());
            cs.setString(5, c.getColor());
            cs.setFloat(6, c.getBateria());
            cs.setDate(7, c.getDate());
            cs.setString(8, c.getEstado());
            cs.setString(9, c.getIdCarnet());
            cs.setFloat(10, numPlazas);
            cs.setFloat(11, numPuertas);

            resultado = cs.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(resultado){
            return new Result.Success<>(200);
        }else{
            return new Result.Error(404, "No se ha borrado ningún coche");
        }
    }

    @Override
    public Result<Coche> updateCoche(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, Date fechaadq, String estado, String idCarnet, float numPlazas, float numPuertas) {
        String sql = "{call GESTIONVEHICULOS.updateCoche(?,?,?,?,?,?,?,?,?,?,?)}";

        boolean resultado = false;
        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,matricula);
            cs.setFloat(2,precioHora);
            cs.setString(3,marca);
            cs.setString(4, descripcion);
            cs.setString(5, color);
            cs.setFloat(6, bateria);
            cs.setDate(7, fechaadq);
            cs.setString(8, estado);
            cs.setString(9, idCarnet);
            cs.setFloat(10, numPlazas);
            cs.setFloat(11, numPuertas);

            resultado = cs.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(resultado){
            return new Result.Success<>(200);
        }else{
            return new Result.Error(404, "No se ha borrado ningún coche");
        }
    }

    @Override
    public Result<Coche> deleteCoche(String matricula) {
        String sql = "{call GESTIONVEHICULOS.deleteCoche(?)}";
        boolean resultado = false;
        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,matricula);
            resultado = cs.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(resultado){
            return new Result.Success<>(200);
        }else{
            return new Result.Error(404, "No se ha borrado ningún coche");
        }
    }

    @Override
    public Result<Coche> consultarCoche(String matricula){
        String sql = "{call GESTIONVEHICULOS.consultarCoche(?,?,?,?,?,?,?,?,?,?,?)}";
        Coche c = null;
        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,matricula);
            cs.registerOutParameter(2, Types.FLOAT);
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.registerOutParameter(6, Types.FLOAT);
            cs.registerOutParameter(7, Types.DATE);
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.registerOutParameter(10, Types.FLOAT);
            cs.registerOutParameter(11, Types.FLOAT);

            cs.execute();

            String v_matricula = matricula;
            float v_precioHora = cs.getFloat(2);
            String v_marca = cs.getString(3);
            String v_descripcion = cs.getString(4);
            String v_color = cs.getString(5);
            float v_bateria = cs.getFloat(6);
            Date date = cs.getDate(7);
            String v_estado = cs.getString(8);
            String v_idCarnet = cs.getString(9);
            float v_numPlazas = cs.getFloat(10);
            float v_numPuertas = cs.getFloat(11);
            Tablas tipo = Tablas.COCHE;

            c = new Coche(v_matricula, v_precioHora, v_marca, v_descripcion, v_color, v_bateria, v_estado, v_idCarnet, date, tipo, v_numPlazas, v_numPuertas);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(c != null){
            return new Result.Success<Coche>(c);
        }else{
            return new Result.Error(404, "No se ha encontrado el coche");
        }

    }
}
