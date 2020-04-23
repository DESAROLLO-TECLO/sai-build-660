package mx.com.teclo.saicdmx.negocio.service.certificados;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.engine.jdbc.NonContextualLobCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.saicdmx.keyloader.certificados.KeyLoaderEnumeration;
import mx.com.teclo.saicdmx.keyloader.certificados.KeyLoaderFactory;
import mx.com.teclo.saicdmx.keyloader.certificados.PrivateKeyLoader;
import mx.com.teclo.saicdmx.keyloader.certificados.PublicKeyLoader;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.certificados.CertificadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado.EmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.perfil.PerfilDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.PerfilDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AgrupamientosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.SectorDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoEmpleadoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadoFirmaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.certificados.ConsultaUsuariosMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.CertificadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.ConsultaUsersVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.EmpleadoVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enumerados.CodigoPerfilesEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.PerfilVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

/**
 * 
 * @author javier07
 *
 */
@Service
public class CertificadosServiceImpl implements CertificadosService {

	private static final Logger logger = LoggerFactory.getLogger(CertificadosServiceImpl.class);

	@Value("${app.config.codigo}")
	private String codeApplication;

	@Autowired
	private ConsultaUsuariosMyBatisDAO consultaUsuarios;
	@Autowired
	private PerfilDAO perfilDAO;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	CertificadoDAO certificadoDAO;
	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	EmpleadoDAO empleadoDAO;

	ReadCertificateDataService readCertificateData;

	EmpleadoFirmaDTO empleadoFirmadoDTO = null;

	/**
	 * @author javier07 {@inheritDoc} Realiza una busqueda de usuarios
	 *         dependiendo de los criterios
	 */
	@Override
	public List<ConsultaUsersVO> obtenerListaUsuarios(String tipoBusqueda, String tipoBusqueda2, String valor) {
		List<ConsultaUsersVO> listaConsultaUsers = null;

		if (tipoBusqueda.equals("emp_placa")) {
			valor = valor != null ? valor.toLowerCase() : valor;

			if (!tipoBusqueda2.equals("3")) {
				listaConsultaUsers = consultaUsuarios.obtieneListaUsuarios_Certificado_Placa(valor, tipoBusqueda2);
			}else{
				listaConsultaUsers = consultaUsuarios.obtieneListaUsuariosPlaca(valor);
			}
		}
		
		if (tipoBusqueda.equals("emp_ape_paterno")) {
			valor = valor != null ? valor.toUpperCase() : valor;

			if (!tipoBusqueda2.equals("3")) {
				listaConsultaUsers = consultaUsuarios.obtieneListaUsuarios_Certificado_APaterno(valor, tipoBusqueda2);
			}else{
				listaConsultaUsers = consultaUsuarios.obtieneListaUsuariosAPaterno(valor);
			}
		}
		
		if (tipoBusqueda.equals("emp_nombre")) {
			valor = valor != null ? valor.toUpperCase() : valor;

			if (!tipoBusqueda2.equals("3")) {
				listaConsultaUsers = consultaUsuarios.obtieneListaUsuarios_Certificado_Nombre(valor, tipoBusqueda2);
			}else{
				listaConsultaUsers = consultaUsuarios.obtieneListaUsuariosNombre(valor);
			}
		}
		
		if (tipoBusqueda.equals("perfil_id")) {
			if (!tipoBusqueda2.equals("3")) {
				listaConsultaUsers = consultaUsuarios.obtieneListaUsuarios_Certificado_Perfil(valor, tipoBusqueda2);
			}else{
				listaConsultaUsers = consultaUsuarios.obtieneListaUsuariosPerfil(valor);
			}
		}

		if (tipoBusqueda.equals("emp_id")) {
			listaConsultaUsers = consultaUsuarios.obtieneListaUsuarios_Activos_Empl_id(valor);
		}

		if (!listaConsultaUsers.isEmpty()) {

			String emptyString = "";
			for (ConsultaUsersVO c : listaConsultaUsers) {

				c.setReg_nombre(c.getReg_nombre() == null ? emptyString : c.getReg_nombre());
				c.setUpc_nombre(c.getUpc_nombre() == null ? emptyString : c.getUpc_nombre());

				if ((c.getReg_nombre().isEmpty() && c.getUpc_nombre().isEmpty())) {
					c.setAreaOperativa("SIN ÁREA OPERATIVA");
				} else {
					c.setAreaOperativa(c.getReg_nombre() + "-" + c.getUpc_nombre());
				}
				c.setEstatus(c.getEstatus().equals("A") ? "Habilitado" : "Deshabilitado");
			}
		}

		return listaConsultaUsers;
	}

	/**
	 * @author Jesus Gutierrez {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<PerfilDTO> listaPerfilesByPerfilUsuario(String codigoPerfil) {
		List<PerfilDTO> lista = new ArrayList<PerfilDTO>();
		
		if (codigoPerfil.equals(CodigoPerfilesEnum.TCL_ADMINISTRADOR.getCodigo())) {
			lista = perfilDAO.obtieneListaPerfilesTCLAdministrador(codeApplication);
		} else {
			lista = perfilDAO.obtieneListaPerfiles(codeApplication);
		}

		return lista;// obtieneListaPerfilVO(listaPerfiles);
	}

	/**
	 * @author javier07 {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<PerfilDTO> obtieneListaPerfilesAdmin() {

		return perfilDAO.obtieneListaPerfilesTCLAdministrador(codeApplication);
	}

	public List<PerfilVO> obtieneListaPerfilVO(List<PerfilDTO> listaDTO) {

		List<PerfilVO> listaVO = new ArrayList<PerfilVO>();
		for (PerfilDTO perfilDTO : listaDTO) {
			PerfilVO perfilVO = new PerfilVO();
			perfilVO.setPerfilId(perfilDTO.getPerfilId());
			perfilVO.setPerfilNombre(perfilDTO.getPerfilNombre());
			listaVO.add(perfilVO);
		}
		return listaVO;

	}

	/**
	 * @author javier07 {@inheritDoc}
	 */
	@Override
	@Transactional
	public Map<String, Object> saveCertKeySAT(String placaOficial, Long usuarioId, List<MultipartFile> files,
			Integer validado) {

		CertificadoVO certificadoVO = null;
		Map<String, Object> response = new HashMap<String, Object>();
		MultipartFile fileKey = null;
		EmpleadosDTO empleadoDTO = empleadoService.getEmpleadoByPlaca(placaOficial);

		try {

			for (MultipartFile multipartFile : files) {

				String fileName = multipartFile.getOriginalFilename();
				String[] parts = fileName.split("[.]");
				String extension = parts[1]; //

				if (extension.equals("cer") || extension.equals("cert")) {
					readCertificateData = new ReadCertificateDataService(multipartFile.getInputStream());
					certificadoVO = readCertificateData.getCertificateData();
					certificadoVO.setCertArchivo(multipartFile.getBytes());

					certificadoVO.setEmpleadoVO(getEmpleadoVO(empleadoDTO));

					if (empleadoDTO.getEmpRFC() == null || empleadoDTO.getEmpRFC().isEmpty()) {
						certificadoVO.setKeyNombre(empleadoDTO.getEmpPlaca() + ".key");
						certificadoVO.setCertNombre(empleadoDTO.getEmpPlaca() + ".cer");
					} else {
						if (empleadoDTO.getEmpRFC().equals(certificadoVO.getPropietarioRfc())) {
							certificadoVO.setKeyNombre(empleadoDTO.getEmpRFC() + ".key");
							certificadoVO.setCertNombre(empleadoDTO.getEmpRFC() + ".cer");
						} else {
							response.put("respuesta", Boolean.FALSE);
							response.put("message", "El RFC del usuario no coincide con el RFC del certificado.");
							return response;
						}
					}

				} else {
					fileKey = multipartFile;
					certificadoVO.setKeyArchivo(fileKey.getBytes());
				}

			}
		} catch (IOException e) {
			logger.error("No se ha podido leer el certificado:" + e.getMessage());
		}

		empleadoFirmadoDTO = getCertificadoDTO(certificadoVO);
		empleadoFirmadoDTO.setFechaCreacion(new Date());
		empleadoFirmadoDTO.setUltimaModificacion(new Date());
		empleadoFirmadoDTO.setValidado(validado);
		empleadoFirmadoDTO.setEstatus(new Integer(1));
		empleadoFirmadoDTO.setCreadoPor(usuarioId);
		certificadoDAO.save(empleadoFirmadoDTO);

		response.put("respuesta", Boolean.FALSE);
		response.put("message", "El certificado  no se pudo guardar");

		if (certificadoVO != null) {
			response.put("respuesta", Boolean.TRUE);
			response.put("message", "El certificado se guardó correctamente");

			response.put("certificado", certificadoVO);
		}
		return response;
	}

	/**
	 * @author javier07 {@inheritDoc}
	 */
	@Transactional
	public CertificadoVO deleteCertificado(Long idEmpleado) {
		EmpleadoFirmaDTO empleadoFirmadoDTO = certificadoDAO.deleteCertificadoByPlaca(idEmpleado);
		return getCertificadoVO(empleadoFirmadoDTO);

	}

	/**
	 * @author javier07 {@inheritDoc}
	 */
	public static Blob arrayBytesToBlob(byte[] archivo) {
		Blob blob = NonContextualLobCreator.INSTANCE.wrap(NonContextualLobCreator.INSTANCE.createBlob(archivo));
		return blob;
	}

	/**
	 * @author javier07 {@inheritDoc}
	 */
	public static byte[] blobToArrayBytes(Blob blob) {
		byte[] data = null;
		if (blob != null) {
			try {
				data = blob.getBytes(1, (int) blob.length());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * @author javier07 {@inheritDoc}
	 */
	public Map<String, Object> validarParidad(List<MultipartFile> files, String passPrivateKey) {
		Map<String, Object> response = new HashMap<String, Object>();

		PrivateKey key = null;
		X509Certificate cert = null;
		try {

			for (MultipartFile multipartFile : files) {

				String fileName = multipartFile.getOriginalFilename();
				String[] parts = fileName.split("[.]");
				String extension = parts[1]; //

				if (extension.equals("cer") || extension.equals("cert")) {
					cert = KeyLoaderFactory
							.createInstance(KeyLoaderEnumeration.PUBLIC_KEY_LOADER, multipartFile.getInputStream())
							.getKey();

				} else {
					key = KeyLoaderFactory.createInstance(KeyLoaderEnumeration.PRIVATE_KEY_LOADER,
							multipartFile.getInputStream(), passPrivateKey).getKey();

				}

			}

			RSAPublicKeySpec rsaPrivateKey = PrivateKeyLoader.getPublicKeySpec(key);
			RSAPublicKeySpec rsaPublicKey = PublicKeyLoader.getPublicKeySpec(cert);

			if ((rsaPrivateKey.getModulus().equals(rsaPublicKey.getModulus()))
					&& rsaPrivateKey.getPublicExponent().equals(rsaPublicKey.getPublicExponent())) {
				response.put("respuesta", Boolean.TRUE);

			} else {
				response.put("respuesta", Boolean.FALSE);
				response.put("message", "La llave no le corresponde al certificado");
			}

		} catch (GeneralSecurityException e) {
			response.put("respuesta", Boolean.FALSE);
			response.put("message", "La contraseña de la llave privada  no es correcta");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * @author javier07 {@inheritDoc}
	 */
	@Override
	@Transactional
	public Map<String, Object> validarParidad(String placaOficial, String passPrivateKey) {
		EmpleadosDTO empleadoDTO = empleadoService.getEmpleadoByPlaca(placaOficial);
		Map<String, Object> response = new HashMap<String, Object>();

		CertificadoVO certificadoVO = getCertificadoVO(
				certificadoDAO.obtieneCertificadoPorPlaca(empleadoDTO.getEmpId()));
		InputStream isCertificado = new ByteArrayInputStream(certificadoVO.getCertArchivo());
		InputStream isLlave = new ByteArrayInputStream(certificadoVO.getKeyArchivo());

		PrivateKey key = null;
		X509Certificate cert = null;
		try {
			cert = KeyLoaderFactory.createInstance(KeyLoaderEnumeration.PUBLIC_KEY_LOADER, isCertificado).getKey();

			key = KeyLoaderFactory.createInstance(KeyLoaderEnumeration.PRIVATE_KEY_LOADER, isLlave, passPrivateKey)
					.getKey();

			RSAPublicKeySpec rsaPrivateKey = PrivateKeyLoader.getPublicKeySpec(key);
			RSAPublicKeySpec rsaPublicKey = PublicKeyLoader.getPublicKeySpec(cert);

			if ((rsaPrivateKey.getModulus().equals(rsaPublicKey.getModulus()))
					&& rsaPrivateKey.getPublicExponent().equals(rsaPublicKey.getPublicExponent())) {

				certificadoDAO.updateStatusCertificado(empleadoDTO.getEmpId());
				response.put("respuesta", Boolean.TRUE);
				response.put("message", "El certificado fue validado correctamente.");

			} else {
				response.put("respuesta", Boolean.FALSE);
				response.put("message", "La llave no le corresponde al certificado");
			}

		} catch (GeneralSecurityException e) {
			response.put("respuesta", Boolean.FALSE);
			response.put("message", "La contraseña de la llave privada  no es correcta");

		}

		return response;

	}

	/**
	 * @author javier07 {@inheritDoc}
	 */
	@Override
	@Transactional
	public Map<String, Object> actualizaRFCEmpleado(String placaOficial, String rfc) {
		EmpleadosDTO empleadoDTO = null;
		Map<String, Object> response = new HashMap<String, Object>();
		List<EmpleadosDTO> listRfc;

		listRfc = empleadoDAO.consultaRFCEmpleado(placaOficial, rfc);

		if (listRfc.isEmpty()) {

			empleadoDTO = empleadoDAO.actualizaRFCEmpleado(placaOficial, rfc);

			if (empleadoDTO != null) {
				response.put("respuesta", Boolean.TRUE);
				response.put("message", "El RFC se actualizó correctamente.");
				response.put("empleado", empleadoDTO.getEmpRFC());

			} else {
				response.put("respuesta", Boolean.FALSE);
				response.put("message", "El RFC no se pudo actualizar.");
			}
		} else {
			response.put("respuesta", Boolean.FALSE);
			response.put("message", "El RFC ya existe, por favor verificar.");
		}

		return response;
	}

	/**
	 * @author javier07 {@inheritDoc}
	 */

	@Override
	@Transactional
	public Map<String, Object> updateCertificado(String placaOficial, Long usuarioId, List<MultipartFile> files,
			Integer validado) {
		CertificadoVO certificadoVO = null;
		MultipartFile fileKey = null;
		Map<String, Object> response = new HashMap<String, Object>();
		EmpleadosDTO empleadoDTO = empleadoDAO.getEmpleadoByPlaca(placaOficial);
		try {

			for (MultipartFile multipartFile : files) {

				String fileName = multipartFile.getOriginalFilename();
				String[] parts = fileName.split("[.]");
				String extension = parts[1]; //

				if (extension.equals("cer") || extension.equals("cert")) {
					readCertificateData = new ReadCertificateDataService(multipartFile.getInputStream());
					certificadoVO = readCertificateData.getCertificateData();
					certificadoVO.setCertArchivo(multipartFile.getBytes());

					certificadoVO.setEmpleadoVO(getEmpleadoVO(empleadoDTO));
					if (empleadoDTO.getEmpRFC() == null || empleadoDTO.getEmpRFC().isEmpty()) {
						certificadoVO.setKeyNombre(empleadoDTO.getEmpPlaca() + ".key");
						certificadoVO.setCertNombre(empleadoDTO.getEmpPlaca() + ".cer");
					} else {
						if (empleadoDTO.getEmpRFC().equals(certificadoVO.getPropietarioRfc())) {
							deleteCertificado(empleadoDTO.getEmpId());

							certificadoVO.setKeyNombre(empleadoDTO.getEmpRFC() + ".key");
							certificadoVO.setCertNombre(empleadoDTO.getEmpRFC() + ".cer");
						} else {
							response.put("respuesta", Boolean.FALSE);
							response.put("message", "El RFC del usuario no coincide con el RFC del certificado.");
							return response;
						}
					}

				} else {
					fileKey = multipartFile;
					certificadoVO.setKeyArchivo(fileKey.getBytes());
				}

			}
			/* } */
		} catch (IOException e) {
			logger.error("No se ha podido leer el certificado:" + e.getMessage());
		}

		empleadoFirmadoDTO = getCertificadoDTO(certificadoVO);
		empleadoFirmadoDTO.setFechaCreacion(new Date());
		empleadoFirmadoDTO.setCreadoPor(usuarioId);
		empleadoFirmadoDTO.setUltimaModificacion(new Date());
		empleadoFirmadoDTO.setEstatus(new Integer(1));
		empleadoFirmadoDTO.setValidado(validado);
		empleadoFirmadoDTO.setModificadoPor(usuarioId);
		certificadoDAO.saveOrUpdate(empleadoFirmadoDTO);

		response.put("respuesta", Boolean.FALSE);
		response.put("message", "El certificado  no se pudo actualizar");

		if (certificadoVO != null) {
			response.put("respuesta", Boolean.TRUE);
			response.put("message", "El certificado se actualizó correctamente");

			response.put("certificado", certificadoVO);
		}
		return response;
	}

	/**
	 * @author javier07 {@inheritDoc}
	 */
	@Override
	@Transactional
	public CertificadoVO obtieneCertificadoPorPlaca(String placaOficial) {
		EmpleadosDTO empleadoDTO = empleadoDAO.getEmpleadoByPlaca(placaOficial);
		empleadoFirmadoDTO = certificadoDAO.obtieneCertificadoPorPlaca(empleadoDTO.getEmpId());
		CertificadoVO certificadoVO = null;
		if (empleadoFirmadoDTO != null) {
			certificadoVO = getCertificadoVO(empleadoFirmadoDTO);
		}
		return certificadoVO;
	}

	/**
	 * @author javier07 {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<CertificadoVO> obtieneCertificados(String tipoBusqueda, String paramBusqueda, String fechaInicio,
			String fechaFin, Integer validado) {
		List<CertificadoVO> listaCerficicadoVO;
		RutinasTiempoImpl utils = new RutinasTiempoImpl();
		Date fechaInicioDate = null;
		Date fechaFinDate = null;
		if (fechaInicio.equals("") || fechaInicio.equals("null")) {
			fechaInicio = null;

		}
		if (fechaFin.equals("") || fechaFin.equals("null")) {
			fechaFin = null;
//			if (fechaInicio != null)
//				fechaInicioDate = utils.convertirStringDate(fechaInicio.replace('-', '/'));

		}
		if (tipoBusqueda.equals("empCod")) {
			paramBusqueda = paramBusqueda.toLowerCase();

		}
		if (tipoBusqueda.equals("") | paramBusqueda.equals("null")) {
			tipoBusqueda = null;
			paramBusqueda = null;
		}

//		if (fechaInicio != null && fechaFin != null) {
//			fechaInicioDate = utils.convertirStringDate(fechaInicio.replace('-', '/'));
//			fechaFinDate = utils.convertirStringDate(fechaFin.replace('-', '/'));
//
//		} else if (fechaFin != null)
//			fechaFinDate = utils.convertirStringDate(fechaFin.replace('-', '/'));

		listaCerficicadoVO = getListCertificadosVO(certificadoDAO.obtieneCertificados(tipoBusqueda, paramBusqueda,
				fechaInicio, fechaFin, validado));

		return listaCerficicadoVO;
	}

	/**
	 * @author javier07 {@inheritDoc}
	 */
	public static List<CertificadoVO> getListCertificadosVO(List<EmpleadoFirmaDTO> listCertificadosDTO) {
		List<CertificadoVO> listCertificadoVO = new ArrayList<CertificadoVO>();

		for (EmpleadoFirmaDTO certificadoDTO : listCertificadosDTO) {
			listCertificadoVO.add(getCertificadoVO(certificadoDTO));
		}

		return listCertificadoVO;
	}

	public static EmpleadoVO getEmpleadoVO(EmpleadosDTO empleado) {

		EmpleadoVO empleadoVO = new EmpleadoVO();

		empleadoVO.setAgrpId(empleado.getAgrupamiento().getAgrupacionId());
		empleadoVO.setCreadoPor(empleado.getCreadoPor());
		empleadoVO.setEmpApeMaterno(empleado.getEmpApeMaterno());
		empleadoVO.setEmpApePaterno(empleado.getEmpApePaterno());
		empleadoVO.setEmpCod(empleado.getEmpCod());
		empleadoVO.setEmpId(empleado.getEmpId());
		empleadoVO.setEmpNombre(empleado.getEmpNombre());
		empleadoVO.setEmpPlaca(empleado.getEmpPlaca());
		empleadoVO.setEmpRFC(empleado.getEmpRFC());
		empleadoVO.setEmpPwd(empleado.getEmpPwd());
		empleadoVO.setEmpStatus(empleado.getEmpStatus());
		empleadoVO.setEmpTipId(empleado.getEmpTip().getEmpTipId());
		empleadoVO.setFechaCreacion(empleado.getFechaCreacion());
		empleadoVO.setModificadoPor(empleado.getModificadoPor());
		empleadoVO.setSecId(empleado.getSector().getSecId());
		empleadoVO.setUltimaModificacion(empleado.getUltimaModificacion());

		return empleadoVO;
	}

	public static EmpleadoFirmaDTO getCertificadoDTO(CertificadoVO certificadoVO) {

		EmpleadoFirmaDTO empleadoFirmaDTO = new EmpleadoFirmaDTO();

		empleadoFirmaDTO.setCertId(certificadoVO.getCertId() == null ? null : certificadoVO.getCertId());
		empleadoFirmaDTO.setCertEmitidoPara(
				certificadoVO.getCertEmitidoPara() == null ? null : certificadoVO.getCertEmitidoPara());
		empleadoFirmaDTO.setCertEmitidoPor(
				certificadoVO.getCertEmitidoPor() == null ? null : certificadoVO.getCertEmitidoPor());
		empleadoFirmaDTO.setCertNombre(certificadoVO.getCertNombre() == null ? null : certificadoVO.getCertNombre());
		empleadoFirmaDTO.setKeyNombre(certificadoVO.getKeyNombre() == null ? null : certificadoVO.getKeyNombre());
		empleadoFirmaDTO.setCertValidoDesde(
				certificadoVO.getCertValidoDesde() == null ? null : certificadoVO.getCertValidoDesde());
		empleadoFirmaDTO.setCertValidoHasta(
				certificadoVO.getCertValidoHasta() == null ? null : certificadoVO.getCertValidoHasta());
		empleadoFirmaDTO.setCertArchivo(
				arrayBytesToBlob(certificadoVO.getCertArchivo() == null ? null : certificadoVO.getCertArchivo()));
		empleadoFirmaDTO.setKeyArchivo(
				arrayBytesToBlob(certificadoVO.getKeyArchivo() == null ? null : certificadoVO.getKeyArchivo()));
		empleadoFirmaDTO.setEmpleadoDTO(
				getEmpleadoDTO(certificadoVO.getEmpleadoVO() == null ? null : certificadoVO.getEmpleadoVO()));
		;

		return empleadoFirmaDTO;
	}

	public static EmpleadosDTO getEmpleadoDTO(EmpleadoVO empleado) {

		EmpleadosDTO empleadoDTO = new EmpleadosDTO();

		AgrupamientosDTO agrupamiento = new AgrupamientosDTO();
		agrupamiento.setAgrupacionId(empleado.getAgrpId() == null ? null : empleado.getAgrpId());
		;
		empleadoDTO.setAgrupamiento(agrupamiento);
		empleadoDTO.setCreadoPor(empleado.getCreadoPor() == null ? null : empleado.getCreadoPor());
		empleadoDTO.setEmpApeMaterno(empleado.getEmpApeMaterno() == null ? null : empleado.getEmpApeMaterno());
		empleadoDTO.setEmpApePaterno(empleado.getEmpApePaterno() == null ? null : empleado.getEmpApePaterno());
		empleadoDTO.setEmpCod(empleado.getEmpCod() == null ? null : empleado.getEmpCod());
		empleadoDTO.setEmpId(empleado.getEmpId() == null ? null : empleado.getEmpId());
		empleadoDTO.setEmpNombre(empleado.getEmpNombre() == null ? null : empleado.getEmpNombre());
		empleadoDTO.setEmpPlaca(empleado.getEmpPlaca() == null ? null : empleado.getEmpPlaca());
		empleadoDTO.setEmpRFC(empleado.getEmpRFC() == null ? null : empleado.getEmpRFC());
		empleadoDTO.setEmpPwd(empleado.getEmpPwd() == null ? null : empleado.getEmpPwd());
		empleadoDTO.setEmpStatus(empleado.getEmpStatus() == null ? null : empleado.getEmpStatus());

		TipoEmpleadoDTO tipoEmpleadoDTO = new TipoEmpleadoDTO();
		tipoEmpleadoDTO.setEmpTipId(empleado.getEmpTipId() == null ? null : empleado.getEmpTipId());
		empleadoDTO.setEmpTip(tipoEmpleadoDTO);
		empleadoDTO.setFechaCreacion(empleado.getFechaCreacion() == null ? null : empleado.getFechaCreacion());
		empleadoDTO.setModificadoPor(empleado.getModificadoPor() == null ? null : empleado.getModificadoPor());

		SectorDTO sectorDTO = new SectorDTO();
		sectorDTO.setSecId(empleado.getSecId() == null ? null : empleado.getSecId());
		empleadoDTO.setSector(sectorDTO);
		empleadoDTO.setUltimaModificacion(
				empleado.getUltimaModificacion() == null ? null : empleado.getUltimaModificacion());

		return empleadoDTO;
	}

	public static CertificadoVO getCertificadoVO(EmpleadoFirmaDTO certificadoDTO) {

		CertificadoVO certificadoVO = new CertificadoVO();

		certificadoVO.setCertId(certificadoDTO.getCertId());
		certificadoVO.setCertEmitidoPara(certificadoDTO.getCertEmitidoPara());
		certificadoVO.setCertEmitidoPor(certificadoDTO.getCertEmitidoPor());
		certificadoVO.setCertNombre(certificadoDTO.getCertNombre());
		certificadoVO.setKeyNombre(certificadoDTO.getKeyNombre());
		certificadoVO.setCertValidoDesde(certificadoDTO.getCertValidoDesde());
		certificadoVO.setCertValidoHasta(certificadoDTO.getCertValidoHasta());
		certificadoVO.setCertArchivo(blobToArrayBytes(certificadoDTO.getCertArchivo()));
		certificadoVO.setKeyArchivo(blobToArrayBytes(certificadoDTO.getKeyArchivo()));
		certificadoVO.setEmpleadoVO(getEmpleadoVO(certificadoDTO.getEmpleadoDTO()));
		certificadoVO.setEstatusCertificado(certificadoDTO.getEstatus());
		certificadoVO.setValidado(certificadoDTO.getValidado());

		return certificadoVO;

	}

}
