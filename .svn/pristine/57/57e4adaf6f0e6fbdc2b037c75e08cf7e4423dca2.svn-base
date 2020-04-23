package mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaEstatusProcesoDTO;
 


@Repository("garantiasEstatusProcesoDAO")
public class GarantiasEstatusProcesoDAOImpl extends	BaseDaoHibernate<GarantiaEstatusProcesoDTO> implements GarantiasEstatusProcesoDAO {

	@SuppressWarnings("unchecked")
	public List<GarantiaEstatusProcesoDTO> buscaGarantiaPorEstatusYFechaCreacion(Integer tipoFecha, Integer estatusProceso, Date fechaInicio, Date fechaFin){
		StringBuilder consultaGarantias = new StringBuilder("");
		
		if(tipoFecha == 1){
			consultaGarantias.append(" FROM 	GarantiaEstatusProcesoDTO gep");
			consultaGarantias.append(" WHERE	TRUNC(gep.fechaCreacion) BETWEEN  :fechaInicio AND :fechaFin");
			consultaGarantias.append(" AND		gep.garantiaProcesoDTO.procesoId = 0");
			
			
		}else if(tipoFecha == 2){
			consultaGarantias.append(" FROM 	GarantiaEstatusProcesoDTO gep");
			consultaGarantias.append(" WHERE	TRUNC(gep.fechaCreacion) BETWEEN  :fechaInicio AND :fechaFin");
			consultaGarantias.append(" AND		gep.garantiaProcesoDTO.procesoId = 1");
			
		}else if(tipoFecha == 3){
			consultaGarantias.append(" FROM 	GarantiaEstatusProcesoDTO gep");
			consultaGarantias.append(" WHERE	TRUNC(gep.fechaCreacion) BETWEEN  :fechaInicio AND :fechaFin");
			consultaGarantias.append(" AND		gep.garantiaProcesoDTO.procesoId = 2");
			
		}else if(tipoFecha == 4){
			consultaGarantias.append(" FROM 	GarantiaEstatusProcesoDTO gep");
			consultaGarantias.append(" WHERE	TRUNC(gep.fechaCreacion) BETWEEN  :fechaInicio AND :fechaFin");
			consultaGarantias.append(" AND		gep.garantiaProcesoDTO.procesoId = 3");
			
		}
		/*esto es para que no aparezcan garantias asociadas*/
		consultaGarantias.append(" AND      gep.garantia.garantiaDocumentoDTO.documentoId !=7");
		
		
		if(estatusProceso != -1){
			consultaGarantias.append(" AND	gep.garantia.garantiaProcesoDTO.procesoId = :estatusProceso");
		}
		
		Query query =getCurrentSession().createQuery(consultaGarantias.toString());
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		
		if(estatusProceso != -1){
			query.setParameter("estatusProceso", estatusProceso);
		}
		return query.list() ;
		
	} 
	
	/**
	 * {@inheritDoc}
	 */
	public GarantiaEstatusProcesoDTO buscaGarantiaEstatusProcesoPagadas(Long garantiaId, Integer procesoId){
		Criteria criteria = getCurrentSession().createCriteria(GarantiaEstatusProcesoDTO.class);
		
		criteria.add(Restrictions.eq("garantia.garantiaId", garantiaId));
		criteria.add(Restrictions.eq("garantiaProcesoDTO.procesoId", procesoId));
		
		return (GarantiaEstatusProcesoDTO) criteria.uniqueResult();
	}
}
