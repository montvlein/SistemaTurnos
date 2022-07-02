package com.dh.proyecto.Repository.DAO.implement;

import com.dh.proyecto.Models.entities.Domicilio;
import com.dh.proyecto.Repository.DAO.ConfigDB;
import com.dh.proyecto.Repository.DAO.iDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static com.dh.proyecto.Repository.DAO.ConfigDB.getConnection;


public class DomicilioDao implements iDao<Domicilio> {

    public static Logger logger = Logger.getLogger(DomicilioDao.class);
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    @Override
    public boolean guardar(Domicilio o) {
        boolean result = false;
        String SQL_QUERY = "INSERT INTO domicilios (calle,numero,localidad, provincia) values(?, ?, ?, ?)";
        ConfigDB.loadDriver();

        try {
            stmt = initQuery(SQL_QUERY);
            stmt.setString(1,o.getCalle());
            stmt.setInt(2,o.getNumero());
            stmt.setString(3, o.getLocalidad());
            stmt.setString(4, o.getProvincia());

            stmt.executeUpdate();
            result = true;
            String msg = "Se guardo correctamente el domicilio";
            logger.info(msg);
        }

        catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    @Override
    public boolean eliminar(Long id) {
        boolean result = false;
        String SQL_QUERY = "DELETE FROM domicilios WHERE id = ?";
        ConfigDB.loadDriver();


        try {
            stmt = initQuery(SQL_QUERY);
            stmt.setLong(1,id);
            stmt.executeUpdate();
            result = true;
            logger.info("Se borro correctamente");
        }

        catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    @Override
    public Domicilio buscar(Long id) {
        Domicilio result = null;
        String SQL_QUERY = "SELECT * FROM domicilios WHERE id = ?";
        ConfigDB.loadDriver();

        try {
            stmt = initQuery(SQL_QUERY);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String calle = rs.getString("calle");
                String localidad = rs.getString("localidad");
                String prov = rs.getString("provincia");
                int numero = rs.getInt("numero");

                result = new Domicilio();
                result.setId(id);
                result.setCalle(calle);
                result.setNumero(numero);
                result.setLocalidad(localidad);
                result.setProvincia(prov);
            }
            logger.info("Se encontro el domicilios: " + result);
        }

        catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    @Override
    public HashMap<Long, Domicilio> listar() {
        HashMap<Long, Domicilio> result = new HashMap<>();
        String SQL_QUERY = "SELECT * FROM domicilios";
        ConfigDB.loadDriver();

        try {
            stmt = initQuery(SQL_QUERY);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String calle = rs.getString("calle");
                String localidad = rs.getString("localidad");
                String prov = rs.getString("provincia");
                int numero = rs.getInt("numero");

                Domicilio o = new Domicilio();
                o.setId(id);
                o.setCalle(calle);
                o.setNumero(numero);
                o.setLocalidad(localidad);
                o.setProvincia(prov);
                result.put(o.getId(),o);
            }
            logger.info("Lista de domicilios encontrada");
        }

        catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    private PreparedStatement initQuery(String q) {
        try {
            conn = this.conn != null ? this.conn : getConnection();
            stmt = conn.prepareStatement(q);
        }
        catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return stmt;
    }
}
