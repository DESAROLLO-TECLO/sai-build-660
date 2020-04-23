package mx.com.teclo.saicdmx.negocio.service.fm;

import java.util.List;

//import com.sun.xml.xsom.impl.scd.Iterators.Map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.fm.FCConsultaDeteccionesSinProcVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FCConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesAgrupacionMes;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesDetalleHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesVO;

public interface ConsultaDeteccionesServiceFM {
	
	/***
	 * @author Fernando Octavio
	 * @return 
	 * Lista de las detecciones que cumplen criterio de consulta
	 */
	//public List<FMConsultaDeteccionesVO> consultaDetecciones(int tipoDeteccion,int tipoRadar,int tipoFecha,int origenPlaca);
	public List<FCConsultaDeteccionesSinProcVO> consultaDeteccionesSP(Integer tipoDeteccion,Integer tipoFecha,List<Integer> multipleTipoArchivo);
	
	//public List<FMConsultaDeteccionesAgrupacionMes> consultaDeteccionesDetalleHistoricas(int tipoDeteccion,int tipoRadar,int tipoFecha,int origenPlaca);
	
	public List<FMConsultaDeteccionesSPVO> consultaDeteccionesSPDetalle(Integer idTipoFotocivica,Integer idTipoArchivo,Integer tipoConsulta,Integer tipoFecha,Integer tipoDetConsulta,String mesConsulta,String anioConsulta);
	
//    public List<FMConsultaDeteccionesAgrupacionMes> consultaDeteccionesDetalleHistoricasDetalleDia(int tipoDeteccion,
//    		                                             int tipoRadar,int tipoFecha,int origenPlaca,String fechaInicio,String fechaFin);
	
    public Map<String, Object> consultaDetecciones(Integer tipoDeteccion, List<Integer> multipleTipoArchivo, String fechaInicio, String fechaFin,Integer tipoBusqueda,String valorBusqueda,Integer consultaProcesables);
    
    @SuppressWarnings("rawtypes")
	public Map getReporteDeteccionesPorLote();
}
