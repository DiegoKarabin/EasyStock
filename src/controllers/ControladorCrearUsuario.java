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
        this.ventana = new FormularioCrearUsuario(this);
        this.primera_vez = primera_vez;
        
        if (primera_vez) {
            this.ventana.getPrivilegiosPanel().setEnabled(false);
            this.ventana.getGerenteRadio().doClick();
            this.ventana.getGerenteRadio().setEnabled(false);
            this.ventana.getCajeroRadio().setEnabled(false);
        }
        
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.ventana.getCreateBtn()))
            crearUsuario();
        if (e.getSource().equals(this.ventana.getCleanBtn()))
            limpiarCampos();
        if (e.getSource().equals(this.ventana.getCancelBtn()))
            cancelar();
    }     
    
    private void crearUsuario() {
        try {
            filtrar_campos();
            
            this.usuario.insertar();
            
            if (primera_vez)
                new ControladorInicioSesion();
                
            this.ventana.dispose();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062)
                // Si intentamos crear un usuario ya registrado
                warning(this.ventana,
                    "El usuario que intenta crear ya se encuentra registrado.");
            else
                warning(this.ventana, ex.getMessage());
        } catch (Exception ex) {
            warning(this.ventana, ex.getMessage());
        }
    }
    
    private void limpiarCampos() {
        this.ventana.getUsernameField().setText("");
        this.ventana.getPasswordField().setText("");
        this.ventana.getConfirmPassField().setText("");
        
        if (!primera_vez)
            this.ventana.getPrivilegiosBtnGrp().clearSelection();
    }
    
    private void cancelar() {
        this.ventana.dispose();
    }
    
    private void filtrar_campos() throws Exception {
        String nombre = this.ventana.getUsernameField().getText();
        String clave = String.valueOf(
            this.ventana.getPasswordField().getPassword()
        );
        String confirmacion = String.valueOf(
            this.ventana.getConfirmPassField().getPassword()
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
        if (this.ventana.getPrivilegiosBtnGrp().getSelection() == null)
            mensaje += "Indique los privilegios del usuario\n";
        
        if (!mensaje.equals(""))
            throw new Exception(mensaje);
        else
            usuario = new Usuario(
                nombre,
                clave,
                this.ventana.getPrivilegiosBtnGrp().isSelected(
                    this.ventana.getGerenteRadio().getModel()),
                true
            );
    }
}
