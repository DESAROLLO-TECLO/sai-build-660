package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoTipoDTO;

@Repository
public class VehiculoTipoDAOImpl extends BaseDaoHibernate<VehiculoTipoDTO> implements VehiculoTipoDAO {

	@Override
	public VehiculoTipoDTO buscarVehiculoTipo(Long idVehiculoTipo) {
		VehiculoTipoDTO vehiculoTipoDTO = findOne(idVehiculoTipo);
		return vehiculoTipoDTO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VehiculoTipoDTO> buscarTodosVehiculoTipoActivo(){
		Criteria criteria = getCurrentSession().createCriteria(VehiculoTipoDTO.class);
		criteria.add(Restrictions.eq("vTipoStatus","A"));
		criteria.addOrder(Order.asc("vTipoNombre"));
		return (List<VehiculoTipoDTO>) criteria.list();
	}
	
	@Override
	public List<VehiculoTipoDTO> buscarVehiculoTipoPorSubTipo(Long subTipoId) {
		//Criteria criteria = getCurrentSession().createCriteria(VehiculoTipoDTO.class);
		//criteria.add(Restrictions.eq("vSubtipo.vSubTipoId", subTipoId));
		//return criteria.list();
		String hql = "Select vt from VehiculoTipoDTO vt where vt.vSubtipo.vSubTipoId = :subTipoId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("subTipoId", subTipoId);
		return query.list();
	}

	@Override
	public VehiculoTipoDTO buscarVehiculoTipoPorCod(String vehiculoTipoCod) {
		Criteria criteria = getCurrentSession().createCriteria(VehiculoTipoDTO.class);
		criteria.add(Restrictions.eq("vipoCod", vehiculoTipoCod));
		return (VehiculoTipoDTO) criteria.uniqueResult();
	}
}
