package es.petclinic.controllers;

import es.petclinic.DAO.ClienteDAO;
import es.petclinic.DAO.IClienteDAO;

import es.petclinic.beans.Cliente;
import es.petclinic.beans.Usuario;

import es.petclinic.models.EnumConverter;
import es.petclinic.models.Utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

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
@WebServlet(name = "RegistroController", urlPatterns = {"/RegistroController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class RegistroController extends HttpServlet {

    private static final String UPLOAD_DIR = "IMG/avatares";

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

        if (null == accion) {

            String url = ".";

            Cliente cliente;
            HttpSession session = request.getSession();

            // Comprobamos que todos los campos estén rellenos y que las contraseñas coincidan
            Enumeration<String> parametros = request.getParameterNames();
            String error = Utils.comprobarCampos(parametros, request);

            if (!error.equals("n")) {
                // En el caso de que exista error se realizan las siguientes funciones:
                String aviso = "Las contraseñas no son iguales";
                if (error.equals("v")) {
                    aviso = "Los campos marcados con (*) son obligatorios";
                }
                request.setAttribute("errorCreate", aviso);
                url = ".";  // Volver a la página principal (index.jsp)
            } else { // Si no hay errores
                try {
                    cliente = new Cliente();
                    
                    // Registrar el convertidor para Enum de genero
                    ConvertUtils.register(new EnumConverter(), Cliente.Genero.class);

                    // Manejar la conversión de la fecha de nacimiento
                    DateConverter dateConverter = new DateConverter(null);
                    dateConverter.setPattern("yyyy-MM-dd"); // Formato de la fecha
                    ConvertUtils.register(dateConverter, java.util.Date.class);
                    ConvertUtils.register(dateConverter, java.sql.Date.class);

                    // Poblar los datos comunes de Usuario y los específicos de Cliente
                    BeanUtils.populate(cliente, request.getParameterMap());

                    // Encriptar la contraseña antes de guardarla usando MD5
                    String plainPassword = cliente.getPassword(); // Obtén la contraseña en texto plano
                    String hashedPassword = Utils.hashPassword(plainPassword); // Encripta la contraseña usando hashMD5
                    cliente.setPassword(hashedPassword);  // Establece la contraseña encriptada en el cliente  

                    // Establecer rol como CLIENTE
                    cliente.setRol(Usuario.Rol.CLIENTE);

                    // Subida de avatar
                    Part filePart = request.getPart("avatar");

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
                        cliente.setAvatar(uniqueFileName);
                    } else {
                        cliente.setAvatar("default.jpg");  // Si no se selecciona avatar, se asigna uno por defecto
                    }

                    // Establecer la fecha del último acceso
                    Timestamp ahora = new Timestamp(new Date().getTime());
                    cliente.setUltimoAcceso(ahora);

                    // Guardar el cliente en la base de datos
                    IClienteDAO clienteDAO = new ClienteDAO();
                    clienteDAO.insertOrUpdateCliente(cliente);  // Guardamos el cliente

                    session.setAttribute("usuario", cliente);  // Meter al cliente en la sesión

                    url = "JSP/Cliente/cliente.jsp";  // Página de inicio de cliente

                } catch (IllegalAccessException | InvocationTargetException e) {
                    // Error al usar BeanUtils
                    Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            request.getRequestDispatcher(url).forward(request, response);
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
