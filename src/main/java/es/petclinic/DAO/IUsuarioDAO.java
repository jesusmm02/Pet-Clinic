package es.petclinic.DAO;

import es.petclinic.beans.Usuario;

public interface IUsuarioDAO extends IGenericoDAO<Usuario> {
    

    /**
     * 
     * Obtiene un usuario por su ID.
     * 
     * @param idUsuario ID del usuario que se desea recuperar.
     * @return El objeto Usuario correspondiente si existe, o null en caso contrario.
     */
    public Usuario getById(int idUsuario);
    
    /**
     * 
     * Obtiene un usuario con rol VETERINARIO.
     * 
     * @return El usuario con rol de veterinario, o null si no existe.
     */
    public Usuario getVeterinario();
    
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
     * Recupera un usuario a partir de su número de identificación.
     * 
     * @param numIdentificacion Número de identificación del usuario.
     * @return El objeto Usuario correspondiente o null si no existe.
     */
    public Usuario obtenerPorNumIdentificacion(String numIdentificacion);
    
    /**
     * 
     * Inserta un nuevo usuario o actualiza uno existente si ya está registrado por su ID.
     * 
     * @param usuario Objeto Usuario que se desea insertar o actualizar.
     */
    public void insertOrUpdateUsuario(Usuario usuario);
    
}
