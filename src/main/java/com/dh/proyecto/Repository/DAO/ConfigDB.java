package com.dh.proyecto.Repository.DAO;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    private final static String driver = "org.h2.Driver";
    private final static String dbUrl = "jdbc:h2:~/java-things/dbs/proyecto/db_proyecto_BackendUno;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String nombreUsuario = "userDH";
    private final static String contrasenaUsuario = "";
    public static Logger logger = Logger.getLogger(ConfigDB.class);

    public static Connection getConnection() {
        Connection result = null;
        try {
            result = DriverManager.getConnection(dbUrl,nombreUsuario,contrasenaUsuario);
        } catch (SQLException e) {
            logger.error("Error: problemas al intentar conectar con la base de datos!\n" + e.getMessage());
        }
        return result;
    }

    public static void loadDriver(){
        try {
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            logger.error("Error: problemas al cargar el driver!\n" + e.getMessage());
            System.exit(1);
        }
    }
}
