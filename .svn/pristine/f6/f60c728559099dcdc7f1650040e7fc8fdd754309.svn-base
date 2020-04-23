package mx.com.teclo.saicdmx.negocio.service.caja;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasDepositosVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasModifSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasModifVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasNuevoVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasUsuariosVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.ProcInformeCorteSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaConTieneOperacionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaSinCorteActVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaSinCorteHistVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VConsultaUsuariosCajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VFoliosCajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

public interface CajaService {

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_HIST FILTRADO Y AGRUPADO
	 * @author Kevin Ojeda
	 * @param String filtroEmpPlaca
	 * @param String filtroCajaCod
	 * @param String filtroPerfil
	 * @param String filtroFechaI
	 * @param String filtroFechaF
	 * @return List<VCajaSinCorteHistVO>
	 */
	public List<VCajaSinCorteHistVO> getVCajaSinCorteHistVOTotalPagos(VCajaSinCorteHistVO vCajaSinCorteHistVO);

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_HIST FILTRADO
	 * @author Kevin Ojeda
	 * @param String filtroEmpPlaca
	 * @param String filtroCajaCod
	 * @param String filtroPerfil
	 * @param String filtroFechaI
	 * @param String filtroFechaF
	 * @return List<VCajaSinCorteHistVO>
	 */
	public List<VCajaSinCorteHistVO> getVCajaSinCorteHistVODetalle(VCajaSinCorteHistVO vCajaSinCorteHistVO);

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_HIST FILTRADO
	 * @author Kevin Ojeda
	 * @param String caja
	 * @param String emp
	 * @param String fecha
	 * @return List<VCajaSinCorteHistVO>
	 */
	public List<VCajaSinCorteHistVO> getVCajaSinCorteHistVODetalleByParams(String caja, String emp, String fecha);

	public ByteArrayOutputStream generarReporteExcel();

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT FILTRADO POR LOS PARAMETROS PERTINENTES
	 * @author Kevin Ojeda
	 * @param tipoBusqueda
	 * @param parametroBusqueda
	 * @return List<VCajaSinCorteActVO>
	 */
	public List<VCajaSinCorteActVO> getVCajaSinCorteActVOTotalByParams(String tipoBusqueda, String parametroBusqueda);

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT FILTRADO
	 * @author Kevin Ojeda
	 * @param String caja
	 * @param String emp
	 * @return List<VCajaSinCorteActVO>
	 */
	public List<VCajaSinCorteActVO> getVCajaSinCorteActVODetalleByParams(String caja, String emp);

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT FILTRADO
	 * @author Kevin Ojeda
	 * @param String caja
	 * @param String emp
	 * @return List<VCajaSinCorteActVO>
	 */
	public List<VCajaSinCorteActVO> getVCajaSinCorteActVODetallesByParams(String tipoBusqueda, String parametroBusqueda);

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_CONSULTA FILTRADO POR EMP_ID
	 * @author Kevin Ojeda
	 * @param String empId
	 * @return List<VCajaConsultaVO>
	 */
	public List<VCajaConsultaVO> getConsultaCajaByUsuario(String empId);

	/**
	 * Ejecuta SP Proc_Informe_Corte_2
	 * @author Kevin Ojeda
	 * @param ProcInformeCorteSPVO procInformeCorteSPVO
	 * @return ProcInformeCorteSPVO
	 * @throws PersistenceException
	 */
	public ProcInformeCorteSPVO procInformeCorte(Integer cajaId, String tipoCorte, String fechaCorte);

	/**
	 * Ejecuta SP Proc_Guardar_Corte_22
	 * @author Kevin Ojeda
	 * @param ProcInformeCorteSPVO procInformeCorteSPVO
	 * @return ProcInformeCorteSPVO
	 * @throws ParseException 
	 * @throws PersistenceException
	 */
	public ProcInformeCorteSPVO procGuardarCorte(ProcInformeCorteSPVO procInformeCorteSPVO) throws ParseException;

	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_CONSULTA FILTRADO POR PLACA O CAJA_NUM DEPENDIENDO DEL PARAMETRO TIPOBUSQUEDA
	 * @author Kevin Ojeda
	 * @param String paramBusqueda
	 * @param String tipoBusqueda
	 * @return List<VCajaConsultaVO>
	 */
	public List<VCajaConsultaVO> getConsultaCajaByUsuario(String paramBusqueda, String tipoBusqueda);


	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CORTES_CAJA_BUSQ FILTRADO POR EMP_ID O CAJA_ID DEPENDIENDO DEL PARAMETRO TIPOBUSQUEDA
	 * @author Kevin Ojeda
	 * @param String param
	 * @param String tipoBusqueda
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> buscarCajaPorTipoParam(String param, String tipoBusqueda);

	/***
	 * @author Jesus Gutierrez
	 * @param empleadoId
	 * @return Boolean
	 */
	public Long buscarCajaEmpleado(Long empleadoId);

	/**
 	 * RETORNA REGISTROS DE LA VISTA V_CAJAS_CONSULTA FILTRADOS POR CAJA_COD, EMP_PLACA Y DEP_ID 
 	 * @author Kevin Ojeda
 	 * @param vCajaConsultaConTieneOperacionesVO
 	 * @return List<VCajaConsultaConTieneOperacionesVO>
 	 * @throws PersistenceException
 	 */
	public List<VCajaConsultaConTieneOperacionesVO> buscarCajasPorCajaCodEmpPlacaAndDepId(String cajaCod, String empPlaca,
			String depId);

	/**
 	 * RETORNA REGISTROS DE LA VISTA V_FOLIOS_CAJA FILTRADOS POR CAJA_ID
 	 * @author Kevin Ojeda
 	 * @param vCajaConsultaConTieneOperacionesVO
 	 * @return List<VCajaConsultaConTieneOperacionesVO>
 	 * @throws PersistenceException
 	 */
	public List<VFoliosCajaVO> buscarFoliosByCajaId(String cajaId);

	/**
	 * EJECUTA SP SP_ADMIN_CAJAS_NUEVO
	 * @author Kevin Ojeda
	 * @param adminCajasNuevoVO
	 * @return AdminCajasNuevoVO
	 * @throws ParseException 
	 */
	public AdminCajasNuevoVO procAdminCajasNuevoSPVO(AdminCajasNuevoVO adminCajasNuevoVO) throws ParseException;

	/**
 	 * RETORNA REGISTROS DE LA VISTA V_CONSULTA_USUARIOS_CAJA FILTRADOS POR EMP_PLACA
	 * @author Kevin Ojeda
	 * @param VConsultaUsuariosCajaVO vConsultaUsuariosCajaVO
	 * @return VConsultaUsuariosCajaVO
	 * @throws PersistenceException
	 */
	public VConsultaUsuariosCajaVO vConsultaUsuariosCajaByEmpPlaca(String placaUsuario);

	/**
	 * EJECUTA SP SP_ADMIN_CAJAS_USUARIOS
	 * @author Kevin Ojeda
	 * @param AdminCajasUsuariosVO adminCajasUsuariosVO
	 * @return AdminCajasUsuariosVO
	 * @throws ParseException 
	 */
	public AdminCajasUsuariosVO procAdminCajasUsuarios(AdminCajasUsuariosVO adminCajasUsuariosVO) throws ParseException;

	/**
	 * Ejecuta SP SP_ADMIN_CAJAS_DEPOSITOS
	 * @author Kevin Ojeda
	 * @param AdminCajasDepositosVO adminCajasDepositosVO
	 * @return AdminCajasDepositosVO
	 * @throws ParseException 
	 * @throws Exception 
	 * @throws PersistenceException
	 */
	public AdminCajasDepositosVO procAdminCajasDepositos(AdminCajasDepositosVO adminCajasDepositosVO) throws ParseException, Exception;

	/**
	 * Ejecuta SP SP_ADMIN_CAJAS_MODIF
	 * @author Kevin Ojeda
	 * @param AdminCajasModifVO adminCajasModifVO
	 * @return AdminCajasModifVO
	 * @throws ParseException 
	 * @throws PersistenceException
	 */
	public AdminCajasModifVO procAdminCajasModif(AdminCajasModifVO adminCajasModifVO) throws ParseException;

	/**
	 * RETORNA REPORTE EXCEL DE CONSULTA DE CAJAS
	 * @author Kevin Ojeda
	 * @return ByteArrayOutputStream
	 */
	public ByteArrayOutputStream generarReporteAdminExcel();
	
	/**
	 * CHECA SI UN USUARIO TIENE DEPOSITO Y CAJA Y ASIGNA A EL USUARIO 
	 * @author Cassio Rocha
	 * @return Boolean
	 */
	public void configurarCaja(UsuarioFirmadoVO usuario) throws BusinessException;

	/**
	 * @author javier07
	 * @param adminCajasModifVO
	 * @return String
	 * @throws ParseException 
	 */
	public String empatarFolios(AdminCajasModifSPVO adminCajasModifSPVO) throws ParseException;
}