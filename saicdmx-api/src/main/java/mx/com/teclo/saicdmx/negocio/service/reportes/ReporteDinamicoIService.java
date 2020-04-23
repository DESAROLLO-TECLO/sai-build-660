package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesDiariasVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteInfraccionesGralVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.RptInfraccionesEmpleado;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.TotalInfraccionesporArticuloVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;


public interface ReporteDinamicoIService {
	
	ReporteDinamicoVO obtenerListaReportes(Long empledoId) throws NotFoundException;
	List<CatalogoDinamicoVO> catalogoNombreDepositos();
	List<CatalogoDinamicoVO> catalogoArticulosActivos();
	
	List<CatalogoDinamicoVO> consultaEmpleados(String PlacasOficial);
	
	List<CatalogoDinamicoVO> catalogoDelegaciones();
	
	List<InfraccionesDiariasVO> InfraccionesDiarias(String fechaInicio);
	List<InfraccionesDiariasVO> InfraccionesDiariasDetalle(String fechaInicio);
	List<ReporteInfraccionesGralVO> consultaInfraccionesGral(String fechaInicio ,String fechaFin);
	
	/*Infracciones por articulo */
	List<ReporteInfraccionesGralVO> consultaInfraccionesArticulo(ReporteInfraccionesGralVO datos);
	/*total infracciones por articulo */
	List<TotalInfraccionesporArticuloVO> consultaInfraccionesArticuloDetalle(TotalInfraccionesporArticuloVO ParametrosBusqueda);
	ByteArrayOutputStream TotalInfraccionesporArticuloExcel();
	
	
	List<ReporteInfraccionesGralVO> consultaInfraccionesDelegaciones(ReporteInfraccionesGralVO datos);
	List<RptInfraccionesEmpleado> consultaInfraccionesEmpleados(RptInfraccionesEmpleado ParametroBusqueda);
	
	
	
	ByteArrayOutputStream descargaExcelDeteccMarcado();
	
	ByteArrayOutputStream descargaExcelInfraccionesEmpleados();


}
