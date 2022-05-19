package es.ieslvareda.server.model.vehiculo;

import es.ieslvareda.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ImpVehiculoService implements IVehiculoService{
    @Override
    public List<Vehiculo> getAll(){
        List<Vehiculo> allVels = new LinkedList<>();
        Tablas tipos[] = {
                Tablas.COCHE,
                Tablas.MOTO,
                Tablas.PATINETE,
                Tablas.BICICLETA
        };
        for (int i = 0; i < 4; i++) {
            for(Vehiculo vel : getAll(tipos[i])){
                allVels.add(vel);
            }
        }
        return allVels;
    }

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
            String color;
            String estado;
            String idCarnet;
            while(resultSet.next()){
                matricula = resultSet.getString("MATRICULA");
                precioHora = resultSet.getFloat("precioHora");
                marca = resultSet.getString("marca");
                color = resultSet.getString("color");
                estado = resultSet.getString("estado");
                idCarnet = resultSet.getString("idcarnet");

                Color c = Color.valueOf(color.toUpperCase());
                Estado e = Estado.valueOf(estado.toUpperCase());

                vehiculoArrayList.add(new Vehiculo(matricula, precioHora, marca, c, e, idCarnet, tabla));
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return vehiculoArrayList;
    }


}
