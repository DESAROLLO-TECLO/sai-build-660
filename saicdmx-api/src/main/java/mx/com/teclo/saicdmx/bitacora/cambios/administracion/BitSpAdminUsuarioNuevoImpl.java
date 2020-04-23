package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.AltaUsuarioMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.ModificaCajaUsuarioMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.UsuarioAdminVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpAdminUsuarioNuevoImpl implements BitSpAdminUsuarioNuevo {
	private ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs;
	
	@Autowired
	private AltaUsuarioMyBatisDAO altaUsuarioMyBatisDAO;
	@Autowired
	private ModificaCajaUsuarioMyBatisDAO modificaCajaUsuarioMyBatisDAO;
	
	@Override
	public List<BitacoraCambiosVO> insertBitacNuevoUsuario(UsuarioAdminVO usuarioAdminVO) {
	
		String nomCompleto = usuarioAdminVO.getEmp_nombre()+" "+ usuarioAdminVO.getEmp_ape_paterno()+" "+ usuarioAdminVO.getEmp_ape_materno();
		String idRegistro = altaUsuarioMyBatisDAO.getIdRegistroEmpleadoSiguiente();
		String perfil = altaUsuarioMyBatisDAO.getNombrePerfilUsuario(usuarioAdminVO.getPerfil_id());
		
		bitacoraCambiosVOs = new ArrayList<>();
			
			final long componente = 2L;
			
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(componente);
			bitacoraCambiosVO.setConceptoId(1L);
			bitacoraCambiosVO.setValorOriginal("");
			bitacoraCambiosVO.setValorFinal(nomCompleto);
			bitacoraCambiosVO.setCreadoPor(usuarioAdminVO.getModificadoPor());
			bitacoraCambiosVO.setIdRegistro(idRegistro);
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
	
			BitacoraCambiosVO bitacoraCambiosVO1 = new BitacoraCambiosVO();
			bitacoraCambiosVO1.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO1.setComponenteId(componente);
			bitacoraCambiosVO1.setConceptoId(6L);
			bitacoraCambiosVO1.setValorOriginal("");
			bitacoraCambiosVO1.setValorFinal(perfil);
			bitacoraCambiosVO1.setCreadoPor(usuarioAdminVO.getModificadoPor());
			bitacoraCambiosVO1.setIdRegistro(idRegistro);
			bitacoraCambiosVO1.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO1);
			
			// Asignación de caja ficticia al usuario si es de perfil HandHeld
			if(usuarioAdminVO.getCd_perfil().equals("HANDHELD")) {
				// Generar caja_id y caja_cod de la caja ficticia ...
				String cajaIdCajaFicticia = modificaCajaUsuarioMyBatisDAO.getCajaIdCajaFicticia();
				String cajaCodCajaFicticia = modificaCajaUsuarioMyBatisDAO.getCajaCodCajaFicticia();
				
				//Bitacora al asignar usuario a una caja ficticia ... 
				bitacoraCambiosVOs.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						15L,
						cajaCodCajaFicticia,
						nomCompleto,
						usuarioAdminVO.getModificadoPor(),
						cajaIdCajaFicticia,
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
				
				bitacoraCambiosVOs.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						12L,
						8L,
						"",
						idRegistro,
						usuarioAdminVO.getModificadoPor(),
						cajaIdCajaFicticia,
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			
			
			if(usuarioAdminVO.getCd_perfil().equals("CAJERO")) {
				BitacoraCambiosVO bitacoraCambiosVO2 = new BitacoraCambiosVO();
				bitacoraCambiosVO2.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO2.setComponenteId(componente);
				bitacoraCambiosVO2.setConceptoId(24L);
				bitacoraCambiosVO2.setValorOriginal("");
				bitacoraCambiosVO2.setValorFinal(nomCompleto);
				bitacoraCambiosVO2.setCreadoPor(usuarioAdminVO.getModificadoPor());
				bitacoraCambiosVO2.setIdRegistro(idRegistro);
				bitacoraCambiosVO2.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO2);
		}
			
		return bitacoraCambiosVOs;

	}
	
}
