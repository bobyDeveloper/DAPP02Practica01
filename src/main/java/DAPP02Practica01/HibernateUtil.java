package DAPP02Practica01;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            SESSION_FACTORY = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SeccionFactory cread fallido");
            throw new ExceptionInInitializerError(ex);

        }
    }
    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
}
    
}
