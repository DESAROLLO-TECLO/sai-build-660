package mx.com.teclo.saicdmx.negocio.service.atencionCiudadana;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatMedioSolicitudDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoDocumentoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoTramiteDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatTipoDocumentoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatTipoTramiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegDetTramiteConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegTramiteConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegTramiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACTramiteConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACTramiteDetalleVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACTramiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.AtenCiudaCamposRequeridosVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ExpedienteTramiteVO;

public interface AtencionCiudadanaService {
	ExpedienteTramiteVO guardarExpedineteTramite(ExpedienteTramiteVO expedienteTramiteVO, Long empId);
	
	List<ACTramiteConsultaVO> consultaDefaultModificacion(Boolean op);
	List<CatTipoDocumentoDTO> getCatalogoDocumento();
	List<CatTipoTramiteDTO> getCatalogoTramite();
	List<CatMedioSolicitudDTO> getCatalogoSolicitud();
	List<String> consultaInfraccion(String placa, String infraccion);
	List<String> insertarTramite(ACTramiteVO tramiteVO);
	ByteArrayOutputStream generaReporteTramite(String idTramite);
	
	/**
     * Consulta la lista de tramites de Atencion ciudadana con diferentes criterios de busqueda.
     * 
     * @param String fechaInicio
     * @param String fechaFin
     * @param Integer paramBusqueda
     * @param Sring valorBusqueda
     * @param Integer Atendido
     * @return List<ACTramiteConsultaVO> Lista de entidades encontradas.
     */
	public  List<ACTramiteConsultaVO> buscarTramitesHistorico(String fechaInicio, String fechaFin, Integer paramBusqueda,
														    String valorBusqueda, Integer Atendido,String avanzadoNombre,String avanzadoAP, String avanzadoAM,
															String avanzadoTel, String avanzadoCorreo,
												            String avanzadoEmpresa, Boolean busquedaAvanzada);
	
	public  List<ACTramiteConsultaVO> buscarTramitesParaModificar(String fechaInicio, String fechaFin, Integer paramBusqueda,
		    String valorBusqueda, Integer Atendido);
	
	public Map getReporteExcelConsulta();
	
	public byte[] obtenerBytesExpediente(String folioExpediente);
	
	public ByteArrayOutputStream vistaPrevia(ACTramiteVO altaTramiteVO);
	
	public AtenCiudaCamposRequeridosVO obtenerJsonCamposRequeridos();
	
	public ACTramiteConsultaVO modificaTramite(ACTramiteConsultaVO modficacaTramiteVO, Long empId);
	
	public ExpedienteTramiteVO descargarExpedineteTramite(String folioTramite, String tipoExp);
	
	public ExpedienteTramiteVO remplazarExpedineteTramite(ExpedienteTramiteVO expedienteTramiteVO, Long empId);
	
	public Map<String, String> obtenerParametrosAyuda();
	
	Boolean nuevoSeguimientoTramite(ACSegTramiteVO AcSegTramite, String[] foliosDeInfraccion);
	
	Boolean modificarSeguimientoTramite(ACSegTramiteVO acSegTramite, String idAcTramite, Integer ctSolicitados,  
			Long idUsrModifica, String[] foliosInfraccionNuevos, String[] foliosInfraccionQuitar);
	
	Boolean desactivaSeguimientoTramite(Long idSegTramite, Integer stSegTramite, 
			Long idUsrModifica);
	
	List<ACSegTramiteConsultaVO> getListaSeguimientosTramite(int tipoBusqueda, 
													String valor, 
													Integer idTipoTramite,
													Integer stSegTramite,
													int tipoFecha,
													String fhInicio,
													String fhFin);
	
	Boolean nuevoACTramiteDetalle(ACTramiteDetalleVO acTramiteDetalle);
	
	Boolean actualizarACTramiteDetalle(String idAcTramite,
			Integer ctSolicitados, 
			Integer ctAtendidos, 
			Integer stSegTramite, 
			Long idUsrModifica, 
			String txComentarioTram);
	
	Boolean actualizarACTramiteDetalleFhMod(String idAcTramite, Long idUsrModifica, String txComentario);
	
	Boolean actualizarACTramiteSeguimientoFhMod(String idAcTramite, Long idUsrModifica);
	
	Boolean actualizarACTramiteFhMod(String idAcTramite, Long idUsrModifica);
	
	Boolean realizarCambioDePersona(String idAcTramite, Long idUsrModifica);

	List<ACSegDetTramiteConsultaVO> getListInfCambioDePersona(
			String placaVehicular, String infracNum);
	
	List<ACSegDetTramiteConsultaVO> getInfCambioDePersona(String listInfracciones);

	ByteArrayOutputStream generaReporteExcelSeguimientoTramite(int tipoBusqueda, String valor, Integer idTipoTramite,
			Integer stSegTramite, int tipoFecha, String fhInicio, String fhFin,
			List<ACSegTramiteConsultaVO> acSegTramiteConsultaVO);

	ByteArrayOutputStream generaReporteExcelSegTramiteInfracciones(String placaVehicular, String infracNum,
			String listInfracciones, List<ACSegDetTramiteConsultaVO> listACSegDetTramiteConsulta, String idAcTramite);
}
