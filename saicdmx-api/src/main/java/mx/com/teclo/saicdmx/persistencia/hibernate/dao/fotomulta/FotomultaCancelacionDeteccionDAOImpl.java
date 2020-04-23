package mx.com.teclo.saicdmx.persistencia.hibernate.dao.fotomulta;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta.FotomultaDeteccionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta.FotomultaMotivoCancelacionDTO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FechasCancelacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaCatComboFechasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ParametrosCancelacionVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

@Repository
public class FotomultaCancelacionDeteccionDAOImpl extends BaseDaoHibernate<FotomultaDeteccionDTO> implements FotomultaCancelacionDeteccionDAO{

	
	public RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
	
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<FotomultaDeteccionDTO> getDeteccionesPorCancelar(ParametrosCancelacionVO parametros) {
	
		Date fechaInicio=null;
		
		if(!parametros.getFecha().equalsIgnoreCase("todas")){
			fechaInicio = rutinasTiempoImpl.convertirStringDate(parametros.getFecha());
		}
		
		/** Se construye el query en base a el rango de fechas***/		
		String query = "select "+
		"fd.fotomultaId as fotomultaId,"+
		"concat(fd.nombre, ' ', fd.apellidoPaterno , ' ', fd.apellidoMaterno) as nombre,"+
		"fd.placa as placa,"+
		"fd.fecha as fecha,"+
		"fd.hora as hora,"+
		"fd.tdskuid as tdskuid,"+
		"fd.oficialNombre as oficialNombre,"+
		"fd.oficialPlaca as oficialPlaca,"+
		"fd.fechaCreacion as fechaCreacion,"+
		"fd.fechaValidacion as fechaValidacion,"+
		"fd.fotomultaTipoRadar as fotomultaTipoRadar, "+
		"fd.origenPlaca as origenPlaca "+
        "from  FotomultaDeteccionDTO fd,"+
        "FotomultaTipoRadarDTO fctr "+
        "where   fctr.radarTipoId = fd.fotomultaTipoRadar.radarTipoId "+
        "and 	 fd.valido = 1 "+
        "and     fd.procesado = 0 "+ 
        "and     fd.autorizada = 1 ";
		
		if(parametros.getTipoRadar()!=0){
			query+="and fd.fotomultaTipoRadar.radarTipoId = :tipoRadar ";
		}		
		if(parametros.getOrigenPlaca()!=2){
			query+="and fd.origenPlaca = :origenPlaca ";
		}
		if (parametros.getFecha().equalsIgnoreCase("todas")){
			query+="and   to_date(substr(fd.fecha,0,2)||'/'||substr( fd.fecha,4,2)||'/'||'20' ||substr( fd.fecha,7,2) ,'dd/MM/yyyy')  <= :fechaFin ";
		}else{
			query+="and  to_date(substr(fd.fecha,0,2)||'/'||substr( fd.fecha,4,2)||'/'||'20' ||substr( fd.fecha,7,2) ,'dd/MM/yyyy') between :fechaInicio and last_day(:fechaFin) ";
		}

       query+="order by '20'||substr(fd.fecha,-2) ,"
        						  + "substr(fd.fecha,-5,2),"
        						  + "substr(fd.fecha,-8,2),"
        						  + "fctr.radarTipoId,fd.fechaValidacion asc";
  
		Query q = getCurrentSession().createQuery(query);
		/** Se evalua el tipo de busqueda respecto a un rango de fecha selecionado**/
		
		if(parametros.getTipoRadar()!=0){
			q.setParameter("tipoRadar", parametros.getTipoRadar());
		}
		if(parametros.getFecha().equalsIgnoreCase("todas")){
			q.setParameter("fechaFin", parametros.getFechaFin()); 
		}else{
			q.setParameter("fechaInicio", fechaInicio)
			 .setParameter("fechaFin", fechaInicio);			 
		} 
		if(parametros.getOrigenPlaca()!=2){
			q.setParameter("origenPlaca", parametros.getOrigenPlaca());
		}
			q.setResultTransformer
			(Transformers.aliasToBean(FotomultaDeteccionDTO.class));
		
		return (List<FotomultaDeteccionDTO>)q.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<FotomultaCatComboFechasVO> getFechaDetecciones(Date fechaFin) {

		String query = "select distinct "+
		"substr( fd.fecha,4,2) as mes,"+
		"'20'||substr( fd.fecha,7,2) as anio "+
        "from  FotomultaDeteccionDTO fd,"+
        "FotomultaTipoRadarDTO fctr "+
        "where   fctr.radarTipoId = fd.fotomultaTipoRadar.radarTipoId "+
        "and 	 fd.valido = 1 "+ 
        "and     fd.procesado = 0 "+ 
        "and     fd.autorizada = 1 "+
        "and     to_date(substr(fd.fecha,0,2)||'/'||substr( fd.fecha,4,2)||'/'||'20' ||substr( fd.fecha,7,2) ,'dd/MM/yyyy') <= :fechaFin "+
        "order by anio,mes asc";
        Query q = getCurrentSession().createQuery(query)
				 .setParameter("fechaFin", fechaFin)
				 .setResultTransformer
				 (Transformers.aliasToBean(FotomultaCatComboFechasVO.class));

		return (List<FotomultaCatComboFechasVO>)q.list();
	}

	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<FotomultaDeteccionDTO> getPrevalidacionesPorCancelar(ParametrosCancelacionVO parametros) {

		Date fechaInicio=null;
		
		if(!parametros.getFecha().equalsIgnoreCase("todas")){
			fechaInicio = rutinasTiempoImpl.convertirStringDate(parametros.getFecha());
		}
		
		/** Se construye el query en base a el rango de fechas***/
		String query = "select "+
		"fd.fotomultaId as fotomultaId,"+
		"concat(fd.nombre, ' ', fd.apellidoPaterno , ' ', fd.apellidoMaterno) as nombre,"+
		"fd.placa as placa,"+
		"fd.fecha as fecha,"+
		"fd.hora as hora,"+
		"fd.tdskuid as tdskuid,"+
		"fd.oficialNombre as oficialNombre,"+
		"fd.oficialPlaca as oficialPlaca,"+
		"fd.fechaCreacion as fechaCreacion,"+
		"fd.fechaValidacion as fechaValidacion,"+
		"fd.fotomultaTipoRadar as fotomultaTipoRadar, "+
		"fd.origenPlaca as origenPlaca "+
        "from  FotomultaDeteccionDTO fd,"+
        "FotomultaTipoRadarDTO fctr "+
        "where   fctr.radarTipoId = fd.fotomultaTipoRadar.radarTipoId "+
        "and 	 fd.valido = 1 "+
        "and     fd.procesado = 0 "+ 
        "and     fd.autorizada = 1 "+
		"and     fd.tdskuid in( "+
        "	select  fp.tdskuid "+
        "	from    FotomultaPrevalidacionDTO fp "+
		" 	where   fp.aceptada = 1 "+
		"	and     fp.validadaSSP = 1 "+
		"	and     fp.cancelada = 0 "+
		"	and     fp.duplicada = 0 "+
		"	and     fp.aceptadaSSP = 1 "+
		"	and     to_date("+
        "	substr(fp.fecha,1,2)||'/'||substr(fp.fecha,4,2)||'/'||substr(fp.fecha,7,2),'dd/mm/yyyy')"+
        "	<= :fechaFin) ";

		if(parametros.getTipoRadar()!=0){
			query+="and fd.fotomultaTipoRadar.radarTipoId = :tipoRadar ";
		}		
		if(parametros.getOrigenPlaca()!=2){
			query+="and fd.origenPlaca = :origenPlaca ";
		}
		if (parametros.getFecha().equalsIgnoreCase("todas")){
			query+="and   to_date(substr(fd.fecha,0,2)||'/'||substr( fd.fecha,4,2)||'/'||'20' ||substr( fd.fecha,7,2) ,'dd/MM/yyyy')  <= :fechaFin ";
		}else{
			query +="and  to_date(substr(fd.fecha,0,2)||'/'||substr( fd.fecha,4,2)||'/'||'20' ||substr( fd.fecha,7,2) ,'dd/MM/yyyy') between :fechaInicio and last_day(:fechaFin) ";
		}

       query +="order by '20'||substr(fd.fecha,-2) ,"
        						  + "substr(fd.fecha,-5,2),"
        						  + "substr(fd.fecha,-8,2),"
        						  + "fctr.radarTipoId,fd.fechaValidacion asc";
  
		Query q = getCurrentSession().createQuery(query);
		/** Se evalua el tipo de busqueda respecto a un rango de fecha selecionado**/
		
		if(parametros.getTipoRadar()!=0){
			q.setParameter("tipoRadar", parametros.getTipoRadar());
		}
		if(parametros.getFecha().equalsIgnoreCase("todas")){
			q.setParameter("fechaFin", parametros.getFechaFin()); 
		}else{
			q.setParameter("fechaInicio", fechaInicio)
			 .setParameter("fechaFin", fechaInicio);			 
		} 
		if(parametros.getOrigenPlaca()!=2){
			q.setParameter("origenPlaca", parametros.getOrigenPlaca());
		}
			q.setResultTransformer
			(Transformers.aliasToBean(FotomultaDeteccionDTO.class));
		
		return (List<FotomultaDeteccionDTO>)q.list();
	}

	/**
	 * {@inheritDoc}
	 */
	public long cancelarDetecciones(ParametrosCancelacionVO parametros) {
		
		Date fechaInicio=null;
		
		if(!parametros.getFecha().equalsIgnoreCase("todas")){
			fechaInicio = rutinasTiempoImpl.convertirStringDate(parametros.getFecha());
		}
	
		String query = "update  FotomultaDeteccionDTO  fd "+
				"set  valido = 0,"+ 
			  	"fd.fechaCancelacion = :fechaCancel,"+
			  	"fd.fotomultaMotivoCancelacionDTO.motivoId = 2 ,";
			
				if(!parametros.getMotivoCancelacion().equalsIgnoreCase("")){
					query+="fd.motivoCancelacion = :motivoCancelacion,";
				}			  	
				query+="fd.modificadoPor = :modificadoPor "+
				"where 	 fd.valido = 1 "+
		        "and     fd.procesado = 0 "+ 
		        "and     fd.autorizada = 1 "; 
				
				if(parametros.getTipoRadar()!=0){
					query+="and fd.fotomultaTipoRadar.radarTipoId = :tipoRadar ";
				}		
				if(parametros.getOrigenPlaca()!=2){
					query+="and fd.origenPlaca = :origenPlaca ";
				}
				if (parametros.getFecha().equals("todas")){
					query+="and   to_date(substr(fd.fecha,0,2)||'/'||substr( fd.fecha,4,2)||'/'||'20' ||substr( fd.fecha,7,2) ,'dd/MM/yyyy')  <= :fechaFin ";
				}else{
					query +="and  to_date(substr(fd.fecha,0,2)||'/'||substr( fd.fecha,4,2)||'/'||'20' ||substr( fd.fecha,7,2) ,'dd/MM/yyyy') between :fechaInicio and last_day(:fechaFin) ";
				}
				
			Query q = getCurrentSession().createQuery(query);	  
					
			if(parametros.getFecha().equals("todas")){
				q.setParameter("fechaFin", parametros.getFechaFin());				 
			}else{
				q.setParameter("fechaInicio", fechaInicio)
				 .setParameter("fechaFin", fechaInicio); 
			}
			
			if(parametros.getTipoRadar()!=0){
				q.setParameter("tipoRadar", parametros.getTipoRadar());
			}		
			if(parametros.getOrigenPlaca()!=2){
				q.setParameter("origenPlaca", parametros.getOrigenPlaca());
			}
		
			if(!parametros.getMotivoCancelacion().equalsIgnoreCase("")){
				q.setParameter("motivoCancelacion", parametros.getMotivoCancelacion());
			}

			q.setParameter("fechaCancel", new Date())
			 .setParameter("modificadoPor", parametros.getModificadoPor()) ;		
			long resultado=q.executeUpdate();
		
		return resultado;
	}

	/**
	 * {@inheritDoc}
	 */
	public long cancelarPrevalidaciones(ParametrosCancelacionVO parametros) {

		Date fechaInicio=null;
		
		if(!parametros.getFecha().equalsIgnoreCase("todas")){
			fechaInicio = rutinasTiempoImpl.convertirStringDate(parametros.getFecha());
		}
		
        String query = "update  FotomultaPrevalidacionDTO fp "+
						"set "+
				        "fp.cancelada = 1,"+
				        "fp.fechaCancelacion = :fechaCancelacion," +
				        "fp.modificadoPor = :modificadoPor,";
        
		if(!parametros.getMotivoCancelacion().equalsIgnoreCase("")){
			 query+="fp.motivoCancelacion = :motivoCancelacion ,";			
		}		        

				 query+="fp.motivoCancelacionId = 2 "+
						" 	where   fp.aceptada = 1 "+
						"	and     fp.validadaSSP = 1 "+
						"	and     fp.cancelada = 0 "+
						"	and     fp.duplicada = 0 "+
						"	and     fp.aceptadaSSP = 1 "+
		    			"   and 	fp.tdskuid "+
		    			"   in ("+
				        "		select fd.tdskuid from FotomultaDeteccionDTO fd "+
		    			"		where "+
		        		"		fd.valido = 0  "+
		        		"		and "+ 
					  	"		fd.fotomultaMotivoCancelacionDTO.motivoId = 2 "; 

							if(parametros.getTipoRadar()!=0){
								query+="and fd.fotomultaTipoRadar.radarTipoId = :tipoRadar ";
							}		
							if(parametros.getOrigenPlaca()!=2){
								query+="and fd.origenPlaca = :origenPlaca ";
							}
							if (parametros.getFecha().equals("todas")){
								query+="and   to_date(substr(fd.fecha,0,2)||'/'||substr( fd.fecha,4,2)||'/'||'20' ||substr( fd.fecha,7,2) ,'dd/MM/yyyy')  <= :fechaFin ))";
							}else{
								query +="and  to_date(substr(fd.fecha,0,2)||'/'||substr( fd.fecha,4,2)||'/'||'20' ||substr( fd.fecha,7,2) ,'dd/MM/yyyy') between :fechaInicio and last_day(:fechaFin)) ";
							}
//
//							query +="and fp.cancelada = 0 "+
//									"and fp.validadaSSP = 1 ";			
							
			Query q = getCurrentSession().createQuery(query);	  
					
			if(parametros.getFecha().equals("todas")){
				q.setParameter("fechaFin", parametros.getFechaFin());				 
			}else{
				q.setParameter("fechaInicio",fechaInicio)
				 .setParameter("fechaFin", fechaInicio); 
			}
			
			if(parametros.getTipoRadar()!=0){
				q.setParameter("tipoRadar", parametros.getTipoRadar());
			}		
			if(parametros.getOrigenPlaca()!=2){
				q.setParameter("origenPlaca", parametros.getOrigenPlaca());
			}

			q.setParameter("fechaCancelacion", new Date())
			 .setParameter("modificadoPor", parametros.getModificadoPor());	
			
			if(!parametros.getMotivoCancelacion().equalsIgnoreCase("")){
				 q.setParameter("motivoCancelacion", parametros.getMotivoCancelacion());
			}
			
			long resultado=q.executeUpdate();
		
		return resultado;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<FotomultaDeteccionDTO> consultarDeteccionesCanceladas(ParametrosCancelacionVO parametros) {

		String query = 		"select "+
							"concat(fd.nombre, ' ', fd.apellidoPaterno , ' ', fd.apellidoMaterno) as nombre,"+
							"fd.placa as placa,"+
							"fd.fecha as fecha,"+
							"fd.hora as hora,"+
							"fd.tdskuid as tdskuid,"+
							"fd.oficialNombre as oficialNombre,"+
							"fd.oficialPlaca as oficialPlaca,"+
							"fd.fechaValidacion as fechaValidacion,"+
							"fd.fechaCreacion as fechaCreacion,"+
							"fd.fechaCancelacion as fechaCancelacion,"+		
							"fd.fotomultaMotivoCancelacionDTO as fotomultaMotivoCancelacionDTO,"+  
							"fd.modificadoPor as modificadoPor, "+
							"fd.motivoCancelacion as motivoCancelacion ,"+		
							"fd.fotomultaTipoRadar as fotomultaTipoRadar, "+
							"fd.origenPlaca as origenPlaca "+
					        "from  FotomultaDeteccionDTO fd,"+
					        "FotomultaTipoRadarDTO fctr "+
//        "where fd.tdskuid "
//		+ " in ("+
//        "		select fp.tdskuid from FotomultaPrevalidacionDTO fp "+
//		" 		where fp.cancelada=1"+
//        "		and fp.fechaCancelacion is not null"+
//		"		and fp.motivoCancelacionId != 0"
//			+ ") "+
					        "where     fctr.radarTipoId = fd.fotomultaTipoRadar.radarTipoId "+
					        "and 	 fd.valido = 0 "+
					        "and     fd.procesado = 0 "+
					        "and     fd.fechaCancelacion is not null ";

		if(parametros.getMotivoId()!=0){
			query+="and fd.fotomultaMotivoCancelacionDTO.motivoId = :motivoId ";
		}
		if(parametros.getTipoRadar()!=0){
			query+="and fd.fotomultaTipoRadar.radarTipoId = :tipoRadar ";
		}		
		if(parametros.getOrigenPlaca()!=2){
			query+="and fd.origenPlaca = :origenPlaca ";
		}
		if (!parametros.getFechaInicioString().equalsIgnoreCase("null")){
			query +="and  to_date(substr(fd.fecha,0,2)||'/'||substr( fd.fecha,4,2)||'/'||'20' ||substr( fd.fecha,7,2) ,'dd/MM/yyyy') between :fechaInicio and :fechaFin ) ";
		}

        query +="order by '20'||substr(fd.fechaCancelacion,-2) ,"
        						  + "substr(fd.fechaCancelacion,-5,2),"
        						  + "fctr.radarTipoId asc";
  
		Query q = getCurrentSession().createQuery(query);
		/** Se evalua el tipo de busqueda respecto a un rango de fecha selecionado**/
		
		if(parametros.getMotivoId()!=0){
			q.setParameter("motivoId", parametros.getMotivoId());
		}
		if(parametros.getTipoRadar()!=0){			
			q.setParameter("tipoRadar", parametros.getTipoRadar());
		}
		if(!parametros.getFechaInicioString().equalsIgnoreCase("null")){
			q.setParameter("fechaInicio", parametros.getFechaInicio())
			 .setParameter("fechaFin", parametros.getFechaFin());	
		}
		if(parametros.getOrigenPlaca()!=2){
			q.setParameter("origenPlaca", parametros.getOrigenPlaca());
		}
			q.setResultTransformer
			(Transformers.aliasToBean(FotomultaDeteccionDTO.class));
		
		return (List<FotomultaDeteccionDTO>)q.list();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<FotomultaMotivoCancelacionDTO> getCatMotivoCancelacion() {
		Criteria criteria = getCurrentSession().createCriteria(FotomultaMotivoCancelacionDTO.class);
		criteria.add(Restrictions.eq("activo",1));		
		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<FechasCancelacionVO> obtenerFechasCancelacion() {

	   String query="select distinct "+
			        "substr(fechaCancelacion,4,2) as mes,"+
			        "substr(fechaCancelacion,7,2) as anio "+
			        "from "+
			        "FotomultaDeteccionDTO "+
			        "where fechaCancelacion is not null "+
			        "and "+
			        "fotomultaMotivoCancelacionDTO.motivoId != 0 "+
			        "order by anio,mes   asc ";
		
		Query q = getCurrentSession().createQuery(query)
				 .setResultTransformer
				 (Transformers.aliasToBean(FechasCancelacionVO.class));
		
		return q.list();	
	}

}
