package mx.com.teclo.saicdmx.persistencia.hibernate.dao.logs;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.logs.WebLogsDTO;
/**
 * 
 * @author javier07
 *
 */
@Repository
public class WebLogsDAOImpl extends BaseDaoHibernate<WebLogsDTO> implements WebLogsDAO{

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WebLogsDTO> busquedaLogsTodos() {

		Criteria c = getCurrentSession().createCriteria(WebLogsDTO.class)
				.addOrder(Order.asc("logId"));
         return  (List<WebLogsDTO>) c.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public WebLogsDTO busquedaLogPorId(Long id) {
		Criteria c =  getCurrentSession().createCriteria(WebLogsDTO.class);
		c.add(Restrictions.eq("logId", id));		
		return (WebLogsDTO) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Long ontieneLog(String descripcion, String logNombre, String logTipoArch, String rutaArchivo) {
		List<WebLogsDTO> lista = new ArrayList<>();
		Criteria c = getCurrentSession().createCriteria(WebLogsDTO.class);
		c.add(Restrictions.eq("logDescripcion", descripcion));
		c.add(Restrictions.eq("logNombre", logNombre));
		c.add(Restrictions.eq("tipoExtensiones", logTipoArch));
		c.add(Restrictions.eq("rutaArchivos", rutaArchivo));
		lista  = (List<WebLogsDTO>) c.list();
		return lista.get(0).getLogId();
	}
}
