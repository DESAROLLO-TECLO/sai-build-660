package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EstatusInfraccionDTO;

@Repository
public class EstatusInfraccionDAOImpl extends BaseDaoHibernate<EstatusInfraccionDTO> implements EstatusInfraccionDAO {

	@Override
	public EstatusInfraccionDTO buscarEstatusInfPorCod(String estatatusInfCod) {
		Criteria criteria = getCurrentSession().createCriteria(EstatusInfraccionDTO.class);
		criteria.add(Restrictions.eq("estatusCodigo", estatatusInfCod));
		return (EstatusInfraccionDTO) criteria.uniqueResult();
	}
}
