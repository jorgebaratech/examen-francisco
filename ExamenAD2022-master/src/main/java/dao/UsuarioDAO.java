package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

public class UsuarioDAO {
    
    private Connection connection;
    
    /* Completar consultas */
    static final String INSERT_QUERY = """
                UPDATE `usuario` SET 
                    `nombre` = ?,
                    `apellidos` = ?,
                    `dni` = ? 
               """;
    
    static final String LIST_QUERY=" SELECT * FROM usuario";
    static final String GET_BY_DNI="select * from usuario where dni=?";
   
      private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/examen?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
 
    
    
    
    
    private static Connection conexion;
    public void connect(){
        try {
            
            /* completar código de conexión */
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(UsuarioDAO.class.getName()).info("Conexión establecida con exito");
            
            
//            System.out.println("Base de datos no conectada");
        }catch(Exception ex){
            System.out.println("Error al conectar a la base de datos");
            System.out.println("ex");
        }     
    }
    
    public void close(){
        try {
            if (conexion != null) {
    try {
        conexion.close();
    } catch (SQLException e) { /* ignored */}
}
//            connection.close();
        } catch (Exception ex) {
            System.out.println("Error al cerrar la base de datos");     
        }
    }
    
    public void save(Usuario user){
        
        /**
         * Completar código para guardar un usuario 
         * Este método no debe generar el id del usuario, ya que la base
         * de datos es la que lo genera.
         * Una vez obtenido el id del usuario tras la consulta sql,
         * hay que modificarlo en el objeto que se pasa como parámetro 
         * de forma que pase a tener el id correcto.
//         */
        try(var pst = conexion.prepareStatement(INSERT_QUERY)){
           
            pst.setString(1, user.getDni());
            pst.setString(2, user.getApellidos());
            pst.setString(3, user.getNombre());
           

            if( pst.executeUpdate()== 0){
                Logger.getLogger(UsuarioDAO.class.getName()).severe("usuario no existe.");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

//        System.out.println("Método save no completado");

    }

    public ArrayList<Usuario> list(){

        var salida = new ArrayList<Usuario>(10);
        
        /* Completar código para devolver un arraylist con todos los usuarios */
          try( var pst = conexion.prepareStatement(LIST_QUERY)){
            ResultSet resultado = pst.executeQuery();
              while(resultado.next()){
                    var usuario=new Usuario();
                usuario.setDni(resultado.getString("dni"));
                usuario.setId(resultado.getLong("id"));
                usuario.setApellidos(resultado.getString("apellidos"));
                usuario.setNombre(resultado.getString("nombre"));
              salida.add(usuario);
              }
               
               
          } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println("Método list no completado");
        
        return salida;
    }    
    
    public Usuario getByDNI(String dni){
        
        Usuario salida = new Usuario();
        
        /**
         * Completar código para devolver el usuario que tenga ese dni.
         * Si no existe, se debe devolver null
         */
           try(var pst = conexion.prepareStatement(GET_BY_DNI)){
                 pst.setString(1, dni);
            
            ResultSet resultado = pst.executeQuery();
            if(resultado.next()){
                var usuario=new Usuario();
                 usuario.setDni(resultado.getString("dni"));
                usuario.setId(resultado.getLong("id"));
                usuario.setApellidos(resultado.getString("apellidos"));
                usuario.setNombre(resultado.getString("nombre"));
                return usuario;
            }else{
                return null;
            }
            
           } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        System.out.println("Método getByDNI no completado");

        return salida;
    }    
}
