package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VRemisionReporteDTO;

@Repository
public class RemisionReporteDAOImpl extends BaseDaoHibernate<VRemisionReporteDTO> implements RemisionReporteDAO {

	@Override
	public VRemisionReporteDTO consultaInformacionReporteIngresos(String infraccion) {
		return (VRemisionReporteDTO) getCurrentSession().createCriteria(VRemisionReporteDTO.class).add(Restrictions.eq("numeroInfraccion", infraccion)).uniqueResult();
	}
}
