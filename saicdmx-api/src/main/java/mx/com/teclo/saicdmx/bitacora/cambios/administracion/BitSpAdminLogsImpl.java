package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.logs.WebLogsDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.perfil.PerfilDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.PerfilDTO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpAdminLogsImpl implements BitSpAdminLogs {
	@Value("${app.config.codigo}")
	private String codeApplication;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private PerfilDAO perfilDAO;
	@Autowired
	private WebLogsDAO webLogsDAO;
	@Autowired
    private BitTrBitacLogs bitTrBitacLogs;
	
	private PerfilDTO perfil = new PerfilDTO();
	final long componente = 2L;
	
	@Override
	public List<BitacoraCambiosVO> bitSpAdminLogsMethod(LogsVO logsVO, LogsVO oldLog) throws ParseException {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		switch (logsVO.getTipoOperacion()) {
		case "A":
			Long l = logId(logsVO.getLogDescripcion(), logsVO.getLogNombre(), logsVO.getLogTipoArchivos(), logsVO.getRutaArchivo());
			l = l == null ? 0L : l;
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(componente);
			bitacoraCambiosVO.setConceptoId(30L);
			bitacoraCambiosVO.setValorOriginal("");
			bitacoraCambiosVO.setValorFinal(logsVO.getLogNombre());
			bitacoraCambiosVO.setCreadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			bitacoraCambiosVO.setIdRegistro(l.toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
			break;
		case "M":
			bitacoraCambiosVOs = bitTrBitacLogs.guardarCambiosBitacora(oldLog,logsVO);
			break;
		case "AP":
			perfil = perfilDAO.getPerfilById(logsVO.getPerfilId(), codeApplication);
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(componente);
			bitacoraCambiosVO.setConceptoId(34L);
			bitacoraCambiosVO.setValorOriginal("");
			bitacoraCambiosVO.setValorFinal(perfil.getPerfilNombre());
			bitacoraCambiosVO.setCreadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			bitacoraCambiosVO.setIdRegistro(logsVO.getLogId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
			break;
		case "EP":
			perfil = perfilDAO.getPerfilById(logsVO.getPerfilId(), codeApplication);
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(componente);
			bitacoraCambiosVO.setConceptoId(35L);
			bitacoraCambiosVO.setValorOriginal("");
			bitacoraCambiosVO.setValorFinal(perfil.getPerfilNombre());
			bitacoraCambiosVO.setCreadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			bitacoraCambiosVO.setIdRegistro(logsVO.getLogId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
			break;
		default:
			break;
		}		
				
		return bitacoraCambiosVOs;

	}
	private Long logId(String des, String name, String tipArchivo, String ruta) {
		return webLogsDAO.ontieneLog(des, name, tipArchivo, ruta);
	}
}
