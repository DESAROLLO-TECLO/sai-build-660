package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RadarCatArchivoTipoDTO;

/**
 * 
 * @author UnitisDes0416
 *
 */
@Repository("radarCatArchivoTipoDAO")
public class RadarCatArchivoTipoDAOImpl extends BaseDaoHibernate<RadarCatArchivoTipoDTO> implements RadarCatArchivoTipoDAO {
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<RadarCatArchivoTipoDTO> buscaCatArchivoTipoActivos(){
		Criteria criteria = getCurrentSession().createCriteria(RadarCatArchivoTipoDTO.class);
		
		criteria.add(Restrictions.eqOrIsNull("activoComplementacion", true));
		criteria.add(Restrictions.eqOrIsNull("activo", true));
		criteria.addOrder(Order.asc("nombre"));
		
		return criteria.list();
	}
}
