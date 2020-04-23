package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoSubTipoDTO;

@Repository
public class VehiculoSubTipoDAOImpl extends BaseDaoHibernate<VehiculoSubTipoDTO> implements VehiculoSubTipoDAO {

	@Override
	public VehiculoSubTipoDTO buscarVehiculoSubTipoPorCod(String vehiculoSubTipoCod) {
		Criteria criteria = getCurrentSession().createCriteria(VehiculoSubTipoDTO.class);
		criteria.add(Restrictions.eq("vSubTipoCodigo", vehiculoSubTipoCod));
		return (VehiculoSubTipoDTO) criteria.uniqueResult();
	}
}
