package mx.com.teclo.saicdmx.negocio.service.fm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaArchivoDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaArchivoOrigenVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service("reporteArchivoDeteccion")
public class ReporteExcelArchivoOrigen {
	
	


	private ByteArrayOutputStream byteArrayOutputStream = null;

	public ByteArrayOutputStream generarReporteDeteccion(List<FMConsultaArchivoDeteccionesVO> listaDetecciones, String nombreReporte) {


		RutinasTiempoImpl cambio = new RutinasTiempoImpl();

		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		propiedadesReporte.setTituloExcel("CONSULTA DETALLADA DE ARCHIVO FOTOCÍVICA");
		propiedadesReporte.setNombreReporte("CONSULTA DETALLADA DE ARCHIVO FOTOCÍVICA");
		propiedadesReporte.setExtencionArchvio(".xls");
		
		List<String> subtitulos = new ArrayList<String>();
		subtitulos.add("Nombre del Archivo: " + nombreReporte);
	

		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
        List<String> titulos = new ArrayList<String>();
		titulos.add("FOLIO");
		titulos.add("CANCELADO");
		//titulos.add("DUPLICADO");
		titulos.add("PROCESADO");
		//titulos.add("CANCELADO");
		titulos.add("LIBERADO");
		titulos.add("PLACA");
		titulos.add("MARCA");
		titulos.add("SUBMARCA");
		titulos.add("MODELO");
		titulos.add("FECHA");
		titulos.add("TIPO DE DETECCIÓN");
		titulos.add("UBICACIÓN UT");
		titulos.add("CALLE DE UT ");
		titulos.add("ENTRE CALLE DE UT ");
		titulos.add("UT CÓDIGO DE SECTOR ");
		titulos.add("UT DELEGACIÓN ");
		titulos.add("UT CODIGO POSTAL ");
		titulos.add("UT COLONIA");
		titulos.add("UT SENTIDO ");
		titulos.add("VELOCIDAD DETECTADA ");
		titulos.add("PUNTOS A DESCONTAR ");
		titulos.add("PLACA OFICIAL LEVANTO ");
		titulos.add("ORIGEN PLACA ");
		titulos.add("VIGENCIA LICENCIA ");
		titulos.add("LICENCIA ");
		titulos.add("VIN ");
		titulos.add("ACEPTADA POR SSP ");
		titulos.add("RADAR NO.SERIE");	
		titulos.add("NOMBRE");
		titulos.add("APELLIDO PATERNO");
		titulos.add("APELLIDO MATERNO");
		titulos.add("COLONIA");
		titulos.add("CP");
		titulos.add("TIPO DE PERSONA");
		titulos.add("OBSERVACIONES ");
		
		
		
		
		
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		encabezadoTitulo.add(titulos);

		List<Object> hojas = new ArrayList<Object>();
		List<Object> hoja1 = new ArrayList<Object>();
		List<String> listaContenido1;

		for ( FMConsultaArchivoDeteccionesVO record : listaDetecciones) {
			listaContenido1 = new ArrayList<String>();

			// long creadoPorL = record.getCreadoPor();
			// String creadoPor = Long.toString(creadoPorL);

			listaContenido1.add(record.getCd_tdskuid());
			listaContenido1.add(record.getSt_cancelado());
			//listaContenido1.add(record.getSt_duplicado());
			listaContenido1.add(record.getSt_procesado());
			//listaContenido1.add(record.getSt_cancelado()) ;
			listaContenido1.add(record.getSt_liberado());
			listaContenido1.add(record.getCd_placa());
			listaContenido1.add(record.getNb_marca());
			listaContenido1.add(record.getNb_submar());
			listaContenido1.add(record.getNb_modelo());
			listaContenido1.add(record.getCd_fechahora());
			listaContenido1.add(record.getId_tipo_deteccion());
			listaContenido1.add(record.getCd_ut());
			listaContenido1.add(record.getNb_ut_calle());
			listaContenido1.add(record.getNb_ut_entrecalle());
			listaContenido1.add(record.getCd_ut_sec_cod());
			listaContenido1.add(record.getId_ut_delegacion());
			listaContenido1.add(record.getCd_ut_codigo());
			listaContenido1.add(record.getNb_ut_colonia());
			listaContenido1.add(record.getTx_ut_sentido());
			listaContenido1.add(record.getNu_velocidaddetectada());
			listaContenido1.add(record.getNu_puntosdescnt());
			listaContenido1.add(record.getCd_oficialplaca());
			listaContenido1.add(record.getId_origenplaca());
			listaContenido1.add(record.getFh_vig_licencia());
			listaContenido1.add(record.getNu_licencia());
			listaContenido1.add(record.getNu_vin());
			listaContenido1.add(record.getSt_aceptada_ssp());
			listaContenido1.add(record.getCd_serie_dispositivo());
			listaContenido1.add(record.getNb_nombre());
			listaContenido1.add(record.getNb_apellidopat());
			listaContenido1.add(record.getNb_apellidomat());
			listaContenido1.add(record.getNb_colonia());
			listaContenido1.add(record.getNu_cp());
			listaContenido1.add(record.getId_tipo_persona());
			listaContenido1.add(record.getTx_observaciones());
			

			//

			hoja1.add(listaContenido1);

		}
		hojas.add(hoja1);
	
		propiedadesReporte.setSubtitulos(subtitulos);
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(hojas);

		try {
			byteArrayOutputStream = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteArrayOutputStream;
	}
	
	
	
	public ByteArrayOutputStream generarReporteArchivoOrigen(List<FMConsultaArchivoOrigenVO> listaArchivos, String nombreReporte,Integer switchRangoFecha,Integer periodoFecha, String fechaInicio,String fechaFin,String estatusProceso,String tipoArchivo,Integer opcion) {


		RutinasTiempoImpl cambio = new RutinasTiempoImpl();
        
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		
		
		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setNombreReporte(nombreReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		 String fInicio="";
		 String fFin="";
		
		
		List<String> subtitulos = new ArrayList<String>();
		if(opcion==2)
		{
		if(switchRangoFecha==0)
		{
		List<String> rangoFechas=generaRangoFechas(periodoFecha);
	    fInicio=rangoFechas.get(0);
	    fFin=rangoFechas.get(1);
		}
		else
		{
		fInicio=fechaInicio;
		fFin=fechaFin;
			
		}
		
	////Recorrer Archivo tipo
				String[] arrArchivoTipo;
				arrArchivoTipo = tipoArchivo.split(",");
				String arcTip = "";
				String busquedaTipoArchivo="";
				int k=0;
				int j=0;
				
				for (int lep2 = 0; lep2 < arrArchivoTipo.length; lep2++) {
					arcTip = arrArchivoTipo[lep2].toString();
					 if(arcTip.equals("1")){
						 busquedaTipoArchivo=busquedaTipoArchivo+" Foráneas";
						
					}else if(arcTip.equals("2")){
						busquedaTipoArchivo=busquedaTipoArchivo+" Físicas";
						
					}else if(arcTip.equals("3")){
						busquedaTipoArchivo=busquedaTipoArchivo+" Morales";
					
					}
					  if((k + 1) != arrArchivoTipo.length){
						  busquedaTipoArchivo=busquedaTipoArchivo + ", ";
				            
				             }
					 k++;
				}
				
				 String[] arrEstatusProceso;
					arrEstatusProceso = estatusProceso.split(",");
					String estPro = "";
					String busquedaEstatusProceso="";
					for (int lep = 0; lep < arrEstatusProceso.length; lep++) {
						estPro = arrEstatusProceso[lep].toString();
						 if(estPro.equals("2")){//2-Complementado
							 busquedaEstatusProceso = busquedaEstatusProceso+" Complementado";
						}else if(estPro.equals("3")){//3-Por complementar
							busquedaEstatusProceso = busquedaEstatusProceso+" Por Complementar";
						}else if(estPro.equals("5")){//1-Cancelado 
							busquedaEstatusProceso = busquedaEstatusProceso+" Cancelado";
						}
						  if((j + 1) != arrEstatusProceso.length){
							  busquedaEstatusProceso=busquedaEstatusProceso + ", ";
					            
					             }
						 j++;
					}
		
		
		subtitulos.add("Fecha de Inicio: " + fInicio);
		subtitulos.add("Fecha de Fin: " + fFin);
		subtitulos.add("Estatus de Proceso: " + busquedaEstatusProceso);
		subtitulos.add("Tipo de Archivo: " + busquedaTipoArchivo);
		}
		else
		{
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			subtitulos.add("Fecha de Fin: " + formato.format(new Date()));
		}
		
		
		
		
		
		

		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();

		List<String> titulos = new ArrayList<String>();
		titulos.add("NOMBRE DE ARCHIVO");
		titulos.add("TIPO DE ARCHIVO");
		titulos.add("PORCENTAJE PROCESADO");
		titulos.add("ESTATUS");
		titulos.add("REPORTADO");
		titulos.add("CARGADO");
		titulos.add("NO PROCESABLE");
		titulos.add("PROCESADO");
		titulos.add("FECHA DE RECEPCIÓN");
		
		
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		encabezadoTitulo.add(titulos);

		List<Object> hojas = new ArrayList<Object>();
		List<Object> hoja1 = new ArrayList<Object>();
		List<String> listaContenido1;

		for ( FMConsultaArchivoOrigenVO record : listaArchivos) {
			listaContenido1 = new ArrayList<String>();
			RutinasTiempoImpl ru=new RutinasTiempoImpl();


			listaContenido1.add(record.getNb_Archivo());
			listaContenido1.add(record.getId_Tipo_Archivo()!=null?record.getId_Tipo_Archivo():"Desconocido");
			listaContenido1.add(Double.toString(record.getPorcentaje()));
			if(record.getSt_Complementado()==1 && !record.getSt_Proceso().contentEquals("Cancelado"))
			{
				record.setSt_Proceso("Complementado");
			}
			listaContenido1.add(record.getSt_Proceso());
			listaContenido1.add(Integer.toString(record.getCantidad_Reportado()));
			listaContenido1.add(Integer.toString(record.getCantidad_Cargado()));
			listaContenido1.add(Integer.toString(record.getCantidad_Inactivo()));
			listaContenido1.add(Integer.toString(record.getCantidad_Procesado())+"/"+(Integer.toString(record.getCantidadProcesable()))) ;
			listaContenido1.add(ru.getFecha_ddMMYYYY_hhmmss(record.getFh_Ini_Carga()));
			
			hoja1.add(listaContenido1);

		}
		hojas.add(hoja1);
		
		propiedadesReporte.setSubtitulos(subtitulos);
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(hojas);

		try {
			byteArrayOutputStream = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteArrayOutputStream;
	}
	
	//Genera Fechas por rango al perido de fecha
	public List<String> generaRangoFechas(Integer periodoFecha){
		List<String> rangoFechas = new ArrayList<String>();
		
		Calendar dateActual = Calendar.getInstance();
		//dateActual = new GregorianCalendar(2018, 6, 8);
		Calendar dateInicio;
		dateInicio = (Calendar) dateActual.clone();
		int diaInicio = 0;
		int mesInicio = 0; 
		int añoInicio = 0;
		String fechaInicio = "";
		Calendar dateFin;
		dateFin = (Calendar) dateActual.clone();
		int diaFin = 0;
		int mesFin = 0; 
		int añoFin = 0;
		String fechaFin = "";
		int diaSemana = 0;
		int inicioSemana = 0;
		int diaMes = 0;
		int diasMes = 0;
		int inicioMes = 0;
		int finMes = 0;
		
		if(periodoFecha == 0) {//"Hoy"
			dateFin = (Calendar) dateInicio.clone();
		}else if(periodoFecha == 1){//"Ayer"
			dateInicio.add(Calendar.DAY_OF_YEAR, -1);
			dateFin = (Calendar) dateInicio.clone();
		}else if(periodoFecha == 2){//"Esta Semana"
			diaSemana = dateInicio.get(Calendar.DAY_OF_WEEK);
			if(diaSemana != 1){
				inicioSemana = ((diaSemana - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioSemana);
			}
			
			dateFin = (Calendar) dateInicio.clone();
			dateFin.add(Calendar.DAY_OF_YEAR, 6);
		}else if(periodoFecha == 3){//"Última Semana"
			diaSemana = dateInicio.get(Calendar.DAY_OF_WEEK);
			if(diaSemana != 1){
				inicioSemana = ((diaSemana - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioSemana);
			}
			dateInicio.add(Calendar.WEEK_OF_YEAR, -1);
			
			dateFin = (Calendar) dateInicio.clone();
			dateFin.add(Calendar.DAY_OF_YEAR, 6);
		}else if(periodoFecha == 4){//"Últimos 7 Días"
			dateInicio.add(Calendar.DAY_OF_YEAR, -7);
			dateFin = (Calendar) dateActual.clone();
		}else if(periodoFecha == 5){//"Este Mes"
			diaMes = dateInicio.get(Calendar.DAY_OF_MONTH);
			if(diaMes != 1){
				inicioMes = ((diaMes - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioMes);
			}
			
			dateFin = (Calendar) dateInicio.clone();
			diasMes = dateFin.getActualMaximum(Calendar.DAY_OF_MONTH);
			finMes = diasMes - 1;
			
			dateFin.add(Calendar.DAY_OF_YEAR, finMes);
		}else if(periodoFecha == 6){//"Último Mes"
			diaMes = dateInicio.get(Calendar.DAY_OF_MONTH);
			if(diaMes != 1){
				inicioMes = ((diaMes - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioMes);
			}
			dateInicio.add(Calendar.MONTH, -1);
			
			dateFin = (Calendar) dateInicio.clone();
			diasMes = dateFin.getActualMaximum(Calendar.DAY_OF_MONTH);
			finMes = diasMes - 1;
			
			dateFin.add(Calendar.DAY_OF_YEAR, finMes);
		}else if(periodoFecha == 7){//"Últimos 30 Días"
			dateInicio.add(Calendar.DAY_OF_YEAR, -30);
			dateFin = (Calendar) dateActual.clone();
		}
		
		diaInicio = dateInicio.get(Calendar.DAY_OF_MONTH);
		mesInicio = dateInicio.get(Calendar.MONTH) + 1;
		añoInicio = dateInicio.get(Calendar.YEAR);
		fechaInicio = diaInicio+ "/" + mesInicio + "/" + añoInicio;
		
		diaFin = dateFin.get(Calendar.DAY_OF_MONTH);
		mesFin = dateFin.get(Calendar.MONTH) + 1;
		añoFin = dateFin.get(Calendar.YEAR);
		fechaFin = diaFin+ "/" + mesFin + "/" + añoFin;
		
		rangoFechas.add(fechaInicio);
		rangoFechas.add(fechaFin);
		return rangoFechas;
	}
	
	
	
	
	

}
