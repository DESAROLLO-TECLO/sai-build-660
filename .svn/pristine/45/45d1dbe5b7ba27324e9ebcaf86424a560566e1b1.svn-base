package mx.com.teclo.saicdmx.negocio.service.certificados;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.PerfilDTO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.CertificadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.ConsultaUsersVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.PerfilVO;
/**
 * 
 * @author javier07
 *
 */
public interface CertificadosService {
	
	/**
	 * @author javier07
	 * @param adminstradores
	 * @param tipoBusqueda
	 * @param valor
	 * @return
	 */
	List<ConsultaUsersVO> obtenerListaUsuarios(String tipoBusqueda,String tipoBusqueda2, String valor);
    /**
     * @author Jesus Gutierrez
     * @return List<PerfilDTO>
     */
	List<PerfilDTO> listaPerfilesByPerfilUsuario (String codigo);
	 /**
	  * @author javier07
	  * @return List<PerfilDTO>
	  */
	List<PerfilDTO> obtieneListaPerfilesAdmin ();
	
	/**
	 * 
	 * @param placaOficial
	 * @param usuarioId
	 * @param files
	 * @param validado
	 * @return Map<String, Object>
	 */
	Map<String, Object> saveCertKeySAT(String placaOficial, Long usuarioId, List<MultipartFile> files,Integer validado);


	/** 
	 * Actualiza  la entidad Certificado existente, de acuerdo a la placa de Empleado.
	 * @param placaOficial 
	 * @param usuarioId 
	 * @param List<MultipartFile>  Contiene los archivos del certificado.
	 * @return Map<String, Object>
	 */
	 Map<String, Object> updateCertificado(String placaOficial, Long usuarioId,  List<MultipartFile> files, Integer validado);
	
	/** 
	 * Valida que la entidad Certificado exista, de acuerdo a la placa de Empleado.
	 * @param placaOficial 
	 * @return CertificadoVO
	 */
	 CertificadoVO obtieneCertificadoPorPlaca( String placaOficial);

	/** 
	 * Valida que la llave privada corresponda al certificado.
	 * @param  List<MultipartFile>  Contiene los archivos del certificado.
	 *         password
	 * @return Map<String, Object>
	 */
	  Map<String, Object> validarParidad( List<MultipartFile> files, String passPrivateKey);

	/** 
	 * Valida que la llave privada corresponda al certificado.
	 * @param String placaOficial 
	 * @param String  passPrivateKey  .
	 * @return Map<String, Object>
	 */
 	  Map<String, Object> validarParidad( String placaOficial, String passPrivateKey);
	
	
	/** 
	 * Cambia a estado inactivo un certificado por placa de Empleado
	 * @param placaOficial String
	 * @return CertificadoVO
	 */
	 CertificadoVO deleteCertificado( Long idEmpleado);
	
	/** 
	 * Busca la entidad Certificado por parámetros de Empleados y Rango de fechas.
	 * @param tipoBusqueda 
	 * @param paramBusqueda 
	 * @param fechaInicio 
	 * @param fechaFin 
	 *@param validado 

	 * @returnMap<String, Object>  Entidades encontradas e informacion adicional.
	 */
	 List<CertificadoVO> obtieneCertificados(  String tipoBusqueda, String paramBusqueda, String fechaInicio, String fechaFin, Integer validado);
	

	/** 
	 * Actualiza rfc de empleado.
	 * @param String placaOficial 
	 * @param String  rfc  .
	 * @return Map<String, Object>
	 */
 	  Map<String, Object> actualizaRFCEmpleado( String placaOficial, String rfc);
	

}
