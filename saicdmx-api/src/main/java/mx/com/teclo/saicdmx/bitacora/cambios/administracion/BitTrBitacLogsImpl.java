package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.logs.WebLogsDTO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrBitacLogsImpl implements BitTrBitacLogs {
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	final long componente = 2L;
	
	@Override
	public BitacoraCambiosVO insertBitacEstatusWebLogs(WebLogsDTO webLogsDTO, Long usuarioId) {
	
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(componente);
		bitacoraCambiosVO.setConceptoId(31L);
		if(webLogsDTO.getLogEstatus()=="A")
			bitacoraCambiosVO.setValorOriginal("D");
		else
			bitacoraCambiosVO.setValorOriginal("A");
		bitacoraCambiosVO.setValorFinal(webLogsDTO.getLogEstatus());
		bitacoraCambiosVO.setCreadoPor(usuarioId);
		bitacoraCambiosVO.setIdRegistro(webLogsDTO.getLogId().toString());
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		
		return bitacoraCambiosVO;

	}
	
	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(LogsVO oldLog, LogsVO newLog) throws ParseException {

		List<BitComponente> filtro = new ArrayList<BitComponente>();

		filtro.add(new BitComponente(36L, "getLogNombre"));
		filtro.add(new BitComponente(33L, "getRutaArchivo"));
		filtro.add(new BitComponente(32L, "getLogTipoArchivos"));
		filtro.add(new BitComponente(37L, "getLogDescripcion"));

		ArrayList<BitacoraCambiosVO> lista = new ArrayList<BitacoraCambiosVO>();
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(oldLog, newLog, LogsVO.class,BitComponente.convertToList(filtro));

		try {
			for (Method m : listaCambios) {
				String oldVal = m.invoke(oldLog) != null ? m.invoke(oldLog).toString() : "";
				String newVal = m.invoke(newLog) != null ? m.invoke(newLog).toString() : "";
				lista.add(new BitacoraCambiosVO(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(), oldVal, newVal,
						usuarioFirmadoService.getUsuarioFirmadoVO().getId(), newLog.getLogId().toString(),
						"", ParametrosBitacoraEnum.ORIGEN_W.getParametro()));
			}
		} catch (InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			return lista;
		}
	}
}
