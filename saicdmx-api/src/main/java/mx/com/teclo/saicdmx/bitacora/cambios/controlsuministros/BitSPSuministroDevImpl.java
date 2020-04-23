package mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DevolucionesVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSPSuministroDevImpl implements BitSPSuministroDev{

	@Override
	public BitacoraCambiosVO guardarCambiosBitacoraAltaDev(DevolucionesVO devolucionVO) throws ParseException {
		
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(3L);
		bitacoraCambiosVO.setConceptoId(6L);
		bitacoraCambiosVO.setValorOriginal("");
		bitacoraCambiosVO.setValorFinal(devolucionVO.getTxt_num_recibo());
		bitacoraCambiosVO.setCreadoPor(devolucionVO.getIdUser() != 0 ? devolucionVO.getIdUser() : 0L);
		bitacoraCambiosVO.setIdRegistro(devolucionVO.getResultado());
		bitacoraCambiosVO.setIdRegistroDescripcion("");
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return bitacoraCambiosVO;
		
	}

}
