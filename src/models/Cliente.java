package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static main.Main.con;
import static views.Dialogs.error;

/**
 * Esta clase representa un cliente.
 */
public class Cliente {
    
    private String id, tipo_doc, nombre, apellido, direccion, telefono;
    private boolean estado;
    
    public static final String[] TIPO_DOC = {"V", "E"};
    
    public Cliente() {
        this(null, null, null, null, null, null);
    }
    
    public Cliente(String id, String tipo_doc, String nombre, String apellido,
        String direccion, String telefono)
    {
        this(id, tipo_doc, nombre, apellido, direccion, telefono, true);
    }
    
    public Cliente(String id, String tipo_doc, String nombre, String apellido,
        String direccion, String telefono, boolean estado)
    {
        this.id = id;
        this.tipo_doc = tipo_doc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    /**
     * Devuelve el id del cliente
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el id del cliente
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el apellido del cliente
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del cliente
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Devuelve la direccion del cliente
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la direccion del cliente
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve el telefono del cliente
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el telefono del cliente
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuleve el tipo de documento del cliente
     */
    public String getTipo_doc() {
        return tipo_doc;
    }

    /**
     * Establece el tipo de documento del cliente
     */
    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    /**
     * Devuelve el estado de un cliente
     */
    public boolean getEstado() {
        return estado;
    }

    /**
     * Establece el estado de un cliente
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    /**
     * Registra un cliente en la base de datos.
     */
    public String insertar() {
        String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "INSERT INTO clientes VALUES(?, ?, ?, ?, ?, ?, DEFAULT)",
                // Pasamos los atributos
                id, tipo_doc, nombre, apellido, direccion, telefono
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Modifica un cliente en la base de datos.
     */
    public String modificar() {
	String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE clientes SET tipo_doc = ?, nombre = ?, apellido = ?, " +
                "direccion = ?, telefono = ?, estado = ? WHERE id = ?",
                // Pasamos los atributos
                tipo_doc, nombre, apellido, direccion, telefono, estado, id
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Realiza un borrado logico de un cliente en la base de datos.
     */
    public String inhabilitar() {
	String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE clientes SET estado = 0 WHERE id = ?",
                // Pasamos los atributos
                id
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Habilita un cliente que habia sido borrado logicamente.
     */
    public String habilitar() {
        String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE clientes SET estado = 1 WHERE id = ?",
                // Pasamos los atributos
                id
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Listamos todos los clientes de la base de datos.
     */
    public static ArrayList<Cliente> getAll() {
        // Esta lista guardara los clientes que traigamos de la base de datos
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try {
            ResultSet rs = con.ejecutar(
                // Indicamos que vamos a traer resultados de la base de datos
                true,
                // Especificamos el SQL
                "SELECT * FROM clientes ORDER BY id ASC"
                );
            
            while(rs.next()) { // Mientras tengamos registros de clientes
                // Extraemos el valor de los campos
                String id = rs.getString("id");
                String tipo_doc = rs.getString("tipo_doc");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                boolean estado = rs.getBoolean("estado");
                
                // Instanciamos un cliente
                Cliente cliente = new Cliente(
                    id, tipo_doc, nombre, apellido, direccion, telefono, estado
                );
                
                // Lo agregamos a la lista
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            error("Error de conexión a la base de datos.\n" +
                "Configure la conexión correctamente");
        }
        
        return clientes; // Devolvemos la lista de clientes
    }
    
}
