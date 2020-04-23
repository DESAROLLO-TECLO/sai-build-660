package mx.com.teclo.saicdmx.persistencia.hibernate.dao.estadistica;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.estadistica.TipoInfraccionDTO;

@Repository
public class TipoInfraccionDAOImpl extends BaseDaoHibernate<TipoInfraccionDTO> implements TipoInfraccionDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoInfraccionDTO> tipoInfracciones() {
		Criteria criteria = getCurrentSession().createCriteria(TipoInfraccionDTO.class);
		         criteria.add(Restrictions.eq("stMapa", 1));
		return criteria.list();
	}

	@Override
	public String getVistaPorTipo(String infracNum) {
		TipoInfraccionDTO tipoInfraccionDTO = null;
		Criteria criteria = getCurrentSession().createCriteria(TipoInfraccionDTO.class);
		criteria.add(Restrictions.eq("cdTipoInfraccion", infracNum.substring(0, 2)));
		tipoInfraccionDTO = (TipoInfraccionDTO) criteria.uniqueResult();
		if(tipoInfraccionDTO==null) {
			criteria = null;
			criteria = getCurrentSession().createCriteria(TipoInfraccionDTO.class);
			criteria.add(Restrictions.eq("cdTipoInfraccion", "x"));
			criteria.add(Restrictions.eq("stActivo", 1));
			tipoInfraccionDTO = (TipoInfraccionDTO) criteria.uniqueResult();
		}
		return tipoInfraccionDTO.getNbVitaLC();
	}

}
