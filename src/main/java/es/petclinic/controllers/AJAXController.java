package es.petclinic.controllers;

import es.petclinic.DAO.ClienteDAO;
import es.petclinic.DAO.IClienteDAO;
import es.petclinic.DAO.IUsuarioDAO;
import es.petclinic.DAO.UsuarioDAO;

import es.petclinic.beans.Cliente;
import es.petclinic.beans.Usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jesús
 */
@WebServlet(name = "AJAXController", urlPatterns = {"/AJAXController"})
public class AJAXController extends HttpServlet {

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
        request.getRequestDispatcher(".").forward(request, response);
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

        String accion = request.getParameter("accion");
        
        IUsuarioDAO usuarioDAO = new UsuarioDAO();

        if (accion != null) {

            switch (accion) {
                case "comprobarEmail":
                    String email = request.getParameter("email");

                    // Verifica si el email ya existe en la base de datos
                    usuarioDAO = new UsuarioDAO();
                    Usuario usuarioExistente = usuarioDAO.obtenerPorEmail(email);

                    if (usuarioExistente != null) {
                        response.setContentType("text/plain");
                        response.getWriter().write("existe");  // El email ya está registrado
                    } else {
                        response.setContentType("text/plain");
                        response.getWriter().write("no_existe");  // El email no está registrado
                    }
                    break;
                    
                case "comprobarIdentificacion":
                    String numIdentificacion = request.getParameter("numIdentificacion");

                    // Verificar si el número de identificación ya está registrado
                    usuarioDAO = new UsuarioDAO();
                    usuarioExistente = usuarioDAO.obtenerPorNumIdentificacion(numIdentificacion);

                    // Responder con "existe" si el número ya está registrado, "disponible" si no
                    response.setContentType("text/plain");
                    if (usuarioExistente != null) {
                        response.getWriter().write("existe");
                    } else {
                        response.getWriter().write("disponible");
                    }
                    break;
            }

        }
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
