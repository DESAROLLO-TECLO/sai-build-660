package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EstadoDTO;

@Repository("estadoDao")
public class EstadoDAOImpl extends BaseDaoHibernate<EstadoDTO> implements EstadoDAO {

	@Override
	public EstadoDTO buscarEstado(Long idEstado) {
		EstadoDTO estadoDTO = findOne(idEstado);

		return estadoDTO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoDTO> buscarEstadoPorCodigo(String codigo) {
		Criteria criteria = getCurrentSession().createCriteria(EstadoDTO.class);
		criteria.add(Restrictions.eq("edoCod", codigo));
		
		
		List<EstadoDTO> estadosDTO = (List<EstadoDTO>) criteria.list();
		if(CollectionUtils.isNotEmpty(estadosDTO))
			return estadosDTO;
		return null;
	}

	@Override
	public EstadoDTO buscarPorCodigo(String estadoCod) {
		Criteria criteria = getCurrentSession().createCriteria(EstadoDTO.class);
		criteria.add(Restrictions.eq("edoCod", estadoCod));
		return (EstadoDTO) criteria.uniqueResult();
	}

}
