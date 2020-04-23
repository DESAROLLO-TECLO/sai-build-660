package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ParametrosColumnDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDaoHibernate;

@Repository
public class ParametrosColumnDAOImpl extends BaseDaoHibernate<ParametrosColumnDTO> implements ParametrosColumnDAO {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ParametrosColumnDTO> getAllColumns(Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.createAlias("parametroTabla", "pt");
		c.add(Restrictions.eq("pt.idParametroTabla", idParametroTabla));
		c.add(Restrictions.eq("pt.stActivo", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (List<ParametrosColumnDTO>) c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ParametrosColumnDTO> getHijos(Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		c.add(Restrictions.eq("idParamTabDependency", idParametroTabla));
		return (List<ParametrosColumnDTO>) c.list();
	}

}
