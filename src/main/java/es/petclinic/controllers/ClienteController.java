package es.petclinic.controllers;

import es.petclinic.DAO.ClienteDAO;
import es.petclinic.DAO.HistorialMedicoDAO;
import es.petclinic.DAO.IClienteDAO;
import es.petclinic.DAO.IHistorialMedicoDAO;
import es.petclinic.DAO.IMascotaDAO;
import es.petclinic.DAO.IServicioDAO;
import es.petclinic.DAO.IUsuarioDAO;
import es.petclinic.DAO.MascotaDAO;
import es.petclinic.DAO.ServicioDAO;
import es.petclinic.DAO.UsuarioDAO;

import es.petclinic.beans.Cliente;
import es.petclinic.beans.HistorialMedico;
import es.petclinic.beans.Mascota;
import es.petclinic.beans.Servicio;
import es.petclinic.beans.Usuario;

import es.petclinic.models.EnumConverter;
import es.petclinic.models.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 *
 * @author Jesús
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ClienteController extends HttpServlet {

    private static final String UPLOAD_DIR = "IMG/avatares"; // Carpeta donde se guardan los avatares

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

        String url = "JSP/Cliente/cliente.jsp";
        String accion = request.getParameter("accion");

        switch (accion) {
            case "homeCliente":

                url = "JSP/Cliente/cliente.jsp"; // Página inicial de cliente

                break;
            case "gestionMascotas":

                // Cargar lista de mascotas del cliente que está en sesión
                HttpSession sessionMascotas = request.getSession(false);
                if (sessionMascotas != null && sessionMascotas.getAttribute("usuario") != null) {
                    Usuario usuario = (Usuario) sessionMascotas.getAttribute("usuario");

                    IMascotaDAO mascotaDAO = new MascotaDAO();
                    List<Mascota> listaMascotas = mascotaDAO.getMascotasByIdCliente(usuario.getId());

                    request.setAttribute("listaMascotas", listaMascotas);
                    url = "JSP/Cliente/mascotas.jsp";
                } else {
                    url = "JSP/Cliente/cliente.jsp";
                }

                break;
            case "citas":
                
                url = "JSP/Cliente/gestionarCitas.jsp"; // Página inicial de gestión de citas
                
                break;
            case "historialMedico":
                try {
                    // Recuperar el cliente de la sesión
                    Usuario cliente = (Usuario) request.getSession().getAttribute("usuario");

                    if (cliente != null) {
                        // Obtener todas las mascotas del cliente
                        MascotaDAO mascotaDAO = new MascotaDAO();
                        List<Mascota> listaMascotas = mascotaDAO.getMascotasByIdCliente(cliente.getId());

                        // Obtener todos los historiales asociados a esas mascotas
                        IHistorialMedicoDAO historialDAO = new HistorialMedicoDAO();
                        List<HistorialMedico> listaHistoriales = new ArrayList<>();

                        for (Mascota mascota : listaMascotas) {
                            List<HistorialMedico> historialesMascota = historialDAO.getHistorialesByIdMascota(mascota.getId());
                            listaHistoriales.addAll(historialesMascota);
                        }

                        // Pasar los datos a la vista
                        request.setAttribute("listaHistoriales", listaHistoriales);
                        request.setAttribute("listaMascotas", listaMascotas);
                    }

                    url = "JSP/Cliente/historialMedico.jsp";

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "verServicios":
                IServicioDAO servicioDAO = new ServicioDAO();
                List<Servicio> listaServicios = servicioDAO.obtenerServicios();
                request.setAttribute("listaServicios", listaServicios);
                url = "JSP/Cliente/servicios.jsp";
                break;
            case "verInfografia":
                url = "JSP/Cliente/infografia.jsp"; // Página de infografía
                break;
            case "editarPerfil":

                HttpSession session = request.getSession(false);

                // Si hay usuario en sesión, entonces puedo actualizarlo
                if (session != null && session.getAttribute("usuario") != null) {
                    Usuario usuario = (Usuario) session.getAttribute("usuario");
                    // Crea un atributo de petición usuario para poder mostrar sus parámetros en la vista de edición
                    request.setAttribute("usuario", usuario);

                    // Redirigir a la vista de edición de perfil
                    url = "JSP/Cliente/editarPerfil.jsp";
                } else {
                    // Si no hay usuario en sesión, redirige al inicio de la aplicación
                    url = ".";
                }

                break;
                
            // Procesa la edición del perfil del cliente    
            case "guardarCambios":

                session = request.getSession(false);

                // Si usuario está en sesión
                if (session != null && session.getAttribute("usuario") != null) {
                    Usuario usuario = (Usuario) session.getAttribute("usuario");
                    IUsuarioDAO usuDAO = new UsuarioDAO();

                    // Recuperar datos del formulario
                    String nombre = request.getParameter("nombre");
                    String apellidos = request.getParameter("apellidos");

                    // Validación de campos obligatorios
                    if (nombre == null || nombre.trim().isEmpty()
                            || apellidos == null || apellidos.trim().isEmpty()) {

                        request.setAttribute("error", "Todos los campos marcados (*) son obligatorios");
                        request.getRequestDispatcher("JSP/Cliente/editarPerfil.jsp").forward(request, response);
                        return;
                    }
                    
                    // Lógica para quitar foto con el botón
                    String quitarFoto = request.getParameter("quitarAvatar");
                    if ("true".equals(quitarFoto)) {
                        // Borrar archivo físico si existe
                        if (usuario.getAvatar() != null && !usuario.getAvatar().isEmpty()) {
                            String rutaFoto = getServletContext().getRealPath("/IMG/avatares/" + usuario.getAvatar());
                            File fileFoto = new File(rutaFoto);
                            if (fileFoto.exists()) {
                                fileFoto.delete();
                            }
                        }
                        // Actualizar usuario para quitar avatar
                        usuario.setAvatar("default.jpg");
                    }

                    // Actualizar campos del usuario
                    usuario.setNombre(nombre);
                    usuario.setApellidos(apellidos);

                    // Obtener cliente y actualizar datos personales
                    IClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.getByIdUsuario(usuario.getId());

                    String generoStr = request.getParameter("genero");
                    String fechaNacimientoStr = request.getParameter("fechaNacimiento");

                    // Conversión de enums y fechas
                    ConvertUtils.register(new EnumConverter(), Cliente.Genero.class);

                    DateConverter dateConverter = new DateConverter(null);
                    dateConverter.setPattern("yyyy-MM-dd");
                    ConvertUtils.register(dateConverter, java.util.Date.class);
                    ConvertUtils.register(dateConverter, java.sql.Date.class);

                    Cliente.Genero genero = (Cliente.Genero) ConvertUtils.convert(generoStr, Cliente.Genero.class);
                    Date fechaNacimiento = (Date) ConvertUtils.convert(fechaNacimientoStr, java.util.Date.class);

                    // Asignar manualmente género y fecha de nacimiento
                    cliente.setGenero(genero);
                    cliente.setFechaNacimiento(fechaNacimiento);

                    // Contraseña
                    String passwordActual = request.getParameter("passwordActual");
                    String passwordNueva1 = request.getParameter("passwordNueva1"); // Password nueva
                    String passwordNueva2 = request.getParameter("passwordNueva2"); // Confirmación de password nueva

                    // Validar cambio de contraseña
                    if (passwordActual != null && !passwordActual.isEmpty()) {
                        String hashedPasswordActual = Utils.hashMD5(passwordActual);

                        if (hashedPasswordActual.equals(usuario.getPassword())) {
                            if (passwordNueva1 != null && passwordNueva1.equals(passwordNueva2)) {
                                String hashedNuevaPassword = Utils.hashMD5(passwordNueva1);
                                usuario.setPassword(hashedNuevaPassword);
                            } else {
                                request.setAttribute("usuario", usuario);
                                request.setAttribute("cliente", cliente);
                                request.setAttribute("error", "Las contraseñas nuevas no coinciden");
                                request.getRequestDispatcher("JSP/Cliente/editarPerfil.jsp").forward(request, response);
                                return;
                            }
                        } else {
                            request.setAttribute("usuario", usuario);
                            request.setAttribute("cliente", cliente);
                            request.setAttribute("error", "La contraseña actual no es correcta");
                            request.getRequestDispatcher("JSP/Cliente/editarPerfil.jsp").forward(request, response);
                            return;
                        }
                    }

                    // Manejar subida del avatar
                    Part filePart = request.getPart("avatar");
                    if (filePart != null && filePart.getSize() > 0) {
                        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                        String uniqueFileName = String.valueOf(System.currentTimeMillis()) + fileExtension;

                        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }

                        String filePath = uploadPath + File.separator + uniqueFileName;

                        filePart.write(filePath);

                        // Guardar el nombre del archivo en el usuario
                        usuario.setAvatar(uniqueFileName);
                    }

                    // Guardar cambios en la base de datos
                    try {
                        usuDAO.insertOrUpdateUsuario(usuario);
                        clienteDAO.insertOrUpdateCliente(cliente);

                        usuario = usuDAO.getById(usuario.getId());
                        cliente = clienteDAO.getByIdUsuario(usuario.getId());

                        request.setAttribute("editado", "Perfil actualizado correctamente.");
                        session.setAttribute("usuario", usuario);
                        session.setAttribute("cliente", cliente);
                        request.getRequestDispatcher("/JSP/Cliente/cliente.jsp").forward(request, response);
                        return;

                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                        request.setAttribute("error", "Error al actualizar los datos. Inténtalo más tarde.");
                        request.getRequestDispatcher("/JSP/Cliente/editarPerfil.jsp").forward(request, response);
                        return;
                    }

                } else {
                    request.getRequestDispatcher("/JSP/Cliente/cliente.jsp").forward(request, response);
                }

                break;

            // Para volver a la vista página principal del cliente
            case "volver":
                url = "JSP/Cliente/cliente.jsp";
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
