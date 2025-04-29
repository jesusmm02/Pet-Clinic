package es.petclinic.DAO;

import es.petclinic.beans.Cliente;
import es.petclinic.persistence.HibernateUtil;

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

            // Verificamos si el cliente ya existe en la base de datos (por email o DNI)
            Query<Cliente> query = session.createQuery("FROM Cliente c WHERE c.email = :email", Cliente.class);
            query.setParameter("email", cliente.getEmail());
            Cliente existingCliente = query.uniqueResult();

            if (existingCliente != null) {
                // Si el cliente ya existe, hacemos una actualización
                cliente.setId(existingCliente.getId());
                session.update(cliente);  // Se actualiza el cliente
            } else {
                // Si no existe, se guarda como nuevo
                session.save(cliente);  // Se guarda el cliente
            }

            endTransaction();
        } catch (HibernateException he) {
            handleException(he);
        }
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

}
