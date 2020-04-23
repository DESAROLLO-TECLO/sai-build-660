package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoAlarmaDTO;

@Repository
public class TipoAlarmaDAOImpl extends BaseDaoHibernate<TipoAlarmaDTO> implements TipoAlarmaDAO {

	@Override
	public TipoAlarmaDTO buscarPorCodAlarma(String CodAlarma) {
		Criteria criteria = getCurrentSession().createCriteria(TipoAlarmaDTO.class);
		criteria.add(Restrictions.eq("alarmaCod", CodAlarma.toUpperCase()));
		return (TipoAlarmaDTO) criteria.uniqueResult();
	}
}
