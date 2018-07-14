package views.main;

import controllers.ControladorPrincipal;
import javax.swing.JMenuItem;

import static main.Main.usuario;

public class VentanaPrincipal extends javax.swing.JFrame {
    
    private ControladorPrincipal controlador;

    public VentanaPrincipal(ControladorPrincipal controlador) {
        this.controlador = controlador;
        
        initComponents();
        
        setListener();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menuClientes = new javax.swing.JMenu();
        opcionVerClientes = new javax.swing.JMenuItem();
        opcionAgregarCliente = new javax.swing.JMenuItem();
        menuProveedores = new javax.swing.JMenu();
        opcionVerProveedores = new javax.swing.JMenuItem();
        opcionAgregarProveedor = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JMenu();
        opcionVerProductos = new javax.swing.JMenuItem();
        opcionAgregarProducto = new javax.swing.JMenuItem();
        menuCotizaciones = new javax.swing.JMenu();
        opcionVerCotizaciones = new javax.swing.JMenuItem();
        opcionEmitirCotizacion = new javax.swing.JMenuItem();
        menuUsuarios = new javax.swing.JMenu();
        opcionVerUsuarios = new javax.swing.JMenuItem();
        opcionAgregarUsuario = new javax.swing.JMenuItem();
        menuOperaciones = new javax.swing.JMenu();
        opcionCompra = new javax.swing.JMenuItem();
        opcionVenta = new javax.swing.JMenuItem();
        menuReportes = new javax.swing.JMenu();
        opcionInventario = new javax.swing.JMenuItem();
        opcionComprasRealizadas = new javax.swing.JMenuItem();
        opcionVentasConsolidadas = new javax.swing.JMenuItem();
        menuCuenta = new javax.swing.JMenu();
        opcionConfigurarCuenta = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        opcionCerrarSesion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        menuClientes.setText("Clientes");

        opcionVerClientes.setText("Ver todos");
        menuClientes.add(opcionVerClientes);

        opcionAgregarCliente.setText("Agregar");
        menuClientes.add(opcionAgregarCliente);

        jMenuBar1.add(menuClientes);

        menuProveedores.setText("Proveedores");

        opcionVerProveedores.setText("Ver todos");
        menuProveedores.add(opcionVerProveedores);

        opcionAgregarProveedor.setText("Agregar");
        menuProveedores.add(opcionAgregarProveedor);

        jMenuBar1.add(menuProveedores);

        menuProductos.setText("Productos");

        opcionVerProductos.setText("Ver todos");
        menuProductos.add(opcionVerProductos);

        opcionAgregarProducto.setText("Agregar");
        menuProductos.add(opcionAgregarProducto);

        jMenuBar1.add(menuProductos);

        menuCotizaciones.setText("Cotizaciones");

        opcionVerCotizaciones.setText("Ver todas");
        menuCotizaciones.add(opcionVerCotizaciones);

        opcionEmitirCotizacion.setText("Emitir");
        menuCotizaciones.add(opcionEmitirCotizacion);

        jMenuBar1.add(menuCotizaciones);

        menuUsuarios.setText("Usuarios");

        opcionVerUsuarios.setText("Ver todos");
        menuUsuarios.add(opcionVerUsuarios);

        opcionAgregarUsuario.setText("Agregar");
        menuUsuarios.add(opcionAgregarUsuario);

        jMenuBar1.add(menuUsuarios);

        menuOperaciones.setText("Operaciones");

        opcionCompra.setText("Compra");
        menuOperaciones.add(opcionCompra);

        opcionVenta.setText("Venta");
        menuOperaciones.add(opcionVenta);

        jMenuBar1.add(menuOperaciones);

        menuReportes.setText("Reportes");

        opcionInventario.setText("Inventario");
        menuReportes.add(opcionInventario);

        opcionComprasRealizadas.setText("Compras realizadas");
        menuReportes.add(opcionComprasRealizadas);

        opcionVentasConsolidadas.setText("Ventas consolidadas");
        menuReportes.add(opcionVentasConsolidadas);

        jMenuBar1.add(menuReportes);

        menuCuenta.setText(usuario.getUsername());

        opcionConfigurarCuenta.setText("Configurar cuenta");
        menuCuenta.add(opcionConfigurarCuenta);
        menuCuenta.add(jSeparator1);

        opcionCerrarSesion.setText("Cerrar sesión");
        menuCuenta.add(opcionCerrarSesion);

        jMenuBar1.add(menuCuenta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 689, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuClientes;
    private javax.swing.JMenu menuCotizaciones;
    private javax.swing.JMenu menuCuenta;
    private javax.swing.JMenu menuOperaciones;
    private javax.swing.JMenu menuProductos;
    private javax.swing.JMenu menuProveedores;
    private javax.swing.JMenu menuReportes;
    private javax.swing.JMenu menuUsuarios;
    private javax.swing.JMenuItem opcionAgregarCliente;
    private javax.swing.JMenuItem opcionAgregarProducto;
    private javax.swing.JMenuItem opcionAgregarProveedor;
    private javax.swing.JMenuItem opcionAgregarUsuario;
    private javax.swing.JMenuItem opcionCerrarSesion;
    private javax.swing.JMenuItem opcionCompra;
    private javax.swing.JMenuItem opcionComprasRealizadas;
    private javax.swing.JMenuItem opcionConfigurarCuenta;
    private javax.swing.JMenuItem opcionEmitirCotizacion;
    private javax.swing.JMenuItem opcionInventario;
    private javax.swing.JMenuItem opcionVenta;
    private javax.swing.JMenuItem opcionVentasConsolidadas;
    private javax.swing.JMenuItem opcionVerClientes;
    private javax.swing.JMenuItem opcionVerCotizaciones;
    private javax.swing.JMenuItem opcionVerProductos;
    private javax.swing.JMenuItem opcionVerProveedores;
    private javax.swing.JMenuItem opcionVerUsuarios;
    // End of variables declaration//GEN-END:variables

    public JMenuItem getOpcionCerrarSesion() {
        return opcionCerrarSesion;
    }
    
    public JMenuItem getOpcionAgregarUsuario() {
        return opcionAgregarUsuario;
    }
    
    private void setListener() {
        opcionAgregarCliente.addMouseListener(controlador);
        opcionAgregarProducto.addMouseListener(controlador);
        opcionAgregarProveedor.addMouseListener(controlador);
        opcionAgregarUsuario.addMouseListener(controlador);
        opcionCerrarSesion.addMouseListener(controlador);
        opcionCompra.addMouseListener(controlador);
        opcionComprasRealizadas.addMouseListener(controlador);
        opcionConfigurarCuenta.addMouseListener(controlador);
        opcionEmitirCotizacion.addMouseListener(controlador);
        opcionInventario.addMouseListener(controlador);
        opcionVenta.addMouseListener(controlador);
        opcionVentasConsolidadas.addMouseListener(controlador);
        opcionVerClientes.addMouseListener(controlador);
        opcionVerCotizaciones.addMouseListener(controlador);
        opcionVerProductos.addMouseListener(controlador);
        opcionVerProveedores.addMouseListener(controlador);
        opcionVerUsuarios.addMouseListener(controlador);
    }

}
