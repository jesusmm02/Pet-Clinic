package es.petclinic.DAO;

import es.petclinic.beans.Servicio;
import es.petclinic.persistence.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class ServicioDAO extends GenericoDAO<Servicio> implements IServicioDAO {
    
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
    public List<Servicio> obtenerServicios() {
        List<Servicio> listaServicios = null;
        try {
            this.startTransaction();

            Query<Servicio> query = session.createQuery("FROM Servicio", Servicio.class);
            listaServicios = query.getResultList();

            this.endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return listaServicios;
    }

    
}
