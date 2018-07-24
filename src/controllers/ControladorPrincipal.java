package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import views.main.VentanaPrincipal;
import static views.Dialogs.*;
import static main.Main.usuario;

public class ControladorPrincipal extends MouseAdapter {
    
    private VentanaPrincipal ventana;
    
    public ControladorPrincipal() {
        ventana = new VentanaPrincipal(this);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
        agregar_confirmacion_salida(ventana);
    }

    /**
     * Capturamos los clicks del raton y los procesamos para tomar
     * accion.
     */
    public void mousePressed(MouseEvent e) {
        // Si desea agregar un usuario
        if (e.getSource().equals(ventana.getOpcionAgregarUsuario()))
            new ControladorCrearUsuario();
        
        // Si desea configurar su cuenta
        if (e.getSource().equals(ventana.getOpcionConfigurarCuenta()))
            new ControladorCrearUsuario(true, usuario);
            
        // Si presiona cerrar sesion.
        if (e.getSource().equals(ventana.getOpcionCerrarSesion())) {
            // Obtenemos la respuesta del usuario
            int opcion = confirm(
                ventana,
                "¿Esta seguro de que desea cerrar su sesión?",
                "Confirmación cierre de sesión",
                YES_NO_OPTION
            );
            
            // Si escoge si, cerramos la sesion
            if (opcion == YES_OPTION)
                cerrar_sesion();
        }
    }

    /**
     * Agregamos un dialogo de confirmacion para la salida del sistema.
     */
    private void agregar_confirmacion_salida(VentanaPrincipal ventana) {
        ventana.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
                int opcion = confirm(
                    ventana,
                    "¿Está seguro de que desea salir del sistema?",
                    "Confirmación de salida",
                    YES_NO_OPTION
                );

                if (opcion == YES_OPTION) {
                    System.exit(0);
                }
            }
            
        });
    }

    private void cerrar_sesion() {
        usuario = null;
        ventana.dispose();
        new ControladorInicioSesion();
    }
    
}
