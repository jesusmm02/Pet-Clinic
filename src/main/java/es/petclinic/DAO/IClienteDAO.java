package es.petclinic.DAO;

import es.petclinic.beans.Cliente;
import java.util.List;

public interface IClienteDAO extends IGenericoDAO<Cliente> {
    
    /**
     * 
     * Inserta un cliente en la BBDD
     * 
     * @param cliente Objeto cliente que se inserta en la BBDD
     */
    public void insertOrUpdateCliente(Cliente cliente);
    
    /**
     * 
     * Obtiene un cliente por el idUsuario
     * 
     * @param idUsuario IdUsuario del cliente que se quiere buscar
     * @return Objeto Cliente que tiene el IdUsuario pasado por parámetro
     */
    public Cliente getByIdUsuario(int idUsuario);
    
    /**
     * 
     * Obtiene un cliente por DNI
     * 
     * @param dni DNI del cliente que se quiere buscar
     * @return Objeto cliente que tiene el DNI pasado por parámetro
     */
    public Cliente getByDNI(String dni);
    
    /**
     * 
     * 
     * 
     * @return 
     */
    public List<Cliente> obtenerClientesConMascotas();
    
}
