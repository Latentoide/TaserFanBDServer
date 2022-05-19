package es.ieslvareda.server.model.patin;

import es.ieslvareda.model.*;

import java.sql.*;

public class ImpPatinService implements IPatinService{
    @Override
    public Result<Patin> createPatinete(Patin p) {
        String sql = "{call GESTIONVEHICULOS.insertarPatinete(?,?,?,?,?,?,?,?,?,?,?)}";
        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,p.getMatricula());
            cs.setFloat(2,p.getPrecioHora());
            cs.setString(3,p.getMarca());
            cs.setString(4, p.getDescripcion());
            cs.setString(5, p.getColor());
            cs.setFloat(6, p.getBateria());
            cs.setDate(7, p.getDate());
            cs.setString(8, p.getEstado());
            cs.setString(9, p.getIdCarnet());
            cs.setFloat(10, p.getNumRuedas());
            cs.setFloat(11, p.getTamanyo());

            cs.execute();
            return new Result.Success<>(200);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Patin> updatePatinete(Patin p) {
        String sql = "{call GESTIONVEHICULOS.updatePatinete(?,?,?,?,?,?,?,?,?,?,?)}";

        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,p.getMatricula());
            cs.setFloat(2,p.getPrecioHora());
            cs.setString(3,p.getMarca());
            cs.setString(4, p.getDescripcion());
            cs.setString(5, p.getColor());
            cs.setFloat(6, p.getBateria());
            cs.setDate(7, p.getDate());
            cs.setString(8, p.getEstado());
            cs.setString(9, p.getIdCarnet());
            cs.setFloat(10, p.getNumRuedas());
            cs.setFloat(11, p.getTamanyo());

            cs.execute();
            return new Result.Success<>(200);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Patin> deletePatinete(String matricula) {
        String sql = "{call GESTIONVEHICULOS.deletePatinete(?)}";
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
            return new Result.Error(404, "No se ha borrado ningún patinete");
        }
    }

    @Override
    public Result<Patin> consultarPatinete(String matricula) {
        String sql = "{call GESTIONVEHICULOS.consultarPatinete(?,?,?,?,?,?,?,?,?,?,?)}";
        Patin c = null;
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
            float v_numruedas = cs.getFloat(10);
            float v_tamanyo = cs.getFloat(11);
            Tablas tipo = Tablas.PATINETE;

            Color ca = Color.valueOf(v_color.toUpperCase());
            Estado e = Estado.valueOf(v_estado.toUpperCase());

            c = new Patin(v_matricula, v_precioHora, v_marca, v_descripcion, ca, v_bateria, e, v_idCarnet, date, tipo, v_numruedas, v_tamanyo);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(c != null){
            return new Result.Success<Patin>(c);
        }else{
            return new Result.Error(404, "No se ha encontrado el patin");
        }

    }
}
