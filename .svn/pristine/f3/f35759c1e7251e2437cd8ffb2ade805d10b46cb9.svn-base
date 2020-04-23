package mx.com.teclo.ms.persistencia.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.ms.persistencia.dto.TipoFechasDTO;
import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;

@Repository
public class CatalogosMsDAOImpl extends BaseDaoHibernate<TipoFechasDTO> implements CatalogosMsDAO {


	@SuppressWarnings("unchecked")
	@Override
	public List<TipoFechasDTO> getCatalogoTipoFechasAll() {
		Criteria criteria = getCurrentSession().createCriteria(TipoFechasDTO.class);
		criteria.add(Restrictions.eq("stTipoFecha", 1));
		criteria.addOrder(Order.asc("nuOrden"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoFechasDTO> getCatalogoTipoFechasOpcion(Long[] opciones) {
		Criteria criteria = getCurrentSession().createCriteria(TipoFechasDTO.class);
		criteria.add(Restrictions.eq("stTipoFecha", 1));
		criteria.add(Restrictions.in("idTipoFecha", opciones));
		criteria.addOrder(Order.asc("nuOrden"));
		return criteria.list();
	}
}
