package es.petclinic.DAO;

import es.petclinic.beans.Mascota;
import java.util.List;

public interface IMascotaDAO extends IGenericoDAO<Mascota> {
    
    /**
     * 
     * 
     * 
     * @param id
     * @return 
     */
    public Mascota getById(int id);
    
    /**
     * 
     * 
     * 
     * @param idCliente
     * @return 
     */
    public List<Mascota> getMascotasByIdCliente(int idCliente);
    
    /**
     * 
     * 
     * 
     * @return 
     */
    public List<Mascota> getAllMascotas();
    
    /**
     * 
     * 
     * 
     * @param mascota 
     */
    public void insertarMascota(Mascota mascota);
    
    /**
     * 
     * 
     * 
     * @param mascota 
     */
    public void actualizarMascota(Mascota mascota);
    
    /**
     * 
     * 
     * 
     * @param id 
     */
    public void eliminarMascota(int id);
    
}
