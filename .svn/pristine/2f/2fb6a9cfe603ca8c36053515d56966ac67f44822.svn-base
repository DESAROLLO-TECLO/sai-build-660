package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatalogoWebOpcionDTO;

@Repository
public class CatalogoWebOpcionDAOImpl extends BaseDaoHibernate<CatalogoWebOpcionDTO> implements CatalogoWebOpcionDAO {

	@Override
	public List<CatalogoWebOpcionDTO> buscarOpcionesPorCatalogoId(Long catalogoId) {
		Criteria criteria = getCurrentSession().createCriteria(CatalogoWebOpcionDTO.class);
		criteria.add(Restrictions.eq("catalogoWebDTO.catalogoId", catalogoId));
		criteria.addOrder(Order.asc("opcionDesc"));
		return (List<CatalogoWebOpcionDTO>) criteria.list();
	}
}
