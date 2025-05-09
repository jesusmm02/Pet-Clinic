package es.petclinic.DAO;

import es.petclinic.beans.Cliente;
import es.petclinic.persistence.HibernateUtil;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class ClienteDAO extends GenericoDAO<Cliente> implements IClienteDAO {

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
    public void insertOrUpdateCliente(Cliente cliente) {
        try {
            startTransaction();

            // Buscar si ya existe un cliente con ese ID
            Query<Cliente> query = session.createQuery("FROM Cliente c WHERE c.id = :idUsuario", Cliente.class);
            query.setParameter("idUsuario", cliente.getId());
            Cliente existente = query.uniqueResult();

            if (existente != null) {
                // Reutilizamos el objeto existente ya cargado en la sesión
                existente.setGenero(cliente.getGenero());
                existente.setFechaNacimiento(cliente.getFechaNacimiento());
                // otros campos si hicieran falta

                session.merge(existente);
            } else {
                session.save(cliente);
            }

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
    }

    @Override
    public Cliente getByIdUsuario(int idUsuario) {
        Cliente cliente = null;
        try {
            this.startTransaction();

            Query<Cliente> query = session.createQuery("FROM Cliente c WHERE c.id = :idUsuario", Cliente.class);
            query.setParameter("idUsuario", idUsuario);
            cliente = query.uniqueResult();

            this.endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return cliente;
    }

    @Override
    public Cliente getByDNI(String dni) {
        Cliente cliente = null;
        try {
            this.startTransaction();

            // Consulta para buscar al cliente por el DNI
            Query<Cliente> query = session.createQuery("FROM Cliente c WHERE c.dni = :dni", Cliente.class);
            query.setParameter("dni", dni);
            cliente = query.uniqueResult(); // Obtiene un único cliente, o null si no existe

            this.endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return cliente;
    }

    @Override
    public List<Cliente> obtenerClientesConMascotas() {
        List<Cliente> listaClientes = null;
        try {
            this.startTransaction();

            Query<Cliente> query = session.createQuery(
                "SELECT DISTINCT c FROM Cliente c LEFT JOIN FETCH c.mascotas", 
                Cliente.class
            );

            listaClientes = query.getResultList();

            this.endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
        return listaClientes;
    }

}
