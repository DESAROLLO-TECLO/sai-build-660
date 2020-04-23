package mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.ControlAlmacenVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpSuministroAlmacenImpl implements BitSpSuministroAlmacen{

	@Override
	public BitacoraCambiosVO bitacoraCambiosAltaAlmacen(ControlAlmacenVO almacenVO) throws ParseException {
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(3L);
		bitacoraCambiosVO.setConceptoId(1L);
		bitacoraCambiosVO.setValorOriginal("");
		bitacoraCambiosVO.setValorFinal(almacenVO.getTxt_num_remision());
		bitacoraCambiosVO.setCreadoPor(almacenVO.getUserID() != null ? almacenVO.getUserID() : 0L);
		bitacoraCambiosVO.setIdRegistro(almacenVO.getResultado());
		bitacoraCambiosVO.setIdRegistroDescripcion("");
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return bitacoraCambiosVO;
	}
}
