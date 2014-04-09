/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class ProfesorBD extends ConexionBD {

    public boolean crear_profesor(String nombre, String correo, String contrasenia, String certificado, String url) {
        String consulta = "SELECT * FROM `Escuela`.`Profesor` WHERE `profesor_correo`='" + correo  +"';";
        String query = "INSERT INTO `Escuela`.`Profesor` (" +
                "`profesor_correo`, `profesor_nombre`, `profesor_contrasena`, `profesor_url_certificado`, `profesor_url_video`) " +
                "VALUES ('" + correo + "', '" + nombre + "', '" + contrasenia + "', '" + certificado + "', '" + url + "');";

        boolean encontrado = false;
                
        Connection conexion = super.conectarBD();
        ResultSet resultado = super.consultar(conexion, consulta);
            
        if (resultado == null) {
            return false;
        }
        
        try {
            encontrado = resultado.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
                
        if (encontrado) {
            return false;
        }
        
        try {
            Statement st = conexion.createStatement();
            st.execute(query);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        super.desconectarBD(conexion);
        return !encontrado;
    }
    
    public int editar_profesor(String correoA, String nombre, String correo, String contrasenia, String url_video, String url_constancia) {
        int ex = -1;
        String query = "UPDATE `Escuela`.`Profesor` SET";
        if (!nombre.equals("")) {
            query += " `profesor_nombre`='" + nombre + "',";
        }
        if (!correo.equals("")) {
            query += " `profesor_correo`='" + correo + "', `profesor_url_certificado`='" + url_constancia
                    + "', `profesor_url_video`='" + url_video + "',";
        }
        if (!contrasenia.equals("")) {
            query += " `profesor_contrasena`='" + contrasenia + "',";
        }
        int temp = query.length()-1;
        if (query.charAt(temp) == ',') {
                query = query.substring(0, temp);
        }
        query += " WHERE `profesor_correo`='" + correoA + "';";
        try {
            Connection conexion = super.conectarBD();
            Statement st = conexion.createStatement();
            ex = st.executeUpdate(query);
            st.close();
            super.desconectarBD(conexion);
            return ex;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex;
    }
    
    public int eliminar_profesor(String correo) {
        int ex = -1;
        String query = "DELETE FROM `Escuela`.`Profesor` WHERE `profesor_correo`='" + correo + "';";
        Connection conexion = null;
        try {
            conexion = super.conectarBD();
            Statement st = conexion.createStatement();
            ex = st.executeUpdate(query);
            st.close();
            super.desconectarBD(conexion);
            return ex;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return ex;
    }
}
