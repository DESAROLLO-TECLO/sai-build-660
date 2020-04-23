package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ResponsableVehiculoDTO;

@Repository
public class ResponsableVehiculoDAOImpl extends BaseDaoHibernate<ResponsableVehiculoDTO> implements ResponsableVehiculoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ResponsableVehiculoDTO> buscarResponsableVehiculo(String status) {
		return getCurrentSession().createCriteria(ResponsableVehiculoDTO.class)
				.add(Restrictions.eq("rVehStatus", status)).list();
	}

	@Override
	public ResponsableVehiculoDTO buscarResponsableVehiculo(Long idResponsableVehiculo) {
		ResponsableVehiculoDTO respVehiculoDTO = findOne(idResponsableVehiculo);
		return respVehiculoDTO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ResponsableVehiculoDTO> buscarResponsableVehiculoConEstatusActivo(){
		Criteria criteria = getCurrentSession().createCriteria(ResponsableVehiculoDTO.class);
		criteria.add(Restrictions.eq("rVehStatus", "A"));
		return (List<ResponsableVehiculoDTO>) criteria.list();
	}

	@Override
	public ResponsableVehiculoDTO buscarResponsableVehiculoPorCod(String responsableVehiculoCod) {
		Criteria criteria = getCurrentSession().createCriteria(ResponsableVehiculoDTO.class);
		criteria.add(Restrictions.eq("rVehCod", responsableVehiculoCod));
		return (ResponsableVehiculoDTO) criteria.uniqueResult();
	}
}
