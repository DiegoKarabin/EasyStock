package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static views.Dialogs.error;


/**
 * Esta clase nos permite establecer una conexion con la base de datos.
 * 
 * Incluye la conexion, validacion y acceso a la base de datos en el host respectivo.
 * 
 */
public class DBConnection {
    
    private String host, database, user, pass;
    private Connection cn;

    /**
     * El constructor de la conexion recibe como argumentos:
     * El host donde se encuentra la base de datos
     * El nombre de la base de datos
     * El usuario con el que se va a conectar
     * El password para acceder
     */
    public DBConnection(String host, String database, String user, String pass)
    {
        // Inicializa los valores
        this.host = host;
        this.database = database;
        this.user = user;
        this.pass = pass;
    }
    
    /**
     * Constructor Vacio. Pasa valores null por defecto.
     */
    public DBConnection() {
        this(null, null, null, null);
    }
    
    public boolean conectar(boolean verificar) {
        boolean ok = true;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                "jdbc:mysql://" + host + ":3306/" + database, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            if(!verificar)
                error(
                    "Error de conexión a la base de datos.\n" +
                    "Configure la conexión correctamente.\n" +
                    "Verifique que este encendido el servidor de MySQL."
                );
            
            ok = false;
        }
        
        return ok;
    }
    
    public ResultSet ejecutar(String comando, Object ... data)
        throws SQLException
    {
        return ejecutar(false, comando, data);
    }
    
    public ResultSet ejecutar(boolean receive, String comando, Object ... data)
        throws SQLException
    {
        ResultSet rs = null;
        
        if(cn != null) {
            PreparedStatement preparedStmt = cn.prepareStatement(comando);
            
            if(data.length > 0)
                for(int i = 0; i < data.length; i++)
                    preparedStmt.setObject(i + 1, data[i]);
                    //preparedStmt.setString(i + 1, data[i]);
            
            if(receive)
                rs = preparedStmt.executeQuery();
            else
                preparedStmt.executeUpdate();
        }
        
        return rs;
    }
    
    public void desconectar()
    {
        try {
            cn.close();
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                "Access denied for User: " + user + ", Password: " + pass +
                ". Configure DB connection.");
        }
    }
}
