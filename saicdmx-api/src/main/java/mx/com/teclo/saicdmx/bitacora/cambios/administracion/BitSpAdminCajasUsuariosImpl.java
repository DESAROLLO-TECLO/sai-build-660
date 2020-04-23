package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.administracion.DerechosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.DepositoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.DepositoEmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado.EmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.DerechosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosEmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasModifSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasUsuariosSPVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpAdminCajasUsuariosImpl implements BitSpAdminCajasUsuarios {

	@Autowired
	private CajaDAO cajaDAO;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private EmpleadoDAO empleadoDAO;
	@Autowired
	private DepositoDAO depositoDAO;
	@Autowired
	private DerechosDAO derechosDAO;
	@Autowired
	private DepositoEmpleadoDAO depositoEmpleadoDAO;
	@Autowired
	private BitSpAdminCajaFicticia bitSpAdminCajaFicticia;
	
	private CajaDTO cajaDTO;
	private DepositosDTO depositoDTO;
	private DerechosDTO derechosDTO;
	private DepositosEmpleadosDTO depositosEmpleadosDTO;
	public static final String ELIMINAR = "E";
	public static final String CAMBIAR = "C";
	public static final String ASIGNAR = "A";
	
	@Transactional
	@Override
	public ArrayList<BitacoraCambiosVO> spAdminCajasUsuarios(AdminCajasUsuariosSPVO objectIn) {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		String opcion = objectIn.getP_operacion();

//		if(opcion.equals(ELIMINAR))
			try {
				bitacoraCambiosVOs.addAll(eliminarUsuario(objectIn));
			} catch (Exception e) {
				e.printStackTrace();
			}
		if(opcion.equals(ASIGNAR) || opcion.equals(CAMBIAR))
			try {
				bitacoraCambiosVOs.addAll(cambioUsuario(objectIn, opcion));
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return bitacoraCambiosVOs;
	}

	/**
	 * Funcion para eliminiar Usuario de caja
	 * 
	 * @author Fernando Castillo
	 * @return ArrayList<BitacoraCambiosVO>
	 */
	@Transactional
	private ArrayList<BitacoraCambiosVO> eliminarUsuario(AdminCajasUsuariosSPVO objectIn) throws Exception {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
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
			
			if(objectIn.getP_caja_emp_perfil_id()==20){
				AdminCajasModifSPVO adminCajasModifSPVO = new AdminCajasModifSPVO(null, null, empId, null, null, null, null);
				bitacoraCambiosVOs.addAll(bitSpAdminCajaFicticia.spAdminCajaFicticia(adminCajasModifSPVO));
			}
		}
		return bitacoraCambiosVOs;
	}

	/**
	 * Funcion para cambio de Usuario
	 * 
	 * @author Fernando Castillo
	 */
	@Transactional
	private ArrayList<BitacoraCambiosVO> cambioUsuario(AdminCajasUsuariosSPVO objectIn, String opcion) throws Exception {

		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		/* se obtiene empleado para bitacoras */
		Long cajaId;
		EmpleadosDTO empleado = empleadoDAO.findOne(objectIn.getP_emp_id());
		String nombreCompleto = empleado.getEmpNombre() + " " + empleado.getEmpApePaterno() + " " +  empleado.getEmpApeMaterno();
		if (objectIn.getP_emp_caja_id() > 0) {
			cajaId = objectIn.getP_emp_caja_id();
			cajaDTO = new CajaDTO();
			cajaDTO = cajaDAO.cajaPorId(cajaId);
			bitacoraCambiosVOs.add(guardaEnBitacora(2L, 16L, cajaDTO.getCajaCod(), nombreCompleto, cajaId.toString()));

			bitacoraCambiosVOs.add(guardaEnBitacora(12L, 8L, empleado.getEmpId().toString(), "", cajaId.toString()));
		}
//		REGISTRA EN BITACORA CAMBIOS LA ASIGNACION DEL USUARIO EN LA CAJA
		cajaId = objectIn.getP_caja_id();
		cajaDTO = new CajaDTO();
		cajaDTO = cajaDAO.cajaPorId(cajaId);
		bitacoraCambiosVOs.add(guardaEnBitacora(2L, 15L, cajaDTO.getCajaCod(), nombreCompleto, cajaId.toString()));
		String emp_puede_cobrar = objectIn.getP_emp_puede_cobrar();
		if(objectIn.getP_emp_perfil_id()==20){
			if(cajaDTO.getAutorizadaPCobro()==0 && objectIn.getP_emp_puede_cobrar()=="S"){
				emp_puede_cobrar = "N";
			}
			derechosDTO = new DerechosDTO();
			derechosDTO = derechosDAO.getDerechosByUsuId(objectIn.getP_emp_id(), 6L);
			if(emp_puede_cobrar.equals("N")){
				if(derechosDTO != null){
					bitacoraCambiosVOs.add(guardaEnBitacora(2L, 25L, "", nombreCompleto, objectIn.getP_emp_id().toString()));
				}
			}
			else{
				if(derechosDTO==null){
					bitacoraCambiosVOs.add(guardaEnBitacora(2L, 24L, "", nombreCompleto, objectIn.getP_emp_id().toString()));
				}
			}
		}
		depositosEmpleadosDTO = depositoEmpleadoDAO.depositoEmpleadoPorEmpId(objectIn.getP_emp_id());
		depositoDTO = new DepositosDTO();
		if(cajaDTO.getDeposito() != null && cajaDTO.getDeposito().getDepId() != 0 && depositosEmpleadosDTO == null){
//			REGISTRA EN BITACORA LA ASIGNACION DE DEPOSITO A USUARIO
			depositoDTO = depositoDAO.findOne(cajaDTO.getDeposito().getDepId());
			bitacoraCambiosVOs.add(guardaEnBitacora(2L, 22L, nombreCompleto, depositoDTO.getDepNombre(), objectIn.getP_emp_id().toString()));
		}
		if(cajaDTO.getDeposito() != null && cajaDTO.getDeposito().getDepId() != 0 && depositosEmpleadosDTO != null){
//			REGISTRA EN BITACORA LA ASIGNACION DE DEPOSITO A USUARIO
			if(cajaDTO.getDeposito().getDepId() != depositosEmpleadosDTO.getDepositos().getDepId()){
				depositoDTO = depositoDAO.findOne(cajaDTO.getDeposito().getDepId());
				bitacoraCambiosVOs.add(guardaEnBitacora(2L, 22L, nombreCompleto, depositoDTO.getDepNombre(), objectIn.getP_emp_id().toString()));
				bitacoraCambiosVOs.add(guardaEnBitacora(2L, 23L, nombreCompleto, depositosEmpleadosDTO.getDepositos().getDepNombre(), objectIn.getP_emp_id().toString()));
			}
		}
		if(cajaDTO.getDeposito() == null && depositosEmpleadosDTO != null){
			bitacoraCambiosVOs.add(guardaEnBitacora(2L, 23L, nombreCompleto, depositosEmpleadosDTO.getDepositos().getDepNombre(), objectIn.getP_emp_id().toString()));
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
