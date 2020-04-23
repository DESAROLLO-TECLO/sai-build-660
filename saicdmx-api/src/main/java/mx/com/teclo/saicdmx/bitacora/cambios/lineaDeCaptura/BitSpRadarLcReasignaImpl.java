package mx.com.teclo.saicdmx.bitacora.cambios.lineaDeCaptura;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpRadarLcReasignaImpl implements BitSpRadarLcReasigna {

	
	@Override
	public BitacoraCambiosVO bitacoraCambioReasignarLineaCaptura(RespuestaWSReasignaLineaCapturaVO respuesta) throws ParseException{
		
		BitacoraCambiosVO bitCambio = new BitacoraCambiosVO();
			
		if(!respuesta.getEstatusResultado().equals("OK")){
			bitCambio.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitCambio.setComponenteId(8L);
			bitCambio.setComponenteId(8L);
			bitCambio.setConceptoId(4L);
			bitCambio.setValorOriginal(respuesta.getFolio());
			bitCambio.setValorFinal(respuesta.getError_descripcion());
			bitCambio.setCreadoPor(Long.parseLong(respuesta.getUsuario()));
			bitCambio.setIdRegistro("0");
			bitCambio.setIdRegistroDescripcion("");
			bitCambio.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}else{
			bitCambio.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitCambio.setComponenteId(8L);
			bitCambio.setConceptoId(3L);
			bitCambio.setValorOriginal(respuesta.getFolio());
			bitCambio.setValorFinal(respuesta.getLineacaptura());
			bitCambio.setCreadoPor(Long.parseLong(respuesta.getUsuario()));
			bitCambio.setIdRegistro("0");
			bitCambio.setIdRegistroDescripcion("");
			bitCambio.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
		
		return bitCambio;
	}
}
