package mx.com.teclo.saicdmx.persistencia.hibernate.dao.articulos;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticulosInfraccionesFinanzasDTO;

@Repository("articulosInfraccionesFinanzasDAO")
public class ArticulosInfraccionesFinanzasDAOImpl 
	extends BaseDaoHibernate<ArticulosInfraccionesFinanzasDTO> implements ArticulosInfraccionesFinanzasDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticulosInfraccionesFinanzasDTO> buscarArticulosActivosPorFecha(Date fecha){
		Criteria criteria = getCurrentSession().createCriteria(ArticulosInfraccionesFinanzasDTO.class);
		Conjunction and = Restrictions.conjunction();
		and.add(Restrictions.le("artInfrFinFechaInicio", fecha));
		and.add(Restrictions.ge("artInfrFinFechaTermino", fecha));
		criteria.add(and);
		criteria.addOrder(org.hibernate.criterion.Order.asc("artInfrFinArticulo"));
		criteria.setProjection(
				Projections.distinct(
						Projections.projectionList().add(	
								Projections.property("artInfrFinArticulo"), "artInfrFinArticulo"))).
		setResultTransformer(new AliasToBeanResultTransformer(ArticulosInfraccionesFinanzasDTO.class));
		
		return (List<ArticulosInfraccionesFinanzasDTO>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticulosInfraccionesFinanzasDTO> buscarFraccionesActivasPorFechaYArticuloNumero(Date fecha, Long articuloNumero){
		Criteria criteria = getCurrentSession().createCriteria(ArticulosInfraccionesFinanzasDTO.class);
		Conjunction and = Restrictions.conjunction();
		and.add(Restrictions.eq("artInfrFinArticulo", articuloNumero));
		and.add(Restrictions.lt("artInfrFinFechaInicio", fecha));
		and.add(Restrictions.ge("artInfrFinFechaTermino", fecha));
		criteria.add(and);
		criteria.addOrder(org.hibernate.criterion.Order.asc("artInfrFinFraccion"));
		criteria.setProjection(
				Projections.distinct(
						Projections.projectionList().add(	
								Projections.property("artInfrFinFraccion"), "artInfrFinFraccion").add(
								Projections.property("artInfrFinArticulo"), "artInfrFinArticulo")
								
						)
				).
		setResultTransformer(new AliasToBeanResultTransformer(ArticulosInfraccionesFinanzasDTO.class));
		
		return (List<ArticulosInfraccionesFinanzasDTO>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticulosInfraccionesFinanzasDTO> buscarIncisoParrafoActivosPorFechaArticuloNumeroYFraccion
		(Date fecha, Long articuloNumero, Long fraccion){
		Criteria criteria = getCurrentSession().createCriteria(ArticulosInfraccionesFinanzasDTO.class);
		Conjunction and = Restrictions.conjunction();
		and.add(Restrictions.eq("artInfrFinArticulo", articuloNumero));
		and.add(Restrictions.eq("artInfrFinFraccion", fraccion));
		and.add(Restrictions.lt("artInfrFinFechaInicio", fecha));
		and.add(Restrictions.ge("artInfrFinFechaTermino", fecha));
		criteria.add(and);
		criteria.addOrder(org.hibernate.criterion.Order.asc("artInfrFinInciso"));
		criteria.addOrder(org.hibernate.criterion.Order.asc("artInfrFinParrafo"));
		
		return (List<ArticulosInfraccionesFinanzasDTO>) criteria.list();
	}

	@Override
	public List<ArticulosInfraccionesFinanzasDTO> buscarArticulosInfraccionesFinanzasPorArticulo(Long articuloId) {
		Criteria criteria = getCurrentSession().createCriteria(ArticulosInfraccionesFinanzasDTO.class);
		criteria.add(Restrictions.eq("articulo.artId", articuloId));
		return criteria.list();
	}
	
	@Override
	public ArticulosInfraccionesFinanzasDTO buscarArticulosInfraccionesFinanzasPorId(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(ArticulosInfraccionesFinanzasDTO.class);
		criteria.add(Restrictions.eq("artInfrFinCons", id));
		return (ArticulosInfraccionesFinanzasDTO) criteria.uniqueResult();
	}
}
