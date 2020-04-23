package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado.EmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.cajas.CajaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasModifSPVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpAdminCajaFicticiaImpl implements BitSpAdminCajaFicticia {

	@Autowired
	private CajaMyBatisDAO cajaMyBatisDAO;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private EmpleadoDAO empeladoDAO;

	@Transactional
	@Override
	public ArrayList<BitacoraCambiosVO> spAdminCajaFicticia(AdminCajasModifSPVO objectIn) throws Exception {
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		try {

//			Long cajaId = objectIn.getP_caja_id();
			Long usuId = objectIn.getP_caja_emp_id();

			Long vParamCajaFicticia = 0L;
			String vCajaCodFicticia = "FI";
			String vCajaNombreFicticia = "CAJA";
			Long vCajaId = 0L;

			/* obteniendo parametro caja ficticia */
			vParamCajaFicticia = cajaMyBatisDAO.getVParamCajaFicticia();

			/* genera vCajaCodFicticia y vCajaNombreFicticia */
			vCajaCodFicticia = vCajaCodFicticia + (10000 + vParamCajaFicticia);
			vCajaNombreFicticia = vCajaNombreFicticia + (vCajaCodFicticia);

			/* se crea caja ficticia */
			vCajaId = cajaMyBatisDAO.getVCajaId();

			/* se obtiene objeto empleado */
			EmpleadosDTO empObject = empeladoDAO.findOne(usuId);
			String nombreCompleto = empObject.getEmpNombre() + " " + empObject.getEmpApePaterno() + " " +  empObject.getEmpApeMaterno();
			
			/* guarda en bitacora el movimiento realizado */
			bitacoraCambiosVOs.add(guardaEnBitacora(2L, 15L, vCajaCodFicticia, nombreCompleto, vCajaId.toString()));
			bitacoraCambiosVOs.add(guardaEnBitacora(12L, 8L, "", empObject.getEmpId().toString(), vCajaId.toString()));
			objectIn.setP_resultado(0L);

		} catch (Exception ex) {
			objectIn.setP_mensaje("Ocurrió un error en el proceso");
			objectIn.setP_resultado(-1L);
		}
		return bitacoraCambiosVOs;
	}
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
