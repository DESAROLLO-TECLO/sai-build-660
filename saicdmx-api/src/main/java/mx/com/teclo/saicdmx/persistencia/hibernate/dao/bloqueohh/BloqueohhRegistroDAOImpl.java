package mx.com.teclo.saicdmx.persistencia.hibernate.dao.bloqueohh;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh.BloqueohhRegistroDTO;
 
 
@Repository 
public class BloqueohhRegistroDAOImpl extends BaseDaoHibernate<BloqueohhRegistroDTO> implements BloqueohhRegistroDAO{

 
	@SuppressWarnings("unchecked")
	@Override
	public List<BloqueohhRegistroDTO> obtenerRegistroshh(Integer estatusBloqueo, Long oficialId, Integer tipoBloqueo,
			String numeroSeriehh,String fechaInicio,String fechaFin) {
		
	      Criteria criteria = getCurrentSession().createCriteria(BloqueohhRegistroDTO.class);
          criteria.add(Restrictions.eq("activo", new Integer(1)));
          criteria.addOrder(Order.desc("fechaBloqueo"));
      
	           
          
          		if(!fechaInicio.equals("null") && !fechaFin.equals("null")){
//            	   criteria.add(Restrictions.between("fechaBloqueo", fechaInicio, fechaFin));
          		   criteria.add(Restrictions.sqlRestriction(" TRUNC (FECHA_BLOQUEO) "
          			 +" between to_date ( '"+ fechaInicio +"', 'dd/MM/yyyy') "
          			 +"   AND to_date( '"+ fechaFin +"' , 'dd/MM/yyyy') "));
          			
          		}
          		
          		if (!fechaInicio.equals("null") ) {
//        			criteria.add(Restrictions.ge("fechaBloqueo",fechaInicio));	
          		  criteria.add(Restrictions.sqlRestriction(" TRUNC (FECHA_BLOQUEO) >= "
               			 +"  to_date ( '"+ fechaInicio +"', 'dd/MM/yyyy') " ));
        		}
          		
        		if(!fechaFin.equals("null")){
//        			criteria.add(Restrictions.le("fechaBloqueo",fechaFin));
        			criteria.add(Restrictions.sqlRestriction(" TRUNC (FECHA_BLOQUEO) <= "
                  			 +"  to_date ( '"+ fechaFin +"', 'dd/MM/yyyy') " ));
        		}
               
              
          
	    	   if (estatusBloqueo != 2){
		           criteria.add(Restrictions.eq("estatusBloqueo", estatusBloqueo));
		       }
	           
	           if (tipoBloqueo != 3){
	        	
	        	   criteria.createAlias("bloqueohhCatTipoBloqueo", "bloqueohhCatTipoBloqueo");
	               criteria.add(Restrictions.eq("bloqueohhCatTipoBloqueo.codigo", tipoBloqueo));
	           }
	           if (!numeroSeriehh.equals("null")){
	        	   
	        	   criteria.add(Restrictions.eq("numeroSeriehh", numeroSeriehh));
	           }
	           if (oficialId != null){

	        	   criteria.add(Restrictions.eq("oficialId", oficialId));
	           }
               	       
		return criteria.list();
	}
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<BloqueohhRegistroDTO> obtenerRegistrosBloqueadoshh(Long oficialId, String numeroSeriehh) {
	
		Criteria criteria = getCurrentSession().createCriteria(BloqueohhRegistroDTO.class);
        criteria.add(Restrictions.eq("activo", new Integer(1)));
        criteria.add(Restrictions.eq("estatusBloqueo", new Integer(1)));
        criteria.addOrder(Order.asc("ultimaModificacion"));

        if (oficialId != null){
        criteria.add(Restrictions.eq("oficialId", oficialId));
        }
        if (!numeroSeriehh.equals("null")){
        criteria.add(Restrictions.eq("numeroSeriehh", numeroSeriehh));
        }

		return criteria.list();
	}
	
	@Override
	public BloqueohhRegistroDTO desbloquearHandHeld(Long idHandHeld,Long idUsarioFirmado) {
		BloqueohhRegistroDTO bloqueohhRegistroDTO;
		Criteria criteria = getCurrentSession().createCriteria(BloqueohhRegistroDTO.class);
		criteria.add(Restrictions.eq("registroId", idHandHeld));
		bloqueohhRegistroDTO = (BloqueohhRegistroDTO) criteria.uniqueResult();
 		
 		bloqueohhRegistroDTO.setEstatusBloqueo(new Integer(0));
		bloqueohhRegistroDTO.setUsuarioModifica(idUsarioFirmado);
		bloqueohhRegistroDTO.setUltimaModificacion(new Date());
		bloqueohhRegistroDTO.setFechaDesbloqueo(new Date());
 		getCurrentSession().saveOrUpdate(bloqueohhRegistroDTO);
		return bloqueohhRegistroDTO;
	}

}
