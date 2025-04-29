package es.petclinic.controllers;

import es.petclinic.DAO.ClienteDAO;
import es.petclinic.DAO.IClienteDAO;
import es.petclinic.DAO.IUsuarioDAO;
import es.petclinic.DAO.UsuarioDAO;
import es.petclinic.beans.Cliente;

import es.petclinic.beans.Usuario;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        
        HttpSession session = request.getSession();
        IUsuarioDAO usuarioDAO = new UsuarioDAO();

        if (accion != null) {

            switch (accion) {
                case "comprobarEmail":
                    String email = request.getParameter("email");

                    // Obtener el usuario en sesión
                    Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuario");

                    // Verificar si el email ya existe
                    Usuario usuarioExistente = usuarioDAO.obtenerPorEmail(email);

                    // Verificar si el email es del usuario en sesión
                    boolean esSuPropioEmail = usuarioExistente != null
                            && usuarioEnSesion != null
                            && usuarioExistente.getId().equals(usuarioEnSesion.getId());

                    response.setContentType("text/plain");
                    response.getWriter().write((usuarioExistente != null && !esSuPropioEmail) ? "existe" : "no_existe");
                    break;

                case "generarDni":
                    String numeros = request.getParameter("numeros");
                    String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

                    try {
                        int posicion = Integer.parseInt(numeros) % 23;
                        String letra = String.valueOf(letras.charAt(posicion));

                        // Responder con la letra calculada
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("{\"data\": \"" + letra + "\"}");
                    } catch (NumberFormatException e) {
                        Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, e);

                        // Enviar error en la respuesta
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("{\"error\": \"Formato de DNI inválido\"}");
                    }
                    break;
                    
                case "comprobarDNI":
                    String dni = request.getParameter("dni");

                    // Verificar si el DNI ya está registrado
                    IClienteDAO clienteDAO = new ClienteDAO();
                    Cliente clienteExistente = clienteDAO.getByDNI(dni);

                    // Si el DNI ya está registrado, respondemos con "existe"
                    if (clienteExistente != null) {
                        response.setContentType("text/plain");
                        response.getWriter().write("existe");
                    } else {
                        // Si el DNI no está registrado, respondemos con "disponible"
                        response.setContentType("text/plain");
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
