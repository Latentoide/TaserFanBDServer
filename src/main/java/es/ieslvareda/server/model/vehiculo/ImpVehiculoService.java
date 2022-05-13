package es.ieslvareda.server.model.vehiculo;

import es.ieslvareda.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpVehiculoService implements IVehiculoService{
    @Override
    public List<Vehiculo> getAll(Tablas tabla) {
        List<Vehiculo> vehiculoArrayList = new ArrayList<>();
        DataSource ds = MyDataSource.getMyOracleDataSource();

        try (Connection con = ds.getConnection();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("select * from vehiculo v inner join " + tabla + " c on v.matricula = c.matricula"))
        {
            String matricula;
            float precioHora;
            String marca;
            String descripcion;
            String color;
            float bateria;
            String estado;
            float idCarnet;
            Date date;
            float numPlazas;
            float numPuertas;
            while(resultSet.next()){
                matricula = resultSet.getString("MATRICULA");
                precioHora = resultSet.getFloat("precioHora");
                marca = resultSet.getString("marca");
                descripcion = resultSet.getString("descripcion");
                color = resultSet.getString("color");
                bateria = resultSet.getFloat("bateria");
                estado = resultSet.getString("estado");
                idCarnet = resultSet.getFloat("idcarnet");
                date = resultSet.getDate("fechaadq");
                numPlazas = resultSet.getFloat("numplazas");
                numPuertas = resultSet.getFloat("numpuertas");

                vehiculoArrayList.add(new Coche(matricula, precioHora, marca, descripcion, color, bateria, estado, idCarnet, date, tabla ,numPlazas, numPuertas));
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return vehiculoArrayList;
    }
}
