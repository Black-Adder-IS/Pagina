/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.EstudianteBD;
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
public class Estudiante extends HttpServlet {
    
    private final String CONFIRMACION_REGISTRO = "0";
    private final String RECHAZO_REGISTRO = "1";
    private final String CONFIRMACION_EDICION = "2";
    private final String RECHAZO_EDICION = "3";
    private final String CONFIRMACION_BORRADO = "4";
    private final String RECHAZO_BORRADO = "5";
    
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
            
        } else if (operacion.equals("registrar_Estudiante")) {
            String nombre = request.getParameter("nombre_Estudiante");
            String correo = request.getParameter("correo_Estudiante");
            String contrasenia = request.getParameter("contrasenia_Estudiante");

            if (new EstudianteBD().crear_estudiante(nombre, correo, contrasenia)) {
                out.println(CONFIRMACION_REGISTRO);
            } else {
               out.println(RECHAZO_REGISTRO);
            }

        } else if (operacion.equals("editar_Estudiante")) {
            String correoA = request.getParameter("correo_Estudiante");
            String nombre = request.getParameter("nuevo_nombre_Estudiante");
            String correo = request.getParameter("nuevo_correo_Estudiante");
            String contrasenia = request.getParameter("nuevo_contrasenia_Estudiante");
            
            if (new EstudianteBD().editar_estudiante(correoA, nombre, correo, contrasenia) == 1) {
                out.println(CONFIRMACION_EDICION);
            } else {
                out.println(RECHAZO_EDICION);
            }
        
        } else if (operacion.equals("eliminar_Estudiante")) {
            String correo = request.getParameter("correo_Estudiante");
            if (new EstudianteBD().eliminar_estudiante(correo) == 1) {
                out.println(CONFIRMACION_BORRADO);
            } else {
                out.println(RECHAZO_BORRADO);
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
