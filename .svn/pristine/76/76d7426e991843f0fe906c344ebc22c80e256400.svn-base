package mx.com.teclo.saicdmx.persistencia.hibernate.dao.articulos;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticuloDTO;
import mx.com.teclo.saicdmx.util.comun.ArticulosUtil;

@Repository
public class ArticuloDAOImpl extends BaseDaoHibernate<ArticuloDTO> implements ArticuloDAO 
{
	private static final Logger logger = Logger.getLogger(ArticuloDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloDTO> buscarNumerosArticulosInfracciones() 
	{
		logger.info("----------->>>> buscarNumeroArticuloInfracciones");
		Criteria c = getCurrentSession().createCriteria(ArticuloDTO.class);
		c.add(Restrictions.gt("artId", 1200L));
		c.add(Restrictions.eq("artStatus", "A"));
		c.addOrder(Order.asc("artNumero"));
		c.setProjection(
				Projections.distinct(
					Projections.projectionList().add(
						Projections.property("artNumero"), "artNumero").add(
						Projections.property("artFraccion"), "artFraccion").add(
						Projections.property("artParrafo"), "artParrafo")
				)
		).setResultTransformer(new AliasToBeanResultTransformer(ArticuloDTO.class));
						
		//c.setProjection(Projections.distinct(Projections.property("artNumero")));
		//c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloDTO> buscarFraccionesArticulo(String numeroArticulo) 
	{   
		Date fechaActual = new Date();
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ARTICULOS a, ARTICULOS_INFRAC_FINANZAS af ");
		sql.append(" where a.ART_ID = af.ART_ID ");
		sql.append(" and af.FECHA_INICIO <= :fechaActual ");
		sql.append(" and af.FECHA_TERMINO >= :fechaActual ");
		sql.append(" and a.ART_NUMERO = :artNumero ");
		sql.append(" and a.ART_STATUS = 'A' ");
		Query query = getCurrentSession().createSQLQuery(sql.toString()).addEntity(ArticuloDTO.class);
		query.setParameter("artNumero", numeroArticulo);
		query.setParameter("fechaActual", fechaActual);
		
		//logger.info("----------->>>> fraccionByArtNum");
		//Criteria c = getCurrentSession().createCriteria(ArticuloDTO.class);
		//c.add(Restrictions.eq("artNumero", numeroArticulo));
		//c.add(Restrictions.eq("artStatus", "A"));
		//return c.list();
		return query.list();
	}

	@Override
	public ArticuloDTO buscarArticulo(Long idArticulo) 
	{
		ArticuloDTO articuloDTO = findOne(idArticulo);
		return articuloDTO;
	}
	
	@Override
	public ArticuloDTO buscarArticulo(String numeroArticulo,String fraccion,String parrafo, String inciso) 
	{		
		Criteria c = getCurrentSession().createCriteria(ArticuloDTO.class);
		c.add(Restrictions.eq("artNumero", numeroArticulo));
		c.add(Restrictions.eq("artFraccion", ArticulosUtil.formatFraccion(fraccion)));
		c.add(Restrictions.eq("artParrafo", ArticulosUtil.formatParrafo(parrafo)));
		c.add(Restrictions.eq("artInciso", ArticulosUtil.formatInciso(inciso)));
		c.add(Restrictions.eq("artStatus", "A"));		
		return (ArticuloDTO) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloDTO> buscarArticuloActivos() {
		Criteria criteria = getCurrentSession().createCriteria(ArticuloDTO.class);
		criteria.add(Restrictions.eq("artStatus", "A"));
		criteria.addOrder(Order.asc("artNumero"));
		return criteria.list();
	}
    
}
