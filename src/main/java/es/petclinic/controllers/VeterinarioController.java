package es.petclinic.controllers;

import es.petclinic.DAO.CitaDAO;
import es.petclinic.DAO.ClienteDAO;
import es.petclinic.DAO.HistorialMedicoDAO;
import es.petclinic.DAO.ICitaDAO;
import es.petclinic.DAO.IClienteDAO;
import es.petclinic.DAO.IHistorialMedicoDAO;
import es.petclinic.DAO.IMascotaDAO;
import es.petclinic.DAO.IServicioDAO;
import es.petclinic.DAO.MascotaDAO;
import es.petclinic.DAO.ServicioDAO;

import es.petclinic.beans.Cita;
import es.petclinic.beans.Cliente;
import es.petclinic.beans.HistorialMedico;
import es.petclinic.beans.Mascota;
import es.petclinic.beans.Servicio;
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
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Jesús
 */
@WebServlet(name = "VeterinarioController", urlPatterns = {"/VeterinarioController"})
public class VeterinarioController extends HttpServlet {

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

        String url = "JSP/Veterinario/veterinario.jsp";
        String accion = request.getParameter("accion");

        switch (accion) {
            case "homeVeterinario":
                
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("usuario") != null) {
                    Usuario usuario = (Usuario) session.getAttribute("usuario");

                    // Obtener citas del día para ese veterinario
                    ICitaDAO citaDAO = new CitaDAO();
                    List<Cita> citasHoy = citaDAO.getCitasByVeterinarioYFecha(usuario.getId(), LocalDate.now());
                    List<String> resumenCitasHoy = new ArrayList<>();

                    // Crear resumen de cada cita
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
                }
                
                // // Mostrar, de las mascotas que hay registradas en el programa, tres aleatorias
                IMascotaDAO mascotaDAO = new MascotaDAO();
                List<Mascota> todasMascotas = mascotaDAO.getAllMascotas();
                Collections.shuffle(todasMascotas);
                List<Mascota> mascotasRandom = todasMascotas.subList(0, Math.min(3, todasMascotas.size()));
                request.setAttribute("mascotasRandom", mascotasRandom);
                
                request.setAttribute("hoy", new Date());

                url = "JSP/Veterinario/veterinario.jsp";

                break;
            case "verClientes":
                HttpSession sessionMascotas = request.getSession(false);
                if (sessionMascotas != null && sessionMascotas.getAttribute("usuario") != null) {

                    IClienteDAO clienteDAO = new ClienteDAO();

                    // Obtenemos todos los clientes con sus mascotas
                    List<Cliente> listaClientes = clienteDAO.obtenerClientesConMascotas();

                    // Pasamos la lista a la vista
                    request.setAttribute("listaClientes", listaClientes);
                    url = "JSP/Veterinario/clientes_mascotas.jsp";
                } else {
                    url = "JSP/Veterinario/veterinario.jsp";
                }
                break;
            case "historialMedico":
                IHistorialMedicoDAO historialDAO = new HistorialMedicoDAO();
                mascotaDAO = new MascotaDAO();

                List<HistorialMedico> listaHistoriales = historialDAO.getAllHistoriales();
                List<Mascota> listaMascotas = mascotaDAO.getAllMascotas();

                // Obtener los clientes con al menos una mascota
                IClienteDAO clienteDAO = new ClienteDAO();
                List<Cliente> listaClientes = clienteDAO.obtenerClientesConMascotas(); // Lista de clientes con mascotas registradas

                // Obtener las especies y razas disponibles
                List<String> listaEspecies = mascotaDAO.obtenerEspecies();
                List<String> listaRazas = mascotaDAO.obtenerRazas();

                request.setAttribute("listaHistoriales", listaHistoriales);
                request.setAttribute("listaMascotas", listaMascotas);
                request.setAttribute("listaClientes", listaClientes);
                request.setAttribute("listaEspecies", listaEspecies);
                request.setAttribute("listaRazas", listaRazas);

                url = "JSP/Veterinario/historialMedico.jsp";
                break;
            case "filtrarHistoriales":
                // Obtener los parámetros de los filtros
                String idDueño = request.getParameter("dueño");
                String especie = request.getParameter("especie");
                String raza = request.getParameter("raza");

                // Filtrar los historiales médicos
                historialDAO = new HistorialMedicoDAO();

                // Aplicar los filtros correspondientes
                if (idDueño != null && !idDueño.isEmpty() && especie != null && !especie.isEmpty() && raza != null && !raza.isEmpty()) {
                    // Filtros combinados: Dueño, Especie y Raza
                    listaHistoriales = historialDAO.getHistorialesByDueñoAndEspecieAndRaza(Integer.parseInt(idDueño), especie, raza);
                } else if (idDueño != null && !idDueño.isEmpty() && especie != null && !especie.isEmpty()) {
                    // Filtro por Dueño y Especie
                    listaHistoriales = historialDAO.getHistorialesByDueñoAndEspecie(Integer.parseInt(idDueño), especie);
                } else if (idDueño != null && !idDueño.isEmpty() && raza != null && !raza.isEmpty()) {
                    // Filtro por Dueño y Raza
                    listaHistoriales = historialDAO.getHistorialesByDueñoAndRaza(Integer.parseInt(idDueño), raza);
                } else if (especie != null && !especie.isEmpty() && raza != null && !raza.isEmpty()) {
                    // Filtro por Especie y Raza
                    listaHistoriales = historialDAO.getHistorialesByEspecieAndRaza(especie, raza);
                } else if (idDueño != null && !idDueño.isEmpty()) {
                    // Filtro por Dueño
                    listaHistoriales = historialDAO.getHistorialesByDueño(Integer.parseInt(idDueño));
                } else if (especie != null && !especie.isEmpty()) {
                    // Filtro por Especie
                    listaHistoriales = historialDAO.getHistorialesByEspecie(especie);
                } else if (raza != null && !raza.isEmpty()) {
                    // Filtro por Raza
                    listaHistoriales = historialDAO.getHistorialesByRaza(raza);
                } else {
                    // Si no hay filtros, obtener todos los historiales
                    listaHistoriales = historialDAO.getAllHistoriales();
                }

                // Obtener la lista de todas las mascotas
                mascotaDAO = new MascotaDAO();
                listaMascotas = mascotaDAO.getAllMascotas();

                // Obtener la lista de clientes con al menos una mascota
                clienteDAO = new ClienteDAO();
                listaClientes = clienteDAO.obtenerClientesConMascotas();

                // Obtener listas de especies y razas existentes
                listaEspecies = mascotaDAO.obtenerEspecies();
                listaRazas = mascotaDAO.obtenerRazas();

                // Pasar las listas como atributos para la vista
                request.setAttribute("listaHistoriales", listaHistoriales);
                request.setAttribute("listaMascotas", listaMascotas);
                request.setAttribute("listaClientes", listaClientes);
                request.setAttribute("listaEspecies", listaEspecies);
                request.setAttribute("listaRazas", listaRazas);

                url = "JSP/Veterinario/historialMedico.jsp"; // Redirigir a la vista de historial médico
                break;

            case "guardarHistorial":
                try {
                    String error = Utils.comprobarCamposHistorial(request.getParameterNames(), request);

                    // Si hay un error (campo vacío), redirigimos y mostramos el mensaje
                    if ("v".equals(error)) {
                        request.setAttribute("errorHistorial", "Todos los campos son obligatorios.");

                        // Recargar la lista de mascotas para el modal
                        historialDAO = new HistorialMedicoDAO();
                        mascotaDAO = new MascotaDAO();
                        listaHistoriales = historialDAO.getAllHistoriales();
                        listaMascotas = mascotaDAO.getAllMascotas();

                        // Validar si hay mascotas
                        if (listaMascotas == null || listaMascotas.isEmpty()) {
                            request.setAttribute("errorHistorial", "No hay mascotas registradas en el sistema.");
                        }

                        // Recoger los valores de los filtros seleccionados
                        idDueño = request.getParameter("dueño");
                        especie = request.getParameter("especie");
                        raza = request.getParameter("raza");

                        // Obtener la lista de clientes con mascotas
                        clienteDAO = new ClienteDAO();
                        listaClientes = clienteDAO.obtenerClientesConMascotas();

                        // Obtener las especies y razas disponibles
                        listaEspecies = mascotaDAO.obtenerEspecies();
                        listaRazas = mascotaDAO.obtenerRazas();

                        request.setAttribute("listaHistoriales", listaHistoriales);
                        request.setAttribute("listaMascotas", listaMascotas);
                        request.setAttribute("listaClientes", listaClientes);
                        request.setAttribute("listaEspecies", listaEspecies);
                        request.setAttribute("listaRazas", listaRazas);

                        // Pasar los filtros actuales
                        request.setAttribute("filtroDueño", idDueño);
                        request.setAttribute("filtroEspecie", especie);
                        request.setAttribute("filtroRaza", raza);

                        // Redirigir a la vista
                        request.getRequestDispatcher("JSP/Veterinario/historialMedico.jsp").forward(request, response);
                        return;
                    }

                    HistorialMedico historial = new HistorialMedico();

                    // Poblar el bean con los datos del formulario
                    BeanUtils.populate(historial, request.getParameterMap());

                    // Obtener la mascota seleccionada
                    int idMascota = Integer.parseInt(request.getParameter("idMascota"));
                    mascotaDAO = new MascotaDAO();
                    Mascota mascota = mascotaDAO.getById(idMascota);

                    // Asignar la mascota al historial
                    historial.setMascota(mascota);

                    // Asignar al historial la fecha actual
                    historial.setFecha(new java.util.Date());

                    // Guardar en la base de datos
                    historialDAO = new HistorialMedicoDAO();
                    historialDAO.guardarHistorial(historial);

                    // Volver a cargar las listas para que se vean al recargar la página
                    listaHistoriales = historialDAO.getAllHistoriales();
                    listaMascotas = mascotaDAO.getAllMascotas();

                    // Obtener los filtros actuales
                    idDueño = request.getParameter("dueño");
                    especie = request.getParameter("especie");
                    raza = request.getParameter("raza");

                    // Filtrar los historiales según los filtros seleccionados
                    if (idDueño != null && !idDueño.isEmpty() && especie != null && !especie.isEmpty() && raza != null && !raza.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByDueñoAndEspecieAndRaza(Integer.parseInt(idDueño), especie, raza);
                    } else if (idDueño != null && !idDueño.isEmpty() && especie != null && !especie.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByDueñoAndEspecie(Integer.parseInt(idDueño), especie);
                    } else if (idDueño != null && !idDueño.isEmpty() && raza != null && !raza.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByDueñoAndRaza(Integer.parseInt(idDueño), raza);
                    } else if (especie != null && !especie.isEmpty() && raza != null && !raza.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByEspecieAndRaza(especie, raza);
                    } else if (idDueño != null && !idDueño.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByDueño(Integer.parseInt(idDueño));
                    } else if (especie != null && !especie.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByEspecie(especie);
                    } else if (raza != null && !raza.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByRaza(raza);
                    } else {
                        listaHistoriales = historialDAO.getAllHistoriales();
                    }

                    // Obtener la lista de clientes, especies y razas
                    clienteDAO = new ClienteDAO();
                    listaClientes = clienteDAO.obtenerClientesConMascotas();
                    listaEspecies = mascotaDAO.obtenerEspecies();
                    listaRazas = mascotaDAO.obtenerRazas();

                    // Asignar al request para la vista
                    request.setAttribute("listaHistoriales", listaHistoriales);
                    request.setAttribute("listaMascotas", listaMascotas);
                    request.setAttribute("listaClientes", listaClientes);
                    request.setAttribute("listaEspecies", listaEspecies);
                    request.setAttribute("listaRazas", listaRazas);

                    // Pasar los valores de los filtros actuales
                    request.setAttribute("filtroDueño", idDueño);
                    request.setAttribute("filtroEspecie", especie);
                    request.setAttribute("filtroRaza", raza);

                    request.setAttribute("creacionHistorial", "Se ha añadido el historial médico correctamente");

                    // Redirigir a página de historiales
                    url = "JSP/Veterinario/historialMedico.jsp";

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "eliminarHistorial":
                try {
                    int idHistorial = Integer.parseInt(request.getParameter("idHistorial"));
                    historialDAO = new HistorialMedicoDAO();
                    historialDAO.eliminarHistorial(idHistorial);

                    
                    // PASAR FILTROS PARA QUE NO SE PIERDAN AL RECARGAR LA PÁGINA UNA VEZ ELIMINEMOS UN HISTORIAL 
                    // Obtener los filtros actuales
                    idDueño = request.getParameter("dueño");
                    especie = request.getParameter("especie");
                    raza = request.getParameter("raza");

                    // Aplicar los filtros correspondientes
                    if (idDueño != null && !idDueño.isEmpty() && especie != null && !especie.isEmpty() && raza != null && !raza.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByDueñoAndEspecieAndRaza(Integer.parseInt(idDueño), especie, raza);
                    } else if (idDueño != null && !idDueño.isEmpty() && especie != null && !especie.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByDueñoAndEspecie(Integer.parseInt(idDueño), especie);
                    } else if (idDueño != null && !idDueño.isEmpty() && raza != null && !raza.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByDueñoAndRaza(Integer.parseInt(idDueño), raza);
                    } else if (especie != null && !especie.isEmpty() && raza != null && !raza.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByEspecieAndRaza(especie, raza);
                    } else if (idDueño != null && !idDueño.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByDueño(Integer.parseInt(idDueño));
                    } else if (especie != null && !especie.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByEspecie(especie);
                    } else if (raza != null && !raza.isEmpty()) {
                        listaHistoriales = historialDAO.getHistorialesByRaza(raza);
                    } else {
                        listaHistoriales = historialDAO.getAllHistoriales();
                    }

                    // Obtener la lista de clientes, especies y razas
                    mascotaDAO = new MascotaDAO();
                    listaMascotas = mascotaDAO.getAllMascotas();

                    clienteDAO = new ClienteDAO();
                    listaClientes = clienteDAO.obtenerClientesConMascotas();

                    listaEspecies = mascotaDAO.obtenerEspecies();
                    listaRazas = mascotaDAO.obtenerRazas();

                    // Pasar los resultados a la vista
                    request.setAttribute("listaHistoriales", listaHistoriales);
                    request.setAttribute("listaClientes", listaClientes);
                    request.setAttribute("listaEspecies", listaEspecies);
                    request.setAttribute("listaRazas", listaRazas);
                    request.setAttribute("listaMascotas", listaMascotas);

                    // Pasar los valores de los filtros actuales
                    request.setAttribute("filtroDueño", idDueño);
                    request.setAttribute("filtroEspecie", especie);
                    request.setAttribute("filtroRaza", raza);

                    request.setAttribute("eliminacionHistorial", "Se ha eliminado el historial médico correctamente");

                    // Redirigir a la vista con los historiales actualizados
                    url = "JSP/Veterinario/historialMedico.jsp";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "citas":
                
                mascotaDAO = new MascotaDAO();
                listaMascotas = mascotaDAO.getAllMascotas();
                
                IServicioDAO servicioDAO = new ServicioDAO();
                List<Servicio> listaServicios = servicioDAO.obtenerServicios();

                // Pasar mascota y servicios a la vista de las citas del veterinario
                request.setAttribute("listaMascotas", listaMascotas);
                request.setAttribute("listaServicios", listaServicios);
                
                url = "JSP/Veterinario/citasProgramadas.jsp";
                break;
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
