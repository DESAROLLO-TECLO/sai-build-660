package mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias;


import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDTO;
import mx.com.teclo.saicdmx.util.enums.EnumGarantiasProceso; 




@Repository("garantiasDAO")
public class GarantiasDAOImpl extends BaseDaoHibernate<GarantiaDTO>
		implements GarantiasDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<GarantiaDTO> getGarantiasSinProcesar(Long empId) {
		Criteria query = getCurrentSession().createCriteria(GarantiaDTO.class);
		query.createAlias("garantiaProcesoDTO", "garantiaProcesoDTO");
		query.createAlias("empleadoDTO", "empleadoDTO");
		
		query.add(Restrictions.eq("empleadoDTO.empId", empId));
		query.add(Restrictions.eq("garantiaProcesoDTO.procesoId",EnumGarantiasProceso.CREADA.getProcesoID()));
		
		return query.list();

	}


	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<GarantiaDTO> busquedaGarantiasGenerales(Integer parametroBusqueda, String parametro){
		Criteria criteria = getCurrentSession().createCriteria(GarantiaDTO.class);
		
		
		
		criteria.createAlias("infraccionDTO", "inf");
		criteria.createAlias("garantiaDocumentoDTO", "garDoc");
		criteria.add(Restrictions.not(Restrictions.eq("garDoc.documentoId", new Integer(7))));
		
		switch (parametroBusqueda) {
		case 1:
			criteria.add(Restrictions.eq("inf.infraccNum", parametro));
			break;
		case 2:
			criteria.add(Restrictions.eq("inf.infraccPlaca", parametro));
			break;
		case 3:
			criteria.add(Restrictions.eq("garantiaId", Long.parseLong(parametro)));
			break;
		case 4:
			criteria.add(Restrictions.eq("idLote", Long.parseLong(parametro)));
			break;
		default:
			break;
		}
		
		
		return (List<GarantiaDTO>) criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<GarantiaDTO> buscarGarantiasEntrega(Integer parametroBusqueda, String parametro, Integer procesoId){
		Criteria criteria = getCurrentSession().createCriteria(GarantiaDTO.class);
	
		criteria.createAlias("infraccionDTO", "inf");
		criteria.createAlias("garantiaProcesoDTO", "gar");
		criteria.createAlias("garantiaDocumentoDTO", "tipo");
		
		switch(parametroBusqueda){
			case 1: 
				criteria.add(Restrictions.eq("inf.infraccPlaca", parametro));
				break;
			case 2: 
				criteria.add(Restrictions.eq("documentoFolio", parametro));
				break;
			case 3: 
				criteria.add(Restrictions.eq("inf.infraccNum", parametro));
				break;
			case 4:
				criteria.add(Restrictions.eq("garantiaId", Long.parseLong(parametro)));
				break;
			default: 
				break;
		}
		
		criteria.add(Restrictions.ne("tipo.documentoId", 7));
		criteria.add(Restrictions.eq("gar.procesoId", procesoId));
		
		return criteria.list();
	}



	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<GarantiaDTO> buscarGarantiasSinRecibir() {
		Criteria criteria = getCurrentSession().createCriteria(GarantiaDTO.class);
		criteria.createAlias("garantiaProcesoDTO", "proceso");
		criteria.add(Restrictions.eq("proceso.procesoId",
				EnumGarantiasProceso.CREADA.getProcesoID()));
		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<GarantiaDTO> buscarGarantiasFisicas(String infracNum, String infracPlaca, Boolean op) {
		Criteria criteria = getCurrentSession().createCriteria(GarantiaDTO.class);
		criteria.createAlias("infraccionDTO", "inf");
		criteria.createAlias("garantiaDocumentoDTO", "tipo");
		criteria.createAlias("garantiaProcesoDTO", "estatus");
		if(!op) {
			criteria.add(Restrictions.eq("inf.infraccNum", infracNum));
			criteria.add(Restrictions.eq("inf.infraccPlaca", infracPlaca));
			criteria.add(Restrictions.ne("tipo.documentoId", 7));
			criteria.add(Restrictions.lt("estatus.procesoId", 2));
		}else {
			criteria.add(Restrictions.eq("inf.infracGarAsociada", infracNum));
			criteria.add(Restrictions.eq("tipo.documentoId", 7));
			criteria.add(Restrictions.eq("estatus.procesoId", 2));
		}
		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<GarantiaDTO> buscarListGarantLote(Long idLote) {
		Criteria criteria = getCurrentSession().createCriteria(GarantiaDTO.class);
		criteria.add(Restrictions.eq("idLote", idLote));
	
		
		return (List<GarantiaDTO>) criteria.list();
	}
}
