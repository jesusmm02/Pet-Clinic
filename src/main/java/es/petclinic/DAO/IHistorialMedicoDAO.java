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
     * @param idDueño
     * @return 
     */
    public List<HistorialMedico> getHistorialesByDueño(int idDueño);
    
    /**
     * 
     * 
     * 
     * @param especie
     * @return 
     */
    public List<HistorialMedico> getHistorialesByEspecie(String especie);
    
    /**
     * 
     * 
     * 
     * @param raza
     * @return 
     */
    public List<HistorialMedico> getHistorialesByRaza(String raza);
    
    /**
     * 
     * 
     * 
     * @param idDueño
     * @param especie
     * @return 
     */
    public List<HistorialMedico> getHistorialesByDueñoAndEspecie(int idDueño, String especie);
    
    /**
     * 
     * 
     * 
     * @param idDueño
     * @param raza
     * @return 
     */
    public List<HistorialMedico> getHistorialesByDueñoAndRaza(int idDueño, String raza);
    
    /**
     * 
     * 
     * 
     * @param especie
     * @param raza
     * @return 
     */
    public List<HistorialMedico> getHistorialesByEspecieAndRaza(String especie, String raza);
    
    /**
     * 
     * 
     * 
     * @param idDueño
     * @param especie
     * @param raza
     * @return 
     */
    public List<HistorialMedico> getHistorialesByDueñoAndEspecieAndRaza(int idDueño, String especie, String raza);
    
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
