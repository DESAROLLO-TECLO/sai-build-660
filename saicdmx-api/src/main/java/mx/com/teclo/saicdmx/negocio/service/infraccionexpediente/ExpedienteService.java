package mx.com.teclo.saicdmx.negocio.service.infraccionexpediente;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import mx.com.teclo.saicdmx.persistencia.vo.infracciones.ImagenesExpedientesReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionValidaImagenVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.FilesSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VConsultaExpediente;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VDirectorioDigitalizacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VImagenesHandHeldVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VImagenesIngresoVO;

public interface ExpedienteService {

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CONSULTA_EXPEDIENTE FILTRADO POR INFRAC_NUM
	 * @Author Kevin Ojeda
	 * @param valor
	 * @return List<VConsultaExpediente>
	 */
	public List<VConsultaExpediente> getVConsultaExpediente(String valor);

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_IMAGENES_INGRESO FILTRADO POR INFRAC_NUM
	 * @Author Kevin Ojeda
	 * @param valor
	 * @return List<VImagenesIngresoVO>
	 */
	public List<VImagenesIngresoVO> getVImagenesIngresoVO(String valor);

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_DIRECTORIOS_DIGITALIZACION FILTRADO POR INFRAC_NUM(valor) y ANV_REV
	 * @author Kevin Ojeda
	 * @param valor
	 * @param anvRev
	 * @return List<VImagenesIngresoVO>
	 */
	public List<VDirectorioDigitalizacionVO> getVDirectorioDigitalizacionVO(String valor, String anvRev);

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_IMAGENES_HANDHELD FILTRADO POR INFRAC_NUM(valor)
	 * @author Kevin Ojeda
	 * @param valor
	 * @return List<VImagenesHandHeldVO>
	 */
	public List<VImagenesHandHeldVO> getVImagenesHandHeldVO(String valor);

	/**
	 * Ejecuta SP SP_INFRACCION_VALIDA_IMAGEN
	 * @author Kevin Ojeda
	 * @param InfraccionValidaImagenSPVO infraccionValidaImagenSPVO
	 * @return InfraccionValidaImagenSPVO
	 * @throws PersistenceException
	 */
	public InfraccionValidaImagenVO SPInfraccionValidaImagenVO(InfraccionValidaImagenVO infraccionValidaImagenVO);

	/**
	 * @author Javier Flores
	 * Realiza la descarga de una imagen alojada en una URL recibida como parametro - Expediente Infracción
	 * @param String url
	 * @return byte[]
	 * @throws IOException 
	 * @throws Throwable 
	 */
	byte[] descargarImagenInfraccion(String url);
	
	public ByteArrayOutputStream generaReporteExpedienteClasico(
			String infraccion, String articulo, String motivacion, 
			List<ImagenesExpedientesReporteVO>listaImgExpedienteInfrac, 
			List<ImagenesExpedientesReporteVO>listaImgExpedienteHandhe, 
			List<ImagenesExpedientesReporteVO>listaImgExpedienteExpPago,
			List<ImagenesExpedientesReporteVO>listaImgExpedienteExpAR
		);
	
	public String consultaRutaImg(String tipo);
}
