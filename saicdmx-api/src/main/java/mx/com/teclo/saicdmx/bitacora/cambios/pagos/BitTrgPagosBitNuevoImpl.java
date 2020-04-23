package mx.com.teclo.saicdmx.bitacora.cambios.pagos;

import java.text.ParseException;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrgPagosBitNuevoImpl implements BitTrgPagosBitNuevo {

	private ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs;
	
	@Override
	public ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(PagoVO newPagoVo) throws ParseException {
		bitacoraCambiosVOs = new ArrayList<>();

		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(4L);
		bitacoraCambiosVO.setConceptoId(1L);
		bitacoraCambiosVO.setValorOriginal(newPagoVo.getTransaccion() != null ? newPagoVo.getTransaccion() : "");
		bitacoraCambiosVO.setValorFinal(newPagoVo.getTotalPago() != null ? newPagoVo.getTotalPago() : "");
		bitacoraCambiosVO.setCreadoPor(newPagoVo.getUsuario() != null ? Long.parseLong(newPagoVo.getUsuario()) : 0L);
		bitacoraCambiosVO.setIdRegistro(newPagoVo.getNumInfrac());
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		bitacoraCambiosVOs.add(bitacoraCambiosVO);

	  return bitacoraCambiosVOs;
	}
}