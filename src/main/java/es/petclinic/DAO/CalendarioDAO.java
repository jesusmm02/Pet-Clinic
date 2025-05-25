package es.petclinic.DAO;

import es.petclinic.beans.Calendario;

import es.petclinic.persistence.HibernateUtil;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class CalendarioDAO extends GenericoDAO<Calendario> implements ICalendarioDAO {
    
    protected Session session;
    protected Transaction transaction;

    @Override
    protected void startTransaction() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    @Override
    protected void endTransaction() {
        if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
            session.getTransaction().commit();
        }
        session.close();
    }

    @Override
    protected void handleException(HibernateException he) throws HibernateException {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
        throw he;
    }
    
    @Override
    public void actualizarCalendario(Calendario calendario) {
        try {
            startTransaction();
            session.update(calendario);
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }    
    
    @Override
    public List<Calendario> getHorariosDisponiblesPorFechaOrdenados(LocalDate fecha) {
        try {
            startTransaction();
            String hql = "FROM Calendario c WHERE c.fecha = :fecha ORDER BY c.horaInicio ASC";
            List<Calendario> resultados = session.createQuery(hql, Calendario.class)
                .setParameter("fecha", fecha)
                .list();
            endTransaction();
            return resultados;
        } catch (HibernateException he) {
            handleException(he);
            return new ArrayList<>();
        }
    }
    
    @Override
    public List<Calendario> getHorariosDisponiblesPorFechaYServicio(LocalDate fecha, int idServicio) {
        try {
            startTransaction();

            // Obtiene la duración en minutos del servicio
            String hqlDuracion = "SELECT s.duracion FROM Servicio s WHERE s.id = :idServicio";
            Integer duracionServicio = session.createQuery(hqlDuracion, Integer.class)
                .setParameter("idServicio", idServicio)
                .uniqueResult();

            if (duracionServicio == null) {
                endTransaction();
                return new ArrayList<>();
            }

            int bloquesNecesarios = duracionServicio / 15; // bloques de 15 minutos

            // Obtener todos los bloques disponibles ese día ordenados
            String hql = "FROM Calendario c WHERE c.fecha = :fecha AND c.disponible = true ORDER BY c.horaInicio ASC";
            List<Calendario> bloquesDisponibles = session.createQuery(hql, Calendario.class)
                .setParameter("fecha", fecha)
                .list();

            endTransaction();

            // Filtrado en Java para devolver solo horarios que tienen suficientes bloques consecutivos libres
            List<Calendario> resultados = new ArrayList<>();

            for (int i = 0; i <= bloquesDisponibles.size() - bloquesNecesarios; i++) {
                boolean consecutivos = true;
                for (int j = 0; j < bloquesNecesarios - 1; j++) {
                    Calendario actual = bloquesDisponibles.get(i + j);
                    Calendario siguiente = bloquesDisponibles.get(i + j + 1);

                    // Comprobar que el siguiente empieza justo 15 minutos después del actual
                    if (!siguiente.getHoraInicio().equals(actual.getHoraInicio().plusMinutes(15))) {
                        consecutivos = false;
                        break;
                    }
                }
                if (consecutivos) {
                    resultados.add(bloquesDisponibles.get(i));
                }
            }

            return resultados;

        } catch (HibernateException he) {
            handleException(he);
            return new ArrayList<>();
        }
    }

    @Override
    public Calendario getCalendarioById(int idHorario) {
        Calendario calendario = null;
        try {
            startTransaction();
            calendario = session.get(Calendario.class, idHorario);
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return calendario;
    }
    
    @Override
    public List<Calendario> getHorariosByFecha(LocalDate fecha) {
        List<Calendario> horarios = null;

        try {
            startTransaction();
            Query<Calendario> query = session.createQuery("FROM Calendario c WHERE c.fecha = :fecha AND c.disponible = true", Calendario.class);
            query.setParameter("fecha", fecha);
            horarios = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }

        return horarios;
    }
    
    @Override
    public void liberarBloquesDeCita(LocalDate fecha, LocalTime horaInicio, int duracionEnMinutos) {
        try {
            startTransaction();

            int bloques = duracionEnMinutos / 15;

            for (int i = 0; i < bloques; i++) {
                LocalTime hora = horaInicio.plusMinutes(15 * i);
                String hql = "UPDATE Calendario c SET c.disponible = true " +
                             "WHERE c.fecha = :fecha AND c.horaInicio = :hora";
                session.createQuery(hql)
                       .setParameter("fecha", fecha)
                       .setParameter("hora", hora)
                       .executeUpdate();
            }

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }
    
}
