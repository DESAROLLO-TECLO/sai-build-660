package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.OperacionesExtemporaneasVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * 
 * @author Javier Flores
 *
 */
@Service
public class BitProcHabilitarExtemporaneaImpl implements BitProcHabilitarExtemporanea{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BitacoraCambiosVO habilitarExtemporanea(OperacionesExtemporaneasVO parametrosVO) {
		
		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(2L); 
		c.setConceptoId(28L); 
		c.setValorOriginal(parametrosVO.getCajaCod()); 
		c.setValorFinal(parametrosVO.getFecha());
		c.setCreadoPor(new Long(parametrosVO.getSupervisorId())); 
		c.setIdRegistro(parametrosVO.getCajaId().toString()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
	}

}
