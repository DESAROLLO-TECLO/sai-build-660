package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ParametrosDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDaoHibernate;

@Repository
public class ParametroDAOImpl extends BaseDaoHibernate<ParametrosDTO> implements ParametroDAO{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ParametrosDTO> getParametrosByReporte(Long idReporte) {
		Criteria c = getCurrentSession().createCriteria(ParametrosDTO.class);
		c.createAlias("reporteDTO", "rep");
		c.add(Restrictions.eq("rep.idReporte", idReporte));
		c.add(Restrictions.eq("stActivo", 1));
		c.addOrder(Order.asc("nuOrden"));
		return (List<ParametrosDTO>) c.list();
	}

}
