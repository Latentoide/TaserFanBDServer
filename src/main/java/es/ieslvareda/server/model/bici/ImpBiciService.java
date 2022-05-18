package es.ieslvareda.server.model.bici;

import es.ieslvareda.model.*;

import java.sql.*;

public class ImpBiciService implements IBiciService{
    @Override
    public Result<Bicicleta> createBicicleta(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, Date fechaadq, String estado, String idCarnet, String tipo) {
        String sql = "{call GESTIONVEHICULOS.insertarBicicleta(?,?,?,?,?,?,?,?,?,?)}";
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
            cs.setString(10, tipo);

            resultado = cs.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(resultado){
            return new Result.Success<>(200);
        }else{
            return new Result.Error(404, "No se ha borrado ninguna bicicleta");
        }
    }

    @Override
    public Result<Bicicleta> updateBicicleta(String matricula, float precioHora, String marca, String descripcion, String color, float bateria, Date fechaadq, String estado, String idCarnet, String tipo) {
        String sql = "{call GESTIONVEHICULOS.updateBicicleta(?,?,?,?,?,?,?,?,?,?)}";

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
            cs.setString(10, tipo);

            resultado = cs.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(resultado){
            return new Result.Success<>(200);
        }else{
            return new Result.Error(404, "No se ha borrado ninguna bicicleta");
        }
    }

    @Override
    public Result<Bicicleta> deleteBicicleta(String matricula) {
        String sql = "{call GESTIONVEHICULOS.deleteBicicleta(?)}";
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
            return new Result.Error(404, "No se ha borrado ninguna bicicleta");
        }
    }

    @Override
    public Result<Bicicleta> consultarBicicleta(String matricula) {
        String sql = "{call GESTIONVEHICULOS.consultarBicicleta(?,?,?,?,?,?,?,?,?,?)}";
        Bicicleta c = null;
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
            cs.registerOutParameter(10, Types.VARCHAR);

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
            String tipob = cs.getString(10);
            Tablas tipo = Tablas.BICICLETA;

            c = new Bicicleta(v_matricula, v_precioHora, v_marca, v_descripcion, v_color, v_bateria, v_estado, v_idCarnet, date, tipo, tipob);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(c != null){
            return new Result.Success<Bicicleta>(c);
        }else{
            return new Result.Error(404, "No se ha encontrado la bicicleta");
        }
    }
}
