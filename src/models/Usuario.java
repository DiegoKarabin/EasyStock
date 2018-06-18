package models;

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
    
}
