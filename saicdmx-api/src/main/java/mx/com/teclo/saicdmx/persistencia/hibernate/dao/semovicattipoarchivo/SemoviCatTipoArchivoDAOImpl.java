package mx.com.teclo.saicdmx.persistencia.hibernate.dao.semovicattipoarchivo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviCatTipoArchivoDTO;

@Repository
public class SemoviCatTipoArchivoDAOImpl extends BaseDaoHibernate<SemoviCatTipoArchivoDTO> implements SemoviCatTipoArchivoDAO {
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public SemoviCatTipoArchivoDTO buscaTipoInfraccionPuntos(){
		Criteria criteria = getCurrentSession().createCriteria(SemoviCatTipoArchivoDTO.class);
		
		criteria.add(Restrictions.eq("activo", false));
		criteria.add(Restrictions.eq("codigo", 1));
		
		return (SemoviCatTipoArchivoDTO)criteria.uniqueResult();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public SemoviCatTipoArchivoDTO buscaTipoActivoPuntosLicencia(){
		Criteria criteria = getCurrentSession().createCriteria(SemoviCatTipoArchivoDTO.class);
		
		criteria.add(Restrictions.eq("activo", true));
		criteria.add(Restrictions.eq("codigo", 2));
		
		return (SemoviCatTipoArchivoDTO)criteria.uniqueResult();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public SemoviCatTipoArchivoDTO buscaTipoActivoLicenciaCancelada(){
		Criteria criteria = getCurrentSession().createCriteria(SemoviCatTipoArchivoDTO.class);
		
		criteria.add(Restrictions.eq("activo", true));
		criteria.add(Restrictions.eq("codigo", 3));
		
		return (SemoviCatTipoArchivoDTO)criteria.uniqueResult();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<SemoviCatTipoArchivoDTO> buscaCatTipoArchivosActivos(){
		Criteria criteria = getCurrentSession().createCriteria(SemoviCatTipoArchivoDTO.class);
		
		criteria.add(Restrictions.eq("activo", true));
		
		return criteria.list();
	}


	@Override
	public Object findOrderByDesc() {
		Criteria criteria = getCurrentSession().createCriteria(SemoviCatTipoArchivoDTO.class);
		criteria.addOrder(Order.desc("catTipoArchivoId"));
		return criteria.list();
	}
}
