package models.DAO;

/**
 * Este error se muestra cuando intentamos acceder a un registro que no existe
 * en la base de datos.
 */
public class RecordDoesntExistsException extends Exception {
    
    public RecordDoesntExistsException(String message) {
        super(message);
    }
    
}
