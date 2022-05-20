package es.ieslvareda.server.model.bici;

import es.ieslvareda.model.*;

import java.sql.*;

public class ImpBiciService implements IBiciService{
    @Override
    public Result<Bicicleta> createBicicleta(Bicicleta b) {
        String sql = "{call GESTIONVEHICULOS.insertarBicicleta(?,?,?,?,?,?,?,?,?,?)}";
        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,b.getMatricula());
            cs.setFloat(2,b.getPrecioHora());
            cs.setString(3,b.getMarca());
            cs.setString(4, b.getDescripcion());
            cs.setString(5, b.getColor());
            cs.setFloat(6, b.getBateria());
            cs.setDate(7, b.getDate());
            cs.setString(8, b.getEstado());
            cs.setString(9, b.getIdCarnet());
            cs.setString(10, b.getTipoBic());

            cs.execute();
            return new Result.Success<>(200);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Bicicleta> updateBicicleta(Bicicleta b) {
        String sql = "{call GESTIONVEHICULOS.updateBicicleta(?,?,?,?,?,?,?,?,?,?)}";

        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,b.getMatricula());
            cs.setFloat(2,b.getPrecioHora());
            cs.setString(3,b.getMarca());
            cs.setString(4, b.getDescripcion());
            cs.setString(5, b.getColor());
            cs.setFloat(6, b.getBateria());
            cs.setDate(7, b.getDate());
            cs.setString(8, b.getEstado());
            cs.setString(9, b.getIdCarnet());
            cs.setString(10, b.getTipoBic());

            cs.execute();
            return new Result.Success<>(200);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
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

            Color ca = Color.NEGRO;
            Estado e = Estado.PREPARADO;
            switch (v_color){
                case "verde":
                    ca = Color.VERDE;
                    break;
                case "amarillo":
                    ca = Color.AMARILLO;
                    break;
                case "rojo":
                    ca = Color.ROJO;
                    break;
                case "blanco":
                    ca = Color.BLANCO;
                    break;
                case "negro":
                    ca = Color.NEGRO;
                    break;
                case "azul":
                    ca = Color.AZUL;
                    break;
            }
            switch (v_estado){
                case "preparado":
                    e = Estado.PREPARADO;
                    break;
                case "baja":
                    e = Estado.BAJA;
                    break;
                case "taller":
                    e = Estado.TALLER;
                    break;
                case "reservado":
                    e = Estado.RESERVADO;
                    break;
                case "alquilado":
                    e = Estado.ALQUILADO;
                    break;
            }

            c = new Bicicleta(v_matricula, v_precioHora, v_marca, v_descripcion, ca, v_bateria, e, v_idCarnet, date, tipo, tipob);

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
