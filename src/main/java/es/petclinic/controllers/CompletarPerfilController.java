package es.petclinic.controllers;

import es.petclinic.DAO.ClienteDAO;
import es.petclinic.DAO.IClienteDAO;

import es.petclinic.beans.Cliente;

import es.petclinic.models.EnumConverter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 *
 * @author Jesús
 */
@WebServlet(name = "CompletarPerfilController", urlPatterns = {"/CompletarPerfilController"})
public class CompletarPerfilController extends HttpServlet {

    
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
        
        // Registrar el convertidor para Enum de genero
        ConvertUtils.register(new EnumConverter(), Cliente.Genero.class);
        
        // Manejar la conversión de la fecha de nacimiento
        DateConverter dateConverter = new DateConverter(null);
        dateConverter.setPattern("yyyy-MM-dd"); // Formato de la fecha
        ConvertUtils.register(dateConverter, java.util.Date.class);
        ConvertUtils.register(dateConverter, java.sql.Date.class);
        
        // Crear una instancia de IClienteDAO
        IClienteDAO clienteDAO = new ClienteDAO();
        
        // Obtener los parámetros del formulario
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String genero = request.getParameter("genero");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");

        // Convertir la fecha de nacimiento si está presente
        Date fechaNacimiento = null;
        if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
            // Usamos SimpleDateFormat para convertir la fecha
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fechaNacimiento = sdf.parse(fechaNacimientoStr);  // Convierte el String a Date
            } catch (Exception e) {
                e.printStackTrace(); // Manejo de la excepción si la fecha no es válida
            }
        }

        // Buscar al cliente en la base de datos usando su id
        Cliente cliente = clienteDAO.getById(idUsuario, Cliente.class);

        if (cliente == null) {
            // Si no se encuentra el cliente, mostrar un error
            request.setAttribute("error", "No se encontró el cliente.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Usar BeanUtils para poblar los campos básicos del cliente desde el formulario
        try {
            // Mapear el formulario a los campos comunes del cliente
            BeanUtils.populate(cliente, request.getParameterMap());

            // Asignar manualmente los campos de género y fecha de nacimiento
            cliente.setGenero(Cliente.Genero.valueOf(genero));
            cliente.setFechaNacimiento(fechaNacimiento);

            // Guardar el cliente actualizado en la base de datos
            clienteDAO.insertOrUpdate(cliente);
            
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // Redirigir a la página de cliente
        request.getRequestDispatcher("JSP/Cliente/cliente.jsp").forward(request, response);
    
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
