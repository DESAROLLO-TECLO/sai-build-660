package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ParametrosBusquedaReporteBitacoraVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.TcaBitacoraCambiosVO;



public interface ReportesBitacoraService {

	List<CatalogoDinamicoVO> consultaComponentesBitacora();
	List<CatalogoDinamicoVO> consultaConceptosPorConponenteId(long componenteId);
	List<TcaBitacoraCambiosVO> consultaBitacoraCambios(ParametrosBusquedaReporteBitacoraVO parametrosBusquedaVO);
	
	//ByteArrayOutputStream generaReporteBitacora(List<TcaBitacoraCambiosVO> listBitComplementada, String nombreReporte,String fechaInicio,String fechaFin);
	
	/*Metodos Bitacora Anterios*/
	
	List<CatalogoDinamicoVO> obtenerListaComponentes();
	List<CatalogoDinamicoVO> obtenerListaConceptos(int componenteId);
	List<TcaBitacoraCambiosVO> consultaBitacoraCambiosAnterior(ParametrosBusquedaReporteBitacoraVO parametrosBusquedaVO);
	
	ByteArrayOutputStream DescargarExcelBitacora();

	
	
}
