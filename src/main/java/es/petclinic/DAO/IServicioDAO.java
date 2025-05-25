package es.petclinic.DAO;

import es.petclinic.beans.Servicio;
import java.util.List;

public interface IServicioDAO extends IGenericoDAO<Servicio> {
    
    /**
     * 
     * Recupera todos los servicios disponibles almacenados en la base de datos.
     * 
     * @return Lista de objetos Servicio que representa los servicios ofrecidos.
     */
    public List<Servicio> obtenerServicios();
    
    /**
     * 
     * Obtiene un servicio específico a partir de su ID.
     * 
     * @param idServicio ID del servicio que se desea recuperar.
     * @return Objeto Servicio correspondiente, o null si no se encuentra.
     */
    public Servicio getServicioById(int idServicio);
    
    
    
}
