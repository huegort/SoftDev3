package ie.gmit.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory = createFactory();

	private static SessionFactory createFactory() {
		// A SessionFactory is set up once for an application!
				final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
						.configure() // configures settings from hibernate.cfg.xml
						.build();
				try {
					return new MetadataSources( registry ).buildMetadata().buildSessionFactory();
				}
				catch (Exception e) {
					// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
					// so destroy it manually.
					StandardServiceRegistryBuilder.destroy( registry );
					throw new RuntimeException("could not create hibernate Session factory",e);
				}
	}
	
	public static Session getNewSession(){
		return sessionFactory.openSession();
	}
	
	
}
