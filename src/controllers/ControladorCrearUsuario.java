package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import models.Usuario;
import views.users.FormularioCrearUsuario;
import static views.Dialogs.*;

public class ControladorCrearUsuario implements ActionListener {
    
    FormularioCrearUsuario ventana;
    boolean primera_vez;
    Usuario usuario;
    
    public ControladorCrearUsuario() {
        this(false);
    }

    public ControladorCrearUsuario(boolean primera_vez) {
        ventana = new FormularioCrearUsuario(this);
        this.primera_vez = primera_vez;
        
        if (primera_vez) {
            ventana.getPrivilegiosPanel().setEnabled(false);
            ventana.getGerenteRadio().doClick();
            ventana.getGerenteRadio().setEnabled(false);
            ventana.getCajeroRadio().setEnabled(false);
        }
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ventana.getCreateBtn()))
            crearUsuario();
        if (e.getSource().equals(ventana.getCleanBtn()))
            limpiarCampos();
        if (e.getSource().equals(ventana.getCancelBtn()))
            cancelar();
    }     
    
    private void crearUsuario() {
        try {
            filtrar_campos();
            
            usuario.insertar();
            
            if (primera_vez)
                new ControladorInicioSesion();
                
            ventana.dispose();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062)
                // Si intentamos crear un usuario ya registrado
                warning(ventana,
                    "El usuario que intenta crear ya se encuentra registrado.");
            else
                warning(ventana, ex.getMessage());
        } catch (Exception ex) {
            warning(ventana, ex.getMessage());
        }
    }
    
    private void limpiarCampos() {
        ventana.getUsernameField().setText("");
        ventana.getPasswordField().setText("");
        ventana.getConfirmPassField().setText("");
        
        if (!primera_vez)
            ventana.getPrivilegiosBtnGrp().clearSelection();
    }
    
    private void cancelar() {
        ventana.dispose();
    }
    
    private void filtrar_campos() throws Exception {
        String nombre = ventana.getUsernameField().getText();
        String clave = String.valueOf(
            ventana.getPasswordField().getPassword()
        );
        String confirmacion = String.valueOf(
            ventana.getConfirmPassField().getPassword()
        );
        
        String mensaje = "";
        
        if (nombre.trim().equals(""))
            mensaje += "Ingrese un nombre de usuario\n";
        if (clave.trim().equals(""))
            mensaje += "Ingrese una contraseña\n";
        if (confirmacion.trim().equals(""))
            mensaje += "Confirme la contraseña\n";
        if (!clave.equals(confirmacion))
            mensaje += "Las contraseñas no coinciden\n";
        if (ventana.getPrivilegiosBtnGrp().getSelection() == null)
            mensaje += "Indique los privilegios del usuario\n";
        
        if (!mensaje.equals(""))
            throw new Exception(mensaje);
        else
            usuario = new Usuario(
                nombre,
                clave,
                ventana.getPrivilegiosBtnGrp().isSelected(
                    ventana.getGerenteRadio().getModel()
                ),
                true
            );
    }
}
