/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modern.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author sergeykargopolov
 */
public class HibernateUtils {
    private static final SessionFactory sessionFactory;

    static {
        Configuration conf = new Configuration();
        conf.configure();

        try {
            System.out.println("Try To new Session");
            sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println("new Session Success");
        } catch (HibernateException e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

