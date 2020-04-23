package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.PropiedadesCompDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDaoHibernate;

@Repository
public class PropiedadesCompDAOImpl extends BaseDaoHibernate<PropiedadesCompDTO> implements PropiedadesCompDAO{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PropiedadesCompDTO> getComponentesPropiedad(Long idComponente) {
		Criteria c = getCurrentSession().createCriteria(PropiedadesCompDTO.class);
		c.createAlias("componente", "c");
		c.add(Restrictions.eq("c.idComponente", idComponente));
		c.add(Restrictions.eq("c.stActivo", 1));
		c.add(Restrictions.eq("stActivo",1));
		return (List<PropiedadesCompDTO>)c.list();
	}
}
