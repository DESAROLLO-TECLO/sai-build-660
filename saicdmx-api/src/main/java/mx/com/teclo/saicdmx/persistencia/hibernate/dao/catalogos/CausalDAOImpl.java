package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CausalesDTO;

@Repository
public class CausalDAOImpl extends BaseDaoHibernate<CausalesDTO> implements CausalDAO {

	@Override
	public List<CausalesDTO> buscarCausalesActivos() {
		Criteria criteria = getCurrentSession().createCriteria(CausalesDTO.class);
//		criteria.add(Restrictions.eq("causalStatus", "A"));
		criteria.addOrder(Order.asc("causalId"));
		return criteria.list();
	}

	@Override
	public CausalesDTO buscarCausalPorCod(String causalCOd) {
		Criteria criteria = getCurrentSession().createCriteria(CausalesDTO.class);
		criteria.add(Restrictions.eq("causalCodigo", causalCOd));
		return (CausalesDTO) criteria.uniqueResult();
	}
}
