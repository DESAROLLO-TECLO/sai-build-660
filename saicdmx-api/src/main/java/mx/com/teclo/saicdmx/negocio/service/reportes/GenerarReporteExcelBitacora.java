package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.TcaBitacoraCambiosVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

import org.springframework.stereotype.Service;

@Service("GenerarReporteExcelBitacora")
public class GenerarReporteExcelBitacora {
	private ByteArrayOutputStream byteArrayOutputStream = null;
	private RutinasTiempoImpl rutinasTiempo;
	
	public ByteArrayOutputStream generarReporteExcel(List<TcaBitacoraCambiosVO> listaRegistros,String nombreReporte, String fechaInicio,String fechaFin,
			                   String componente,String concepto){
		
		PeticioReporteVO peticioReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		rutinasTiempo = new  RutinasTiempoImpl();
		
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();
		
		titulos.add("COMPONENTE");
		titulos.add("CONCEPTO");
		titulos.add("VALOR ORIGINAL");
		titulos.add("VALOR FINAL");
		titulos.add("MODIFICADO POR");
		titulos.add("REGISTRO MODIFICADO");
		titulos.add("FECHA DE MODIFICACIÓN");
		
		/* Parametros de busqueda */
		subtitulos.add("Fecha de Consulta: "+ rutinasTiempo.getFechaActualFO());
		subtitulos.add("");
		subtitulos.add("Filtros de Búsqueda");
		subtitulos.add("Componente: "+componente);
		subtitulos.add("Concepto: "+concepto);
		subtitulos.add("Fecha Inicio: "+fechaInicio);
		subtitulos.add("Fecha Fin: "+fechaFin);
		
		/**
		 * Condision para agregar el mensaje en caso de que tenga 5,000 registros la consulta 
		 * @param listaRegistros
		 * @return mensaje personalizado 
		 */
		if(listaRegistros.size() == 5000){
			subtitulos.add("");
			subtitulos.add("NOTA: El reporte contiene más de 5,000 resultados, si requiere de la información completa por favor solicítela a soporte," +
	    	  		   " de lo contrario modifique los parámetros de búsqueda.");
			subtitulos.add("");
		}
				
		
 		propiedadesReporte.setNombreReporte("Reporte " + nombreReporte);
		propiedadesReporte.setExtencionArchvio(".xls");

		
		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setSubtitulos(subtitulos);
		encabezadoTitulo.add(titulos);
		
		//se crean los cuerpo del reporte 
		List<String> listaContenido1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for (TcaBitacoraCambiosVO Detalles : listaRegistros) {
			listaContenido1 = new ArrayList<String>();
			String fechaComoCadena = sdf.format(Detalles.getFechaModificacion());
			
			listaContenido1.add(Detalles.getNombreConponente());
			listaContenido1.add(Detalles.getNombreConcepto());
			listaContenido1.add(Detalles.getValorOriginal());
			listaContenido1.add(Detalles.getValorFinal());
			listaContenido1.add(Detalles.getModificadoPor());
			listaContenido1.add(Detalles.getRegistroAlterado());
			listaContenido1.add(fechaComoCadena);
			contenido1.add(listaContenido1);
		}
		//bloqueohhRegistroVO.getFechaDesbloqueo() != null ? rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy hh:mm:ss", bloqueohhRegistroVO.getFechaDesbloqueo()) : ""
		
		contenido.add(contenido1);
		
		peticioReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticioReporteVO.setEncabezado(encabezadoTitulo);
		peticioReporteVO.setContenido(contenido);
		
		try {
			byteArrayOutputStream =	peticionReporteBOImpl.generaReporteExcel(peticioReporteVO);
		} catch (IOException e) {
 			e.printStackTrace();
		}
	
 		return byteArrayOutputStream;
	}
}