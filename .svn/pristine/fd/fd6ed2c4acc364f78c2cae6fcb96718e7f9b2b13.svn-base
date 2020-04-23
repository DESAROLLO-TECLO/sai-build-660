package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ConceptosSoporteDTO;

@Repository
public class ConceptosSoporteDAOImpl extends BaseDaoHibernate<ConceptosSoporteDTO> implements ConceptosSoporteDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<ConceptosSoporteDTO> buscarConceptosSoporteByComponente(Long componenteId, Integer valido) {
		Criteria criteria = getCurrentSession().createCriteria(ConceptosSoporteDTO.class);
		criteria.add(Restrictions.eq("componenteId.componenteId", componenteId));
		criteria.add(Restrictions.eq("valido", valido));
		criteria.addOrder(Order.asc("conceptoNombre"));
		return criteria.list();
	}
	
	@Override
	@Transactional
	public Long buscarComponenteId(Long conceptoId) {
		Criteria query = getCurrentSession().createCriteria(ConceptosSoporteDTO.class)
				.add(Restrictions.eq("conceptoId", conceptoId));
		query.setProjection(Projections.max("componenteId.componenteId"));

		Long componenteId = (Long) query.uniqueResult();

		return componenteId;

	}

}
