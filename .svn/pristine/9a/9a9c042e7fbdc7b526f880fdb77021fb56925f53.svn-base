package mx.com.teclo.saicdmx.negocio.service.fm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm.FMDeteccionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm.FMEstadisticasMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMEstadisticasDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTiposDeteccionesVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service
public class FMEstadisticasDeteccionesServiceImpl implements FMEstadisticasDeteccionesService{
	
	@Autowired
	private FMEstadisticasMyBatisDAO estadisticasMyBatisDAO;
	@Autowired
	private FMDeteccionesMyBatisDAO deteccionesMyBatisDAO;  
	
	
	private RutinasTiempoImpl rutina;
	private Map reporteTipo;
	private Map reporteDelegacion;
	
	@Override
	public Map<String, Object> estadisticasDetecciones(Integer switchRangoFecha, Integer periodoFecha, 
			String fechaInicio, String fechaFin,  Integer tipoDeteccion,  Integer tipoRadar, 
			Integer origenPlaca, String estatusproceso, String nombreRadar, String nombreDeteccion,
			String opcion){
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		List<FMEstadisticasDeteccionesVO> estadisticasDeteccionesVo = null;
		List<FMEstadisticasDeteccionesVO> responceEstadisticasDeteccionesVo = new ArrayList<FMEstadisticasDeteccionesVO>();
		List<FMEstadisticasDeteccionesVO> responceEstadisticasDeteccionesControlVo = new ArrayList<FMEstadisticasDeteccionesVO>();
		List<FMEstadisticasDeteccionesVO> responceEstadisticasDeteccionesGraficaVo = new ArrayList<FMEstadisticasDeteccionesVO>();
		List<FMEstadisticasDeteccionesVO> responceEstadisticasDeteccionesTotalesVo = new ArrayList<FMEstadisticasDeteccionesVO>();
		if(switchRangoFecha == 0){ 
			List<String> rangoFechas = generaRangoFechas(periodoFecha);
			fechaInicio = rangoFechas.get(0);
			fechaFin = rangoFechas.get(1);
		}
		List<FMTiposDeteccionesVO> listaRadares = estadisticasMyBatisDAO.buscarTipoDeteccion();
		
		String[] arrEstatusProceso;
		arrEstatusProceso = estatusproceso.split(",");
		String estPro = "";
		Integer estProCancelado = 0;
		Integer estProComplementado = 0;
		Integer estProLiberado = 0;
		Integer estProSinProcesar = 0;
		for (int lep = 0; lep < arrEstatusProceso.length; lep++) {
			estPro = arrEstatusProceso[lep].toString();
			if(estPro.equals("1")){//1-Cancelado 
				estProCancelado = 1;
			}else if(estPro.equals("2")){//2-Complementado
				estProComplementado = 1;
			}else if(estPro.equals("3")){//3-Liberado
				estProLiberado = 1;
			}else if(estPro.equals("4")){//4-Sin Procesar
				estProSinProcesar = 1;
			}
		}
		
		String radar = "";
		int TotalDetecciones = 0;
		int TotalFora = 0;
		int TotalCDMX = 0;
		int i = 0, r = 0, x = 0, largo = 0, cabeza = 0, d = 0;
		RutinasTiempoImpl fEmisionRut1 = new RutinasTiempoImpl();
		Date fechaInicio2 = fEmisionRut1.convertirStringDate(fechaInicio,"dd/MM/yyyy");
		RutinasTiempoImpl fEmisionRut2 = new RutinasTiempoImpl();
		Date fechaFin2 = fEmisionRut2.convertirStringDate(fechaFin,"dd/MM/yyyy");
		String Radares[][] = new String[listaRadares.size()][4];
		String colores[][] = {
			{"#C4000F","#DE0011","#EB0012","#FF0019"},
			{"#0066C4","#0084DE","#008BEB","#00A7EB"},
			{"#26BE00","#2BD300","#34DD00","#38F016"}, 
			{"#C76900","#E37A00","#F78400","#FF8F0A"}
		};
		//for (int lr = 0; lr < listaRadares.size(); lr++) {
		for (int lr = (listaRadares.size() - 1); lr >= 0; lr--) {
			radar = listaRadares.get(lr).getNbDispositivoDeteccion();
			Radares[lr][0] = radar;
			Radares[lr][1] = colores[lr][0]; 
			Radares[lr][2] = colores[lr][1]; 
			Radares[lr][3] = colores[lr][3];
		}
		
		switch (opcion) {
			case "tipoDeteccion" :
				estadisticasDeteccionesVo = estadisticasMyBatisDAO.estadisticarByTipoDeteccion(fechaInicio2,fechaFin2,tipoDeteccion,tipoRadar,origenPlaca,estProCancelado,estProComplementado,estProLiberado,estProSinProcesar);
				for (x = 0; x < estadisticasDeteccionesVo.size(); x++) {
					TotalDetecciones += estadisticasDeteccionesVo.get(x).getTotal();
					TotalFora += estadisticasDeteccionesVo.get(x).getFORA();
					TotalCDMX += estadisticasDeteccionesVo.get(x).getCDMX();
				}
				
				for (r = 0; r < Radares.length; r++){
					String TipoDeteccion = Radares[r][0];
					String colorTotal = Radares[r][1];
					String colorCdmx = Radares[r][2];
					String colorFora = Radares[r][3];
					Integer totalPorTipo = 0, iDTipoDet = 0;
					
					for(i = 0; i < estadisticasDeteccionesVo.size(); i++){
//						if(estadisticasDeteccionesVo.get(i).getTotal().equals("0")){
//							largo=1;
//						}else{
							//if(TipoDeteccion.equals(estadisticasDeteccionesVo.get(i).getTipoDeteccion()) && cabeza == 0){
						if(TipoDeteccion.equals(estadisticasDeteccionesVo.get(i).getTipoDeteccion())){	
							FMEstadisticasDeteccionesVO filter = new FMEstadisticasDeteccionesVO();
						    
							filter.setIdTipoDeteccion(estadisticasDeteccionesVo.get(i).getIdTipoDeteccion());
							filter.setIdTipoFora(estadisticasDeteccionesVo.get(i).getIdTipoFora());
							filter.setIdMarca(estadisticasDeteccionesVo.get(i).getIdMarca());
							filter.setMarca(estadisticasDeteccionesVo.get(i).getMarca());
							filter.setTipoDeteccion(estadisticasDeteccionesVo.get(i).getTipoDeteccion());
							filter.setOrigenPlaca(estadisticasDeteccionesVo.get(i).getOrigenPlaca());
							filter.setTotal(estadisticasDeteccionesVo.get(i).getTotal());
							filter.setFORA(estadisticasDeteccionesVo.get(i).getFORA());
							filter.setCDMX(estadisticasDeteccionesVo.get(i).getCDMX());
							responceEstadisticasDeteccionesVo.add(d,filter);
							
							FMEstadisticasDeteccionesVO filterG = new FMEstadisticasDeteccionesVO();
							filterG.setIdTipoDeteccion(estadisticasDeteccionesVo.get(i).getIdTipoDeteccion());
							filterG.setIdTipoFora(estadisticasDeteccionesVo.get(i).getIdTipoFora());
							filterG.setIdMarca(estadisticasDeteccionesVo.get(i).getIdMarca());
							filterG.setMarca(estadisticasDeteccionesVo.get(i).getMarca());
							filterG.setTipoDeteccion(estadisticasDeteccionesVo.get(i).getTipoDeteccion() + " - " + estadisticasDeteccionesVo.get(i).getMarca());
							filterG.setOrigenPlaca(estadisticasDeteccionesVo.get(i).getOrigenPlaca());
							filterG.setTotal(estadisticasDeteccionesVo.get(i).getTotal());
							filterG.setFORA(estadisticasDeteccionesVo.get(i).getFORA());
							filterG.setCDMX(estadisticasDeteccionesVo.get(i).getCDMX());
							filterG.setColorTotal(colorTotal);
							filterG.setColorCdmx(colorCdmx);
							filterG.setColorFora(colorFora);
							responceEstadisticasDeteccionesGraficaVo.add(d,filterG);
								
//								cabeza = 1;
//								d += 1;
//							}else if(TipoDeteccion.equals(estadisticasDeteccionesVo.get(i).getTipoDeteccion()) && cabeza == 1){
//								FMEstadisticasDeteccionesVO filtersnDeteccion = new FMEstadisticasDeteccionesVO();
//								filtersnDeteccion.setIdTipoDeteccion(estadisticasDeteccionesVo.get(i).getIdTipoDeteccion());
//								filtersnDeteccion.setIdTipoFora(estadisticasDeteccionesVo.get(i).getIdTipoFora());
//								filtersnDeteccion.setIdMarca(estadisticasDeteccionesVo.get(i).getIdMarca());
//								filtersnDeteccion.setMarca(estadisticasDeteccionesVo.get(i).getMarca());
//								filtersnDeteccion.setTipoDeteccion(null);
//								filtersnDeteccion.setOrigenPlaca(estadisticasDeteccionesVo.get(i).getOrigenPlaca());
//								filtersnDeteccion.setTotal(estadisticasDeteccionesVo.get(i).getTotal());
//								filtersnDeteccion.setFORA(estadisticasDeteccionesVo.get(i).getFORA());
//								filtersnDeteccion.setCDMX(estadisticasDeteccionesVo.get(i).getCDMX());
//								responceEstadisticasDeteccionesVo.add(d,filtersnDeteccion);
////								System.out.print("Se creo renglon sin tipodedeteccion");
//								
//								FMEstadisticasDeteccionesVO filtersnDeteccionG = new FMEstadisticasDeteccionesVO();
//								filtersnDeteccionG.setIdTipoDeteccion(estadisticasDeteccionesVo.get(i).getIdTipoDeteccion());
//								filtersnDeteccionG.setIdTipoFora(estadisticasDeteccionesVo.get(i).getIdTipoFora());
//								filtersnDeteccionG.setIdMarca(estadisticasDeteccionesVo.get(i).getIdMarca());
//								filtersnDeteccionG.setMarca(estadisticasDeteccionesVo.get(i).getMarca());
//								filtersnDeteccionG.setTipoDeteccion(null);
//								filtersnDeteccionG.setOrigenPlaca(estadisticasDeteccionesVo.get(i).getOrigenPlaca());
//								filtersnDeteccionG.setTotal(estadisticasDeteccionesVo.get(i).getTotal());
//								filtersnDeteccionG.setFORA(estadisticasDeteccionesVo.get(i).getFORA());
//								filtersnDeteccionG.setCDMX(estadisticasDeteccionesVo.get(i).getCDMX());
//								filtersnDeteccionG.setTipoDeteccion(estadisticasDeteccionesVo.get(i).getTipoDeteccion());
//								filtersnDeteccionG.setColorTotal(colorTotal);
//								filtersnDeteccionG.setColorCdmx(colorCdmx);
//								filtersnDeteccionG.setColorFora(colorFora);
//								responceEstadisticasDeteccionesGraficaVo.add(d,filtersnDeteccionG);
//								
//								d += 1;
//							}else{
//								cabeza = 0;
//								continue;
//							}
							
							if(TipoDeteccion.equals(estadisticasDeteccionesVo.get(i).getTipoDeteccion())){
								totalPorTipo += estadisticasDeteccionesVo.get(i).getTotal();
								iDTipoDet = estadisticasDeteccionesVo.get(i).getIdTipoFora();
							}
						}
						
						//iDTipoDet = estadisticasDeteccionesVo.get(i).getIdTipoDeteccion();
					}
					
					FMEstadisticasDeteccionesVO control = new FMEstadisticasDeteccionesVO();
					control.setIdTipoFora(iDTipoDet);
					control.setTipoDeteccion(TipoDeteccion);
					control.setTotal(totalPorTipo);
					control.setColorTotal(colorTotal);
					control.setDuracion(1500);
					control.setEffect("swing");
					control.setIcono("fa-bullseye");
					responceEstadisticasDeteccionesControlVo.add(control);
				}
				
				if(TotalDetecciones == 0 && TotalFora == 0 && TotalCDMX == 0){
					
				}else{
					FMEstadisticasDeteccionesVO filter2 = new FMEstadisticasDeteccionesVO();
					filter2.setTotal(TotalDetecciones);
					filter2.setFORA(TotalFora);
					filter2.setCDMX(TotalCDMX);
					responceEstadisticasDeteccionesTotalesVo.add(0,filter2);
				}
				Collections.reverse(responceEstadisticasDeteccionesVo);
				Collections.reverse(responceEstadisticasDeteccionesGraficaVo);
				//Collections.reverse(responceEstadisticasDeteccionesControlVo);
				Collections.reverse(responceEstadisticasDeteccionesTotalesVo);
				
				reporteTipo = this.crearReporteDetecciones(estadisticasDeteccionesVo, switchRangoFecha, 
						periodoFecha, fechaInicio, fechaFin, tipoDeteccion, tipoRadar, origenPlaca,
						estProCancelado,estProComplementado,estProLiberado,estProSinProcesar,
						nombreRadar, nombreDeteccion, opcion);
				break;
			case "porMes":
				//int instrucciones = 0;
				break;
			case "porDelegacion":
				estadisticasDeteccionesVo = estadisticasMyBatisDAO.estadisticarByTipoDelegacion(fechaInicio2,fechaFin2,tipoDeteccion,tipoRadar,origenPlaca,estProCancelado,estProComplementado,estProLiberado,estProSinProcesar);
				for (x = 0; x < estadisticasDeteccionesVo.size(); x++) {
					TotalDetecciones += estadisticasDeteccionesVo.get(x).getTotal();
					TotalFora += estadisticasDeteccionesVo.get(x).getFORA();
					TotalCDMX += estadisticasDeteccionesVo.get(x).getCDMX();
					
					estadisticasDeteccionesVo.get(x).setColorCdmx("#DE0011");
					estadisticasDeteccionesVo.get(x).setColorFora("#C2C2C2");
				}
				responceEstadisticasDeteccionesVo = estadisticasDeteccionesVo;
				
				if(TotalDetecciones == 0 && TotalFora == 0 && TotalCDMX == 0){
					
				}else{
					FMEstadisticasDeteccionesVO filter2 = new FMEstadisticasDeteccionesVO();
					filter2.setTotal(TotalDetecciones);
					filter2.setFORA(TotalFora);
					filter2.setCDMX(TotalCDMX);
					responceEstadisticasDeteccionesTotalesVo.add(0,filter2);
				}
				
				reporteDelegacion = this.crearReporteDetecciones(estadisticasDeteccionesVo, switchRangoFecha, 
						periodoFecha, fechaInicio, fechaFin, tipoDeteccion, tipoRadar, origenPlaca, 
						estProCancelado, estProComplementado, estProLiberado, estProSinProcesar, 
						nombreRadar, nombreDeteccion, opcion);
				break;
			default:
				//sentencias;
				break;
		}
		respuesta.put("respuestaTabla", responceEstadisticasDeteccionesVo);
		respuesta.put("respuestaGrafica", responceEstadisticasDeteccionesGraficaVo);
		respuesta.put("respuestaControl", responceEstadisticasDeteccionesControlVo);
		respuesta.put("respuestaTotales", responceEstadisticasDeteccionesTotalesVo);
		return respuesta;
	}
	
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map crearReporteDetecciones(List<FMEstadisticasDeteccionesVO> estadisticasDeteccionesVo, 
			Integer switchRangoFecha, Integer periodoFecha,	String fechaInicio, String fechaFin, 
			Integer tipoDeteccion,  Integer tipoRadar, Integer origenPlaca, 
			Integer estProCancelado, Integer estProComplementado, Integer estProLiberado, Integer estProSinProcesar, 
			String nombreRadar, String nombreDeteccion, String opcion) {
		Map response = new HashMap();
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		rutina = new RutinasTiempoImpl();
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();
		String nbReporte = "";
		
		switch (opcion) {
			case "tipoDeteccion" :	
				//titulos.add("Folio Infraccion");
				titulos.add("ID TIPO");
				titulos.add("TIPO DETECCIÓN");
				titulos.add("RADAR");
				if(origenPlaca == 2){
					titulos.add("CDMX");
					titulos.add("FORÁNEAS");
				}else if(origenPlaca == 0){

					titulos.add("CDMX");
				}else if(origenPlaca == 1){

					titulos.add("FORÁNEAS");
				}
				titulos.add("TOTAL");
				
				nbReporte = "Detecciones por Tipo - ";
				break;
			case "porDelegacion":
				//titulos.add("Folio Infraccion");
				titulos.add("ID DELEGACION");
				titulos.add("DELEGACION");
				if(origenPlaca == 2){
					titulos.add("CDMX");
					titulos.add("FORÁNEAS");
				}else if(origenPlaca == 0){

					titulos.add("CDMX");
				}else if(origenPlaca == 1){

					titulos.add("FORÁNEAS");
				}
				titulos.add("TOTAL");
				
				nbReporte = "Detecciones por Delegacion - ";
				break;
		}
		
		//titulos.add("Oficial Nombre");
		encabezadoTitulo.add(titulos);
		
		String origenPlacaString = "Todos";
		if(origenPlaca == 0){origenPlacaString = "CDMX";}
		else if(origenPlaca==1){origenPlacaString = "Foránea";}
		
		String estatusprocesoString = "";
		Integer flagComa = 0;
		if(estProCancelado == 1){estatusprocesoString = "Cancelado";}
		if(estProComplementado == 1 && flagComa == 1){estatusprocesoString = ", Complementado";}
		else if(estProComplementado == 1 && flagComa == 0){estatusprocesoString = "Complementado";}
		if(estProLiberado == 1 && flagComa == 1){estatusprocesoString = ", Liberado";}
		else if(estProLiberado == 1 && flagComa == 0){estatusprocesoString = "Liberado";} 
		if(estProSinProcesar == 1 && flagComa == 1){estatusprocesoString = ", Sin Procesar";}
		else if(estProSinProcesar == 1 && flagComa == 0){estatusprocesoString = "Sin Procesar";}
		
		subtitulos.add("Tipo de Deteccion: " + nombreDeteccion);
		subtitulos.add("Tipo de Radar: " + nombreRadar);
		subtitulos.add("Origen de Placa: " + origenPlacaString);
		subtitulos.add("Estatus de Proceso: " + estatusprocesoString);
		
		propiedadesReporte.setFechaI(fechaInicio);
		propiedadesReporte.setFechaF(fechaFin);
		String fechaReporte = fechaInicio + "-" + fechaFin;
		String fechaNReporte = fechaInicio.replace("/", "") + fechaFin.replace("/", "");
		
		propiedadesReporte.setNombreReporte(nbReporte + fechaNReporte);
		propiedadesReporte.setTituloExcel(nbReporte + fechaReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		List<FMDeteccionesVO> listresult = null;
		Date fechaI = rutina.convertirStringDate(fechaInicio, "dd/MM/yyyy");
		Date fechaF = rutina.convertirStringDate(fechaFin, "dd/MM/yyyy");
		
		if(!estadisticasDeteccionesVo.isEmpty()){
			for(FMEstadisticasDeteccionesVO deteccionVO : estadisticasDeteccionesVo){
				listaContenido1 = new ArrayList<String>();				
				switch (opcion) {
					case "tipoDeteccion" :	
						listaContenido1.add(deteccionVO.getIdTipoDeteccion().toString());
						listaContenido1.add(deteccionVO.getTipoDeteccion());
						listaContenido1.add(deteccionVO.getMarca());
						if(origenPlaca == 2){
							listaContenido1.add(deteccionVO.getCDMX().toString());
							listaContenido1.add(deteccionVO.getFORA().toString());
						}else if(origenPlaca == 0){
							listaContenido1.add(deteccionVO.getCDMX().toString());
						}else if(origenPlaca == 1){
							listaContenido1.add(deteccionVO.getFORA().toString());
						}
						listaContenido1.add(deteccionVO.getTotal().toString());
						break;
					case "porDelegacion":
						listaContenido1.add(deteccionVO.getIdDelegacion().toString());
						listaContenido1.add(deteccionVO.getDelegacion());
						if(origenPlaca == 2){
							listaContenido1.add(deteccionVO.getCDMX().toString());
							listaContenido1.add(deteccionVO.getFORA().toString());
						}else if(origenPlaca == 0){
							listaContenido1.add(deteccionVO.getCDMX().toString());
						}else if(origenPlaca == 1){
							listaContenido1.add(deteccionVO.getFORA().toString());
						}
						listaContenido1.add(deteccionVO.getTotal().toString());
						break;
				}
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
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map getReporteDetecciones(String opcion) {
		Map reporte = null;
		switch (opcion) {
			case "tipoDeteccion" :	
				reporte = reporteTipo;
				break;
			case "porDelegacion":
				reporte = reporteDelegacion;
				break;
		}
		return reporte;
	}
}
