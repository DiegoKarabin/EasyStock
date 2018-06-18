package models.DAO;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Parametros;
import models.DBConnection;
import models.Producto;

/**
 * Esta clase realiza las operaciones de CRUD para la tabla de productos.
 */
public class ProductoDAO {
    
    /**
     * Busca un producto en la base de datos segun su identificador.
     */
    public Producto get(String id) {
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
            String sql = "SELECT * FROM productos WHERE id = ?";
            PreparedStatement query = conn.getConnection().prepareStatement(sql);
            // Pasamos el id a la consulta
            query.setString(1, id);
            // Ejecutamos la consulta
            resultado = query.executeQuery();
            
            // Si tenemos un resultado instanciamos un Producto
            if (resultado.next()) {
                return new Producto(
                    resultado.getString("id"),
                    resultado.getString("nombre"),
                    resultado.getString("descripcion"),
                    resultado.getInt("stock")
                );
            }
        } catch (SQLException e) {
            // Si ocurre un error, lo notificamos
            e.printStackTrace();
        } finally {
            // Cerramos la conexion
            conn.close();
        }
        
        // Si no encontramos el producto retornamos null
        return null;
    }
    
    /**
     * Creamos un nuevo producto en la base de datos.
     * Si el producto que se intenta crear ya existe, genera un error.
     */
    public boolean insert(Producto producto)
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
            Verificamos si existe el producto en la base de datos,
            si EXISTE lanzamos un error.
            */
            if (get(producto.getId()) == null) {
                // Creamos la sentencia SQL
                String sql = "INSERT INTO productos VALUES (?, ?, ?, ?)";
                PreparedStatement query = conn.getConnection()
                                              .prepareStatement(sql);
                // Pasamos los campos del producto a la sentencia
                query.setString(1, producto.getId());
                query.setString(2, producto.getNombre());
                query.setString(3, producto.getDescripcion());
                query.setInt(4, producto.getStock());
                // Ejecutamos la sentencia
                query.executeUpdate();
                // En este punto se realizo exitosamente
                exito = true;
            } else
                throw new RecordAlreadyExistsException("Producto ya registrado");
        } catch (SQLException e) {
            // Si ocurre un error, lo notificamos
            e.printStackTrace();
        } finally {
            // Cerramos la conexion
            conn.close();
        }
        
        // Indicamos si la operacion fue o no exitosa
        return exito;
    }
    
    /**
     * Modifica un producto en la base de datos.
     * Si el producto que se intenta modificar no existe, genera un error.
     */
    public boolean update(Producto producto)
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
            Verificamos que el producto exista en la base de datos.
            Si el producto NO EXISTE lanzamos un error.
            */
            if (get(producto.getId()) != null) {
                // Creamos la sentencia SQL
                String sql =
                    "UPDATE productos SET nombre=?, descripcion=?, stock=? " +
                    "WHERE id=?";
                PreparedStatement query = conn.getConnection()
                                              .prepareStatement(sql);
                // Pasamos los campos del producto a la sentencia
                query.setString(1, producto.getNombre());
                query.setString(2, producto.getDescripcion());
                query.setInt(3, producto.getStock());
                query.setString(4, producto.getId());
                // Ejecutamos la sentencia
                query.executeUpdate();
                // En este punto se realizo exitosamente
                exito = true;
            } else
                throw new RecordDoesntExistsException("Producto no existe");
        } catch (SQLException e) {
            // Si ocurre un error, lo notificamos.
            e.printStackTrace();
        } finally {
            // Cerramos la conexion
            conn.close();
        }
        
        // Indicamos si la operacion fue o no exitosa
        return exito;
    }
    
    /**
     * Elimina un producto de la base de datos.
     * Si el producto no existe, genera un error.
     */
    public boolean delete(Producto producto)
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
            Verificamos si el producto existe en la base de datos,
            si NO EXISTE lanzamos un error.
            */
            if (get(producto.getId()) != null) {
                // Creamos la sentencia SQL
                String sql = "DELETE productos WHERE id=?";
                PreparedStatement query = conn.getConnection()
                                              .prepareStatement(sql);
                // Pasamos el id a la sentencia
                query.setString(1, producto.getId());
                // Ejecutamos la sentencia
                query.execute();
                // En este punto se realizo exitosamente
                exito = true;
            } else
                throw new RecordDoesntExistsException("Producto no existe");
        } catch (SQLException e) {
            // Si ocurre un error, lo indicamos
            e.printStackTrace();
        } finally {
            // Cerramos la conexion
            conn.close();
        }
        
        // Indicamos si la operacion fue o no exitosa
        return exito;
    }
    
}
