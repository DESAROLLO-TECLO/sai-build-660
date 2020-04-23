package mx.com.teclo.saicdmx.persistencia.hibernate.dao.semovicatmotivocancelacion;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviCatMotivoCancelacionDTO;

@Repository
public class SemoviCatMotivoCancelacionDAOImpl extends BaseDaoHibernate<SemoviCatMotivoCancelacionDTO> implements SemoviCatMotivoCancelacionDAO {
	
	/**
	 * {@inheritDoc}
	 */
	public SemoviCatMotivoCancelacionDTO buscaCancelacionPorArchvioCanceladas(){
		Criteria criteria = getCurrentSession().createCriteria(SemoviCatMotivoCancelacionDTO.class);
		
		criteria.add(Restrictions.eq("activo", true));
		criteria.add(Restrictions.eq("codigo", 1));
		
		return (SemoviCatMotivoCancelacionDTO)criteria.uniqueResult();
	}
}
