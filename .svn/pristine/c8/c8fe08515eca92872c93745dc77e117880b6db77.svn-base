package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.empleados.BitTrBitacEmpleados;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.UsuarioAdminEstatusMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminUsuarioEstatusVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpAdminUsuarioEstatusImpl implements BitSpAdminUsuarioEstatus {

	@Autowired
	private BitTrBitacEmpleados bitTrBitacEmpleados;
	@Autowired
	private UsuarioAdminEstatusMyBatisDAO usuarioAdminEstatusMyBatisDAO;
	
	@Override
	public List<BitacoraCambiosVO> insertCambioEstatus(AdminUsuarioEstatusVO adminUsuarioEstatusVO) {
		final long componente = 2L;
		
		List<BitacoraCambiosVO> lista = new ArrayList<BitacoraCambiosVO>();
		
	    String nombreOficial = usuarioAdminEstatusMyBatisDAO.getNombreOficial (adminUsuarioEstatusVO.getUsuario_id());
	    Long cajaExiste = usuarioAdminEstatusMyBatisDAO.getCajaExiste(adminUsuarioEstatusVO.getUsuario_id());
		
		cajaExiste = cajaExiste != null ? cajaExiste : 0;
		
		if(adminUsuarioEstatusVO.getTipo_accion().equals("R")) {
			lista.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					2L,
					"*****",
					"*****",
					adminUsuarioEstatusVO.getModificado_por_id(),
					adminUsuarioEstatusVO.getUsuario_id().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
		));
			
		}else if(adminUsuarioEstatusVO.getTipo_accion().equals("D")) {
			lista.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					10L,
					"",
					nombreOficial,
					adminUsuarioEstatusVO.getModificado_por_id(),
					adminUsuarioEstatusVO.getUsuario_id().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					
					));			
			
			lista.add(bitTrBitacEmpleados.getHabilitaDesh(adminUsuarioEstatusVO, "E"));
			
			if(cajaExiste > 0 ) {
				
				lista.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						16L,
						usuarioAdminEstatusMyBatisDAO.getCodCaja(cajaExiste),
						nombreOficial,
						adminUsuarioEstatusVO.getModificado_por_id(),
						cajaExiste.toString(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						
						));
			}
			
		}else if(adminUsuarioEstatusVO.getTipo_accion().equals("H")) {	
			lista.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					13L,
					"",
					nombreOficial,
					adminUsuarioEstatusVO.getModificado_por_id(),
					adminUsuarioEstatusVO.getUsuario_id().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					
					));
			lista.add(bitTrBitacEmpleados.getHabilitaDesh(adminUsuarioEstatusVO, "A"));

		}
		return lista;
	
		}

	}

