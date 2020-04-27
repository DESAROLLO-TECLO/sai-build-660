package mx.com.teclo.saicdmx.negocio.service.evaluaciones;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.evaluaciones.EvaluacionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.evaluaciones.EvaluacionUsuarioVO;
import mx.com.teclo.saicdmx.persistencia.vo.evaluaciones.EvaluacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.evaluaciones.UsuarioVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteGeneralFVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service
public class EvaluacionesServiceImpl implements EvaluacionesService {

	@Autowired
	private EvaluacionesMyBatisDAO evaluacionesMyBatisDAO;
	
	@Transactional
	@Override
	public List<EvaluacionVO> getEvaluaciones(Integer tipoBusqueda, String valor, String fhIni, String fhFin) {
		List<EvaluacionVO> listEvaluaciones = evaluacionesMyBatisDAO.getEvaluaciones(
				tipoBusqueda, valor, fhIni, fhFin); 
		return listEvaluaciones;
	}

	@Transactional
	@Override
	public EvaluacionUsuarioVO getEvaluacionUsuarios(Integer idEvaluacion) {
		
		EvaluacionUsuarioVO evaluacionUsuarioVO = new EvaluacionUsuarioVO();
		List<UsuarioVO> usuarios = evaluacionesMyBatisDAO.getEvaluacionUsuarios(idEvaluacion);
		EvaluacionVO evaluacion = evaluacionesMyBatisDAO.getEvaluacionPorId(idEvaluacion);
		evaluacionUsuarioVO.setUsuarios(usuarios);
		evaluacionUsuarioVO.setEvaluacion(evaluacion);
		
		return evaluacionUsuarioVO;
	}
	
	@Override
	public ByteArrayOutputStream generaReporteEvaluaciones(List<EvaluacionVO> evaluaciones) {
		
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		

		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
				
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		List<String> subtitulos = new ArrayList<String>(); 
		
		titulos.add("FOLIO");
		titulos.add("NOMBRE");
		titulos.add("FECHA INICIO");
		titulos.add("FECHA FIN");
		titulos.add("EVALUADOS");
		titulos.add("PORCENTAJE APLICACIÓN");
		titulos.add("PROMEDIO GENERAL");
		titulos.add("EFECTIVIDAD");
		
		encabezadoTitulo.add(titulos);

		propiedadesReporte.setTituloExcel("Reporte Evaluaciones");
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(EvaluacionVO e : evaluaciones){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(e.getCdEvaluacion());
			listaContenido1.add(e.getNbEvaluacion());
			listaContenido1.add(e.getFhVigIni());
			listaContenido1.add(e.getFhVigFin());
			listaContenido1.add(e.getNuEvaluados());
			listaContenido1.add(e.getNuPorAplicacion() + "%");
			listaContenido1.add(e.getNuPromedioCal()+"");
			listaContenido1.add(e.getTxEfectividad());
			contenido1.add(listaContenido1);
		}
		
		contenido.add(contenido1);
		
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		try {
			reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
 			e.printStackTrace();
		}
	
 		return reporte;
	}
	
	@Override
	public ByteArrayOutputStream generaReporteEvaluacionUsuario(EvaluacionUsuarioVO evaluacionUsuario) {
		
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		

		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
				
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		List<String> subtitulos = new ArrayList<String>();
		
		subtitulos.add("FOLIO: " + evaluacionUsuario.getEvaluacion().getCdEvaluacion());
		subtitulos.add("EVALUACIÓN " + evaluacionUsuario.getEvaluacion().getNbEvaluacion());
		
		titulos.add("PLACA OFICIAL");
		titulos.add("NOMBRE OFICIAL");
		titulos.add("FECHA APLICACIÓN");
		titulos.add("CALIFICACIÓN");
		titulos.add("RESULTADO");
		titulos.add("INTENTOS");
		
		encabezadoTitulo.add(titulos);

		propiedadesReporte.setTituloExcel("Reporte Usuarios por Evaluación");
		propiedadesReporte.setSubtitulos(subtitulos);
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(UsuarioVO eu : evaluacionUsuario.getUsuarios()){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(eu.getEmpPlaca());
			listaContenido1.add(eu.getEmpNombre() + " " + eu.getEmpApePaterno() + " " + eu.getEmpApeMaterno());
			listaContenido1.add(eu.getFhInicio());
			listaContenido1.add(eu.getNuCalificacion()+"");
			listaContenido1.add(eu.getNbStCalificacion());
			listaContenido1.add(eu.getNuIntentos()+"");
			contenido1.add(listaContenido1);
		}
		
		contenido.add(contenido1);
		
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		try {
			reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
 			e.printStackTrace();
		}
	
 		return reporte;
	}
}
