package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpCambioAdscNuevoImpl implements BitSpCambioAdscNuevo{
	private final Long componente = 2L;
	
	@Override
	public BitacoraCambiosVO insertarBitacoraAdscripcionNuevo(String adId, String folio,Long modId){
		return new BitacoraCambiosVO(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
				componente,
				5L,
				"",
				folio,
				modId,
				adId.toString(),
				"",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro()
				);
	}
}
