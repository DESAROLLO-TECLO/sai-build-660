package mx.com.teclo.saicdmx.negocio.service.radarArchivoProcesado;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar.BitRadarProcesarArchivoServlet;
import mx.com.teclo.saicdmx.negocio.service.semovi.ParametroService;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultabitacora.FotomultaBitacoraMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultadetecciones.FotomultaDeteccionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultalotes.FotomultaLotesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarArchivoProcesado.RadarArchivoEnvioMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarArchivoProcesado.RadarArchivoProcesadosMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarabitacoraproceso.RadarBitacoraProcesoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radararchivo.RadarArchivoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radararchivodetalle.RadarArchivoDetalleMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radararchivodetallehist.RadarArchivoDetalleHistMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarasignafoliotemp.RadarAsignaFolioTempMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarbitacoracambios.RadarBitacoraCambiosMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ArchivoBatchFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ArchivosTotalesVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ConsultaArchivosProcesadosVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.DetalleInfraccionesLiberadasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.DeteccionesComplementadasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.DeteccionesIncorrectasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoEstatusVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoResumenVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarComboTipoArchivoVO;
import mx.com.teclo.saicdmx.util.comun.RadarUtils;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enumerados.ArchivoTipoAtributes;
import mx.com.teclo.saicdmx.util.enumerados.ArchivosEnum;
import mx.com.teclo.saicdmx.util.enumerados.EstatusProcesoLote;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclo.saicdmx.ws.fotomulta.WSFotoMulta;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import mx.com.teclomexicana.fotomultas.ws.AsignacionLote;
import mx.com.teclomexicana.fotomultas.ws.LiberacionLote;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;


@Service
public class RadarArchivoProcesadosServiceImpl implements RadarArchivoProcesadosService {

	@Autowired
	private RadarArchivoProcesadosMyBatisDAO radarArchivoProcesadosMyBatisDAO;
	
	@Autowired
	private RadarAsignaFolioTempMyBatisDAO radarAsignaFolioTempMyBatisDAO;
	@Autowired
	private ParametroService parametroService;

	@Autowired
	private RadarArchivoMyBatisDAO radarArchivoMyBatisDAO;
	
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	
	@Autowired
	private RadarArchivoDetalleMyBatisDAO radarArchivoDetalleMyBatisDAO;
	
	@Autowired
	private RadarBitacoraProcesoMyBatisDAO radarBitacoraProcesoMyBatisDAO;
	
	@Autowired
	private RadarBitacoraCambiosMyBatisDAO radarBitacoraCambiosMyBatisDAO;
	
	@Autowired
	private RadarArchivoDetalleHistMyBatisDAO radarArchivoDetalleHistMyBatisDAO;
	
	@Autowired 
	private FotomultaDeteccionesMyBatisDAO fotomultaDeteccionesMyBatisDAO;
	
	@Autowired
	private FotomultaLotesMyBatisDAO fotomultaLotesMyBatisDAO;
	
	@Autowired
	private FotomultaBitacoraMyBatisDAO fotomultaBitacoraMyBatisDAO;
	
	@Autowired
	private ParametroService parametros;
	
	@Autowired
	private WSFotoMulta fotomultaWS;
	
	private RadarUtils radarUtils;
	
	@Autowired
	BitRadarProcesarArchivoServlet bitRadarProcesarArchivoServlet;
	
	@Autowired
	RadarArchivoEnvioMyBatisDAO radarArchivoEnvioMyBatisDAO;
	
	@Override
	public List<RadarComboTipoArchivoVO> getComboArchivoTipo() {	
		return radarArchivoProcesadosMyBatisDAO.getArchivoProcesado();
	}
	
	@Override
	public List<RadarComboTipoArchivoVO> getComboRadarTipo(Integer tipoArchivo) {	
		return radarArchivoProcesadosMyBatisDAO.getRadarProcesado(tipoArchivo);
	}

//	@Override
//	public  List<ConsultaArchivosProcesadosVO> getArchivosProcesados(String tipoArchivo, Integer tipoFecha,
//			String fechaInicio, String fechaFin, String tipoProces) {
//		return radarArchivoProcesadosMyBatisDAO.getArchivosProcesados(tipoArchivo,tipoFecha,fechaInicio,fechaFin,tipoProces);
//	}
//	@Override 
//	public List<ConsultaArchivosProcesadosVO> getArchivosProcesadosAll(String tipoArchivo) {
//		
//		return radarArchivoProcesadosMyBatisDAO.getArchivosProcesadosAll(tipoArchivo);
//		
//	}
	
	@Override
	public  List<ConsultaArchivosProcesadosVO> getArchivosProcesados(Integer origenProceso, Integer tipoProceso, Integer tipoDeteccion, Integer tipoArchivo,
			Integer tipoPersona, Integer tipoFecha, String fechaInicio, String fechaFin) {
		//FUNCION PARA CONVERTIR EL ID DEL ARCHIVO_FORA e ID_MARCA a ID_TIPO_DETECCION
		//FALTA FUNCION
		//Integer idTipoDeteccion = radarArchivoProcesadosMyBatisDAO.buscarIdTipoDeteccion(tipoArchivo, idMarca);
		return radarArchivoProcesadosMyBatisDAO.getArchivosProcesados(origenProceso, tipoProceso, tipoDeteccion, tipoArchivo,
				tipoPersona, tipoFecha, fechaInicio, fechaFin);
	}
	@Override 
	public List<ConsultaArchivosProcesadosVO> getArchivosProcesadosAll(String tipoArchivo, Integer origenProceso) {
		return radarArchivoProcesadosMyBatisDAO.getArchivosProcesadosAll(tipoArchivo, origenProceso);
	}

	@Override
	public ArchivoBatchFinanzasVO cargarArchivo(String archivoId) {
		return radarArchivoProcesadosMyBatisDAO.cargarArchivo(archivoId);
	}

//	@Override
//	public void setArchivoListoParaLiberar(Integer archivoListoParaLiberar, Long empId, Integer enProceso, String archivoId) {
//		radarBitacoraProcesoMyBatisDAO.insertaRadarBitacoraProceso(new Long(archivoId), new Long(archivoListoParaLiberar), empId);
//		radarArchivoMyBatisDAO.setArchivoListoParaLiberarUpdate(archivoListoParaLiberar,enProceso, empId, archivoId);
//	}
	
	@Override
	public void setArchivoListoParaLiberar(Integer archivoListoParaLiberar, Long empId, Integer enProceso, String archivoId) {
		Integer origenLote = radarArchivoProcesadosMyBatisDAO.buscarOrigenById(new Long(archivoId));
		System.out.println("Origen lote: "+origenLote);
		//if(origenLote == 0){
			radarBitacoraProcesoMyBatisDAO.insertaRadarBitacoraProceso(new Long(archivoId), new Long(archivoListoParaLiberar), empId);
			radarArchivoMyBatisDAO.setArchivoListoParaLiberarUpdate(archivoListoParaLiberar,enProceso, empId, archivoId);
		//}else if(origenLote == 1){
		//	radarBitacoraProcesoMyBatisDAO.insertaRadarBitacoraProcesoFM(new Long(archivoId), new Long(archivoListoParaLiberar), empId);
		//	radarArchivoMyBatisDAO.setArchivoListoParaLiberarUpdateFM(archivoListoParaLiberar,enProceso, empId, archivoId);
		//}
		//radarArchivoMyBatisDAO.setArchivoListoParaLiberarEnTotal(archivoListoParaLiberar,enProceso, empId, archivoId);
	}

//	/***
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void cancelarArchivo(ArchivoBatchFinanzasVO objetoVO, String motivo) throws BusinessException {
//		Integer estatus = EstatusProcesoLote.CANCELADO.getEstatusProceso();
//		
//		radarAsignaFolioTempMyBatisDAO.InsertFoliosCancelados(objetoVO.getArchivoId());
//		
//		Integer deteccionesCanceladas = this.radarArchivoDetalleMyBatisDAO.buscaCantidadDeDetecciones(objetoVO.getArchivoId(), 0);
//		Integer deteccionesProcesadas = this.radarArchivoDetalleMyBatisDAO.buscaCantidadDeDetecciones(objetoVO.getArchivoId(), 1);
//		Integer totalDeteccionesCanceladas = deteccionesCanceladas + deteccionesProcesadas;
//		
//		this.radarArchivoMyBatisDAO.cancelaRadarArchivo(objetoVO.getArchivoId(), new Long(estatus), 
//														objetoVO.getModificadoPorId(),
//														totalDeteccionesCanceladas, 0, motivo);
//		
//		this.radarArchivoDetalleMyBatisDAO.cancelaDeteccionesPorArchivo(objetoVO.getArchivoId());
//		this.radarBitacoraProcesoMyBatisDAO.insertaRadarBitacoraProceso(objetoVO.getArchivoId(), new Long(estatus), objetoVO.getModificadoPorId());
//		String tipoArchivo = this.radarArchivoMyBatisDAO.buscaArchivoTipo(objetoVO.getArchivoId());
//		
//		if(tipoArchivo.equalsIgnoreCase(ArchivosEnum.FOTO_MUTLTA.getConstante())){
//			this.fotomultaLotesMyBatisDAO.actualizarCantidadCanceladosLote(objetoVO.getArchivoId(), deteccionesCanceladas);
//			this.fotomultaDeteccionesMyBatisDAO.updateDeteccionesLoteCancelado(objetoVO.getArchivoId());
//			this.fotomultaLotesMyBatisDAO.actualizarCantidadDeteccionesPorLote(totalDeteccionesCanceladas, 0, objetoVO.getArchivoId());
//		}
//		
//		this.radarArchivoDetalleHistMyBatisDAO.insertaRadarArchivoDetalleCompleto(objetoVO.getArchivoId());
//		this.radarArchivoDetalleMyBatisDAO.eliminaDeteccionesCancelacionLote(objetoVO.getArchivoId());
//		this.insertarEnBitacoraCambios(objetoVO, estatus);
//	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public void cancelarArchivo(ArchivoBatchFinanzasVO objetoVO, String motivo) throws BusinessException {
		Integer estatus = EstatusProcesoLote.CANCELADO.getEstatusProceso();
		
		radarAsignaFolioTempMyBatisDAO.InsertFoliosCancelados(objetoVO.getArchivoId());
		
		Integer deteccionesCanceladas = this.radarArchivoDetalleMyBatisDAO.buscaCantidadDeDetecciones(objetoVO.getArchivoId(), 0);
		Integer deteccionesProcesadas = this.radarArchivoDetalleMyBatisDAO.buscaCantidadDeDetecciones(objetoVO.getArchivoId(), 1);
		Integer totalDeteccionesCanceladas = deteccionesCanceladas + deteccionesProcesadas;
		
		this.radarArchivoMyBatisDAO.cancelaRadarArchivo(objetoVO.getArchivoId(), new Long(estatus), 
														objetoVO.getModificadoPorId(),
														totalDeteccionesCanceladas, 0, motivo);
		
		this.radarArchivoMyBatisDAO.cancelaRadarArchivoTotal(objetoVO.getArchivoId(), new Long(estatus), 
															 objetoVO.getModificadoPorId(),
															 totalDeteccionesCanceladas, 0, motivo);
		
		this.radarArchivoDetalleMyBatisDAO.cancelaDeteccionesPorArchivo(objetoVO.getArchivoId());
		this.radarBitacoraProcesoMyBatisDAO.insertaRadarBitacoraProceso(objetoVO.getArchivoId(), new Long(estatus), objetoVO.getModificadoPorId());
		String tipoArchivo = this.radarArchivoMyBatisDAO.buscaArchivoTipo(objetoVO.getArchivoId());
		
		if(tipoArchivo.equalsIgnoreCase(ArchivosEnum.FOTO_MUTLTA.getConstante())){
			this.fotomultaLotesMyBatisDAO.actualizarCantidadCanceladosLote(objetoVO.getArchivoId(), deteccionesCanceladas);
			this.fotomultaDeteccionesMyBatisDAO.updateDeteccionesLoteCancelado(objetoVO.getArchivoId());
			this.fotomultaLotesMyBatisDAO.actualizarCantidadDeteccionesPorLote(totalDeteccionesCanceladas, 0, objetoVO.getArchivoId());
		}
		
		this.radarArchivoDetalleHistMyBatisDAO.insertaRadarArchivoDetalleCompleto(objetoVO.getArchivoId());
		this.radarArchivoDetalleMyBatisDAO.eliminaDeteccionesCancelacionLote(objetoVO.getArchivoId());
		this.insertarEnBitacoraCambios(objetoVO, estatus);
	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public void cancelarArchivoTotal(ArchivoBatchFinanzasVO objetoVO, String motivo) throws BusinessException {
		Integer estatus = EstatusProcesoLote.CANCELADO.getEstatusProceso();
		Integer origenLote = radarArchivoProcesadosMyBatisDAO.buscarOrigenById(objetoVO.getArchivoId());
		
		radarAsignaFolioTempMyBatisDAO.InsertFoliosCancelados(objetoVO.getArchivoId());
		
		Integer deteccionesCanceladas = this.radarArchivoDetalleMyBatisDAO.buscaCantidadDeDetecciones(objetoVO.getArchivoId(), 0);
		Integer deteccionesProcesadas = this.radarArchivoDetalleMyBatisDAO.buscaCantidadDeDetecciones(objetoVO.getArchivoId(), 1);
		Integer totalDeteccionesCanceladas = deteccionesCanceladas + deteccionesProcesadas;
		
		this.radarArchivoMyBatisDAO.cancelaRadarArchivo(objetoVO.getArchivoId(), new Long(estatus), 
					objetoVO.getModificadoPorId(),
				totalDeteccionesCanceladas, 0, motivo);
		
		
		this.radarArchivoDetalleMyBatisDAO.cancelaDeteccionesPorArchivo(objetoVO.getArchivoId());
		this.radarBitacoraProcesoMyBatisDAO.insertaRadarBitacoraProceso(objetoVO.getArchivoId(), new Long(estatus), objetoVO.getModificadoPorId());
		String tipoArchivo = this.radarArchivoMyBatisDAO.buscaArchivoTipo(objetoVO.getArchivoId());
		
		if(origenLote == 0){
			if(tipoArchivo.equalsIgnoreCase(ArchivosEnum.FOTO_MUTLTA.getConstante())){
				this.fotomultaLotesMyBatisDAO.actualizarCantidadCanceladosLote(objetoVO.getArchivoId(), deteccionesCanceladas);
				this.fotomultaDeteccionesMyBatisDAO.updateDeteccionesLoteCancelado(objetoVO.getArchivoId());
				this.fotomultaLotesMyBatisDAO.actualizarCantidadDeteccionesPorLote(totalDeteccionesCanceladas, 0, objetoVO.getArchivoId());
			}
		}else if(origenLote == 1){
			
			//limpiar estatus duplicado
			this.fotomultaDeteccionesMyBatisDAO.updateResetDeteccionesFCLoteCanceladoTAI005(objetoVO.getArchivoId());
			this.fotomultaDeteccionesMyBatisDAO.updateResetDeteccionesFCLoteCanceladoTAI027(objetoVO.getArchivoId());
			this.fotomultaDeteccionesMyBatisDAO.updateDeteccionesFCLoteCancelado(objetoVO.getArchivoId());
			actualizaArchivosOrigen(objetoVO.getArchivoId());
			this.fotomultaDeteccionesMyBatisDAO.updateDeteccionesLoteCanceladoFM(objetoVO.getArchivoId());
		}
		
		this.radarArchivoDetalleHistMyBatisDAO.insertaRadarArchivoDetalleCompleto(objetoVO.getArchivoId());
		this.radarArchivoDetalleMyBatisDAO.eliminaDeteccionesCancelacionLote(objetoVO.getArchivoId());
		this.insertarEnBitacoraCambios(objetoVO, estatus);
		
		
		
//		if(origenLote == 0){
//			radarAsignaFolioTempMyBatisDAO.InsertFoliosCancelados(objetoVO.getArchivoId());
//			
//			Integer deteccionesCanceladas = this.radarArchivoDetalleMyBatisDAO.buscaCantidadDeDetecciones(objetoVO.getArchivoId(), 0);
//			Integer deteccionesProcesadas = this.radarArchivoDetalleMyBatisDAO.buscaCantidadDeDetecciones(objetoVO.getArchivoId(), 1);
//			Integer totalDeteccionesCanceladas = deteccionesCanceladas + deteccionesProcesadas;
//			
//			this.radarArchivoMyBatisDAO.cancelaRadarArchivo(objetoVO.getArchivoId(), new Long(estatus), 
//															objetoVO.getModificadoPorId(),
//															totalDeteccionesCanceladas, 0, motivo);
//			
//			this.radarArchivoMyBatisDAO.cancelaRadarArchivoTotal(objetoVO.getArchivoId(), new Long(estatus), 
//																 objetoVO.getModificadoPorId(),
//																 totalDeteccionesCanceladas, 0, motivo);
//			
//			this.radarArchivoDetalleMyBatisDAO.cancelaDeteccionesPorArchivo(objetoVO.getArchivoId());
//			this.radarBitacoraProcesoMyBatisDAO.insertaRadarBitacoraProceso(objetoVO.getArchivoId(), new Long(estatus), objetoVO.getModificadoPorId());
//			String tipoArchivo = this.radarArchivoMyBatisDAO.buscaArchivoTipo(objetoVO.getArchivoId());
////////			
//			if(tipoArchivo.equalsIgnoreCase(ArchivosEnum.FOTO_MUTLTA.getConstante())){
//				this.fotomultaLotesMyBatisDAO.actualizarCantidadCanceladosLote(objetoVO.getArchivoId(), deteccionesCanceladas);
//				this.fotomultaDeteccionesMyBatisDAO.updateDeteccionesLoteCancelado(objetoVO.getArchivoId());
//				this.fotomultaLotesMyBatisDAO.actualizarCantidadDeteccionesPorLote(totalDeteccionesCanceladas, 0, objetoVO.getArchivoId());
//			}
//			
//			this.radarArchivoDetalleHistMyBatisDAO.insertaRadarArchivoDetalleCompleto(objetoVO.getArchivoId());
//			this.radarArchivoDetalleMyBatisDAO.eliminaDeteccionesCancelacionLote(objetoVO.getArchivoId());
//			this.insertarEnBitacoraCambios(objetoVO, estatus);
//		}else if(origenLote == 1){
//			Integer deteccionesCanceladas = this.radarArchivoDetalleMyBatisDAO.buscaCantidadDeDeteccionesFM(objetoVO.getArchivoId(), 0);
//			Integer deteccionesProcesadas = this.radarArchivoDetalleMyBatisDAO.buscaCantidadDeDeteccionesFM(objetoVO.getArchivoId(), 1);
//			Integer totalDeteccionesCanceladas = deteccionesCanceladas + deteccionesProcesadas;
//			
//			this.radarArchivoMyBatisDAO.cancelaRadarArchivoFM(
//					objetoVO.getArchivoId(), new Long(estatus), objetoVO.getModificadoPorId(), 
//					totalDeteccionesCanceladas, 0, motivo);
//			
//			this.radarArchivoMyBatisDAO.cancelaRadarArchivoTotal(objetoVO.getArchivoId(), new Long(estatus), 
//					objetoVO.getModificadoPorId(),
//					totalDeteccionesCanceladas, 0, motivo);
//			
//			this.radarArchivoDetalleMyBatisDAO.cancelaDeteccionesPorArchivoFM(objetoVO.getArchivoId());
//			this.radarBitacoraProcesoMyBatisDAO.insertaRadarBitacoraProcesoFM(objetoVO.getArchivoId(), 
//																				new Long(estatus), 
//																				objetoVO.getModificadoPorId());
//			String tipoArchivo = this.radarArchivoMyBatisDAO.buscaArchivoTipoFM(objetoVO.getArchivoId());
//			this.fotomultaDeteccionesMyBatisDAO.updateDeteccionesLoteCanceladoFM(objetoVO.getArchivoId());
//			
//			radarAsignaFolioTempMyBatisDAO.InsertFoliosCanceladosFM(objetoVO.getArchivoId());
//			this.radarArchivoDetalleMyBatisDAO.eliminaDeteccionesCancelacionLoteFM(objetoVO.getArchivoId());
//			this.insertarEnBitacoraCambios(objetoVO, estatus);
//		}
	}
	
	public void insertarEnBitacoraCambios(ArchivoBatchFinanzasVO objetoVO, Integer tipoProceso){
		Integer conceptoId = 0;

		if (tipoProceso == EstatusProcesoLote.LISTO_PARA_COMPLEMENTAR.getEstatusProceso()) {
			conceptoId = 1;
		} else if (tipoProceso == EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso()) {
			conceptoId = 2;
		} else if (tipoProceso == EstatusProcesoLote.CANCELADO.getEstatusProceso()) {
			conceptoId = 5;
		} else if (tipoProceso == EstatusProcesoLote.LIBERADO.getEstatusProceso()) {
			conceptoId = 6;
		}
		
		String valorFinal = objetoVO.getFileName() + "-" + objetoVO.getFechaEmision();
		radarBitacoraCambiosMyBatisDAO.insertaRadarBitacoraProceso(conceptoId, objetoVO.getArchivoId(), "", valorFinal, objetoVO.getModificadoPorId());
		RadarArchivoEstatusVO radarArchivoEstatusVO = new RadarArchivoEstatusVO(); radarArchivoEstatusVO.setArchivoNombre(valorFinal);
		radarArchivoEstatusVO.setArchivoId(objetoVO.getArchivoId());
		bitacoraCambiosService.guardarBitacoraCambios(bitRadarProcesarArchivoServlet.cancelarComplementacionBit(radarArchivoEstatusVO));
	}

//	/***
//	 * {@inheritDoc}
//	 */
//	@SuppressWarnings("rawtypes")
//	@Override
//	public RadarArchivoResumenVO buscaRadarArchivoACancelar(Long archivoId) {
//		RadarArchivoResumenVO objectResumen = null;
//		RadarArchivoEstatusVO object = this.radarArchivoMyBatisDAO.buscaRadarArchivoACancelar(archivoId);
//		
//		if (object != null) {
//			objectResumen = new RadarArchivoResumenVO();
//			objectResumen.setArchivoId(object.getArchivoId());
//			objectResumen.setFechaEmision(object.getFechaEmision());
//			objectResumen.setEstatusProceso(object.getEstatusProcesoNombre());
//			objectResumen.setRequiredUpdate(false);
//			EstatusProcesoLote whichValue = EstatusProcesoLote.getArchivoEstatusProceso(object.getEstatusProcesoId());
//			 switch (whichValue) {
//			 	case COMPLEMENTADO:
//			 		objectResumen.setArchivoComplementado(true);
//			 		objectResumen.setArchivoLiberado(false);
//			 		objectResumen.setMostrarCancelacion(true);
//			 		objectResumen.setArchivoEnLiberacion(false);
//			 		objectResumen.setFechaComplementacion(object.getFechaComplementacion());
//			 		break;
//			 	case LIBERADO:
//			 		objectResumen.setArchivoLiberado(true);
//			 		objectResumen.setArchivoComplementado(true);
//			 		objectResumen.setMostrarCancelacion(false);
//			 		objectResumen.setArchivoEnLiberacion(false);
//			 		objectResumen.setFechaLiberacion(object.getFechaLiberacion());
//			 		DetalleInfraccionesLiberadasVO detalleLiberadas = radarArchivoProcesadosMyBatisDAO.buscarDetalleInfraccionesLiberadas(archivoId);
//			 		 if (detalleLiberadas != null) {
//			 			String folios =  detalleLiberadas.getFolioInicial().
//			 							 substring(0, detalleLiberadas.getFolioInicial().length() - 1) + "-" +
//			 							 detalleLiberadas.getFolioFinal().
//		 								 substring(0, detalleLiberadas.getFolioFinal().length() - 1);
//			 			objectResumen.setLiberadasTotal((Long.valueOf(detalleLiberadas.getTotal().toString())));
//			 			objectResumen.setLiberadasFolios(folios);
//			 		 }	
//			 		break;
//			 	case LISTO_PARA_LIBERAR:
//			 	case LIBERANDO:
//			 		objectResumen.setArchivoComplementado(true);
//			 		objectResumen.setArchivoLiberado(false);
//			 		objectResumen.setMostrarCancelacion(true);
//			 		objectResumen.setArchivoEnLiberacion(true);
//			 		objectResumen.setRequiredUpdate(true);
//			 		break;
//			 }
//			 
//		 	Map archivoTotales = this.totalesArchivo(archivoId, object.getEstatusProcesoId());
//		 	objectResumen.setArchivoTotal((Long) (archivoTotales.get("TOTAL") == null ? 0L : archivoTotales.get("TOTAL"))); 	
//		 	objectResumen.setComplementadasValidasTotal((Long) (archivoTotales.get("COMPLEMENTADAS") == null ? 0L : archivoTotales.get("COMPLEMENTADAS")));
//		 	objectResumen.setComplementadasInvalidasTotal((Long) (archivoTotales.get("RECHAZADAS") == null ? 0L : archivoTotales.get("RECHAZADAS")));
//		 	objectResumen.setLiberadasTotal((Long) (archivoTotales.get("LIBERADAS") == null ? 0L : archivoTotales.get("LIBERADAS")));
//		 	
//		 	String accionComplementadas = accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId( ), 1);
//		 	objectResumen.setComplementadasAccion(accionComplementadas);	
//		 	if(accionComplementadas == "0" || accionComplementadas == "1"){
//		 		objectResumen.setZipEstatus(ArchivoTipoAtributes.ZIP_COMPLEMENTADAS);
//		 	}else if(accionComplementadas == "3"){
//		 		objectResumen.setRequiredUpdate(true);
//		 	}
//		 	
//		 	String accionRechazadas = accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId(), 2);
//		 	objectResumen.setRechazadasAccion(accionRechazadas);
//		 	if(accionRechazadas == "0" || accionRechazadas == "1"){
//		 		objectResumen.setZipEstatus(ArchivoTipoAtributes.ZIP_RECHAZADAS);
//		 	}else if(accionRechazadas == "3"){
//		 		objectResumen.setRequiredUpdate(true);
//		 	}
//		 	objectResumen.setLiberadasAccion(accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId(), 3));
//		 	//object.getArchivoId();
//		}
//		return objectResumen;
//	}
//	
//	/***
//	 * @author Jesus Gutierrez 
//	 * @param archivoId
//	 * @param estatusProcesoId
//	 * @return Map
//	 */
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public Map totalesArchivo(Long archivoId, Integer estatusProcesoId){
//		List<ArchivosTotalesVO> objects = null;
//		if(estatusProcesoId == EstatusProcesoLote.LIBERADO.getEstatusProceso()){
//			objects = this.radarArchivoDetalleMyBatisDAO.totalesArchivoLiberado(archivoId);
//		}else if(estatusProcesoId == EstatusProcesoLote.CANCELADO.getEstatusProceso()){
//			objects = this.radarArchivoDetalleMyBatisDAO.totalesArchivoCancelado(archivoId);
//		}else{
//			objects = this.radarArchivoDetalleMyBatisDAO.totalesArchivo(archivoId);
//		}
//		
//		Map conceptos = new HashMap();
//		for(ArchivosTotalesVO concepto : objects){
//			conceptos.put(concepto.getArchivoConcepto(), concepto.getArchivoTotal());
//		}
//		
//		return conceptos;
//	}
//	
//	/***
//	 * {@inheritDoc}
//	 */
//	@Override
//	public String accionTotalesArchivo(Long archivoId, Integer estatusProcesoId, Integer accion) {
//		String code = "";
//		String estatusArchivo = "";
//		
//		//Complementadas
//		if(accion == 1){
//			if(EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso() == estatusProcesoId
//					|| EstatusProcesoLote.LIBERADO.getEstatusProceso() == estatusProcesoId){
//				
//				estatusArchivo = this.radarArchivoMyBatisDAO.buscarEstatusArchivoZIP(archivoId, ArchivoTipoAtributes.ARCHIVO_COMPLEMENTADAS);
//				if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_GENERADO)){
//					code = "1";
//				}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_SIN_INFORMACION)){
//					code = "2";
//				}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_EN_PROCESO)){
//					code = "3";
//				}else{
//					code = "0";
//				}
//			}
//		}else if(accion == 2){ //Rechazadas
//			if(EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso() == estatusProcesoId
//					|| EstatusProcesoLote.LIBERADO.getEstatusProceso() == estatusProcesoId){
//				
//				estatusArchivo = this.radarArchivoMyBatisDAO.buscarEstatusArchivoZIP(archivoId, ArchivoTipoAtributes.ARCHIVO_RECHAZADAS);
//				if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_GENERADO)){
//					code = "1";
//				}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_SIN_INFORMACION)){
//					code = "2";
//				}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_EN_PROCESO)){
//					code = "3";
//				}else{
//					code = "0";
//				}
//			}
//		}else if(accion == 3){ //Liberadas
//			if(this.radarArchivoMyBatisDAO.isArchivoComplementado(archivoId) == 0){
//				code = "0";
//			}else if(EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso() == estatusProcesoId){
//				code = "1";
//				
//			}
//		}
//		return code;
//	}
//
//
//	@Override
//	public void crearExcelComplementadas(String ArchivoId, String fileName) {
//		boolean comprime = false;
//		
//		String nombreArchivo;
//		String columnaArchivoComplementadas = "archivo_complementadas";
//		actualizaRegistroArchivosExcel(columnaArchivoComplementadas, "GENERAR",comprime,ArchivoId);
//		List<DeteccionesComplementadasVO> lista = radarArchivoMyBatisDAO.listaDeteccionesComplementadas(ArchivoId);
//
//			ByteArrayOutputStream reporte = new ByteArrayOutputStream();
//			PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
//			PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
//			PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
//			
//			String[] titulosEst = { "Placa", "Fecha", "Hora", "TDSKUID", "UT",
//					"Velocidad", "Nombre", "Domicilio", "CP",
//					"Delegación/Municipio", "Entidad Federativa", "Teléfono",
//					"Marca", "Submarca", "Modelo", "Núm. Serie", "Motor",
//					"Folio Infracción", "Días SMV", "Porcentaje Descuento",
//					"Fecha Emisión/Fecha Proceso", "Fecha Imposición",
//					"Fecha Vencimiento", "Importe Infracción",
//					"Importe Descuento", "Importe Total", "Línea de Captura",
//					"Centro de Reparto", "Clave de Pago", "Calle",
//					"Entre Calle", "Y Calle", "Sector", "Delegación",
//					"Oficial Placa", "Oficial Nombre" };
//			
//			if(!lista.isEmpty()){
//			//Resultados de la tabla
//			List<Object> contenido = new ArrayList<Object>();
//			List<Object> contenido1 = new ArrayList<Object>();
//					
//			//Leyendas de las columnas de las tablas
//			List<Object> encabezadoTitulo = new ArrayList<Object>();
//			List<String> titulos = Arrays.asList(titulosEst); 
//		
//			
//			encabezadoTitulo.add(titulos);
//		
//			RutinasTiempoImpl rutinastiempo = new RutinasTiempoImpl();
//			propiedadesReporte.setFechaI(rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date()));
//			
//			propiedadesReporte.setTituloExcel("Infracciones Complementadas");
//			propiedadesReporte.setExtencionArchvio(".xls");
//			
//			
//			//cuerpo del reporte
//			List<String> listaContenido1;
//			
//			for(DeteccionesComplementadasVO list : lista){
//				listaContenido1 = new ArrayList<String>();
//				listaContenido1.add(list.getPlaca());
//				listaContenido1.add(list.getFecha());
//				listaContenido1.add(list.getHora());
//				listaContenido1.add(list.getTdskuid());
//				listaContenido1.add(list.getUt());
//				listaContenido1.add(list.getVelocidad_detectada());
//				listaContenido1.add(list.getNombre());
//				listaContenido1.add(list.getDomicilio());
//				listaContenido1.add(list.getCodigo_postal());
//				listaContenido1.add(list.getMunicipio());
//				listaContenido1.add(list.getEntidad_federativa());
//				listaContenido1.add(list.getTelefono());
//				listaContenido1.add(list.getMarca());
//				
//				listaContenido1.add(list.getSubmarca());
//				listaContenido1.add(list.getModelo());
//				listaContenido1.add(list.getSerie());
//				listaContenido1.add(list.getMotor());
//				listaContenido1.add(list.getInfrac_num());
//				listaContenido1.add(list.getDias());
//				
//				listaContenido1.add(list.getPorcentaje_descuento());
//				listaContenido1.add(list.getFecha_emision());
//				listaContenido1.add(list.getFecha_imposicion());
//				listaContenido1.add(list.getFecha_vencimiento());
//				listaContenido1.add(list.getImporte_infraccion());
//				listaContenido1.add(list.getImporte_descuento());
//				listaContenido1.add(list.getImporte_total());
//				
//				listaContenido1.add(list.getLinea_captura());
//				listaContenido1.add(list.getCentro_reparto());
//				listaContenido1.add(list.getClave_pago());
//				listaContenido1.add(list.getCalle());
//				listaContenido1.add(list.getEntre_calle());
//				listaContenido1.add(list.getY_calle());
//				listaContenido1.add(list.getSector());
//
//				listaContenido1.add(list.getDelegacion());
//				listaContenido1.add(list.getOficial_placa());
//				listaContenido1.add(list.getOficial_nombre());
//				
//				
//				contenido1.add(listaContenido1);
//			}
//			
//			contenido.add(contenido1);
//			
//			
//			
//			try {
//				creaDirectoriosPorRuta(parametroService.getRutaZip());
//				nombreArchivo = parametroService.getRutaZip();
//				String namereal = "";
//				   if (fileName.contains(".xml")) {
//					     namereal = fileName.replace(".xml", "_" + ArchivoId + "_comp");
//					} else {
//						namereal = fileName+ "_" + ArchivoId + "_comp";
//					}
//
//			
//				propiedadesReporte.setNombreReporte(namereal);
//				propiedadesReporte.setRutaArchivo(nombreArchivo);
//				peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
//				peticionReporteVO.setEncabezado(encabezadoTitulo);
//				peticionReporteVO.setContenido(contenido);
//				reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
//				nombreArchivo += namereal + ".xls";
//				
//				String rutaArchivoZip = nombreArchivo.replace(".xls", ".zip");
//				comprime = comprime(nombreArchivo, rutaArchivoZip);
//				
//				File archivoExcel = new File(nombreArchivo);
//				archivoExcel.delete();
//				
//				actualizaRegistroArchivosExcel(columnaArchivoComplementadas,
//						"CREAR", comprime,ArchivoId);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//			
//			} else {
//				actualizaRegistroArchivosExcel(columnaArchivoComplementadas,
//						"SIN_INFORMACION", comprime, ArchivoId);
//			}
//	 		
//		}
	
	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public RadarArchivoResumenVO buscaRadarArchivoACancelar(Long archivoId) {
		RadarArchivoResumenVO objectResumen = null;
		Integer origenLote = radarArchivoProcesadosMyBatisDAO.buscarOrigenById(archivoId);
		
		RadarArchivoEstatusVO object = this.radarArchivoMyBatisDAO.buscaRadarArchivoACancelar(archivoId);
		if (object != null) {
			objectResumen = new RadarArchivoResumenVO();
			objectResumen.setArchivoId(object.getArchivoId());
			objectResumen.setFechaEmision(object.getFechaEmision());
			objectResumen.setEstatusProceso(object.getEstatusProcesoNombre());
			objectResumen.setRequiredUpdate(false);
			EstatusProcesoLote whichValue = EstatusProcesoLote.getArchivoEstatusProceso(object.getEstatusProcesoId());
			
			switch (whichValue) {
				case COMPLEMENTADO:
					objectResumen.setArchivoComplementado(true);
					objectResumen.setArchivoLiberado(false);
					objectResumen.setMostrarCancelacion(true);
					objectResumen.setArchivoEnLiberacion(false);
					objectResumen.setFechaComplementacion(object.getFechaComplementacion());
					break;
				case LIBERADO:
					objectResumen.setArchivoLiberado(true);
					objectResumen.setArchivoComplementado(true);
					objectResumen.setMostrarCancelacion(false);
					objectResumen.setArchivoEnLiberacion(false);
					objectResumen.setFechaLiberacion(object.getFechaLiberacion());
					DetalleInfraccionesLiberadasVO detalleLiberadas = radarArchivoProcesadosMyBatisDAO.buscarDetalleInfraccionesLiberadas(archivoId);
					if (detalleLiberadas != null) {
						String folios =  detalleLiberadas.getFolioInicial().
								substring(0, detalleLiberadas.getFolioInicial().length() - 1) + "-" +
								detalleLiberadas.getFolioFinal().
								substring(0, detalleLiberadas.getFolioFinal().length() - 1);
						objectResumen.setLiberadasTotal((Long.valueOf(detalleLiberadas.getTotal().toString())));
						objectResumen.setLiberadasFolios(folios);
					}	
					break;
				case LISTO_PARA_LIBERAR:
				case LIBERANDO:
					objectResumen.setArchivoComplementado(true);
					objectResumen.setArchivoLiberado(false);
					objectResumen.setMostrarCancelacion(true);
					objectResumen.setArchivoEnLiberacion(true);
					objectResumen.setRequiredUpdate(true);
					break;	 
			}
			
			Map archivoTotales = this.totalesArchivo(archivoId, object.getEstatusProcesoId());
			objectResumen.setArchivoTotal((Long) (archivoTotales.get("TOTAL") == null ? 0L : archivoTotales.get("TOTAL"))); 	
			objectResumen.setComplementadasValidasTotal((Long) (archivoTotales.get("COMPLEMENTADAS") == null ? 0L : archivoTotales.get("COMPLEMENTADAS")));
			objectResumen.setComplementadasInvalidasTotal((Long) (archivoTotales.get("RECHAZADAS") == null ? 0L : archivoTotales.get("RECHAZADAS")));
			objectResumen.setLiberadasTotal((Long) (archivoTotales.get("LIBERADAS") == null ? 0L : archivoTotales.get("LIBERADAS")));
		 	
			String accionComplementadas = accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId( ), 1);
			objectResumen.setComplementadasAccion(accionComplementadas);	
			if(accionComplementadas == "0" || accionComplementadas == "1"){
				objectResumen.setZipEstatus(ArchivoTipoAtributes.ZIP_COMPLEMENTADAS);
			}else if(accionComplementadas == "3"){
				objectResumen.setRequiredUpdate(true);
			}
		 	
		 	String accionRechazadas = accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId(), 2);
		 	objectResumen.setRechazadasAccion(accionRechazadas);
		 	if(accionRechazadas == "0" || accionRechazadas == "1"){
		 		objectResumen.setZipEstatus(ArchivoTipoAtributes.ZIP_RECHAZADAS);
		 	}else if(accionRechazadas == "3"){
		 		objectResumen.setRequiredUpdate(true);
		 	}
		 	objectResumen.setLiberadasAccion(accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId(), 3));
		}
		
		return objectResumen;
//		if(origenLote == 0){
//			RadarArchivoEstatusVO object = this.radarArchivoMyBatisDAO.buscaRadarArchivoACancelar(archivoId);
//			if (object != null) {
//				objectResumen = new RadarArchivoResumenVO();
//				objectResumen.setArchivoId(object.getArchivoId());
//				objectResumen.setFechaEmision(object.getFechaEmision());
//				objectResumen.setEstatusProceso(object.getEstatusProcesoNombre());
//				objectResumen.setRequiredUpdate(false);
//				EstatusProcesoLote whichValue = EstatusProcesoLote.getArchivoEstatusProceso(object.getEstatusProcesoId());
//				 switch (whichValue) {
//				 	case COMPLEMENTADO:
//				 		objectResumen.setArchivoComplementado(true);
//				 		objectResumen.setArchivoLiberado(false);
//				 		objectResumen.setMostrarCancelacion(true);
//				 		objectResumen.setArchivoEnLiberacion(false);
//				 		objectResumen.setFechaComplementacion(object.getFechaComplementacion());
//				 		break;
//				 	case LIBERADO:
//				 		objectResumen.setArchivoLiberado(true);
//				 		objectResumen.setArchivoComplementado(true);
//				 		objectResumen.setMostrarCancelacion(false);
//				 		objectResumen.setArchivoEnLiberacion(false);
//				 		objectResumen.setFechaLiberacion(object.getFechaLiberacion());
//				 		DetalleInfraccionesLiberadasVO detalleLiberadas = radarArchivoProcesadosMyBatisDAO.buscarDetalleInfraccionesLiberadas(archivoId);
//				 		 if (detalleLiberadas != null) {
//				 			String folios =  detalleLiberadas.getFolioInicial().
//				 							 substring(0, detalleLiberadas.getFolioInicial().length() - 1) + "-" +
//				 							 detalleLiberadas.getFolioFinal().
//			 								 substring(0, detalleLiberadas.getFolioFinal().length() - 1);
//				 			objectResumen.setLiberadasTotal((Long.valueOf(detalleLiberadas.getTotal().toString())));
//				 			objectResumen.setLiberadasFolios(folios);
//				 		 }	
//				 		break;
//				 	case LISTO_PARA_LIBERAR:
//				 	case LIBERANDO:
//				 		objectResumen.setArchivoComplementado(true);
//				 		objectResumen.setArchivoLiberado(false);
//				 		objectResumen.setMostrarCancelacion(true);
//				 		objectResumen.setArchivoEnLiberacion(true);
//				 		objectResumen.setRequiredUpdate(true);
//				 		break;	 
//				 }
				 
//			 	Map archivoTotales = this.totalesArchivo(archivoId, object.getEstatusProcesoId());
//			 	objectResumen.setArchivoTotal((Long) (archivoTotales.get("TOTAL") == null ? 0L : archivoTotales.get("TOTAL"))); 	
//			 	objectResumen.setComplementadasValidasTotal((Long) (archivoTotales.get("COMPLEMENTADAS") == null ? 0L : archivoTotales.get("COMPLEMENTADAS")));
//			 	objectResumen.setComplementadasInvalidasTotal((Long) (archivoTotales.get("RECHAZADAS") == null ? 0L : archivoTotales.get("RECHAZADAS")));
//			 	objectResumen.setLiberadasTotal((Long) (archivoTotales.get("LIBERADAS") == null ? 0L : archivoTotales.get("LIBERADAS")));
			 	
//			 	String accionComplementadas = accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId( ), 1);
//			 	objectResumen.setComplementadasAccion(accionComplementadas);	
//			 	if(accionComplementadas == "0" || accionComplementadas == "1"){
//			 		objectResumen.setZipEstatus(ArchivoTipoAtributes.ZIP_COMPLEMENTADAS);
//			 	}else if(accionComplementadas == "3"){
//			 		objectResumen.setRequiredUpdate(true);
//			 	}
//			 	
//			 	String accionRechazadas = accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId(), 2);
//			 	objectResumen.setRechazadasAccion(accionRechazadas);
//			 	if(accionRechazadas == "0" || accionRechazadas == "1"){
//			 		objectResumen.setZipEstatus(ArchivoTipoAtributes.ZIP_RECHAZADAS);
//			 	}else if(accionRechazadas == "3"){
//			 		objectResumen.setRequiredUpdate(true);
//			 	}
//			 	objectResumen.setLiberadasAccion(accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId(), 3));
			 	//object.getArchivoId();
//			}
//		}else if(origenLote == 1){
//			RadarArchivoEstatusVO object = this.radarArchivoMyBatisDAO.buscaRadarArchivoACancelarFM(archivoId);
//			if (object != null) {
//				objectResumen = new RadarArchivoResumenVO();
//				objectResumen.setArchivoId(object.getArchivoId());
//				objectResumen.setFechaEmision(object.getFechaEmision());
//				objectResumen.setEstatusProceso(object.getEstatusProcesoNombre());
//				objectResumen.setRequiredUpdate(false);
//				EstatusProcesoLote whichValue = EstatusProcesoLote.getArchivoEstatusProceso(object.getEstatusProcesoId());
//				switch (whichValue) {
//					case COMPLEMENTADO:
//						objectResumen.setArchivoComplementado(true);
//						objectResumen.setArchivoLiberado(false);
//						objectResumen.setMostrarCancelacion(true);
//						objectResumen.setArchivoEnLiberacion(false);
//						objectResumen.setFechaComplementacion(object.getFechaComplementacion());
//						break;
//					case LIBERADO:
//						objectResumen.setArchivoLiberado(true);
//						objectResumen.setArchivoComplementado(true);
//						objectResumen.setMostrarCancelacion(false);
//						objectResumen.setArchivoEnLiberacion(false);
//						objectResumen.setFechaLiberacion(object.getFechaLiberacion());
//						DetalleInfraccionesLiberadasVO detalleLiberadas = radarArchivoProcesadosMyBatisDAO.buscarDetalleInfraccionesLiberadasFM(archivoId);
//						if (detalleLiberadas != null) {
//				 			String folios =  detalleLiberadas.getFolioInicial().
//				 							 substring(0, detalleLiberadas.getFolioInicial().length() - 1) + "-" +
//				 							 detalleLiberadas.getFolioFinal().
//			 								 substring(0, detalleLiberadas.getFolioFinal().length() - 1);
//				 			objectResumen.setLiberadasTotal((Long.valueOf(detalleLiberadas.getTotal().toString())));
//				 			objectResumen.setLiberadasFolios(folios);
//						}	
//				 		break;
//						case LISTO_PARA_LIBERAR:
//						case LIBERANDO:
//							objectResumen.setArchivoComplementado(true);
//							objectResumen.setArchivoLiberado(false);
//							objectResumen.setMostrarCancelacion(true);
//							objectResumen.setArchivoEnLiberacion(true);
//							objectResumen.setRequiredUpdate(true);
//						break;
//				}
//				 
//			 	Map archivoTotales = this.totalesArchivo(archivoId, object.getEstatusProcesoId());
//			 	objectResumen.setArchivoTotal((Long) (archivoTotales.get("TOTAL") == null ? 0L : archivoTotales.get("TOTAL"))); 	
//			 	objectResumen.setComplementadasValidasTotal((Long) (archivoTotales.get("COMPLEMENTADAS") == null ? 0L : archivoTotales.get("COMPLEMENTADAS")));
//			 	objectResumen.setComplementadasInvalidasTotal((Long) (archivoTotales.get("RECHAZADAS") == null ? 0L : archivoTotales.get("RECHAZADAS")));
//			 	objectResumen.setLiberadasTotal((Long) (archivoTotales.get("LIBERADAS") == null ? 0L : archivoTotales.get("LIBERADAS")));
//			 	
//			 	String accionComplementadas = accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId( ), 1);
//			 	objectResumen.setComplementadasAccion(accionComplementadas);	
//			 	if(accionComplementadas == "0" || accionComplementadas == "1"){
//			 		objectResumen.setZipEstatus(ArchivoTipoAtributes.ZIP_COMPLEMENTADAS);
//			 	}else if(accionComplementadas == "3"){
//			 		objectResumen.setRequiredUpdate(true);
//			 	}
//			 	
//			 	String accionRechazadas = accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId(), 2);
//			 	objectResumen.setRechazadasAccion(accionRechazadas);
//			 	if(accionRechazadas == "0" || accionRechazadas == "1"){
//			 		objectResumen.setZipEstatus(ArchivoTipoAtributes.ZIP_RECHAZADAS);
//			 	}else if(accionRechazadas == "3"){
//			 		objectResumen.setRequiredUpdate(true);
//			 	}
//			 	objectResumen.setLiberadasAccion(accionTotalesArchivo(object.getArchivoId(), object.getEstatusProcesoId(), 3));
			 	//object.getArchivoId();
//			}
//		}
//		return objectResumen;
	}
	
	/***
	 * @author Jesus Gutierrez 
	 * @param archivoId
	 * @param estatusProcesoId
	 * @return Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map totalesArchivo(Long archivoId, Integer estatusProcesoId){
		List<ArchivosTotalesVO> objects = null;
		Map conceptos = new HashMap();
		Integer origenLote = radarArchivoProcesadosMyBatisDAO.buscarOrigenById(archivoId);
		if(origenLote == 0){
			if(estatusProcesoId == EstatusProcesoLote.LIBERADO.getEstatusProceso()){
				objects = this.radarArchivoDetalleMyBatisDAO.totalesArchivoLiberado(archivoId);
			}else if(estatusProcesoId == EstatusProcesoLote.CANCELADO.getEstatusProceso()){
				objects = this.radarArchivoDetalleMyBatisDAO.totalesArchivoCancelado(archivoId);
			}else{
				objects = this.radarArchivoDetalleMyBatisDAO.totalesArchivo(archivoId);
			}
			
			for(ArchivosTotalesVO concepto : objects){
				conceptos.put(concepto.getArchivoConcepto(), concepto.getArchivoTotal());
			}
		}else if(origenLote == 1){
			if(estatusProcesoId == EstatusProcesoLote.LIBERADO.getEstatusProceso()){
				objects = this.radarArchivoDetalleMyBatisDAO.totalesArchivoLiberadoFM(archivoId);
			}else if(estatusProcesoId == EstatusProcesoLote.CANCELADO.getEstatusProceso()){
				objects = this.radarArchivoDetalleMyBatisDAO.totalesArchivoCanceladoFM(archivoId);
			}else{
				objects = this.radarArchivoDetalleMyBatisDAO.totalesArchivoFM(archivoId);
			}
			
			for(ArchivosTotalesVO concepto : objects){
				conceptos.put(concepto.getArchivoConcepto(), concepto.getArchivoTotal());
			}
		}
		
		return conceptos;
	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public String accionTotalesArchivo(Long archivoId, Integer estatusProcesoId, Integer accion) {
		String code = "";
		String estatusArchivo = "";
		Integer origenLote = radarArchivoProcesadosMyBatisDAO.buscarOrigenById(archivoId);
		if(origenLote == 0){
			//Complementadas
			if(accion == 1){
				if(EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso() == estatusProcesoId
						|| EstatusProcesoLote.LIBERADO.getEstatusProceso() == estatusProcesoId){
					
					estatusArchivo = this.radarArchivoMyBatisDAO.buscarEstatusArchivoZIP(archivoId, ArchivoTipoAtributes.ARCHIVO_COMPLEMENTADAS);
					if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_GENERADO)){
						code = "1";
					}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_SIN_INFORMACION)){
						code = "2";
					}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_EN_PROCESO)){
						code = "3";
					}else{
						code = "0";
					}
				}
			}else if(accion == 2){ //Rechazadas
				if(EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso() == estatusProcesoId
						|| EstatusProcesoLote.LIBERADO.getEstatusProceso() == estatusProcesoId){
					
					estatusArchivo = this.radarArchivoMyBatisDAO.buscarEstatusArchivoZIP(archivoId, ArchivoTipoAtributes.ARCHIVO_RECHAZADAS);
					if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_GENERADO)){
						code = "1";
					}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_SIN_INFORMACION)){
						code = "2";
					}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_EN_PROCESO)){
						code = "3";
					}else{
						code = "0";
					}
				}
			}else if(accion == 3){ //Liberadas
				if(this.radarArchivoMyBatisDAO.isArchivoComplementado(archivoId) == 0){
					code = "0";
				}else if(EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso() == estatusProcesoId){
					code = "1";
					
				}
			}
		}else if(origenLote == 1){
			//Complementadas
			if(accion == 1){
				if(EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso() == estatusProcesoId
						|| EstatusProcesoLote.LIBERADO.getEstatusProceso() == estatusProcesoId){
					
					estatusArchivo = this.radarArchivoMyBatisDAO.buscarEstatusArchivoZIPFM(archivoId, ArchivoTipoAtributes.ARCHIVO_COMPLEMENTADAS);
					if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_GENERADO)){
						code = "1";
					}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_SIN_INFORMACION)){
						code = "2";
					}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_EN_PROCESO)){
						code = "3";
					}else{
						code = "0";
					}
				}
			}else if(accion == 2){ //Rechazadas
				if(EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso() == estatusProcesoId
						|| EstatusProcesoLote.LIBERADO.getEstatusProceso() == estatusProcesoId){
					
					estatusArchivo = this.radarArchivoMyBatisDAO.buscarEstatusArchivoZIPFM(archivoId, ArchivoTipoAtributes.ARCHIVO_RECHAZADAS);
					if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_GENERADO)){
						code = "1";
					}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_SIN_INFORMACION)){
						code = "2";
					}else if(estatusArchivo.equals(ArchivoTipoAtributes.ARCHIVO_EN_PROCESO)){
						code = "3";
					}else{
						code = "0";
					}
				}
			}else if(accion == 3){ //Liberadas
				if(this.radarArchivoMyBatisDAO.isArchivoComplementadoFM(archivoId) == 0){
					code = "0";
				}else if(EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso() == estatusProcesoId){
					code = "1";
					
				}
			}
		}
		return code;
	}


	@Override
	public Map crearExcelErrores(List<DeteccionesIncorrectasVO> detErrores) {
		// TODO Auto-generated method stub
		Map response = new HashMap();
		System.out.println("Creas Excel erroneas");
		String nombreArchivo;
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		String[] titulosEst = { 
				"TIPO DETECCIÓN","ORIGEN","PLACA","FECHA","HORA","TDSKUID","UT", "VELOCIDAD DETECTADA",
				"NOMBRE","APELLIDO PATERNO","APELLIDO MATERNO",
				"CALLE","NUMERO EXTERIOR","NUMERO INTERIOR","COLONIA","CODIGO POSTAL","ENTIDAD FEDERATIVA",
				"MUNICIPIO/DELEGACION","TELEFONO","MARCA","SUBMARCA","MODELO", "SERIE", "MOTOR",
				 "ARTÍCULO ID"
				 };
		if(!detErrores.isEmpty()){
			//Resultados de la tabla
			List<Object> contenido = new ArrayList<Object>();
			List<Object> contenido1 = new ArrayList<Object>();
					
			//Leyendas de las columnas de las tablas
			List<Object> encabezadoTitulo = new ArrayList<Object>();
			List<String> titulos = Arrays.asList(titulosEst); 
			encabezadoTitulo.add(titulos);
			
			RutinasTiempoImpl rutinastiempo = new RutinasTiempoImpl();
			propiedadesReporte.setFechaI(rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date()));
			
			propiedadesReporte.setTituloExcel("Registros con errores");
			propiedadesReporte.setExtencionArchvio(".xls");
			
			//cuerpo del reporte
			List<String> listaContenido1;
			
			for(DeteccionesIncorrectasVO list : detErrores){
				listaContenido1 = new ArrayList<String>();				
				listaContenido1.add(list.getTipodeteccion());
				listaContenido1.add(list.getOrigenplaca());
				listaContenido1.add(list.getPlaca());
				listaContenido1.add(list.getFecha());
				listaContenido1.add(list.getHora());
				listaContenido1.add(list.getTdskuid());
				listaContenido1.add(list.getUt());
				listaContenido1.add(list.getVelocidaddetectada());
				listaContenido1.add(list.getNombre());
				listaContenido1.add(list.getApellidopat());
				listaContenido1.add(list.getApellidomat());
				listaContenido1.add(list.getCalle());
				listaContenido1.add(list.getNumext());
				listaContenido1.add(list.getNumint());
				listaContenido1.add(list.getColonia());
				listaContenido1.add(list.getCp());
				listaContenido1.add(list.getEntidad());
				listaContenido1.add(list.getDelegacion());
				listaContenido1.add(list.getTelefono());
				listaContenido1.add(list.getMarca());
				listaContenido1.add(list.getSubmar());
				listaContenido1.add(list.getModelo());
				listaContenido1.add(list.getNumserie());
				listaContenido1.add(list.getNummotor());
				listaContenido1.add(list.getArtid());

				contenido1.add(listaContenido1);
			}
			
			contenido.add(contenido1);
			
			try {
//				creaDirectoriosPorRuta(parametroService.getRutaZip());
				nombreArchivo = parametroService.getRutaZip();
				Random rand = new Random();
				int  n = rand.nextInt(100) + 1;
				String namereal = "Reporte de errores_"+n+
				rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date());
				
				propiedadesReporte.setNombreReporte(namereal);
				
				peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
				peticionReporteVO.setEncabezado(encabezadoTitulo);
				peticionReporteVO.setContenido(contenido);
				reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
				
		    	response.put("reporte", reporte);
				response.put("nombre", propiedadesReporte.getNombreReporte());
				
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			
		}
		return response;
		
	}
	
	@Override
	public void crearExcelComplementadas(String ArchivoId, String fileName) {
		boolean comprime = false;
		System.out.println("Creas Excel complementadas");
		String nombreArchivo;
		String columnaArchivoComplementadas = "archivo_complementadas";
		Integer origenLote = radarArchivoProcesadosMyBatisDAO.buscarOrigenById(Long.parseLong(ArchivoId));
		List<DeteccionesComplementadasVO> lista = null;
//		if(origenLote == 0){
			actualizaRegistroArchivosExcel(columnaArchivoComplementadas, "GENERAR",comprime,ArchivoId);
			lista = radarArchivoMyBatisDAO.listaDeteccionesComplementadas(ArchivoId);
//		}else if(origenLote == 1){
//			lista = radarArchivoMyBatisDAO.listaDeteccionesComplementadasFM(ArchivoId);
//		}

		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		String[] titulosEst = { "Placa", "Fecha", "Hora", "TDSKUID", "UT",
				"Velocidad", "Nombre", "Domicilio", "CP",
				"Delegación/Municipio", "Entidad Federativa", "Teléfono",
				"Marca", "Submarca", "Modelo", "Núm. Serie", "Motor",
				"Folio Infracción", "Días SMV", "Porcentaje Descuento",
				"Fecha Emisión/Fecha Proceso", "Fecha Imposición",
				"Fecha Vencimiento", "Importe Infracción",
				"Importe Descuento", "Importe Total", "Línea de Captura",
				"Centro de Reparto", "Clave de Pago", "Calle",
				"Entre Calle", "Y Calle", "Sector", "Delegación",
				"Oficial Placa", "Oficial Nombre", "Articulo", "Fraccion", "Inciso", "Parrafo",
				"Oficial Placa Firmante", "Oficial RFC Firmante", "Cadena Original", "Firma Electrónica"};
		
		if(!lista.isEmpty()){
			//Resultados de la tabla
			List<Object> contenido = new ArrayList<Object>();
			List<Object> contenido1 = new ArrayList<Object>();
					
			//Leyendas de las columnas de las tablas
			List<Object> encabezadoTitulo = new ArrayList<Object>();
			List<String> titulos = Arrays.asList(titulosEst); 
		
			
			encabezadoTitulo.add(titulos);
		
			RutinasTiempoImpl rutinastiempo = new RutinasTiempoImpl();
			propiedadesReporte.setFechaI(rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date()));
			
			propiedadesReporte.setTituloExcel("Infracciones Complementadas");
			propiedadesReporte.setExtencionArchvio(".xls");
			
			//cuerpo del reporte
			List<String> listaContenido1;
			
			for(DeteccionesComplementadasVO list : lista){
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(list.getPlaca());
				listaContenido1.add(list.getFecha());
				listaContenido1.add(list.getHora());
				listaContenido1.add(list.getTdskuid());
				listaContenido1.add(list.getUt());
				listaContenido1.add(list.getVelocidad_detectada());
				listaContenido1.add(list.getNombre());
				listaContenido1.add(list.getDomicilio());
				listaContenido1.add(list.getCodigo_postal());
				listaContenido1.add(list.getMunicipio());
				listaContenido1.add(list.getEntidad_federativa());
				listaContenido1.add(list.getTelefono());
				listaContenido1.add(list.getMarca());
				
				listaContenido1.add(list.getSubmarca());
				listaContenido1.add(list.getModelo());
				listaContenido1.add(list.getSerie());
				listaContenido1.add(list.getMotor());
				listaContenido1.add(list.getInfrac_num());
				listaContenido1.add(list.getDias());
				
				listaContenido1.add(list.getPorcentaje_descuento());
				listaContenido1.add(list.getFecha_emision());
				listaContenido1.add(list.getFecha_imposicion());
				listaContenido1.add(list.getFecha_vencimiento());
				listaContenido1.add(list.getImporte_infraccion());
				listaContenido1.add(list.getImporte_descuento());
				listaContenido1.add(list.getImporte_total());
				
				listaContenido1.add(list.getLinea_captura());
				listaContenido1.add(list.getCentro_reparto());
				listaContenido1.add(list.getClave_pago());
				listaContenido1.add(list.getCalle());
				listaContenido1.add(list.getEntre_calle());
				listaContenido1.add(list.getY_calle());
				listaContenido1.add(list.getSector());
	
				listaContenido1.add(list.getDelegacion());
				listaContenido1.add(list.getOficial_placa());
				listaContenido1.add(list.getOficial_nombre());
				
				listaContenido1.add(list.getArticulo()+"");
				listaContenido1.add(list.getFraccion()+"");
				listaContenido1.add(list.getInciso());
				listaContenido1.add(list.getParrafo());
				
				listaContenido1.add(list.getEmpCodFirmante());
				listaContenido1.add(list.getCd_rfc_firmante());
				listaContenido1.add(list.getTx_cadena_original());
				listaContenido1.add(list.getTx_firma_eletronica());
				
				contenido1.add(listaContenido1);
			}
			
			contenido.add(contenido1);
			
			try {
				creaDirectoriosPorRuta(parametroService.getRutaZip());
				nombreArchivo = parametroService.getRutaZip();
				String namereal = "";
				if (fileName.contains(".xml")) {
					namereal = fileName.replace(".xml", "_" + ArchivoId + "_comp");
				}else{
					namereal = fileName+ "_" + ArchivoId + "_comp";
				}
				propiedadesReporte.setNombreReporte(namereal);
				propiedadesReporte.setRutaArchivo(nombreArchivo);
				peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
				peticionReporteVO.setEncabezado(encabezadoTitulo);
				peticionReporteVO.setContenido(contenido);
				reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
				nombreArchivo += namereal + ".xls";
				
				String rutaArchivoZip = nombreArchivo.replace(".xls", ".zip");
				comprime = comprime(nombreArchivo, rutaArchivoZip);
				
				File archivoExcel = new File(nombreArchivo);
				archivoExcel.delete();
				if(origenLote == 0){
					actualizaRegistroArchivosExcel(columnaArchivoComplementadas, "CREAR", comprime,ArchivoId);
				}
			} catch (IOException e) {
	 			e.printStackTrace();
			}
		} else {
			if(origenLote == 0){
				actualizaRegistroArchivosExcel(columnaArchivoComplementadas, "SIN_INFORMACION", comprime, ArchivoId);
			}
		}
	}
		
		
	private void actualizaRegistroArchivosExcel(String tipoColumna,String accion, boolean generado,String ArchivoId) {
		int pGenerar;
		
		if (accion.equals("GENERAR")) {
			pGenerar = 2;
			
		} else if (accion.equals("SIN_INFORMACION")) {
			pGenerar = 3;
			
		} else {
			pGenerar = generado ? 1 : 0;
			
		}
		
		if(tipoColumna.equals("archivo_complementadas")){
			radarArchivoMyBatisDAO.updateRadarArchivo(tipoColumna,pGenerar, ArchivoId);
		}else{
			radarArchivoMyBatisDAO.updateRadarArchivoRechazadas(pGenerar, ArchivoId);
		}
		
		
	}



//	@Override
//	public void crearExcelRechazadas(String ArchivoId, String fileName) {
//		
//		boolean comprime = false;
//		String nombreArchivo;
//		String columnaArchivoRechazadas = "archivo_rechazadas";
//		actualizaRegistroArchivosExcel(columnaArchivoRechazadas, "GENERAR",comprime,ArchivoId);
//		List<DeteccionesComplementadasVO> lista = radarArchivoMyBatisDAO.listaDeteccionesRechazadas(ArchivoId);
//
//			ByteArrayOutputStream reporte = new ByteArrayOutputStream();
//			PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
//			PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
//			PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
//			
//			String[] titulosEst = { "Placa", "Fecha", "Hora", "TDSKUID", "UT",
//					"Velocidad", "Nombre", "Domicilio", "CP",
//					"Delegación/Municipio", "Entidad Federativa", "Teléfono",
//					"Marca", "Submarca", "Modelo", "Núm. Serie", "Motor",
//					"Folio Infracción", "Días SMV", "Porcentaje Descuento",
//					"Fecha Emisión/Fecha Proceso", "Fecha Imposición",
//					"Fecha Vencimiento", "Importe Infracción",
//					"Importe Descuento", "Importe Total", "Línea de Captura",
//					"Centro de Reparto", "Clave de Pago", "Calle",
//					"Entre Calle", "Y Calle", "Sector", "Delegación",
//					"Oficial Placa", "Oficial Nombre" };
//			
//			if(!lista.isEmpty()){
//			//Resultados de la tabla
//			List<Object> contenido = new ArrayList<Object>();
//			List<Object> contenido1 = new ArrayList<Object>();
//					
//			//Leyendas de las columnas de las tablas
//			List<Object> encabezadoTitulo = new ArrayList<Object>();
//			List<String> titulos = Arrays.asList(titulosEst); 
//		
//			
//			encabezadoTitulo.add(titulos);
//			
//			RutinasTiempoImpl rutinastiempo = new RutinasTiempoImpl();
//			propiedadesReporte.setFechaI(rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date()));
//			
//			propiedadesReporte.setTituloExcel("Infracciones Rechazadas");
//			propiedadesReporte.setExtencionArchvio(".xls");
//			
//			
//			//cuerpo del reporte
//			List<String> listaContenido1;
//			
//			for(DeteccionesComplementadasVO list : lista){
//				listaContenido1 = new ArrayList<String>();
//				listaContenido1.add(list.getPlaca());
//				listaContenido1.add(list.getFecha());
//				listaContenido1.add(list.getHora());
//				listaContenido1.add(list.getTdskuid());
//				listaContenido1.add(list.getUt());
//				listaContenido1.add(list.getVelocidad_detectada());
//				listaContenido1.add(list.getNombre());
//				listaContenido1.add(list.getDomicilio());
//				listaContenido1.add(list.getCodigo_postal());
//				listaContenido1.add(list.getMunicipio());
//				listaContenido1.add(list.getEntidad_federativa());
//				listaContenido1.add(list.getTelefono());
//				listaContenido1.add(list.getMarca());
//				
//				listaContenido1.add(list.getSubmarca());
//				//listaContenido1.add(list.getDomicilio());
//				listaContenido1.add(list.getModelo());
//				listaContenido1.add(list.getSerie());
//				listaContenido1.add(list.getMotor());
//				listaContenido1.add(list.getInfrac_num());
//				listaContenido1.add(list.getDias());
//				
//				listaContenido1.add(list.getPorcentaje_descuento());
//				listaContenido1.add(list.getFecha_emision());
//				listaContenido1.add(list.getFecha_imposicion());
//				listaContenido1.add(list.getFecha_vencimiento());
//				listaContenido1.add(list.getImporte_infraccion());
//				listaContenido1.add(list.getImporte_descuento());
//				listaContenido1.add(list.getImporte_total());
//				
//				listaContenido1.add(list.getLinea_captura());
//				listaContenido1.add(list.getCentro_reparto());
//				listaContenido1.add(list.getClave_pago());
//				listaContenido1.add(list.getCalle());
//				listaContenido1.add(list.getEntre_calle());
//				listaContenido1.add(list.getY_calle());//YCALLE
//				listaContenido1.add(list.getSector());
//
//				listaContenido1.add(list.getDelegacion());
//				listaContenido1.add(list.getOficial_placa());
//				listaContenido1.add(list.getOficial_nombre());
//				
//				
//				contenido1.add(listaContenido1);
//			}
//			
//			contenido.add(contenido1);
//			
//			peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
//			peticionReporteVO.setEncabezado(encabezadoTitulo);
//			peticionReporteVO.setContenido(contenido);
//			
//			try {
//				creaDirectoriosPorRuta(parametroService.getRutaZip());
//				nombreArchivo = parametroService.getRutaZip();
//				String namereal = "";
//				
//				if (fileName.contains(".xml")) {
//					namereal = fileName.replace(".xml", "_" + ArchivoId + "_err");
//				} else {
//					namereal = fileName+"_" + ArchivoId + "_err";
//				}
//				
//				
//			
//				propiedadesReporte.setNombreReporte(namereal);
//				propiedadesReporte.setRutaArchivo(nombreArchivo);
//				peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
//				peticionReporteVO.setEncabezado(encabezadoTitulo);
//				peticionReporteVO.setContenido(contenido);
//				reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
//				nombreArchivo += namereal + ".xls";
//				
//				String rutaArchivoZip = nombreArchivo.replace(".xls", ".zip");
//				comprime = comprime(nombreArchivo, rutaArchivoZip);
//				
//				File archivoExcel = new File(nombreArchivo);
//				archivoExcel.delete();
//				
//				actualizaRegistroArchivosExcel(columnaArchivoRechazadas,
//						"CREAR", comprime,ArchivoId);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//			
//			}else {
//				actualizaRegistroArchivosExcel(columnaArchivoRechazadas,
//						"SIN_INFORMACION", comprime, ArchivoId);
//			}
//	 		
//	}
	
	@Override
	public void crearExcelRechazadas(String ArchivoId, String fileName) {
		
		boolean comprime = false;
		String nombreArchivo;
		String columnaArchivoRechazadas = "archivo_rechazadas";
		Integer origenLote = radarArchivoProcesadosMyBatisDAO.buscarOrigenById(Long.parseLong(ArchivoId));
		List<DeteccionesComplementadasVO> lista = null;
//		if(origenLote == 0){
			actualizaRegistroArchivosExcel(columnaArchivoRechazadas, "GENERAR",comprime,ArchivoId);
			lista = radarArchivoMyBatisDAO.listaDeteccionesRechazadas(ArchivoId);
//		}else if(origenLote == 1){
//			lista = radarArchivoMyBatisDAO.listaDeteccionesRechazadasFM(ArchivoId);
//		}

		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		String[] titulosEst = { "Placa", "Fecha", "Hora", "TDSKUID", "UT",
				"Velocidad", "Nombre", "Domicilio", "CP",
				"Delegación/Municipio", "Entidad Federativa", "Teléfono",
				"Marca", "Submarca", "Modelo", "Núm. Serie", "Motor",
				"Folio Infracción", "Días SMV", "Porcentaje Descuento",
				"Fecha Emisión/Fecha Proceso", "Fecha Imposición",
				"Fecha Vencimiento", "Importe Infracción",
				"Importe Descuento", "Importe Total", "Línea de Captura",
				"Centro de Reparto", "Clave de Pago", "Calle",
				"Entre Calle", "Y Calle", "Sector", "Delegación",
				"Oficial Placa", "Oficial Nombre" ,"Motivo de Rechazo"};
		
		if(!lista.isEmpty()){
			//Resultados de la tabla
			List<Object> contenido = new ArrayList<Object>();
			List<Object> contenido1 = new ArrayList<Object>();
			
			//Leyendas de las columnas de las tablas
			List<Object> encabezadoTitulo = new ArrayList<Object>();
			List<String> titulos = Arrays.asList(titulosEst); 
			
			encabezadoTitulo.add(titulos);
			
			RutinasTiempoImpl rutinastiempo = new RutinasTiempoImpl();
			propiedadesReporte.setFechaI(rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date()));
			
			propiedadesReporte.setTituloExcel("Infracciones Rechazadas");
			propiedadesReporte.setExtencionArchvio(".xls");
			
			//cuerpo del reporte
			List<String> listaContenido1;
			
			for(DeteccionesComplementadasVO list : lista){
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(list.getPlaca());
				listaContenido1.add(list.getFecha());
				listaContenido1.add(list.getHora());
				listaContenido1.add(list.getTdskuid());
				listaContenido1.add(list.getUt());
				listaContenido1.add(list.getVelocidad_detectada());
				listaContenido1.add(list.getNombre());
				listaContenido1.add(list.getDomicilio());
				listaContenido1.add(list.getCodigo_postal());
				listaContenido1.add(list.getMunicipio());
				listaContenido1.add(list.getEntidad_federativa());
				listaContenido1.add(list.getTelefono());
				listaContenido1.add(list.getMarca());
				
				listaContenido1.add(list.getSubmarca());
				//listaContenido1.add(list.getDomicilio());
				listaContenido1.add(list.getModelo());
				listaContenido1.add(list.getSerie());
				listaContenido1.add(list.getMotor());
				listaContenido1.add(list.getInfrac_num());
				listaContenido1.add(list.getDias());
				
				listaContenido1.add(list.getPorcentaje_descuento());
				listaContenido1.add(list.getFecha_emision());
				listaContenido1.add(list.getFecha_imposicion());
				listaContenido1.add(list.getFecha_vencimiento());
				listaContenido1.add(list.getImporte_infraccion());
				listaContenido1.add(list.getImporte_descuento());
				listaContenido1.add(list.getImporte_total());
				
				listaContenido1.add(list.getLinea_captura());
				listaContenido1.add(list.getCentro_reparto());
				listaContenido1.add(list.getClave_pago());
				listaContenido1.add(list.getCalle());
				listaContenido1.add(list.getEntre_calle());
				listaContenido1.add(list.getY_calle());//YCALLE
				listaContenido1.add(list.getSector());
				
				listaContenido1.add(list.getDelegacion());
				listaContenido1.add(list.getOficial_placa());
				listaContenido1.add(list.getOficial_nombre());
				listaContenido1.add(list.getMotivo());
				contenido1.add(listaContenido1);
			}
			contenido.add(contenido1);
			
//			peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
//			peticionReporteVO.setEncabezado(encabezadoTitulo);
//			peticionReporteVO.setContenido(contenido);
			
			try {
				creaDirectoriosPorRuta(parametroService.getRutaZip());
				nombreArchivo = parametroService.getRutaZip();
				String namereal = "";
				
				if (fileName.contains(".xml")) {
					namereal = fileName.replace(".xml", "_" + ArchivoId + "_err");
				} else {
					namereal = fileName+"_" + ArchivoId + "_err";
				}
				
				propiedadesReporte.setNombreReporte(namereal);
				propiedadesReporte.setRutaArchivo(nombreArchivo);
				peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
				peticionReporteVO.setEncabezado(encabezadoTitulo);
				peticionReporteVO.setContenido(contenido);
				reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
				nombreArchivo += namereal + ".xls";
				
				String rutaArchivoZip = nombreArchivo.replace(".xls", ".zip");
				comprime = comprime(nombreArchivo, rutaArchivoZip);
				
				File archivoExcel = new File(nombreArchivo);
				archivoExcel.delete();
				if(origenLote == 0){
					actualizaRegistroArchivosExcel(columnaArchivoRechazadas,"CREAR", comprime,ArchivoId);
				}
			} catch (IOException e) {
	 			e.printStackTrace();
			}
		}else {
			if(origenLote == 0){
				actualizaRegistroArchivosExcel(columnaArchivoRechazadas,"SIN_INFORMACION", comprime, ArchivoId);
			}
		}
	}

	public boolean comprime(String reporte, String target) {
		try {
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
					target));

			FileInputStream fis = new FileInputStream(reporte);
			File file = new File(reporte);
			zos.putNextEntry(new ZipEntry(file.getName()));
			int i;
			do {
				i = fis.read();
				if (i != -1) {
					zos.write(i);
				}
			} while (i != -1);
			zos.closeEntry();
			fis.close();

			zos.close();
		} catch (IOException e) {
			System.out.println("Error al crear archivo ZIP: " + e.getMessage());
			e.printStackTrace();
			return false;
		}

		return true;
	}


	public boolean creaDirectoriosPorRuta(String path) {

		File directorio = new File(parametroService.getRutaZip());
		if (!directorio.exists()) {
			return directorio.mkdirs();
		} else {
			return true;
		}
	}

	@Override
	public Boolean notificaLiberacionLote(Long archivoId, Integer tipoProcesoWS) {
		RutinasTiempoImpl rutina = new RutinasTiempoImpl();
		Date currentDate = new Date();
		LiberacionLote request = new LiberacionLote();
		//fotomultaWS = (WSFotoMulta) new WSFotoMultaImpl();
		String tipoLote = fotomultaLotesMyBatisDAO.buscaTipoLoteFotoMulta(archivoId);
		request.setLote(archivoId.toString());
		request.setFecha(rutina.getStringDateFromFormta("dd/MM/yyyy", currentDate));
		request.setHora(rutina.getStringDateFromFormta("HH:mm:ss", currentDate));
		request.setTipoRadar(tipoLote);
		
		fotomultaWS.liberacionLote(request);
		
		Integer estatus = fotomultaBitacoraMyBatisDAO.buscaEstatusEjecucionFotomultaBitacora(archivoId, tipoProcesoWS);
		if(estatus != null){
			if(estatus == 0){
				 return true;
			}
		}
		return false;
	}
	
	@Override
	public Boolean notificaAsignacionLote(Long archivoId, Integer tipoProcesoWS, String nombreLote) {
		String nombreArchivo = nombreLote+"_"+archivoId+"_fotomulta";
		AsignacionLote request = new AsignacionLote();
		this.radarUtils = new RadarUtils();
		//fotomultaWS = (WSFotoMulta) new WSFotoMultaImpl();
		String tipoLote = this.fotomultaLotesMyBatisDAO.buscaTipoLoteFotoMulta(archivoId);
		request.setLote(archivoId.toString());
		request.setTipoRadar(tipoLote);
		List<String> lista = radarArchivoDetalleMyBatisDAO.buscaInformacionFotoMulta(archivoId);
		request.setFileContents(radarUtils.getBytesArchivoFotomulta(archivoId, nombreArchivo, parametros.getRutaZip(), lista));
		
		fotomultaWS.asignacionLote(request);
		Integer estatus = fotomultaBitacoraMyBatisDAO.buscaEstatusEjecucionFotomultaBitacora(archivoId, tipoProcesoWS);
		if(estatus != null){
			if(estatus == 0){
				 return true;
			}
		}
		return false;
	}

//	@Override
//	public Integer complementarArchivo(Long archivoId) throws BusinessException {
//		final Integer enProceso = 1;
//		Integer ArchivoEnompletacion = radarArchivoMyBatisDAO.buscarArchivoEnComplementacion(enProceso);
//		if (ArchivoEnompletacion == 0) {
//			return radarArchivoMyBatisDAO.actualizaArchivoParaComplementacion(archivoId, enProceso);
//		}else{
//			throw new BusinessException("Hay un lote en complementación. Intente mas tarde.");
//		}
//		
//	}
//
//	@Override
//	public ResponseEntity<byte[]> generarZip(Integer tipoZIP, ArchivoBatchFinanzasVO a,String archivoId) throws IOException{
//		String nombreArchivo = "";
//		ResponseEntity<byte[]> response = null;
//        switch (tipoZIP) {
//            case ArchivoTipoAtributes.ZIP_COMPLEMENTADAS:
//                if (a.getFileName().contains(".xml")) {
//					nombreArchivo = a.getFileName().replace(".xml", "_" + a.getArchivoId() + "_comp" + ".zip");
//				} else {
//					nombreArchivo = a.getFileName()+"_" + a.getArchivoId() + "_comp" + ".zip";
//				}
//                break;
//            case ArchivoTipoAtributes.ZIP_RECHAZADAS:
//            	if (a.getFileName().contains(".xml")) {
//					nombreArchivo = a.getFileName().replace(".xml", "_" + a.getArchivoId() + "_err" + ".zip");
//				} else {
//					nombreArchivo = a.getFileName()+"_" + a.getArchivoId() + "_err" + ".zip";
//				}
//                break;
//
//        }
//
//        String rutaArchivo = parametroService.getRutaZip() + nombreArchivo;
//
//        ByteArrayOutputStream byteArray;
//        BufferedInputStream input = null;
//        byte data[];
//        int count;
//
//        if (a != null && (a.getEstatusProcesoId() == EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso() || a.getEstatusProcesoId() == EstatusProcesoLote.LIBERADO.getEstatusProceso())) {
//        
//                File archivo = new File(rutaArchivo);
//                if (!archivo.exists()) {
//                	this.crearExcelComplementadas(archivoId, a.getFileName());
//                }
//                archivo = new File(rutaArchivo);
//                if(archivo.exists()){
//                	
//                	input = new BufferedInputStream(new FileInputStream(archivo));
//                    byteArray = new ByteArrayOutputStream((int) archivo.length());
//                    data = new byte[(int) archivo.length()];
//                    while ((count = input.read(data)) > 0) {
//                    byteArray.write(data, 0, count);
//                    
//                    }
//                    
//                    byteArray.flush();
//                    final byte[] bytes = byteArray.toByteArray();
//                    byteArray.close();
//                    input.close();
//                     
//                    HttpHeaders headers = new HttpHeaders();
//                    headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
//            		headers.add("Content-Disposition", "attachmnt; filename =" + nombreArchivo);
//            		headers.add("filename", nombreArchivo);
//            		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//            		headers.setContentLength(bytes.length);
//           			response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
//            	    
//                }
//		
//        }
//        return response;	
//	}
	
	@Override
	public Integer complementarArchivo(Long archivoId) throws BusinessException {
		final Integer enProceso = 1;
		Integer ArchivoEnompletacion = radarArchivoProcesadosMyBatisDAO.buscarArchivoEnComplementacion(enProceso);
		if (ArchivoEnompletacion == 0) {
			Integer actualizo = radarArchivoProcesadosMyBatisDAO.actualizaRadarTotalParaComplementacion(archivoId, enProceso);
			Integer origenLote = radarArchivoProcesadosMyBatisDAO.buscarOrigenById(archivoId);
			if(origenLote == 0){
				return radarArchivoProcesadosMyBatisDAO.actualizaRadarArchivoParaComplementacion(archivoId, enProceso);
			}else{
				return radarArchivoProcesadosMyBatisDAO.actualizaRadarArchivoParaComplementacion(archivoId, enProceso);
				//return radarArchivoProcesadosMyBatisDAO.actualizaFMLoteParaComplementacion(archivoId, enProceso);
			}
		}else{
			throw new BusinessException("Hay un lote en complementación. Intente mas tarde.");
		}
	}
	
	@Override
	@Transactional
	public Integer enviarArchivo(Long idArchivo, Long empId) throws BusinessException {
		final Integer enProceso = 1;
		final Integer estatusReevioArchivo = 48;
		Integer ArchivoEnompletacion = radarArchivoProcesadosMyBatisDAO.buscarArchivoEnComplementacion(enProceso);
		if(ArchivoEnompletacion==0) {
			radarArchivoEnvioMyBatisDAO.guardaEstatusReenvio(idArchivo);
			Integer update = radarArchivoEnvioMyBatisDAO.updateEstatusRadarArchivo(idArchivo, estatusReevioArchivo, empId);
			radarArchivoEnvioMyBatisDAO.insertarBitacoraRadarArchvio(idArchivo, estatusReevioArchivo, empId);
			return update;
		}else {
			throw new BusinessException("Hay un lote en complementación o en proceso de reenvío. Intente mas tarde.");
		}
	}
	
	@Override
	public ResponseEntity<byte[]> generarZip(Integer tipoZIP, ArchivoBatchFinanzasVO a,String archivoId) throws IOException{
		System.out.println("VIenes a generarZip aqui: "+tipoZIP);
		String nombreArchivo = "";
		ResponseEntity<byte[]> response = null;
        switch (tipoZIP) {
            case ArchivoTipoAtributes.ZIP_COMPLEMENTADAS:
                if (a.getFileName().contains(".xml")) {
					nombreArchivo = a.getFileName().replace(".xml", "_" + a.getArchivoId() + "_comp" + ".zip");
				} else {
					nombreArchivo = a.getFileName()+"_" + a.getArchivoId() + "_comp" + ".zip";
				}
                break;
            case ArchivoTipoAtributes.ZIP_RECHAZADAS:
            	if (a.getFileName().contains(".xml")) {
					nombreArchivo = a.getFileName().replace(".xml", "_" + a.getArchivoId() + "_err" + ".zip");
				} else {
					nombreArchivo = a.getFileName()+"_" + a.getArchivoId() + "_err" + ".zip";
				}
                break;

        }

        String rutaArchivo = parametroService.getRutaZip() + nombreArchivo;
        Integer origenLote = radarArchivoProcesadosMyBatisDAO.buscarOrigenById(Long.parseLong(archivoId));
        ByteArrayOutputStream byteArray;
        BufferedInputStream input = null;
        byte data[];
        int count;

        if (a != null && (a.getEstatusProcesoId() == EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso() || a.getEstatusProcesoId() == EstatusProcesoLote.LIBERADO.getEstatusProceso())) {
        	System.out.println("entraste aqui");
        	File archivo = new File(rutaArchivo);
        	System.out.println("Esta es la ruta del archivo"+rutaArchivo);
			if (!archivo.exists()) {
				if(tipoZIP==ArchivoTipoAtributes.ZIP_COMPLEMENTADAS) {
					this.crearExcelComplementadas(archivoId, a.getFileName());
				}if(tipoZIP==ArchivoTipoAtributes.ZIP_RECHAZADAS) {
					this.crearExcelRechazadas(archivoId, a.getFileName());
				}				
			}
			archivo = new File(rutaArchivo);
			if(archivo.exists()){
				System.out.println("si existe archivo");
				input = new BufferedInputStream(new FileInputStream(archivo));
			    byteArray = new ByteArrayOutputStream((int) archivo.length());
			    data = new byte[(int) archivo.length()];
			    while ((count = input.read(data)) > 0) {
			    	byteArray.write(data, 0, count);
			    }
			    
			    byteArray.flush();
			    final byte[] bytes = byteArray.toByteArray();
			    byteArray.close();
			    input.close();
			     
			    HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
				headers.add("Content-Disposition", "attachmnt; filename =" + nombreArchivo);
				headers.add("filename", nombreArchivo);
				headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
				headers.setContentLength(bytes.length);
				response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
			}
        }
        return response;	
	}
	
	public void actualizaArchivosOrigen(Long loteId) {
		fotomultaDeteccionesMyBatisDAO.actualizarCantProcs(loteId);
		fotomultaDeteccionesMyBatisDAO.actualizarStComp(loteId);
	}
}
