package mx.com.teclo.saicdmx.bitacora.cambios.impugnacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionAltaVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class BitSpImpugnacionNuevoImpl implements BitSpImpugnacionNuevo {

	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;
	
	@Override
	public void altaImpugnacion(ImpugnacionAltaVO impugnacionVO) {
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 7L, 1L, 
				impugnacionVO.getResultado(), "", 
				impugnacionVO.getEmpleadoId(),impugnacionVO.getReferencia(),
				"",ParametrosBitacoraEnum.ORIGEN_W.getParametro()
			);
		
	}

}
