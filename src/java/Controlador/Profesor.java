/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.CursoBD;
import Modelo.ProfesorBD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jesus
 */
public class Profesor extends HttpServlet {
    
    private final String CONFIRMACION_REGISTRO = "0";
    private final String RECHAZO_REGISTRO = "1";
    private final String CONFIRMACION_EDICION = "2";
    private final String RECHAZO_EDICION = "3";
    private final String CONFIRMACION_BORRADO = "4";
    private final String RECHAZO_BORRADO = "5";
    private final String CONFIRMACION = "6";
    private final String RECHAZO = "7";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operacion = request.getParameter("operacion");
        PrintWriter out = response.getWriter();

        
        if (operacion == null) {
            
        } else if (operacion.equals("registrar_Profesor")) {
            String user = request.getParameter("nombre_Profesor");
            String correo = request.getParameter("correo_Profesor");
            String contrasenia = request.getParameter("contrasenia_Profesor");
            String url_constancia = "data/constancias/" + correo + ".pdf";
            String url_video = "data/videos/" + correo + ".mp4";
            
            if(new ProfesorBD().crear_profesor(user, correo, contrasenia, url_constancia, url_video)) {
                out.println(CONFIRMACION_REGISTRO);
            } else {
                out.print(RECHAZO_REGISTRO);
            }
            
        } else if (operacion.equals("editar_Profesor")) {
            String correoA = request.getParameter("correo_Profesor");
            String nombre = request.getParameter("nuevo_nombre_Profesor");
            String correo = request.getParameter("nuevo_correo_Profesor");
            String contrasenia = request.getParameter("nuevo_contrasenia_Profesor");
            String url_constancia = "data/constancias/" + correo + ".pdf";
            String url_video = "data/videos/" + correo + ".mp4";
            
            if (new ProfesorBD().editar_profesor(correoA, nombre, correo, contrasenia, url_video, url_constancia) == 1) {
                out.println(CONFIRMACION_EDICION);
            } else {
                out.println(RECHAZO_EDICION);
            }
            
        } else if (operacion.equals("eliminar_Profesor")) {
            String correo = request.getParameter("correo_Profesor");
            if (new ProfesorBD().eliminar_profesor(correo) == 1) {
                out.println(CONFIRMACION_BORRADO);
            } else {
                out.println(RECHAZO_BORRADO);
            }
        } else if (operacion.equals("aceptar_Estudiante")) {
            String correo_Estudiante = request.getParameter("correo_Estudiante");
            String id = request.getParameter("id_Curso");
            if (new CursoBD().editar_curso(id, correo_Estudiante)) {
                out.println(CONFIRMACION);
            } else {
                out.println(RECHAZO);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
