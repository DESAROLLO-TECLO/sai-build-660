package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bitacora.SoporteBitacoraDTO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.EjecutaSoporteOperacionVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

@Repository
public class BitSoporteBitacoraImpl extends  BaseDaoHibernate<SoporteBitacoraDTO> implements BitSoporteBitacora{

	
	@Transactional
	public void persisteSoporteBitacora(EjecutaSoporteOperacionVO objeto, Long vEmpAutId, Long cambioId, Long componenteId, String fechaSoporte) {
		
		Long modificadoPor=  null;
		String valorOriginal = null;
		String valorFinal = null;
		
		modificadoPor=  objeto.getUsuarioId();
		SoporteBitacoraDTO soporteBitacoraDTO = new SoporteBitacoraDTO();
		soporteBitacoraDTO.setCambioId(cambioId);
		soporteBitacoraDTO.setComponenteId(componenteId);
		soporteBitacoraDTO.setConceptoId(objeto.getTipoSoporte().longValue());
		soporteBitacoraDTO.setFechaCambio(new Date());
		soporteBitacoraDTO.setAutorizadoPor(vEmpAutId);
		
		
		if (objeto.getTipoSoporte().equals(1) || objeto.getTipoSoporte().equals(2)
				|| objeto.getTipoSoporte().equals(3) || objeto.getTipoSoporte().equals(4)
				|| objeto.getTipoSoporte().equals(5) || objeto.getTipoSoporte().equals(10)
				|| objeto.getTipoSoporte().equals(14) || objeto.getTipoSoporte().equals(18)) {
			
			 
			 valorOriginal = objeto.getInfraccionNum();
			 valorFinal = "";
			
		}else if(objeto.getTipoSoporte().equals(6)){
			
			 
			 valorOriginal = objeto.getInfraccionNum();
			 valorFinal = objeto.getInfraccionPreImpresa();
			 
		}else if(objeto.getTipoSoporte().equals(7) || objeto.getTipoSoporte().equals(8) 
				|| objeto.getTipoSoporte().equals(15)) {
			
			 valorOriginal = objeto.getInfraccionNum();
			 valorFinal = objeto.getNumOficio();
			
		}else if (objeto.getTipoSoporte().equals(9)) {
			
			 valorOriginal = objeto.getInfraccionNum();
			 valorFinal = objeto.getInfraccionNumNueva();
			 
		}else if (objeto.getTipoSoporte().equals(11)) {
			
			 valorOriginal = objeto.getInfraccionNum();
			 valorFinal = objeto.getInfraccionPlaca();
			 
		}else if (objeto.getTipoSoporte().equals(12)) {
			
			 valorOriginal = objeto.getInfraccionNum();
			 valorFinal = objeto.getResguardo();
			 
		}else if (objeto.getTipoSoporte().equals(13) || objeto.getTipoSoporte().equals(16)) {
			
			 valorOriginal = objeto.getInfraccionNum();
			 valorFinal = fechaSoporte;
			 
		}else if (objeto.getTipoSoporte().equals(17)) {
			
			valorOriginal = fechaSoporte;
			valorFinal = objeto.getFolioInicial() + "," + objeto.getFolioFinal() + "," + objeto.getReciboTotal()
			+ "," + objeto.getReciboUtilizados() + "," + objeto.getReciboCancelados() + "," + objeto.getOperacionEfectuada();
		}
		
		
		
		
		soporteBitacoraDTO.setModificadoPor(modificadoPor);
		soporteBitacoraDTO.setValorOriginal(valorOriginal);
		soporteBitacoraDTO.setValorFinal(valorFinal);
		
		getCurrentSession().save(soporteBitacoraDTO);
		getCurrentSession().flush();
		
	}	
	
}
