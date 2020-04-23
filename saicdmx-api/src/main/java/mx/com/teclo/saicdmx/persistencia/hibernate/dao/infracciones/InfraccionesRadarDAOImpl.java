package mx.com.teclo.saicdmx.persistencia.hibernate.dao.infracciones;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionRadarDTO;

@Repository
public class InfraccionesRadarDAOImpl extends BaseDaoHibernate<InfraccionRadarDTO> implements InfraccionesRadarDAO {

	@Override
	public InfraccionRadarDTO buscarInfraccionPorFolio(String folio) {
		Criteria criteria = getCurrentSession().createCriteria(InfraccionRadarDTO.class);
		criteria.add(Restrictions.eq("infraccNum", folio));
		return (InfraccionRadarDTO) criteria.uniqueResult();
	}

}
