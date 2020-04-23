package mx.com.teclo.saicdmx.negocio.service.fm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.bloqueohh.BloqueohhRegistroVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDetalleDeteccionesVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;


@Service("fmreporteDetalleDetecciones")
public class FMReporteDetalleDetecciones {
	
	private ByteArrayOutputStream byteArrayOutputStream = null;
	private RutinasTiempoImpl rutinasTiempo;

	public ByteArrayOutputStream generarReporteExcel(List<FMConsultaDetalleDeteccionesVO> listaRegistros,String nombreReporte, String fechaInicio,  String fechaFin ){
		
		PeticioReporteVO peticioReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		rutinasTiempo = new  RutinasTiempoImpl();
		
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();

		titulos.add("PLACA VEHICULAR");
		titulos.add("TDSKUID");
		titulos.add("FECHA");
		titulos.add("HORA");
		titulos.add("NOMBRE USUARIO");
		titulos.add("USUARIO");
		titulos.add("FECHA VALIDACIÓN");
		titulos.add("FECHA CREACIÓN");
		titulos.add("TIPO RADAR");
		titulos.add("ORIGEN PLACA");
		

 		propiedadesReporte.setNombreReporte("Reporte Detecciones");
		propiedadesReporte.setExtencionArchvio(".xls");
 		propiedadesReporte.setFechaI(fechaInicio);
		propiedadesReporte.setFechaF(fechaFin);

		
		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setSubtitulos(subtitulos);
		encabezadoTitulo.add(titulos);
		
		//se crean los cuerpo del reporte 
		List<String> listaContenido1;
		
		
		for (FMConsultaDetalleDeteccionesVO Detalles : listaRegistros) {
			
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(Detalles.getPlaca());
			listaContenido1.add(Detalles.getTdskuid());
			listaContenido1.add(Detalles.getFecha());
			listaContenido1.add(Detalles.getHora());
			listaContenido1.add(Detalles.getNombre());
			listaContenido1.add(Detalles.getUsuario());
			listaContenido1.add(Detalles.getFechavalidacion());
			listaContenido1.add(Detalles.getFechacreacion());
			listaContenido1.add(Detalles.getRadar());
			listaContenido1.add(Detalles.getOrigenPlaca());
			
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
