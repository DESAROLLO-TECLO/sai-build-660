package mx.com.teclo.saicdmx.persistencia.hibernate.dao.atencionCiudadana;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.atencionCiudadana.AtencionCiudadanaTramitesDTO;



@Repository 
public class TramitesAtencionCiudadanaDAOImpl extends BaseDaoHibernate<AtencionCiudadanaTramitesDTO> implements TramitesAtencionCiudadanaDAO {

	@SuppressWarnings({ "unchecked" })
	@Override
	@Transactional
	public List<AtencionCiudadanaTramitesDTO> obtieneTramite(String fechaInicio, String fechaFin,
			Integer paramBusqueda, String valorBusqueda, Integer atendido,String avanzadoNombre,String avanzadoAP, String avanzadoAM,
			String avanzadoTel, String avanzadoCorreo,
            String avanzadoEmpresa, Boolean busquedaAvanzada,Integer cantidaMaxRegistros) {
		
		  Criteria criteria = getCurrentSession().createCriteria(AtencionCiudadanaTramitesDTO.class);
          criteria.addOrder(Order.desc("idACTramite"));
          criteria.setMaxResults(cantidaMaxRegistros);
          
          if(!fechaInicio.equals("") && !fechaFin.equals("")){
     		   criteria.add(Restrictions.sqlRestriction(" TRUNC (FH_ALTA) "
     			 +" between to_date ( '"+ fechaInicio +"', 'dd/MM/yyyy') "
     			 +"   AND to_date( '"+ fechaFin +"' , 'dd/MM/yyyy') "));	
     		}
          
          if(!busquedaAvanzada)
          {
          if(paramBusqueda!=1)
          {
        	  if(paramBusqueda==2)
        	  {
        		  criteria.add(Restrictions.eq("idACTramite", valorBusqueda));
        	  }else if(paramBusqueda==3)
        	  {
        		  criteria.add(Restrictions.eq("cdCPlaca", valorBusqueda));
        	  }else if(paramBusqueda==4)
        	  {
        		  criteria.add(Restrictions.eq("nbCPaterno", valorBusqueda));
        	  }else if(paramBusqueda==5)
        	  {
        		  criteria.createAlias("empId", "empId");
        		  criteria.add(Restrictions.eq("empId.empApePaterno", valorBusqueda));
        	  }
          }
          }else
          {
        	  
        	  if(!avanzadoNombre.equals(""))
        	  {
        		  criteria.add(Restrictions.eq("nbCiudadano", avanzadoNombre));
        	  }
        	  
        	  if(!avanzadoAP.equals(""))
        	  {
        		  criteria.add(Restrictions.eq("nbCPaterno", avanzadoAP));
        	  }
        	  
        	  if(!avanzadoAM.equals(""))
        	  {
        		  criteria.add(Restrictions.eq("nbMaterno", avanzadoAM));
        	  }
        	  
        	  
        	  if(!avanzadoCorreo.equals(""))
        	  {
        		  criteria.add(Restrictions.eq("txCCorreo", avanzadoCorreo));
        	  }
        	  
        	  if(!avanzadoEmpresa.equals(""))
        	  {
        		  criteria.add(Restrictions.eq("nbEmpresa", avanzadoEmpresa));
        	  }
        	  
        	  
          }
          
          if(atendido!=0)
          {
        	  if(atendido==1)
        	  {
        	   criteria.add(Restrictions.eq("stAtendido", true));
        	  }else if(atendido==2)
        	  {
        		criteria.add(Restrictions.eq("stAtendido", false));
        	  }
          }
       criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
       return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AtencionCiudadanaTramitesDTO> consultaDefaultModificacion(Integer nuDias, Boolean op) {
		List<AtencionCiudadanaTramitesDTO> list = new ArrayList<AtencionCiudadanaTramitesDTO>();
		Date hoy = new Date();
		Date fhIni = DateUtils.truncate(hoy, Calendar.DATE);
		Date fhFin = DateUtils.addDays(fhIni, -nuDias);
		if(op) {
		list = getCurrentSession().createQuery(
				"from AtencionCiudadanaTramitesDTO atc where trunc(atc.fhAlta) "
				+ "between :fhfin and :fhini and atc.stExpediente = false").setDate("fhfin", fhFin).setDate("fhini", fhIni).list();
		}else {
			list = getCurrentSession().createQuery(
					"from AtencionCiudadanaTramitesDTO atc where (trunc(atc.fhAlta) "
					+ "between :fhfin and :fhini and atc.stExpediente = false) or ("
					+ "trunc(atc.fhModificacion) = :fhMod)").setDate("fhfin", fhFin).setDate("fhini", fhIni).setDate("fhMod", fhIni).list();
		}
		return list;
	}
}
