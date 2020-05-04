package mx.com.teclo.saicdmx.negocio.service.administracion;

import java.text.ParseException;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.PerfilDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.cajas.VCajaExtDesactivarDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionalesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.RegionalesUPCVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminCajaParametrosVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminCajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminPerfilesParamVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminUsuarioClaveVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminUsuarioEstatusVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdscripcionVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.MenuAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.OperacionesExtemporaneasVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.PerfilesAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.UsuarioAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.CajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaExtDesactivarVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.ConsultaUsersVO;

/**
 * @author javier07
 */
public interface AdministracionService {

	/**
	 * @author Sail
	 * @param userId
	 * @param clave
	 * @param nuevaClave
	 * @param repetirClave
	 * @return
	 */
	AdminUsuarioClaveVO cambiarClaveAcceso(Long userId, String clave, String nuevaClave, String repetirClave);

	/**
	 * @author javier07
	 * @param tipoBusqueda
	 * @param valor
	 * @param isAdmin
	 * @param codigo
	 * @param isConsulta
	 * @return List<ConsultaUsersVO>
	 */
	List<ConsultaUsersVO> obtenerListaUsuarios(String tipoBusqueda, String valor, Boolean isAdmin, String codigo, Boolean isConsulta);

	/**
	 * @author javier07
	 * @return String
	 */
	String actualizarUsuario(UsuarioAdminVO usuarioAdminVO);

	/**
	 * @author javier07
	 * @return String
	 */
	String nuevoUsuario(UsuarioAdminVO usuarioAdminVO);

	/**
	 * @author javier07
	 * @return List<RegionalesDTO>
	 */
	List<RegionalesDTO> obtenerRegionales();

	/**
	 * @author javier07
	 * @return List<RegionalesUPCVO>
	 */
	List<RegionalesUPCVO> obtenerRegionales_UPC();

	/**
	 * @author javier07
	 * @param adscripcionVO
	 * @return String
	 */
	String nuevaAdscripcion(AdscripcionVO adscripcionVO);

	/**
	 * @author javier07
	 * @return List<DepositosDTO>
	 */
	List<DepositosDTO> obtieneDepositosActivos();

	/**
	 * @author javier07
	 * @param adminDepositoVO
	 * @return String
	 */
	String modificaDeposito(AdminDepositoVO adminDepositoVO);

	/**
	 * @author javier07
	 * @param adminUsuarioEstatusVO
	 * @return String
	 */
	String actualizaEstatus(AdminUsuarioEstatusVO adminUsuarioEstatusVO);

	/**
	 * @author javier07
	 * @param numCaja
	 * @return List<AdminCajaVO>
	 */
	List<AdminCajaVO> consultaCaja(String numCaja);

	/**
	 * @author javier07
	 * @param adminCajaParametrosVO
	 * @return String
	 */
	String modificaCaja(AdminCajaParametrosVO adminCajaParametrosVO);

	/* COMIENZA SOPORTE OPERACION */

	/**
	 * @author javier07
	 * @return List<PerfilDTO>
	 */
	List<PerfilDTO> obtieneListaPerfiles();

	/***
	 * @author Jesus Gutierrez
	 * @param codigoPerfil
	 * @return
	 */
	List<PerfilDTO> listaPerfilesByPerfilUsuario(String codigoPerfil);

	/**
	 * @author javier07
	 * @param tipo
	 * @param id
	 * @return List<MenuAdminVO>
	 */
	List<MenuAdminVO> obtieneListaMenus(String tipo, Long id);

	/**
	 * @author javier07
	 * @param parametros
	 * @return String
	 */
	String crudPerfiles(AdminPerfilesParamVO parametros);

	CajaVO bucarCajaOperacionesExtemporaneas(String valor, String tipoBusqueda);

	void habilitarExtemporanea(OperacionesExtemporaneasVO operacionesExtemporaneasVO) throws ParseException;

	void desabilitarExtemporanea(VCajaExtDesactivarVO vCajaExtDesactivarVO) throws ParseException;

	public List<VCajaExtDesactivarDTO> buscarCajaExtDesactivar();
	
	
	 public List<PerfilesAdminVO> getPerfilByCdApp();
}
