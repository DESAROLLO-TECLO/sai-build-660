package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoFechasDTO;

@Repository
public class TipoFechasDAOImpl extends BaseDaoHibernate<TipoFechasDTO> implements TipoFechasDAO {

	/***
	 * 
	 * @author VAPD1226
	 * @return lista de todas los tipos de fechas con estatus activo
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoFechasDTO> buscarTipoFechas() {
		Criteria criteria = getCurrentSession().createCriteria(TipoFechasDTO.class);
		criteria.add(Restrictions.eq("stTipoFecha", 1l));
		criteria.addOrder(Order.asc("idTipoFecha"));
		
		return criteria.list();
		
	}
}
