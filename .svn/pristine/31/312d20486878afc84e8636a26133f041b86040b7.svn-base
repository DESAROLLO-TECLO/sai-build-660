package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ZonaServicioDTO;

@Service
public class ZonaServicioDAOImpl extends BaseDaoHibernate<ZonaServicioDTO> implements ZonaServicioDAO {

	@Override
	public ZonaServicioDTO buscarZonaServicioPorCod(String zonaServicioCod) {
		Criteria criteria = getCurrentSession().createCriteria(ZonaServicioDTO.class);
		criteria.add(Restrictions.eq("zonaCod", zonaServicioCod));
		return (ZonaServicioDTO) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZonaServicioDTO> busquedaOrdenada() {
		Criteria criteria = getCurrentSession().createCriteria(ZonaServicioDTO.class);
		criteria.addOrder(Order.asc("zonaId"));
		return criteria.list();
	}
}
