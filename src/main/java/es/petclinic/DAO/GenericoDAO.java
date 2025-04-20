package es.petclinic.DAO;

import es.petclinic.persistence.HibernateUtil;
import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class GenericoDAO<T> implements IGenericoDAO<T> {

    protected Session session;
    protected Transaction transaction;

    protected void startTransaction() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    protected void endTransaction() {
        if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
            session.getTransaction().commit();
        }
        session.close();
    }

    protected void handleException(HibernateException he) throws HibernateException {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
        throw he;
    }

    @Override
    public void insertOrUpdate(T objeto) {
        try {
            startTransaction();
            session.saveOrUpdate(objeto);
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }

    @Override
    public <T> List<T> selectAll(Class<T> claseEntidad) {
        List<T> lista = null;
        try {
            startTransaction();
            lista = session.createQuery("FROM " + claseEntidad.getName(), claseEntidad).getResultList();
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return lista;
    }

    @Override
    public <T> T getById(Serializable pk, Class<T> claseEntidad) {
        T objeto = null;
        try {
            startTransaction();
            objeto = session.get(claseEntidad, pk);
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return objeto;
    }

    @Override
    public void delete(T objeto) {
        try {
            startTransaction();
            session.delete(objeto);
            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }
    
}
