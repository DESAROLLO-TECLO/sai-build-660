package mx.com.teclo.saicdmx.pdf.remisionadeposito;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ReciboArrastreVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ReciboIngresoVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

public class RecibosConInfraccion {

	private static final Logger logger = Logger
			.getLogger(RecibosConInfraccion.class);
	
	RutinasTiempoImpl rutinasTiempo = null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ByteArrayOutputStream generaReporteResguardoPDF(ReciboIngresoVO reciboIngresoVO, String rutaTotalArchivo, String listInventario) throws FileNotFoundException{

		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		
		rutinasTiempo = new RutinasTiempoImpl();
		Date fechaInfraccion = null;
		
		String infrac_mFecha_hora = "";
		String infrac_mFecha_dia = "";
		String infrac_mFecha_mes = "";
		String infrac_mFecha_anio = "";
		
		String ingreso_sellado_deposito = "";
		String ingreso_sellado_arrastre = "";
		String ingreso_cajuela = "";
		String infrac_calle = " ";
		String infrac_colonia = " ";
		String infrac_del = " ";
		
		
		if (reciboIngresoVO.getFecha() != null){
			fechaInfraccion = rutinasTiempo.convertirStringDate(reciboIngresoVO.getFecha(), "dd/MM/yyyy HH:mm");	
		}else{
			fechaInfraccion = rutinasTiempo.getFechaActual();
		}
		
		Map fechaDesglosada = rutinasTiempo.obtenerFechaDesglosada(fechaInfraccion);
		
		infrac_mFecha_hora = rutinasTiempo.getStringDateFromFormta("HH:mm", fechaInfraccion);
		infrac_mFecha_dia = (String) fechaDesglosada.get("day").toString();			
		infrac_mFecha_mes = (String) fechaDesglosada.get("month"); 			
		infrac_mFecha_anio = (String) fechaDesglosada.get("year").toString();  
				
	
        if (reciboIngresoVO.getSellado() != null) {
            ingreso_sellado_deposito = reciboIngresoVO.getSellado().equals("D") ? "X" : "";
            ingreso_sellado_arrastre = reciboIngresoVO.getSellado().equals("A") ? "X" : "";
        }
        if (reciboIngresoVO.getCajuela() != null) {
            ingreso_cajuela = reciboIngresoVO.getCajuela().equalsIgnoreCase("S") ? "SI" : "NO";
        }
//        else{
//        	ingreso_cajuela = "NO";
//        }
 
        if (reciboIngresoVO.getInfrac_m_en_la_calle() != null){
        	infrac_calle = reciboIngresoVO.getInfrac_m_en_la_calle();
        }
        
        if (reciboIngresoVO.getInfrac_m_colonia() != null){
        	infrac_colonia = reciboIngresoVO.getInfrac_m_colonia();
        } 
        
        if (reciboIngresoVO.getDel_nombre() != null){
        	infrac_del = reciboIngresoVO.getDel_nombre();
        }
		
		 Map p = new HashMap();
		  
		 p.put("infrac_mFecha_hora", infrac_mFecha_hora);
		 p.put("infrac_m_en_la_calle", infrac_calle);
		 p.put("infrac_m_colonia", infrac_colonia);
		 p.put("infrac_m_delegacion", infrac_del);
		 p.put("infrac_placa", reciboIngresoVO.getInfrac_placa());
		 p.put("infrac_mFecha_dia", infrac_mFecha_dia);
		 p.put("infrac_mFecha_mes", infrac_mFecha_mes.toUpperCase());
		 p.put("infrac_mFecha_anio", infrac_mFecha_anio);
		 p.put("infrac_vehiculo", reciboIngresoVO.getVtipo_nombre());
		 p.put("infrac_marca", reciboIngresoVO.getVmar_nombre());
		 p.put("infrac_modelo", reciboIngresoVO.getVmod_nombre());
		 p.put("infrac_color",  reciboIngresoVO.getVcolor_nombre());
		 p.put("infrac_deposito", reciboIngresoVO.getDep_nombre());
		 p.put("ingreso_tipo_ingreso", reciboIngresoVO.getT_ingr_nombre());
		 p.put("ingreso_num_grua_eco", reciboIngresoVO.getGrua_cod());
		 p.put("infrac_empleado", reciboIngresoVO.getEmp_nombre());
		 p.put("infrac_articulo", reciboIngresoVO.getArt_numero());
		 p.put("infrac_fraccion", reciboIngresoVO.getArt_fraccion());
		 p.put("infrac_parrafo", reciboIngresoVO.getArt_parrafo());
		 p.put("infrac_inciso", reciboIngresoVO.getArt_inciso());
		 p.put("infrac_motivacion", reciboIngresoVO.getArt_motivacion());
		 p.put("infrac_s_articulo", reciboIngresoVO.getSancionnumero());
		 p.put("infrac_s_fraccion", reciboIngresoVO.getSancionfraccion());
		 p.put("infrac_s_parrafo", reciboIngresoVO.getSancionparrafo());
		 p.put("infrac_s_inciso", reciboIngresoVO.getSancioninciso());
		 p.put("infrac_s_dias_multa", reciboIngresoVO.getArt_dias());
		 p.put("ingreso_sellado_deposito", ingreso_sellado_deposito);
		 p.put("ingreso_sellado_arrastre", ingreso_sellado_arrastre);
		 p.put("ingreso_cajuela", ingreso_cajuela);
		 p.put("ingreso_lista_componentes", listInventario); //PENDIENTE
		 p.put("infrac_nombre_infractor", reciboIngresoVO.getInfractor());
		 p.put("infrac_tipo_licencia", reciboIngresoVO.getTipo_l_cod());
		 p.put("ingreso_resguardo", reciboIngresoVO.getIngr_asn());
		 p.put("ingreso_placa_oficial", reciboIngresoVO.getEmp_placa());
		 p.put("ingreso_observaciones", reciboIngresoVO.getIngr_observa()); 
       
	        
		 try {
			 reporte.write(JasperRunManager.runReportToPdf(rutaTotalArchivo, p, new JREmptyDataSource()));
		  } catch (Exception e) {
			 logger.error(e.getMessage());
		  }
		 
	    return reporte;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ByteArrayOutputStream generaReporteArrastrePDF(ReciboArrastreVO reciboArrastreVO, String rutaTotalArchivo) throws FileNotFoundException{
        ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
        rutinasTiempo = new RutinasTiempoImpl();
        
        String tipoArrastreI = "";
        String tipoArrastreA = "";
        Date fechaInfraccion = null;
        String num_infraccion_mostrar = "";
        
        if (reciboArrastreVO.getFecha() != null){
			fechaInfraccion = rutinasTiempo.convertirStringDate(reciboArrastreVO.getFecha(), "dd/MM/yyyy HH:mm");	
		}else{
			fechaInfraccion = rutinasTiempo.getFechaActual();
		}
		
		String infrac_mFecha_hora = rutinasTiempo.getStringDateFromFormta("HH:mm", fechaInfraccion);
		String infrac_mFecha = rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy", fechaInfraccion);

		if(reciboArrastreVO.getInfrac_tipo_arrastre() == null){
			tipoArrastreA = "";
		}else{
			if(reciboArrastreVO.getInfrac_tipo_arrastre().equals("I")){
	        	tipoArrastreI = "X";
	        }else if(reciboArrastreVO.getInfrac_tipo_arrastre().equals("A")){
	        	tipoArrastreA = "X";
	        }
		}
        
        if(reciboArrastreVO.getInfrac_docto() == null){
        	num_infraccion_mostrar = "No. INFRACCI\326N: " + reciboArrastreVO.getInfrac_num();
        }else{
        	num_infraccion_mostrar = "DOCUMENTO: " + reciboArrastreVO.getInfrac_docto();
        }

	    Map p = new HashMap();
	    p.put("infrac_num_arrastre", reciboArrastreVO.getInfrac_num_arrastre());
	    p.put("infrac_sector", reciboArrastreVO.getSec_nombre());
	    p.put("ingreso_num_resguardo", reciboArrastreVO.getIngr_asn());
	    p.put("infrac_grua", reciboArrastreVO.getGrua_cod());
	    p.put("infrac_concesionaria", reciboArrastreVO.getConse_nombre());
	    p.put("infrac_veh_marca", reciboArrastreVO.getVmar_nombre());
	    p.put("infrac_veh_modelo", reciboArrastreVO.getVmod_nombre());
	    p.put("infrac_veh_color", reciboArrastreVO.getVcolor_nombre());
	    p.put("infrac_tipo_arrastre_i", tipoArrastreI);
	    p.put("infrac_m_en_la_calle", reciboArrastreVO.getInfrac_m_en_la_calle());
	    p.put("infrac_m_entre_la_calle", reciboArrastreVO.getInfrac_m_entre_calle());
	    p.put("infrac_m_y_la_calle", reciboArrastreVO.getInfrac_m_y_la_calle());
	    p.put("infrac_tipo_arrastre_a", tipoArrastreA);
	    p.put("infrac_emp_placa", reciboArrastreVO.getEmp_placa());
        p.put("infrac_emp_nombre", reciboArrastreVO.getEmp_nombre());
	    p.put("infrac_placa", reciboArrastreVO.getInfrac_placa());
	    p.put("infrac_mFecha_hora", infrac_mFecha_hora);
	    p.put("infrac_m_delegacion", reciboArrastreVO.getDel_nombre());
	    p.put("infrac_m_colonia", reciboArrastreVO.getInfrac_m_colonia());
	    p.put("infrac_mFecha", infrac_mFecha);
	    p.put("infrac_observacion", reciboArrastreVO.getInfrac_observacion());
	    p.put("infrac_num_infraccion", num_infraccion_mostrar);
	    p.put("infrac_oper_grua", reciboArrastreVO.getInfrac_oper_grua());
	    
	    try {
	    	reporte.write(JasperRunManager.runReportToPdf(rutaTotalArchivo, p, new JREmptyDataSource()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return reporte;
	}

}
