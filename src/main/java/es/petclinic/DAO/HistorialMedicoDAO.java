package es.petclinic.DAO;

import es.petclinic.beans.HistorialMedico;
import es.petclinic.persistence.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class HistorialMedicoDAO extends GenericoDAO<HistorialMedico> implements IHistorialMedicoDAO {
    
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
    public List<HistorialMedico> getAllHistoriales() {
        List<HistorialMedico> historiales = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query<HistorialMedico> query = session.createQuery("FROM HistorialMedico", HistorialMedico.class);
            historiales = query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return historiales;
    }
    
    @Override
    public List<HistorialMedico> getHistorialesByIdMascota(int idMascota) {
        List<HistorialMedico> historiales = null;

        try {
            startTransaction();
            Query<HistorialMedico> query = session.createQuery("FROM HistorialMedico h WHERE h.mascota.id = :idMascota", HistorialMedico.class);
            query.setParameter("idMascota", idMascota);
            historiales = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return historiales;
    }
 
    @Override
    public void guardarHistorial(HistorialMedico historial) {
        try {
            startTransaction();

            // Guardar el historial
            session.save(historial);

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }
    
    @Override
    public void eliminarHistorial(int idHistorial) {
        try {
            startTransaction();

            // Buscar el historial
            HistorialMedico historial = session.get(HistorialMedico.class, idHistorial);

            if (historial != null) {
                session.delete(historial);
            }

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }
    
}
