package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.VistaDTO;

@Repository
public class VistaInfraccionesAnualesDAOimpl extends BaseDaoHibernate<VistaDTO> implements VistaInfraccionesAnualesDAO{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<VistaDTO> getInfo() {
		Criteria c = getCurrentSession().createCriteria(VistaDTO.class);
		c.setProjection(Projections.distinct(Projections.property("anio")));
		c.addOrder(Order.asc("anio"));
		return (List<VistaDTO>)c.list();
	}

}
