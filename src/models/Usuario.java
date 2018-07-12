package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static main.Main.con;
import static views.Dialogs.error;

/**
 * Esta clase representa a un usuario.
 */
public class Usuario {
    
    private String username, password;
    private boolean isActive, isAdmin;
    
    /**
     * Constructor vacio, inicia con valores por defecto.
     */
    public Usuario() {
        this(null, null, true, false);
    }
    
    /**
     * Constructor de Usuario, recibe como argumentos:
     * El nombre de usuario (username).
     * La contrasenna.
     * Un booleano que indica si el usuario esta activo.
     * Un booleano que indica si el usuario es administrador.
     */
    public Usuario(String username, String password, boolean isActive,
        boolean isAdmin)
    {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.isAdmin = isAdmin;
    }

    /**
     * Devuelve el nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario al valor dado.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Devuelve la contraseña.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña al valor dado.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Indica si el usuario esta activo.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Activa o desactiva al usuario segun el valor dado.
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Indica si el usuario es administrador.
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Establece al usuario como administrador segun el valor dado.
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    /**
     * Registra un usuario en la base de datos.
     */
    public String insertar() throws SQLException {
        String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "INSERT INTO usuarios VALUES(?, ?, ?, DEFAULT)",
                // Pasamos los atributos
                username, password, isAdmin
            );
        } catch (SQLException ex) {
            throw ex; // Si ocurre un error lo notificamos
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Modifica un usuario en la base de datos.
     */
    public String modificar() {
	String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE usuarios SET clave = ?, es_admin = ? " +
                "WHERE username = ?",
                // Pasamos los atributos
                password, isAdmin, username
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Realiza un borrado logico de un usuario en la base de datos.
     */
    public String inhabilitar() {
	String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE usuarios SET estado = 0 WHERE username = ?",
                // Pasamos los atributos
                username
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Habilita un usuario que habia sido borrado logicamente.
     */
    public String habilitar() {
        String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE usuarios SET estado = 1 WHERE username = ?",
                // Pasamos los atributos
                username
            );
        } catch (SQLException ex) {
             msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Listamos todos los usuarios de la base de datos.
     */
    public static ArrayList<Usuario> listar() {
        // Esta lista guardara los usuarios que traigamos de la base de datos
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        try {
            ResultSet rs = con.ejecutar(
                // Indicamos que vamos a traer resultados de la base de datos
                true,
                // Especificamos el SQL
                "SELECT * FROM usuarios ORDER BY username ASC"
            );
            
            while(rs.next()) { // Mientras tengamos registros de usuarios
                // Extraemos el valor de los campos
                String username = rs.getString("username");
                String password = rs.getString("clave");
                boolean isAdmin = rs.getBoolean("es_admin");
                boolean isActive = rs.getBoolean("estado");
                
                // Instanciamos un usuario
                Usuario usuario = new Usuario(
                    username, password, isAdmin, isActive
                );
                
                // Lo agregamos a la lista
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            error("Error de conexión a la base de datos.\n" +
                "Configure la conexión correctamente");
        }
        
        return usuarios; // Devolvemos la lista de usuarios
    }
    
    /**
     * Busca un usuario por su username.
     */
    public static Usuario buscar(String username) {
        try {
            ResultSet rs = con.ejecutar(
                // Indicamos que vamos a traer resultados de la base de datos
                true,
                // Especificamos el SQL
                "SELECT * FROM usuarios WHERE username = ?",
                // Pasamos los atributos
                username
            );
            
            if (rs.first()) { // Si encontramos al usuario
                // Obtenemos los valores de los campos
                String name = rs.getString("username");
                String password = rs.getString("clave");
                boolean es_admin = rs.getBoolean("es_admin");
                boolean estado = rs.getBoolean("estado");
                
                // Retornamos una instancia del usuario
                return new Usuario(name, password, es_admin, estado);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
}
