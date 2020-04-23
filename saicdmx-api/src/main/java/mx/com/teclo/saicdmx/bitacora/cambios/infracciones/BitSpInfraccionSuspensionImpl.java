package mx.com.teclo.saicdmx.bitacora.cambios.infracciones;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.infracciones.SuspensionInfraccionSPVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class BitSpInfraccionSuspensionImpl implements BitSpInfraccionSuspension {
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	
	@Override
	public void bitSuspensionInfraccion(Long emp_id, SuspensionInfraccionSPVO suspensionInfraccionSPVO,ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs) {
		
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(5L);
		bitacoraCambiosVO.setConceptoId(16L);
		bitacoraCambiosVO.setValorOriginal(suspensionInfraccionSPVO.generaRespuestaVO().getpResultado());
		bitacoraCambiosVO.setValorFinal("");
		bitacoraCambiosVO.setCreadoPor(emp_id);
		bitacoraCambiosVO.setIdRegistro(suspensionInfraccionSPVO.generaRespuestaVO().getpResultado());
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		bitacoraCambiosVOs.add(bitacoraCambiosVO);
		
		bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
		
	}
	
	public void bitSuspensionInfraccionsp(Long emp_id, SuspensionInfraccionSPVO suspensionInfraccionSPVO) {
		
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(5L);
		bitacoraCambiosVO.setConceptoId(16L);
		bitacoraCambiosVO.setValorOriginal(suspensionInfraccionSPVO.getP_infrac_num());
		bitacoraCambiosVO.setValorFinal("");
		bitacoraCambiosVO.setCreadoPor(emp_id);
		bitacoraCambiosVO.setIdRegistro(suspensionInfraccionSPVO.getP_infrac_num());
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				
		bitacoraCambiosService.guardarBitacoraCambios(bitacoraCambiosVO);
		
	}

}
