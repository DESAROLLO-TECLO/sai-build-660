package mx.com.teclo.saicdmx.pdf.garantia;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteRecibirFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteRecibirMasivaFVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;




public class GarantiasRecibe {
	private static final Logger logger = Logger
			.getLogger(GarantiasRecibe.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ByteArrayOutputStream generaReporteGarantiasRecibePDF(Long garantiaId, String nombreEmpleado, File rutaLinuxArchivo, File rutaLinuxImagen, VSSPGarantiaReporteRecibirFVO listaGarantiasReporteVO) throws FileNotFoundException{
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		
		//File img = new File(rutaLinuxImagen);
        InputStream imagen = new FileInputStream(rutaLinuxImagen);
        InputStream rutaArchivo = new FileInputStream(rutaLinuxArchivo);
	    Map listaFecha = rutinasTiempoImpl.obtenerFechaDesglosada(new Date());
	    Locale locale = new Locale("es", "MX");
		
	    Map parametros = new HashMap();
	    //parametros.put("fecha", "M�xico, CDMX., a "+ listaFecha.get("day") + " de "+ listaFecha.get("month") + " de " + listaFecha.get("year") + ".");
	    parametros.put("imagen", imagen);
	    
	    //logger.error("IMAGEN : "+imagen);
	    
	    parametros.put("garantiaId",garantiaId.toString());
	    //logger.error("GARANTIA ID : "+garantiaId.toString());
	    
	    parametros.put("nombreEmpleadoLogeado", nombreEmpleado);
	    //logger.error("EMPLEADO : "+nombreEmpleado);
	    
	    parametros.put("REPORT_LOCALE", locale);
	    //logger.error("LOCAL : "+locale);
	    
	    parametros.put("nombre", listaGarantiasReporteVO.getNOMBRE());
	    //logger.error("NOMBRE : "+listaGarantiasReporteVO.getNOMBRE());
	    
	    parametros.put("documentoFolio", listaGarantiasReporteVO.getDOCUMENTO_FOLIO());
	    //logger.error("documentoFolio : "+listaGarantiasReporteVO.getDOCUMENTO_FOLIO());
	    
	    parametros.put("infracNum", listaGarantiasReporteVO.getINFRAC_NUM());
	    //logger.error("infracNum : "+listaGarantiasReporteVO.getINFRAC_NUM());
	    
	    parametros.put("infracFecha", listaGarantiasReporteVO.getINFRAC_FECHA());
	    //logger.error("infracFecha : "+listaGarantiasReporteVO.getINFRAC_FECHA());
	    
	    parametros.put("nombreOficial", listaGarantiasReporteVO.getNOMBRE_OFICIAL());
	    //logger.error("nombreOficial : "+listaGarantiasReporteVO.getNOMBRE_OFICIAL());
	    
	    parametros.put("fechaCreacion", listaGarantiasReporteVO.getFECHA_CREACION());
	    //logger.error("fechaCreacion : "+listaGarantiasReporteVO.getFECHA_CREACION());
	    
	    parametros.put("fechaEntrega", listaGarantiasReporteVO.getFECHA_ENTREGA());
	    //logger.error("fechaEntrega : "+listaGarantiasReporteVO.getFECHA_ENTREGA());
	    
	    try {
	    	reporte.write(JasperRunManager.runReportToPdf(rutaArchivo, parametros,new JREmptyDataSource()));
		} catch (Exception e) {
			logger.error("Entro al excepction al crear el PDF: "+e.getMessage());
		}
	    return reporte;
	}
	/***
	 * 
	 * @param garantiaId
	 * @param nombreEmpleado
	 * @param rutaLinuxArchivo
	 * @param rutaLinuxImagen
	 * @param listaGarantiasReporteVO
	 * @param bandJasper
	 * @param garantiasReportVO
	 * @param cantidadesId
	 * @return
	 * @throws FileNotFoundException
	 * @author VAPD1226
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public ByteArrayOutputStream generaReporteGarantiasRecibePDFMasiva(Long idGarantiaInd, String nombreEmpleado, File rutaLinuxArchivo, File rutaLinuxImagen, List<VSSPGarantiaReporteRecibirMasivaFVO> listaGarantiasReporteVO, Integer bandJasper,VSSPGarantiaReporteRecibirFVO garantiasReportVO, Integer cantidadesId) throws FileNotFoundException{                                                                                                                                                                                     
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		
		List<VSSPGarantiaReporteRecibirMasivaFVO> first = new ArrayList();
		List<VSSPGarantiaReporteRecibirMasivaFVO> second = new ArrayList();
		
		List<VSSPGarantiaReporteRecibirMasivaFVO> cloneFirst = new ArrayList();
		List<VSSPGarantiaReporteRecibirMasivaFVO> cloneSecond = new ArrayList();
		
//		File img = new File(rutaLinuxImagen);
        InputStream imagen = new FileInputStream(rutaLinuxImagen);
        InputStream rutaArchivo = new FileInputStream(rutaLinuxArchivo);
	    Map listaFecha = rutinasTiempoImpl.obtenerFechaDesglosada(new Date());
	    Locale locale = new Locale("es", "MX");
	    
	    Map parametros = new HashMap();
	    //parametros.put("fecha", "M�xico, CDMX., a "+ listaFecha.get("day") + " de "+ listaFecha.get("month") + "de " + listaFecha.get("year") + ".");
	    parametros.put("imagen", imagen);
	    
	    ArrayList<String> infracNum = new ArrayList<>();
	    String nombreOficial="", infracFecha="",fechEntrega="";
	    
//	    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
//	    DecimalFormat formateador = new DecimalFormat("###,###,###.##", simbolo); 
	   
	    
	    if(bandJasper==0){   
	    	
	    	parametros.put("garantiaId", idGarantiaInd.toString());
		    //logger.error("GARANTIA ID : " + idGarantiaInd.toString());
		    
		    parametros.put("nombreEmpleadoLogeado", nombreEmpleado);
		    //logger.error("EMPLEADO : "+nombreEmpleado);
		    
		    parametros.put("REPORT_LOCALE", locale);
		    //logger.error("LOCAL : "+locale);
				
			parametros.put("nombre", garantiasReportVO.getNOMBRE());
		    //logger.error("NOMBRE : "+garantiasReportVO.getNOMBRE());
	    
		    parametros.put("documentoFolio", garantiasReportVO.getDOCUMENTO_FOLIO());
		    //logger.error("documentoFolio : "+garantiasReportVO.getDOCUMENTO_FOLIO());
		    
		    parametros.put("infracNum", garantiasReportVO.getINFRAC_NUM());
		    //logger.error("infracNum : "+garantiasReportVO.getINFRAC_NUM());
		    
		    parametros.put("infracFecha", garantiasReportVO.getINFRAC_FECHA());
		    //logger.error("infracFecha : "+garantiasReportVO.getINFRAC_FECHA());
		    
		    parametros.put("nombreOficial", garantiasReportVO.getNOMBRE_OFICIAL());
		    //logger.error("nombreOficial : "+garantiasReportVO.getNOMBRE_OFICIAL());
		    
		    parametros.put("fechaCreacion", garantiasReportVO.getFECHA_CREACION());
		    //logger.error("fechaCreacion : "+garantiasReportVO.getFECHA_CREACION());
		    
		    parametros.put("fechaEntrega", garantiasReportVO.getFECHA_ENTREGA());
		    //logger.error("fechaEntrega : "+garantiasReportVO.getFECHA_ENTREGA());
	    }
	    else if(bandJasper==1){
	    	
	    	JRBeanCollectionDataSource listaGarantiaMenor = new JRBeanCollectionDataSource(listaGarantiasReporteVO);
	    	JRBeanCollectionDataSource listaGarantiaMenorC = new JRBeanCollectionDataSource(listaGarantiasReporteVO);
	    	
	    	for(VSSPGarantiaReporteRecibirMasivaFVO registro : listaGarantiasReporteVO){
			    	infracNum.add(registro.getINFRAC_NUM());
			    	nombreOficial=registro.getNOMBRE_OFICIAL();
			    	fechEntrega=registro.getFECHA_ENTREGA();
			    	infracFecha= registro.getINFRAC_FECHA();
			    	
	    	 }
	    	 parametros.put("cantidad", cantidadesId.toString());
	    	 //logger.error("cantidad : "+cantidadesId.toString());
	    	 
	    	 parametros.put("nombreEmpleadoLogeado", nombreEmpleado);
			 //logger.error("EMPLEADO : "+nombreEmpleado); 		    
			   
			 parametros.put("ListGarantiasM", listaGarantiaMenor);
			 //logger.error("ListGarantiasM : " + listaGarantiaMenor);
			    
			 parametros.put("ListGMV", listaGarantiaMenorC);
			 //logger.error("ListGMV : " + listaGarantiaMenorC);
			    
			 parametros.put("nombreOficial", nombreOficial);
			 //logger.error("nombreOficial : "+nombreOficial);
			    
			 parametros.put("fechaEntrega", fechEntrega);
			 //logger.error("fechaEntrega : "+fechEntrega);
			    
			 parametros.put("infracFecha", infracFecha);
			 //logger.error("infracFecha : " + infracFecha);
	    }
	  
	    else if(bandJasper==2){
	    	
	    	int size = listaGarantiasReporteVO.size();

		    for(int i=0; i<size; i++){
		    	
		    	if(i<(size+1)/2){
		    		first.add(listaGarantiasReporteVO.get(i));
		    	}else{
		    		second.add(listaGarantiasReporteVO.get(i));
		    	}
		    }
	   
		    cloneFirst =(List<VSSPGarantiaReporteRecibirMasivaFVO>) ((ArrayList<VSSPGarantiaReporteRecibirMasivaFVO>) first).clone();
		    cloneSecond=(List<VSSPGarantiaReporteRecibirMasivaFVO>)((ArrayList<VSSPGarantiaReporteRecibirMasivaFVO>) second).clone();
	       
		    JRBeanCollectionDataSource listFirst = new JRBeanCollectionDataSource(first);
		    JRBeanCollectionDataSource listSecond = new JRBeanCollectionDataSource(second);
		    
		    JRBeanCollectionDataSource cloneListFirst = new JRBeanCollectionDataSource(cloneFirst);
		    JRBeanCollectionDataSource cloneListSecond = new JRBeanCollectionDataSource(cloneSecond);
	    	   
		    for(VSSPGarantiaReporteRecibirMasivaFVO registro : listaGarantiasReporteVO){
		    	infracNum.add(registro.getINFRAC_NUM());
		    	nombreOficial=registro.getNOMBRE_OFICIAL();
		    	fechEntrega=registro.getFECHA_ENTREGA();	
		    }
		    
		    parametros.put("cantidad", cantidadesId.toString());
	    	//logger.error("cantidad : "+cantidadesId.toString());
		    
		    parametros.put("nombreEmpleadoLogeado", nombreEmpleado);
		    //logger.error("EMPLEADO : "+nombreEmpleado);
		  		    
		    parametros.put("ListGarantiasM", listFirst);
		    //logger.error("ListGarantiasM : " + listFirst);
		    
		    parametros.put("ListGMasiva", listSecond);
		    //logger.error("ListGMasiva : " + listSecond);
		    
		    parametros.put("ListGMV", cloneListFirst);
		    //logger.error("ListGMV : " + cloneListFirst);
		    
		    parametros.put("ListGMasiv", cloneListSecond);
		    //logger.error("ListGMasiv : "+ cloneListSecond);
		    
		    parametros.put("nombreOficial", nombreOficial);
		    //logger.error("nombreOficial : "+nombreOficial);
		    
		    parametros.put("fechaEntrega", fechEntrega);
		    //logger.error("fechaEntrega : "+fechEntrega);
	    }
	    else if(bandJasper==3){
	    	
	    	int size = listaGarantiasReporteVO.size();
	    	int countSwitch=0;
	    	boolean isFirst=true;
	    	int countSwitchTabla=1;
	    	

	    	int maxFilasTabla=55;
	    	int numTablasMin=2;
	    	

	    	for (int i = 0; i < size; i++) {
	    		if (isFirst) {
	    		first.add(listaGarantiasReporteVO.get(i));

	    		} else {
	    		second.add(listaGarantiasReporteVO.get(i));

	    		}
	    		countSwitch++;

	    		if (countSwitch == maxFilasTabla) {
	    		isFirst = !isFirst;
	    		countSwitch = 0;
	    		if(countSwitchTabla>=numTablasMin){
	    			
	    		maxFilasTabla=63;
	    		}
	    		countSwitchTabla++;
	    		}
	    		}
		    
	       
		    JRBeanCollectionDataSource listFirst = new JRBeanCollectionDataSource(first);
		    JRBeanCollectionDataSource listSecond = new JRBeanCollectionDataSource(second);
   
	    	
	    	for(VSSPGarantiaReporteRecibirMasivaFVO registroAll : listaGarantiasReporteVO){
		    	infracNum.add(registroAll.getINFRAC_NUM());
		    	nombreOficial=registroAll.getNOMBRE_OFICIAL();
		    	fechEntrega=registroAll.getFECHA_ENTREGA();
		    }
	  	
	    	parametros.put("cantidad", cantidadesId.toString());
	    	//logger.error("cantidad : "+cantidadesId.toString());
	    	 
	    	parametros.put("nombreEmpleadoLogeado", nombreEmpleado);
			//logger.error("EMPLEADO : "+nombreEmpleado);
			  		    	  
			parametros.put("ListGarantiasM", listFirst);
			//logger.error("ListGarantiasM : " + listFirst);
		    
		    parametros.put("ListGarantiasMM", listSecond);
		    //logger.error("ListGarantiasMM : " + listSecond);
		    
		    parametros.put("nombreOficial", nombreOficial);
		    //logger.error("nombreOficial : "+nombreOficial);
		    
		    parametros.put("fechaEntrega", fechEntrega);
		    //logger.error("fechaEntrega : "+fechEntrega);
		    
		    parametros.put("fechaEntrega", fechEntrega);
		    //logger.error("fechaEntrega : "+fechEntrega);
	    }  
	    if(bandJasper==4){
	    	
		    JRBeanCollectionDataSource listFirst = new JRBeanCollectionDataSource(listaGarantiasReporteVO);
   
	    	
	    	for(VSSPGarantiaReporteRecibirMasivaFVO registroAll : listaGarantiasReporteVO){
		    	infracNum.add(registroAll.getINFRAC_NUM());
		    	nombreOficial=registroAll.getNOMBRE_OFICIAL();
		    	fechEntrega=registroAll.getFECHA_ENTREGA();	
		    }
	    	parametros.put("cantidad", cantidadesId.toString());
	    	//logger.error("cantidad : "+cantidadesId.toString());
	    	 
	    	parametros.put("nombreEmpleadoLogeado", nombreEmpleado);
			//logger.error("EMPLEADO : "+nombreEmpleado);
			  		    	
			parametros.put("ListGarantiasM", listFirst);
			//logger.error("ListGarantiasM : " + listFirst);
		    
		    parametros.put("nombreOficial", nombreOficial);
		    //logger.error("nombreOficial : "+nombreOficial);
		    
		    parametros.put("fechaEntrega", fechEntrega);
		    //logger.error("fechaEntrega : "+fechEntrega);
		    
		    parametros.put("fechaEntrega", fechEntrega);
		    //logger.error("fechaEntrega : "+fechEntrega);
	    	
	    }
	    	try {
	    		
	    		reporte.write(JasperRunManager.runReportToPdf(rutaArchivo, parametros,new JREmptyDataSource()));
	    		
			} catch (Exception e) {
				logger.error("Entro al excepction al crear el PDF: "+e.getMessage());
			}
		    return reporte;
	    	
	    }
}
	