package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionalesDTO;

@Repository
public class RegionalesDAOImpl  extends BaseDaoHibernate<RegionalesDTO> implements RegionalesDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<RegionalesDTO> buscarRegionales() {
		Criteria criteria =  getCurrentSession().createCriteria(RegionalesDTO.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("reg_nombre"));
		return criteria.list();
	}

}
