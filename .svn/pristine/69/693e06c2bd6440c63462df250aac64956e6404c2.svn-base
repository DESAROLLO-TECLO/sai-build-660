package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosDTO;

@Repository
public class DepositoDAOImpl extends BaseDaoHibernate<DepositosDTO> implements DepositoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DepositosDTO> obtenerDepositosActivos() {
		
		Criteria c = getCurrentSession().createCriteria(DepositosDTO.class);
		c.add(Restrictions.eq("depEstatus", "A"));
		c.addOrder(Order.asc("depNombre"));
		return c.list();
	}

	@Override
	public DepositosDTO buscarDepositoPorCodigo(String depositoCod) {
		Criteria criteria = getCurrentSession().createCriteria(DepositosDTO.class);
		criteria.add(Restrictions.eq("depCod", depositoCod));
		return (DepositosDTO) criteria.uniqueResult();
	}
}
