package es.ieslvareda.server.model.empleado;

import es.ieslvareda.model.Authentification;
import es.ieslvareda.model.Empleado;
import es.ieslvareda.model.MyDataSource;
import es.ieslvareda.model.Result;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpEmpeladoService implements  IEmpleadoService{
    @Override
    public Result<Empleado> autenticar(Authentification a) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        Empleado e = null;
        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from empleado where email like ? and ENCRYPT_PASWD.decrypt_val(password) like ?");){

            int pos = 0;
            stmt.setString(++pos, a.getEmail());
            stmt.setString(++pos, a.getPassword());
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

    @Override
    public List<Empleado> getAll() {
        List<Empleado> empleadoList = new ArrayList<>();
        DataSource ds = MyDataSource.getMyOracleDataSource();

        try (Connection con = ds.getConnection();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("select * from empleado"))
        {
            String nombre;
            String apellidos;
            String domiciolio;
            String codigoPostal;
            String dni;
            while(resultSet.next()){
                apellidos = resultSet.getString("apellidos");
                domiciolio = resultSet.getString("domicilio");
                nombre = resultSet.getString("nombre");
                codigoPostal = resultSet.getString("cp");
                dni = resultSet.getString("dni");

                empleadoList.add(new Empleado(nombre, apellidos, domiciolio, codigoPostal, dni));
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return empleadoList;
    }


}
