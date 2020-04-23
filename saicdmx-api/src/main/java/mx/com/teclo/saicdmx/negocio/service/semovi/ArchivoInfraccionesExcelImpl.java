package mx.com.teclo.saicdmx.negocio.service.semovi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesConPuntosLicenciaVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;



/**
 * 
 * @author javier07
 *
 */
@Service
public class ArchivoInfraccionesExcelImpl implements ArchivoInfraccionesExcel{
	
	private static final Logger logger = Logger
			.getLogger(ArchivoInfraccionesExcelImpl.class);
	
	@Autowired
	private ParametroService parametros;
	@Autowired
	private ArchivoService archivoService;
	
	/**
	 * @author javier07
	 * @param listaRegistros
	 * @param nombreReporte String
	 * @return byte[]
	 */
	@Transactional
	public Map<String, Object> creaArchivoExcel(List<InfraccionesConPuntosLicenciaVO> listaRegistros ,String nombreArchivoExcel, String fechaArchivo){
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		Map<String, Object> response = new HashMap<String, Object>();
		
		Integer fila = 0;
		String[] listaEncabezados = {"FECHA_CORTA","LICENCIA","TIPO_LICENCIA", "LICENCIA_EXPEDIDA_EN", "APELLIDO_PATERNO", "APELLIDO_MATERNO", "NOMBRE","INFRACCIÓN",
									 "FECHA_LARGA","ARTÍCULO", "FRACCIÓN", "INCISO", "PÁRRAFO", "MOTIVACIÓN", "PUNTOS"};
		FileOutputStream archivo = null;
		 
		String anio = fechaArchivo.substring(fechaArchivo.length()-4, fechaArchivo.length());
		String rutaArchivo = parametros.getRutaSemovi() + "SALIDA/"+ anio + "/" + nombreArchivoExcel;
		
		archivoService.creaDirectoriosSemovi(anio);
		
        File archivoXLS = new File(rutaArchivo);
        
        try {
			archivoXLS.createNewFile();
			
			archivo = new FileOutputStream(archivoXLS);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}catch (IOException e1) {
			logger.error(e1.getMessage());
		} catch(Exception e3){
			logger.error(e3.getMessage());
		}
		
		Workbook libro = new HSSFWorkbook();
        Sheet hoja = libro.createSheet();
        
        //sub titulos        
        HSSFFont fontEnbezados = (HSSFFont) libro.createFont();
        fontEnbezados.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        HSSFCellStyle styleEncabezado = (HSSFCellStyle) libro.createCellStyle();  
        styleEncabezado.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        styleEncabezado.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        styleEncabezado.setFont(fontEnbezados);
        
        
        Row filaEncabezados = hoja.createRow(fila);
        
        for (int i = 0; i < listaEncabezados.length; i++) {
			Cell celdaEncazado = filaEncabezados.createCell(i);
			celdaEncazado.setCellValue(listaEncabezados[i]);
			celdaEncazado.setCellStyle(styleEncabezado);
		}
        fila++;
       
        //se crea el cuerpo del documento
        
        Row fContenido;
        
        for(InfraccionesConPuntosLicenciaVO InfraccionesConPuntosLicenciaVO : listaRegistros){
        	fContenido = hoja.createRow(fila);
        	fContenido.createCell(0).setCellValue(InfraccionesConPuntosLicenciaVO.getFechaCorta());
        	fContenido.createCell(1).setCellValue(InfraccionesConPuntosLicenciaVO.getLicencia());
        	fContenido.createCell(2).setCellValue(InfraccionesConPuntosLicenciaVO.getTipoLicencia());
        	fContenido.createCell(3).setCellValue(InfraccionesConPuntosLicenciaVO.getLicenciaExpedidaEn());
        	fContenido.createCell(4).setCellValue(InfraccionesConPuntosLicenciaVO.getApellidoPaterno());
        	fContenido.createCell(5).setCellValue(InfraccionesConPuntosLicenciaVO.getApellidoMaterno());
        	fContenido.createCell(6).setCellValue(InfraccionesConPuntosLicenciaVO.getNombre());
        	fContenido.createCell(7).setCellValue(InfraccionesConPuntosLicenciaVO.getInfraccion());
        	fContenido.createCell(8).setCellValue(InfraccionesConPuntosLicenciaVO.getFechaLarga());
        	fContenido.createCell(9).setCellValue(InfraccionesConPuntosLicenciaVO.getArticulo());
        	fContenido.createCell(10).setCellValue(InfraccionesConPuntosLicenciaVO.getFraccion());
        	fContenido.createCell(11).setCellValue(InfraccionesConPuntosLicenciaVO.getInciso());
        	fContenido.createCell(12).setCellValue(InfraccionesConPuntosLicenciaVO.getParrafo());
        	fContenido.createCell(13).setCellValue(InfraccionesConPuntosLicenciaVO.getMotivacion());
        	fContenido.createCell(14).setCellValue(InfraccionesConPuntosLicenciaVO.getPuntos());
            
            fila++;
        }        
        
        try {
        	//escribe el archivo que se genero en la ruta
			libro.write(archivo);
	        archivo.close();
	        response.put("nombreArchvo", nombreArchivoExcel);
	        response.put("accion", 1);
		} catch (IOException e) {
			logger.error(e.getMessage());
	        response.put("accion", 2);
		}
        
        return response;
	}
	
	
	
}
