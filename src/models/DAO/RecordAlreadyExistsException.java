package models.DAO;

/**
 * Este error se muestra cuando se intenta crear un registro que ya existe en
 * la base de datos.
 */
public class RecordAlreadyExistsException extends Exception {
    
    public RecordAlreadyExistsException(String message) {
        super(message);
    }
    
}
