package mx.com.teclo.saicdmx.pdf.lineadecaptura;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.DetalleDeReasignacionesInfraccionVO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

public class PagoFinanzas {
	private static final Logger logger = Logger.getLogger(PagoFinanzas.class);
	/**
	 * @author Jesus Gutierrez
	 * @param parametrosVO
	 * @param rutaTotalArchivo
	 * @param rutaTotalImagen
	 * @return ByteArrayOutputStream
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ByteArrayOutputStream generaReporteFUTPDF(DetalleDeReasignacionesInfraccionVO parametrosVO, String rutaTotalArchivo, String rutaTotalImagen) throws FileNotFoundException{
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		File img = new File(rutaTotalImagen);
	    InputStream imagen = new FileInputStream(img);
	    Map parametros = new HashMap();
	    parametros.put("formatoTesoreria", imagen);
	    parametros.put("conceptoCobro1","MULTA DE TRÁNSITO CON PLACA VEHICULAR");
	    parametros.put("conceptoCobro2","MULTA DE TRÁNSITO CON PLACA VEHICULAR");
	    parametros.put("descuento",NumberFormat.getCurrencyInstance().format(parametrosVO.getDescuento()));
	    parametros.put("lineaCaptura", parametrosVO.getLineaCaptura());
	    parametros.put("multa", NumberFormat.getCurrencyInstance().format(parametrosVO.getImporte()));
	    parametros.put("numeroInfraccion", parametrosVO.getFolioInfraccion());
	    parametros.put("recargos", NumberFormat.getCurrencyInstance().format(parametrosVO.getRecargos()));
        parametros.put("totalPagar",  NumberFormat.getCurrencyInstance().format(parametrosVO.getTotal()));
        parametros.put("vigencia", parametrosVO.getVigencia());  
	    parametros.put("placa", parametrosVO.getPlacaVehiculo()); 
	    try {
	    	reporte.write(JasperRunManager.runReportToPdf(rutaTotalArchivo, parametros));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	    return reporte;
	}
}
