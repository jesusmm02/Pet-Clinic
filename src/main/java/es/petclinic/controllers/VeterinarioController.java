package es.petclinic.controllers;

import es.petclinic.DAO.ClienteDAO;
import es.petclinic.DAO.HistorialMedicoDAO;
import es.petclinic.DAO.IClienteDAO;
import es.petclinic.DAO.IHistorialMedicoDAO;
import es.petclinic.DAO.IMascotaDAO;
import es.petclinic.DAO.MascotaDAO;

import es.petclinic.beans.Cliente;
import es.petclinic.beans.HistorialMedico;
import es.petclinic.beans.Mascota;
import es.petclinic.beans.Usuario;

import java.io.IOException;
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
            case "verClientes":
               HttpSession sessionMascotas = request.getSession(false);
                if (sessionMascotas != null && sessionMascotas.getAttribute("usuario") != null) {

                    // DAO para clientes y sus mascotas
                    IClienteDAO clienteDAO = new ClienteDAO();

                    // Obtenemos todos los clientes con sus mascotas en una única consulta
                    List<Cliente> listaClientes = clienteDAO.obtenerClientesConMascotas();

                    // Pasamos la lista directamente a la vista
                    request.setAttribute("listaClientes", listaClientes);
                    url = "JSP/Veterinario/clientes_mascotas.jsp";
                } else {
                    url = "JSP/Veterinario/veterinario.jsp";
                }
                break;
            case "historialMedico":
                IHistorialMedicoDAO historialDAO = new HistorialMedicoDAO();
                IMascotaDAO mascotaDAO = new MascotaDAO();

                List<HistorialMedico> listaHistoriales = historialDAO.getAllHistoriales();
                List<Mascota> listaMascotas = mascotaDAO.getAllMascotas();

                request.setAttribute("listaHistoriales", listaHistoriales);
                request.setAttribute("listaMascotas", listaMascotas);

                url = "JSP/Veterinario/historialMedico.jsp";
                break;
            case "guardarHistorial":
                try {
                    // Crear instancia del HistorialMedico
                    HistorialMedico historial = new HistorialMedico();

                    // Poblar el bean con los datos del formulario
                    BeanUtils.populate(historial, request.getParameterMap());

                    // Obtener la mascota seleccionada
                    int idMascota = Integer.parseInt(request.getParameter("idMascota"));
                    mascotaDAO = new MascotaDAO();
                    Mascota mascota = mascotaDAO.getById(idMascota);

                    // Asignar la mascota al historial
                    historial.setMascota(mascota);
                    
                    // Asignar al hitorial la fecha actual
                    historial.setFecha(new java.util.Date());

                    // Guardar en la base de datos
                    historialDAO = new HistorialMedicoDAO();
                    historialDAO.guardarHistorial(historial);
                    
                    // Volver a cargar la lista para que se vea al recargar la página
                    listaHistoriales = historialDAO.getAllHistoriales();
                    listaMascotas = mascotaDAO.getAllMascotas();

                    // Asignar al request para la vista
                    request.setAttribute("listaHistoriales", listaHistoriales);
                    request.setAttribute("listaMascotas", listaMascotas);
                    
                    request.setAttribute("creacionHistorial", "Se ha añadido el historial médico correctamente");

                    // Redirigir a página de historiales
                    url= "JSP/Veterinario/historialMedico.jsp";

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "eliminarHistorial":
                try {
                    int idHistorial = Integer.parseInt(request.getParameter("idHistorial"));
                    historialDAO = new HistorialMedicoDAO();
                    
                    historialDAO.eliminarHistorial(idHistorial);
                    mascotaDAO = new MascotaDAO();
                    
                    // Volver a cargar la lista para que se vea al recargar la página
                    listaHistoriales = historialDAO.getAllHistoriales();
                    listaMascotas = mascotaDAO.getAllMascotas();

                    // Asignar al request para la vista
                    request.setAttribute("listaHistoriales", listaHistoriales);
                    request.setAttribute("listaMascotas", listaMascotas);
                    
                    request.setAttribute("eliminacionHistorial", "Se ha eliminado el historial médico correctamente");

                    // Redirigir a la misma página para actualizar
                    url="JSP/Veterinario/historialMedico.jsp";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "citas":
                url = "JSP/Veterinario/verCitas.jsp";
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
