package es.petclinic.DAO;

import java.io.Serializable;
import java.util.List;

public interface IGenericoDAO<T> {
    
    /**
     * Inserta un nuevo objeto en la base de datos o lo actualiza si ya existe.
     *
     * @param objeto Objeto de tipo T que se va a insertar o actualizar.
     */
    public void insertOrUpdate(T objeto);
    
    /**
     * Obtiene todos los registros de una entidad específica de la base de datos.
     *
     * @param <T> Tipo de la entidad.
     * @param claseEntidad Clase de la entidad que se desea obtener.
     * @return Lista de objetos de la entidad especificada.
     */
    public <T> List<T> selectAll(Class<T> claseEntidad);
    
    /**
     * Obtiene un objeto de la base de datos utilizando su clave primaria.
     *
     * @param <T> Tipo de la entidad.
     * @param pk Clave primaria del objeto que se desea recuperar.
     * @param claseEntidad Clase de la entidad del objeto a recuperar.
     * @return Objeto de tipo T correspondiente a la clave primaria especificada, o null si no se encuentra.
     */
    public <T> T getById(Serializable pk, Class<T> claseEntidad);
    
    /**
     * Elimina un objeto de la base de datos.
     *
     * @param objeto Objeto de tipo T que se desea eliminar.
     */
    public void delete(T objeto);
    
}
