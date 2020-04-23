package mx.com.teclo.saicdmx.persistencia.hibernate.dao.semovicontrollicencia;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviControlLicenciasDTO;

@Repository
public class SemoviControlLicenciasDAOImpl extends BaseDaoHibernate<SemoviControlLicenciasDTO> implements SemoviControlLicenciasDAO {
	
	/**
	 * {@inheritDoc}
	 */
	public SemoviControlLicenciasDTO buscaLicenciaPorFolioLicencia(String folioLicencia){
		Criteria criteria = getCurrentSession().createCriteria(SemoviControlLicenciasDTO.class);
		
		criteria.add(Restrictions.eq("folioLicencia", folioLicencia));
		
		return (SemoviControlLicenciasDTO) criteria.uniqueResult();
	}
}
