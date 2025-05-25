package es.petclinic.DAO;

import es.petclinic.beans.Mascota;
import java.util.List;

public interface IMascotaDAO extends IGenericoDAO<Mascota> {
    
    /**
     * 
     * Recupera una mascota por su ID.
     * 
     * @param id ID de la mascota.
     * @return Objeto Mascota correspondiente, o null si no existe.
     */
    public Mascota getById(int id);
    
    /**
     * 
     * Obtiene todas las mascotas pertenecientes a un cliente.
     * 
     * @param idCliente ID del cliente.
     * @return Lista de mascotas asociadas al cliente.
     */
    public List<Mascota> getMascotasByIdCliente(int idCliente);
    
    /**
     * 
     * Devuelve una lista con todas las especies distintas registradas en la base de datos.
     * 
     * @return Lista de nombres de especies (sin duplicados).
     */
    public List<String> obtenerEspecies();
    
    /**
     * 
     * Devuelve una lista con todas las razas distintas registradas en la base de datos.
     * 
     * @return Devuelve una lista con todas las razas distintas registradas en la base de datos.
     */
    public List<String> obtenerRazas();
    
    /**
     * 
     * Recupera todas las mascotas almacenadas en la base de datos.
     * 
     * @return Lista de todas las mascotas.
     */
    public List<Mascota> getAllMascotas();
    
    /**
     * 
     * Inserta una nueva mascota en la base de datos.
     * 
     * @param mascota Objeto Mascota que se desea guardar.
     */
    public void insertarMascota(Mascota mascota);
    
    /**
     * 
     * Actualiza la información de una mascota existente.
     * 
     * @param mascota Objeto Mascota con los datos actualizados.
     */
    public void actualizarMascota(Mascota mascota);
    
    /**
     * 
     * Elimina una mascota por su ID. También elimina los historiales médicos y citas asociadas.
     * Si la mascota tiene citas, se libera el horario del calendario correspondiente.
     * 
     * @param id ID de la mascota a eliminar.
     */
    public void eliminarMascota(int id);
    
}
