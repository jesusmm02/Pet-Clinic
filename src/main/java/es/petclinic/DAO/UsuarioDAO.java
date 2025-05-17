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
    public Usuario getById(int idUsuario) {
        Usuario usuario = null;
        try {
            startTransaction();

            Query<Usuario> query = session.createQuery("FROM Usuario u WHERE u.id = :idUsuario", Usuario.class);
            query.setParameter("idUsuario", idUsuario);

            usuario = query.uniqueResult(); // Obtenemos el usuario

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return usuario;
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
    
    @Override
    public Usuario obtenerPorNumIdentificacion(String numIdentificacion) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Usuario usuario = null;

        try {
            String hql = "FROM Usuario WHERE numIdentificacion = :numIdentificacion";
            Query query = session.createQuery(hql);
            query.setParameter("numIdentificacion", numIdentificacion);
            
            // Ejecutar la consulta
            usuario = (Usuario) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return usuario;
    }

    @Override
    public void insertOrUpdateUsuario(Usuario usuario) {
        try {
            startTransaction();

            // Verificar si ya existe un usuario con ese ID (o podrías usar email, NIF, etc.)
            Usuario existingUsuario = session.get(Usuario.class, usuario.getId());

            if (existingUsuario != null) {
                session.merge(usuario); // Actualiza el usuario existente
            } else {
                session.save(usuario); // Inserta nuevo usuario
            }

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }

}
