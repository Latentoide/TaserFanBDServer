package es.ieslvareda.server.model.moto;

import es.ieslvareda.model.*;

import java.sql.*;

public class ImpMotoService implements IMotoService{
    @Override
    public Result<Moto> createMoto(Moto m) {
        String sql = "{call GESTIONVEHICULOS.insertarMoto(?,?,?,?,?,?,?,?,?,?,?)}";
        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,m.getMatricula());
            cs.setFloat(2,m.getPrecioHora());
            cs.setString(3,m.getMarca());
            cs.setString(4, m.getDescripcion());
            cs.setString(5, m.getColor());
            cs.setFloat(6, m.getBateria());
            cs.setDate(7, m.getDate());
            cs.setString(8, m.getEstado());
            cs.setString(9, m.getIdCarnet());
            cs.setFloat(10, m.getVelMax());
            cs.setFloat(11, m.getCilindrada());

            cs.execute();
            return new Result.Success<>(200);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Moto> updateMoto(Moto m) {
        String sql = "{call GESTIONVEHICULOS.updateMoto(?,?,?,?,?,?,?,?,?,?,?)}";

        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,m.getMatricula());
            cs.setFloat(2,m.getPrecioHora());
            cs.setString(3,m.getMarca());
            cs.setString(4, m.getDescripcion());
            cs.setString(5, m.getColor());
            cs.setFloat(6, m.getBateria());
            cs.setDate(7, m.getDate());
            cs.setString(8, m.getEstado());
            cs.setString(9, m.getIdCarnet());
            cs.setFloat(10, m.getVelMax());
            cs.setFloat(11, m.getCilindrada());

            cs.execute();
            return new Result.Success<>(200);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Moto> deleteMoto(String matricula) {
        String sql = "{call GESTIONVEHICULOS.deleteMoto(?)}";
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
            return new Result.Error(404, "No se ha borrado ninguna moto");
        }
    }

    @Override
    public Result<Moto> consultarMoto(String matricula){
        String sql = "{call GESTIONVEHICULOS.consultarMoto(?,?,?,?,?,?,?,?,?,?,?)}";
        Moto c = null;
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
            float v_velMax = cs.getFloat(10);
            float v_cilindrada = cs.getFloat(11);
            Tablas tipo = Tablas.MOTO;

            Color ca = Color.valueOf(v_color.toUpperCase());
            Estado e = Estado.valueOf(v_estado.toUpperCase());

            c = new Moto(v_matricula, v_precioHora, v_marca, v_descripcion, ca, v_bateria, e, v_idCarnet, date, tipo, v_velMax, v_cilindrada);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(c != null){
            return new Result.Success<Moto>(c);
        }else{
            return new Result.Error(404, "No se ha encontrado la moto");
        }

    }
}
