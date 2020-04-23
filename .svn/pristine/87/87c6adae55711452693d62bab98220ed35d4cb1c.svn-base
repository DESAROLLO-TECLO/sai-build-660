package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.DepositoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasNuevoSPVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class BitSpAdminCajasNuevoImpl implements BitSpAdminCajasNuevo {

	@Autowired
	private CajaDAO cajaDAO;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;
	@Autowired
	private DepositoDAO depositoDAO;
	
	private CajaDTO cajaDTO;
	private DepositosDTO depositoDTO;

	public static final String ALTA = "A";
	public static final String MODIFICAR = "M";
	
	@Transactional
	@Override
	public AdminCajasNuevoSPVO spAdminCajasNuevo(AdminCajasNuevoSPVO objectIn, String getCajaCode) {
		String opcion = objectIn.getP_operacion();
		
		if(opcion.equals(ALTA))
			try {
				return crearCaja(objectIn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		if(opcion.equals(MODIFICAR))
			try {
				return modificarCaja(objectIn,getCajaCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return null;
	}

	/**
	 * Funcion para crear caja
	 * 
	 * @author Fernando Castillo
	 * @return AdminCajasNuevoSPVO
	 */
	@Transactional
	private AdminCajasNuevoSPVO crearCaja(AdminCajasNuevoSPVO objectIn) throws Exception {

		cajaDTO = new CajaDTO();
		depositoDTO = new DepositosDTO();
		cajaDTO = cajaDAO.cajaPorCod(objectIn.getP_caja_cod());
		Long cajaId = cajaDTO.getCajaId();

		if(objectIn.getP_dep_id() != null && objectIn.getP_dep_id()!=0){
			/* Agrega bitacoreo */
			depositoDTO = depositoDAO.findOne(cajaDTO.getDeposito().getDepId());
			guardaEnBitacora(2L, 17L, objectIn.getP_caja_cod(), depositoDTO.getDepNombre(), cajaId.toString());
		}
		guardaEnBitacora(2L, 14L, "", objectIn.getP_caja_cod(), cajaId.toString());
		return objectIn;
	}

	/**
	 * Funcion para modificar caja
	 * 
	 * @author Fernando Castillo
	 */
	@Transactional
	private AdminCajasNuevoSPVO modificarCaja(AdminCajasNuevoSPVO objectIn, String getCajaCode) throws Exception {

		Long cajaId = objectIn.getP_caja_id();
		guardaEnBitacora(2L, 27L, getCajaCode, objectIn.getP_caja_cod(), cajaId.toString());
		return objectIn;
	}

	/**
	 * Guarda en bitacora todos los moviemientos en bitacora
	 * 
	 * @author Fernando Castillo
	 * 
	 */
	private void guardaEnBitacora(Long componente, Long concepto, String valO, String valF, String idRegistro) {
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), componente, concepto, valO, valF,
				usuarioFirmadoService.getUsuarioFirmadoVO().getId(), idRegistro, "",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
	}
}
