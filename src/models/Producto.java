package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static main.Main.con;
import static views.Dialogs.error;

/**
 * Esta clase modela un producto del almacen.
 */
public class Producto {
    
    private String codigo, categoria, nombre, descripcion;
    private double precio_unitario;
    private int cantidad;
    private boolean estado;

    /**
     * El constructor recibe como parametros:
     * El identificador del producto.
     * El nombre del producto.
     * La descripcion del producto.
     * La cantidad del producto en el stock.
     */
    public Producto(String codigo, String categoria, String nombre,
        String descripcion, double precio_unitario, int cantidad)
    {
        this(
            codigo, categoria, nombre, descripcion,
            precio_unitario, cantidad, true
        );
    }

    /**
     * Constructor vacio, inicia con valores por defecto.
     */
    public Producto() {
        this(null, null, null, null, 0.0, 0);
    }
    
    public Producto(String codigo, String categoria, String nombre,
        String descripcion, double precio_unitario, int cantidad,
        boolean estado)
    {
        // Inicializa los valores
        this.codigo = codigo;
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio_unitario = precio_unitario;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    /**
     * Devuelve el identificador del producto.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el identificador al valor dado.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Devuelve el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto al valor dado.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la descripcion del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion del producto al valor dado.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la cantidad en stock del producto.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad en stock al valor dado.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Devuelve la categoria del producto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoria del producto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Devuelve el precio unitario del producto.
     */
    public double getPrecio_unitario() {
        return precio_unitario;
    }

    /**
     * Establece el precio unitario del producto.
     */
    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
    
    /**
     * Registra un producto en la base de datos.
     */
    public String insertar() {
        String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "INSERT INTO productos VALUES(?, ?, ?, ?, ?, ?, DEFAULT)",
                // Pasamos los atributos
                codigo, categoria, nombre, descripcion,
                precio_unitario, cantidad
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Modifica un producto en la base de datos.
     * @return 
     */
    public String modificar() {
	String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE productos SET categoria = ?, nombre = ?, " +
                "descripcion = ?, precio_unitario = ?, cantidad = ? " +
                "WHERE codigo = ?",
                // Pasamos los atributos
                categoria, nombre, descripcion, precio_unitario,
                cantidad, codigo
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Realiza un borrado logico de un producto en la base de datos.
     */
    public String inhabilitar() {
	String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE productos SET estado = 0 WHERE codigo = ?",
                // Pasamos los atributos
                codigo
            );
        } catch (SQLException ex) {
            msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Habilita un producto que habia sido borrado logicamente.
     */
    public String habilitar() {
        String msg = ""; // Aca guardamos los mensajes de error
        
        try {
            con.ejecutar(
                // Especificamos el SQL
                "UPDATE productos SET estado = 1 WHERE codigo = ?",
                // Pasamos los atributos
                codigo
            );
        } catch (SQLException ex) {
             msg = ex.getMessage(); // Si ocurre un error guardamos el mensaje
        }
        
        return msg; // Retornamos el mensaje
    }
    
    /**
     * Listamos todos los productos de la base de datos.
     */
    public static ArrayList<Producto> getAll() {
        // Esta lista guardara los productos que traigamos de la base de datos
        ArrayList<Producto> productos = new ArrayList<>();
        
        try {
            ResultSet rs = con.ejecutar(
                // Indicamos que vamos a traer resultados de la base de datos
                true,
                // Especificamos el SQL
                "SELECT * FROM productos ORDER BY codigo ASC"
                );
            
            while(rs.next()) { // Mientras tengamos registros de productos
                // Extraemos el valor de los campos
                String codigo = rs.getString("codigo");
                String categoria = rs.getString("cod_categoria");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio_unitario = rs.getDouble("precio_unitario");
                int cantidad = rs.getInt("cantidad");
                boolean estado = rs.getBoolean("estado");
                
                // Instanciamos un producto
                Producto producto = new Producto(
                    codigo, categoria, nombre, descripcion,
                    precio_unitario, cantidad, estado
                );
                
                // Lo agregamos a la lista
                productos.add(producto);
            }
        } catch (SQLException ex) {
            error("Error de conexión a la base de datos.\n" +
                "Configure la conexión correctamente");
        }
        
        return productos; // Devolvemos la lista de productos
    }
    
}
