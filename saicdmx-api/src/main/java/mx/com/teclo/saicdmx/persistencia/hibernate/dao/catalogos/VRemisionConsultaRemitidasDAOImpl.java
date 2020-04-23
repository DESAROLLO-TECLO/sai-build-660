package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VRemisionConsultaRemitidasDTO;

@Repository
public class VRemisionConsultaRemitidasDAOImpl extends BaseDaoHibernate<VRemisionConsultaRemitidasDTO> implements VRemisionConsultaRemitidasDAO {

	@Override
	public String obtenerInfoIngresoIngresoPorPlaca(String placa) {

		Criteria criteria = getCurrentSession().createCriteria(VRemisionConsultaRemitidasDTO.class)
		    .setMaxResults(1)
		    .setProjection(Projections.projectionList()
			      .add(Projections.property("informacionIngreso")));
		criteria.add(Restrictions.eq("placa", placa));
		
		return criteria.uniqueResult().toString();
		
	}

	@Override
	public String obtenerInfoIngresoIngresoPorInfraccion(String numeroInfraccion) {
		
		Criteria criteria = getCurrentSession().createCriteria(VRemisionConsultaRemitidasDTO.class)	
		  .setMaxResults(1)
		    .setProjection(Projections.projectionList()
			      .add(Projections.property("informacionIngreso")));
		  criteria.add(Restrictions.eq("infraccion", numeroInfraccion));
		
		return criteria.uniqueResult().toString();
		
		
	}

}
