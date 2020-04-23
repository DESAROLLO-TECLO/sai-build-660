package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.DepositoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado.EmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.AdminPerfilesAdminMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.PerfilUsuarioVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasDepositosSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasModifSPVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpAdminCajasDepositosImpl implements BitSpAdminCajasDepositos {

	@Autowired
	private CajaDAO cajaDAO;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private EmpleadoDAO empleadoDAO;
	@Autowired
	private DepositoDAO depositoDAO;
	@Autowired
	private AdminPerfilesAdminMyBatisDAO adminPerfiles;
	@Autowired
	private BitSpAdminCajaFicticia bitSpAdminCajaFicticia;
	
	private CajaDTO cajaDTO;
	private DepositosDTO depositoDTO;
	
	public static final String ELIMINAR = "E";
	public static final String CAMBIAR = "C";
	public static final String ASIGNAR = "A";
	
	@Transactional
	@Override
	public ArrayList<BitacoraCambiosVO> spAdminCajasDepositos(AdminCajasDepositosSPVO objectIn) throws Exception {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		String opcion = objectIn.getP_operacion();
		Long cajaId = objectIn.getP_caja_id();
		cajaDTO = new CajaDTO();
		cajaDTO = cajaDAO.cajaPorId(cajaId);
		if (objectIn.getP_caja_emp_id() != null && objectIn.getP_caja_emp_id() > 0) {
			Long empId = objectIn.getP_caja_emp_id();
			/* se obtiene empleado para bitacoras */
			EmpleadosDTO empleado = empleadoDAO.findOne(empId);
			bitacoraCambiosVOs.add(guardaEnBitacora(2L, 16L, cajaDTO.getCajaCod(),
					empleado.getEmpNombre() + " " + empleado.getEmpApePaterno() + " " +  empleado.getEmpApeMaterno(),
					cajaId.toString()));
			PerfilUsuarioVO perfil = adminPerfiles.consultaPerfilUsuario(empId, objectIn.getCd_aplicacion());
			if (perfil != null) {
				if(perfil.getPerfilId()==20){
					AdminCajasModifSPVO adminCajasModifSPVO = new AdminCajasModifSPVO(null, null, empId, null, null, null, null);
					bitacoraCambiosVOs.addAll(bitSpAdminCajaFicticia.spAdminCajaFicticia(adminCajasModifSPVO));
				}
			}
		}
		switch (opcion) {
		case ASIGNAR:
			try {
				bitacoraCambiosVOs.addAll(asignarDeposito(objectIn));
			} catch (Exception e) {
				// rollback();
				e.printStackTrace();
			}
		case ELIMINAR:
			try {
				bitacoraCambiosVOs.addAll(eliminarDeposito(objectIn));
			} catch (Exception e) {
				// rollback();
				e.printStackTrace();
			}
		case CAMBIAR:
			try {
				bitacoraCambiosVOs.addAll(cambioDeposito(objectIn, opcion));
			} catch (Exception e) {
				e.printStackTrace();
			}
		default:
			break;
		}
		return bitacoraCambiosVOs;
	}

	/**
	 * Funcion para asignar deposito a caja
	 * 
	 * @author Fernando Castillo
	 * @return AdminCajasDepositosSPVO
	 */
	@Transactional
	private ArrayList<BitacoraCambiosVO> asignarDeposito(AdminCajasDepositosSPVO objectIn) throws Exception {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		Long cajaId = objectIn.getP_caja_id();
		cajaDTO = new CajaDTO();
		depositoDTO = new DepositosDTO();
		cajaDTO = cajaDAO.cajaPorId(cajaId);
		depositoDTO = depositoDAO.findOne(objectIn.getP_dep_id());

		/* Agrega bitacoreo */
		bitacoraCambiosVOs.add(guardaEnBitacora(2L, 17L, cajaDTO.getCajaCod(), depositoDTO.getDepNombre(), cajaId.toString()));

		return bitacoraCambiosVOs;
	}

	/**
	 * Funcion para eliminiar deposito de caja
	 * 
	 * @author Fernando Castillo
	 * @return AdminCajasDepositosVO
	 */
	@Transactional
	private ArrayList<BitacoraCambiosVO> eliminarDeposito(AdminCajasDepositosSPVO objectIn) throws Exception {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		/* se obtiene objeto completo de CAJAS */
		Long cajaId = objectIn.getP_caja_id();
		cajaDTO = new CajaDTO();
		cajaDTO = cajaDAO.cajaPorId(cajaId);
		
		bitacoraCambiosVOs.add(guardaEnBitacora(2L, 18L, cajaDTO.getCajaCod(), cajaDTO.getDeposito().getDepNombre(), cajaId.toString()));
		
		return bitacoraCambiosVOs;
	}

	/**
	 * Funcion para cambio de Deposito
	 * 
	 * @author Fernando Castillo
	 */
	@Transactional
	private ArrayList<BitacoraCambiosVO> cambioDeposito(AdminCajasDepositosSPVO objectIn, String opcion) throws Exception {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		Long cajaId = objectIn.getP_caja_id();
		cajaDTO = new CajaDTO();
		cajaDTO = cajaDAO.cajaPorId(cajaId);
		
		bitacoraCambiosVOs.add(guardaEnBitacora(2L, 18L, cajaDTO.getCajaCod(), cajaDTO.getDeposito().getDepNombre(), cajaId.toString()));
		
		depositoDTO = new DepositosDTO();
		depositoDTO = depositoDAO.findOne(objectIn.getP_dep_id());
		
		bitacoraCambiosVOs.add(guardaEnBitacora(2L, 17L, cajaDTO.getCajaCod(), depositoDTO.getDepNombre(), cajaId.toString()));

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
