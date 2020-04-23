package mx.com.teclo.saicdmx.persistencia.hibernate.dao.semoviarchivoslicencia;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviArchivosLicenciaDTO;


/**
 * 
 * @author javier07
 *
 */
@Repository
public class SemoviArchivosLicenciaDAOImpl extends BaseDaoHibernate<SemoviArchivosLicenciaDTO> implements SemoviArchivosLicenciaDAO {
	/**
	 * {@inheritDoc}
	 */
	public SemoviArchivosLicenciaDTO buscaArchivoNombreFechaCreacion(String nombreArchivo){
		Criteria criteria = getCurrentSession().createCriteria(SemoviArchivosLicenciaDTO.class);
		
		criteria.add(Restrictions.eq("nombreArchivo", nombreArchivo));
		
		return (SemoviArchivosLicenciaDTO)criteria.uniqueResult();
	}
	/**
	 * {@inheritDoc}
	 */
	public SemoviArchivosLicenciaDTO buscaArchivoNombreFechaArchivo(String nombreArchivo, Date fechaArchivo){
		Criteria criteria = getCurrentSession().createCriteria(SemoviArchivosLicenciaDTO.class);
		
		criteria.add(Restrictions.eq("nombreArchivo", nombreArchivo));
		criteria.add(Restrictions.eq("fechaArchivo", fechaArchivo));
		
		return (SemoviArchivosLicenciaDTO)criteria.uniqueResult();
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SemoviArchivosLicenciaDTO> buscarArchivosLicencia(Long tipo,Date fechaInicio,Date fechaFin) {
		
		Integer codigo = (int) (long) tipo;
		
		Criteria criteria = getCurrentSession().createCriteria(SemoviArchivosLicenciaDTO.class)
				.addOrder(Order.desc("fechaArchivo"));		
        criteria.add(Restrictions.eq("activo", true));
    
        if(tipo != 0 && fechaInicio == null){
			
    		criteria.createAlias("semoviCatTipoArchivoDTO", "semoviCatTipoArchivoDTO");
    		criteria.add(Restrictions.eq("semoviCatTipoArchivoDTO.codigo", codigo));
    		
        }
    
        if(tipo != 0 && fechaInicio != null){
			
		criteria.createAlias("semoviCatTipoArchivoDTO", "semoviCatTipoArchivoDTO");
		criteria.add(Restrictions.eq("semoviCatTipoArchivoDTO.codigo", codigo));
		criteria.add(Restrictions.between("fechaArchivo", fechaInicio, fechaFin));
		}
		
		if (tipo == 0 && fechaInicio != null){
	
			criteria.add(Restrictions.between("fechaArchivo", fechaInicio, fechaFin));
		} 

		return criteria.list();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<SemoviArchivosLicenciaDTO> buscaArchivoPorFecha(Date fechaArchivo, Integer codigoArchivo){
		Criteria criteria = getCurrentSession().createCriteria(SemoviArchivosLicenciaDTO.class);
		
		criteria.createAlias("semoviCatTipoArchivoDTO", "semoviCatTipoArchivoDTO");
		criteria.add(Restrictions.eq("fechaArchivo", fechaArchivo));
		criteria.add(Restrictions.eq("semoviCatTipoArchivoDTO.codigo", codigoArchivo));
		criteria.add(Restrictions.eq("activo", true));
		
		return criteria.list();
	}
	
}
