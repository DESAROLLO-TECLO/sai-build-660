package mx.com.teclo.saicdmx.persistencia.hibernate.dao.atencionCiudadana;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.atencionCiudadana.ExpedienteTramiteDTO;

@Repository
public class ExpedienteTramiteDAOImpl extends BaseDaoHibernate<ExpedienteTramiteDTO> implements ExpedienteTramiteDAO {

	@Override
	public ExpedienteTramiteDTO buscarExpedintePorFolioTipo(String folioTramite, String tipoExp) {
		Criteria criteria = getCurrentSession().createCriteria(ExpedienteTramiteDTO.class);
		criteria.add(Restrictions.eq("folioTramite", folioTramite));
		criteria.add(Restrictions.eq("tipoExp", tipoExp));
		return (ExpedienteTramiteDTO) criteria.uniqueResult();
	}
}
