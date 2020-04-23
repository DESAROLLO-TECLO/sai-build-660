package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TurnoDTO;

@Repository
public class TurnoDAOImpl extends BaseDaoHibernate<TurnoDTO> implements TurnoDAO {

	@Override
	public TurnoDTO buscarturnoPorCod(String turnoCod) {
		Criteria criteria = getCurrentSession().createCriteria(TurnoDTO.class);
		criteria.add(Restrictions.eq("turnoCod", turnoCod));
		return (TurnoDTO) criteria.uniqueResult();
	}

}
