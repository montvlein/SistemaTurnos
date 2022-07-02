package com.dh.proyecto.Repository.DAO.implement;

import com.dh.proyecto.Models.entities.Paciente;
import com.dh.proyecto.Repository.DAO.ConfigDB;
import com.dh.proyecto.Repository.DAO.iDao;
import com.dh.proyecto.Services.implement.DomicilioService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.HashMap;

import static com.dh.proyecto.Repository.DAO.ConfigDB.getConnection;

public class PacienteDao implements iDao<Paciente> {

    public static Logger logger = Logger.getLogger(PacienteDao.class);
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    @Override
    public boolean guardar(Paciente o) {
        boolean result = false;
        String SQL_QUERY = "INSERT INTO pacientes (nombre, apellido, fecha_ingreso, id_domicilio) values(?, ?, ?, ?)";
        ConfigDB.loadDriver();

        try {
            stmt = initQuery(SQL_QUERY);
            stmt.setString(1,o.getNombre());
            stmt.setString(2, o.getApellido());
            stmt.setObject(3, o.getFechaIngreso()); // stmt.setDate(3, Date.valueOf(o.getFechaIngreso()));
            stmt.setLong(4,o.getDomicilio().getId());

            stmt.executeUpdate();
            result = true;
            String msg = "Se guardo correctamente el paciente";
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
        String SQL_QUERY = "DELETE FROM pacientes WHERE id = ?";
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
    public Paciente buscar(Long id) {
        Paciente result = null;
        String SQL_QUERY = "SELECT * FROM pacientes WHERE id = ?";
        ConfigDB.loadDriver();

        try {
            stmt = initQuery(SQL_QUERY);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                long id_domicilio = rs.getLong("id_domicilio");

                result = new Paciente();
                result.setId(id);
                result.setNombre(nombre);
                result.setApellido(apellido);
                result.setDomicilio(new DomicilioService().buscar(id_domicilio));
                // falta setear la fecha de ingreso
            }
            logger.info("Se encontro el odontologo: " + result);
        }

        catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    @Override
    public HashMap<Long, Paciente> listar() {
        HashMap<Long, Paciente> result = new HashMap<>();
        String SQL_QUERY = "SELECT * FROM pacientes";
        ConfigDB.loadDriver();

        try {
            stmt = initQuery(SQL_QUERY);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                long id_domicilio = rs.getLong("id_domicilio");


                Paciente o = new Paciente();
                o.setId(id);
                o.setNombre(nombre);
                o.setApellido(apellido);
                o.setDomicilio(new DomicilioService().buscar(id_domicilio));
                // falta setear la fecha de ingreso
                result.put(o.getId(),o);
            }
            logger.info("Lista de pacientes encontrada");
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
