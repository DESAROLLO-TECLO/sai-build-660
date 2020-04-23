package mx.com.teclo.saicdmx.negocio.service.fm;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaArchivoDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaArchivoOrigenVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoEstatusProcesoArchivoVO;



public interface ConsultaArchivoOrigenService {
	public abstract  List<FMConsultaArchivoOrigenVO>obtenerConsultaArchivoOrigen(Integer switchRangoFecha,Integer periodoFecha, String fechaInicio,String fechaFin,String estatusProceso,String tipoArchivo);
	
	public abstract  List<FMConsultaArchivoOrigenVO>consultaArchivoOrigenTodo();

	
	
	public abstract  List<FMConsultaArchivoDeteccionesVO> obtenerConsultaArchivoDeteccion(Integer idArchivo);
	
	public abstract ByteArrayOutputStream generarReportExcelArchivoOrigen(Integer opcion,Boolean busqueda, Integer switchRangoFecha,Integer periodoFecha, String fechaInicio,String fechaFin,String estatusProceso,String tipoArchivo);
	
	public abstract ByteArrayOutputStream generarReportExcelDetecciones(Integer idArchivo,String nombreArchivo);
	
	public abstract Integer updateCancelar (Integer idArchivo);
	 
	public  List<FMConsultaArchivoOrigenVO>obtenerConsultaPorId(Integer idArchivo );

	public  List<FMTipoEstatusProcesoArchivoVO> comboEstatusProceso();

	public  List<FMTipoEstatusProcesoArchivoVO> comboTipoArchivoOrigen();
 
}
