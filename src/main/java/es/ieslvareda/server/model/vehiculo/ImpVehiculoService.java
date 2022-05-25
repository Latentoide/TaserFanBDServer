package es.ieslvareda.server.model.vehiculo;

import es.ieslvareda.model.*;
import oracle.jdbc.OracleTypes;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class ImpVehiculoService implements IVehiculoService {
    @Override
    public List<Vehiculo> getAll() {
        List<Vehiculo> allVels = new LinkedList<>();
        Tablas tipos[] = {
                Tablas.COCHE,
                Tablas.MOTO,
                Tablas.PATINETE,
                Tablas.BICICLETA
        };
        for (int i = 0; i < 4; i++) {
            for (Vehiculo vel : getAll(tipos[i])) {
                allVels.add(vel);
            }
        }
        return allVels;
    }

    @Override
    public List<Vehiculo> getAll(Tablas tabla) {
        List<Vehiculo> vehiculoArrayList = new ArrayList<>();
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String query = "{call GESTIONVEHICULOS.listarvehiculos(?, ?)}";
        ResultSet resultSet;
        try (Connection con = ds.getConnection();
             CallableStatement st = con.prepareCall(query);
        ) {
            st.setString(1, tabla.getStr());
            st.registerOutParameter(2, OracleTypes.CURSOR);
            st.execute();

            resultSet = (ResultSet) st.getObject(2);
            while (resultSet.next()) {
                String matricula;
                float precioHora;
                String marca;
                String color;
                String estado;
                String idCarnet;
                while (resultSet.next()) {
                    matricula = resultSet.getString("c1");
                    precioHora = resultSet.getFloat("n1");
                    marca = resultSet.getString("c2");
                    color = resultSet.getString("c4").toLowerCase();
                    estado = resultSet.getString("c6").toLowerCase();
                    idCarnet = resultSet.getString("c8");
                    Color c = Color.NEGRO;
                    Estado e = Estado.PREPARADO;
                    switch (color) {
                        case "verde":
                            c = Color.VERDE;
                            break;
                        case "amarillo":
                            c = Color.AMARILLO;
                            break;
                        case "rojo":
                            c = Color.ROJO;
                            break;
                        case "blanco":
                            c = Color.BLANCO;
                            break;
                        case "negro":
                            c = Color.NEGRO;
                            break;
                        case "azul":
                            c = Color.AZUL;
                            break;
                    }
                    switch (estado) {
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


                    vehiculoArrayList.add(new Vehiculo(matricula, precioHora, marca, c, e, idCarnet, tabla));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return vehiculoArrayList;
    }


}
