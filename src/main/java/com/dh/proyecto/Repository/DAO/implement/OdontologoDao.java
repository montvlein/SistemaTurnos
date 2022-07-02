package com.dh.proyecto.Repository.DAO.implement;


import com.dh.proyecto.Models.entities.Odontologo;
import com.dh.proyecto.Repository.DAO.ConfigDB;
import com.dh.proyecto.Repository.DAO.iDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static com.dh.proyecto.Repository.DAO.ConfigDB.getConnection;


public class OdontologoDao implements iDao<Odontologo> {

    public static Logger logger = Logger.getLogger(OdontologoDao.class);
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    @Override
    public boolean guardar(Odontologo o) {
        boolean result = false;
        String SQL_QUERY = "INSERT INTO odontologos (matricula,nombre,apellido) values(?, ?, ?)";
        ConfigDB.loadDriver();

        try {
            stmt = initQuery(SQL_QUERY);
            stmt.setString(1,o.getMatricula());
            stmt.setString(2,o.getNombre());
            stmt.setString(3, o.getApellido());

            stmt.executeUpdate();
            result = true;
            String msg = "Se guardo correctamente el odontologo";
            msg = o.getNombre() == null || o.getApellido() == null ? msg : msg + ": " + o.getApellido() + ", " + o.getNombre();
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
        String SQL_QUERY = "DELETE FROM odontologos WHERE id = ?";
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
    public Odontologo buscar(Long id) {
        Odontologo result = null;
        String SQL_QUERY = "SELECT * FROM odontologos WHERE id = ?";
        ConfigDB.loadDriver();

        try {
            stmt = initQuery(SQL_QUERY);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String matricula = rs.getString("matricula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                result = new Odontologo();
                result.setId(id);
                result.setMatricula(matricula);
                result.setNombre(nombre);
                result.setApellido(apellido);
            }
            logger.info("Se encontro el odontologo: " + result);
        }

        catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    @Override
    public HashMap<Long, Odontologo> listar() {
        HashMap<Long, Odontologo> result = new HashMap<>();
        String SQL_QUERY = "SELECT * FROM odontologos";
        ConfigDB.loadDriver();

        try {
            stmt = initQuery(SQL_QUERY);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String matricula = rs.getString("matricula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                Odontologo o = new Odontologo();
                o.setId(id);
                o.setMatricula(matricula);
                o.setNombre(nombre);
                o.setApellido(apellido);
                result.put(o.getId(),o);
            }
            logger.info("Lista de odontologos encontrada");
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
