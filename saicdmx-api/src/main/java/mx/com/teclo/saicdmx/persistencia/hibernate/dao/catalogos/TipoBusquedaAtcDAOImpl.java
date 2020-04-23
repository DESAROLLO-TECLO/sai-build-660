package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoBusquedaAtcDTO;

@Repository
public class TipoBusquedaAtcDAOImpl extends BaseDaoHibernate<TipoBusquedaAtcDTO> implements TipoBusquedaAtcDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoBusquedaAtcDTO> buscarTiposBusquedaAtencionCiudadana() {
		Criteria criteria = getCurrentSession().createCriteria(TipoBusquedaAtcDTO.class);
		criteria.add(Restrictions.eq("stActivo", 1));
		criteria.addOrder(Order.asc("cdOrden"));
		return criteria.list();
	}
}
