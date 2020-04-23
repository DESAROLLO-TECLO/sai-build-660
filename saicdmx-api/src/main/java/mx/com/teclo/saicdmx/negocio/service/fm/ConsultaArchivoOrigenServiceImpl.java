package mx.com.teclo.saicdmx.negocio.service.fm;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm.FMConsultaArchivoOrigenMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaArchivoDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaArchivoOrigenVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoEstatusProcesoArchivoVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarComboTipoArchivoVO;

@Service
public class ConsultaArchivoOrigenServiceImpl implements ConsultaArchivoOrigenService {

	@Autowired
	private FMConsultaArchivoOrigenMyBatisDAO FMconsultaArchivoOrigenMyBatisDAO;
	
	@Autowired
	private ReporteExcelArchivoOrigen reporteArchivoOrigen;

	private ByteArrayOutputStream reporte;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@SuppressWarnings("null")
	@Override
	public List<FMConsultaArchivoOrigenVO> obtenerConsultaArchivoOrigen(Integer switchRangoFecha,Integer periodoFecha, String fechaInicio,String fechaFin,String estatusProceso,String tipoArchivo) {
		// TODO Auto-generated method stub
		    List<FMConsultaArchivoOrigenVO> listaArchivo=null;
		    List<FMConsultaArchivoOrigenVO> listaArchivo2=null;
		    List<FMConsultaArchivoOrigenVO> listaArchivo3=null;
		    
//		    List<String> rangoFechas=generaRangoFechas(periodoFecha);
//            String fInicio=rangoFechas.get(0);
//            String fFin=rangoFechas.get(1);
		    
		    
		    String[] arrEstatusProceso;
			arrEstatusProceso = estatusProceso.split(",");
			String estPro = "";
			Integer estProCancelado = -1;
			Integer estProComplementado = 0;
			Integer estProPorComplementar = -1;
			Integer estProSinDetecciones = 0;
			for (int lep = 0; lep < arrEstatusProceso.length; lep++) {
				estPro = arrEstatusProceso[lep].toString();
				 if(estPro.equals("2")){//2-Complementado
					estProComplementado = 1;
				}else if(estPro.equals("3")){//3-Por complementar
					estProPorComplementar = 3;
				}else if(estPro.equals("4")){//4-Sin Detecciones
					estProSinDetecciones = 1;//Se pone en uno para consultar sus detecciones
				}else if(estPro.equals("5")){//1-Cancelado 
					estProCancelado =5;
				}
			}

			
			////Recorrer Archivo tipo
			String[] arrArchivoTipo;
			arrArchivoTipo = tipoArchivo.split(",");
			String arcTip = "";
			Integer tipoFisica = 0;
			Integer tipoMoral = 0;
			Integer tipoForanea = 0;
			
			for (int lep2 = 0; lep2 < arrArchivoTipo.length; lep2++) {
				arcTip = arrArchivoTipo[lep2].toString();
				 if(arcTip.equals("1")){
					 tipoForanea=1;
				
				}else if(arcTip.equals("2")){
					tipoFisica=2;
					
				}else if(arcTip.equals("3")){
					tipoMoral= 3;
				}
			}

			Map<String,String> consultaPorcentaje=FMconsultaArchivoOrigenMyBatisDAO.consultaPorcentaje();

		   
	
		    
		    switch (switchRangoFecha) {
			case 0:
                List<String> rangoFechas=generaRangoFechas(periodoFecha);
	            String fInicio=rangoFechas.get(0);
	            String fFin=rangoFechas.get(1);
	            
	            listaArchivo=FMconsultaArchivoOrigenMyBatisDAO.consultaOrigenArchivoEstatus(fInicio, fFin, estProCancelado, estProPorComplementar, tipoFisica,tipoMoral,tipoForanea,consultaPorcentaje.get("CD_VALOR_P_CONFIG"));
	            
	            if(estProComplementado==1){
	            	listaArchivo2=FMconsultaArchivoOrigenMyBatisDAO.consultaOrigenArchivoComplementado(fInicio, fFin, tipoFisica,tipoMoral,tipoForanea,consultaPorcentaje.get("CD_VALOR_P_CONFIG"));
	            	listaArchivo.addAll(listaArchivo2);
	            	
	            }

            	if(estProSinDetecciones!=0){
            		listaArchivo3=FMconsultaArchivoOrigenMyBatisDAO.consultaArchivoSinDetecciones(fInicio, fFin,consultaPorcentaje.get("CD_VALOR_P_CONFIG"));
            	    listaArchivo.addAll(listaArchivo3);
        
            			}
            	
	            
				break;

            case 1:
            	
            	listaArchivo=FMconsultaArchivoOrigenMyBatisDAO.consultaOrigenArchivoEstatus(fechaInicio, fechaFin, estProCancelado, estProPorComplementar, tipoFisica,tipoMoral,tipoForanea,consultaPorcentaje.get("CD_VALOR_P_CONFIG"));
            		
            	  if(estProComplementado==1){
  	            	listaArchivo2=FMconsultaArchivoOrigenMyBatisDAO.consultaOrigenArchivoComplementado(fechaInicio, fechaFin, tipoFisica,tipoMoral,tipoForanea,consultaPorcentaje.get("CD_VALOR_P_CONFIG"));
  	            	listaArchivo.addAll(listaArchivo2);
  	            	
  	            }
            	
            	if(estProSinDetecciones!=0){
            		listaArchivo3=FMconsultaArchivoOrigenMyBatisDAO.consultaArchivoSinDetecciones(fechaInicio, fechaFin,consultaPorcentaje.get("CD_VALOR_P_CONFIG"));
                		listaArchivo.addAll(listaArchivo3);
                	
            			}
            	
            	
            	
            	
				break;
			default:
				break;
			}
		    
		    for(FMConsultaArchivoOrigenVO actual:listaArchivo)
		    {
		    	actual.setCantidadProcesable(actual.getCantidad_Cargado()-actual.getCantidad_Inactivo());
		    }
		    return listaArchivo;
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


	@Override
	public List<FMConsultaArchivoDeteccionesVO> obtenerConsultaArchivoDeteccion(Integer idArchivo) {
		List <FMConsultaArchivoDeteccionesVO> listaDetecciones=null;
		
		listaDetecciones=FMconsultaArchivoOrigenMyBatisDAO.consultaOrigenArchivoDeteccion(idArchivo);
		
		
		
		return listaDetecciones;
	}


	@Override
	public ByteArrayOutputStream generarReportExcelDetecciones(Integer idArchivo,String nombreArchivo) {
	
		
		List <FMConsultaArchivoDeteccionesVO> listaDetecciones=null;
		listaDetecciones=FMconsultaArchivoOrigenMyBatisDAO.consultaOrigenArchivoDeteccion(idArchivo);
		
	
		

		reporte = reporteArchivoOrigen.generarReporteDeteccion(listaDetecciones, nombreArchivo);
		
				return this.reporte;
		
	}

	
	@Override
	public ByteArrayOutputStream generarReportExcelArchivoOrigen(Integer opcion,Boolean busqueda,Integer switchRangoFecha,Integer periodoFecha, String fechaInicio,String fechaFin,String estatusProceso,String tipoArchivo) {
		
		
		List <FMConsultaArchivoOrigenVO> listaArchivoOrigen=null;
		if(busqueda==true&&opcion!=1){

		listaArchivoOrigen=obtenerConsultaArchivoOrigen(switchRangoFecha, periodoFecha, fechaInicio, fechaFin, estatusProceso, tipoArchivo);
		}else{
			
			Map<String,String> consultaPorcentaje=FMconsultaArchivoOrigenMyBatisDAO.consultaPorcentaje();
			listaArchivoOrigen=FMconsultaArchivoOrigenMyBatisDAO.consultaOrigenArchivoTodo(consultaPorcentaje.get("CD_VALOR_P_CONFIG"));
		    for(FMConsultaArchivoOrigenVO actual:listaArchivoOrigen)
		    {
		    	actual.setCantidadProcesable(actual.getCantidad_Cargado()-actual.getCantidad_Inactivo());
		    }
	}
		
		reporte = reporteArchivoOrigen.generarReporteArchivoOrigen(listaArchivoOrigen,"CONSULTA DE ARCHIVOS FOTOCÍVICAS",switchRangoFecha, periodoFecha, fechaInicio, fechaFin, estatusProceso, tipoArchivo,opcion);
//		
	return this.reporte;
		
		
	}

	
	
	
	

	@Override
	public Integer updateCancelar(Integer idArchivo) {

		Integer conprobarProceso=FMconsultaArchivoOrigenMyBatisDAO.validarArchivoProceso(idArchivo);
		if (conprobarProceso==0) {
		String usuario  = usuarioFirmadoService.getUsuarioFirmadoVO().getId().toString();
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//String fechaModificacion=sdf.format(new Date());
		FMconsultaArchivoOrigenMyBatisDAO.actualizaArchivoCancela(idArchivo,usuario);
		FMconsultaArchivoOrigenMyBatisDAO.actualizaDeteccionCancela(idArchivo,usuario);
		FMconsultaArchivoOrigenMyBatisDAO.actualizaDeteccionCancela05(idArchivo,usuario);
		return conprobarProceso;
			
		}
		
	  return conprobarProceso;
		   
	}


	@Override
	public List<FMConsultaArchivoOrigenVO> obtenerConsultaPorId(Integer idArchivo) {
		
		 List<FMConsultaArchivoOrigenVO>  listaArchivoId=FMconsultaArchivoOrigenMyBatisDAO.consultaArchivoId(idArchivo);
		return listaArchivoId;
	}


	@Override
	public List<FMConsultaArchivoOrigenVO> consultaArchivoOrigenTodo() {
		
		Map<String,String> consultaPorcentaje=FMconsultaArchivoOrigenMyBatisDAO.consultaPorcentaje();
		List<FMConsultaArchivoOrigenVO> listaArchivoTodo=null;
		
		listaArchivoTodo=FMconsultaArchivoOrigenMyBatisDAO.consultaOrigenArchivoTodo(consultaPorcentaje.get("CD_VALOR_P_CONFIG"));
		
	    for(FMConsultaArchivoOrigenVO actual:listaArchivoTodo)
	    {
	    	actual.setCantidadProcesable(actual.getCantidad_Cargado()-actual.getCantidad_Inactivo());
	    }
		return listaArchivoTodo;
	}


@Override
	public List<FMTipoEstatusProcesoArchivoVO> comboEstatusProceso() {
 
	List<FMTipoEstatusProcesoArchivoVO> listaComboEstatusProceso=FMconsultaArchivoOrigenMyBatisDAO.consultaCatalogoEstatusArchivo();
	
	return listaComboEstatusProceso;
}


@Override
	public List<FMTipoEstatusProcesoArchivoVO> comboTipoArchivoOrigen() {
	
	List <FMTipoEstatusProcesoArchivoVO>listaComboTipoArchivo =FMconsultaArchivoOrigenMyBatisDAO.consultaCatalogoArchivoTipo();
		
	return listaComboTipoArchivo;
	}
	
	
	
	


}
