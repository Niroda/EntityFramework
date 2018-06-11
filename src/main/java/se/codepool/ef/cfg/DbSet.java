package se.codepool.ef.cfg;

import java.io.File;
import java.lang.instrument.IllegalClassFormatException;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.naming.directory.InvalidAttributesException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import se.codepool.ef.enums.ConfigType;
import se.codepool.ef.utility.ConnectionString;
import se.codepool.ef.utility.PredicateBuilder;


public class DbSet<T> extends Configuration {
	private EntityManager entityManager;
	private Class<T> type;
	private CriteriaBuilder criteriaBuilder;
	private CriteriaQuery<T> queryBuilder;
	private TypedQuery<T> typedQuery;
	private Root<T> root;
//	private ConfigType configType;
//	private File xml;
//	private Properties properties;

	public DbSet(Class<T> type, Properties prop) throws IllegalClassFormatException {
		super(prop, type);
//		this.type = type;
//		this.configType = ConfigType.Properties;
		this.buildEntityManager();
	}
	public DbSet(Class<T> type, File xml) throws IllegalClassFormatException {
		super(xml, type);
//		this.type = type;
//		this.configType = ConfigType.XML;
		this.buildEntityManager();
	}
	
	
	/*
	 * Lazy operations
	 * 
	 */
	
	
	
	
	/*
	 * Terminal operations
	 * 
	 */
	
	public <U> Collection<T> where(Function<U, T> condition) {
		this.queryBuilder = this.queryBuilder.where();
		
		
		
		return null;
	}
	
	public T firstOrDefault() {
		
		return null;
	}
	
	public T firstOrDefault(Predicate<T> condition) {
		
		return null;
	}
	
	/*
	 * Private functions
	 */
	
	private EntityManager createEntityManager() {
		// return entity manager instance
		return this.setDriver(this.getDriver())
								.setPassword(this.getPassword())
								.setURL(this.getUrl())
								.setUsername(this.getUsername())
								.getEntityManager();
	}
	
	private TypedQuery<T> createTypedQuery(PredicateBuilder<T> pre) {
		// building criteria query using lambda expression
		CriteriaQuery<T> query = this.queryBuilder
									.select(this.root)
									.where(
											pre.build(
													this.criteriaBuilder, 
													this.root
													)
										);
		// for type safety, create typed query
		return this.entityManager.createQuery(query);
	}
	
	private void buildCriteria() {
		this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
		this.queryBuilder = this.criteriaBuilder.createQuery(this.type);
		this.root = this.queryBuilder.from(this.type);
	}

	private void buildEntityManager() {
		this.entityManager = this.createEntityManager();
		this.buildCriteria();
	}
}
