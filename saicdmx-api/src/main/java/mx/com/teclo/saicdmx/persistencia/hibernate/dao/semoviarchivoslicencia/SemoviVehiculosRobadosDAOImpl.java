package mx.com.teclo.saicdmx.persistencia.hibernate.dao.semoviarchivoslicencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh.BloqueohhRegistroDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviVehiculoRobadoDTO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.VehiculosRobadosVO;

@Repository
public class SemoviVehiculosRobadosDAOImpl extends BaseDaoHibernate<SemoviVehiculoRobadoDTO> implements SemoviVehiculosRobadosDAO {

	public boolean insertVehiculosRobados(VehiculosRobadosVO robadosVO, Long next, Long idUsuario) throws ParseException {
	SemoviVehiculoRobadoDTO vehRobadosDTO = new SemoviVehiculoRobadoDTO();
	
	  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	  Date date;
	  if(robadosVO.getFechaRobo() != null){
		 date = formatter.parse(robadosVO.getFechaRobo());  
	  }else{
		  date = new Date();
	  }
	  
	vehRobadosDTO.setIdRobo(next);
	vehRobadosDTO.setTurnoDgant(robadosVO.getTurnoDgant());
	vehRobadosDTO.setExpediente(robadosVO.getExpediente());
	vehRobadosDTO.setFechaRobo(date);
	vehRobadosDTO.setPlacaVehiculo(robadosVO.getPlacaVehiculo());
	vehRobadosDTO.setNumSerie(robadosVO.getNumSerie());
	vehRobadosDTO.setNumMotor(robadosVO.getNumMotor());
	vehRobadosDTO.setIdMar(robadosVO.getIdMar());
	vehRobadosDTO.setIdMod(robadosVO.getIdMod());
	vehRobadosDTO.setIdColor(robadosVO.getIdColor());
	vehRobadosDTO.setIdAnio(robadosVO.getIdAnio() != null ? Long.valueOf(robadosVO.getIdAnio()) : 0);
	vehRobadosDTO.setIdEstatus(robadosVO.getIdEstatus());	
	vehRobadosDTO.setCreadoPor(idUsuario);
	vehRobadosDTO.setFechaCreacion(new Date());
	vehRobadosDTO.setModificadoPor(idUsuario);
	vehRobadosDTO.setUltimaModificacion(new Date());
	vehRobadosDTO.setActivo(1L);
	save(vehRobadosDTO);
		
		
		
		return true;
		
		
	}

	public SemoviVehiculoRobadoDTO buscaIdVehiculoRobado(Long idRobo) {
		SemoviVehiculoRobadoDTO semoviVehiculoRobadoDTO;
		Criteria criteria = getCurrentSession().createCriteria(SemoviVehiculoRobadoDTO.class);
        criteria.add(Restrictions.eq("idRobo", idRobo ));
        criteria.addOrder(Order.desc("fechaCreacion"));
        semoviVehiculoRobadoDTO = (SemoviVehiculoRobadoDTO) criteria.uniqueResult();	 
		return semoviVehiculoRobadoDTO != null ? semoviVehiculoRobadoDTO : null;
	}

	public SemoviVehiculoRobadoDTO updateVehiculoRob(VehiculosRobadosVO convertVO, Long long1) throws ParseException {
		SemoviVehiculoRobadoDTO semoviVehiculoRobadoDTO;
		Criteria criteria = getCurrentSession().createCriteria(SemoviVehiculoRobadoDTO.class);
        criteria.add(Restrictions.eq("idRobo", convertVO.getIdRobo() ));
        criteria.addOrder(Order.desc("fechaCreacion"));
        semoviVehiculoRobadoDTO = (SemoviVehiculoRobadoDTO) criteria.uniqueResult();
     
	     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	  	  Date date;
	  	  if(convertVO.getFechaRobo() != null){
	  		 date = formatter.parse(convertVO.getFechaRobo());  
	  	  }else{
	  		  date = new Date();
	  	  }
        semoviVehiculoRobadoDTO.setTurnoDgant(!convertVO.getTurnoDgant().equals(semoviVehiculoRobadoDTO.getTurnoDgant()) ? convertVO.getTurnoDgant() : semoviVehiculoRobadoDTO.getTurnoDgant() );
        semoviVehiculoRobadoDTO.setExpediente(!convertVO.getExpediente().equals(semoviVehiculoRobadoDTO.getExpediente()) ? convertVO.getExpediente() : semoviVehiculoRobadoDTO.getExpediente() );
        semoviVehiculoRobadoDTO.setFechaRobo(!date.equals(semoviVehiculoRobadoDTO.getFechaRobo()) ? date : semoviVehiculoRobadoDTO.getFechaRobo() );
        semoviVehiculoRobadoDTO.setPlacaVehiculo(!convertVO.getPlacaVehiculo().equals(semoviVehiculoRobadoDTO.getPlacaVehiculo()) ? convertVO.getPlacaVehiculo() : semoviVehiculoRobadoDTO.getPlacaVehiculo() );
        semoviVehiculoRobadoDTO.setNumSerie(!convertVO.getNumSerie().equals(semoviVehiculoRobadoDTO.getNumSerie()) ? convertVO.getNumSerie() : semoviVehiculoRobadoDTO.getNumSerie() );
        semoviVehiculoRobadoDTO.setNumMotor(!convertVO.getNumMotor().equals(semoviVehiculoRobadoDTO.getNumMotor()) ? convertVO.getNumMotor() : semoviVehiculoRobadoDTO.getNumMotor() );
        semoviVehiculoRobadoDTO.setIdMar(!Long.valueOf(convertVO.getIdMar()).equals(semoviVehiculoRobadoDTO.getIdMar()) ? Long.valueOf(convertVO.getIdMar()) : semoviVehiculoRobadoDTO.getIdMar() );
        semoviVehiculoRobadoDTO.setIdMod(!Long.valueOf(convertVO.getIdMod()).equals(semoviVehiculoRobadoDTO.getIdMod()) ? Long.valueOf(convertVO.getIdMod()) : semoviVehiculoRobadoDTO.getIdMod() );
        semoviVehiculoRobadoDTO.setIdColor(!Long.valueOf(convertVO.getIdColor()).equals(semoviVehiculoRobadoDTO.getIdColor()) ? Long.valueOf(convertVO.getIdColor()) : semoviVehiculoRobadoDTO.getIdColor() );
        semoviVehiculoRobadoDTO.setIdAnio(!convertVO.getIdAnio().equals(semoviVehiculoRobadoDTO.getIdAnio()) ? convertVO.getIdAnio() : semoviVehiculoRobadoDTO.getIdAnio() );
		semoviVehiculoRobadoDTO.setModificadoPor(!long1.equals(semoviVehiculoRobadoDTO.getModificadoPor()) ? long1 : semoviVehiculoRobadoDTO.getModificadoPor() );
		semoviVehiculoRobadoDTO.setUltimaModificacion(!new Date().equals(semoviVehiculoRobadoDTO.getUltimaModificacion()) ? new Date() : semoviVehiculoRobadoDTO.getUltimaModificacion() );
		semoviVehiculoRobadoDTO.setIdEstatus(!convertVO.getIdEstatus().equals(semoviVehiculoRobadoDTO.getIdEstatus()) ? convertVO.getIdEstatus() : semoviVehiculoRobadoDTO.getIdEstatus() );
    	getCurrentSession().saveOrUpdate(semoviVehiculoRobadoDTO);
 		
 		return semoviVehiculoRobadoDTO;
 	}


}
