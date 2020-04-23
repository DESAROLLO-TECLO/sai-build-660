package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DelId;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DelegacionDTO;

@Repository
public class DelegacionDAOImpl 	extends 	BaseDaoHibernate<DelegacionDTO> 
								implements	DelegacionDAO 
{

	@Override
	public DelegacionDTO buscarDelegacion(long idDelegacion, Long estadoDTO) 
	{
		DelId dId = new DelId();
		dId.setDelId(idDelegacion);		
		dId.setEdoId(estadoDTO);
		DelegacionDTO delegacionDTO =  findOne(dId);
		return delegacionDTO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DelegacionDTO> buscarDelegacionPorEstado(Long estadoId){
		Criteria criteria = getCurrentSession().createCriteria(DelegacionDTO.class);
		criteria.add(Restrictions.eqOrIsNull("estadoDTO.edoId", estadoId));
		criteria.add(Restrictions.eqOrIsNull("delStatus", "A"));
		List<DelegacionDTO> delegacionesDTO= (List<DelegacionDTO>)criteria.list();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		if(CollectionUtils.isNotEmpty(delegacionesDTO))
			return delegacionesDTO;
		else
			return null;
	}

	@Override
	public DelegacionDTO buscarDelegacionPorCod(String DelCod) {
		Criteria criteria = getCurrentSession().createCriteria(DelegacionDTO.class);
		criteria.add(Restrictions.eqOrIsNull("delCod", DelCod.toUpperCase()));
		return (DelegacionDTO) criteria.uniqueResult();
	}
}
