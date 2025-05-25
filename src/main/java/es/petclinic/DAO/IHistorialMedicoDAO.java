package es.petclinic.DAO;

import es.petclinic.beans.HistorialMedico;
import java.util.List;

public interface IHistorialMedicoDAO extends IGenericoDAO<HistorialMedico> {
    
    /**
     * 
     * Recupera todos los historiales médicos registrados en la base de datos.
     * 
     * @return Lista de todos los historiales médicos existentes.
     */
    public List<HistorialMedico> getAllHistoriales();
    
    /**
     * 
     * Recupera todos los historiales médicos de una mascota específica por su ID.
     * 
     * @param idMascota ID de la mascota.
     * @return Lista de historiales médicos asociados a esa mascota.
     */
    public List<HistorialMedico> getHistorialesByIdMascota(int idMascota);
    
    /**
     * 
     * Recupera todos los historiales médicos de un cliente determinado.
     * 
     * @param idDueño ID del cliente propietario de las mascotas.
     * @return Lista de historiales médicos pertenecientes a mascotas del cliente.
     */
    public List<HistorialMedico> getHistorialesByDueño(int idDueño);
    
    /**
     * 
     * Recupera los historiales médicos filtrados por especie.
     * 
     * @param especie Nombre de la especie.
     * @return Lista de historiales médicos correspondientes a esa especie.
     */
    public List<HistorialMedico> getHistorialesByEspecie(String especie);
    
    /**
     * 
     * Recupera los historiales médicos filtrados por raza.
     * 
     * @param raza Nombre de la raza.
     * @return Lista de historiales médicos de mascotas de esa raza.
     */
    public List<HistorialMedico> getHistorialesByRaza(String raza);
    
    /**
     * 
     * Recupera historiales médicos filtrados por ID del dueño y especie.
     * 
     * @param idDueño ID del cliente.
     * @param especie Especie de la mascota.
     * @return Lista filtrada por dueño y especie.
     */
    public List<HistorialMedico> getHistorialesByDueñoAndEspecie(int idDueño, String especie);
    
    /**
     * 
     * Recupera historiales médicos filtrados por su ID del dueño y raza.
     * 
     * @param idDueño ID del cliente.
     * @param raza Raza de la mascota.
     * @return Lista filtrada por dueño y raza.
     */
    public List<HistorialMedico> getHistorialesByDueñoAndRaza(int idDueño, String raza);
    
    /**
     * 
     * Recupera historiales médicos filtrados por especie y raza.
     * 
     * @param especie Especie de la mascota.
     * @param raza Raza de la mascota.
     * @return Lista filtrada por especie y raza.
     */
    public List<HistorialMedico> getHistorialesByEspecieAndRaza(String especie, String raza);
    
    /**
     * 
     * Recupera historiales médicos por dueño, especie y raza.
     * 
     * @param idDueño ID del cliente.
     * @param especie Especie de la mascota.
     * @param raza Raza de la mascota.
     * @return Lista filtrada por todos los parámetros.
     */
    public List<HistorialMedico> getHistorialesByDueñoAndEspecieAndRaza(int idDueño, String especie, String raza);
    
    /**
     * 
     * Guarda un nuevo historial médico en la base de datos.
     * 
     * @param historial Objeto de tipo HistorialMedico que se desea guardar.
     */
    public void guardarHistorial(HistorialMedico historial);
    
    /**
     * 
     * Elimina un historial médico existente a partir de su ID.
     * 
     * @param idHistorial ID del historial médico a eliminar.
     */
    public void eliminarHistorial(int idHistorial);
    
}
