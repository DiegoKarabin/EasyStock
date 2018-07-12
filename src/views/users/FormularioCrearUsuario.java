package views.users;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import controllers.ControladorCrearUsuario;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import views.Validations;

public class FormularioCrearUsuario extends javax.swing.JFrame {
    
    private ControladorCrearUsuario controlador;
    
    public FormularioCrearUsuario(ControladorCrearUsuario controlador) {
        this.controlador = controlador;
        
        initComponents();
        
        Validations.length(usernameField, 50);
        Validations.only_alphanumeric(usernameField);
        Validations.length(passwordField, 100);
        Validations.length(confirmPassField, 100);
        
        setListener();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        privilegiosBtnGrp = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        privilegiosPanel = new javax.swing.JPanel();
        cajeroRadio = new javax.swing.JRadioButton();
        gerenteRadio = new javax.swing.JRadioButton();
        createBtn = new javax.swing.JButton();
        cleanBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        confirmPassField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Creación de Usuario");

        jLabel2.setText("Nombre de Usuario");

        jLabel3.setText("Contraseña");

        jLabel4.setText("Confirmar Contraseña");

        privilegiosPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Privilegios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        privilegiosBtnGrp.add(cajeroRadio);
        cajeroRadio.setText("Cajero");

        privilegiosBtnGrp.add(gerenteRadio);
        gerenteRadio.setText("Gerente");

        javax.swing.GroupLayout privilegiosPanelLayout = new javax.swing.GroupLayout(privilegiosPanel);
        privilegiosPanel.setLayout(privilegiosPanelLayout);
        privilegiosPanelLayout.setHorizontalGroup(
            privilegiosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(privilegiosPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(cajeroRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gerenteRadio)
                .addGap(48, 48, 48))
        );
        privilegiosPanelLayout.setVerticalGroup(
            privilegiosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(privilegiosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(privilegiosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajeroRadio)
                    .addComponent(gerenteRadio))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        createBtn.setText("Crear");

        cleanBtn.setText("Limiar Campos");

        cancelBtn.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(createBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34)
                .addComponent(cleanBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(privilegiosPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmPassField)
                            .addComponent(passwordField)
                            .addComponent(usernameField, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(confirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(privilegiosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cleanBtn)
                    .addComponent(cancelBtn)
                    .addComponent(createBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton cajeroRadio;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton cleanBtn;
    private javax.swing.JPasswordField confirmPassField;
    private javax.swing.JButton createBtn;
    private javax.swing.JRadioButton gerenteRadio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.ButtonGroup privilegiosBtnGrp;
    private javax.swing.JPanel privilegiosPanel;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public JButton getCleanBtn() {
        return cleanBtn;
    }

    public JPasswordField getConfirmPassField() {
        return confirmPassField;
    }

    public JButton getCreateBtn() {
        return createBtn;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public ButtonGroup getPrivilegiosBtnGrp() {
        return privilegiosBtnGrp;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JRadioButton getCajeroRadio() {
        return cajeroRadio;
    }

    public JRadioButton getGerenteRadio() {
        return gerenteRadio;
    }

    public JPanel getPrivilegiosPanel() {
        return privilegiosPanel;
    }
    
    public void setListener() {
        this.createBtn.addActionListener(controlador);
        this.cleanBtn.addActionListener(controlador);
        this.cancelBtn.addActionListener(controlador);
    }

}
