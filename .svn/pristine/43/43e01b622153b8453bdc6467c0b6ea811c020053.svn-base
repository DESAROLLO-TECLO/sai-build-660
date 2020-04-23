package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoEmpleadoDTO;

@Repository
public class TipoEmpleadoDAOImpl extends BaseDaoHibernate<TipoEmpleadoDTO> implements TipoEmpleadoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoEmpleadoDTO> buscarTiposEmpleado() {
		Criteria criteria = getCurrentSession().createCriteria(TipoEmpleadoDTO.class);
		criteria.addOrder(Order.asc("empTipId"));
		return criteria.list();
	}

	@Override
	public TipoEmpleadoDTO buscarTipoEmpleadoPorCod(String empleadoTipoCod) {
		Criteria criteria = getCurrentSession().createCriteria(TipoEmpleadoDTO.class);
		criteria.add(Restrictions.eq("empTipCodigo", empleadoTipoCod.toUpperCase()));
		return (TipoEmpleadoDTO) criteria.uniqueResult();
	}
}
