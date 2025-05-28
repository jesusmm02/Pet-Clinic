package es.petclinic.controllers;

import com.google.gson.Gson;

import es.petclinic.DAO.CitaDAO;
import es.petclinic.DAO.ICitaDAO;
import es.petclinic.DAO.IUsuarioDAO;
import es.petclinic.DAO.UsuarioDAO;

import es.petclinic.beans.Cita;
import es.petclinic.beans.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ICitaDAO citaDAO = new CitaDAO();

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

                    response.setContentType("text/plain");
                    if (usuarioExistente != null) {
                        response.getWriter().write("existe"); // El número ya está registrado
                    } else {
                        response.getWriter().write("disponible"); // El número está disponible
                    }
                    break;

                // Obtener citas filtradas por animal y/o servicio, para pintar en el calendario  
                case "obtenerCitasCalendario":
                    
                    String animalIdStr = request.getParameter("animalId");
                    String servicioIdStr = request.getParameter("servicioId");

                    // Convertir los parámetros a Integer si no están vacíos
                    Integer animalId = (animalIdStr != null && !animalIdStr.isEmpty()) ? Integer.parseInt(animalIdStr) : null;
                    Integer servicioId = (servicioIdStr != null && !servicioIdStr.isEmpty()) ? Integer.parseInt(servicioIdStr) : null;

                    // Obtener las listas en función de los filtros
                    List<Cita> citas;
                    if (animalId != null && servicioId != null) {
                        citas = citaDAO.getCitasByMascotaAndServicio(animalId, servicioId);
                    } else if (animalId != null) {
                        citas = citaDAO.getCitasByMascota(animalId);
                    } else if (servicioId != null) {
                        citas = citaDAO.getCitasByServicio(servicioId);
                    } else {
                        citas = citaDAO.getAllCitas();
                    }
                    
                    // Fecha y hora actual para comparar
                    LocalDateTime ahora = LocalDateTime.now();

                    // Formatear las citas en objetos compatibles con calendarios JS
                    List<Map<String, Object>> eventos = new ArrayList<>();
                    for (Cita cita : citas) {
                        LocalDate fechaCita = cita.getCalendario().getFecha();
                        LocalTime horaInicio = cita.getCalendario().getHoraInicio();

                        LocalDateTime fechaHoraCita = LocalDateTime.of(fechaCita, horaInicio);

                        // Filtrar solo citas futuras o actuales
                        if (!fechaHoraCita.isBefore(ahora)) {
                            Map<String, Object> evento = new HashMap<>();
                            evento.put("id", cita.getId());
                            evento.put("title", cita.getServicio().getNombre());
                            evento.put("start", cita.getCalendario().getFecha() + "T" + cita.getCalendario().getHoraInicio());
                            eventos.add(evento);
                        }
                    }

                    response.setContentType("application/json");
                    response.getWriter().write(new Gson().toJson(eventos));
                    break;

                // Obtener detalles completos de una cita concreta
                case "obtenerDetalleCita":
                    int idCita = Integer.parseInt(request.getParameter("idCita"));
                    Cita cita = citaDAO.getCitaById(idCita); // Obtener los detalles de la cita

                    // Armar los detalles de la cita
                    Map<String, Object> detalles = new HashMap<>();
                    detalles.put("mascota", cita.getMascota().getNombre());
                    detalles.put("servicio", cita.getServicio().getNombre());
                    detalles.put("veterinario", cita.getVeterinario().getNombre() + " " + cita.getVeterinario().getApellidos());
                    detalles.put("fecha", cita.getCalendario().getFecha().toString());
                    
                    // Calcular la hora de fin sumando duración a la hora de inicio
                    LocalTime horaInicio = cita.getCalendario().getHoraInicio();
                    int duracion = cita.getServicio().getDuracion(); // duración total en minutos
                    LocalTime horaFinCita = horaInicio.plusMinutes(duracion);

                    detalles.put("horaInicio", horaInicio.toString());
                    detalles.put("horaFin", horaFinCita.toString());

                    response.setContentType("application/json");
                    response.getWriter().write(new Gson().toJson(detalles));
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
