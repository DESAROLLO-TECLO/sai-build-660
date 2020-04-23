package mx.com.teclo.saicdmx.negocio.service.garantias;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaEstatusProcesoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaProcesoDTO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.GarantiaDetallePorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.GarantiaPorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsGralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsultaEntregaFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsultaFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaRecepcionInfoFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteGeneralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;




public interface GarantiaService {
	
	List<VSSPGarantiaConsGralFVO> buscarGarantiasSinProcesar(String placaOfical, boolean op);
	Boolean obtenerPerfilCajero();
	List<GarantiaPorPagarVO> buscarGarantiasPorPagar(String valor, String tipo);
	List<GarantiaDetallePorPagarVO> buscarDetalleGarantiaPorPagar(String valor) throws NotFoundException;
	GarantiaDTO getGarantiaById(Long garantiaId);
	GarantiaProcesoDTO busquedaProceso();
	GarantiaEstatusProcesoDTO busquedaEstatusProcesoId();
	ByteArrayOutputStream generaReporteGarantiaRecibe(Long garantiaId, EmpleadosDTO empFirmado, String rutaArchivo, String rutaImagen) throws FileNotFoundException,IOException ;
	List<VSSPGarantiaConsultaEntregaFVO> buscarGarantiaEntrega(String dato, Integer opcionBusqueda);
	ResponseEntity<String> guardarGarantiaEntrega(Long garantiaId, EmpleadosDTO empFirmado, String observacion);
	ByteArrayOutputStream generaReporteGarantiaEntrega(Long garantiaId, String rutaArchivo,String rutaImagen)throws FileNotFoundException;
	Date RutinaInicio(String fechaInicio);
	Date RutinaFin(String fechaFin);
	List<GarantiaProcesoDTO> obtenerGarantiaProceso();
	ByteArrayOutputStream imprimeReporteVoucher(String infracNum);
	List<VSSPGarantiaReporteGeneralFVO> consultaReporteGral(String fechaInicio, String fechaFin);
	
	ByteArrayOutputStream generaReporteExcel(List<VSSPGarantiaReporteGeneralFVO> garantiaReporteGeneralVO, String fechaInicio, String fechaFin);
	ByteArrayOutputStream generarReporteExcelGarantiasRecepcion(List<VSSPGarantiaConsGralFVO> garantiasRecepcionVO);
	ByteArrayOutputStream generarReporteExcelGarantiasEntrega(List<VSSPGarantiaConsultaEntregaFVO> garantiasEntregaVO, Boolean op);
	ByteArrayOutputStream generarReporteExcelConsulta(List<VSSPGarantiaConsultaFVO> garantiasConsultaVO);
	
	String obtenerTokenMit();
	PagoVO pagarGarantiaPorDocumento(DatosPagoVO datosPagoVO);
	PagoVO pagarGarantiaPorTarjeta(DatosPagoVO datosPagoVO);
	void guardarGarantiaPagada(Integer tipoPago, String infracNum);
	ByteArrayOutputStream imprimirVoucherCentroPagos(String numOperacion);
	
	ByteArrayOutputStream imprimirVoucherCancelacion(String numOperacion,String tranId);
	VSSPGarantiaRecepcionInfoFVO getGarantiaByIdInfo(Long garantiaId);
	Boolean generarGarantiasSinProcesar(String empPlaca, Long id);
	
	Boolean generarGarantiasSinProcesarPorInf(String infrac_num, Long emp_id);
	
	ByteArrayOutputStream generaReporteGarantiaRecibeMasiva(Long idGarantiaInd , EmpleadosDTO empFirmado, String rutaArchivo, String rutaImagen, Integer banderaJasper, Integer idLote) throws FileNotFoundException,IOException ;
	ByteArrayOutputStream generaReporteGarantiaRecibeMasiva2(Long idGarantiaInd , EmpleadosDTO empFirmado, String rutaArchivo2, String rutaImagen, Integer banderaJasper, Integer idLote) throws FileNotFoundException,IOException ;
	String obtenerRutaCarpeta(String cdLlave);
	Map<String, String> getParametrosLP();
	
}
