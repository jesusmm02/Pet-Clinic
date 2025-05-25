package es.petclinic.controllers;

import es.petclinic.DAO.CitaDAO;
import es.petclinic.DAO.ICitaDAO;
import es.petclinic.DAO.IMascotaDAO;
import es.petclinic.DAO.IUsuarioDAO;
import es.petclinic.DAO.MascotaDAO;
import es.petclinic.DAO.UsuarioDAO;
import es.petclinic.beans.Cita;
import es.petclinic.beans.Mascota;

import es.petclinic.beans.Usuario;
import es.petclinic.models.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
                    // Validar que los campos de login no estén vacíos
                    if ("v".equals(Utils.comprobarCamposLogin(request.getAttributeNames(), request))) {
                        request.setAttribute("error", "Todos los campos son obligatorios.");
                    } else {
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");

                        // Buscar usuario por email
                        IUsuarioDAO usuarioDAO = new UsuarioDAO();
                        Usuario usuario = usuarioDAO.obtenerPorEmail(email);

                        // Verificar existencia del usuario y que la contraseña coincida
                        if (usuario != null && Utils.verifyPassword(password, usuario.getPassword())) {
                            HttpSession session = request.getSession();
                            session.setAttribute("usuario", usuario);

                            if (usuario.getRol() == null) {
                                request.setAttribute("error", "No tienes permisos para acceder.");
                                url = ".";
                            } else {
                                switch (usuario.getRol()) {
                                    // En el caso de que haya login alguien con Rol VETERINARIO
                                    case VETERINARIO:

                                        // Actualizar fecha de último acceso
                                        usuario.setUltimoAcceso(new Date());

                                        // Obtener citas del día actual
                                        ICitaDAO citaDAO = new CitaDAO();
                                        List<Cita> citasHoy = citaDAO.getCitasByVeterinarioYFecha(usuario.getId(), LocalDate.now());
                                        List<String> resumenCitasHoy = new ArrayList<>();

                                        // Crear un resumen de ellas (citas) para la vista
                                        for (Cita cita : citasHoy) {
                                            LocalTime horaInicio = cita.getCalendario().getHoraInicio();
                                            int duracion = cita.getServicio().getDuracion();
                                            LocalTime horaFin = horaInicio.plusMinutes(duracion);

                                            String nombreMascota = cita.getMascota().getNombre();
                                            String nombreCliente = cita.getMascota().getPropietario().getNombre() + " " + cita.getMascota().getPropietario().getApellidos();

                                            String resumen = horaInicio + " - " + horaFin + " – " + nombreMascota + " (con " + nombreCliente + ")";
                                            resumenCitasHoy.add(resumen);
                                        }

                                        request.setAttribute("resumenCitasHoy", resumenCitasHoy);

                                        // Mostrar, de las mascotas que hay registradas en el programa, tres aleatorias
                                        IMascotaDAO mascotaDAO = new MascotaDAO();
                                        List<Mascota> todasMascotas = mascotaDAO.getAllMascotas();
                                        Collections.shuffle(todasMascotas);
                                        List<Mascota> mascotasRandom = todasMascotas.subList(0, Math.min(3, todasMascotas.size()));
                                        request.setAttribute("mascotasRandom", mascotasRandom);

                                        request.setAttribute("hoy", new Date());

                                        url = "JSP/Veterinario/veterinario.jsp";
                                        break;
                                        
                                    // Login alguien con Rol CLIENTE
                                    case CLIENTE:

                                        // Actualizar fecha de último acceso
                                        usuario.setUltimoAcceso(new Date());

                                        url = "JSP/Cliente/cliente.jsp";
                                        break;
                                    default:
                                        request.setAttribute("error", "No tienes permisos para acceder.");
                                        url = ".";
                                        break;
                                }
                            }
                        } else {
                            // Si no existe el usuario o la contraseña es incorrecta
                            request.setAttribute("error", "Correo o contraseña incorrectos.");
                        }
                    }
                    break;

                case "logout":
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.removeAttribute("usuario"); // Elimina al usuario de la sesión
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
