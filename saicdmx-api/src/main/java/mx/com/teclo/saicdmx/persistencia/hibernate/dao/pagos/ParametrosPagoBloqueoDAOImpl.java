package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.ParametrosPagosDTO;

@Repository
public class ParametrosPagoBloqueoDAOImpl extends BaseDaoHibernate<ParametrosPagosDTO> implements ParametrosPagoBloqueoDAO {

	@Override
	public ParametrosPagosDTO obtenerParametroPago(Integer idParametroPago) {
		Criteria criteria = getCurrentSession().createCriteria(ParametrosPagosDTO.class);
		criteria.add(Restrictions.eq("idParametroPago", idParametroPago !=null ? idParametroPago.intValue() : 0));
		criteria.add(Restrictions.eq("stActivo", 1));
		return (ParametrosPagosDTO) criteria.uniqueResult();
	}

}
