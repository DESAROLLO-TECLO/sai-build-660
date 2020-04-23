package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ParamPropScriptDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDaoHibernate;

@Repository
public class ParamPropScriptDAOImpl extends BaseDaoHibernate<ParamPropScriptDTO> implements ParamPropScriptDAO {

	@Override
	@Transactional
	public ParamPropScriptDTO getParamPropById(Long idParamtrosProp) {
		Criteria c = getCurrentSession().createCriteria(ParamPropScriptDTO.class);
		c.createAlias("paramtrosPropDTO", "p");
		c.add(Restrictions.eq("p.idParamtrosProp", idParamtrosProp));
		c.add(Restrictions.eq("p.stActivo", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (ParamPropScriptDTO) c.uniqueResult();
	}

}
