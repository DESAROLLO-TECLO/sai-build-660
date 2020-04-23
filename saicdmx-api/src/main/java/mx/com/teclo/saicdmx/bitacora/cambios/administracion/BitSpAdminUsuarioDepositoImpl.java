package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.DepositoAdmin_MyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.ModificaCajaUsuarioMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.UsuarioAdminEstatusMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminDepositoVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpAdminUsuarioDepositoImpl implements BitSpAdminUsuarioDeposito {
	@Autowired
	private UsuarioAdminEstatusMyBatisDAO usuarioAdminEstatusMyBatisDAO;
	@Autowired
	private DepositoAdmin_MyBatisDAO depositoMyBatisDAO;
	@Autowired
	private ModificaCajaUsuarioMyBatisDAO modificaCajaUsuarioMyBatisDAO;
	
	
	@Override
	public List<BitacoraCambiosVO> insertBitacNuevoUsuario(AdminDepositoVO adminDepositoVO) {
	
		String nombreOficial = usuarioAdminEstatusMyBatisDAO.getNombreOficial (adminDepositoVO.getP_emp_id());	
	List<BitacoraCambiosVO> lista = new ArrayList<BitacoraCambiosVO>();	
	
	final long componente = 2L;
	
		if (adminDepositoVO.getP_emp_caja_id() > 0) {
			lista.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					16L,
					usuarioAdminEstatusMyBatisDAO.getCodCaja(new Long(adminDepositoVO.getP_emp_caja_id())),
					nombreOficial,
					adminDepositoVO.getP_modificado_por(),
					String.valueOf(adminDepositoVO.getP_emp_caja_id()),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					
			));
			
			if(depositoMyBatisDAO.getPerfilUsuarioByIdEmp(adminDepositoVO.getP_emp_id()) == 20L){
				String cajaCodCajaFicticia = modificaCajaUsuarioMyBatisDAO.getCajaCodCajaFicticia();
				String cajaIdCajaFicticia = modificaCajaUsuarioMyBatisDAO.getCajaIdCajaFicticia();
				
				//Bitacora al asignar usuario a una caja ficticia ... 
				lista.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						15L,
						cajaCodCajaFicticia,
						nombreOficial,
						adminDepositoVO.getP_modificado_por(),
						cajaIdCajaFicticia,
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));

				lista.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						12L,
						8L,
						"",
						adminDepositoVO.getP_emp_id().toString(),
						adminDepositoVO.getP_modificado_por(),
						cajaIdCajaFicticia,
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}

		} // end if

		if (adminDepositoVO.getP_operacion().equals("E")) {
			lista.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					23L,
					nombreOficial,
					adminDepositoVO.getNombreDep(),
					adminDepositoVO.getP_modificado_por(),
					adminDepositoVO.getP_emp_id().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
			));

		} else if (adminDepositoVO.getP_operacion().equals("A")) {

			lista.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					22L,
					nombreOficial,
					depositoMyBatisDAO.getDepNombre(adminDepositoVO.getP_dep_id()),
					adminDepositoVO.getP_modificado_por(),
					adminDepositoVO.getP_emp_id().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
			));

		} else if (adminDepositoVO.getP_operacion().equals("C")) {

			lista.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					23L,
					nombreOficial,
					adminDepositoVO.getNombreDep(),
					adminDepositoVO.getP_modificado_por(),
					adminDepositoVO.getP_emp_id().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
			));
			lista.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					22L,
					nombreOficial,
					depositoMyBatisDAO.getDepNombre(adminDepositoVO.getP_dep_id()),
					adminDepositoVO.getP_modificado_por(),
					adminDepositoVO.getP_emp_id().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
			));

		}
		return lista;
	}

}
