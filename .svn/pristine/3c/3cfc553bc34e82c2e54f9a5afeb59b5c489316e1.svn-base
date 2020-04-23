package mx.com.teclo.saicdmx.persistencia.dao.comun;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.jdbc.Work;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDaoHibernate<T extends Serializable> implements BaseDao<T> {

	protected static final Logger log = Logger.getLogger(BaseDaoHibernate.class);
	protected Class<? extends T> clazz;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseDaoHibernate() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		clazz = (Class) pt.getActualTypeArguments()[0];
	}

	public T findOne(final Serializable id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(final String login) {
		return getCurrentSession().createQuery("from " + clazz.getName() + " u where u.login='" + login + "'").list();
	}

	// public void save(final T entity) {
	// getCurrentSession().persist(entity);
	// }

	// @SuppressWarnings("unchecked")
	// public T update(final T entity) {
	// return (T) getCurrentSession().merge(entity);
	// }

	public void delete(final T entity) {
		getCurrentSession().delete(entity);
	}

	public void deleteById(final Serializable id) {
		final T entity = findOne(id);
		delete(entity);
	}

	// / AQUI EMPIEZAN METODOS PARA EL USO DEL FRAMEWORK DE AUDITORIA

	public void rollback() {
		getSession().getTransaction().rollback();
	}

	public void commit() {
		getSession().getTransaction().commit();
	}

	protected Query getNamedQuery(String queryName) {
		return getSession().getNamedQuery(queryName);
	}

	public void clear() {
		getSession().clear();
	}

	public void flush() {
		getSession().flush();
	}

	public void closeSession() {
		try {
			getSession().close();
		} catch (Exception e) {

		}
	}

	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected ClassMetadata getClassMetadata() {
		return getSessionFactory().getClassMetadata(getPersistentClass());
	}

	public T findById(Serializable id) {
		return findById(id, false);
	}
	
	public T findById(Serializable id, boolean lock) {
		T entity;
		if (lock)
			entity = (T) getSession().load(getPersistentClass(), id, LockOptions.UPGRADE);
		else
			entity = (T) getSession().load(getPersistentClass(), id);
		return entity;
	}
	
	public T getById(Serializable id) {
		return (T) getSession().get(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion criterion[]) {
		Criteria criteria = createCriteria();
		Criterion acriterion[];
		int j = (acriterion = criterion).length;
		for (int i = 0; i < j; i++) {
			Criterion c = acriterion[i];
			if (c != null)
				criteria.add(c);
		}

		return (List<T>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion criterion[], List<Order> orders) {
		Criteria criteria = createCriteria();
		Criterion acriterion[];
		int j = (acriterion = criterion).length;
		for (int i = 0; i < j; i++) {
			Criterion c = acriterion[i];
			if (c != null)
				criteria.add(c);
		}

		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order o = iterator.next();
			if (o != null)
				criteria.addOrder(o);
		}

		return (List<T>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String excludeProperty[]) {
		Criteria criteria = createCriteria();
		Example example = Example.create(exampleInstance);
		String as[];
		int j = (as = excludeProperty).length;
		for (int i = 0; i < j; i++) {
			String exclude = as[i];
			example.excludeProperty(exclude);
		}

		criteria.add(example);
		return (List<T>) criteria.list();
	}

	public Class<T> getPersistentClass() {
		return getPersistentClass();
	}

	public T saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public T merge(T entity) {
		getSession().merge(entity);
		return entity;
	}

	public Criteria createCriteria() {
		return getSession().createCriteria(getPersistentClass());
	}

	////////////////////////////////////////////////////////////
	// IMPLEMENTACION DE SAVE Y UPDATE
	////////////////////////////////////////////////////////////
	public Serializable save(final T entity) {
		return getCurrentSession().save(entity);
	}

	public T update(final T entity) {
		getCurrentSession().update(entity);

		return entity;
	}

	@Override
	public String encriptarCampo(final String valorCampo) {
		if (valorCampo == null || valorCampo.trim().equals("")) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		this.getCurrentSession().doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				CallableStatement call = connection.prepareCall("{ ? = call pkg_encripcion.encripta(?) }");
				call.registerOutParameter(1, Types.NVARCHAR); // or whatever it
																// is
				call.setString(2, valorCampo);
				call.execute();
				sb.append(call.getString(1)); // propagate this back to
												// enclosing class
			}
		});

		return sb.toString();
	}

	@Override
	public String desencriptarCampo(final String valorCampo) {
		final StringBuilder sb = new StringBuilder();

		if (valorCampo != null && !valorCampo.toUpperCase().trim().equals("NULL")) {

			this.getCurrentSession().doWork(new Work() {
				public void execute(Connection connection) throws SQLException {
					CallableStatement call = connection.prepareCall("{ ? = call pkg_encripcion.desencripta(?) }");
					call.registerOutParameter(1, Types.NVARCHAR); // or whatever
																	// it is
					call.setString(2, valorCampo);
					call.execute();
					sb.append(call.getString(1)); // propagate this back to
													// enclosing class
				}
			});

		}

		return sb.toString();
	}
}
