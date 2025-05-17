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

        try {
            startTransaction();  // Iniciar la transacción

            Query<HistorialMedico> query = session.createQuery("FROM HistorialMedico", HistorialMedico.class);
            historiales = query.list();

            endTransaction();  // Finalizar la transacción
        } catch (HibernateException he) {
            handleException(he);  // Manejar excepciones
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
    public List<HistorialMedico> getHistorialesByDueño(int idDueño) {
        List<HistorialMedico> historiales = null;

        try {
            startTransaction();
            Query<HistorialMedico> query = session.createQuery(
                "FROM HistorialMedico h WHERE h.mascota.propietario.id = :idDueño", 
                HistorialMedico.class
            );
            query.setParameter("idDueño", idDueño);
            historiales = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return historiales;
    }


    @Override
    public List<HistorialMedico> getHistorialesByEspecie(String especie) {
        List<HistorialMedico> historiales = null;

        try {
            startTransaction();
            Query<HistorialMedico> query = session.createQuery(
                    "FROM HistorialMedico h WHERE h.mascota.especie = :especie",
                    HistorialMedico.class
            );
            query.setParameter("especie", especie);
            historiales = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return historiales;
    }

    @Override
    public List<HistorialMedico> getHistorialesByRaza(String raza) {
        List<HistorialMedico> historiales = null;

        try {
            startTransaction();
            Query<HistorialMedico> query = session.createQuery(
                    "FROM HistorialMedico h WHERE h.mascota.raza = :raza",
                    HistorialMedico.class
            );
            query.setParameter("raza", raza);
            historiales = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return historiales;
    }
    
    @Override
    public List<HistorialMedico> getHistorialesByDueñoAndEspecie(int idDueño, String especie) {
        List<HistorialMedico> historiales = null;
        try {
            startTransaction();
            Query<HistorialMedico> query = session.createQuery(
                "FROM HistorialMedico h WHERE h.mascota.propietario.id = :idDueño AND h.mascota.especie = :especie", 
                HistorialMedico.class
            );
            query.setParameter("idDueño", idDueño);
            query.setParameter("especie", especie);
            historiales = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return historiales;
    }
    
    @Override
    public List<HistorialMedico> getHistorialesByDueñoAndRaza(int idDueño, String raza) {
        List<HistorialMedico> historiales = null;
        try {
            startTransaction();
            Query<HistorialMedico> query = session.createQuery(
                "FROM HistorialMedico h WHERE h.mascota.propietario.id = :idDueño AND h.mascota.raza = :raza", 
                HistorialMedico.class
            );
            query.setParameter("idDueño", idDueño);
            query.setParameter("raza", raza);
            historiales = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return historiales;
    }
    
    @Override
    public List<HistorialMedico> getHistorialesByEspecieAndRaza(String especie, String raza) {
        List<HistorialMedico> historiales = null;
        try {
            startTransaction();
            Query<HistorialMedico> query = session.createQuery(
                "FROM HistorialMedico h WHERE h.mascota.especie = :especie AND h.mascota.raza = :raza", 
                HistorialMedico.class
            );
            query.setParameter("especie", especie);
            query.setParameter("raza", raza);
            historiales = query.list();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return historiales;
    }
    
    @Override
    public List<HistorialMedico> getHistorialesByDueñoAndEspecieAndRaza(int idDueño, String especie, String raza) {
        List<HistorialMedico> historiales = null;
        try {
            startTransaction();
            Query<HistorialMedico> query = session.createQuery(
                "FROM HistorialMedico h WHERE h.mascota.propietario.id = :idDueño AND h.mascota.especie = :especie AND h.mascota.raza = :raza", 
                HistorialMedico.class
            );
            query.setParameter("idDueño", idDueño);
            query.setParameter("especie", especie);
            query.setParameter("raza", raza);
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
