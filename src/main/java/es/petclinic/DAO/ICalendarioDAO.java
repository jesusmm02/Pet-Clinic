package es.petclinic.DAO;

import es.petclinic.beans.Calendario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ICalendarioDAO extends IGenericoDAO<Calendario> {
    
    /**
     * 
     * Actualiza un objeto Calendario en la base de datos.
     * 
     * @param calendario El calendario a actualizar.
     */
    public void actualizarCalendario(Calendario calendario);
    
    /**
     * 
     * Obtiene todos los bloques horarios de un día concreto. ordenados por hora de inicio.
     * 
     * @param fecha La fecha deseada.
     * @return Lista de objetos Calendario para esa fecha.
     */
    public List<Calendario> getHorariosDisponiblesPorFechaOrdenados(LocalDate fecha);
    
    /**
     * 
     * Devuelve los bloques de inicio disponibles en una fecha concreta
     * que permiten cubrir la duración de un servicio (bloques consecutivos).
     * 
     * @param fecha La fecha deseada.
     * @param idServicio Identificador del servicio.
     * @return Lista de bloques de inicio válidos para ese servicio.
     */
    public List<Calendario> getHorariosDisponiblesPorFechaYServicio(LocalDate fecha, int idServicio);
    
    /**
     * 
     * Busca un objeto Calendario por su ID.
     * 
     * @param idHorario Identificador del calendario.
     * @return El objeto Calendario encontrado o null.
     */
    public Calendario getCalendarioById(int idHorario);
    
    /**
     * 
     * Devuelve los bloques horarios disponibles (no ocupados) en una fecha concreta.
     * 
     * @param fecha La fecha deseada.
     * @return Lista de bloques Calendario disponibles.
     */
    public List<Calendario> getHorariosByFecha(LocalDate fecha);
    
    /**
     * 
     * Libera (marca como disponibles) los bloques usados por una cita.
     * 
     * @param fecha Fecha de la cita
     * @param horaInicio Hora de inicio de la cita
     * @param duracionEnMinutos Duración del servicio en minutos
     */
    public void liberarBloquesDeCita(LocalDate fecha, LocalTime horaInicio, int duracionEnMinutos);
    
}
