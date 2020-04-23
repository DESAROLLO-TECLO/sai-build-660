package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatalogoWebDTO;

@Repository
public class CatalogoWebDAOImpl extends BaseDaoHibernate<CatalogoWebDTO> implements CatalogoWebDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CatalogoWebDTO> buscarCatalogosActivos() {
		Criteria criteria = getCurrentSession().createCriteria(CatalogoWebDTO.class);
		criteria.add(Restrictions.eq("catalogoStatus", 1L));
		criteria.addOrder(Order.asc("catalogoDesc"));
		return (List<CatalogoWebDTO>) criteria.list();
	}
	
}
