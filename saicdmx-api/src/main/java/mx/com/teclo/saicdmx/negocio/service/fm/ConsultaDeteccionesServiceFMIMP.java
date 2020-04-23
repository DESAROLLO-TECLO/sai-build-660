package mx.com.teclo.saicdmx.negocio.service.fm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.sun.xml.xsom.impl.scd.Iterators.Map;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm.FMDeteccionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm.FMLotesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FCConsultaDeteccionesSinProcVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FCConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesAgrupacionMes;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesDetalleHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesSPDetalleMesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesSPTipoArchivoFcivicaVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesSPTipoFcivicaVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoArchivoFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoFotocivicaVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.DeteccionesResultadoVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service
public class ConsultaDeteccionesServiceFMIMP implements ConsultaDeteccionesServiceFM {

	@Autowired
	private FMDeteccionesMyBatisDAO consultaDeteccionesMyBatisDAO;
	
	private RutinasTiempoImpl rutina;
	private Map reporte;
	
	@Override
	public List<FCConsultaDeteccionesSinProcVO> consultaDeteccionesSP(
			Integer tipoDeteccion,Integer tipoFecha,List<Integer> multipleTipoArchivo) {
	//public List<FMConsultaDeteccionesVO> consultaDetecciones(int tipoDeteccion, int tipoRadar, int tipoFecha,int origenPlaca) {
		List<FCConsultaDeteccionesSinProcVO> consultaDetecciones = null;
		List<FCConsultaDeteccionesSinProcVO> consultaDeteccionesHistoricas = null;
		List<FCConsultaDeteccionesSinProcVO> responce = new ArrayList<FCConsultaDeteccionesSinProcVO>();
		
		List<FMTipoFotocivicaVO> listaFMTipoFotocivicaVO = consultaDeteccionesMyBatisDAO.buscarTipoDeteccionFc(0);
		List<FMTipoArchivoFCVO> listaFMTipoArchivoFCVO = consultaDeteccionesMyBatisDAO.buscarArchivoFC();
		
		Map<String, String> parametros = getParametros(); 
		String strConsultaDetecciones = parametros.get("CONS_DETECCIONES_SP");
		String paramFechaDetHist = parametros.get("PARAM_FECHA_DET_HIST");
		String consParamFechaDetHist = parametros.get("CONS_PARAM_FECHA_DET_HIST");
		
		String[] arrayParamFechaDetHist = paramFechaDetHist.split("\\|");
		Float segmentos = Float.parseFloat(arrayParamFechaDetHist[0]);
		Float divisorMes = Float.parseFloat(arrayParamFechaDetHist[1]);
		
		Calendar date = Calendar.getInstance();
		String fActual = date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH) + 1)+ "/" + date.get(Calendar.YEAR);
		
		consParamFechaDetHist = StringUtils.replace(consParamFechaDetHist, "#{fActual}", fActual);
		consParamFechaDetHist = StringUtils.replace(consParamFechaDetHist, "#{segmentos}", segmentos.toString());
		consParamFechaDetHist = StringUtils.replace(consParamFechaDetHist, "#{divisorMes}", divisorMes.toString());
		String fechaInicio = consultaDeteccionesMyBatisDAO.consParamFechaDetHist(consParamFechaDetHist);
		
		int TotalActuales = 0;
		int TotalHistoricos = 0;
		int i = 0, x = 0, largo = 0, cabeza = 0;
		int d = 0;
		String totHitorico = "0";
		String strMultipleTipoArchivo = "";
		for (x = 0; x < multipleTipoArchivo.size(); x++) {
			if((x + 1) == multipleTipoArchivo.size()){
				strMultipleTipoArchivo += multipleTipoArchivo.get(x).toString();
			}else{
				strMultipleTipoArchivo += multipleTipoArchivo.get(x).toString() + ",";
			}
		}
		
		strConsultaDetecciones = StringUtils.replace(strConsultaDetecciones, "#{tipoDeteccion}", tipoDeteccion.toString());
		strConsultaDetecciones = StringUtils.replace(strConsultaDetecciones, "${strMultipleTipoArchivo}", strMultipleTipoArchivo);
		
		String strConsultaDeteccionesActuales = strConsultaDetecciones;
		String strConsultaDeteccionesHistoricas = strConsultaDetecciones;
		
		// Actuales -------   >= 'fechaInicio'
		String strFechaConsActuales = " >= TO_DATE('" + fechaInicio + "','DD/MM/YYYY') ";
		strConsultaDeteccionesActuales = StringUtils.replace(strConsultaDeteccionesActuales, "#{fechaInicio}", strFechaConsActuales);
		
		// Historicas -----   < 'fechaInicio'
		String strFechaConsHistoricas = " < TO_DATE('" + fechaInicio + "','DD/MM/YYYY') ";
		strConsultaDeteccionesHistoricas = StringUtils.replace(strConsultaDeteccionesHistoricas, "#{fechaInicio}", strFechaConsHistoricas);
		
		if(tipoFecha==1){
			consultaDetecciones = consultaDeteccionesMyBatisDAO.consultaDetecciones(strConsultaDeteccionesActuales);
			consultaDeteccionesHistoricas = consultaDeteccionesMyBatisDAO.consultaDeteccionesHistoricas(strConsultaDeteccionesHistoricas);
		}else if(tipoFecha==2){
//			consultaDetecciones = consultaDeteccionesMyBatisDAO.consultaDeteccionesFecha2(tipoDeteccion, tipoRadar,origenPlaca, fechaInicio);
//			consultaDeteccionesHistoricas = consultaDeteccionesMyBatisDAO.consultaDeteccionesHistoricasFecha2(tipoDeteccion,tipoRadar, origenPlaca, fechaInicio);
		}
		
		for (x = 0; x < consultaDetecciones.size(); x++) {
			TotalActuales += consultaDetecciones.get(x).getTotal();
		}
		for (x = 0; x < consultaDeteccionesHistoricas.size(); x++) {
			TotalHistoricos += consultaDeteccionesHistoricas.get(x).getTotal();
		}
		if (tipoDeteccion != 99) { //!= 99> 0
			for (i = 0; i < consultaDetecciones.size(); i++) {
				if(consultaDetecciones.get(i).getTotal() == 0 && consultaDeteccionesHistoricas.get(i).getTotal() == 0){
					
				}else{
					FCConsultaDeteccionesSinProcVO filter = new FCConsultaDeteccionesSinProcVO();
					if(largo==0){
						//filter.setTIPO(consultaDetecciones.get(i).getTIPO());
						filter.setNbTipoFotoCivica(consultaDetecciones.get(i).getNbTipoFotoCivica());
						largo=1;
					}else{
						filter.setNbTipoFotoCivica(null);
					}
					
					filter.setNbTipoArchivoFCivica(consultaDetecciones.get(i).getNbTipoArchivoFCivica());
					filter.setTotal(consultaDetecciones.get(i).getTotal());
					filter.setHistorico(consultaDeteccionesHistoricas.get(i).getTotal());
					filter.setTotalActuales(0);
					filter.setTotalHistoricos(0);
					filter.setIdTipoArchivo(consultaDetecciones.get(i).getIdTipoArchivo());
					filter.setIdTipoFotocivica(consultaDetecciones.get(i).getIdTipoFotocivica());
					responce.add(d, filter);
					d+=1;
				}
			}
			if(TotalActuales!= 0 || TotalHistoricos!=0){
				FCConsultaDeteccionesSinProcVO filterEspecifico = new FCConsultaDeteccionesSinProcVO();
				filterEspecifico.setNbTipoFotoCivica(null);
			    filterEspecifico.setNbTipoArchivoFCivica(null);
			    filterEspecifico.setTotal(null);
			    filterEspecifico.setHistorico(null);
			    filterEspecifico.setTotalActuales(TotalActuales);
			    filterEspecifico.setTotalHistoricos(TotalHistoricos);
			    filterEspecifico.setIdTipoFotocivica(99);
			    responce.add(responce.size(), filterEspecifico);
			}
		}else{
			d=0;
			for (FMTipoFotocivicaVO fmTipoFotocivicaVO : listaFMTipoFotocivicaVO) {
				for(i=0;i<consultaDetecciones.size();i++){
					if(consultaDetecciones.get(i).getTotal() == 0 && consultaDeteccionesHistoricas.get(i).getTotal() == 0){
						largo=1;
					}else{
						if(fmTipoFotocivicaVO.getNbTipoFotocivica().equals(consultaDetecciones.get(i).getNbTipoFotoCivica()) && cabeza == 0){ 
							FCConsultaDeteccionesSinProcVO filter = new FCConsultaDeteccionesSinProcVO();
						    filter.setNbTipoFotoCivica(consultaDetecciones.get(i).getNbTipoFotoCivica());
							filter.setNbTipoArchivoFCivica(consultaDetecciones.get(i).getNbTipoArchivoFCivica());
							filter.setTotal(consultaDetecciones.get(i).getTotal());
							filter.setHistorico(consultaDeteccionesHistoricas.get(i).getTotal());
							filter.setTotalActuales(0);
							filter.setTotalHistoricos(0);
							filter.setIdTipoFotocivica(consultaDetecciones.get(i).getIdTipoFotocivica());
							filter.setIdTipoArchivo(consultaDetecciones.get(i).getIdTipoArchivo());
							responce.add(d,filter);
							
							cabeza=1;
							d+=1;
						}else if(fmTipoFotocivicaVO.getNbTipoFotocivica().equals(consultaDetecciones.get(i).getNbTipoFotoCivica()) && cabeza == 1){
							FCConsultaDeteccionesSinProcVO filtersnDeteccion = new FCConsultaDeteccionesSinProcVO();
							 filtersnDeteccion .setNbTipoFotoCivica(null);
							 filtersnDeteccion .setNbTipoArchivoFCivica(consultaDetecciones.get(i).getNbTipoArchivoFCivica());
							 filtersnDeteccion .setTotal(consultaDetecciones.get(i).getTotal());
							 filtersnDeteccion .setHistorico(consultaDeteccionesHistoricas.get(i).getTotal());
							 filtersnDeteccion .setTotalActuales(0);
							 filtersnDeteccion .setTotalHistoricos(0);
							 filtersnDeteccion.setIdTipoFotocivica(consultaDetecciones.get(i).getIdTipoFotocivica());
							 filtersnDeteccion.setIdTipoArchivo(consultaDetecciones.get(i).getIdTipoArchivo());
							 responce.add(d,filtersnDeteccion);
							 //System.out.print("Se creo renglon sin tipodedeteccion");
							 d+=1;
						}else{
							cabeza=0;
							continue;
						}
					}
				}
			}
			if(TotalActuales== 0 && TotalHistoricos==0){
				
			}else{
				FCConsultaDeteccionesSinProcVO filter2 = new FCConsultaDeteccionesSinProcVO();
				filter2.setNbTipoFotoCivica(null);
				filter2.setNbTipoArchivoFCivica(null);
				filter2.setTotal(null);
				filter2.setHistorico(null);
				filter2.setTotalActuales(TotalActuales);
				filter2.setTotalHistoricos(TotalHistoricos);
				filter2.setIdTipoFotocivica(99);
				responce.add(d,filter2);
			}
		}
		return responce;
	}
	
	@Override
	public List<FMConsultaDeteccionesSPVO> consultaDeteccionesSPDetalle(
			Integer idTipoFotocivica, Integer idTipoArchivo, 
			Integer tipoConsulta, Integer tipoFecha,Integer tipoDetConsulta,
			String mesConsulta,String anioConsulta) {
		List<FMConsultaDeteccionesSPDetalleMesVO> consultaDeteccionesSPDetalleMes = null;
		
//		Calendar date = Calendar.getInstance();
//		Calendar tresMeses;
//		tresMeses = (Calendar) date.clone();
//		tresMeses.add(Calendar.MONTH, -2);
//		int mesInicio = tresMeses.get(Calendar.MONTH) + 1;
//		int año = tresMeses.get(Calendar.YEAR);
//		String fechaInicio = "01/" + mesInicio + "/" + año;
		String consultaDeteccionesSPMes = "";
		Map<String, String> parametros = getParametros(); 
		String strConsultaDetecciones = parametros.get("CONS_DETECCIONES_SP");
		String paramFechaDetHist = parametros.get("PARAM_FECHA_DET_HIST");
		String consParamFechaDetHist = parametros.get("CONS_PARAM_FECHA_DET_HIST");
		
		String[] arrayParamFechaDetHist = paramFechaDetHist.split("\\|");
		Float segmentos = Float.parseFloat(arrayParamFechaDetHist[0]);
		Float divisorMes = Float.parseFloat(arrayParamFechaDetHist[1]);
		
		Calendar date = Calendar.getInstance();
		String fActual = date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH) + 1)+ "/" + date.get(Calendar.YEAR);
		
		consParamFechaDetHist = StringUtils.replace(consParamFechaDetHist, "#{fActual}", fActual);
		consParamFechaDetHist = StringUtils.replace(consParamFechaDetHist, "#{segmentos}", segmentos.toString());
		consParamFechaDetHist = StringUtils.replace(consParamFechaDetHist, "#{divisorMes}", divisorMes.toString());
		String fechaInicio = consultaDeteccionesMyBatisDAO.consParamFechaDetHist(consParamFechaDetHist);
		String strFechaCons = "";
		
		if(tipoConsulta == 1) {// Actuales
			//Actuales -- >= 'fechaInicio'
			strFechaCons = ">= TO_DATE('" + fechaInicio + "','DD/MM/YYYY') ";
		}else if(tipoConsulta == 2) {// Historicas
			//Historicas -- < 'fechaInicio'
			strFechaCons = "< TO_DATE('" + fechaInicio + "','DD/MM/YYYY') ";
		}
		
		String param = "";
		if (tipoFecha == 1) {
			param = "CONS_DETECCIONES_SP_DET_MES";
		}
		
		String strCampoDia = "";
		String strGroupDia = "";
		String strOrderDia = "";
		String strWhereMesAnio = "";
		
		if(tipoDetConsulta == 0) {
		}else if(tipoDetConsulta == 1) {// Consulta por dia
			strCampoDia = "TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD') AS dia, ";
			strGroupDia = "TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD'), ";
			strOrderDia = "TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD'), ";
			
			strWhereMesAnio = 
					"AND TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM') = '" + mesConsulta + "' "
					+ "AND TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'yyyy') = '" + anioConsulta + "' ";
		}
		
		consultaDeteccionesSPMes = parametros.get(param);
		consultaDeteccionesSPMes = StringUtils.replace(consultaDeteccionesSPMes, "#{idTipoFotocivica}", idTipoFotocivica.toString());
		consultaDeteccionesSPMes = StringUtils.replace(consultaDeteccionesSPMes, "#{idTipoArchivo}", idTipoArchivo.toString());
		consultaDeteccionesSPMes = StringUtils.replace(consultaDeteccionesSPMes, "#{fechaInicio}", strFechaCons);
		consultaDeteccionesSPMes = StringUtils.replace(consultaDeteccionesSPMes, "#{strCampoDia}", strCampoDia);
		consultaDeteccionesSPMes = StringUtils.replace(consultaDeteccionesSPMes, "#{strGroupDia}", strGroupDia);
		consultaDeteccionesSPMes = StringUtils.replace(consultaDeteccionesSPMes, "#{strOrderDia}", strOrderDia);
		consultaDeteccionesSPMes = StringUtils.replace(consultaDeteccionesSPMes, "#{strWhereMesAnio}", strWhereMesAnio);
		
		consultaDeteccionesSPDetalleMes = consultaDeteccionesMyBatisDAO.consultaDeteccionesDetalleActuales(consultaDeteccionesSPMes);
		
		return AgruparColeccion(consultaDeteccionesSPDetalleMes, tipoDetConsulta);
	}
	
	public List<FMConsultaDeteccionesSPVO> AgruparColeccion (List<FMConsultaDeteccionesSPDetalleMesVO> Datos,Integer tipoConsulta){
		List<FMConsultaDeteccionesSPVO> responce = new ArrayList<FMConsultaDeteccionesSPVO>();
		
		String diaAnterior = "", mesAnterior = "", mesLetraAnterior = "", anioAnterior = "", nbTipoDeteccionAnterior = "", nbTipoArchivoAnterior = "";
		Integer idTipoDeteccionAnterior = 0, idTipoArchivoAnterior = 0, totalAnterior = 0;
		String diaActual = "", mesActual = "", mesLetraActual = "", anioActual = "", nbTipoDeteccionActual = "", nbTipoArchivoActual = "";
		Integer idTipoDeteccionActual = 0, idTipoArchivoActual = 0, totalActual = 0;
		Integer largo = 0, caja = 0, sumaTotal = 0;

		Integer sumaTotalAnio = 0;
		Integer sumaTotalMes = 0;
		Integer sumaTotalDia = 0;
		Integer sumaTotalTipoFC = 0;
		Integer sumaTotalTipoAArchivo = 0;

		List<FMConsultaDeteccionesSPVO> listaFMConsultaDetSPVO = new ArrayList<FMConsultaDeteccionesSPVO>();
		List<FMConsultaDeteccionesSPTipoFcivicaVO> listaFMConsultaDetSPTFCVO = new ArrayList<FMConsultaDeteccionesSPTipoFcivicaVO>();
		List<FMConsultaDeteccionesSPTipoArchivoFcivicaVO> listaFMConsultaDetSPTArchivoFCVO = new ArrayList<FMConsultaDeteccionesSPTipoArchivoFcivicaVO>();
		FMConsultaDeteccionesSPVO fMConsultaDetSPVO = new FMConsultaDeteccionesSPVO();
		FMConsultaDeteccionesSPTipoFcivicaVO fMConsultaDetSPTFCVO = new FMConsultaDeteccionesSPTipoFcivicaVO();
		FMConsultaDeteccionesSPTipoArchivoFcivicaVO fMConsultaDetSPTArchivoFCVO = new FMConsultaDeteccionesSPTipoArchivoFcivicaVO();
		
		for(FMConsultaDeteccionesSPDetalleMesVO fMConsultaDeteccionesSPDetalleMesVO : Datos) {
			diaActual = fMConsultaDeteccionesSPDetalleMesVO.getDia();
			mesActual = fMConsultaDeteccionesSPDetalleMesVO.getMes();
			mesLetraActual = fMConsultaDeteccionesSPDetalleMesVO.getMesLetra();
			anioActual = fMConsultaDeteccionesSPDetalleMesVO.getAnio();
			idTipoDeteccionActual = fMConsultaDeteccionesSPDetalleMesVO.getIdTipoFotocivica();
			nbTipoDeteccionActual = fMConsultaDeteccionesSPDetalleMesVO.getNbTipoFotocivica();
			idTipoArchivoActual = fMConsultaDeteccionesSPDetalleMesVO.getIdTipoArchivoFCivica();
			nbTipoArchivoActual = fMConsultaDeteccionesSPDetalleMesVO.getNbTipoArchivoFCivica();
			totalActual = fMConsultaDeteccionesSPDetalleMesVO.getTotal();
			
			if(idTipoDeteccionAnterior == 0 
					|| idTipoArchivoAnterior == 0 
					|| anioAnterior.equals("") 
					|| mesAnterior.equals("")
					|| (tipoConsulta == 1 && diaAnterior.equals(""))
				){
				idTipoDeteccionAnterior = idTipoDeteccionActual;
				nbTipoDeteccionAnterior = nbTipoDeteccionActual;
				idTipoArchivoAnterior = idTipoArchivoActual;
				nbTipoArchivoAnterior = nbTipoArchivoActual;
				diaAnterior = diaActual;
				mesAnterior = mesActual;
				mesLetraAnterior = mesLetraActual;
				anioAnterior = anioActual;
			}else if( idTipoDeteccionAnterior != idTipoDeteccionActual 
					|| idTipoArchivoAnterior != idTipoArchivoActual
					|| anioAnterior.equals(anioActual) == false 
					|| mesAnterior.equals(mesActual) == false
					|| (tipoConsulta == 1 && diaAnterior.equals(diaActual) == false)
				){
				
				if(anioAnterior.equals(anioActual) == false 
						|| mesAnterior.equals(mesActual) == false 
						|| (tipoConsulta == 1 && diaAnterior.equals(diaActual) == false)){
					guardaTipoArchivoFC(
							listaFMConsultaDetSPTArchivoFCVO,fMConsultaDetSPTArchivoFCVO,
							idTipoArchivoAnterior,nbTipoArchivoAnterior,sumaTotalTipoAArchivo);
					guardaTipoFC(
							listaFMConsultaDetSPTFCVO,fMConsultaDetSPTFCVO,
							listaFMConsultaDetSPTArchivoFCVO,idTipoDeteccionAnterior,
							nbTipoDeteccionAnterior, sumaTotalTipoFC);
					guardaAnioMesDia(
							listaFMConsultaDetSPVO,fMConsultaDetSPVO,
							listaFMConsultaDetSPTFCVO,anioAnterior,mesAnterior,
							mesLetraAnterior,diaAnterior,sumaTotalMes);
					
					listaFMConsultaDetSPTFCVO = new ArrayList<FMConsultaDeteccionesSPTipoFcivicaVO>();
					listaFMConsultaDetSPTArchivoFCVO = new ArrayList<FMConsultaDeteccionesSPTipoArchivoFcivicaVO>();
					sumaTotalTipoAArchivo = 0;
					sumaTotalTipoFC = 0;
					
					if(anioAnterior.equals(anioActual) == false){
						sumaTotalAnio = 0;
						sumaTotalMes = 0;
						sumaTotalDia = 0;
					}else if(mesAnterior.equals(mesActual) == false){
						sumaTotalMes = 0;
						sumaTotalDia = 0;
					}else if(tipoConsulta == 1 && diaAnterior.equals(diaActual) == false) {
						sumaTotalDia = 0;
					}
				}else if(idTipoDeteccionAnterior != idTipoDeteccionActual){
					guardaTipoArchivoFC(
							listaFMConsultaDetSPTArchivoFCVO,fMConsultaDetSPTArchivoFCVO,
							idTipoArchivoAnterior,nbTipoArchivoAnterior,sumaTotalTipoAArchivo);
					guardaTipoFC(listaFMConsultaDetSPTFCVO,fMConsultaDetSPTFCVO,
							listaFMConsultaDetSPTArchivoFCVO,idTipoDeteccionAnterior,
							nbTipoDeteccionAnterior, sumaTotalTipoFC);
					listaFMConsultaDetSPTArchivoFCVO = new ArrayList<FMConsultaDeteccionesSPTipoArchivoFcivicaVO>();
					sumaTotalTipoAArchivo = 0;
					sumaTotalTipoFC = 0;
				}else if(idTipoArchivoAnterior != idTipoArchivoActual){
					guardaTipoArchivoFC(listaFMConsultaDetSPTArchivoFCVO,fMConsultaDetSPTArchivoFCVO,idTipoArchivoAnterior,nbTipoArchivoAnterior,sumaTotalTipoAArchivo);
					sumaTotalTipoAArchivo = 0;
				}
				
				sumaTotal = 0;
				idTipoDeteccionAnterior = idTipoDeteccionActual;
				nbTipoDeteccionAnterior = nbTipoDeteccionActual;
				idTipoArchivoAnterior = idTipoArchivoActual;
				nbTipoArchivoAnterior = nbTipoArchivoActual;
				diaAnterior = diaActual;
				mesAnterior = mesActual;
				mesLetraAnterior = mesLetraActual;
				anioAnterior = anioActual;
			}
			
			if(idTipoDeteccionAnterior == idTipoDeteccionActual 
					&& idTipoArchivoAnterior == idTipoArchivoActual 
					&& anioAnterior.equals(anioActual) 
					&& mesAnterior.equals(mesActual)
				){
				sumaTotalTipoAArchivo += totalActual;
				sumaTotalTipoFC += totalActual;
				sumaTotalAnio += totalActual;
				sumaTotalMes += totalActual;
				sumaTotalDia += totalActual;
				sumaTotal += totalActual;
			}else{
				System.out.println("entro aqui");
			}
			diaActual = "";
			mesActual = "";
			mesLetraActual = "";
			anioActual = "";
			idTipoDeteccionActual = 0;
			nbTipoDeteccionActual = "";
			idTipoArchivoActual = 0;
			nbTipoArchivoActual = "";
		}
		
		guardaTipoArchivoFC(
				listaFMConsultaDetSPTArchivoFCVO,fMConsultaDetSPTArchivoFCVO,
				idTipoArchivoAnterior,nbTipoArchivoAnterior,sumaTotalTipoAArchivo);
		guardaTipoFC(
				listaFMConsultaDetSPTFCVO,fMConsultaDetSPTFCVO,
				listaFMConsultaDetSPTArchivoFCVO,idTipoDeteccionAnterior,
				nbTipoDeteccionAnterior, sumaTotalTipoFC);
		guardaAnioMesDia(
				listaFMConsultaDetSPVO,fMConsultaDetSPVO,
				listaFMConsultaDetSPTFCVO,anioAnterior,mesAnterior,
				mesLetraAnterior,diaAnterior,sumaTotalMes);

		responce = listaFMConsultaDetSPVO;
		return responce;
	}
	
	public void guardaTipoArchivoFC(
			List<FMConsultaDeteccionesSPTipoArchivoFcivicaVO> listaFMConsultaDetSPTArchivoFCVO,
			FMConsultaDeteccionesSPTipoArchivoFcivicaVO fMConsultaDetSPTArchivoFCVO,
			Integer idTipoArchivoAnterior,String nbTipoArchivoAnterior,
			Integer sumaTotalTipoAArchivo
		){
		fMConsultaDetSPTArchivoFCVO = new FMConsultaDeteccionesSPTipoArchivoFcivicaVO();
		fMConsultaDetSPTArchivoFCVO.setIdTipoArchivoFCivica(idTipoArchivoAnterior);
		fMConsultaDetSPTArchivoFCVO.setNbTipoArchivoFCivica(nbTipoArchivoAnterior);
		fMConsultaDetSPTArchivoFCVO.setTotalTipoArchivoFCivica(sumaTotalTipoAArchivo);
		listaFMConsultaDetSPTArchivoFCVO.add(fMConsultaDetSPTArchivoFCVO);
	}
	
	public void guardaTipoFC(
			List<FMConsultaDeteccionesSPTipoFcivicaVO> listaFMConsultaDetSPTFCVO,
			FMConsultaDeteccionesSPTipoFcivicaVO fMConsultaDetSPTFCVO,
			List<FMConsultaDeteccionesSPTipoArchivoFcivicaVO> listaFMConsultaDetSPTArchivoFCVO,
			Integer idTipoDeteccionAnterior, String nbTipoDeteccionAnterior,
			Integer sumaTotalTipoFC
		){
		fMConsultaDetSPTFCVO = new FMConsultaDeteccionesSPTipoFcivicaVO();
		fMConsultaDetSPTFCVO.setIdTipoFotocivica(idTipoDeteccionAnterior);
		fMConsultaDetSPTFCVO.setNbTipoFotocivica(nbTipoDeteccionAnterior);
		fMConsultaDetSPTFCVO.setTotalTipoFotocivica(sumaTotalTipoFC);
		fMConsultaDetSPTFCVO.setListaFMConsultaDetSPTArchivoFCVO(listaFMConsultaDetSPTArchivoFCVO);
		listaFMConsultaDetSPTFCVO.add(fMConsultaDetSPTFCVO);
	}
	
	public void guardaAnioMesDia(
			List<FMConsultaDeteccionesSPVO> listaFMConsultaDetSPVO,
			FMConsultaDeteccionesSPVO fMConsultaDetSPVO,
			List<FMConsultaDeteccionesSPTipoFcivicaVO> listaFMConsultaDetSPTFCVO,
			String anio,String mes,String mesLetra,
			String dia,Integer sumaTotalMes
		){
		fMConsultaDetSPVO = new FMConsultaDeteccionesSPVO();
		fMConsultaDetSPVO.setAnio(anio);
		fMConsultaDetSPVO.setMes(mes);
		fMConsultaDetSPVO.setMesLetra(mesLetra);
		fMConsultaDetSPVO.setDia(dia);
		fMConsultaDetSPVO.setTotalMes(sumaTotalMes);
		fMConsultaDetSPVO.setListaFMConsultaDetSPTFCVO(listaFMConsultaDetSPTFCVO);
		listaFMConsultaDetSPVO.add(fMConsultaDetSPVO);
	}
	
	@Override
	public Map<String, Object> consultaDetecciones(
			Integer tipoDeteccion,List<Integer> multipleTipoArchivo,
			String fechaInicio,String fechaFin,
			Integer tipoBusqueda,String valorBusqueda,
			Integer consultaProcesables) {
		
		Map<String, String> parametros = getParametros(); 
		String countRegConsulta = parametros.get("CONS_CONTEO_DETECCIONES");
		String consultaDetecciones = parametros.get("CONS_DETECCIONES");
		String countRegConsultaNProc = parametros.get("CONS_CONTEO_DETEC_NO_PROCESABLES");
		String consultaDeteccionesNProc = parametros.get("CONS_DETEC_NO_PROCESABLES");
		String tipoBusquedaDet = parametros.get("TIPO_BUSQUEDA_DETECCIONES");
		String numRegConsulta = parametros.get("CANTIDAD_REG_CONS_DETECCIONES");
		String strMensajeRespuesta = parametros.get("MENSAJE_CANT_REG_CONS_DETECCIONES");
		
		String consultaArticulo = parametros.get("CONS_AIF_ARTICULO");
		String consultaFraccion = parametros.get("CONS_AIF_FRACCION");
		String consultaParrafo = parametros.get("CONS_AIF_PARRAFO");
		String consultaInciso = parametros.get("CONS_AIF_INCISO");
		
		List<FCConsultaDeteccionesVO> listaFCConsultaDeteccionesVO = null;
		String strCondicionBusquedaDet = "";
		String condicionBusquedaDet = "";
		Integer filtroFecha = 2;
		String cantlimite = numRegConsulta;
		Boolean stTbusq = false;
		Boolean stFechas = false;
		Integer countConsDeteccionesFC = 0;
		
		if(tipoBusqueda != null && tipoBusqueda != -1 && valorBusqueda != null && valorBusqueda.isEmpty() != true && valorBusqueda.equals("") != true) {
			String[] catBusqueda = tipoBusquedaDet.split("\\|");
			for (Integer j = 0; j < catBusqueda.length; j++) {
				String[] tipoBusquedaSel = catBusqueda[j].split(",");
				if(Integer.valueOf(tipoBusquedaSel[0]) == tipoBusqueda) {
					condicionBusquedaDet = tipoBusquedaSel[3];
					condicionBusquedaDet = " " + StringUtils.replace(condicionBusquedaDet, "#{valorBusqueda}", valorBusqueda);
					strCondicionBusquedaDet = tipoBusquedaSel[2];
					stTbusq = true;
				}
			}
		}
		
		if (fechaInicio != null && fechaInicio != null && 
				fechaInicio.isEmpty() != true && fechaFin.isEmpty() != true &&
				fechaInicio.equals("") != true && fechaFin.equals("") != true) {
			//filtroFecha = " AND TRUNC(TO_DATE(TAI005.CD_FECHAHORA ,'dd/mm/yyyy HH24:MI:ss')) BETWEEN TO_DATE('#{fechaInicio}') AND TO_DATE('#{fechaFin}') ";
			filtroFecha = 1;
			stFechas = true;
		}else {
			filtroFecha = 2;
			fechaInicio = "";
			fechaFin = "";
			stFechas = false;
		}
		
		if(consultaProcesables == 1) {
			String strMultipleTipoArchivo = "";
			for (Integer x = 0; x < multipleTipoArchivo.size(); x++) {
				if((x + 1) == multipleTipoArchivo.size()){
					strMultipleTipoArchivo += multipleTipoArchivo.get(x).toString();
				}else{
					strMultipleTipoArchivo += multipleTipoArchivo.get(x).toString() + ",";
				}
			}
			
			// Filtro Tipo deteccione
			countRegConsulta = StringUtils.replace(countRegConsulta, "#{tipoDeteccion}", tipoDeteccion.toString());
			// Filtro tipo de archivo
			countRegConsulta = StringUtils.replace(countRegConsulta, "#{tipoArchivo}", strMultipleTipoArchivo);
			// Filtro valores busqueda
			countRegConsulta = StringUtils.replace(countRegConsulta, "#{condicionBusquedaDet}", condicionBusquedaDet);
			// Filtro por rango de fechas
			countRegConsulta = StringUtils.replace(countRegConsulta, "#{filtroFecha}", filtroFecha.toString());
			countRegConsulta = StringUtils.replace(countRegConsulta, "#{fechaInicio}", fechaInicio);
			countRegConsulta = StringUtils.replace(countRegConsulta, "#{fechaFin}", fechaFin);
			
			countConsDeteccionesFC = consultaDeteccionesMyBatisDAO.countConsDeteccionesFC(countRegConsulta);
			//------------------------------------------------------------------------------------------------------------------
			//Cosnutla Articulo 
			consultaDetecciones = StringUtils.replace(consultaDetecciones, "#{consultaArticulo}", consultaArticulo);
			// Consulta Fraccion con numeros romanos
			consultaDetecciones = StringUtils.replace(consultaDetecciones, "#{consultaFraccion}", consultaFraccion);
			// Consulta Parrafo con letra
			consultaDetecciones = StringUtils.replace(consultaDetecciones, "#{consultaParrafo}", consultaParrafo);
			// Consulta Inciso
			consultaDetecciones = StringUtils.replace(consultaDetecciones, "#{consultaInciso}", consultaInciso);
			
			// Filtro Tipo deteccione
			consultaDetecciones = StringUtils.replace(consultaDetecciones, "#{tipoDeteccion}", tipoDeteccion.toString());
			// Filtro tipo de archivo
			consultaDetecciones = StringUtils.replace(consultaDetecciones, "#{tipoArchivo}", strMultipleTipoArchivo);
			// Filtro valores busqueda
			consultaDetecciones = StringUtils.replace(consultaDetecciones, "#{condicionBusquedaDet}", condicionBusquedaDet);
			// Filtro por rango de fechas
			consultaDetecciones = StringUtils.replace(consultaDetecciones, "#{filtroFecha}", filtroFecha.toString());
			consultaDetecciones = StringUtils.replace(consultaDetecciones, "#{fechaInicio}", fechaInicio);
			consultaDetecciones = StringUtils.replace(consultaDetecciones, "#{fechaFin}", fechaFin);
			// Filtro cantidad de registro
			consultaDetecciones = StringUtils.replace(consultaDetecciones, "#{numRegConsulta}", numRegConsulta);
			
			listaFCConsultaDeteccionesVO = consultaDeteccionesMyBatisDAO.consDeteccionesFC(consultaDetecciones);
		}else if(consultaProcesables == 2){
			// Filtro valores busqueda
			countRegConsultaNProc = StringUtils.replace(countRegConsultaNProc, "#{condicionBusquedaDet}", condicionBusquedaDet);
			// Filtro por rango de fechas
			countRegConsultaNProc = StringUtils.replace(countRegConsultaNProc, "#{filtroFecha}", filtroFecha.toString());
			countRegConsultaNProc = StringUtils.replace(countRegConsultaNProc, "#{fechaInicio}", fechaInicio);
			countRegConsultaNProc = StringUtils.replace(countRegConsultaNProc, "#{fechaFin}", fechaFin);
			
			countConsDeteccionesFC = consultaDeteccionesMyBatisDAO.countConsDeteccionesFC(countRegConsultaNProc);
			//------------------------------------------------------------------------------------------------------------------
			//Cosnutla Articulo 
			consultaDeteccionesNProc = StringUtils.replace(consultaDeteccionesNProc, "#{consultaArticulo}", consultaArticulo);
			// Consulta Fraccion con numeros romanos
			consultaDeteccionesNProc = StringUtils.replace(consultaDeteccionesNProc, "#{consultaFraccion}", consultaFraccion);
			// Consulta Parrafo con letra
			consultaDeteccionesNProc = StringUtils.replace(consultaDeteccionesNProc, "#{consultaParrafo}", consultaParrafo);
			// Consulta Inciso
			consultaDeteccionesNProc = StringUtils.replace(consultaDeteccionesNProc, "#{consultaInciso}", consultaInciso);
			
			// Filtro valores busqueda
			consultaDeteccionesNProc = StringUtils.replace(consultaDeteccionesNProc, "#{condicionBusquedaDet}", condicionBusquedaDet);
			// Filtro por rango de fechas
			consultaDeteccionesNProc = StringUtils.replace(consultaDeteccionesNProc, "#{filtroFecha}", filtroFecha.toString());
			consultaDeteccionesNProc = StringUtils.replace(consultaDeteccionesNProc, "#{fechaInicio}", fechaInicio);
			consultaDeteccionesNProc = StringUtils.replace(consultaDeteccionesNProc, "#{fechaFin}", fechaFin);
			// Filtro cantidad de registro
			consultaDeteccionesNProc = StringUtils.replace(consultaDeteccionesNProc, "#{numRegConsulta}", numRegConsulta);
			
			listaFCConsultaDeteccionesVO = consultaDeteccionesMyBatisDAO.consDeteccionesFC(consultaDeteccionesNProc);
		}
		
		reporte = crearReporteDeteccionesPorLote(
				listaFCConsultaDeteccionesVO,tipoDeteccion,multipleTipoArchivo,fechaInicio,fechaFin,
				strCondicionBusquedaDet, valorBusqueda,stTbusq,stFechas,consultaProcesables);
		String strMensajeRespuesta1 = "";
		String strMensajeRespuesta2 = "";
		Boolean mensajeRespuesta = false;
		
		if(countConsDeteccionesFC > Integer.parseInt(cantlimite)) {
			strMensajeRespuesta = StringUtils.replace(strMensajeRespuesta, "#{countConsDeteccionesFC}", countConsDeteccionesFC.toString());
			strMensajeRespuesta = StringUtils.replace(strMensajeRespuesta, "#{numRegConsulta}", cantlimite);
			strMensajeRespuesta1 = strMensajeRespuesta;
			strMensajeRespuesta2 = cantlimite + "/" + countConsDeteccionesFC.toString() ;
			mensajeRespuesta = true;
		}
		Map<String, Object> response = new HashMap<String, Object>();
    	response.put("listaFCConsultaDeteccionesVO", listaFCConsultaDeteccionesVO);
    	response.put("strMensajeRespuesta1", strMensajeRespuesta1);
    	response.put("strMensajeRespuesta2", strMensajeRespuesta2);
    	response.put("mensajeRespuesta", mensajeRespuesta);
		return response;
	}
	
	@Transactional (readOnly =  true)
	public Map<String, String> getParametros(){
		List<Map<String, String>> listaParametros = consultaDeteccionesMyBatisDAO.buscarQuerys();
		Map<String, String> parametros = new HashMap<String, String>();
		for(Map<String, String> registro : listaParametros) {
			parametros.put(registro.get("CD_LLAVE_P_CONFIG"), registro.get("CD_VALOR_P_CONFIG"));
		}
		return parametros;
	}
	
	@Transactional (readOnly =  true)
	public Map<String, String> getParametrosLPPorNBConfig(String nbConfig) {
		List<Map<String, String>> listaParametros  = consultaDeteccionesMyBatisDAO.buscarParametrosPorNbConfig(nbConfig);
		Map<String, String> parametros = new HashMap<String, String>();
		for(Map<String, String> registro : listaParametros) {
			parametros.put(registro.get("CD_LLAVE_P_CONFIG"), registro.get("CD_VALOR_P_CONFIG"));
		}
		return parametros;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map crearReporteDeteccionesPorLote(
			List<FCConsultaDeteccionesVO> listaFCConsultaDeteccionesVO, 
			Integer tipoDeteccion, List<Integer> multipleTipoArchivo, 
			String fechaInicio, String fechaFin,
			String strCondicionBusquedaDet, String valorBusqueda,
			Boolean stTbusq, Boolean stFechas,
			Integer consultaProcesables) {
		
		List<FMTipoArchivoFCVO> listaFMTipoArchivoFCVO = consultaDeteccionesMyBatisDAO.buscarArchivoFC();
		List<FMTipoFotocivicaVO> listaFMTipoFotocivicaVO = consultaDeteccionesMyBatisDAO.buscarTipoDeteccionFc(0);
		
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
		
		titulos.add("PLACA");
//		titulos.add("FECHA");
//		titulos.add("HORA");
		titulos.add("FECHA-HORA DETECCIÓN");
		if(consultaProcesables == 2) titulos.add("FECHA-HORA RECEPCIÓN");
		titulos.add("TDSKUID");
		titulos.add("TIPO FOTOCIVICA");
		titulos.add("TIPO ARCHIVO");
		titulos.add("ARTÍCULO");
		titulos.add("FRACCIÓN");
		titulos.add("PÁRRAFO");
		titulos.add("INCISO");
		titulos.add("ESTATUS");
		if(consultaProcesables == 1) {
			titulos.add("LOTE");
		}else if(consultaProcesables == 2) {
			titulos.add("ARCHIVO ORIGEN");
		}
		titulos.add("MOTIVO DESCARTE");
		titulos.add("SERIE CAPTURA"); 
		
		//titulos.add("FOLIO INFRACCION");
		encabezadoTitulo.add(titulos);
		
		String strTipoDeteccion = "";
		String strNbTipoArchivo = "";
		
		if(tipoDeteccion == 99) {
			strTipoDeteccion = "Todos";
			Integer contador = 0;
			for(FMTipoFotocivicaVO fMTipoFotocivicaVO : listaFMTipoFotocivicaVO){
				if((contador + 1) == listaFMTipoFotocivicaVO.size()){
					strTipoDeteccion += fMTipoFotocivicaVO.getNbTipoFotocivica();
				}else {
					strTipoDeteccion += fMTipoFotocivicaVO.getNbTipoFotocivica() + ", ";
				}
				contador ++;
			}
		}else{
			for(FMTipoFotocivicaVO fMTipoFotocivicaVO : listaFMTipoFotocivicaVO){
				if(tipoDeteccion == fMTipoFotocivicaVO.getIdTipoFotocivica()) {
					strTipoDeteccion = fMTipoFotocivicaVO.getNbTipoFotocivica();
				}
			}
		}
		
		for (Integer x = 0; x < multipleTipoArchivo.size(); x++) {
			for(FMTipoArchivoFCVO fMTipoArchivoFCVO : listaFMTipoArchivoFCVO){
				if(multipleTipoArchivo.get(x) == fMTipoArchivoFCVO.getIdTipoArchivoFCivica()) {
					if((x + 1) == multipleTipoArchivo.size()){
						strNbTipoArchivo += fMTipoArchivoFCVO.getNbTipoArchivoFCivica();
					}else{
						strNbTipoArchivo += fMTipoArchivoFCVO.getNbTipoArchivoFCivica() + ", ";
					}
				}
			}
		}
		String fechaReporte = "";
		String fechaNReporte = "";
		String fechaConsReporte = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fEmisionSP = sdf.format(new Date());
		fechaConsReporte = fEmisionSP;
		if(stFechas) {
			String fInicio = "";
			String fFin = "";
			fechaReporte = fechaInicio + " al " + fechaFin;
			fechaNReporte = fechaInicio.replace("/", "") + fechaFin.replace("/", "");
		}else{
			fechaReporte = fEmisionSP;
			fechaNReporte = fEmisionSP.replace("/", "");
		}
//		propiedadesReporte.setFechaI(fechaInicio);
//		propiedadesReporte.setFechaF(fechaFin);
		subtitulos.add("Fecha de Consulta: " + fechaConsReporte);
		subtitulos.add("Tipo de Detección: " + strTipoDeteccion);
		subtitulos.add("Tipo de Archivo: " + strNbTipoArchivo);
		if(stFechas) {
			subtitulos.add("Reporte del periodo: " + fechaReporte);
		}
		
		if(stTbusq) {
			subtitulos.add("Tipo Búsqueda: " + strCondicionBusquedaDet);
			subtitulos.add("Valor Búsqueda: " + valorBusqueda);
		}
		propiedadesReporte.setNombreReporte("DETECCIONES" + fechaNReporte);
		propiedadesReporte.setTituloExcel("Consulta Detecciónes");
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		List<FCConsultaDeteccionesVO> listresult = new ArrayList<FCConsultaDeteccionesVO>();
		listresult.addAll(listaFCConsultaDeteccionesVO);
		
		if(!listresult.isEmpty()){	
			for(FCConsultaDeteccionesVO deteccionVO : listresult){
				listaContenido1 = new ArrayList<String>();
				
				listaContenido1.add(deteccionVO.getCdPlaca());
//				listaContenido1.add(deteccionVO.getFecha());
//				listaContenido1.add(deteccionVO.getHora());
				listaContenido1.add(deteccionVO.getFechaHoraDet());
				if(consultaProcesables == 2) listaContenido1.add(deteccionVO.getFechaHoraRec()); 
				listaContenido1.add(deteccionVO.getCdTdskuid());
				listaContenido1.add(deteccionVO.getNbTipoFotocivica());
				listaContenido1.add(deteccionVO.getNbTipoArchivoFcivica());
				
				listaContenido1.add(deteccionVO.getArticuloFn());
				listaContenido1.add(deteccionVO.getFraccionFn());
				listaContenido1.add(deteccionVO.getParrafoFn());
				listaContenido1.add(deteccionVO.getIncisoFn());
				
				listaContenido1.add(deteccionVO.getEstatus());
				if(consultaProcesables == 1) {
					listaContenido1.add(deteccionVO.getIdLote().toString());
				}else if(consultaProcesables == 2) {
					listaContenido1.add(deteccionVO.getNbArchivo());
				}
				listaContenido1.add(deteccionVO.getMotivoCancelacion());
				listaContenido1.add(deteccionVO.getSerieCaptura());
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
	public Map getReporteDeteccionesPorLote() {
		return reporte;
	}
}