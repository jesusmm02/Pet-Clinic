package es.petclinic.DAO;

import es.petclinic.beans.HistorialMedico;
import java.util.List;

public interface IHistorialMedicoDAO extends IGenericoDAO<HistorialMedico> {
    
    /**
     * 
     * 
     * 
     * @return 
     */
    public List<HistorialMedico> getAllHistoriales();
    
    /**
     * 
     * 
     * 
     * @param idMascota
     * @return 
     */
    public List<HistorialMedico> getHistorialesByIdMascota(int idMascota);
    
    /**
     * 
     * 
     * 
     * @param historial 
     */
    public void guardarHistorial(HistorialMedico historial);
    
    /**
     * 
     * 
     * 
     * @param idHistorial 
     */
    public void eliminarHistorial(int idHistorial);
    
}
