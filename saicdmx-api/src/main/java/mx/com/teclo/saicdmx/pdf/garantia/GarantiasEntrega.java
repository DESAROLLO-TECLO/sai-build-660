package mx.com.teclo.saicdmx.pdf.garantia;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteEntregarFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteRecibirFVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;



public class GarantiasEntrega {
	private static final Logger logger = Logger
			.getLogger(GarantiasEntrega.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ByteArrayOutputStream generaReporteGarantiasEntregaPDF(Long garantiaId, String rutaTotalArchivo, String rutaTotalImagen, VSSPGarantiaReporteEntregarFVO listaGarantiasEntregaVO) throws FileNotFoundException{
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		File img = new File(rutaTotalImagen);
	    InputStream imagen = new FileInputStream(img);
	    Map listaFecha = rutinasTiempoImpl.obtenerFechaDesglosada(new Date());
	    Locale locale = new Locale("es", "MX");
		
	    Map parametros = new HashMap();
	    parametros.put("fecha", "M�xico, D.F., a "+ listaFecha.get("day") + " de "+ listaFecha.get("month") + " de " + listaFecha.get("year") + ".");
	    parametros.put("imagen", imagen);
	    parametros.put("garantiaId",garantiaId.toString());
	    parametros.put("REPORT_LOCALE", locale);
	    
	    parametros.put("nombre", listaGarantiasEntregaVO.getNOMBRE());
	    parametros.put("documentoFolio", listaGarantiasEntregaVO.getDOCUMENTO_FOLIO());
	    parametros.put("infracNum", listaGarantiasEntregaVO.getINFRAC_NUM());
	    parametros.put("infracFecha", listaGarantiasEntregaVO.getINFRAC_FECHA());
	    parametros.put("fechaCreacion", listaGarantiasEntregaVO.getFECHA_CREACION());
	    parametros.put("fechaRecibimiento", listaGarantiasEntregaVO.getFECHA_RECIBIMIENTO());
	    
	    try {
	    	reporte.write(JasperRunManager.runReportToPdf(rutaTotalArchivo, parametros, new JREmptyDataSource()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	    return reporte;
	}
}
