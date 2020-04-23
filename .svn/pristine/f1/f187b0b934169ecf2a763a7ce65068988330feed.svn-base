package mx.com.teclo.saicdmx.persistencia.hibernate.dao.infracciones;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDigitalTodoDiaDTO;

@Repository
public class InfraccionDigitalTodoDiaDAOImpl 
	extends BaseDaoHibernate<InfraccionDigitalTodoDiaDTO> implements InfraccionDigitalTodoDiaDAO {
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InfraccionDigitalTodoDiaDTO> buscarPorEstatus(String status){
		Criteria criteria = getCurrentSession().createCriteria(InfraccionDigitalTodoDiaDTO.class);
		criteria.add(Restrictions.eq("status", status));
		return (List<InfraccionDigitalTodoDiaDTO>) criteria.list();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Long countByStatus(String status) {
		Criteria criteria = getCurrentSession().createCriteria(InfraccionDigitalTodoDiaDTO.class);
		criteria.add(Restrictions.eq("status", status));
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>)criteria.list();
		if(CollectionUtils.isNotEmpty(list))
			return list.get(0);
		return 0L;

	}
}
