package es.petclinic.controllers;

import es.petclinic.DAO.IMascotaDAO;
import es.petclinic.DAO.MascotaDAO;

import es.petclinic.beans.Cliente;
import es.petclinic.beans.Mascota;

import es.petclinic.models.EnumConverter;
import es.petclinic.models.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 *
 * @author Jesús
 */
@WebServlet(name = "MascotaController", urlPatterns = {"/MascotaController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class MascotaController extends HttpServlet {

    private static final String UPLOAD_DIR = "IMG/fotosMascotas";

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

        String url = "JSP/Cliente/mascotas.jsp";
        String accion = request.getParameter("accion");

        switch (accion) {
            case "crearMascota":
                url = "JSP/Mascota/crearMascota.jsp";
                break;

            case "guardarMascota":

                HttpSession sessionGuardar = request.getSession(false);

                if (sessionGuardar != null && sessionGuardar.getAttribute("usuario") != null) {
                    Cliente propietario = (Cliente) sessionGuardar.getAttribute("usuario");

                    // Validar campos obligatorios
                    Enumeration<String> parametros = request.getParameterNames();
                    String error = Utils.comprobarCamposMascota(parametros, request);

                    if (!error.equals("n")) {
                        String aviso = "Todos los campos marcados (*) son obligatorios";
                        request.setAttribute("errorCreate", aviso);
                        url = "JSP/Mascota/crearMascota.jsp";
                    } else {
                        try {
                            // Preparar la nueva mascota
                            Mascota mascota = new Mascota();

                            // Registrar convertidores para enums y fecha
                            ConvertUtils.register(new EnumConverter(), Mascota.Genero.class);

                            DateConverter dateConverter = new DateConverter(null);
                            dateConverter.setPattern("yyyy-MM-dd");
                            ConvertUtils.register(dateConverter, java.util.Date.class);
                            ConvertUtils.register(dateConverter, java.sql.Date.class);

                            // Mapear parámetros al bean
                            BeanUtils.populate(mascota, request.getParameterMap());

                            // Establecer el propietario (cliente logueado)
                            mascota.setPropietario(propietario);

                            // MANEJAR SUBIDA DE FOTO
                            Part filePart = request.getPart("foto");

                            if (filePart != null && filePart.getSize() > 0) {
                                // Obtener el nombre del archivo original
                                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                                // Obtener la extensión del archivo (por ejemplo, .jpg, .png, etc.)
                                String fileExtension = fileName.substring(fileName.lastIndexOf("."));

                                // Generar nombre único para el archivo
                                String uniqueFileName = String.valueOf(System.currentTimeMillis()) + fileExtension;

                                // Establecer el directorio de subida
                                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
                                File uploadDir = new File(uploadPath);
                                if (!uploadDir.exists()) {
                                    uploadDir.mkdir();
                                }

                                // Ruta completa para guardar el archivo
                                String filePath = uploadPath + File.separator + uniqueFileName;

                                // Guardar archivo
                                filePart.write(filePath);

                                // Guardar la ruta del avatar en el cliente (solo el nombre del archivo)
                                mascota.setFoto(uniqueFileName);
                            } else {
                                mascota.setFoto("defaultMascota.jpg"); // valor por defecto si no suben foto
                            }

                            // Guardar mascota
                            IMascotaDAO mascotaDAO = new MascotaDAO();
                            mascotaDAO.insertarMascota(mascota);

                            // Cargar lista actualizada de mascotas
                            List<Mascota> listaMascotas = mascotaDAO.getMascotasByIdCliente(propietario.getId());
                            request.setAttribute("listaMascotas", listaMascotas);

                            request.setAttribute("creada", "Mascota creada correctamente.");

                            // Redirigir tras guardar
                            url = "JSP/Cliente/mascotas.jsp";

                        } catch (Exception e) {
                            e.printStackTrace();
                            request.setAttribute("errorCreate", "Error al guardar la mascota.");
                            url = "JSP/Mascota/crearMascota.jsp";
                        }
                    }
                } else {
                    url = ".";
                    return;
                }

                break;

            case "editarMascota":
                try {
                int idMascota = Integer.parseInt(request.getParameter("id"));

                IMascotaDAO mascotaDAO = new MascotaDAO();
                Mascota mascota = mascotaDAO.getById(idMascota);

                if (mascota != null) {
                    request.setAttribute("mascota", mascota);
                    url = "JSP/Mascota/editarMascota.jsp";
                    break;
                } else {
                    request.setAttribute("error", "No se encontró la mascota.");
                    url = "JSP/Cliente/mascotas.jsp";
                    break;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID de mascota no válido.");
                url = "JSP/Cliente/mascotas.jsp";
            }
            break;

            case "modificarMascota":
                try {
                // Validar campos
                Enumeration<String> parametros = request.getParameterNames();
                String error = Utils.comprobarCamposMascota(parametros, request);

                if (!error.equals("n")) {
                    request.setAttribute("errorCreate", "Todos los campos marcados (*) son obligatorios");

                    // Cargar la mascota original para que no se pierdan datos
                    int idMascota = Integer.parseInt(request.getParameter("id"));
                    IMascotaDAO mascotaDAO = new MascotaDAO();
                    Mascota mascota = mascotaDAO.getById(idMascota);
                    request.setAttribute("mascota", mascota);

                    url = "JSP/Mascota/editarMascota.jsp";
                    break;
                }

                int idMascota = Integer.parseInt(request.getParameter("id"));
                IMascotaDAO mascotaDAO = new MascotaDAO();
                Mascota mascota = mascotaDAO.getById(idMascota);

                if (mascota != null) {
                    // Convertidores
                    ConvertUtils.register(new EnumConverter(), Mascota.Genero.class);
                    DateConverter dateConverter = new DateConverter(null);
                    dateConverter.setPattern("yyyy-MM-dd");
                    ConvertUtils.register(dateConverter, java.util.Date.class);
                    ConvertUtils.register(dateConverter, java.sql.Date.class);

                    // Actualizar con los nuevos datos
                    BeanUtils.populate(mascota, request.getParameterMap());

                    // Manejar nueva foto
                    Part filePart = request.getPart("foto");
                    if (filePart != null && filePart.getSize() > 0) {
                        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                        String uniqueFileName = System.currentTimeMillis() + fileExtension;

                        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }

                        String filePath = uploadPath + File.separator + uniqueFileName;
                        filePart.write(filePath);

                        mascota.setFoto(uniqueFileName);
                    }

                    mascotaDAO.actualizarMascota(mascota);

                    // Recargar lista
                    HttpSession session = request.getSession(false);
                    if (session != null && session.getAttribute("usuario") != null) {
                        Cliente cliente = (Cliente) session.getAttribute("usuario");
                        List<Mascota> lista = mascotaDAO.getMascotasByIdCliente(cliente.getId());
                        request.setAttribute("listaMascotas", lista);
                    }

                    request.setAttribute("editada", "Mascota actualizada correctamente.");
                    url = "JSP/Cliente/mascotas.jsp";

                } else {
                    request.setAttribute("error", "No se encontró la mascota.");
                    url = "JSP/Cliente/mascotas.jsp";
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al actualizar la mascota.");
                url = "JSP/Cliente/mascotas.jsp";
            }
            break;

            case "eliminarMascota":
                
                try {
                int idMascota = Integer.parseInt(request.getParameter("id"));
                IMascotaDAO mascotaDAO = new MascotaDAO();

                mascotaDAO.eliminarMascota(idMascota);

                // Volver a cargar la lista actualizada
                HttpSession sessionEliminar = request.getSession(false);
                if (sessionEliminar != null && sessionEliminar.getAttribute("usuario") != null) {
                    Cliente cliente = (Cliente) sessionEliminar.getAttribute("usuario");
                    List<Mascota> listaActualizada = mascotaDAO.getMascotasByIdCliente(cliente.getId());
                    request.setAttribute("listaMascotas", listaActualizada);
                }

                request.setAttribute("eliminada", "Mascota eliminada correctamente.");
                url = "JSP/Cliente/mascotas.jsp";

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "No se pudo eliminar la mascota.");
                url = "JSP/Cliente/mascotas.jsp";
            }
            break;

            case "volver":
                url = "JSP/Cliente/mascota.jsp";
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
