package mx.com.teclo.saicdmx.bitacora.cambios.ingresos;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.IngresoInfraccionVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrgIngresoBitNuevoImpl implements BitTrgIngresoBitNuevo {

	private ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs;
	@Override
	public ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(IngresoInfraccionVO ingresoInfracVO) {
		
		bitacoraCambiosVOs = new ArrayList<>();
		
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(11L);
		bitacoraCambiosVO.setConceptoId(1L);
		bitacoraCambiosVO.setValorOriginal(ingresoInfracVO.getNum_resguardo() != null ? ingresoInfracVO.getNum_resguardo() : "" );
		bitacoraCambiosVO.setValorFinal(ingresoInfracVO.getDep_id() != null ? ingresoInfracVO.getDep_id().toString(): "");
		bitacoraCambiosVO.setCreadoPor(
				ingresoInfracVO.getEmp_id() != 0L ? ingresoInfracVO.getEmp_id() : 0L );
		bitacoraCambiosVO.setIdRegistro(ingresoInfracVO.getInfrac_num() != null ? ingresoInfracVO.getInfrac_num(): "");
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		bitacoraCambiosVOs.add(bitacoraCambiosVO);
		
		return bitacoraCambiosVOs;
	}

}
