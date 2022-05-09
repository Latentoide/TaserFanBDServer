package es.ieslvareda.server.model;

import es.ieslvareda.model.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpCiudadService implements ICiudadService{
    @Override
    public Result<Ciudad> getCiudad(Ciudad a) {
        DataSource ds = MyDataSource.getMyMariaDBDataSource();
        Empleado e = null;
        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into ciudades values(?,?,?.?)");){

            int pos = 0;
            stmt.setString(++pos, a.getNombre());
            stmt.setString(++pos, a.getImg());
            stmt.setFloat(++pos, a.getLon());
            stmt.setFloat(++pos, a.getLat());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String domicilio = rs.getString("domicilio");
                String codigoPostal = rs.getString("CP");
                String dni = rs.getString("dni");

                e = new Empleado(nombre, apellidos, domicilio, codigoPostal, dni);
                return new Result.Success<Empleado>(e);
            }else{
                return new Result.Error(404, "Datos incorrectos");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(404, "Erros de acceso a la base de datos");
        }
    }
}
