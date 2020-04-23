package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultadetecciones.FotomultaDeteccionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ReporteDeteccionesVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service
public class ConsultaDeteccionesServiceImpl implements ConsultaDeteccionesService{

	@Autowired
	private FotomultaDeteccionesMyBatisDAO consultaDeteccionesDAO; 
	private RutinasTiempoImpl rutinastiempo;
	private Map reportMap;
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<ConsultaDeteccionesVO> buscaFotomultasByTipoFechas(Integer tipoFecha, String fechaInicio, String fechaFin,
			Long autorizado, Integer procesado, Integer radarTipo, Integer valido, String nombreRadar, Integer origenPlaca) {
		
		rutinastiempo = new RutinasTiempoImpl();
		Date fInicio = rutinastiempo.convertirStringDate(fechaInicio);
		Date fFin = rutinastiempo.convertirStringDate(fechaFin);
		
		List<ConsultaDeteccionesVO> listDetecciones = null;
		try {
			if(tipoFecha == 1){
				listDetecciones = consultaDeteccionesDAO.buscaFotomultasByFechaInfraccion(fInicio, fFin, autorizado, procesado, radarTipo, valido, origenPlaca);
			}else if(tipoFecha == 2){
				listDetecciones = consultaDeteccionesDAO.buscaFotomultasByFechaValidacion(fInicio, fFin, autorizado, procesado, radarTipo, valido, origenPlaca);
			}
			
		} catch (Exception e) {
			listDetecciones = null;
		}
		
		
		reportMap = generarReporteDetecciones(fechaInicio, fechaFin, autorizado, procesado, radarTipo, tipoFecha, nombreRadar, origenPlaca);
		return listDetecciones;
	}

	/***
	 * @author Jesus Gutierrez
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map generarReporteDetecciones(String fechaInicio, String fechaFin, Long autorizado, Integer procesado,
			Integer radarTipo, Integer tipoFechaBusqueda, String nombreRadar, Integer origenPlaca) {
		Map response = new HashMap();
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		Integer valido = 1;
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		List<String> subtitulos = new ArrayList<String>(); 
				
		titulos.add("Placa");
		titulos.add("Tipo de Placa");
		titulos.add("Fecha");
		titulos.add("Hora");
		titulos.add("TDSKUID");
		titulos.add("UT");
		titulos.add("Velocidad Detectada");
		titulos.add("Nombre");
		titulos.add("Apellido Paterno");
		titulos.add("Apellido Materno");
		titulos.add("Calle");
		titulos.add("Num. Exterior");
		titulos.add("Num. Interior");
		titulos.add("Colonia");
		titulos.add("Municipio");
		titulos.add("Codigo Postal");
		titulos.add("Entidad Federativa");
		titulos.add("Marca");
		titulos.add("Submarca");
		titulos.add("Modelo");
		titulos.add("Telefono");
		titulos.add("Serie");
		titulos.add("Motor");
		titulos.add("Oficial Placa");
		titulos.add("Oficial Nombre");
		encabezadoTitulo.add(titulos);
		
		String origenPlacaString="";
		
		if(origenPlaca==2){origenPlacaString="Foraneás y CDMX";}
			else{
				if(origenPlaca==1){origenPlacaString="Foraneás";}
				 else{origenPlacaString="CDMX";}
			}
		
		subtitulos.add("Tipo de Radar: " + nombreRadar);
		subtitulos.add("Tipo de Placa: " + origenPlacaString);
		
		String fechaReporte = "";
		propiedadesReporte.setFechaI(fechaInicio);
		propiedadesReporte.setFechaF(fechaFin);
		fechaReporte = fechaInicio + "-" + fechaFin;
		
		propiedadesReporte.setNombreReporte("ReporteDetecciones-"+fechaReporte);
		propiedadesReporte.setTituloExcel("DETECCIONES " + fechaReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		List<ReporteDeteccionesVO> listresult = null;
		Date fechaI = rutinastiempo.convertirStringDate(fechaInicio, "dd/MM/yy");
		Date fechaF = rutinastiempo.convertirStringDate(fechaFin, "dd/MM/yy");
		
		if(tipoFechaBusqueda == 1){
			listresult = consultaDeteccionesDAO.buscaFotomultasDetecionesRangoFechasByFechaInfraccion(fechaI, fechaF, valido, procesado, radarTipo, autorizado, origenPlaca);
		}else if(tipoFechaBusqueda == 2){
			listresult = consultaDeteccionesDAO.buscaFotomultasDetecionesRangoFechasByFechaValidacion(fechaI, fechaF, valido, procesado, radarTipo, autorizado, origenPlaca);
		}
		
		if(listresult.size() > 0){
				
			for(ReporteDeteccionesVO deteccionVO : listresult){
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(deteccionVO.getPlaca());
				listaContenido1.add(deteccionVO.getOrigenPlaca().equals("1") ? "Foránea" : "CDMX");
				listaContenido1.add(deteccionVO.getFecha());
	            listaContenido1.add(deteccionVO.getHora());
	            listaContenido1.add(deteccionVO.getTdskuid());
	            listaContenido1.add(deteccionVO.getUt());
	            listaContenido1.add(deteccionVO.getVelocidadDetectada());
	            listaContenido1.add(deteccionVO.getNombre());
	            listaContenido1.add(deteccionVO.getApellidoPaterno());
	            listaContenido1.add(deteccionVO.getApellidoMaterno());
	            listaContenido1.add(deteccionVO.getCalle());
	            listaContenido1.add(deteccionVO.getNumExterior());
	            listaContenido1.add(deteccionVO.getNumInterior());
	            listaContenido1.add(deteccionVO.getColonia());
	            listaContenido1.add(deteccionVO.getMunicipio());
	            listaContenido1.add(deteccionVO.getCodigoPostal());
	            listaContenido1.add(deteccionVO.getEntidadFederativa());
	            listaContenido1.add(deteccionVO.getMarca());
	            listaContenido1.add(deteccionVO.getSubmarca());
	            listaContenido1.add(deteccionVO.getModelo());
	            listaContenido1.add(deteccionVO.getTelefono());
	            listaContenido1.add(deteccionVO.getSerie());
	            listaContenido1.add(deteccionVO.getMotor());
	            listaContenido1.add(deteccionVO.getOficialPlaca());
	            listaContenido1.add(deteccionVO.getOficialNombre());
				
				contenido1.add(listaContenido1);
			}
					
			contenido.add(contenido1);
			
			propiedadesReporte.setSubtitulos(subtitulos);
			peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
			peticionReporteVO.setEncabezado(encabezadoTitulo);
			peticionReporteVO.setContenido(contenido);
			
			try {
				reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
				response.put("reporte", reporte);
				response.put("nombre", propiedadesReporte.getNombreReporte());
			} catch (IOException e) {
	 			e.printStackTrace();
			}
		}
		
		return response;
	}

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map getReporteDetecciones() {
		return this.reportMap;
	}

}
