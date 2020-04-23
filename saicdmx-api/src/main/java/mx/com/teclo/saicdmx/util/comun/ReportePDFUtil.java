package mx.com.teclo.saicdmx.util.comun;

import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;



public class ReportePDFUtil {

	/**
     * Genera Reporte pdf 
     * @param parametrosReporte : datos del reporte.
     * @param pathReporteJasper : ruta del reporte .jasper.
     * @param nombreReporteSalida : nombre del reporte.
     * @return Regresa un <b>byte[]</b> 
	 * @throws JRException 
     */
	public static byte[] generaReportePdf(Map<String, Object> parametrosReporte, String pathReporteJasper, String nombreReporteSalida) throws JRException {
        JasperPrint jp = JasperFillManager.fillReport(pathReporteJasper, parametrosReporte, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jp);
	}
	
}