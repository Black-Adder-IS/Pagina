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
public class EstudianteBD extends ConexionBD {
    
    public boolean crear_estudiante(String nombre, String correo, String contrasenia) {
        String consulta = "SELECT * FROM `Escuela`.`Estudiante` WHERE `estudiante_correo`='" + correo  +"';";
        String query = "INSERT INTO `Escuela`.`Estudiante` (`estudiante_correo`, `estudiante_nombre`, `estudiante_contrasena`) VALUES ('" +
                        correo + "', '" + nombre + "', '" + contrasenia + "');";
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
    
    public int editar_estudiante(String correoA, String nombre, String correo, String contrasenia) {
        int ex = -1;
        String query = "UPDATE `Escuela`.`Estudiante` SET";
        if (!nombre.equals("")) {
            query += " `estudiante_nombre`='" + nombre + "',";
        }
        if (!correo.equals("")) {
            query += " `estudiante_correo`='" + correo + "',";
        }
        if (!contrasenia.equals("")) {
            query += " `estudiante_contrasena`='" + contrasenia + "',";
        }
        int temp = query.length()-1;
        if (query.charAt(temp) == ',') {
                query = query.substring(0, temp);
        }
        query += " WHERE `estudiante_correo`='" + correoA + "';";
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
    
    public int eliminar_estudiante(String correo) {
        int ex = -1;
        String query = "DELETE FROM `Escuela`.`Estudiante` WHERE `estudiante_correo`='" + correo + "';";
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
