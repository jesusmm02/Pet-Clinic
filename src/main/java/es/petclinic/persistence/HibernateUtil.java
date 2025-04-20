package es.petclinic.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Clase para tener acceso al objeto session de la clase SessionFactory
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Construcción de la factoría de sesiones que utilizaremos para trabajar con la base de datos
     */
    public static synchronized void buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
                Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
                sessionFactory = metaData.getSessionFactoryBuilder().build();

            } catch (Throwable th) {
                System.err.println("Fallo al crear la factoría de sesiones " + th);
                throw new ExceptionInInitializerError(th);
            }
        }
    }

    /**
     * Obtiene la factoria de sesiones
     * @return La factoría de sesiones
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * Cierra la factoría de sesiones 
     */
    public static void closeSessionFactory() {
        if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
            sessionFactory.close();
            System.out.println("Se cerró la factoria de sesiones");
        } else {
            System.out.println("La factoria de sesiones estaba vacía o cerrada");
        }
    }
}