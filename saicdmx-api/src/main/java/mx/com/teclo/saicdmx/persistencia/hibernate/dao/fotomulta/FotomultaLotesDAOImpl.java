package mx.com.teclo.saicdmx.persistencia.hibernate.dao.fotomulta;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta.FotomultaLotesDTO;

@Repository
public class FotomultaLotesDAOImpl extends BaseDaoHibernate<FotomultaLotesDTO> implements FotomultaLotesDAO{

	/***
	 * {@inheritDoc}
	 */
	@Override
	public FotomultaLotesDTO buscarLotesEnCreacion(Integer tipoRadar, Integer archivoTipo) {
		Criteria criteria = getCurrentSession().createCriteria(FotomultaLotesDTO.class);
		criteria.add(Restrictions.eq("enCreacion", 1));
		criteria.add(Restrictions.eq("radarTipoId", tipoRadar));
		criteria.add(Restrictions.eq("archivoTipo", archivoTipo));
		return (FotomultaLotesDTO) criteria.uniqueResult();
	}

}
