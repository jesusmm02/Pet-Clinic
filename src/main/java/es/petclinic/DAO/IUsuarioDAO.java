package es.petclinic.DAO;

import es.petclinic.beans.Usuario;

public interface IUsuarioDAO extends IGenericoDAO<Usuario> {
    
    /**
     * 
     * Obtiene un objeto Usuario cuyo email sea el pasado por parámetro.
     * 
     * @param email Email a buscar.
     * @return Objeto Usuario que tiene ese email.
     */
    public Usuario obtenerPorEmail(String email);
    
    /**
     * 
     * 
     * 
     * @param numIdentificacion
     * @return 
     */
    public Usuario obtenerPorNumIdentificacion(String numIdentificacion);
    
    /**
     * 
     * 
     * 
     * @param usuario 
     */
    public void insertOrUpdateUsuario(Usuario usuario);
    
}
