package mx.com.teclo.saicdmx.negocio.service.logs;

import java.text.ParseException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpAdminLogs;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitTrBitacLogs;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.bitacora.BitacoraCambiosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.logs.PerfilLogsDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.logs.WebLogsDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bitacora.BitacoraCambiosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.logs.PerfilLogsDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.logs.WebLogsDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.logs.LogsMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.dto.PerfilDTO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class LogsProcedureServiceImpl implements LogsProcedureService{
	
	//private static final String NOMBRE_CLASE = ""+LogsProcedureServiceImpl.class;

	@Autowired
	private LogsMyBatisDAO logsMyBatisDAO;
	@Autowired
	private BitacoraCambiosDAO bitacoraCambiosDAO;
	@Autowired
	private PerfilLogsDAO perfilLogsDAO;
	@Autowired
	private WebLogsDAO webLogsDAO;
	@Autowired
    private BitacoraCambiosService bitacoraCambiosService;
//	@Autowired
//    private BitAdminLogs bitAdminLogs;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
    private BitSpAdminLogs bitSpAdminLogs;
	@Autowired
    private BitTrBitacLogs bitTrBitacLogs;
	
	
	@Override
	@Transactional
	public void agregarPerfilALog(Long logId, Long perfilId, Long usuarioId) {
		
		PerfilDTO perfilDTO = new PerfilDTO();
		perfilDTO.setPerfilId(perfilId);		
		WebLogsDTO webLogDTO = new WebLogsDTO();
		webLogDTO.setLogId(logId);
		
		PerfilLogsDTO perfilLogDTO = new PerfilLogsDTO();
		perfilLogDTO.setPerfilDTO(perfilDTO);
		perfilLogDTO.setWebLogsDTO(webLogDTO);	
		perfilLogsDAO.save(perfilLogDTO);

//		bitacoraCambiosService.guardarBitacoraCambios(bitAdminLogs.insertBitacNuevoPerfilALog(perfilLogDTO, usuarioId));
		
	}

	@Override
	@Transactional
	public void eliminarPerfilALog(Long logId, Long perfilId, Long usuarioId) {
		
		PerfilDTO perfilDTO = new PerfilDTO();
		perfilDTO.setPerfilId(perfilId);		
		WebLogsDTO webLogDTO = new WebLogsDTO();
		webLogDTO.setLogId(logId);
		
		PerfilLogsDTO perfilLogDTO = new PerfilLogsDTO();
		perfilLogDTO.setPerfilDTO(perfilDTO);
		perfilLogDTO.setWebLogsDTO(webLogDTO);
		perfilLogsDAO.delete(perfilLogDTO);
		
//		bitacoraCambiosService.guardarBitacoraCambios(bitAdminLogs.insertBitacEliminarPerfilALog(perfilLogDTO, usuarioId));
		
	}

	@Override
	@Transactional
	public void cambioDeEstatus(Long logId, String accion) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		Long usuarioId = usuarioFirmadoVO.getId();
		
		accion = accion.equals("A") ? "D" : "A";
		WebLogsDTO webLogsDTO = webLogsDAO.findOne(logId);
		webLogsDTO.setLogEstatus(accion);
		webLogsDAO.update(webLogsDTO);
		
		bitacoraCambiosService.guardarBitacoraCambios(bitTrBitacLogs.insertBitacEstatusWebLogs(webLogsDTO, usuarioId));
		
	}

	
	@Override
	@Transactional
	public void insertaBitacoraCambios(BitacoraCambiosDTO bitacoraCambiosDTO) {
		
		bitacoraCambiosDAO.save(bitacoraCambiosDTO);
	}

	@Override
	public LogsVO crudLog(LogsVO log,String operacion) throws ParseException {
		LogsVO oldLog = new LogsVO();
		if(log.getLogId()!= null){
			oldLog = obtieneLogById(log.getLogId());
		}
		log.setTipoOperacion(operacion);
		logsMyBatisDAO.ejecutarSPAdminLogs(log);
		//System.out.println("MENSAJE------***"+resultado.getResultadoPrincipal());
		bitacoraCambiosService.guardarListaBitacoraCambios(bitSpAdminLogs.bitSpAdminLogsMethod(log, oldLog));
		return log;	
	}
	
	private LogsVO obtieneLogById(Long id){
		WebLogsDTO logDTO = webLogsDAO.busquedaLogPorId(id);
		LogsVO logVO = new LogsVO();
		ResponseConverter.copiarPropriedades(logVO, logDTO);
		logVO.setRutaArchivo(logDTO.getRutaArchivos());
		logVO.setLogTipoArchivos(logDTO.getTipoExtensiones());
		return logVO;
	}
}
