package ieslvareda.model;

import es.ieslvareda.server.controllers.PersonController;
import es.ieslvareda.server.model.IPersonService;
import es.ieslvareda.server.model.ImpPersonService;
import es.ieslvareda.server.model.JsonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    static Logger logger = LoggerFactory.getLogger(PersonController.class);

    private static IPersonService service = new ImpPersonService();
    private static JsonTransformer<Person> jsonTransformer = new JsonTransformer<>();
    // MariaDB
    public List<Person> getPersons(){

        List<Person> personList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from person")){

            String dni;
            String nombre;
            String apellidos;
            int edad;

            while(resultSet.next()){
                dni = resultSet.getString("dni");
                nombre = resultSet.getString("nombre");
                apellidos = resultSet.getString("apellidos");
                edad = resultSet.getInt("edad");

                personList.add(new Person(dni,nombre,apellidos,edad));

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return personList;

    }
    public Person addPerson(Person person){

        DataSource ds = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = ds.getConnection();
        Statement statement = con.createStatement();) {
            String sql = "INSERT INTO " +
                    "person VALUES ('"+person.getDni()+"','"+person.getNombre()+"','"+person.getApellidos()+"',"+person.getEdad()+")";

            int count = statement.executeUpdate(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }
    public int updatePerson(Person person){
        DataSource ds = MyDataSource.getMyMariaDBDataSource();
        int count = 0 ;
        try(Connection con = ds.getConnection();
            Statement statement = con.createStatement();) {
            String sql = "UPDATE person SET " +
                    "nombre='"+person.getNombre()+"',apellidos='"+person.getApellidos()+"', edad="+person.getEdad()+
                    " WHERE dni='"+person.getDni()+"'";

            count = statement.executeUpdate(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int deletePerson(String dni){

        DataSource ds = MyDataSource.getMyMariaDBDataSource();
        int count = 0 ;
        String sql = "DELETE FROM person WHERE dni=?";

        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);) {

            int pos = 0;
            pstmt.setString(++pos, dni);

            count = pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    // Oracle
    public List<Empleado> getEmpleados(){

        List<Empleado> empleados = new ArrayList<>();
        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NOMBRE FROM EMPLEADO")) {

            int pos;
            while (rs.next()){
                //empleados.add(new Empleado(rs.getString(1)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empleados;
    }
    public Empleado authenticate(String email,String password){
        String sql = "SELECT  FROM EMPLEADO WHERE " +
                    "EMAIL='"+email+"' AND "+
                    "PASSWORD=ENCRYPT_PASWD.encrypt_val('"+password+"')";

        DataSource ds = MyDataSource.getMyOracleDataSource();

        Empleado e = null;
        try(Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            rs.next();

            e.setNombre(rs.getString("nombre"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return e;
    }


}
