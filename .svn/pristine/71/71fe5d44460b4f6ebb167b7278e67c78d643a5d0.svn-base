package mx.com.teclo.saicdmx.ws.fotomulta;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.semovi.ParametroService;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultabitacora.FotomultaBitacoraMyBatisDAO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enumerados.FotoMultaWSProceso;
import mx.com.teclomexicana.fotomultas.WSClientFotomultas;
import mx.com.teclomexicana.fotomultas.WSClientFotomultasImpl;
import mx.com.teclomexicana.fotomultas.ws.AsignacionLote;
import mx.com.teclomexicana.fotomultas.ws.LiberacionLote;
import mx.com.teclomexicana.fotomultas.ws.ServiceResult;

@Service
public class WSFotoMultaImpl implements WSFotoMulta{
	
	private static final Logger logger = Logger
			.getLogger(WSFotoMultaImpl.class);
	
	@Autowired
	private FotomultaBitacoraMyBatisDAO fotomultaBitacoraMyBatisDAO;
	
	@Autowired
	private ParametroService parametroService; 
	
	private RutinasTiempoImpl rutina;
	
	private String storedLote;
	private String storedFolio;
	
	@Override
	public ServiceResult liberacionLote(LiberacionLote request) {
		ServiceResult resultado = new ServiceResult();
		WSClientFotomultas wsClientFotomultas;
		rutina = new RutinasTiempoImpl();
		Date fecha = new Date(rutina.getFechaActual().getTime());
		
		try{
			fotomultaBitacoraMyBatisDAO.insertFotomultaBitacora(Long.parseLong(request.getLote()), request.getUsuario(), fecha, 1, FotoMultaWSProceso.OPERACION_LIBERACION_LOTE_ID, 1, null);
			
			wsClientFotomultas = new WSClientFotomultasImpl();
			resultado = wsClientFotomultas.liberacionLote(request);
			this.storedLote = request.getLote();
			
			fotomultaBitacoraMyBatisDAO.updateFotomultaBitacora(resultado.getError(), resultado.getResultado(), 0, request.getUsuario(), FotoMultaWSProceso.OPERACION_LIBERACION_LOTE_ID, Long.parseLong(request.getLote()), null);
		}catch(Exception ex){
			logger.error(ex.getMessage());
			resultado.setError(ex.getMessage());
			fotomultaBitacoraMyBatisDAO.updateFotomultaBitacora(resultado.getError(), resultado.getResultado(), 1, request.getUsuario(), FotoMultaWSProceso.OPERACION_LIBERACION_LOTE_ID, Long.parseLong(request.getLote()), null);
		}
		creaLog(resultado, FotoMultaWSProceso.OPERACION_LIBERACION_LOTE);
		return resultado;
	}
	

	@Override
	public ServiceResult asignacionLote(AsignacionLote request) {
		ServiceResult resultado = new ServiceResult();
		WSClientFotomultas wsClientFotomultas = null;
		rutina = new RutinasTiempoImpl();
		Date fecha = new Date(rutina.getFechaActual().getTime());
		try {
			fotomultaBitacoraMyBatisDAO.insertFotomultaBitacora(Long.parseLong(request.getLote()), request.getUsuario(), fecha, 1, FotoMultaWSProceso.OPERACION_ASIGNACION_LOTE_ID, 1, null);
			
			wsClientFotomultas = new WSClientFotomultasImpl();
			resultado = wsClientFotomultas.asignacionLote(request);
			
			this.storedLote = request.getLote();
			
			fotomultaBitacoraMyBatisDAO.updateFotomultaBitacora(resultado.getError(), resultado.getResultado(), 0, request.getUsuario(), FotoMultaWSProceso.OPERACION_ASIGNACION_LOTE_ID, Long.parseLong(request.getLote()), null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultado.setError(e.getMessage());
			fotomultaBitacoraMyBatisDAO.updateFotomultaBitacora(resultado.getError(), resultado.getResultado(), 1, request.getUsuario(), FotoMultaWSProceso.OPERACION_ASIGNACION_LOTE_ID, Long.parseLong(request.getLote()), null);
		}
		
		creaLog(resultado, FotoMultaWSProceso.OPERACION_ASIGNACION_LOTE);
		return resultado;
	}
	
	private void creaLog(ServiceResult message, String operacion) {

		try {
			System.out.println("ENTRO WSLOG");
			Logger log = Logger.getLogger(WSFotoMultaImpl.class);
			RutinasTiempoImpl rutina = new RutinasTiempoImpl();
			java.util.Date fecha = new java.util.Date();
	
			String ruta = parametroService.getDirRadares() + "log/consumoWSFotomultas.log";
			PatternLayout defaultLayout = new PatternLayout("%m%n");
			RollingFileAppender rollingFileAppender = new RollingFileAppender();
			rollingFileAppender.setFile(ruta, true, false, 0);
			rollingFileAppender.setMaxFileSize("50MB");
			rollingFileAppender.setLayout(defaultLayout);
			log.removeAllAppenders();
			log.addAppender(rollingFileAppender);
			log.setAdditivity(false);
			log.info("+-------------------------------------------------------------------+");
			log.info(rutina.getStringDateFromFormta("dd/MM/yyyy HH:mm", fecha));
			log.info("Operación: " + operacion);
			log.info("Lote: " + this.storedLote);
			log.info("Infracción: " + this.storedFolio);
			log.info("Error: " + message.getError());
			log.info("Resultado: " + message.getResultado());
			System.out.println("WSLOG EXITOSO");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("WSLOG ERROR: " + e.getMessage());
		}

	}
}
