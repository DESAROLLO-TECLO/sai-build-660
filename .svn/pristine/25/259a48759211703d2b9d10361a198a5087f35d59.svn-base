package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado.EmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.AdminPerfilesAdminMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.UsuarioAdminEstatusMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.cajas.CajaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.PerfilUsuarioVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasModifSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VFoliosCajaVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpAdminCajasModifImpl implements BitSpAdminCajasModif {

	@Autowired
	private CajaDAO cajaDAO;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private CajaMyBatisDAO cajaMyBatisDAO;
	@Autowired
	private EmpleadoDAO empleadoDAO;
	@Autowired
	private UsuarioAdminEstatusMyBatisDAO usuarioAdminEstatusMyBatisDAO;
	@Autowired
	private BitSpAdminCajaFicticia bitSpAdminCajaFicticia;
	@Autowired
	private AdminPerfilesAdminMyBatisDAO adminPerfiles;
	
	CajaDTO cajaDTO;

	public static final String HABILITAR = "H";
	public static final String DESHABILITAR = "D";
	public static final String TRANSACCION = "TRANSACCION";
	public static final String PAGO = "PAGO";
	public static final String CORTE = "CORTE";

	@Transactional
	@Override
	public ArrayList<BitacoraCambiosVO> spAdminCajasModif(AdminCajasModifSPVO objectIn) {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		String opcion = objectIn.getP_operacion();
		switch (opcion) {
		case HABILITAR:
			try {
				return habilitarCaja(objectIn);
			} catch (Exception e) {
				// rollback();
				e.printStackTrace();
			}
		case DESHABILITAR:
			try {
				return deshabilitarCaja(objectIn);
			} catch (Exception e) {
				// rollback();
				e.printStackTrace();
			}
		case TRANSACCION:
			try {
				return empatarFolio(objectIn, opcion);
			} catch (Exception e) {
				e.printStackTrace();
			}
		case PAGO:
			try {
				return empatarFolio(objectIn, opcion);
			} catch (Exception e) {
				e.printStackTrace();
			}
		case CORTE:
			try {
				return empatarFolio(objectIn, opcion);
			} catch (Exception e) {
				e.printStackTrace();
			}
		default:
			break;
		}
		return bitacoraCambiosVOs;
	}

	/**
	 * Funcion para habilitar caja
	 * 
	 * @author Fernando Castillo
	 * @return AdminCajasModifVO
	 */
	@Transactional
	private ArrayList<BitacoraCambiosVO> habilitarCaja(AdminCajasModifSPVO objectIn) throws Exception {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		try {
			/* Obtiene objecto a atualizar */
			// CajaDTO cajaDTO = new CajaDTO();
			Long cajaId = objectIn.getP_caja_id();
			cajaDTO = new CajaDTO();
			cajaDTO = cajaDAO.cajaPorId(cajaId);
			String getCajaCode = cajaDTO.getCajaCod();//usuarioAdminEstatusMyBatisDAO.getCodCaja(objectIn.getP_caja_id().intValue());
//			cajaDTO.setCajaStatus("A");
//			cajaDTO.setUltimaModificacion(new Date());
//			cajaDTO.setModificadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());

			/* Actualiza caja */
//			cajaDAO.update(cajaDTO);
//			objectIn.setP_resultado(0L);
//			objectIn.setP_mensaje("Caja habilitada correctamente");

			/* Agrega bitacoreo de CAJA HABILITADA */
			bitacoraCambiosVOs.add(guardaEnBitacora(2L, 19L, "", getCajaCode, objectIn.getP_caja_id().toString()));

		} catch (Exception ex) {
			objectIn.setP_resultado(-1L);
			objectIn.setP_mensaje("Ocurrió un error al habilitar caja");
			ex.printStackTrace();
		}
		return bitacoraCambiosVOs;
	}

	/**
	 * Funcion para deshabilitar caja
	 * 
	 * @author Fernando Castillo
	 * @return AdminCajasModifVO
	 */
	@Transactional
	private ArrayList<BitacoraCambiosVO> deshabilitarCaja(AdminCajasModifSPVO objectIn) throws Exception {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		/* se obtiene objeto completo de CAJAS */
		Long cajaId = objectIn.getP_caja_id();
		cajaDTO = new CajaDTO();
		cajaDTO = cajaDAO.cajaPorId(cajaId);
		try {
			/* solo para bitacora */
			String getCajaCode = usuarioAdminEstatusMyBatisDAO.getCodCaja(objectIn.getP_caja_id());
			/*
			 * valida si la caja tiene empleado asigando
			 */
			if (objectIn.getP_caja_emp_id() > 0) {

				Long empId = objectIn.getP_caja_emp_id();
//				Long cajaId = objectIn.getP_caja_id();
//				Long perfilId = 0L;

				/* se obtiene empleado para bitacoras */
				EmpleadosDTO empleado = empleadoDAO.findOne(empId);

				/* obtiene un objeto completo de EMPLEADOS_CAJAS, por cajaId */
//				EmpleadosCajasDTO empleadosCajas = empleadosCajasDAO.getEmpCajasById(cajaId);

//				EmpleadosCajasHistIdDTO idEmpCajasHist = new EmpleadosCajasHistIdDTO(cajaId, empId);
//
//				EmpleadosCajasHistDTO empleadosCajasHist = new EmpleadosCajasHistDTO(idEmpCajasHist, "E",
//						usuarioFirmadoService.getUsuarioFirmadoVO().getId(), new Date(),
//						usuarioFirmadoService.getUsuarioFirmadoVO().getId(), new Date());
				/*
				 * guardar el objeto para registro historico en
				 * EMPLEADOS_CAJAS_HIST
				 */
//				empleadosCajasHistDAO.save(empleadosCajasHist);

				/*
				 * borrar registro en EMPLEADOS_CAJAS despues de haber
				 * registrado en EMPLEADOS_CAJAS_HIST
				 */
//				empleadosCajasDAO.delete(empleadosCajas);

				/* agregar registro en bitacora */
				bitacoraCambiosVOs.add(guardaEnBitacora(2L, 16L, getCajaCode,
						empleado.getEmpNombre() + " " + empleado.getEmpApePaterno() + " " +  empleado.getEmpApeMaterno(),
						cajaId.toString()));

				/* actualizar valores en CAJAS */
				// k

				//DepositosEmpleadosDTO depEmp = depositoEmpDAO.depositoEmpleadoPorEmpId(empId);
				//depEmp.setEmpleado(null);
				//dffff
//				cajaDTO.setEmpleado(null);
				//cajaDTO.setDepEmp(depositoEmpDAO.);
//				cajaDTO.setModificadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
//				cajaDTO.setUltimaModificacion(new Date());
//				cajaDAO.update(cajaDTO);

				/* se obtiene el perfil del usuario */
				PerfilUsuarioVO perfil = adminPerfiles.consultaPerfilUsuario(empId, objectIn.getCd_aplicacion());
				if (perfil != null) {
					if(perfil.getPerfilId()==20){
						AdminCajasModifSPVO adminCajasModifSPVO = new AdminCajasModifSPVO(null, null, empId, null, null, null, null);
						bitacoraCambiosVOs.addAll(bitSpAdminCajaFicticia.spAdminCajaFicticia(adminCajasModifSPVO));
					}
				}
//				String resultComprubaPerfilWeb = fnSpCajasModif.fnComprubaPerfilWeb("HANDHELD", perfilId);
//				if (resultComprubaPerfilWeb.equals("S")) {
//					try {
//						AdminCajasModifSPVO resultadoAdminLimpiaFolio = bitSpAdminCajaLimpiaFolios
//								.spAdminCajasLimpiaFolios(objectIn);
//						if (resultadoAdminLimpiaFolio.getP_resultado().compareTo(-1L) == 0) {
//							return objectIn;
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					/*
//					 * procedimiento temporal para la creación de caja ficticia
//					 */
//					try {
//						AdminCajasModifSPVO resultadoAdminCajaFicticia = bitSpAdminCajaFicticia
//								.spAdminCajaFicticia(objectIn);
//						if (resultadoAdminCajaFicticia.getP_resultado().compareTo(-1L) == 0) {
//							return objectIn;
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
			}

//			Long cajaId = objectIn.getP_caja_id();
			Long vDepId = 0L;
			/* se obtiene id del deposito */

//			cajaDTO = new CajaDTO();
//			cajaDTO = cajaDAO.buscarCajaPorId(cajaId);// truena
			if (cajaDTO != null) {
				vDepId = cajaDTO.getDeposito() != null ? cajaDTO.getDeposito().getDepId() : 0L;
			}
			if (vDepId.compareTo(0L) != 0) {
				/* guarda en bitacora los cambios realizados */
				bitacoraCambiosVOs.add(guardaEnBitacora(2L, 18L, getCajaCode, cajaDTO.getDeposito().getDepNombre(), cajaId.toString()));

				/* se eliminar la relación de la caja con el deposito */

				//DepositosEmpleadosDTO depEmp = depositoEmpDAO.depositoEmpleadoPorEmpId(objectIn.getP_caja_emp_id());
				//depEmp.setDepositos(null);
//				cajaDTO.setDepEmp(null);
//				cajaDTO.setModificadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
//				cajaDTO.setUltimaModificacion(new Date());

				/* se guardan las modificaciones en tabla CAJAS */
//				cajaDAO.update(cajaDTO);

			}
			/* se guarda en bitacora la deshabilitación de la caja */
			bitacoraCambiosVOs.add(guardaEnBitacora(2L, 20L, "", getCajaCode, cajaId.toString()));

			/* se guardan las modificaciones en tabla CAJAS */
			// cajaDAO.update(cajaDTO);
//			cajaDTO.setCajaStatus("E");
//			cajaDTO.setModificadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
//			cajaDTO.setUltimaModificacion(new Date());

			/* se guardan las modificaciones en tabla CAJAS */
//			cajaDAO.update(cajaDTO);
//
//			objectIn.setP_resultado(0L);
//			objectIn.setP_mensaje("Caja deshabilitada correctamente");

		} catch (Exception ex) {
			objectIn.setP_resultado(-1L);
			objectIn.setP_mensaje("Ocurrió un error al deshabilitar la caja");
			ex.printStackTrace();
		}
		return bitacoraCambiosVOs;
	}

	/**
	 * Funcion para TRANSACCION, PAGO o CORTE
	 * 
	 * @author Fernando Castillo
	 */
	@Transactional
	private ArrayList<BitacoraCambiosVO> empatarFolio(AdminCajasModifSPVO objectIn, String opcion) throws Exception {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		try {

			cajaDTO = new CajaDTO();
			Long cajaId = objectIn.getP_caja_id();
			VFoliosCajaVO folios = cajaMyBatisDAO.getFoliosCaja(cajaId, opcion);

			bitacoraCambiosVOs.add(guardaEnBitacora(2L, 21L, folios.getUltimoFolioSistema(), folios.getUltimoFolioCaja(), cajaId.toString()));

//			cajaDTO = cajaDAO.buscarCajaPorId(cajaId);
//
//			switch (opcion) {
//			case TRANSACCION:
//				cajaDTO.setCajaNumTran(Long.parseLong(folios.getUltimoFolioSistema()));
//				cajaDTO.setModificadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
//				cajaDTO.setUltimaModificacion(new Date());
//				cajaDAO.update(cajaDTO);
//				break;
//			case PAGO:
//				cajaDTO.setCajaNumPago(Long.parseLong(folios.getUltimoFolioSistema()));
//				cajaDTO.setModificadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
//				cajaDTO.setUltimaModificacion(new Date());
//				cajaDAO.update(cajaDTO);
//				break;
//			case CORTE:
//				cajaDTO.setCajaNumCorte(Long.parseLong(folios.getUltimoFolioSistema()));
//				cajaDTO.setModificadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
//				cajaDTO.setUltimaModificacion(new Date());
//				cajaDAO.update(cajaDTO);
//				break;
//			default:
//				break;
//			}
			objectIn.setP_resultado(cajaId);
			objectIn.setP_mensaje("Folio actualizado correctamente");
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return bitacoraCambiosVOs;
	}

	/**
	 * Guarda en bitacora todos los moviemientos en bitacora
	 * 
	 * @author Fernando Castillo
	 * 
	 */
	private BitacoraCambiosVO guardaEnBitacora(Long componente, Long concepto, String valO, String valF, String idRegistro) {
//		bitacoraCambiosService.guardarBitacoraCambiosParametros(
//				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), componente, concepto, valO, valF,
//				usuarioFirmadoService.getUsuarioFirmadoVO().getId(), idRegistro, "",
//				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(componente);
		bitacoraCambiosVO.setConceptoId(concepto);
		bitacoraCambiosVO.setValorOriginal(valO);
		bitacoraCambiosVO.setValorFinal(valF);
		bitacoraCambiosVO.setCreadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		bitacoraCambiosVO.setIdRegistro(idRegistro);
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return bitacoraCambiosVO;
	}
}
