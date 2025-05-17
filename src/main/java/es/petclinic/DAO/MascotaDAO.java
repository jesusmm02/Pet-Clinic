package es.petclinic.DAO;

import es.petclinic.beans.HistorialMedico;
import es.petclinic.beans.Mascota;
import es.petclinic.persistence.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class MascotaDAO extends GenericoDAO<Mascota> implements IMascotaDAO {

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
    public Mascota getById(int id) {
        Mascota mascota = null;
        try {
            startTransaction();

            mascota = session.get(Mascota.class, id);

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return mascota;
    }

    @Override
    public List<Mascota> getMascotasByIdCliente(int idCliente) {
        List<Mascota> mascotas = new ArrayList<>();
        try {
            startTransaction();

            Query<Mascota> query = session.createQuery(
                    "FROM Mascota m WHERE m.propietario.id = :idCliente", Mascota.class
            );
            query.setParameter("idCliente", idCliente);

            mascotas = query.getResultList();

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return mascotas;
    }
    
    @Override
    public List<String> obtenerEspecies() {
        List<String> especies = new ArrayList<>();
        try {
            startTransaction();

            // HQL para obtener las especies sin duplicados
            Query<String> query = session.createQuery("SELECT DISTINCT m.especie FROM Mascota m ORDER BY m.especie ASC", String.class);

            especies = query.getResultList();

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return especies;
    }

    @Override
    public List<String> obtenerRazas() {
        List<String> razas = new ArrayList<>();
        try {
            startTransaction();

            // HQL para obtener las razas sin duplicados
            Query<String> query = session.createQuery("SELECT DISTINCT m.raza FROM Mascota m ORDER BY m.raza ASC", String.class);

            razas = query.getResultList();

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return razas;
    }
    
    @Override
    public List<Mascota> getAllMascotas() {
        List<Mascota> mascotas = null;

        try {
            startTransaction();

            // HQL para obtener todas las mascotas
            Query<Mascota> query = session.createQuery("FROM Mascota", Mascota.class);
            mascotas = query.list();

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }

        return mascotas;
    }


    @Override
    public void insertarMascota(Mascota mascota) {
        try {
            startTransaction();
            session.save(mascota);
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }
    
    @Override
    public void actualizarMascota(Mascota mascota) {
        try {
            startTransaction();
            session.update(mascota);
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
            throw he;
        }
    }

    @Override
    public void eliminarMascota(int id) {
        try {
            startTransaction();

            // Buscar la mascota antes de eliminarla
            Mascota mascota = session.get(Mascota.class, id);

            if (mascota != null) {
                // Obtener todos los historiales médicos asociados a la mascota
                Query<HistorialMedico> query = session.createQuery("FROM HistorialMedico h WHERE h.mascota.id = :idMascota", HistorialMedico.class);
                query.setParameter("idMascota", id);
                List<HistorialMedico> historiales = query.list();

                // Eliminar todos los historiales médicos de la mascota
                for (HistorialMedico historial : historiales) {
                    session.delete(historial);
                }

                // Eliminar la mascota
                session.delete(mascota);
            }

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }


}
