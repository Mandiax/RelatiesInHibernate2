package util;

import model.Bedrijf;
import model.Project;
import model.Persoon;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	  
    @SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
          //return new Configuration().configure().buildSessionFactory();
        	
           //return new AnnotationConfiguration().configure()
        	//		.addAnnotatedClass(Bedrijf.class)
        	//		.addAnnotatedClass(Project.class)
        	//		.addAnnotatedClass(Persoon.class)
        	//		.buildSessionFactory();
        	
        	Configuration config = new Configuration();
            config.configure();
            config.addAnnotatedClass(Bedrijf.class);
            config.addAnnotatedClass(Persoon.class);
            config.addAnnotatedClass(Project.class);
            
            ServiceRegistry registry = new StandardServiceRegistryBuilder()
            .applySettings(config.getProperties())
            .build();
                        
            return config.buildSessionFactory(registry);
        	
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
  
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
  
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
