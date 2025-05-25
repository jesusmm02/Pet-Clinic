package es.petclinic.DAO;

import es.petclinic.beans.Cita;

import es.petclinic.persistence.HibernateUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class CitaDAO extends GenericoDAO<Cita> implements ICitaDAO {
    
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
    public void insertarCita(Cita cita) {
        try {
            startTransaction();
            session.save(cita);
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }
    
    @Override
    public Cita getCitaById(int idCita) {
        Cita cita = null;
        try {
            startTransaction();
            cita = session.get(Cita.class, idCita);
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return cita;
    }
    
    @Override
    public List<Cita> getCitasByVeterinarioYFecha(int idVeterinario, LocalDate fecha) {
        try {
            startTransaction();
            String hql = "FROM Cita c WHERE c.veterinario.id = :idVet AND c.calendario.fecha = :fecha ORDER BY c.calendario.horaInicio ASC";
            List<Cita> resultados = session.createQuery(hql, Cita.class)
                    .setParameter("idVet", idVeterinario)
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
    public List<Cita> getCitasByCliente(int idCliente) {
        List<Cita> citas = null;
        try {
            startTransaction();
            Query<Cita> query = session.createQuery(
                    "SELECT c FROM Cita c WHERE c.mascota.propietario.id = :idCliente", Cita.class);
            query.setParameter("idCliente", idCliente);
            citas = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return citas;
    }
    
    @Override
    public List<Cita> getCitasByMascota(int idMascota) {
        List<Cita> citas = null;
        try {
            startTransaction();
            Query<Cita> query = session.createQuery(
                "FROM Cita c WHERE c.mascota.id = :idMascota", Cita.class);
            query.setParameter("idMascota", idMascota);
            citas = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return citas;
    }
    
    @Override
    public List<Cita> getCitasByServicio(int idServicio) {
        List<Cita> citas = null;
        try {
            startTransaction();
            Query<Cita> query = session.createQuery(
                "FROM Cita c WHERE c.servicio.id = :idServicio", Cita.class);
            query.setParameter("idServicio", idServicio);
            citas = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return citas;
    }
    
    @Override
    public List<Cita> getCitasByMascotaAndServicio(int idMascota, int idServicio) {
        List<Cita> citas = null;
        try {
            startTransaction();
            Query<Cita> query = session.createQuery(
                "FROM Cita c WHERE c.mascota.id = :idMascota AND c.servicio.id = :idServicio", Cita.class);
            query.setParameter("idMascota", idMascota);
            query.setParameter("idServicio", idServicio);
            citas = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return citas;
    }
    
    @Override
    public List<Cita> filtrarCitasCliente(int idCliente, Integer mascotaId, Integer servicioId, LocalDate fecha) {
        List<Cita> citas = null;
        try {
            startTransaction();

            String hql = "FROM Cita c WHERE c.mascota.propietario.id = :idCliente";
            if (mascotaId != null) hql += " AND c.mascota.id = :mascotaId";
            if (servicioId != null) hql += " AND c.servicio.id = :servicioId";
            if (fecha != null) hql += " AND c.calendario.fecha = :fecha";

            Query<Cita> query = session.createQuery(hql, Cita.class);
            query.setParameter("idCliente", idCliente);
            if (mascotaId != null) query.setParameter("mascotaId", mascotaId);
            if (servicioId != null) query.setParameter("servicioId", servicioId);
            if (fecha != null) query.setParameter("fecha", fecha);

            citas = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return citas;
    }

    
    @Override
    public List<Cita> getAllCitas() {
        List<Cita> citas = null;

        try {
            startTransaction();

            // HQL para obtener todas las mascotas
            Query<Cita> query = session.createQuery("FROM Cita", Cita.class);
            citas = query.list();

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }

        return citas;
    }

    @Override
    public void eliminarCita(int idCita) {
        try {
            startTransaction();
            Cita cita = session.get(Cita.class, idCita);
            if (cita != null) {
                session.delete(cita);
            }
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }  
    
}