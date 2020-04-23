package mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.SuministroAreasVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpSuministroNuevoImpl implements BitSpSuministroNuevo {
	
	@Override
	public BitacoraCambiosVO bitacoraCambiosAltaSuministro(SuministroAreasVO suministroVO) throws ParseException{
			
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(3L);
		bitacoraCambiosVO.setConceptoId(2L);
		bitacoraCambiosVO.setValorOriginal("");
		bitacoraCambiosVO.setValorFinal(suministroVO.getTxt_num_recibo().toString());
		bitacoraCambiosVO.setCreadoPor(suministroVO.getIdUser() != null ? suministroVO.getIdUser() : 0L);
		bitacoraCambiosVO.setIdRegistro(suministroVO.getResultado());
		bitacoraCambiosVO.setIdRegistroDescripcion("");
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return bitacoraCambiosVO;
	}
}
