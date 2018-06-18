package models.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Parametros;
import models.DBConnection;
import models.Usuario;

/**
 * Esta clase realiza las operaciones de CRUD para la tabla de usuarios.
 */
public class UsuarioDAO {
    
    /**
     * Busca un usuario en la base de datos segun su username
     */
    public Usuario get(String username) {
        /*
        Inicializamos la conexion y la variable donde guardaremos el resultado
        de la consulta
        */
        ResultSet resultado = null;
        DBConnection conn = new DBConnection( // Usamos la clase Parametros
            "localhost/" + Parametros.nombre_bd,
            Parametros.usuario,
            Parametros.password
        );
        
        try {
            // Creamos una consulta preparada
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement query = conn.getConnection()
                                          .prepareStatement(sql);
            // Pasamos el username a la consulta
            query.setString(1, username);
            // Ejecutamos la consulta
            resultado = query.executeQuery();
            
            // Si tenemos un resultado instanciamos un usuario
            if (resultado.next())
                return new Usuario(
                    resultado.getString("username"),
                    resultado.getString("password"),
                    resultado.getBoolean("is_active"),
                    resultado.getBoolean("is_admin")
                );
        } catch (SQLException ex) {
            // Si ocurre un error lo notificamos
            ex.printStackTrace();
        } finally {
            // Cerramos la conexion
            conn.close();
        }
        
        // Si no encontramos al usuario retornamos null
        return null;
    }
    
    /**
     * Crea un nuevo usuario en la base de datos.
     * Si el usuario que se intenta crear ya existe, genera un error.
     */
    public boolean insert(Usuario user)
        throws RecordAlreadyExistsException
    {
        // Inicializamos la conexion
        DBConnection conn = new DBConnection(
            "localhost/" + Parametros.nombre_bd,
            Parametros.usuario,
            Parametros.password
        );
        // Usamos una variable para determinar el exito de la operacion
        boolean exito = false;
        
        try {
            /*
            Verificamos que el usuario exista en la base de datos,
            si EXISTE lanzamos un error.
            */
            if (get(user.getUsername()) == null) {
                // Creamos la sentencia SQL
                String sql = "INSERT INTO users VALUES (?, ?, ?, ?)";
                PreparedStatement query = conn.getConnection()
                        .prepareStatement(sql);
                // Pasamos los campos del usuario a la sentencia
                query.setString(1, user.getUsername());
                query.setString(2, user.getPassword());
                query.setBoolean(3, user.isActive());
                query.setBoolean(4, user.isAdmin());
                // Ejecutamos la sentencia
                query.executeUpdate();
                // En este punto se realizo exitosamente
                exito = true;
            } else throw new RecordAlreadyExistsException("Usuario ya registrado.");
        } catch (SQLException ex) {
            // Si ocurre un error lo notificamos
            ex.printStackTrace();
        } finally {
            // Cerramos la conexion
            conn.close();
        }
        
        // Indicamos si la operacion fue o no exitosa
        return exito;
    }
    
    /**
     * Modifica un usuario en la base de datos.
     * Si el usuario que se intenta modificar no existe, genera un error.
     */
    public boolean update(Usuario user)
        throws RecordDoesntExistsException
    {
        // Inicializamos la conexion
        DBConnection conn = new DBConnection(
            "localhost/" + Parametros.nombre_bd,
            Parametros.usuario,
            Parametros.password
        );
        // Usamos una variable para determinar el exito de la operacion
        boolean exito = false;

        try {
            /*
            Verificamos que el usuario exista en la base de datos,
            si NO EXISTE asi lanzamos un error.
            */
            if (get(user.getUsername()) != null) {
                // Creamos la sentencia SQL
                String sql =
                    "UPDATE users SET password=?, is_active=?, is_admin=?" +
                    " WHERE username = ?";
                PreparedStatement query = conn.getConnection()
                                              .prepareStatement(sql);
                // Pasamos los campos del usuario a la sentencia
                query.setString(1, user.getPassword());
                query.setBoolean(2, user.isActive());
                query.setBoolean(3, user.isAdmin());
                query.setString(4, user.getUsername());
                // Ejecutamos la sentencia
                query.executeUpdate();
                // En este punto se realizo exitosamente
                exito = true;
            } else
                throw new RecordDoesntExistsException("Usuario no existe.");
        } catch (SQLException ex) {
            // Si ocurre un error, lo notificamos
            ex.printStackTrace();
        } finally {
            // Cerramos la conexion
            conn.close();
        }

        // Indicamos si la operacion fue o no exitosa
        return exito;
    }
    
    /**
     * Borra un usuario de la base de datos.
     * Si el usuario que se intenta borrar no existe, genera un error.
     */
    public boolean delete(Usuario user)
        throws RecordDoesntExistsException
    {
        // Inicializamos la conexion
        DBConnection conn = new DBConnection(
            "localhost/" + Parametros.nombre_bd,
            Parametros.usuario,
            Parametros.password
        );
        // Usamos una variable para determinar el exito de la operacion
        boolean exito = false;

        try {
            /*
            Verificamos que el usuario exista en la base de datos,
            si NO EXISTE lanzamos un error
            */
            if (get(user.getUsername()) != null) {
                // Creamos la sentencia SQL
                String sql = "DELETE FROM users WHERE username = ?";
                PreparedStatement query = conn.getConnection()
                        .prepareStatement(sql);
                // Pasamos el username a la sentencia
                query.setString(1, user.getUsername());
                // Ejecutamos la sentencia
                query.executeUpdate();
                // En este punto se realizo correctamente
                exito = true;
            } else
                throw new RecordDoesntExistsException("Usuario no existe.");
        } catch (SQLException ex) {
            // Si ocurre un error lo notificamos
            ex.printStackTrace();
        } finally {
            // Cerramos la conexion
            conn.close();
        }

        // Indicamos si la operacion fue o no exitosa        
        return exito;
    }
    
}
