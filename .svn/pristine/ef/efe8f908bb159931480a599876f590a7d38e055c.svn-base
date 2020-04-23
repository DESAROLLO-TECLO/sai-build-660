package mx.com.teclo.saicdmx.negocio.service.caja;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpAdminCajasDepositos;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpAdminCajasModif;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpAdminCajasNuevo;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitSpAdminCajasUsuarios;
import mx.com.teclo.saicdmx.bitacora.cambios.cajas.BitTrBitacCajas;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.UsuarioAdminEstatusMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.cajas.CajaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasDepositosSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasDepositosVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasModifSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasModifVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasNuevoSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasNuevoVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasUsuariosSPVO;
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
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;


@Service
public class CajaServiceImpl implements CajaService {
	
	@Value("${app.config.codigo}")
	private String codeApplication;	
	@Autowired
	private CajaMyBatisDAO cajaMyBatisDAO;
	@Autowired
	private CajaReporteExcel cajaReporteExcel;
	@Autowired
	private CajaDAO cajaDAO;
	@Autowired
	private BitSpAdminCajasNuevo bitSpAdminCajasNuevo;
	
	private ByteArrayOutputStream reporte;
	
	private ByteArrayOutputStream reporteAdmin;
	
	
	
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	@Autowired
	private BitTrBitacCajas bitTrBitacCajas;
	@Autowired
	private BitSpAdminCajasModif bitSpAdminCajasModif;
	@Autowired
	private BitSpAdminCajasUsuarios bitSpAdminCajasUsuarios;
	@Autowired
	private BitSpAdminCajasDepositos bitSpAdminCajasDepositos;
	@Autowired
	private UsuarioAdminEstatusMyBatisDAO usuarioAdminEstatusMyBatisDAO;
	
	/* **********************                HISTORICO                ********************************* */
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VCajaSinCorteHistVO> getVCajaSinCorteHistVODetalle(VCajaSinCorteHistVO vCajaSinCorteHistVO){
		List<VCajaSinCorteHistVO> lvCajaSinCorteHistVO = cajaMyBatisDAO.getVCajaSinCorteHistVODetalle
				(vCajaSinCorteHistVO.getEmpPlaca(),
				vCajaSinCorteHistVO.getCajaCod(),
				vCajaSinCorteHistVO.getPerfilId(),
				vCajaSinCorteHistVO.getFiltroFechaI(),
				vCajaSinCorteHistVO.getFiltroFechaF());
		if(CollectionUtils.isNotEmpty(lvCajaSinCorteHistVO)){
			reporte = cajaReporteExcel.generarReporteSinCajaInfraccion(lvCajaSinCorteHistVO, "Caja Sin Corte Historico Detalle");
			return lvCajaSinCorteHistVO;
		}
		 return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VCajaSinCorteHistVO> getVCajaSinCorteHistVOTotalPagos(VCajaSinCorteHistVO vCajaSinCorteHistVO){
		List<VCajaSinCorteHistVO> lvCajaSinCorteHistVO = cajaMyBatisDAO.getVCajaSinCorteHistVOTotalPagos
				(vCajaSinCorteHistVO.getEmpPlaca(),
				vCajaSinCorteHistVO.getCajaCod(),
				vCajaSinCorteHistVO.getPerfilId(),
				vCajaSinCorteHistVO.getFiltroFechaI(),
				vCajaSinCorteHistVO.getFiltroFechaF());
		if(CollectionUtils.isNotEmpty(lvCajaSinCorteHistVO)){
			reporte = cajaReporteExcel.generarReporteSinCajaInfraccion(lvCajaSinCorteHistVO, "Caja Sin Corte Historico Total Infracción");
			return lvCajaSinCorteHistVO;
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VCajaSinCorteHistVO> getVCajaSinCorteHistVODetalleByParams 
	(String caja, String emp, String fecha){
		List<VCajaSinCorteHistVO> lvCajaSinCorteHistVO = cajaMyBatisDAO.getVCajaSinCorteHistVODetalleByParams(caja, emp, fecha);
		return lvCajaSinCorteHistVO;
	}
	
	@Override
	public ByteArrayOutputStream generarReporteExcel() {
		return this.reporte;
	}
	
	/* **********************                 HISTORICO              ********************************* */
	
	/* **********************                  ACTUAL                ********************************* */
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VCajaSinCorteActVO>getVCajaSinCorteActVOTotalByParams(String tipoBusqueda, String parametroBusqueda){
		List<VCajaSinCorteActVO> lVCajaSinCorteActVO = new ArrayList<VCajaSinCorteActVO>();
		if(tipoBusqueda.equals("TODAS")){
			lVCajaSinCorteActVO = cajaMyBatisDAO.getVCajaSinCorteActVOTotalPagos(codeApplication);
		}
		else if(tipoBusqueda.equals("CAJA_COD")){
			lVCajaSinCorteActVO = cajaMyBatisDAO.getVCajaSinCorteActVOTotalPagosByCajaCod(parametroBusqueda, codeApplication);
		}
		else if(tipoBusqueda.equals("EMP_PLACA")){
			lVCajaSinCorteActVO = cajaMyBatisDAO.getVCajaSinCorteActVOTotalPagosByEmpPlaca(parametroBusqueda, codeApplication);
		}
		else if(tipoBusqueda.equals("PERFIL_ID")){
			lVCajaSinCorteActVO = cajaMyBatisDAO.getVCajaSinCorteActVOTotalPagosByPerfilId(parametroBusqueda, codeApplication);
			
		}
		/*else{
			lVCajaSinCorteActVO = null;
		}*/
		if(CollectionUtils.isNotEmpty(lVCajaSinCorteActVO)){
			reporte = cajaReporteExcel.generarReporteSinCajaInfraccionAct(lVCajaSinCorteActVO, "Caja Sin Corte Actual Total Infracción");
			return lVCajaSinCorteActVO;
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VCajaSinCorteActVO>getVCajaSinCorteActVODetallesByParams(String tipoBusqueda, String parametroBusqueda){
		List<VCajaSinCorteActVO> lVCajaSinCorteActVO = new ArrayList<VCajaSinCorteActVO>();
		if(tipoBusqueda.equals("TODAS")){
			lVCajaSinCorteActVO = cajaMyBatisDAO.getVCajaSinCorteActVODetallePagos(codeApplication);
		}
		else if(tipoBusqueda.equals("CAJA_COD")){
			lVCajaSinCorteActVO = cajaMyBatisDAO.getVCajaSinCorteActVODetallePagosByCajaCod(parametroBusqueda, codeApplication);
		}
		else if(tipoBusqueda.equals("EMP_PLACA")){
			lVCajaSinCorteActVO = cajaMyBatisDAO.getVCajaSinCorteActVODetallePagosByEmpPlaca(parametroBusqueda, codeApplication);
		}
		else if(tipoBusqueda.equals("PERFIL_ID")){
			lVCajaSinCorteActVO = cajaMyBatisDAO.getVCajaSinCorteActVODetallePagosByPerfilId(parametroBusqueda, codeApplication);
		}
		if(CollectionUtils.isNotEmpty(lVCajaSinCorteActVO)){
			reporte = cajaReporteExcel.generarReporteSinCajaInfraccionAct(lVCajaSinCorteActVO, "Caja Sin Corte Actual Detalle");
			return lVCajaSinCorteActVO;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VCajaSinCorteActVO> getVCajaSinCorteActVODetalleByParams
	(String caja, String emp){
		List<VCajaSinCorteActVO> lvCajaSinCorteHistVO = cajaMyBatisDAO.getVCajaSinCorteActVODetalleByParams(caja, emp);
		return lvCajaSinCorteHistVO;
	}
	
	/* **********************                  ACTUAL                ********************************* */
	
	/* **********************               CORTE NUEVO              ********************************* */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VCajaConsultaVO> getConsultaCajaByUsuario(String empId){
		List<VCajaConsultaVO> lVCajaConsultaVO = cajaMyBatisDAO.getConsultaCajaByUsuarioByEmpId(empId);
		return lVCajaConsultaVO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VCajaConsultaVO> getConsultaCajaByUsuario(String paramBusqueda, String getConsultaCajaByUsuarioByEmpId){
		List<VCajaConsultaVO> lVCajaConsultaVO = new ArrayList<VCajaConsultaVO>();
		if(getConsultaCajaByUsuarioByEmpId.equals("buscaPorCaja"))
			lVCajaConsultaVO = cajaMyBatisDAO.getConsultaCajaByUsuarioByCajaCod(paramBusqueda, codeApplication);
		else if(getConsultaCajaByUsuarioByEmpId.equals("buscaPorPlaca"))
			lVCajaConsultaVO = cajaMyBatisDAO.getConsultaCajaByUsuarioByEmpPlaca(paramBusqueda, codeApplication);
		return lVCajaConsultaVO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcInformeCorteSPVO procInformeCorte(Integer cajaId, String tipoCorte, String fechaCorte){
		ProcInformeCorteSPVO procInformeCorteSPVO = new ProcInformeCorteSPVO();
		procInformeCorteSPVO.setP_caja_id(cajaId);
		procInformeCorteSPVO.setP_TIPO_CORTE(tipoCorte);
		if(tipoCorte.equals("A"))
			procInformeCorteSPVO.setPTXT_FECHA_CORTE(fechaCorte);
		cajaMyBatisDAO.procInformeCorte(procInformeCorteSPVO);
		/*CorteCajaVO corteCaja = new CorteCajaVO();
		corteCaja.setP_num_corte(procInformeCorteSPVO.getString(2));
        corteCaja.setP_total_efectivo(procInformeCorteSPVO.getString(3));
        corteCaja.setP_total_cheques(procInformeCorteSPVO.getString(4));
        corteCaja.setP_total_tarjetas(procInformeCorteSPVO.getString(5));
        corteCaja.setP_total_otros(procInformeCorteSPVO.getString(6));
        corteCaja.setP_total_corte(procInformeCorteSPVO.getString(7));
        corteCaja.setP_partida_inicial(procInformeCorteSPVO.getString(8));
        corteCaja.setP_partida_final(procInformeCorteSPVO.getString(9));
        corteCaja.setP_num_operaciones(procInformeCorteSPVO.getString(10));
        corteCaja.setErrorCodigo(procInformeCorteSPVO.getString(33));
        corteCaja.setErrorMensaje(procInformeCorteSPVO.getString(34));
        corteCaja.setP_caja_id(String.valueOf(cajaId));*/
		return procInformeCorteSPVO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws ParseException 
	 */
	@Override
	public ProcInformeCorteSPVO procGuardarCorte(ProcInformeCorteSPVO procInformeCorteSPVO) throws ParseException{
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		VCajaConsultaVO newCajaVO = new VCajaConsultaVO();
		VCajaConsultaVO oldCajaVO = cajaMyBatisDAO.getNumCorte(procInformeCorteSPVO.getP_caja_id());
		cajaMyBatisDAO.procGuardarCorte(procInformeCorteSPVO);
		if(procInformeCorteSPVO.getP_resultado() == 0 ) {
			newCajaVO = cajaMyBatisDAO.getNumCorte(procInformeCorteSPVO.getP_caja_id());	
			bitacoraCambiosVOs = bitTrBitacCajas.guardarCambiosBitacora(newCajaVO, oldCajaVO);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
		}
		return procInformeCorteSPVO;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public Long buscarCajaEmpleado(Long empleadoId) {
		Long depositoId = new Long(0);
		CajaDTO caja = cajaDAO.buscarCajaEmpleado(empleadoId);
		if(caja != null){
			if(caja.getDeposito() != null){
				depositoId = caja.getDeposito().getDepId();
			}
		}
		return depositoId;
	}
	
	/* **********************               CORTE NUEVO              ********************************* */
	
	/* **********************              CORTE CONSULTA             ********************************* */
	/**
	 * {@inheritDoc}
	 */
	@Override 
	public List<FilterValuesVO> buscarCajaPorTipoParam(String param, String tipoBusqueda){
		return tipoBusqueda.equals("buscaPorPlaca") ? cajaMyBatisDAO.buscarCajaPorEmpId(param) : cajaMyBatisDAO.buscarCajaPorCajaId(param);
		
	}
	
	/* **********************              CORTE CONSULTA             ********************************* */
	
	/* **********************              ADMINISTRACION             ********************************* */
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VCajaConsultaConTieneOperacionesVO> buscarCajasPorCajaCodEmpPlacaAndDepId(String cajaCod, String empPlaca, String depId){
		VCajaConsultaConTieneOperacionesVO vCajaConsultaConTieneOperacionesVO = new VCajaConsultaConTieneOperacionesVO();
		vCajaConsultaConTieneOperacionesVO.setCajaCod(cajaCod);
		vCajaConsultaConTieneOperacionesVO.setEmpPlaca(empPlaca);
		vCajaConsultaConTieneOperacionesVO.setDepId(depId);
		vCajaConsultaConTieneOperacionesVO.setCd_aplicacion(codeApplication);
		
		List<VCajaConsultaConTieneOperacionesVO> listVCajaConsultaConTieneOperacionesVO = 
				depId.equals("TODOS") ? cajaMyBatisDAO.buscarCajasPorCajaCodEmpPlacaAndAllDepId(vCajaConsultaConTieneOperacionesVO)
						: cajaMyBatisDAO.buscarCajasPorCajaCodEmpPlacaAndDepId(vCajaConsultaConTieneOperacionesVO);
		
		
				
		if(CollectionUtils.isNotEmpty(listVCajaConsultaConTieneOperacionesVO)){
			reporteAdmin = cajaReporteExcel.generarReporteCajasAdmin(listVCajaConsultaConTieneOperacionesVO, "Cajas");
			return listVCajaConsultaConTieneOperacionesVO;
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ByteArrayOutputStream generarReporteAdminExcel() {
		return this.reporteAdmin;
	}
	
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public List<VFoliosCajaVO> buscarFoliosByCajaId(String cajaId){
		VFoliosCajaVO vFoliosCajaVO = new VFoliosCajaVO();
		vFoliosCajaVO.setCajaId(cajaId);
		List<VFoliosCajaVO> listvFoliosCajaVO = cajaMyBatisDAO.buscarFoliosByCajaId(vFoliosCajaVO);
		if(CollectionUtils.isNotEmpty(listvFoliosCajaVO))
			return listvFoliosCajaVO;
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws ParseException 
	 */
	@Override
	public AdminCajasNuevoVO procAdminCajasNuevoSPVO(AdminCajasNuevoVO adminCajasNuevoVO) throws ParseException{
		//adminCajasNuevoVO.setpOperacion("M");
		//adminCajasNuevoVO.setpDepId(null);
		//adminCajasNuevoVO.setpCdAplicacion(codeApplication);
		VCajaConsultaVO newCajaVO = new VCajaConsultaVO();
		VCajaConsultaVO oldCajaVO = new VCajaConsultaVO();
		adminCajasNuevoVO.generaParametrosParaSP();
		AdminCajasNuevoSPVO adminCajasNuevoSPVO = adminCajasNuevoVO.getAdminCajasNuevoSPVO();
		String getCajaCode = "";
		if(adminCajasNuevoSPVO.getP_operacion().equals("M")){
			getCajaCode = usuarioAdminEstatusMyBatisDAO.getCodCaja(adminCajasNuevoSPVO.getP_caja_id());
		}
		if(adminCajasNuevoSPVO.getP_caja_id()!=null){
			oldCajaVO = cajaMyBatisDAO.getCajaById(adminCajasNuevoSPVO.getP_caja_id());
			oldCajaVO.setCajaIdD(adminCajasNuevoSPVO.getP_caja_id().toString());
		}
		cajaMyBatisDAO.procAdminCajasNuevoSPVO(adminCajasNuevoSPVO);
		if(adminCajasNuevoSPVO.getP_resultado() != -1 ) {
			bitSpAdminCajasNuevo.spAdminCajasNuevo(adminCajasNuevoSPVO,getCajaCode);
			if(adminCajasNuevoSPVO.getP_operacion().equals("M")){
				newCajaVO = cajaMyBatisDAO.getCajaById(adminCajasNuevoSPVO.getP_caja_id());	
				newCajaVO.setCajaIdD(adminCajasNuevoSPVO.getP_caja_id().toString());
				newCajaVO.setModificadoPor(adminCajasNuevoSPVO.getP_modificado_por().toString());
				bitacoraCambiosService.guardarListaBitacoraCambios(bitTrBitacCajas.guardarCambiosBitacora(newCajaVO, oldCajaVO));
			}
		}

		adminCajasNuevoVO.setpResultado(adminCajasNuevoSPVO.getP_resultado());
		adminCajasNuevoVO.setpMensaje(adminCajasNuevoSPVO.getP_mensaje());
		adminCajasNuevoVO.setAdminCajasNuevoSPVO(null);
		return adminCajasNuevoVO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public VConsultaUsuariosCajaVO vConsultaUsuariosCajaByEmpPlaca(String placaUsuario){
		VConsultaUsuariosCajaVO vConsultaUsuariosCajaVO = new VConsultaUsuariosCajaVO();
		vConsultaUsuariosCajaVO.setEmpPlaca(placaUsuario);
		List<VConsultaUsuariosCajaVO> lVConsultaUsuariosCajaVO = cajaMyBatisDAO.vConsultaUsuariosCajaByEmpPlaca(vConsultaUsuariosCajaVO);
		if(CollectionUtils.isEmpty(lVConsultaUsuariosCajaVO))
			return null;
		return lVConsultaUsuariosCajaVO.get(0);
	}
	
	/**
	 * {@inheritDoc}
	 * @throws ParseException 
	 */
	@Override
	public AdminCajasUsuariosVO procAdminCajasUsuarios(AdminCajasUsuariosVO adminCajasUsuariosVO) throws ParseException{
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		VCajaConsultaVO newCajaVO = new VCajaConsultaVO();
		adminCajasUsuariosVO.generaParametrosParaSP();
		AdminCajasUsuariosSPVO adminCajasUsuariosSPVO = adminCajasUsuariosVO.getAdminCajasUsuariosSPVO();
		VCajaConsultaVO oldCajaVO = cajaMyBatisDAO.getCajaById(adminCajasUsuariosSPVO.getP_caja_id());
		bitacoraCambiosVOs.addAll(bitSpAdminCajasUsuarios.spAdminCajasUsuarios(adminCajasUsuariosSPVO));
		cajaMyBatisDAO.procAdminCajasUsuarios(adminCajasUsuariosSPVO);
		if(adminCajasUsuariosSPVO.getP_resultado() != -1 ) {
			newCajaVO = cajaMyBatisDAO.getCajaById(adminCajasUsuariosSPVO.getP_caja_id());	
			newCajaVO.setCajaIdD(adminCajasUsuariosSPVO.getP_caja_id().toString());
			newCajaVO.setModificadoPor(adminCajasUsuariosSPVO.getP_modificado_por().toString());
			bitacoraCambiosVOs.addAll(bitTrBitacCajas.guardarCambiosBitacora(newCajaVO, oldCajaVO));
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
		}
		adminCajasUsuariosVO.setAdminCajasUsuariosSPVO(null);
		adminCajasUsuariosVO.setpResultado(adminCajasUsuariosSPVO.getP_resultado());
		adminCajasUsuariosVO.setpMensaje(adminCajasUsuariosSPVO.getP_mensaje());
		return adminCajasUsuariosVO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws Exception 
	 */
	@Override
	public AdminCajasDepositosVO procAdminCajasDepositos(AdminCajasDepositosVO adminCajasDepositosVO) throws Exception{
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		VCajaConsultaVO newCajaVO = new VCajaConsultaVO();
		adminCajasDepositosVO.setpCdAplicacion(codeApplication);
		adminCajasDepositosVO.generaParametrosParaSP();
		AdminCajasDepositosSPVO adminCajasDepositosSPVO = adminCajasDepositosVO.getAdminCajasDepositosSPVO();
		VCajaConsultaVO oldCajaVO = cajaMyBatisDAO.getCajaById(adminCajasDepositosSPVO.getP_caja_id());
		bitacoraCambiosVOs.addAll(bitSpAdminCajasDepositos.spAdminCajasDepositos(adminCajasDepositosSPVO));
		cajaMyBatisDAO.procAdminCajasDepositos(adminCajasDepositosSPVO);
		if(adminCajasDepositosSPVO.getP_resultado() != -1 ) {
			newCajaVO = cajaMyBatisDAO.getCajaById(adminCajasDepositosSPVO.getP_caja_id());	
			newCajaVO.setCajaIdD(adminCajasDepositosSPVO.getP_caja_id().toString());
			newCajaVO.setModificadoPor(adminCajasDepositosSPVO.getP_modificado_por().toString());
			bitacoraCambiosVOs.addAll(bitTrBitacCajas.guardarCambiosBitacora(newCajaVO, oldCajaVO));
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
		}
		adminCajasDepositosVO.setAdminCajasDepositosSPVO(null);
		adminCajasDepositosVO.setpResultado(adminCajasDepositosSPVO.getP_resultado());
		adminCajasDepositosVO.setpMensaje(adminCajasDepositosSPVO.getP_mensaje());
		return adminCajasDepositosVO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws ParseException 
	 */
	@Override
	public AdminCajasModifVO procAdminCajasModif(AdminCajasModifVO adminCajasModifVO) throws ParseException{
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		VCajaConsultaVO newCajaVO = new VCajaConsultaVO();
		adminCajasModifVO.generaParametrosParaSP();
		AdminCajasModifSPVO adminCajasModifSPVO = adminCajasModifVO.getAdminCajasModifSPVO();
		VCajaConsultaVO oldCajaVO = cajaMyBatisDAO.getCajaById(adminCajasModifSPVO.getP_caja_id());
		adminCajasModifSPVO.setCd_aplicacion(codeApplication);
		bitacoraCambiosVOs.addAll(bitSpAdminCajasModif.spAdminCajasModif(adminCajasModifSPVO));
		cajaMyBatisDAO.procAdminCajasModif(adminCajasModifSPVO);
		if(adminCajasModifSPVO.getP_resultado() != -1 ) {
			newCajaVO = cajaMyBatisDAO.getCajaById(adminCajasModifSPVO.getP_caja_id());	
			newCajaVO.setCajaIdD(adminCajasModifSPVO.getP_caja_id().toString());
			newCajaVO.setModificadoPor(adminCajasModifSPVO.getP_modificado_por().toString());
			bitacoraCambiosVOs.addAll(bitTrBitacCajas.guardarCambiosBitacora(newCajaVO, oldCajaVO));
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
		}
		adminCajasModifVO.setpResultado(adminCajasModifSPVO.getP_resultado());
		adminCajasModifVO.setpMensaje(adminCajasModifSPVO.getP_mensaje());
		adminCajasModifVO.setAdminCajasModifSPVO(null);
		return adminCajasModifVO;		
	}

	@Override
	public void configurarCaja(UsuarioFirmadoVO usuario) throws BusinessException {
		CajaDTO caja = cajaDAO.buscarCajaEmpleado(usuario.getId());
		if (caja == null) {
			throw new BusinessException("Verifique caja y depósito válido para el usuario");
		} else if (caja.getDeposito() == null) {
			throw new BusinessException("El usuario no tiene deposito asignado");
		}	
		usuario.setNumeroCaja(caja.getCajaCod());
		usuario.setNombreDeposito(caja.getDeposito().getDepNombre());
	}

	@Override
	public String empatarFolios(AdminCajasModifSPVO adminCajasModifSPVO) throws ParseException {
		VCajaConsultaVO newCajaVO = new VCajaConsultaVO(); 
		VCajaConsultaVO oldCajaVO = cajaMyBatisDAO.getCajaById(adminCajasModifSPVO.getP_caja_id());
		cajaMyBatisDAO.procAdminCajasModif(adminCajasModifSPVO);
		if(adminCajasModifSPVO.getP_resultado() != -1 ) {
			newCajaVO = cajaMyBatisDAO.getCajaById(adminCajasModifSPVO.getP_caja_id());	
			newCajaVO.setCajaIdD(adminCajasModifSPVO.getP_caja_id().toString());
			newCajaVO.setModificadoPor(adminCajasModifSPVO.getP_modificado_por().toString());
			bitacoraCambiosService.guardarListaBitacoraCambios(bitTrBitacCajas.guardarCambiosBitacora(newCajaVO, oldCajaVO));
		}
		return adminCajasModifSPVO.getP_mensaje();
	}
}
