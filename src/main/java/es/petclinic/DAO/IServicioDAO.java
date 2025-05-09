package es.petclinic.DAO;

import es.petclinic.beans.Servicio;
import java.util.List;

public interface IServicioDAO extends IGenericoDAO<Servicio> {
    
    /**
     * 
     * 
     * 
     * @return 
     */
    public List<Servicio> obtenerServicios();
    
}
