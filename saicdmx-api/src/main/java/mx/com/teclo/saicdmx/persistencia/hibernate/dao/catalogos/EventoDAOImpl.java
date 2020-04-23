package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EventoDTO;

@Repository
public class EventoDAOImpl extends BaseDaoHibernate<EventoDTO> implements EventoDAO {

	@Override
	public EventoDTO buscarEventoPorCod(String eventoCod) {
		Criteria criteria = getCurrentSession().createCriteria(EventoDTO.class);
		criteria.add(Restrictions.eq("eventoCodigo", eventoCod));
		return (EventoDTO) criteria.uniqueResult();
	}
}
