package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaExtDesactivarVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * 
 * @author Javier Flores
 *
 */
@Service
public class BitProcDeshabilitarExtemporaneaImpl implements BitProcDeshabilitarExtemporanea{

	@Override
	public BitacoraCambiosVO deshabilitarExtemporanea(VCajaExtDesactivarVO parametrosVO) {
		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(2L); 
		c.setConceptoId(29L); 
		c.setValorOriginal(""); 
		c.setValorFinal(parametrosVO.getCajaCod());
		c.setCreadoPor(new Long(parametrosVO.getSupervisorId())); 
		c.setIdRegistro(parametrosVO.getCajaId().toString()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
	}
}
