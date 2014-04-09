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
public class CursoBD extends ConexionBD {
    
    public boolean crear_curso(String correo, String tinicio, String tfinal, String tipo) {
        String consulta = "SELECT * FROM `Escuela`.`Curso` WHERE `profesor_correo`='" + correo  +"' AND `curso_inicio`='" + tinicio + "' AND (`curso_estado`='Cursando' OR `curso_tipo`='" + tipo + "');";
        String query = "INSERT INTO `Escuela`.`Curso` (`profesor_correo`, `estudiante_correo`, `curso_inicio`, `curso_final`, `curso_tipo`, "
                + "`curso_estado`, `curso_nota`, `curso_calificacion`) VALUES ('" + correo + "', NULL, '" + tinicio + "', '" + tfinal
                + "','" + tipo + "', 'Espera', NULL, NULL);";
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
            st.executeUpdate(query);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        super.desconectarBD(conexion);
        return !encontrado;
    }
    
    public boolean editar_curso(String id, String correo) {
        String consulta = "SELECT `curso_estado` FROM `Escuela`.`Curso` WHERE `curso_id`='" + id  +"' AND `curso_estado`='Cursando';";
        String query = "UPDATE `Escuela`.`Curso` SET `estudiante_correo`='" + correo + "', `curso_estado`='Cursando'";
        query += " WHERE `curso_id`='" + id + "';";
        
        Connection conexion = super.conectarBD();
        ResultSet resultado = super.consultar(conexion, consulta);
        boolean encontrado = false;

        if (resultado == null) {
            return false;
        }

        try {
            encontrado = resultado.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!encontrado) {

            try {
                Statement st = conexion.createStatement();
                st.execute(query);
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.desconectarBD(conexion);
        return !encontrado;
    }

    public int eliminar_curso(String id) {
        int ex = -1;
        String query = "DELETE FROM `Escuela`.`Curso` WHERE `curso_id`='" + id + "';";
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
