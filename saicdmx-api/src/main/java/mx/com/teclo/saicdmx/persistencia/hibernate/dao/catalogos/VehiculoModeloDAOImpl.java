package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoModeloDTO;

@Repository
public class VehiculoModeloDAOImpl extends BaseDaoHibernate<VehiculoModeloDTO> implements VehiculoModeloDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<VehiculoModeloDTO> buscarVehiculoModelo(Long marcaId, String status) {
		return getCurrentSession().createCriteria(VehiculoModeloDTO.class)
				.add(Restrictions.eq("vModId.vMarId", marcaId)).add(Restrictions.eq("vModStatus", status)).addOrder(Order.asc("vModNombre"))
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VehiculoModeloDTO> buscarVehiculoModeloTodos(Long marcaId) {
		return getCurrentSession().createCriteria(VehiculoModeloDTO.class)
				.add(Restrictions.eq("vModId.vMarId", marcaId))
				.list();
	}

	@Override
	public VehiculoModeloDTO buscarVehiculoModeloPorCod(String vehiculoModeloCod) {
		Criteria criteria = getCurrentSession().createCriteria(VehiculoModeloDTO.class);
		criteria.add(Restrictions.eq("vModCod", vehiculoModeloCod));
		return (VehiculoModeloDTO) criteria.uniqueResult();
	}
}
