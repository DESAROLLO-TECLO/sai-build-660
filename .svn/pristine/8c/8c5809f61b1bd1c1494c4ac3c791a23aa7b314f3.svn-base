package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.SectorDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoLicenciaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoSuministroDTO;

@Repository
public class TipoSuministroDAOImpl extends BaseDaoHibernate<TipoSuministroDTO> implements TipoSuministroDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoSuministroDTO> buscarTipoSuministro() {
		Criteria criteria =  getCurrentSession().createCriteria(TipoSuministroDTO.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
}
