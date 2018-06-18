package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static views.Dialogs.error;


/**
 * Esta clase nos permite establecer una conexion con la base de datos.
 */
public class DBConnection {
    
    private String url, user, pass;
    private Connection cn;

    /**
     * El constructor de la conexion recibe como argumentos:
     * La url a la base de datos.
     * El usuario con el que se va a conectar.
     * El password para acceder.
     */
    public DBConnection(String url, String user, String pass) {
        // Inicializa los valores
        this.url = url;
        this.user = user;
        this.pass = pass;
        
        // Si son valores validos, crea una conexion
        if (url != null && user != null && pass != null)
            getConnection(url, user, pass);
        else
            // Sino la conexion es null
            this.cn = null;
    }
    
    /**
     * Constructor Vacio. Pasa valores null por defecto.
     */
    public DBConnection() {
        this(null, null, null);
    }
    
    
    /**
     * Este metodo nos crea una conexion con la base de datos.
     */
    public Connection getConnection(String url, String user, String pass) {
        try {
            // Verifica que este el driver de MySQL
            Class.forName("com.mysql.jdbc.Driver");
            // Intenta crear la conexion
            this.cn = DriverManager.getConnection(
                    "jdbc:mysql://" + url, user, pass);
            // Si todo sale bien, muestra un mensaje por consola
            System.out.println("Conexión con la base de datos exitosa.");
        } catch (ClassNotFoundException ex) {
            // Si el driver no esta instalado, muestra este error
            error("El driver para mysql no está instalado.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            /*
            Si ocurre un error al conectarse a la base de datos,
            muestra el error
            */
            error("Ocurrio un error al conectarse con la base de datos.");
            ex.printStackTrace();
        }
        
        // Devuelve el objeto con la conexion a la base de datos
        return this.cn;
    }
    
    /**
     * Devuelve el objeto con la conexion a la base de datos.
     */
    public Connection getConnection() {
        return this.cn;
    }
    
    /**
     * Abre la conexion con la base de datos.
     */
    public Connection open() {
        return getConnection(url, user, pass);
    }
    
    /**
     * Cierra la conexion con la base de datos.
     */
    public void close() {
        if (cn != null)
            try {
                cn.close();
            } catch (SQLException ex) {
                error("Error al cerrar la conexion.");
                ex.printStackTrace();
            }
    }
    
}
