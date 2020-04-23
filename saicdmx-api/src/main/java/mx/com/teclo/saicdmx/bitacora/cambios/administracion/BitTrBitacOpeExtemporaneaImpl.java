package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.OperacionExtDTO;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
@Service
public class BitTrBitacOpeExtemporaneaImpl implements BitTrBitacOpeExtemporanea{
	
	private ArrayList<BitacoraCambiosVO> listaBitacoraCambios=null;
	private RutinasTiempoImpl rutinasTiempo =null;

	@Override
	public ArrayList<BitacoraCambiosVO> obtenerParametrosBitacora(OperacionExtDTO newDTO, OperacionExtDTO oldDTO,Long idUsuario)
			throws ParseException {
		
		listaBitacoraCambios = new ArrayList<>();
		rutinasTiempo = new RutinasTiempoImpl();
		
		if (!ComparaUtils.comparaCadenas(oldDTO.getUsuarioId(), newDTO.getUsuarioId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(14L);
			bitacoraCambiosVO.setConceptoId(1L);
			bitacoraCambiosVO.setValorOriginal(oldDTO.getUsuarioId().toString() != null ? oldDTO.getUsuarioId().toString() : "");
			bitacoraCambiosVO.setValorFinal(newDTO.getUsuarioId().toString() != null ? newDTO.getUsuarioId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(idUsuario);
			bitacoraCambiosVO.setIdRegistro(newDTO.getCajaId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			listaBitacoraCambios.add(bitacoraCambiosVO);
		}
		
//		Map<String, String> mapaFechaOld = rutinasTiempo.obtenerFechaDesglosada(oldDTO.getFechaHabilitada());		
//		Map<String, String> mapaFechaNew = rutinasTiempo.obtenerFechaDesglosada(newDTO.getFechaHabilitada());
//		String fechaOld = getFormatDDMhontYY(mapaFechaOld);
//		String fechaNew = getFormatDDMhontYY(mapaFechaNew);	
		String fechaOld = rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy",oldDTO.getFechaHabilitada());
		String fechaNew = rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy",newDTO.getFechaHabilitada());	
		
		if (!ComparaUtils.comparaCadenas(fechaOld, fechaNew)) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(14L);
			bitacoraCambiosVO.setConceptoId(2L);
			bitacoraCambiosVO.setValorOriginal(fechaOld);
			bitacoraCambiosVO.setValorFinal(fechaNew);
			bitacoraCambiosVO.setCreadoPor(idUsuario);
			bitacoraCambiosVO.setIdRegistro(newDTO.getCajaId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			listaBitacoraCambios.add(bitacoraCambiosVO);
		}

		if (!ComparaUtils.comparaCadenas(oldDTO.getCapStatus(), newDTO.getCapStatus())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(14L);
			bitacoraCambiosVO.setConceptoId(3L);
			bitacoraCambiosVO.setValorOriginal(oldDTO.getCapStatus());
			bitacoraCambiosVO.setValorFinal(newDTO.getCapStatus());
			bitacoraCambiosVO.setCreadoPor(idUsuario);
			bitacoraCambiosVO.setIdRegistro(newDTO.getCajaId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			listaBitacoraCambios.add(bitacoraCambiosVO);
		}

		return listaBitacoraCambios;
	}
	
	public static String getFormatDDMhontYY(Map<String, String> mapa){				
			String fechaFormat="";
			String dia=String.valueOf(mapa.get("day")).length() > 1 ? String.valueOf(mapa.get("day")) : "0".concat(String.valueOf(mapa.get("day")));
			String mes=mapa.get("month").substring(0, 3);
			String anio=String.valueOf(mapa.get("year")).substring(2);	
			fechaFormat = dia+"-"+mes+"-"+anio;
		return fechaFormat;
	}


}
