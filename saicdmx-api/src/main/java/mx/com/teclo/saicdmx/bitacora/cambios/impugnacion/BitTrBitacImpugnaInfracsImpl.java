package mx.com.teclo.saicdmx.bitacora.cambios.impugnacion;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.InfraccionImpugnacionInfracsVO;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrBitacImpugnaInfracsImpl implements BitTrBitacImpugnaInfracs {
	
	public ArrayList<BitacoraCambiosVO> compararInfracImpugnacion (InfraccionImpugnacionInfracsVO newImpugnacionVO,InfraccionImpugnacionInfracsVO oldImpugnacionVO){
		
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		
		
		if(!ComparaUtils.comparaCadenas(oldImpugnacionVO.getEstatus(), newImpugnacionVO.getEstatus())){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(7L);
			bitacoraCambiosVO.setConceptoId(11L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldImpugnacionVO.getEstatus()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newImpugnacionVO.getEstatus()));
			//bitacoraCambiosVO.setFechaCreacion(new Date());
			bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadoPor());
			bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getInfracNum());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		if(oldImpugnacionVO.getValido()!=newImpugnacionVO.getValido()){
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(7L);
		bitacoraCambiosVO.setConceptoId(12L);
		bitacoraCambiosVO.setValorOriginal(oldImpugnacionVO.getValido()==null?"":oldImpugnacionVO.getValido().toString());
		bitacoraCambiosVO.setValorFinal(newImpugnacionVO.getValido()==null?"":newImpugnacionVO.getValido().toString());
		//bitacoraCambiosVO.setFechaCreacion(new Date());
		bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadoPor());
		bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getInfracNum());
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		if(!ComparaUtils.comparaCadenas(oldImpugnacionVO.getMotivoInvalido(), newImpugnacionVO.getMotivoInvalido())){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(7L);
			bitacoraCambiosVO.setConceptoId(13L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldImpugnacionVO.getMotivoInvalido()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newImpugnacionVO.getMotivoInvalido()));
			//bitacoraCambiosVO.setFechaCreacion(new Date());
			bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadoPor());
			bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getInfracNum());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);	
		}
		// Para los casos de que valor inicial o final sea null 	Al parecer este esta de mas
//		if(oldImpugnacionVO.getMotivoInvalido()==null && newImpugnacionVO.getMotivoInvalido()!=null
//		 || newImpugnacionVO.getMotivoInvalido()==null && oldImpugnacionVO.getMotivoInvalido()!=null){
//			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
//			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
//			bitacoraCambiosVO.setComponenteId(7L);
//			bitacoraCambiosVO.setConceptoId(13L);
//			bitacoraCambiosVO.setValorOriginal(oldImpugnacionVO.getMotivoInvalido()==null?"":oldImpugnacionVO.getMotivoInvalido());
//			bitacoraCambiosVO.setValorFinal(newImpugnacionVO.getMotivoInvalido()==null?"":newImpugnacionVO.getMotivoInvalido());
//			//bitacoraCambiosVO.setFechaCreacion(new Date());
//			bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadoPor());
//			bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getInfracNum());
//			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
//			bitacoraCambiosVOs.add(bitacoraCambiosVO);	
//		}
			return bitacoraCambiosVOs;
	}
	
}
