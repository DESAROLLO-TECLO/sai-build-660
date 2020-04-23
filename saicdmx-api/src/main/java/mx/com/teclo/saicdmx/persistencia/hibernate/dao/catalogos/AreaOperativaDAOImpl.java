package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AreaOperativaDTO;

@Repository
public class AreaOperativaDAOImpl extends BaseDaoHibernate<AreaOperativaDTO> implements AreaOperativaDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AreaOperativaDTO> buscarAreaOperativa(Long reg_id) {
		Criteria criteria =  getCurrentSession().createCriteria(AreaOperativaDTO.class);
		criteria.add(Restrictions.eq("regionalDTO.reg_id", reg_id));
		criteria.addOrder(Order.asc("upc_nombre"));
		return criteria.list();
	}

}
