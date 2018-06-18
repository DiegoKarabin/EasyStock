package models;

/**
 * Esta clase modela un producto del almacen.
 */
public class Producto {
    
    private String id, nombre, descripcion;
    private int stock;

    /**
     * El constructor recibe como parametros:
     * El identificador del producto.
     * El nombre del producto.
     * La descripcion del producto.
     * La cantidad del producto en el stock.
     */
    public Producto(String id, String nombre, String descripcion, int stock) {
        // Inicializa los valores
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    /**
     * Constructor vacio, inicia con valores por defecto.
     */
    public Producto() {
        this(null, "", "", 0);
    }

    /**
     * Devuelve el identificador del producto.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador al valor dado.
     */
    public void setId(String id) {
        this.id = id;
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
    public int getStock() {
        return stock;
    }

    /**
     * Establece la cantidad en stock al valor dado.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
}
