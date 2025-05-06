package es.petclinic.controllers;

import es.petclinic.DAO.ClienteDAO;
import es.petclinic.DAO.IClienteDAO;
import es.petclinic.DAO.IUsuarioDAO;
import es.petclinic.DAO.UsuarioDAO;

import es.petclinic.beans.Usuario;
import es.petclinic.models.Utils;

import java.io.IOException;
import java.util.Date;

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
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    
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
        
        String url = "."; // Redirección por defecto
        String accion = request.getParameter("boton");

        if (accion == null) {
            url = ".";
        } else {
            switch (accion) {
                case "login":
                    // Validar que los campos no estén vacíos
                    if ("v".equals(Utils.comprobarCamposLogin(request.getAttributeNames(), request))) {
                        request.setAttribute("error", "Todos los campos son obligatorios.");
                    } else {
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");

                        IUsuarioDAO usuarioDAO = new UsuarioDAO();
                        IClienteDAO clienteDAO = new ClienteDAO();
                        Usuario usuario = usuarioDAO.obtenerPorEmail(email);

                        if (usuario != null && Utils.verifyPassword(password, usuario.getPassword())) {
                            HttpSession session = request.getSession();
                            session.setAttribute("usuario", usuario);

                            if (usuario.getRol() == null) {
                                request.setAttribute("error", "No tienes permisos para acceder.");
                                url = ".";
                            } else {
                                switch (usuario.getRol()) {
                                    case VETERINARIO:
                                        
                                        // Actualizar fecha de último acceso
                                        usuario.setUltimoAcceso(new Date());
                                        
                                        request.setAttribute("acceso", "Has iniciado sesión como veterinario.");
                                        url = "JSP/Veterinario/veterinario.jsp";
                                        break;
                                    case CLIENTE:
                                        
                                        // Actualizar fecha de último acceso
                                        usuario.setUltimoAcceso(new Date());
                                        
                                        request.setAttribute("acceso", "Has iniciado sesión como cliente.");
                                        url = "JSP/Cliente/cliente.jsp";
                                        break;
                                        
                                    default:
                                        request.setAttribute("error", "No tienes permisos para acceder.");
                                        url = ".";
                                        break;
                                }
                            }
                        } else {
                            request.setAttribute("error", "Correo o contraseña incorrectos.");
                        }
                    }
                    break;

                case "logout":
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.removeAttribute("usuario");
                    }
                    request.setAttribute("logout", "Has cerrado sesión.");
                    url = ".";
                    break;
            }
        }

        request.getRequestDispatcher(url).forward(request, response);
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
