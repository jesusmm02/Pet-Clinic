package es.petclinic.DAO;

import es.petclinic.beans.Cliente;
import java.util.List;

public interface IClienteDAO extends IGenericoDAO<Cliente> {
    
    /**
     * 
     * Inserta un cliente en la base de datos.
     * 
     * @param cliente Objeto cliente que se inserta en la base de datos.
     */
    public void insertOrUpdateCliente(Cliente cliente);
    
    /**
     * 
     * Obtiene un cliente por el idUsuario.
     * 
     * @param idUsuario IdUsuario del cliente que se quiere buscar.
     * @return Objeto Cliente que tiene el IdUsuario pasado por parámetro.
     */
    public Cliente getByIdUsuario(int idUsuario);
    
    /**
     * 
     * Recupera una lista de clientes junto con sus mascotas asociadas.
     * 
     * @return Lista de objetos Cliente con sus listas de mascotas.
     */
    public List<Cliente> obtenerClientesConMascotas();
    
}
