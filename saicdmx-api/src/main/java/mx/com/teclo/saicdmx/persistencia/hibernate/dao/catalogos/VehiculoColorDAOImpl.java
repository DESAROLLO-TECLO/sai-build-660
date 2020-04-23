package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoColorDTO;

@Repository
public class VehiculoColorDAOImpl extends BaseDaoHibernate<VehiculoColorDTO> implements VehiculoColorDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VehiculoColorDTO> buscarColor(String status) {
		return getCurrentSession().createCriteria(VehiculoColorDTO.class).add(Restrictions.eq("vColorStatus", status))
				.list();
	}

	@Override
	public VehiculoColorDTO buscarColor(Long idVehiculoColor) {
		VehiculoColorDTO vehColorDTO = findOne(idVehiculoColor);
		return vehColorDTO;
	}

	@Override
	public VehiculoColorDTO buscarVehiculoColorPorCod(String vehiculoColorCod) {
		Criteria criteria = getCurrentSession().createCriteria(VehiculoColorDTO.class);
		criteria.add(Restrictions.eq("vColorCod", vehiculoColorCod));	
		return (VehiculoColorDTO) criteria.uniqueResult();
	}
}
