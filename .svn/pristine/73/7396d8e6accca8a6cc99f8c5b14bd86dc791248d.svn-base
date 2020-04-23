package mx.com.teclo.saicdmx.negocio.service.administracion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitProcDeshabilitarExtemporanea;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitProcHabilitarExtemporanea;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSoporteBitacora;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpAdminPerfilWeb;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpAdminUsuarioCaja;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpAdminUsuarioDeposito;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpAdminUsuarioEstatus;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpAdminUsuarioModif;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpAdminUsuarioNuevo;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpCambioAdscNuevo;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitTrBitacOpeExtemporanea;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitTrgSoporteBitacora;
import mx.com.teclo.saicdmx.bitacora.cambios.cajas.BitTrBitacCajas;
import mx.com.teclo.saicdmx.bitacora.cambios.empleados.BitTrBitacEmpleados;
import mx.com.teclo.saicdmx.bitacora.cambios.infracciones.BitTrBitUpInfrac;
import mx.com.teclo.saicdmx.bitacora.cambios.ingresos.BitTrBitacIngresos;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.administracion.OperacionExtDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.caja.CajaExtDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.ConceptosSoporteDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.DepositoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.RegionalesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado.EmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.perfil.PerfilDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.OperacionExtDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.PerfilDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.cajas.VCajaExtDesactivarDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionalesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.RegionalesUPCVO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.AdminPerfilesAdminMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.AdscripcionUsuarioMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.AltaUsuarioMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.CajaUsuarioAdminMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.ConsultaUsuarios_AdminMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.DepositoAdmin_MyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.MenuAdminMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.ModificaCajaUsuarioMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.ModificaUsuarioMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.OperacionesExtemporaneasMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.RegionalUsuarioMyBtisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.SoporteOperacionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.UsuarioAdminEstatusMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.cajas.CajaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.infracciones.InfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminCajaParametrosVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminCajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminPerfilesParamVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminUsuarioClaveVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminUsuarioEstatusVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdscripcionVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.EjecutaSoporteOperacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.MenuAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.OperacionesExtemporaneasVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.PerfilesAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.SoporteEmbargoConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.SoporteEmbargoVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.SoporteFoliosVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.SoporteOperacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.UsuarioAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.CajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VBuscarCorteCaja;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaExtDesactivarVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.ConsultaUsersVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.EmpleadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.empleados.EmpleadosPorPlacaVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.ingresos.IngresosVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enumerados.CodigoPerfilesEnum;
import mx.com.teclo.saicdmx.util.enumerados.ConceptosSoporteOperacion;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

/**
 * @author javier07
 */
@Service
public class AdministracionServiceImpl implements AdministracionService {

	@Value("${app.config.codigo}")
	private String codeApplication;

	@Autowired
	private ConsultaUsuarios_AdminMyBatisDAO consultaMyBatisDAO;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private ModificaUsuarioMyBatisDAO modificaUsuarioMyBatisDAO;
	@Autowired
	private AltaUsuarioMyBatisDAO altaUsuarioMyBatisDAO;
	@Autowired
	private RegionalesDAO regionalesDAO;
	@Autowired
	private RegionalUsuarioMyBtisDAO regionalUsuarioMyBtisDAO;
	@Autowired
	private AdscripcionUsuarioMyBatisDAO adscripcionUsuarioMyBatisDAO;
	@Autowired
	private UsuarioAdminEstatusMyBatisDAO usuarioAdminEstatusMyBatisDAO;
	@Autowired
	private DepositoDAO depositoDAO;
	@Autowired
	private DepositoAdmin_MyBatisDAO depositoMyBatisDAO;
	@Autowired
	private CajaUsuarioAdminMyBatisDAO cajaUsuarioAdminMyBatisDAO;
	@Autowired
	private ModificaCajaUsuarioMyBatisDAO modificaCajaUsuarioMyBatisDAO;
	@Autowired
	private SoporteOperacionMyBatisDAO soporteOperacionMyBatisDAO;

	@Autowired
	private MenuAdminMyBatisDAO menuAdminMyBatisDAO;
	@Autowired
	private PerfilDAO perfilDAO;
	@Autowired
	private AdminPerfilesAdminMyBatisDAO crudPerfilesMybatisDAO;
	@Autowired
	private EmpleadoDAO empleadoDAO;
	@Autowired
	private ConceptosSoporteDAO conceptosSoporteDAO;

	@Autowired
	private OperacionesExtemporaneasMyBatisDAO operacionesExtemporaneasMyBatisDAO;
	@Autowired
	private CajaExtDAO cajaExtDAO;
	@Autowired
	private CajaMyBatisDAO cajaMyBatisDAO;
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	@Autowired
	private BitSpAdminUsuarioNuevo bitSpAdminUsuarioNuevo;
	@Autowired
	private BitSpAdminUsuarioEstatus bitSpAdminUsuarioEstatus;
	@Autowired
	private BitSpAdminUsuarioDeposito bitSpAdminUsuarioDeposito;
	@Autowired
	private BitSpAdminUsuarioCaja bitSpAdminUsuarioCaja;

	@Autowired
	private BitTrBitacCajas bitTrBitacCajas;
	@Autowired
	private BitTrBitacEmpleados bitTrBitacEmpleados;
	@Autowired
	private BitSpCambioAdscNuevo bitSpCambioAdscNuevo;
	@Autowired
	private BitSpAdminPerfilWeb bitSpAdminPerfilesWeb;
	@Autowired
	private BitSpAdminUsuarioModif bitSpAdminUsuarioModif;
	@Autowired
	private BitProcHabilitarExtemporanea habilitarOperacionExt;
	@Autowired
	private BitProcDeshabilitarExtemporanea deshabilitarOperacionExt;
	@Autowired
	private BitTrBitacOpeExtemporanea bitTrBitacOpeExtemporanea; 
	@Autowired
	private BitSoporteBitacora bitSoporteBitacora;
	@Autowired
	private BitTrgSoporteBitacora bitTrgSoporteBitacora;
	@Autowired
	private BitTrBitUpInfrac bitTrBitUpInfrac;
	@Autowired
	private BitTrBitacIngresos bitTrBitacIngresos;
	@Autowired
	private OperacionExtDAO operacionExtDAO;
	@Autowired
	private EmpleadoService empleadoService;
	

	
	@Autowired
	private InfraccionMyBatisDAO infraccionMyBatisDAO;
	
	
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdminUsuarioClaveVO cambiarClaveAcceso(Long userId, String clave, String nuevaClave, String repetirClave) {
		AdminUsuarioClaveVO adminClaveVO = new AdminUsuarioClaveVO();
		adminClaveVO.setUsuario_id(userId);
		adminClaveVO.setVieja_contra(clave);
		adminClaveVO.setNueva_contra(nuevaClave);
		adminClaveVO.setRepetir_contra(repetirClave);
		modificaUsuarioMyBatisDAO.cambiarClaveAcceso(adminClaveVO);
		if(adminClaveVO.getResultado() == 0) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
					2L,	
					2L, 
					"*****", 
					"*****", 
					adminClaveVO.getUsuario_id() != null ? Long.valueOf(adminClaveVO.getUsuario_id().toString()) : 0, 
					adminClaveVO.getUsuario_id() != null ? adminClaveVO.getUsuario_id().toString() : "", 
					"",  
					ParametrosBitacoraEnum.ORIGEN_W.getParametro() // Origen W / H
				);
					
		}
		return adminClaveVO;
	}

	@Override
	public List<ConsultaUsersVO> obtenerListaUsuarios(String tipoBusqueda, String valor, Boolean isAdmin, String codigo, Boolean isConsulta) {
		List<ConsultaUsersVO> listaConsultaUsers = null;

		if (tipoBusqueda.equals("emp_placa")) {
			valor = valor != null ? valor.toLowerCase() : valor;
			
			if(isAdmin){
				listaConsultaUsers = consultaMyBatisDAO.obtieneListaUsuariosPlaca(valor, codeApplication);
			}else{
				listaConsultaUsers = consultaMyBatisDAO.obtieneListaUsuariosPlaca_Activos(valor, codeApplication);
			}
		}

		if (tipoBusqueda.equals("emp_ape_paterno")) {
			valor = valor != null ? valor.toUpperCase() : valor;

			if(isAdmin){
				listaConsultaUsers = consultaMyBatisDAO.obtieneListaUsuariosAPaterno(valor, codeApplication);
			}else{
				listaConsultaUsers = consultaMyBatisDAO.obtieneListaUsuariosAPaterno_Activos(valor, codeApplication);
			}
		}
		
		if (tipoBusqueda.equals("emp_nombre")) {
			valor = valor != null ? valor.toUpperCase() : valor;
			if(isAdmin){
				listaConsultaUsers = consultaMyBatisDAO.obtieneListaUsuariosNombre(valor, codeApplication);
			}else{
				listaConsultaUsers = consultaMyBatisDAO.obtieneListaUsuariosNombre_Activos(valor, codeApplication);
			}
		}
		
		if (tipoBusqueda.equals("perfil_id")) {
			if(isAdmin){
				listaConsultaUsers = consultaMyBatisDAO.obtieneListaUsuariosPerfil(valor, codeApplication);
			}else{
				listaConsultaUsers = consultaMyBatisDAO.obtieneListaUsuariosPerfil_Activos(valor, codeApplication);
			}
		}
		
		if (tipoBusqueda.equals("emp_id")) {
			if(isAdmin){
				listaConsultaUsers = consultaMyBatisDAO.obtieneListaUsuariosEmpId(valor, codeApplication);
			}else{
				listaConsultaUsers = consultaMyBatisDAO.obtieneListaUsuariosEmpId_Activos(valor, codeApplication);
			}
		}
		
		if(isConsulta){
			if (!listaConsultaUsers.isEmpty()) {
				String emptyString = "";
				for (Iterator<ConsultaUsersVO> iterator = listaConsultaUsers.iterator(); iterator.hasNext();){
					ConsultaUsersVO user = (ConsultaUsersVO) iterator.next();
					
					if(codigo.equals(CodigoPerfilesEnum.SSP_ADMINISTRADOR.getCodigo())){
						if(user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_ADMINISTRADOR.getCodigo()) || 
								user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_OPERACION.getCodigo())  ||
										user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_CONTACT_CENTER.getCodigo()) || 
												user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_SOPORTE_ESPECIAL.getCodigo())){
							
							iterator.remove();
							continue;
						}
					}else if(codigo.equals(CodigoPerfilesEnum.TCL_SOPORTE_ESPECIAL.getCodigo())){
						if(!user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_ADMINISTRADOR.getCodigo()) &&
								!user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_OPERACION.getCodigo()) &&
									!user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_CONTACT_CENTER.getCodigo()) &&
										!user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_SOPORTE_ESPECIAL.getCodigo()) &&
											!user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_SUPERVISOR.getCodigo())){
							iterator.remove();
							continue;
						}
					}
					
					if(codigo.equals(CodigoPerfilesEnum.SSP_ADMINISTRADOR.getCodigo()) || 
							codigo.equals(CodigoPerfilesEnum.TCL_ADMINISTRADOR.getCodigo()) ||
								codigo.equals(CodigoPerfilesEnum.TCL_OPERACION.getCodigo()) && 
									!user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_ADMINISTRADOR.getCodigo()) &&
										!user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_OPERACION.getCodigo()) &&
											!user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_CONTACT_CENTER.getCodigo()) &&
												!user.getCd_perfil().equals(CodigoPerfilesEnum.TCL_SOPORTE_ESPECIAL.getCodigo())){
						
						user.setReg_nombre(user.getReg_nombre() == null ? emptyString : user.getReg_nombre());
						user.setUpc_nombre(user.getUpc_nombre() == null ? emptyString : user.getUpc_nombre());
			
						if ((user.getReg_nombre().isEmpty() && user.getUpc_nombre().isEmpty())) {
							user.setAreaOperativa("SIN ÁREA OPERATIVA");
						} else {
							user.setAreaOperativa(user.getReg_nombre() + "-" + user.getUpc_nombre());
						}
						user.setEstatus(user.getEstatus().equals("A") ? "Habilitado" : "Deshabilitado");
					}else{
						iterator.remove();
					}
				}
			}
		}
		
		return listaConsultaUsers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String actualizarUsuario(UsuarioAdminVO usuarioAdminVO) {
		
		List<BitacoraCambiosVO> lBitCambios= new ArrayList<>();
		EmpleadoVO voEmplOld;
		// Consulta de información de la caja del usuario, si tiene asignada una y si es de usuario de tipo HandHeld...
		VCajaConsultaVO voCajaOld  = null;		
		if (usuarioAdminVO.getCaja_id() > 0)
			voCajaOld = modificaCajaUsuarioMyBatisDAO.getDatosCaja(usuarioAdminVO.getCaja_id());

		//Consulta informción del empleado antes de ser actualizada ...
		EmpleadosDTO empl = empleadoService.getEmpleadoByPlaca(usuarioAdminVO.getEmp_placa());
		voEmplOld = ResponseConverter.copiarPropiedadesFull(empl, EmpleadoVO.class);
		voEmplOld.setEmpTipId(empl.getEmpTip().getEmpTipId());
		//Se obtiene la bitacora de la modificación del usuario ...
		lBitCambios.addAll(bitSpAdminUsuarioModif.bitacoraAdminModifUsuario(usuarioAdminVO, voEmplOld));
		modificaUsuarioMyBatisDAO.modficarUsuario(usuarioAdminVO);
		
		// se aplica bitacora si se aplico los cambios al usuario ...
		if(usuarioAdminVO.getResultado() == 0){
			EmpleadosDTO emplea2 = empleadoService.getEmpleadoByPlaca(usuarioAdminVO.getEmp_placa());
			EmpleadoVO voEmplNew = ResponseConverter.copiarPropiedadesFull(emplea2, EmpleadoVO.class);
			voEmplNew.setModificadoPor(usuarioAdminVO.getModificadoPor());
			voEmplNew.setEmpTipId(emplea2.getEmpTip().getEmpTipId());
			lBitCambios.addAll(bitTrBitacEmpleados.bitModificacionEmpleados(voEmplOld, voEmplNew));
			
			if(voCajaOld != null){
				VCajaConsultaVO voCajaNew =  modificaCajaUsuarioMyBatisDAO.getDatosCaja(usuarioAdminVO.getCaja_id());
				voCajaNew.setModificadoPor(usuarioAdminVO.getModificadoPor().toString());
				voCajaNew.setCajaIdD(usuarioAdminVO.getCaja_id().toString());
				try {
					lBitCambios.addAll(bitTrBitacCajas.guardarCambiosBitacora(voCajaNew, voCajaOld));			
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			bitacoraCambiosService.guardarListaBitacoraCambios(lBitCambios);
						
		}
		
		return usuarioAdminVO.getMensaje();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String nuevoUsuario(UsuarioAdminVO usuarioAdminVO) {

		String realizaCobro = "N";
		// Se coloca el numero de empleado 13 aquellos que no pertenezcan a
		// Handheld o Cajero
		int tipoEmpleado = 13;
		if (usuarioAdminVO.getCd_perfil().equals(CodigoPerfilesEnum.HANDHELD.getCodigo())) {// Si el perfil es handheld usuarioAdminVO.getPerfilWebNuevo() == 20
			tipoEmpleado = usuarioAdminVO.getTipOficialNuevo();

		} else if (usuarioAdminVO.getCd_perfil().equals(CodigoPerfilesEnum.CAJERO.getCodigo())) {// Si el perfil es cajero usuarioAdminVO.getPerfilWebNuevo() == 12
			tipoEmpleado = usuarioAdminVO.getTipOficialNuevo();
			realizaCobro = "S";
		}
		usuarioAdminVO.setEmp_rfc(usuarioAdminVO.getEmp_rfc() == null ? " " : usuarioAdminVO.getEmp_rfc());
		usuarioAdminVO.setCobroNuevo(realizaCobro);
		usuarioAdminVO.setTipOficialNuevo(tipoEmpleado);
		
		usuarioAdminVO.setCd_aplicacion(codeApplication);

		List<BitacoraCambiosVO> listBit = bitSpAdminUsuarioNuevo.insertBitacNuevoUsuario(usuarioAdminVO);
		
		altaUsuarioMyBatisDAO.nuevoUsuario(usuarioAdminVO);
		if(usuarioAdminVO.getResultado() == 0) {
			bitacoraCambiosService.guardarListaBitacoraCambios(listBit);
		}
		return usuarioAdminVO.getMensaje();
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<RegionalesDTO> obtenerRegionales() {

		return regionalesDAO.buscarRegionales();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RegionalesUPCVO> obtenerRegionales_UPC() {
		return regionalUsuarioMyBtisDAO.obtieneRegiones_UPC();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String nuevaAdscripcion(AdscripcionVO adscripcionVO) {

		String paramValues = adscripcionVO.getAreaIdParametros();
		// obtener infraccion
		String v_regional = paramValues.substring(0, paramValues.indexOf("-"));
		// obtener placa
		String v_area = paramValues.substring(paramValues.indexOf("-") + 1);
		adscripcionVO.setP_regionalid(Integer.parseInt(v_regional));
		adscripcionVO.setP_areaid(Integer.parseInt(v_area));

		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("hh:mm");
		String hora = formateador.format(ahora).toString();
		adscripcionVO.setP_fecha(adscripcionVO.getP_fecha() + " " + hora);

		adscripcionUsuarioMyBatisDAO.nuevaAdscripcion(adscripcionVO);
		if(!adscripcionVO.getP_resultado().equals("-1"))
			bitacoraCambiosService.guardarBitacoraCambios(bitSpCambioAdscNuevo.insertarBitacoraAdscripcionNuevo(adscripcionVO.getP_resultado(), adscripcionVO.getP_folio(), adscripcionVO.getP_empid_modif()));
		
		return adscripcionVO.getP_mensaje();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<DepositosDTO> obtieneDepositosActivos() {
		return depositoDAO.obtenerDepositosActivos();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String modificaDeposito(AdminDepositoVO adminDepositoVO) {

		String tipoOperacion = "SIN_OPERACION";
		if (!adminDepositoVO.getActivaDeposito().equals("null")) {

			if (adminDepositoVO.getActivaDeposito().equals("C")) {
				tipoOperacion = "C";
			} else if (adminDepositoVO.getActivaDeposito().equals("E")) {
				tipoOperacion = "E";
			}
		}

		if (!adminDepositoVO.getAsignaDeposito().equals("null")) {

			if (adminDepositoVO.getAsignaDeposito().equals("A")) {
				tipoOperacion = "A";
			}
		}
		adminDepositoVO.setP_operacion(tipoOperacion);
		adminDepositoVO.setNombreDep(depositoMyBatisDAO.getDepNombreOld(adminDepositoVO.getP_emp_id()));
		
		List<BitacoraCambiosVO> listBit = new ArrayList<>();
		//obtención de la información de la caja del usuario, previa a los cambios ...
		VCajaConsultaVO cajaOld = null;
		if (adminDepositoVO.getP_emp_caja_id() > 0)
			cajaOld	=  modificaCajaUsuarioMyBatisDAO.getDatosCaja(new Long(adminDepositoVO.getP_emp_caja_id()));
		listBit.addAll(bitSpAdminUsuarioDeposito.insertBitacNuevoUsuario(adminDepositoVO));

		
		depositoMyBatisDAO.modificaDeposito(adminDepositoVO);
		
		if(adminDepositoVO.getP_resultado() == 0) {
			if(adminDepositoVO.getP_emp_caja_id() > 0){
				VCajaConsultaVO cajaNew =  modificaCajaUsuarioMyBatisDAO.getDatosCaja(new Long(adminDepositoVO.getP_emp_caja_id()));
				cajaNew.setModificadoPor(adminDepositoVO.getP_modificado_por().toString());
				cajaNew.setCajaIdD(String.valueOf(adminDepositoVO.getP_emp_caja_id()));
				try {
					listBit.addAll(bitTrBitacCajas.guardarCambiosBitacora(cajaNew, cajaOld));			
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			bitacoraCambiosService.guardarListaBitacoraCambios(listBit);
		}
		return adminDepositoVO.getP_mensaje();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String actualizaEstatus(AdminUsuarioEstatusVO adminUsuarioEstatusVO) {

		String tipo = "";

		if (adminUsuarioEstatusVO.getTipo_accion().equals("Habilitado")) {
			tipo = "D";
		} else if (adminUsuarioEstatusVO.getTipo_accion().equals("Deshabilitado")) {

			tipo = "H";
		} else if (adminUsuarioEstatusVO.getTipo_accion().equals("Restablecer")) {
			tipo = "R";
		}

		adminUsuarioEstatusVO.setTipo_accion(tipo);
		adminUsuarioEstatusVO.setEstatus(usuarioAdminEstatusMyBatisDAO.getPlaca(adminUsuarioEstatusVO.getUsuario_id()));		

		List<BitacoraCambiosVO> lBitCambios = bitSpAdminUsuarioEstatus.insertCambioEstatus(adminUsuarioEstatusVO);

		//obtención de la información de la caja del usuario, previa a los cambios ...
		VCajaConsultaVO cajaOld = null;
		Long cajaExiste = null;
		if(tipo.equals("D")){
			cajaExiste = usuarioAdminEstatusMyBatisDAO.getCajaExiste(adminUsuarioEstatusVO.getUsuario_id());
			if(cajaExiste != null && cajaExiste > 0)
				cajaOld	=  modificaCajaUsuarioMyBatisDAO.getDatosCaja(cajaExiste);
		}
		
		usuarioAdminEstatusMyBatisDAO.modficarEstatusUsuario(adminUsuarioEstatusVO);		
		if (adminUsuarioEstatusVO.getResult_out().equals("0")) {// Si el mismo usuario se deshabilita se invalida la sesión //
		    
			//obtención de la información de la caja del usuario despues de los cambios ...
			if(cajaOld != null){
				VCajaConsultaVO cajaNew =  modificaCajaUsuarioMyBatisDAO.getDatosCaja(cajaExiste);
				cajaNew.setModificadoPor(adminUsuarioEstatusVO.getModificado_por_id().toString());
				cajaNew.setCajaIdD(cajaExiste.toString());
				try {
					lBitCambios.addAll(bitTrBitacCajas.guardarCambiosBitacora(cajaNew, cajaOld));			
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			bitacoraCambiosService.guardarListaBitacoraCambios(lBitCambios);
			if (adminUsuarioEstatusVO.getModificado_por_id().equals(adminUsuarioEstatusVO.getUsuario_id())
					&& tipo.equals("D")) {
				return "0";
				// session.invalidate();
			}
		}
		return adminUsuarioEstatusVO.getMessage_out();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AdminCajaVO> consultaCaja(String numCaja) {
		return cajaUsuarioAdminMyBatisDAO.consultaCaja(numCaja, codeApplication);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String modificaCaja(AdminCajaParametrosVO adminCajaParametrosVO) {

		String PERFIL_HANDHELD = "20";
		String tipoOperacion = "SIN_OPERACION";
		if (adminCajaParametrosVO.getActivaCaja() != null) {

			if (adminCajaParametrosVO.getActivaCaja().equals("C")) {
				tipoOperacion = "C";
			} else if (adminCajaParametrosVO.getActivaCaja().equals("E")) {
				tipoOperacion = "E";
			}
		}

		if (adminCajaParametrosVO.getAsignaCaja() != null) {

			if (adminCajaParametrosVO.getAsignaCaja().equals("A")) {
				tipoOperacion = "A";
			}

		}
		String perfil = tipoOperacion.equals("E") ? "" : adminCajaParametrosVO.getP_perfil_id().toString();
		String realizaCobro = "S";
		if (perfil.equals(PERFIL_HANDHELD)) {// Si el perfil es handheld
			realizaCobro = adminCajaParametrosVO.getP_emp_puede_cobrar() == null ? "N"
					: adminCajaParametrosVO.getP_emp_puede_cobrar();
		}
		adminCajaParametrosVO.setP_emp_puede_cobrar(realizaCobro);
		adminCajaParametrosVO.setP_operacion(tipoOperacion);
		
		List<BitacoraCambiosVO> lBitCambios = new ArrayList<>();
		lBitCambios.addAll(bitSpAdminUsuarioCaja.insertCambioUsuarioCaja(adminCajaParametrosVO));

		//obtención de la información de la caja de usuario y asignación, previa a los cambios ...
		VCajaConsultaVO cajaOld = null, cajaEmpOld = null;
		if(adminCajaParametrosVO.getP_caja_id() != null && adminCajaParametrosVO.getP_caja_id() > 0)
			cajaOld	=  modificaCajaUsuarioMyBatisDAO.getDatosCaja(adminCajaParametrosVO.getP_caja_id());
		if(adminCajaParametrosVO.getP_emp_caja_id() != null && adminCajaParametrosVO.getP_emp_caja_id() > 0)
			cajaEmpOld = modificaCajaUsuarioMyBatisDAO.getDatosCaja(adminCajaParametrosVO.getP_emp_caja_id());
		
		modificaCajaUsuarioMyBatisDAO.modficarCaja(adminCajaParametrosVO);
		
		//Validacion de respuesta : '0' se omite, ya que el store procedure realiza los cambios y bitacora aun si ocurre errores ...
		if(adminCajaParametrosVO.getP_resultado() == 0) {

		//obtención de la información de la caja de usuario y asignacion, despues de los cambios y revisión de cambios ...
		if(adminCajaParametrosVO.getP_caja_id() != null && adminCajaParametrosVO.getP_caja_id() > 0){
			VCajaConsultaVO cajaNew =  modificaCajaUsuarioMyBatisDAO.getDatosCaja(adminCajaParametrosVO.getP_caja_id());
			cajaNew.setModificadoPor(adminCajaParametrosVO.getP_modificado_por().toString());
			cajaNew.setCajaIdD(adminCajaParametrosVO.getP_caja_id().toString());
			try {
				lBitCambios.addAll(bitTrBitacCajas.guardarCambiosBitacora(cajaNew, cajaOld));			
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(adminCajaParametrosVO.getP_emp_caja_id() != null && adminCajaParametrosVO.getP_emp_caja_id() > 0){
			VCajaConsultaVO cajaEmpNew =  modificaCajaUsuarioMyBatisDAO.getDatosCaja(adminCajaParametrosVO.getP_emp_caja_id());
			cajaEmpNew.setModificadoPor(adminCajaParametrosVO.getP_modificado_por().toString());
			cajaEmpNew.setCajaIdD(adminCajaParametrosVO.getP_emp_caja_id().toString());
			try {
					lBitCambios.addAll(bitTrBitacCajas.guardarCambiosBitacora(cajaEmpNew, cajaEmpOld));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		bitacoraCambiosService.guardarListaBitacoraCambios(lBitCambios);
		}
		
		return adminCajaParametrosVO.getP_mensaje();
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public EmpleadosPorPlacaVO buscaAutorizacion(Integer conceptoId, String placa) {
		if (conceptoId == ConceptosSoporteOperacion.USUARIOS_ASIGNAR_FOLIOS) {
			return soporteOperacionMyBatisDAO.buscaAutorizacionOficialAsignarFolios(placa);
		}

		return soporteOperacionMyBatisDAO.buscaAutorizacionOficial(placa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<PerfilDTO> obtieneListaPerfiles() {
		return perfilDAO.obtieneListaPerfilesTCLAdministrador(codeApplication);
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<PerfilDTO> listaPerfilesByPerfilUsuario(String codigoPerfil) {
		List<PerfilDTO> lista = new ArrayList<PerfilDTO>();
		if (codigoPerfil.equals(CodigoPerfilesEnum.TCL_ADMINISTRADOR.getCodigo())) {
			lista = perfilDAO.obtieneListaPerfilesTCLAdministrador(codeApplication);
		} else if (codigoPerfil.equals(CodigoPerfilesEnum.TCL_SOPORTE_ESPECIAL.getCodigo())) {
			lista = perfilDAO.obtieneListaPerfilesTCLSoporteEspecial(codeApplication);
		} else {
			lista = perfilDAO.obtieneListaPerfiles(codeApplication);
		}

		return lista;
	}

	/***
	 * {@inheritDoc}
	 * @throws ParseException 
	 */
	@Override
	public EjecutaSoporteOperacionVO ejecutarSoporteOperacion(SoporteOperacionVO objeto, Long usuario) throws ParseException {
				
		List<BitacoraCambiosVO> listBitCambios = new ArrayList<>();
		
		EjecutaSoporteOperacionVO execValues = new EjecutaSoporteOperacionVO();
		execValues.setUsuarioId(usuario);
		execValues.setInfraccionNum(objeto.getInfraccionNum());
			
		execValues.setFechaHora(objeto.getFechaHora());
		execValues.setInfraccionPreImpresa(objeto.getInfraccionPreImpresa());
		execValues.setInfraccionNumNueva(objeto.getInfraccionNumNueva());

		execValues.setNciNuevo(objeto.getNciNuevo());
		execValues.setInfraccionPlaca(objeto.getInfraccionPlaca());
		execValues.setLstCausaIngreso(objeto.getLstCausaIngreso());
		execValues.setLstTipoIngreso(objeto.getLstTipoIngreso());

		execValues.setNumOficio(objeto.getNumOficio());
		execValues.setOficialPlaca(objeto.getOficialPlaca());
		execValues.setResguardo(objeto.getResguardo());
		execValues.setFolioInicial(objeto.getFolioInicial());
		execValues.setFolioFinal(objeto.getFolioFinal());
		execValues.setReciboTotal(objeto.getReciboTotal());
		execValues.setReciboUtilizados(objeto.getReciboUtilizados());
		execValues.setReciboCancelados(objeto.getReciboCancelados());
		execValues.setTipoSoporte(objeto.getTipoSoporte());
		execValues.setOperacionEfectuada(objeto.getOperacionEfectuada());
		execValues.setEmpIdFolios(objeto.getEmpIdFolios());
		execValues.setUsuarioAutoriza(objeto.getUsuarioAutoriza());
		
		IngresosVO oldIngresos =  null;
		VSSPInfracConsGralFVO oldInfraccion = null;
		VSSPInfracConsGralFVO oldInfraccionDig = null;
		VCajaConsultaVO oldCaja  = null; 
		Long cajaId = null;
		
		/* Consulta de información en la tabla de Infracciones antes de realizar cambios por el sp sp_admin_soporte_operacion*/
		if(objeto.getTipoSoporte() == 1 || objeto.getTipoSoporte() == 3 || objeto.getTipoSoporte() == 4 ||
				objeto.getTipoSoporte() == 5 ||	objeto.getTipoSoporte() == 6 ||	objeto.getTipoSoporte() == 8 ||
				objeto.getTipoSoporte() == 9 ||	objeto.getTipoSoporte() == 10 || objeto.getTipoSoporte() == 11 ||
				objeto.getTipoSoporte() == 18)
			oldInfraccion = infraccionMyBatisDAO.getDatosInfraccionParaBitacora(objeto.getInfraccionNum());
		
		/* Consulta de información en la tabla de Ingresos antes de realizar cambios por el sp sp_admin_soporte_operacion*/
		if(objeto.getTipoSoporte() == 7 || objeto.getTipoSoporte() == 9 || objeto.getTipoSoporte() == 12 ||
				objeto.getTipoSoporte() == 13 || objeto.getTipoSoporte() == 15 ||
				objeto.getTipoSoporte() == 16 || objeto.getTipoSoporte() == 18)
			oldIngresos = soporteOperacionMyBatisDAO.getInformacionIngreso(objeto.getInfraccionNum());

		/* Consulta de información en la tabla de Infracciones_Digitalizacion antes de realizar cambios por el sp sp_admin_soporte_operacion*/
		else if(objeto.getTipoSoporte() == 11 && oldInfraccion == null)
			oldInfraccionDig = infraccionMyBatisDAO.getDatosInfraccionParaBitacora(objeto.getInfraccionNum());
		
		/* Consulta de información en la tabla de Cajas antes de realizar cambios por el sp sp_admin_soporte_operacion*/
		else if(objeto.getTipoSoporte() == 19 && objeto.getEmpIdFolios() != null){
				cajaId = modificaCajaUsuarioMyBatisDAO.getCajaIdFromEmpId(objeto.getEmpIdFolios().longValue());
				if(cajaId != null)
					oldCaja = modificaCajaUsuarioMyBatisDAO.getDatosCaja(cajaId);			
		}
		
		soporteOperacionMyBatisDAO.EjecutarSoporteOperacion(execValues);
		
		if(!execValues.getResultado().equals("-1")){
			
			try{
				/* Cambio del numero de Infracción en el caso de tipo soporte 9, puesto que se le asigna un nuevo numero de infraccion*/
				if(objeto.getTipoSoporte() == 9)
					objeto.setInfraccionNum(objeto.getInfraccionNumNueva());
				
				/* Consulta de información en la tabla de Infracciones despues de realizar cambios por el sp sp_admin_soporte_operacion,
				 *  para poder generar bitacora de cambios */
				if(oldInfraccion!=null){
					VSSPInfracConsGralFVO newInfraccion = infraccionMyBatisDAO.getDatosInfraccionParaBitacora(objeto.getInfraccionNum());
					newInfraccion.setModificadoPor(usuario.intValue());
//					newInfraccion.setAutorizaId(objeto.getUsuarioAutoriza());
					listBitCambios.addAll(bitTrBitUpInfrac.guardarCambiosBitacora(newInfraccion, oldInfraccion));
				}
				
				/* Consulta de información en la tabla de Ingresos despues de realizar cambios por el sp sp_admin_soporte_operacion,
				 *  para poder generar bitacora de cambios */
				if(oldIngresos!=null){
					if(objeto.getTipoSoporte() == 12 || objeto.getTipoSoporte() == 18)
						objeto.setInfraccionNum(objeto.getInfraccionNum().substring(1));
					
					IngresosVO newIngresos = soporteOperacionMyBatisDAO.getInformacionIngreso(objeto.getInfraccionNum());
					newIngresos.setInfracNum(objeto.getInfraccionNum());
					listBitCambios.addAll(bitTrBitacIngresos.generarBitacoraIngresos(oldIngresos, newIngresos));
				}
				
				/* Consulta de información en la tabla de Cajas_empleados despues de realizar cambios por el sp sp_admin_soporte_operacion,
				 *  para poder generar bitacora de cambios */
				if(oldCaja != null){
					VCajaConsultaVO newCaja =  modificaCajaUsuarioMyBatisDAO.getDatosCaja(cajaId);
					newCaja.setModificadoPor(usuario.toString());
					newCaja.setCajaIdD(cajaId.toString());
					listBitCambios.addAll(bitTrBitacCajas.guardarCambiosBitacora(newCaja, oldCaja));			
					
				}
				
				/* Generación de bitacora de cambios al realizar cambios en la tabla de Infracciones Digitalizacion*/
				if(execValues.getTipoSoporte() == 11 && oldInfraccionDig != null){
					listBitCambios.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							5L,
							2L,
							oldInfraccionDig.getVehiculoPlaca(),
							objeto.getInfraccionPlaca(),
							objeto.getOficialPlaca() != null ? new Long(objeto.getOficialPlaca()) : 0L,
							objeto.getInfraccionNum().toString(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));			
					listBitCambios.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							5L,
							44L,
							oldInfraccionDig.getAutorizaId(),
							objeto.getUsuarioAutoriza(),
							objeto.getOficialPlaca() != null ? new Long(objeto.getOficialPlaca()) : 0L,
							objeto.getInfraccionNum().toString(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));			
				}
				
				/* Bitacora generada cuando se realiza un ingreso a deposito desde el servicio soporte operación
				 * para ello se crea el registro y se manda a guardar la información en bitacoras_cambio */
				if(execValues.getTipoSoporte() == 14){
					IngresosVO newIngreso = soporteOperacionMyBatisDAO.getInformacionIngreso(objeto.getInfraccionNum());
					listBitCambios.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							11L,
							1L,
							newIngreso.getIngrResguardo(),
							newIngreso.getDepId().toString(),
							newIngreso.getModificadoPor() != null ? new Long(newIngreso.getModificadoPor()) : 0L, // Siempre se registra con el Emp_ID = 75L
							objeto.getInfraccionNum().toString(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));			
				}
				
				bitacoraCambiosService.guardarListaBitacoraCambios(listBitCambios);
				
			}catch(Exception e){
				System.err.println(e.getMessage());
			}finally{
				/*Metodo que permite realizar el bitacoreo de soporte_Bitacora ...*/
				try{
				//persisteSoporteBitacora(execValues);
				}catch(Exception e){
					System.err.println(e.getMessage());
				}
			}
		}
				
		return execValues;

	}
	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map buscaIngresoPorInfraccion(String infraccion) {
		Map ingreso = soporteOperacionMyBatisDAO.buscaIngresoPorInfraccion(infraccion);
		return ingreso;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map buscaIngresoDetallePorInfraccion(String infraccion) {
		Map ingresodetalle = soporteOperacionMyBatisDAO.buscaIngresoDetallePorInfraccion(infraccion);
		return ingresodetalle;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<SoporteEmbargoVO> buscaEmbargoPorPlaca(String placa) {
		return soporteOperacionMyBatisDAO.buscaEmbargoPorPlaca(placa);
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public String buscarPagoDeInfraccion(String infraccion) {
		String infraccionPagada = this.soporteOperacionMyBatisDAO.buscaPagoDeInfraccion(infraccion);
		if (infraccionPagada != null) {
			if (infraccionPagada.equals("SI")) {
				return "La infracción YA fue pagada";
			}
			return "La infracción NO ha sido pagada";
		}
		return infraccionPagada;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<SoporteEmbargoConsultaVO> buscaEmbargos(Integer tipoBusqueda, String valor) {
		return soporteOperacionMyBatisDAO.buscaEmbargos(tipoBusqueda, valor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MenuAdminVO> obtieneListaMenus(String tipo, Long id) {
		List<MenuAdminVO> listaMenus = null;
		if (tipo.equals("activos")) {
			listaMenus = menuAdminMyBatisDAO.obtieneListaMenusActivos(id);
		} else {
			listaMenus = menuAdminMyBatisDAO.obtieneListaMenusInactivos(id);
		}
		return listaMenus;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Se tuvo que implementar un try / catch en este service debido a un fallo
	 * en el SP Cassio autorizó esto
	 * 
	 */
	@Override
	@Transactional
	public String crudPerfiles(AdminPerfilesParamVO parametros) {
		
		BitacoraCambiosVO bitacoraVO = null;
		int operacionRealizada = parametros.getOperacionTipo();
		parametros.setCdAplicacion(codeApplication);
		
		PerfilDTO perfilDTO = null;
		perfilDTO=new PerfilDTO();
		
		if(operacionRealizada!=1){
			if(parametros.getPerfilId()==null){
			     perfilDTO=perfilDAO.buscarPerfilPorNombre(parametros.getPerfilNombre(), codeApplication);	
			}else{
				 perfilDTO=perfilDAO.findOne(new Long(parametros.getPerfilId()));
			}
			parametros.setPerfilId(perfilDTO.getPerfilId().toString());
			parametros.setPerfilNombre(perfilDTO.getPerfilNombre());
		}
		
		try {			
			crudPerfilesMybatisDAO.crudPerfilesWeb(parametros);
			if(parametros.getResultado()==0){	

				
				switch (operacionRealizada) {	
					/** Nuevo Perfil **/
					case 1:
						bitacoraVO=bitSpAdminPerfilesWeb.nuevoPerfil(parametros);
						bitacoraCambiosService.guardarBitacoraCambios(bitacoraVO);
						break;
					/** Eliminar Perfil **/
					case 2:
						bitacoraVO=bitSpAdminPerfilesWeb.eliminaPerfil(parametros);
						bitacoraCambiosService.guardarBitacoraCambios(bitacoraVO);
						break;
					/** Agregar Servicio Perfil **/
					case 3:
						bitacoraCambiosService.guardarBitacoraCambiosParametros(
										ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
										2L,	
										7L, 
										"", 
										parametros.getMenuId(), 
										new Long(parametros.getEmpleadoId()), 
										parametros.getPerfilId(), 
										"",  
										ParametrosBitacoraEnum.ORIGEN_W.getParametro() // Origen W / H
									);
						break;
					/** Desligar Servicio Perfil **/
					case 4:
						bitacoraVO=bitSpAdminPerfilesWeb.desligarServicioPerfil(parametros);
						bitacoraCambiosService.guardarBitacoraCambios(bitacoraVO);
						break;	
					}
				}
			return parametros.getMensaje();		
		
		} catch (DuplicateKeyException e) {	
					bitacoraCambiosService.guardarBitacoraCambiosParametros(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
							2L,	
							7L, 
							"", 
							parametros.getMenuId(), 
							new Long(parametros.getEmpleadoId()), 
							parametros.getPerfilId(), 
							"",  
							ParametrosBitacoraEnum.ORIGEN_W.getParametro() // Origen W / H
						);
			String mensaje = "Servicio agregado correctamente";
			return mensaje;
		}		
	}

	public CajaVO bucarCajaOperacionesExtemporaneas(String valor, String tipoBusqueda) {
		if ("placa".equals(tipoBusqueda)) {
			//return cajaDAO.buscarCajaEmpleadoPlaca(valor);
			return operacionesExtemporaneasMyBatisDAO.buscarCajasByEmpPlaca(valor);
		}
		if ("caja".equals(tipoBusqueda)) {
			//return cajaDAO.buscarCajaPorCod(valor);
			return operacionesExtemporaneasMyBatisDAO.buscarCajasByCodCaja(valor);
		}
		return null;
	}

	@Override
	@Transactional
	public void habilitarExtemporanea(OperacionesExtemporaneasVO operacionesExtemporaneasVO) throws ParseException {
		
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		operacionesExtemporaneasVO.setSupervisorId(usuarioFirmadoVO.getId());

		/* Consultar si existe el registro en operaciones extemporaneas para realizar la comprobación de cambios ...*/
		OperacionExtDTO oldOperacionExtDTO=new OperacionExtDTO();
		oldOperacionExtDTO = operacionExtDAO.findOne(operacionesExtemporaneasVO.getCajaId());		
		operacionesExtemporaneasMyBatisDAO.habilitarExtemporanea(operacionesExtemporaneasVO);
	
		if(operacionesExtemporaneasVO.getResultado().equals("0")){
			List<BitacoraCambiosVO> listaCambios = new ArrayList<>();
			/*Guarda cambios cuando existe algun cambio en el registro de la tabla Operacion Extemporanea*/
			if(oldOperacionExtDTO!=null){
				OperacionExtDTO newOperacionExtDTO=new OperacionExtDTO();	
				Long idEmplNew = operacionesExtemporaneasMyBatisDAO.getIdEmpfromIdCaja(operacionesExtemporaneasVO.getCajaId());				
				newOperacionExtDTO.setUsuarioId(idEmplNew);
				newOperacionExtDTO.setFechaHabilitada(new RutinasTiempoImpl().convertirStringDate(operacionesExtemporaneasVO.getFecha()));
				newOperacionExtDTO.setCapStatus("A");
				newOperacionExtDTO.setCajaId(oldOperacionExtDTO.getCajaId());
				listaCambios = bitTrBitacOpeExtemporanea.obtenerParametrosBitacora(newOperacionExtDTO, oldOperacionExtDTO,usuarioFirmadoVO.getId());
			}
			
			for (int i = 0; i < listaCambios.size(); i++) {
				bitacoraCambiosService.guardarBitacoraCambios(listaCambios.get(i));
			}
			
			//Guarda Cambios cuando se habilita la caja
			BitacoraCambiosVO cambiosVO=habilitarOperacionExt.habilitarExtemporanea(operacionesExtemporaneasVO);
			bitacoraCambiosService.guardarBitacoraCambios(cambiosVO);
		
		}
	}

	@Override
	public List<VCajaExtDesactivarDTO> buscarCajaExtDesactivar() {
		return cajaExtDAO.findAll();
	}

	@Override
	@Transactional
	public void desabilitarExtemporanea(VCajaExtDesactivarVO vCajaExtDesactivarVO) throws ParseException {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		vCajaExtDesactivarVO.setSupervisorId(usuarioFirmadoVO.getId());
		OperacionExtDTO oldOperacionExtDTO=new OperacionExtDTO();
		OperacionExtDTO newOperacionExtDTO=new OperacionExtDTO();	
		oldOperacionExtDTO= operacionExtDAO.findOne(vCajaExtDesactivarVO.getCajaId());
		operacionesExtemporaneasMyBatisDAO.desabilitarExtemporanea(vCajaExtDesactivarVO);
		
		
		if(vCajaExtDesactivarVO.getResultado().equals("0")){
			
			//Guarda cambios cuando existe algun cambio en el registro de la tabla Operacion Extemporanea
			newOperacionExtDTO.setUsuarioId(oldOperacionExtDTO.getUsuarioId());
			newOperacionExtDTO.setCajaId(oldOperacionExtDTO.getCajaId());
			newOperacionExtDTO.setFechaHabilitada(oldOperacionExtDTO.getFechaHabilitada());
			newOperacionExtDTO.setCapStatus("E");
			List<BitacoraCambiosVO> listaCambios = bitTrBitacOpeExtemporanea.obtenerParametrosBitacora(newOperacionExtDTO, oldOperacionExtDTO,usuarioFirmadoVO.getId());
			
			for (int i = 0; i < listaCambios.size(); i++) {
				bitacoraCambiosService.guardarBitacoraCambios(listaCambios.get(i));
			}
			//Guarda cambios cuando se deshabilita la caja
			
			BitacoraCambiosVO cambiosVO=deshabilitarOperacionExt.deshabilitarExtemporanea(vCajaExtDesactivarVO);
			bitacoraCambiosService.guardarBitacoraCambios(cambiosVO);

		}
		
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaUsersVO buscaUsuarioHH(String placa) {
		return soporteOperacionMyBatisDAO.buscaUsuarioHH(placa, codeApplication);
	}

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map buscaFoliosEmpleado(Long empleadoId) {
		Map folios = new HashMap<String, Integer>();

		folios.put(ConceptosSoporteOperacion.FOLIO_INFRACCION, 0);
		folios.put(ConceptosSoporteOperacion.FOLIO_ARRASTRE, 0);
		folios.put(ConceptosSoporteOperacion.FOLIO_PLACAS, 0);
		folios.put(ConceptosSoporteOperacion.FOLIO_PAGO, 0);
		folios.put(ConceptosSoporteOperacion.FOLIO_TRANSACCION, 0);
		folios.put(ConceptosSoporteOperacion.FOLIO_CORTE_CAJA, 0);

		List<SoporteFoliosVO> listaFolios = soporteOperacionMyBatisDAO.buscaFoliosEmpleado(empleadoId);

		for (SoporteFoliosVO objeto : listaFolios) {
			folios.put(objeto.getFolioClave(), objeto.getCantidad());
		}

		return folios;
	}

	public String cabeceraFolio(String folioClave) {
		if (folioClave.equals(ConceptosSoporteOperacion.FOLIO_INFRACCION)) {
			return "INFRACCIÓN";
		} else if (folioClave.equals(ConceptosSoporteOperacion.FOLIO_ARRASTRE)) {
			return "ARRASTRE";
		} else if (folioClave.equals(ConceptosSoporteOperacion.FOLIO_PLACAS)) {
			return "PLACA";
		} else if (folioClave.equals(ConceptosSoporteOperacion.FOLIO_PAGO)) {
			return "PAGO";
		} else if (folioClave.equals(ConceptosSoporteOperacion.FOLIO_TRANSACCION)) {
			return "TRANSACCIÓN";
		} else if (folioClave.equals(ConceptosSoporteOperacion.FOLIO_CORTE_CAJA)) {
			return "CORTE DE CAJA";
		} else {
			return "";
		}
	}
	

	public void persisteSoporteBitacora(EjecutaSoporteOperacionVO objeto) {

		String fechaSoporteOcorteId = null;
		Long conceptosId = conceptosSoporteDAO.buscarComponenteId(objeto.getTipoSoporte().longValue());
		EmpleadosDTO empleadosDTO = empleadoDAO.consultaEmpleadoAutorizaSoporte(objeto.getUsuarioAutoriza());
		Long cambioId = bitTrgSoporteBitacora.consultaIdMaximoSoporteBitacora();

		/* Enviar fechas segun tipo de soporte */
		if (objeto.getTipoSoporte().equals(13) || objeto.getTipoSoporte().equals(16)
				|| objeto.getTipoSoporte().equals(17)) {

			fechaSoporteOcorteId = objeto.getFechaHora();

			if (objeto.getTipoSoporte().equals(17)) {

				VBuscarCorteCaja corte = obtenerCorteId(fechaSoporteOcorteId, objeto.getOficialPlaca());
				fechaSoporteOcorteId = corte.getCorteId();
				empleadosDTO.setEmpId(Long.valueOf(corte.getEmpId()));

			} else if (objeto.getTipoSoporte().equals(13)) {

				String fecha = buscaIngresoPorInfraccionFecha(objeto.getInfraccionNum());
				fechaSoporteOcorteId = fecha;

			}

		}

		bitSoporteBitacora.persisteSoporteBitacora(objeto, empleadosDTO.getEmpId(), cambioId, conceptosId,
				fechaSoporteOcorteId);

	}
	
	
	public VBuscarCorteCaja obtenerCorteId(String fecha, String placaOficial) {

		VBuscarCorteCaja corte = cajaMyBatisDAO.getCorteId(fecha, placaOficial);

		return corte;
	}
	

	public String buscaIngresoPorInfraccionFecha(String infraccion) {
		String fecha = soporteOperacionMyBatisDAO.buscaIngresoPorInfraccionFecha(infraccion);
		return fecha;
	}
	@Override
	@Transactional
	public List<PerfilesAdminVO> getPerfilByCdApp() {
		List<PerfilDTO> listDTO = perfilDAO.getPerfilActiveBycdApp(codeApplication);
		List<PerfilesAdminVO> listVOReturn = new ArrayList<>();
		if(listDTO.isEmpty())
			return null;
		for(PerfilDTO dto: listDTO){
			PerfilesAdminVO vo = new PerfilesAdminVO();
			ResponseConverter.copiarPropriedades(vo, dto);
			listVOReturn.add(vo);
		}
		return listVOReturn;
	}
}
