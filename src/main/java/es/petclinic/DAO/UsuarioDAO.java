package es.petclinic.DAO;

import es.petclinic.beans.Usuario;
import es.petclinic.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class UsuarioDAO extends GenericoDAO<Usuario> implements IUsuarioDAO {

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
    public Usuario obtenerPorEmail(String email) {
        Usuario usuario = null;
        try {
            this.startTransaction();
            Query<Usuario> query = session.createQuery("FROM Usuario u WHERE u.email = :email", Usuario.class);
            query.setParameter("email", email);
            usuario = query.uniqueResult();
            this.endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return usuario;
    }

}
