package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VRemisionConsultaPruebasDTO;

@Repository
public class VRemisionConsultaPruebasDAOImpl extends BaseDaoHibernate<VRemisionConsultaPruebasDTO> implements VRemisionConsultaPruebasDAO {

	@Override
	public VRemisionConsultaPruebasDTO buscarIngreso(String numeroInfraccion) {

		Criteria criteria = getCurrentSession().createCriteria(VRemisionConsultaPruebasDTO.class);
		criteria.add(Restrictions.eq("infraccion", numeroInfraccion));

		return (VRemisionConsultaPruebasDTO) criteria.uniqueResult();
	}

}
