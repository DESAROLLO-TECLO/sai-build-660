package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EntidadPagoDTO;

@Repository
public class EntidadPagoDAOImpl extends BaseDaoHibernate<EntidadPagoDTO> implements EntidadPagoDAO {

	@Override
	public EntidadPagoDTO buscarEntidadPagoPorCod(String entidadPagoCod) {
		Criteria criteria = getCurrentSession().createCriteria(EntidadPagoDTO.class);
		criteria.add(Restrictions.eq("entidadCodigo", entidadPagoCod));
		return (EntidadPagoDTO) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntidadPagoDTO> busquedaOrdenanda() {
		Criteria criteria = getCurrentSession().createCriteria(EntidadPagoDTO.class);
		criteria.addOrder(Order.asc("entidadId"));
		return criteria.list();
	}
}
