package mx.com.teclo.saicdmx.persistencia.dao.comun;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T extends Serializable> {

	public abstract void clear();

	public abstract void flush();

	public abstract void rollback();

	public abstract void commit();

	public abstract void closeSession();

	public T findOne(final Serializable id);

	public List<T> findAll();

	public List<T> findAll(final String login);

	// public void save(final T entity);
	public Serializable save(final T entity);

	public T update(final T entity);

	public T saveOrUpdate(T entity);

	public void delete(final T entity);

	public void deleteById(final Serializable id);

	public String desencriptarCampo(final String valorCampo);

	public String encriptarCampo(final String valorCampo);
}
