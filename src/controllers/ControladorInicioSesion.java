package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Usuario;
import views.users.FormularioInicioSesion;

import static main.Main.usuario;
import static views.Dialogs.warning;

public class ControladorInicioSesion implements ActionListener {
    
    private FormularioInicioSesion ventana;
    
    public ControladorInicioSesion() {
        this.ventana = new FormularioInicioSesion(this);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventana.getBotonIngresar())
            iniciar_sesion();
        if (e.getSource() == ventana.getBotonCancelar())
            ventana.dispose();
    }
    
    private void iniciar_sesion() {
        try {
            filtrar_campos();
            
            String nombre = ventana.getCampoUsername().getText();
            String clave = String.valueOf(
                ventana.getCampoClave().getPassword()
            );
            
            Usuario u = Usuario.buscar(nombre);
            
            if (u == null)
                throw new Exception("El usuario \"" + nombre +
                    "\" no se encuentra registrado.");
            
            if (!u.getPassword().equals(clave))
                throw new Exception("La contraseña es incorrecta.");
            
            usuario = u;
            ventana.dispose();
            
            new ControladorPrincipal();
        } catch (Exception ex) {
            warning(ventana, ex.getMessage());
        }
    }
    
    private void filtrar_campos() throws Exception {
        String nombre = ventana.getCampoUsername().getText();
        String clave = String.valueOf(
            ventana.getCampoClave().getPassword()
        );
        
        String mensaje = "";
        
        if (nombre.trim().equals(""))
            mensaje += "Ingrese un nombre de usuario\n";
        if (clave.trim().equals(""))
            mensaje += "Ingrese una contraseña\n";
        
        if (!mensaje.equals(""))
            throw new Exception(mensaje);
    }
    
}
