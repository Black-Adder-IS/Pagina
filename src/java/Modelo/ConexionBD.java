/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class ConexionBD {
    
    private final String SERVIDOR = "jdbc:mysql://127.0.0.1:3306/Escuela";
    private final String USUARIO = "root";
    private final String CONTRASENIA = "mysql";
    
    public Connection conectarBD(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(SERVIDOR, USUARIO, CONTRASENIA);
            return conexion;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void desconectarBD(Connection conexion) {
        try {
            if( conexion != null && !conexion.isClosed() ) {
                conexion.close();
            }
        } catch( SQLException e ) {
            e.printStackTrace();
        }
    }
    
    public ResultSet consultar(Connection conexion, String query) {
        ResultSet resultado = null;
        try {
            Statement st = conexion.createStatement();
            resultado = st.executeQuery(query);
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        return resultado;
    }
}
