package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static main.Main.con;
import static views.Dialogs.error;

/**
 * Esta clase representa un proveedor.
 */
public class Proveedor {
    
    private String id, tipo_doc, nombre, direccion, telefono;
    private boolean estado;
    
    public static final String[] TIPO_DOC = {"V", "E", "J"};

    public Proveedor() {
        this(null, null, null, null, null);
    }

    public Proveedor(String id, String tipo_doc, String nombre,
        String direccion, String telefono)
    {
        this(id, tipo_doc, nombre, direccion, telefono, true);
    }
    
    public Proveedor(String id, String tipo_doc, String nombre,
        String direccion, String telefono, boolean estado)
    {
        this.id = id;
        this.tipo_doc = tipo_doc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    /**
     * Devuelve el id del proveedor
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el id del proveedor
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del proveedor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del proveedor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la direccion del proveedor
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la direccion del proveedor
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve el telefono del proveedor
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el telefono del proveedor
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuleve el tipo de documento del proveedor
     */
    public String getTipo_doc() {
        return tipo_doc;
    }

    /**
     * Establece el tipo de documento del proveedor
     */
    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }
    
    /**
     * Registra un proveedor en la base de datos.
     */
    public String insertar() {
        String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "INSERT INTO proveedores VALUES(?, ?, ?, ?, ?, DEFAULT)",
                // Pasamos los atributos
                id, tipo_doc, nombre, direccion, telefono
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Modifica un proveedor en la base de datos.
     */
    public String modificar() {
	String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE proveedores SET tipo_doc = ?, nombre = ?, " +
                "direccion = ?, telefono = ? WHERE id = ?",
                // Pasamos los atributos
                tipo_doc, nombre, direccion, telefono, id
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Realiza un borrado logico de un proveedor en la base de datos.
     */
    public String inhabilitar() {
	String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE proveedores SET estado = 0 WHERE id = ?",
                // Pasamos los atributos
                id
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Habilita un proveedor que habia sido borrado logicamente.
     */
    public String habilitar() {
        String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE proveedores SET estado = 1 WHERE id = ?",
                // Pasamos los atributos
                id
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Listamos todos los proveedores de la base de datos.
     */
    public static ArrayList<Proveedor> getAll() {
        // Esta lista guardara los proveedores que traigamos de la base de datos
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        
        try {
            ResultSet rs = con.ejecutar(
                // Indicamos que vamos a traer resultados de la base de datos
                true,
                // Especificamos el SQL
                "SELECT * FROM proveedores ORDER BY id ASC"
            );
            
            while(rs.next()) { // Mientras tengamos registros de proveedores
                // Extraemos el valor de los campos
                String id = rs.getString("id");
                String tipo_doc = rs.getString("tipo_doc");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                boolean estado = rs.getBoolean("estado");
                
                // Instanciamos un proveedor
                Proveedor proveedor = new Proveedor(
                    id, tipo_doc, nombre, direccion, telefono, estado
                );
                
                // Lo agregamos a la lista
                proveedores.add(proveedor);
            }
        } catch (SQLException ex) {
            error("Error de conexión a la base de datos.\n" +
                "Configure la conexión correctamente");
        }
        
        return proveedores; // Devolvemos la lista de proveedores
    }
    
}
