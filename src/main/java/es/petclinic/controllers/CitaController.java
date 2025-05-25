package es.petclinic.controllers;

import es.petclinic.DAO.CalendarioDAO;
import es.petclinic.DAO.CitaDAO;
import es.petclinic.DAO.ICalendarioDAO;
import es.petclinic.DAO.ICitaDAO;
import es.petclinic.DAO.IMascotaDAO;
import es.petclinic.DAO.IServicioDAO;
import es.petclinic.DAO.IUsuarioDAO;
import es.petclinic.DAO.MascotaDAO;
import es.petclinic.DAO.ServicioDAO;
import es.petclinic.DAO.UsuarioDAO;

import es.petclinic.beans.Calendario;
import es.petclinic.beans.Cita;
import es.petclinic.beans.Cliente;
import es.petclinic.beans.Mascota;
import es.petclinic.beans.Servicio;
import es.petclinic.beans.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jesús
 */
@WebServlet(name = "CitaController", urlPatterns = {"/CitaController"})
public class CitaController extends HttpServlet {

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

        String accion = request.getParameter("accion");

        switch (accion) {
            
            // Obtener los bloques horarios disponibles para una fecha y servicio seleccionados
            case "obtenerHorarios":
                
                String fechaStr = request.getParameter("fechaSeleccionada");
                String idServicioStr = request.getParameter("idServicio");

                if (fechaStr != null && idServicioStr != null) {
                    LocalDate fecha = LocalDate.parse(fechaStr);
                    int idServicio = Integer.parseInt(idServicioStr);

                    // Obtener horarios que estén libres y cumplan con la duración del servicio
                    ICalendarioDAO calendarioDAO = new CalendarioDAO();
                    List<Calendario> horariosDisponibles = calendarioDAO.getHorariosDisponiblesPorFechaYServicio(fecha, idServicio);

                    // Crear un array JSON con los horarios disponibles
                    JSONArray horariosArray = new JSONArray();
                    for (Calendario h : horariosDisponibles) {
                        JSONObject obj = new JSONObject();
                        obj.put("id", h.getId());
                        obj.put("horaInicio", h.getHoraInicio().toString());
                        horariosArray.put(obj);
                    }

                    response.setContentType("application/json");
                    response.getWriter().write(horariosArray.toString());
                }
                break;

            // Cargar la vista con la lista de citas del cliente autenticado
            case "misCitas":
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("usuario") != null) {
                    Usuario usuario = (Usuario) session.getAttribute("usuario");
                    ICitaDAO citaDAO = new CitaDAO();
                    List<Cita> listaCitas = citaDAO.getCitasByCliente(usuario.getId());
                    request.setAttribute("listaCitas", listaCitas);
                    request.getRequestDispatcher("JSP/Cliente/misCitas.jsp").forward(request, response);
                } else {
                    // Si no hay sesión, redirigir a inicio
                    request.getRequestDispatcher(".").forward(request, response);
                }
                break;
        }

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

        String url = "JSP/Cliente/solicitarCita.jsp";
        String accion = request.getParameter("accion");

        ICitaDAO citaDAO = new CitaDAO();

        switch (accion) {
            
            // Cargar datos necesarios para mostrar el formulario de solicitud de cita
            case "solicitarCita":
                try {
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("usuario") != null) {
                    Cliente cliente = (Cliente) session.getAttribute("usuario");

                    // Obtener las mascotas del cliente
                    IMascotaDAO mascotaDAO = new MascotaDAO();
                    List<Mascota> listaMascotas = mascotaDAO.getMascotasByIdCliente(cliente.getId());

                    // Obtener la lista de servicios
                    IServicioDAO servicioDAO = new ServicioDAO();
                    List<Servicio> listaServicios = servicioDAO.obtenerServicios();

                    // Si hay una fecha seleccionada, buscar los horarios para esa fecha
                    String fechaSeleccionada = request.getParameter("fechaSeleccionada");
                    ICalendarioDAO calendarioDAO = new CalendarioDAO();
                    List<Calendario> listaHorarios;

                    if (fechaSeleccionada != null && !fechaSeleccionada.isEmpty()) {
                        LocalDate fecha = LocalDate.parse(fechaSeleccionada);
                        listaHorarios = calendarioDAO.getHorariosByFecha(fecha);
                    } else {
                        listaHorarios = new ArrayList<>(); // Lista vacía si no hay fecha seleccionada
                    }

                    // Pasar los datos a la vista
                    request.setAttribute("listaMascotas", listaMascotas);
                    request.setAttribute("listaServicios", listaServicios);
                    request.setAttribute("listaHorarios", listaHorarios);

                    url = "JSP/Cliente/solicitarCita.jsp";
                } else {
                    url = ".";
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al cargar los datos para la solicitud de cita.");
                url = "JSP/Cliente/solicitarCita.jsp";
            }
            break;

            // Ver todas las citas del cliente, ya sean las ya hechas o las futuras
            case "verCitas":
                citaDAO = new CitaDAO();
                HttpSession session = request.getSession(false);

                if (session != null && session.getAttribute("usuario") != null) {
                    Usuario usuario = (Usuario) session.getAttribute("usuario");
                    int idCliente = usuario.getId();

                    // Obtener todas las citas del cliente
                    List<Cita> listaCitas = citaDAO.getCitasByCliente(usuario.getId());

                    // Crear listas separadas para clasificar las citas
                    List<Cita> citasFuturas = new ArrayList<>();
                    List<Cita> citasPasadas = new ArrayList<>();

                    // Fecha y hora actual para clasificar las citas
                    LocalDate hoy = LocalDate.now();
                    LocalTime ahora = LocalTime.now();

                    // Clasificación: si es posterior a hoy o misma fecha pero más tarde, es futura
                    for (Cita cita : listaCitas) {
                        LocalDate fechaCita = cita.getCalendario().getFecha();
                        LocalTime horaInicio = cita.getCalendario().getHoraInicio();

                        if (fechaCita.isAfter(hoy) || (fechaCita.isEqual(hoy) && horaInicio.isAfter(ahora))) {
                            citasFuturas.add(cita);
                        } else {
                            citasPasadas.add(cita);
                        }
                    }

                    // Mapas para rangos horarios y fechas
                    Map<Integer, String> horariosCitasFuturas = new HashMap<>();
                    Map<Integer, Date> fechasFormateadasFuturas = new HashMap<>();

                    Map<Integer, String> horariosCitasPasadas = new HashMap<>();
                    Map<Integer, Date> fechasFormateadasPasadas = new HashMap<>();

                    // Rellenar el mapa citasFuturas con citas futuras
                    for (Cita cita : citasFuturas) {
                        LocalTime horaInicio = cita.getCalendario().getHoraInicio();
                        int duracion = cita.getServicio().getDuracion();
                        LocalTime horaFinCalculada = horaInicio.plusMinutes(duracion);
                        horariosCitasFuturas.put(cita.getId(), horaInicio + " - " + horaFinCalculada);
                        fechasFormateadasFuturas.put(cita.getId(), java.sql.Date.valueOf(cita.getCalendario().getFecha()));
                    }

                    // Rellenar el mapa citasPasadas con citas pasadas
                    for (Cita cita : citasPasadas) {
                        LocalTime horaInicio = cita.getCalendario().getHoraInicio();
                        int duracion = cita.getServicio().getDuracion();
                        LocalTime horaFinCalculada = horaInicio.plusMinutes(duracion);
                        horariosCitasPasadas.put(cita.getId(), horaInicio + " - " + horaFinCalculada);
                        fechasFormateadasPasadas.put(cita.getId(), java.sql.Date.valueOf(cita.getCalendario().getFecha()));
                    }

                    // Pasar atributos a la vista
                    request.setAttribute("citasFuturas", citasFuturas);
                    request.setAttribute("horariosCitasFuturas", horariosCitasFuturas);
                    request.setAttribute("fechasFormateadasFuturas", fechasFormateadasFuturas);

                    request.setAttribute("citasPasadas", citasPasadas);
                    request.setAttribute("horariosCitasPasadas", horariosCitasPasadas);
                    request.setAttribute("fechasFormateadasPasadas", fechasFormateadasPasadas);

                    // También se envían las listas de mascotas y servicios para usar como filtros en la vista
                    IMascotaDAO mascotaDAO = new MascotaDAO();
                    IServicioDAO servicioDAO = new ServicioDAO();
                    request.setAttribute("listaMascotas", mascotaDAO.getMascotasByIdCliente(idCliente));
                    request.setAttribute("listaServicios", servicioDAO.obtenerServicios());
                }

                url = "JSP/Cliente/misCitas.jsp";
                break;

            case "crearCita":
                try {
                    // Recupera parámetros
                    String idMascotaStr = request.getParameter("idMascota");
                    String idServicioStr = request.getParameter("idServicio");
                    String idHorarioStr = request.getParameter("idHorario");

                    session = request.getSession(false);
                    Cliente cliente = (Cliente) session.getAttribute("usuario");

                    // Validar que los parámetros no sean nulos o vacíos
                    if (idMascotaStr == null || idMascotaStr.isEmpty()
                            || idServicioStr == null || idServicioStr.isEmpty()
                            || idHorarioStr == null || idHorarioStr.isEmpty() || cliente == null) {

                        request.setAttribute("errorCita", "Todos los campos son obligatorios.");

                        // Volver a cargar listas para que los select no se queden vacíos
                        IMascotaDAO mascotaDAO = new MascotaDAO();
                        IServicioDAO servicioDAO = new ServicioDAO();

                        List<Mascota> listaMascotas = mascotaDAO.getMascotasByIdCliente(cliente.getId());
                        List<Servicio> listaServicios = servicioDAO.obtenerServicios();
                        List<Calendario> listaHorarios = new ArrayList<>(); // Vacío por defecto

                        request.setAttribute("listaMascotas", listaMascotas);
                        request.setAttribute("listaServicios", listaServicios);
                        request.setAttribute("listaHorarios", listaHorarios);

                        url = "JSP/Cliente/solicitarCita.jsp";
                        break;
                    }

                    // Conversión de parámetros
                    int idMascota = Integer.parseInt(idMascotaStr);
                    int idServicio = Integer.parseInt(idServicioStr);
                    int idHorario = Integer.parseInt(idHorarioStr);

                    // Obtener entidades desde DAO
                    IMascotaDAO mascotaDAO = new MascotaDAO();
                    IServicioDAO servicioDAO = new ServicioDAO();
                    ICalendarioDAO calendarioDAO = new CalendarioDAO();
                    citaDAO = new CitaDAO();
                    IUsuarioDAO usuarioDAO = new UsuarioDAO();

                    Mascota mascota = mascotaDAO.getById(idMascota);
                    Servicio servicio = servicioDAO.getServicioById(idServicio);
                    Calendario horarioInicio = calendarioDAO.getCalendarioById(idHorario);
                    Usuario veterinario = usuarioDAO.getVeterinario();

                    if (mascota == null || servicio == null || horarioInicio == null) {
                        request.setAttribute("errorCita", "No se pudo crear la cita, datos incorrectos.");

                        // Recarga listas
                        List<Mascota> listaMascotas = mascotaDAO.getMascotasByIdCliente(cliente.getId());
                        List<Servicio> listaServicios = servicioDAO.obtenerServicios();
                        List<Calendario> listaHorarios = new ArrayList<>();

                        request.setAttribute("listaMascotas", listaMascotas);
                        request.setAttribute("listaServicios", listaServicios);
                        request.setAttribute("listaHorarios", listaHorarios);

                        url = "JSP/Cliente/solicitarCita.jsp";
                        break;
                    }

                    // Calcular cuántos bloques de 15 minutos de necesitan
                    int bloquesNecesarios = servicio.getDuracion() / 15;
                    LocalDate fecha = horarioInicio.getFecha();
                    List<Calendario> bloquesDia = calendarioDAO.getHorariosDisponiblesPorFechaOrdenados(fecha);

                    // Buscar los bloques consecutivos desde la hora seleccionada
                    List<Calendario> bloquesSeleccionados = new ArrayList<>();
                    boolean inicioEncontrado = false;

                    for (int i = 0; i < bloquesDia.size(); i++) {
                        if (!inicioEncontrado && bloquesDia.get(i).getId() == idHorario) {
                            // Intentar coger los siguientes bloques necesarios
                            boolean hayHueco = true;
                            for (int j = 0; j < bloquesNecesarios; j++) {
                                if (i + j >= bloquesDia.size() || !bloquesDia.get(i + j).getDisponible()) {
                                    hayHueco = false;
                                    break;
                                }
                            }

                            if (hayHueco) {
                                for (int j = 0; j < bloquesNecesarios; j++) {
                                    bloquesSeleccionados.add(bloquesDia.get(i + j));
                                }
                                inicioEncontrado = true;
                                break;
                            }
                        }
                    }

                    // Si suficientes bloques consecutivos
                    if (!inicioEncontrado || bloquesSeleccionados.size() < bloquesNecesarios) {
                        request.setAttribute("errorCita", "No hay bloques disponibles suficientes para ese servicio.");
                        
                        request.setAttribute("listaMascotas", mascotaDAO.getMascotasByIdCliente(cliente.getId()));
                        request.setAttribute("listaServicios", servicioDAO.obtenerServicios());
                        request.setAttribute("listaHorarios", new ArrayList<>());
                        url = "JSP/Cliente/solicitarCita.jsp";
                        break;
                    }

                    // Crear y guardar la cita
                    Cita cita = new Cita();
                    cita.setMascota(mascota);
                    cita.setServicio(servicio);
                    cita.setCalendario(bloquesSeleccionados.get(0)); // primer bloque
                    cita.setVeterinario(veterinario);

                    // Guardar en la base de datos
                    citaDAO.insertarCita(cita);

                    // Marcar los bloques como ocupados
                    for (Calendario bloque : bloquesSeleccionados) {
                        bloque.setDisponible(false);
                        calendarioDAO.actualizarCalendario(bloque);
                    }

                    request.setAttribute("mensajeCita", "Cita creada exitosamente.");

                    
                    List<Cita> todasCitas = citaDAO.getCitasByCliente(cliente.getId()); // o usuario.getId()

                    List<Cita> citasFuturas = new ArrayList<>();
                    List<Cita> citasPasadas = new ArrayList<>();

                    Map<Integer, String> horariosCitasFuturas = new HashMap<>();
                    Map<Integer, Date> fechasFormateadasFuturas = new HashMap<>();

                    Map<Integer, String> horariosCitasPasadas = new HashMap<>();
                    Map<Integer, Date> fechasFormateadasPasadas = new HashMap<>();

                    LocalDateTime ahora = LocalDateTime.now();

                    for (Cita c : todasCitas) {
                        LocalDate fechaCita = c.getCalendario().getFecha();
                        LocalTime horaInicio = c.getCalendario().getHoraInicio();
                        LocalDateTime fechaHoraCita = LocalDateTime.of(fechaCita, horaInicio);

                        int duracion = c.getServicio().getDuracion();
                        LocalTime horaFinCalculada = horaInicio.plusMinutes(duracion);
                        String horario = horaInicio + " - " + horaFinCalculada;
                        Date fechaSQL = java.sql.Date.valueOf(fechaCita);

                        if (!fechaHoraCita.isBefore(ahora)) {
                            citasFuturas.add(c);
                            horariosCitasFuturas.put(c.getId(), horario);
                            fechasFormateadasFuturas.put(c.getId(), fechaSQL);
                        } else {
                            citasPasadas.add(c);
                            horariosCitasPasadas.put(c.getId(), horario);
                            fechasFormateadasPasadas.put(c.getId(), fechaSQL);
                        }
                    }

                    // Luego pones estos atributos en la request:
                    request.setAttribute("citasFuturas", citasFuturas);
                    request.setAttribute("citasPasadas", citasPasadas);

                    request.setAttribute("horariosCitasFuturas", horariosCitasFuturas);
                    request.setAttribute("fechasFormateadasFuturas", fechasFormateadasFuturas);

                    request.setAttribute("horariosCitasPasadas", horariosCitasPasadas);
                    request.setAttribute("fechasFormateadasPasadas", fechasFormateadasPasadas);


                    request.setAttribute("listaMascotas", mascotaDAO.getMascotasByIdCliente(cliente.getId()));
                    request.setAttribute("listaServicios", servicioDAO.obtenerServicios());
                    
                    url = "JSP/Cliente/misCitas.jsp";

                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorCita", "Error al crear la cita.");

                    session = request.getSession(false);
                    Cliente cliente = (session != null) ? (Cliente) session.getAttribute("usuario") : null;

                    IMascotaDAO mascotaDAO = new MascotaDAO();
                    IServicioDAO servicioDAO = new ServicioDAO();

                    if (cliente != null) {
                        List<Mascota> listaMascotas = mascotaDAO.getMascotasByIdCliente(cliente.getId());
                        List<Servicio> listaServicios = servicioDAO.obtenerServicios();
                        List<Calendario> listaHorarios = new ArrayList<>();

                        request.setAttribute("listaMascotas", listaMascotas);
                        request.setAttribute("listaServicios", listaServicios);
                        request.setAttribute("listaHorarios", listaHorarios);
                    }

                    url = "JSP/Cliente/solicitarCita.jsp";
                }
                break;
                
            // Elimina una cita y libera bloques ocupados
            case "eliminarCita":
                try {
                    int idCita = Integer.parseInt(request.getParameter("idCita"));
                    citaDAO = new CitaDAO();
                    ICalendarioDAO calendarioDAO = new CalendarioDAO();

                    // Obtener la cita y el horario asociado
                    Cita cita = citaDAO.getCitaById(idCita);
                    Calendario calendarioInicio = cita.getCalendario();
                    Servicio servicio = cita.getServicio();

                    // Calcular cuántos bloques de 15 minutos ocupa la cita
                    int bloques = servicio.getDuracion() / 15;
                    LocalDate fecha = calendarioInicio.getFecha();
                    LocalTime horaInicio = calendarioInicio.getHoraInicio();

                    // Obtener todos los bloques del día ordenados por hora
                    List<Calendario> bloquesDia = calendarioDAO.getHorariosDisponiblesPorFechaOrdenados(fecha);

                    // Identificar los bloques que deben liberarse
                    List<Calendario> bloquesALiberar = new ArrayList<>();
                    boolean inicioEncontrado = false;

                    
                    for (int i = 0; i < bloquesDia.size(); i++) {
                        Calendario actual = bloquesDia.get(i);
                        if (!inicioEncontrado && actual.getHoraInicio().equals(horaInicio)) {
                            // Verificar que los siguientes bloques coincidan
                            boolean coincide = true;
                            for (int j = 0; j < bloques; j++) {
                                if (i + j >= bloquesDia.size()) {
                                    coincide = false;
                                    break;
                                }
                                if (!bloquesDia.get(i + j).getHoraInicio()
                                        .equals(horaInicio.plusMinutes(15 * j))) {
                                    coincide = false;
                                    break;
                                }
                            }
                            if (coincide) {
                                for (int j = 0; j < bloques; j++) {
                                    bloquesALiberar.add(bloquesDia.get(i + j));
                                }
                                break;
                            }
                        }
                    }

                    // Eliminar la cita
                    citaDAO.eliminarCita(idCita);

                    // Marcar los bloques como disponibles
                    calendarioDAO.liberarBloquesDeCita(fecha, horaInicio, servicio.getDuracion());

                    request.setAttribute("mensajeCita", "Cita cancelada correctamente.");

                    // Recargar la lista de citas
                    session = request.getSession(false);
                    if (session != null && session.getAttribute("usuario") != null) {
                        Cliente cliente = (Cliente) session.getAttribute("usuario");

                        // Al obtener todas las citas:
                        List<Cita> todasCitas = citaDAO.getCitasByCliente(cliente.getId()); // o usuario.getId()

                        List<Cita> citasFuturas = new ArrayList<>();
                        List<Cita> citasPasadas = new ArrayList<>();

                        Map<Integer, String> horariosCitasFuturas = new HashMap<>();
                        Map<Integer, Date> fechasFormateadasFuturas = new HashMap<>();

                        Map<Integer, String> horariosCitasPasadas = new HashMap<>();
                        Map<Integer, Date> fechasFormateadasPasadas = new HashMap<>();

                        LocalDateTime ahora = LocalDateTime.now();

                        for (Cita c : todasCitas) {
                            LocalDate fechaCita = c.getCalendario().getFecha();
                            horaInicio = c.getCalendario().getHoraInicio();
                            LocalDateTime fechaHoraCita = LocalDateTime.of(fechaCita, horaInicio);

                            int duracion = c.getServicio().getDuracion();
                            LocalTime horaFinCalculada = horaInicio.plusMinutes(duracion);
                            String horario = horaInicio + " - " + horaFinCalculada;
                            Date fechaSQL = java.sql.Date.valueOf(fechaCita);

                            if (!fechaHoraCita.isBefore(ahora)) {
                                citasFuturas.add(c);
                                horariosCitasFuturas.put(c.getId(), horario);
                                fechasFormateadasFuturas.put(c.getId(), fechaSQL);
                            } else {
                                citasPasadas.add(c);
                                horariosCitasPasadas.put(c.getId(), horario);
                                fechasFormateadasPasadas.put(c.getId(), fechaSQL);
                            }
                        }


                        request.setAttribute("citasFuturas", citasFuturas);
                        request.setAttribute("citasPasadas", citasPasadas);

                        request.setAttribute("horariosCitasFuturas", horariosCitasFuturas);
                        request.setAttribute("fechasFormateadasFuturas", fechasFormateadasFuturas);

                        request.setAttribute("horariosCitasPasadas", horariosCitasPasadas);
                        request.setAttribute("fechasFormateadasPasadas", fechasFormateadasPasadas);

                        IMascotaDAO mascotaDAO = new MascotaDAO();
                        IServicioDAO servicioDAO = new ServicioDAO();

                        request.setAttribute("listaMascotas", mascotaDAO.getMascotasByIdCliente(cliente.getId()));
                        request.setAttribute("listaServicios", servicioDAO.obtenerServicios());

                    }

                    url = "JSP/Cliente/misCitas.jsp";

                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorCita", "Error al eliminar la cita.");
                    url = "JSP/Cliente/misCitas.jsp";
                }
                break;
                
            // Filtrar citas del cliente por mascota, servicio y/o fecha
            case "filtrarCitasCliente":
                try {
                    int idCliente = ((Usuario) request.getSession().getAttribute("usuario")).getId();
                    IMascotaDAO mascotaDAO = new MascotaDAO();
                    IServicioDAO servicioDAO = new ServicioDAO();

                    String mascotaIdStr = request.getParameter("mascotaId");
                    String servicioIdStr = request.getParameter("servicioId");
                    String fechaStr = request.getParameter("fechaFiltro");

                    Integer mascotaId = (mascotaIdStr != null && !mascotaIdStr.isEmpty()) ? Integer.parseInt(mascotaIdStr) : null;
                    Integer servicioId = (servicioIdStr != null && !servicioIdStr.isEmpty()) ? Integer.parseInt(servicioIdStr) : null;
                    LocalDate fecha = (fechaStr != null && !fechaStr.isEmpty()) ? LocalDate.parse(fechaStr) : null;

                    List<Cita> listaCitas = citaDAO.filtrarCitasCliente(idCliente, mascotaId, servicioId, fecha);

                    List<Cita> citasFuturas = new ArrayList<>();
                    List<Cita> citasPasadas = new ArrayList<>();

                    LocalDate hoy = LocalDate.now();
                    LocalTime ahora = LocalTime.now();

                    for (Cita cita : listaCitas) {
                        LocalDate fechaCita = cita.getCalendario().getFecha();
                        LocalTime horaInicio = cita.getCalendario().getHoraInicio();

                        if (fechaCita.isAfter(hoy) || (fechaCita.isEqual(hoy) && horaInicio.isAfter(ahora))) {
                            citasFuturas.add(cita);
                        } else {
                            citasPasadas.add(cita);
                        }
                    }

                    Map<Integer, String> horariosCitasFuturas = new HashMap<>();
                    Map<Integer, Date> fechasFormateadasFuturas = new HashMap<>();

                    Map<Integer, String> horariosCitasPasadas = new HashMap<>();
                    Map<Integer, Date> fechasFormateadasPasadas = new HashMap<>();

                    for (Cita cita : citasFuturas) {
                        LocalTime horaInicio = cita.getCalendario().getHoraInicio();
                        int duracion = cita.getServicio().getDuracion();
                        LocalTime horaFinCalculada = horaInicio.plusMinutes(duracion);
                        horariosCitasFuturas.put(cita.getId(), horaInicio + " - " + horaFinCalculada);
                        fechasFormateadasFuturas.put(cita.getId(), java.sql.Date.valueOf(cita.getCalendario().getFecha()));
                    }

                    for (Cita cita : citasPasadas) {
                        LocalTime horaInicio = cita.getCalendario().getHoraInicio();
                        int duracion = cita.getServicio().getDuracion();
                        LocalTime horaFinCalculada = horaInicio.plusMinutes(duracion);
                        horariosCitasPasadas.put(cita.getId(), horaInicio + " - " + horaFinCalculada);
                        fechasFormateadasPasadas.put(cita.getId(), java.sql.Date.valueOf(cita.getCalendario().getFecha()));
                    }

                    request.setAttribute("citasFuturas", citasFuturas);
                    request.setAttribute("horariosCitasFuturas", horariosCitasFuturas);
                    request.setAttribute("fechasFormateadasFuturas", fechasFormateadasFuturas);

                    request.setAttribute("citasPasadas", citasPasadas);
                    request.setAttribute("horariosCitasPasadas", horariosCitasPasadas);
                    request.setAttribute("fechasFormateadasPasadas", fechasFormateadasPasadas);

                    // Recargar listas para filtros
                    request.setAttribute("listaMascotas", mascotaDAO.getMascotasByIdCliente(idCliente));
                    request.setAttribute("listaServicios", servicioDAO.obtenerServicios());

                    url = "JSP/Cliente/misCitas.jsp";

                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorCita", "Error al filtrar las citas.");
                    url = "JSP/Cliente/misCitas.jsp";
                }
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
