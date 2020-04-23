package mx.com.teclo.saicdmx.bitacora.cambios.cajas;

import java.text.ParseException;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaVO;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrBitacCajasImpl implements BitTrBitacCajas {

	private ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs;
	
	@Override
	public ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(VCajaConsultaVO newCajaVO, VCajaConsultaVO oldCajaVO) throws ParseException {
		bitacoraCambiosVOs = new ArrayList<>();

		if (!ComparaUtils.comparaCadenas(oldCajaVO.getDepId(), newCajaVO.getDepId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(12L);
			bitacoraCambiosVO.setConceptoId(1L);
			bitacoraCambiosVO.setValorOriginal(oldCajaVO.getDepId() != null ? oldCajaVO.getDepId() : "");
			bitacoraCambiosVO.setValorFinal(newCajaVO.getDepId() != null ? newCajaVO.getDepId() : "");
			bitacoraCambiosVO.setCreadoPor(newCajaVO.getModificadoPor() != null ? Long.parseLong(newCajaVO.getModificadoPor()) : 0L);
			bitacoraCambiosVO.setIdRegistro(newCajaVO.getCajaIdD());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if (!ComparaUtils.comparaCadenas(oldCajaVO.getCajaCod(), newCajaVO.getCajaCod())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(12L);
			bitacoraCambiosVO.setConceptoId(2L);
			bitacoraCambiosVO.setValorOriginal(oldCajaVO.getCajaCod() != null ? oldCajaVO.getCajaCod() : "");
			bitacoraCambiosVO.setValorFinal(newCajaVO.getCajaCod() != null ? newCajaVO.getCajaCod() : "");
			bitacoraCambiosVO.setCreadoPor(newCajaVO.getModificadoPor() != null ? Long.parseLong(newCajaVO.getModificadoPor()) : 0L);
			bitacoraCambiosVO.setIdRegistro(newCajaVO.getCajaIdD());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if (!ComparaUtils.comparaCadenas(oldCajaVO.getCajaNombre(), newCajaVO.getCajaNombre())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(12L);
			bitacoraCambiosVO.setConceptoId(3L);
			bitacoraCambiosVO.setValorOriginal(oldCajaVO.getCajaNombre() != null ? oldCajaVO.getCajaNombre() : "");
			bitacoraCambiosVO.setValorFinal(newCajaVO.getCajaNombre() != null ? newCajaVO.getCajaNombre() : "");
			bitacoraCambiosVO.setCreadoPor(newCajaVO.getModificadoPor() != null ? Long.parseLong(newCajaVO.getModificadoPor()) : 0L);
			bitacoraCambiosVO.setIdRegistro(newCajaVO.getCajaIdD());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if (!ComparaUtils.comparaCadenas(oldCajaVO.getCajaNumPago(), newCajaVO.getCajaNumPago())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(12L);
			bitacoraCambiosVO.setConceptoId(4L);
			bitacoraCambiosVO.setValorOriginal(oldCajaVO.getCajaNumPago() != null ? oldCajaVO.getCajaNumPago() : "0");
			bitacoraCambiosVO.setValorFinal(newCajaVO.getCajaNumPago() != null ? newCajaVO.getCajaNumPago() : "0");
			bitacoraCambiosVO.setCreadoPor(newCajaVO.getModificadoPor() != null ? Long.parseLong(newCajaVO.getModificadoPor()) : 0L);
			bitacoraCambiosVO.setIdRegistro(newCajaVO.getCajaIdD());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if (!ComparaUtils.comparaCadenas(oldCajaVO.getCajaNumTran(), newCajaVO.getCajaNumTran())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(12L);
			bitacoraCambiosVO.setConceptoId(5L);
			bitacoraCambiosVO.setValorOriginal(oldCajaVO.getCajaNumTran() != null ? oldCajaVO.getCajaNumTran() : "0");
			bitacoraCambiosVO.setValorFinal(newCajaVO.getCajaNumTran() != null ? newCajaVO.getCajaNumTran() : "0");
			bitacoraCambiosVO.setCreadoPor(newCajaVO.getModificadoPor() != null ? Long.parseLong(newCajaVO.getModificadoPor()) : 0L);
			bitacoraCambiosVO.setIdRegistro(newCajaVO.getCajaIdD());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		if (!ComparaUtils.comparaCadenas(oldCajaVO.getCajaNumCorte(), newCajaVO.getCajaNumCorte())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(12L);
			bitacoraCambiosVO.setConceptoId(6L);
			bitacoraCambiosVO.setValorOriginal(oldCajaVO.getCajaNumCorte() != null ? oldCajaVO.getCajaNumCorte() : "0");
			bitacoraCambiosVO.setValorFinal(newCajaVO.getCajaNumCorte() != null ? newCajaVO.getCajaNumCorte() : "0");
			bitacoraCambiosVO.setCreadoPor(newCajaVO.getModificadoPor() != null ? Long.parseLong(newCajaVO.getModificadoPor()) : 0L);
			bitacoraCambiosVO.setIdRegistro(newCajaVO.getCajaIdD());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		if (!ComparaUtils.comparaCadenas(oldCajaVO.getCajaEstatus(), newCajaVO.getCajaEstatus())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(12L);
			bitacoraCambiosVO.setConceptoId(7L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldCajaVO.getCajaEstatus()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newCajaVO.getCajaEstatus()));
			bitacoraCambiosVO.setCreadoPor(newCajaVO.getModificadoPor() != null ? Long.parseLong(newCajaVO.getModificadoPor()) : 0L);
			bitacoraCambiosVO.setIdRegistro(newCajaVO.getCajaIdD());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		if (!ComparaUtils.comparaCadenas(oldCajaVO.getEmpId(), newCajaVO.getEmpId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(12L);
			bitacoraCambiosVO.setConceptoId(8L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldCajaVO.getEmpId()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newCajaVO.getEmpId()));
			bitacoraCambiosVO.setCreadoPor(newCajaVO.getModificadoPor() != null ? Long.parseLong(newCajaVO.getModificadoPor()) : 0L);
			bitacoraCambiosVO.setIdRegistro(newCajaVO.getCajaIdD());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

	  return bitacoraCambiosVOs;
	}
}