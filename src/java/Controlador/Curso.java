/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.CursoBD;
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
public class Curso extends HttpServlet {

    private final String VALIACION_TIEMPO = "-1";
    private final String CONFIRMACION_CREACION = "0";
    private final String RECHAZO_CREACION = "1";
    private final String CONFIRMACION = "2";
    private final String RECHAZO = "3";
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
        response.setContentType("text/html;charset=UTF-8");
        String operacion = request.getParameter("operacion");
        PrintWriter out = response.getWriter();
        
        if (operacion == null) {
            
        } else if (operacion.equals("crear_Curso")) {
            String correo = request.getParameter("correo_Profesor");
            String tinicio = request.getParameter("tiempo_Inicio");
            String tfinal = request.getParameter("tiempo_Final");
            String tipo = request.getParameter("tipo_Curso");
            
            if (tinicio.compareTo(tfinal) >= 0) {
                out.println(VALIACION_TIEMPO);
            } else {
            
                if (new CursoBD().crear_curso(correo, tinicio, tfinal, tipo)) {
                    out.println(CONFIRMACION_CREACION);
                } else {
                    out.println(RECHAZO_CREACION);
                }
            }

        /*} else if (operacion.equals("aceptar_Estudiante")) {
            String correo_Estudiante = request.getParameter("correo_Estudiante");
            String id = request.getParameter("id_Curso");
            if (new CursoBD().editar_curso(id, correo_Estudiante)) {
                out.println(CONFIRMACION);
            } else {
                out.println(RECHAZO);
            }*/
        
        } else if (operacion.equals("eliminar_Curso")) {
            String correo = request.getParameter("correo_Profesor");
            String id = request.getParameter("id_Curso");
            
            if (new CursoBD().eliminar_curso(id) == 1) {
                out.println(CONFIRMACION_BORRADO);
            } else {
                out.println(RECHAZO_BORRADO);
            }
        }
        //}
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
