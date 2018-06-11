package se.codepool.ef.cfg;

import java.io.File;
import java.lang.instrument.IllegalClassFormatException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolverHolder;
import javax.persistence.spi.PersistenceUnitInfo;
import org.eclipse.persistence.internal.jpa.config.persistenceunit.PersistenceUnitImpl;

import se.codepool.ef.utility.*;

public abstract class Configuration extends ConnectionString {
	private final static Set<String> entities = new HashSet<String>();
	
	private Properties properties;
	private volatile static EntityManagerFactory factory;

	protected Configuration(File xml, Class<?> type) throws IllegalClassFormatException {
		this(type, xml);
	}
	
	protected Configuration(Properties prop, Class<?> type) throws IllegalClassFormatException {
		this(type, prop);
	}

	private Configuration(Class<?> type, Object config) throws IllegalClassFormatException {
		super(config);
		if(!type.isAnnotationPresent(Entity.class)) {
			String classPath = type.getName();
			String message = classPath + " is unknown entity. Make sure you decorate " + classPath + " with @Entity annotation.";
			throw new IllegalClassFormatException(message);
		}
		if(properties == null)
			this.properties = new Properties();
		this.properties.put("javax.persistence.schema-generation.database.action", "create");
		entities.add(type.getName());
	}
	
	private EntityManagerFactory getInstance() {
	    if (factory == null) {
	        synchronized (EntityManagerFactory.class) {
	            if (factory == null) {
	            	PersistenceUnitInfo info = new PersistenceUnitImpl("ef").getPersistenceUnitInfo();
	            	
	            	for(String entity : entities)
	            		info.getManagedClassNames().add(entity);
	            	
	            	PersistenceProvider provider = PersistenceProviderResolverHolder
	            			.getPersistenceProviderResolver()
		            		.getPersistenceProviders()
		            		.get(0);
	            	if(provider != null) {
	            		factory = provider.createContainerEntityManagerFactory(info, properties);
	            	}
	            }
	        }
	    }
	    return factory;
	}


	public EntityManager getEntityManager() {
		return this.getInstance().createEntityManager();
	}	
	
	public Configuration setUsername(String username) {
		this.properties.put("javax.persistence.jdbc.user", username);
		return this;
	}

	public Configuration setPassword(String password) {
		this.properties.put("javax.persistence.jdbc.password", password);
		return this;
	}
	
	public Configuration setURL(String url) {
		this.properties.put("javax.persistence.jdbc.url", url);
		return this;
	}
	
	public Configuration setDriver(String driver) {
		this.properties.put("javax.persistence.jdbc.driver", driver);
		return this;
	}
	
	
}
