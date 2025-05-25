package es.petclinic.DAO;

import es.petclinic.beans.Cita;
import java.time.LocalDate;
import java.util.List;

public interface ICitaDAO extends IGenericoDAO<Cita> {

    /**
     * 
     * Insertar una nueva cita en la base de datos.
     * 
     * @param cita La cita a guardar.
     */
    public void insertarCita(Cita cita);
     
    /**
     * 
     * Obtiene una cita por su ID.
     * 
     * @param idCita Identificador de la cita.
     * @return Objeto Cita o null si no se encuentra.
     */
    public Cita getCitaById(int idCita);
    
    /**
     * 
     * Obtiene todas las citas de un veterinario para una fecha concreta.
     * 
     * @param idVeterinario ID del veterinario.
     * @param fecha Fecha a consultar.
     * @return Lista de citas asignadas al veterinario en ese día.
     */
    public List<Cita> getCitasByVeterinarioYFecha(int idVeterinario, LocalDate fecha);
    
    /**
     * 
     * Devuelve todas las citas de un cliente.
     * 
     * @param idCliente ID del cliente.
     * @return Lista de citas asociadas a ese cliente.
     */
    public List<Cita> getCitasByCliente(int idCliente);
    
    /**
     * 
     * Devuelve las citas asociadas a una mascota.
     * 
     * @param idMascota ID de la mascota.
     * @return Lista de citas para la mascota.
     */
    public List<Cita> getCitasByMascota(int idMascota);
    
    /**
     * 
     * Devuelve las citas paa un servicio concreto.
     * 
     * @param idServicio ID del servicio.
     * @return Lista de citas asociadas al servicio.
     */
    public List<Cita> getCitasByServicio(int idServicio);
    
    /**
     * 
     * Devuelve citas para una combinación de mascota y servicio.
     * 
     * @param idMascota ID de la mascota.
     * @param idServicio ID del servicio.
     * @return Lista de citas que coinciden con ambos criterios.
     */
    public List<Cita> getCitasByMascotaAndServicio(int idMascota, int idServicio);
    
    /**
     * 
     * Permite filtrar citas de un cliente por mascota, servicio y/o fecha.
     * 
     * @param idCliente ID del cliente.
     * @param mascotaId ID de la mascota.
     * @param servicioId ID del servicio
     * @param fecha Fecha específica.
     * @return Lista de citas que cumplen los criterios.
     */
    public List<Cita> filtrarCitasCliente(int idCliente, Integer mascotaId, Integer servicioId, LocalDate fecha);
    
    /**
     * 
     * Recupera todas las citas registradas en la base de datos.
     * 
     * @return Lista completa de citas.
     */
    public List<Cita> getAllCitas();
    
    /**
     * 
     * Elimina una cita de la base de datos dado su identificador.
     * 
     * @param idCita ID de la cita a eliminar.
     */
    public void eliminarCita(int idCita);
    
}
