package mx.com.teclo.saicdmx.negocio.service.infraccionexpediente;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.infracciones.BitSpInfraccionValidaImagen;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.infraccionexpediente.ExpedienteMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.ImagenesExpedientesReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionValidaImagenSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionValidaImagenVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VConsultaExpediente;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VDirectorioDigitalizacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VImagenesHandHeldVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VImagenesIngresoVO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service("expedienteService")
public class ExpedienteServiceImpl implements ExpedienteService {
	
	private static final Logger logger = Logger
			.getLogger(ExpedienteServiceImpl.class);
	
	@Autowired
	private ExpedienteMyBatisDAO expedienteMyBatisDAO;
	@Autowired
    private BitSpInfraccionValidaImagen  bitSpInfraccionValidaImagen;
	
	@Autowired
	private ServletContext context;
	
	private static final String URL_STORAGE = "http://imagenes.infracciones.df.gob.mx/";
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VConsultaExpediente> getVConsultaExpediente(String valor){
		return expedienteMyBatisDAO.getVConsultaExpediente(valor);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VImagenesIngresoVO> getVImagenesIngresoVO(String valor){
		return expedienteMyBatisDAO.getVImagenesIngresoVO(valor);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VDirectorioDigitalizacionVO> getVDirectorioDigitalizacionVO(String valor, String anvRev){
		return anvRev.equals("A") ? 
				expedienteMyBatisDAO.getVDirectorioDigitalizacionVOA(valor) : 
					anvRev.equals("R") ? 
							expedienteMyBatisDAO.getVDirectorioDigitalizacionVOR(valor) :
								anvRev.equals("A_VALID") ? 
										expedienteMyBatisDAO.getVDirectorioDigitalizacionVO("A", valor):
											expedienteMyBatisDAO.getVDirectorioDigitalizacionVO("R", valor);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VImagenesHandHeldVO> getVImagenesHandHeldVO(String valor){
		return expedienteMyBatisDAO.getVImagenesHandHeldVO(valor);
	}
	
	/**
	 * {@inheritDoc}
	 * @author dagoberto
	 * SP_INFRACCION_VALIDA_IMAGEN
	 */
	@Override
	public InfraccionValidaImagenVO SPInfraccionValidaImagenVO
	(InfraccionValidaImagenVO infraccionValidaImagenVO){
		infraccionValidaImagenVO.generaParametrosParaSP();
		InfraccionValidaImagenSPVO infraccionValidaImagenSPVO = infraccionValidaImagenVO.getInfraccionValidaImagenSPVO();
		expedienteMyBatisDAO.InfraccionValidaImagenSPVO(infraccionValidaImagenSPVO);
		
		if(infraccionValidaImagenSPVO.getP_resultado() != null){
			bitSpInfraccionValidaImagen.validaSpImagen(infraccionValidaImagenSPVO);
			
		}

		
		infraccionValidaImagenVO.setInfraccionValidaImagenSPVO(null);
		infraccionValidaImagenVO.setpResultado(infraccionValidaImagenSPVO.getP_resultado());
		infraccionValidaImagenVO.setpMensaje(infraccionValidaImagenSPVO.getP_mensaje());
		return infraccionValidaImagenVO;
	}

	@Override
	public byte[] descargarImagenInfraccion(String ruta) {
		
			HttpURLConnection  connection =null;
			InputStream in =null;
		 	byte[] array = null;
		 	System.setProperty("http.keepAlive", "false");
		 	URL url;
			
			try {
			
			url = new URL(URL_STORAGE+ruta);
			connection =   (HttpURLConnection)(url.openConnection());
			
			 in = connection.getInputStream();
		    int contentLength = connection.getContentLength();
 
		    ByteArrayOutputStream tmpOut;
		    if (contentLength != -1) {
		        tmpOut = new ByteArrayOutputStream(contentLength);
		    } else {
		        tmpOut = new ByteArrayOutputStream(16384); 
		        // Pick some appropriate size
		    }

		    byte[] buf = new byte[512];
		    while (true) {
		        int len = in.read(buf);
		        if (len == -1) {
		            break;
		        }
		        tmpOut.write(buf, 0, len);
		    }
		   // in.close();
		    tmpOut.close();
		    
		    array = tmpOut.toByteArray();
			} catch (IOException e) {
				
				logger.error("**************** No se puede acceder "+e.getMessage());
				
				return null;
			}finally {
			    if (in != null) {
			        try {
			            in.close();
			        } catch (IOException e) {
			        }
			    }
			    if (connection != null) {
			    	connection.disconnect();
			    }
			}
			return array;	
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ByteArrayOutputStream generaReporteExpedienteClasico(
			String infraccion, String articulo, String motivacion, 
			List<ImagenesExpedientesReporteVO>listaImgExpedienteInfrac, 
			List<ImagenesExpedientesReporteVO>listaImgExpedienteHandhe, 
			List<ImagenesExpedientesReporteVO>listaImgExpedienteExpPago,
			List<ImagenesExpedientesReporteVO>listaImgExpedienteExpAR
		){
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		String rutaArchivo;
		String rutalogoSSP;
		String rutalogoTeclo;
		InputStream inputStreamLogoSSP = null;
		InputStream inputStreamLogoTeclo  = null;
		Map parametros = new HashMap();
		
		rutaArchivo = context.getRealPath("/WEB-INF/jasper/expediente/reporteExpedienteInfraccion.jasper");
		JRBeanCollectionDataSource dtImgInfrac = new JRBeanCollectionDataSource(listaImgExpedienteInfrac);
		JRBeanCollectionDataSource dtImgHandhe = new JRBeanCollectionDataSource(listaImgExpedienteHandhe);
		JRBeanCollectionDataSource dtImgExpPago = new JRBeanCollectionDataSource(listaImgExpedienteExpPago);
		JRBeanCollectionDataSource dtImgExpAR = new JRBeanCollectionDataSource(listaImgExpedienteExpAR);
		
		rutalogoSSP ="WEB-INF/imagenes/logo_CDMX_SSC.png";
		rutalogoTeclo = "WEB-INF/imagenes/logoTeclo.png";
		try {
			File fileLogoSSP = new ClassPathResource(rutalogoSSP).getFile();
			File fileLogoTeclo  = new ClassPathResource(rutalogoTeclo).getFile();
			
			inputStreamLogoSSP = new FileInputStream(fileLogoSSP);
			inputStreamLogoTeclo  = new FileInputStream(fileLogoTeclo);
		} catch (IOException e1) {
			
		}
		
		parametros.put( "expediente", infraccion);
		parametros.put( "articulo", articulo);
		parametros.put( "motivacion", motivacion);
		parametros.put( "logotipoCdmxSsp", inputStreamLogoSSP);
		parametros.put( "logotipoTeclo", inputStreamLogoTeclo);
		parametros.put( "listaImagenesInfrac", dtImgInfrac);
		parametros.put( "listaImagenesHandhe", dtImgHandhe);
		parametros.put( "listaImagenesExpPago", dtImgExpPago);
		parametros.put( "listaImagenesExpAR", dtImgExpAR);
		//rutaArchivo = context.getRealPath("/WEB-INF/jasper/expediente/reporteExpedienteInfraccion.jasper");
        
        try {
			reporte.write(JasperRunManager.runReportToPdf(rutaArchivo, parametros,new JREmptyDataSource()));
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return reporte;
		//return null;
	}
	
	@Override
	public String consultaRutaImg(String tipo){
		String rutaImg = null;
		if(tipo.equals("HANDHELD")){
			rutaImg = expedienteMyBatisDAO.getVRutaImgHandheld();
		}
		
		return rutaImg;
	}
}
