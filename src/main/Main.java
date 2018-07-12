package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import controllers.ControladorCrearUsuario;
import controllers.ControladorInicioSesion;
import models.DBConnection;
import models.Usuario;
import static views.Dialogs.*;

/**
 * Esta es la clase principal.
 * Aqui empieza la ejecucion del programa.
 */
public class Main {
    
    /**
     * Objeto global utilizado para la conexion con la base de datos,
     * sus atributos van cambiando durante la ejecucion del software y
     * dependiendo de la configuracion.
     */
    public static DBConnection con;
    
    /**
     * Objeto global que identifica al usuario actual que hace uso del
     * sistema con sus respectivos permisos, ya sea de administrador o
     * de usuario.
     */
    public static Usuario usuario;
    
    /**
     * Este metodo inicializa todas las variables y servicios necesarios
     * para la ejecucion del programa.
     */
    public static void iniciar() throws Exception {
        try {
            // Leemos el archivo de parametros
            String [] conexion_data = new String[4];
            FileReader fr = new FileReader("parametros");
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            int number = 0;
            
            while((linea = br.readLine()) != null)
            {
                conexion_data[number] = linea.substring(
                    linea.indexOf("=") + 1, linea.length());
                number++;
                if(number > 3)
                    break;
            }
            
            // Creamos la conexion con los parametros que leimos del archivo
            con = new DBConnection(conexion_data[0], conexion_data[1],
                conexion_data[2], conexion_data[3]);
            // Conectamos
            con.conectar(false);
            // Instanciamos el usuario Global
            usuario= new Usuario();
        } catch (FileNotFoundException e) {
            error("No se encontro el archivo de parametros.");
            throw e;
        } catch (IOException e) {
            error("Ocurrio un error al leer el archivo de parametros.");
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void main(String[] args) {
        try {
            
            iniciar();
            
            if (Usuario.listar().size() == 0) {
                info(
                    null,
                    "No se encontro un usuario administrador registrado.\n" +
                    "Para iniciar el programa se procedera a crear el usuario" +
                    "administrador."
                );
                new ControladorCrearUsuario(true);
            } else
                new ControladorInicioSesion();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
}
