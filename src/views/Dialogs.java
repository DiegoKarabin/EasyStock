package views;

import java.awt.Container;
import javax.swing.JOptionPane;

/**
 * Esta clase tiene funciones para trabajar con los cuadros de dialogo
 * de una manera mas facil.
 */
public class Dialogs extends JOptionPane {
    
    /**
     * Muestra un cuadro de error centrado en la pantalla.
     */
    public static void error(String mensaje) {
        error(null, mensaje);
    }
    
    /**
     * Muestra un cuadro de error centrado en el contenedor padre.
     */
    public static void error(Container padre, String mensaje) {
        showMessageDialog(padre, mensaje, "Error", 0);
    }
    
    /**
     * Muestra un cuadro de informacion centrado en el contenedor padre.
     */
    public static void info(Container padre, String mensaje) {
        showMessageDialog(padre, mensaje, "Informacion", 1);
    }
    
    /**
     * Muestra un cuadro de advertencia centrado en el contenedor padre.
     */
    public static void warning(Container padre, String mensaje) {
        showMessageDialog(padre, mensaje, "Advertencia", 2);
    }
    
    /**
     * Muestra un cuadro de dialogo centrado en el contenedor padre.
     */
    public static void message(Container padre, String mensaje, String titulo,
        int tipo)
    {
        showMessageDialog(padre, mensaje, titulo, tipo);
        System.out.println(mensaje);
    }
    
    /**
     * Muestra un cuadro de confirmacion centrado en el contenedor padre.
     */
    public static int confirm(Container padre, String pregunta, String titulo,
        int opciones)
    {
        return showConfirmDialog(padre, pregunta, titulo, opciones);
    }
    
}
