package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoMarcaDTO;

@Repository
public class VehiculoMarcaDAOImpl extends BaseDaoHibernate<VehiculoMarcaDTO> implements VehiculoMarcaDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VehiculoMarcaDTO> buscarVehiculoMarca(String status) {
		return getCurrentSession().
				createCriteria(VehiculoMarcaDTO.class).
				add(Restrictions.eq("vMarStatus", status)).
				addOrder(Order.asc("vMarId"))
				.list();
	}

	@Override
	public VehiculoMarcaDTO buscarVehiculoMarca(Long idVehiculoMarca) {
		VehiculoMarcaDTO vehMarcaDTO = findOne(idVehiculoMarca);
		return vehMarcaDTO;
	}

	@Override
	public VehiculoMarcaDTO buscarVehiculoMarcaPorNombre(String nombreMarca) {
		return (VehiculoMarcaDTO) getCurrentSession().createCriteria(VehiculoMarcaDTO.class)
				.add(Restrictions.eq("vMarNombre", nombreMarca)).uniqueResult();
	}

	@Override
	public VehiculoMarcaDTO buscarVehiculoMarcaPorCod(String VehiculoMarcaCod) {
		Criteria criteria = getCurrentSession().createCriteria(VehiculoMarcaDTO.class);
		criteria.add(Restrictions.eq("vMarCod", VehiculoMarcaCod));
		return (VehiculoMarcaDTO) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehiculoMarcaDTO> busquedaVehiculoMarcaOrdenada() {
		Criteria criteria = getCurrentSession().createCriteria(VehiculoMarcaDTO.class);
		criteria.addOrder(Order.asc("vMarId"));
		return criteria.list();
	}
}
