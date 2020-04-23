package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DenominacionDTO;

@Repository
public class DenominacionDAOImpl extends BaseDaoHibernate<DenominacionDTO> implements DenominacionDAO {

	@Override
	public DenominacionDTO buscarDenominacionPorCod(String denominacionCod) {
		Criteria criteria = getCurrentSession().createCriteria(DenominacionDTO.class);
		criteria.add(Restrictions.eq("denominacionCodigo", denominacionCod));
		return (DenominacionDTO) criteria.uniqueResult();
	}
}
