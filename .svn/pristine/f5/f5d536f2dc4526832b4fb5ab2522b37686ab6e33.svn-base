package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ComponentesSoporteDTO;

@Repository
public class ComponentesSoporteDAOImpl extends BaseDaoHibernate<ComponentesSoporteDTO> implements ComponentesSoporteDAO{

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ComponentesSoporteDTO> buscarComponentesPorStatus(Integer status) {
		Criteria criteria = getCurrentSession().createCriteria(ComponentesSoporteDTO.class);
		criteria.add(Restrictions.eq("valido", status));
		criteria.addOrder(Order.asc("nombre"));
		return criteria.list();
	}

}
