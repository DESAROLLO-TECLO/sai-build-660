package mx.com.teclo.saicdmx.bitacora.cambios.empleados;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminUsuarioEstatusVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.EmpleadoVO;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrBitacEmpleadosImpl implements BitTrBitacEmpleados {
	private final long componente = 13L;

	
	@Override
	public BitacoraCambiosVO getHabilitaDesh(AdminUsuarioEstatusVO adminUsuarioEstatusVO, String flag) {
		// TODO Auto-generated method stub
	
			return new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					8L,
					adminUsuarioEstatusVO.getEstatus(),
					flag,
					adminUsuarioEstatusVO.getModificado_por_id(),
					adminUsuarioEstatusVO.getUsuario_id().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					);
	}
	
	@Override
	public List<BitacoraCambiosVO> bitModificacionEmpleados(EmpleadoVO voEmplOld,EmpleadoVO voEmplNew){
		
		//String, Long, Long, String, String, Long, String, String, String
		List<BitacoraCambiosVO> bitEmpl = new ArrayList<>();
		
		if(!voEmplOld.getEmpPlaca().trim().equals(voEmplNew.getEmpPlaca().trim()))
			bitEmpl.add(new BitacoraCambiosVO(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
				componente,
				1L,
				voEmplOld.getEmpPlaca(),// info old
				voEmplNew.getEmpPlaca(),// info new
				voEmplNew.getModificadoPor(),
				voEmplNew.getEmpId().toString(),
				"",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro()
				)
			);
		if(!ComparaUtils.comparaCadenas(voEmplOld.getEmpTipId(), voEmplNew.getEmpTipId()))
			bitEmpl.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					2L,
					voEmplOld.getEmpTipId().toString(),// info old
					voEmplNew.getEmpTipId().toString(),// info new
					voEmplNew.getModificadoPor(),
					voEmplNew.getEmpId().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					)
			);
		if(!ComparaUtils.comparaCadenas(voEmplOld.getAgrpId(), voEmplNew.getAgrpId()))
			bitEmpl.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					3L,
					voEmplOld.getAgrpId().toString(),// info old
					voEmplNew.getAgrpId().toString(),// info new
					voEmplNew.getModificadoPor(),
					voEmplNew.getEmpId().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					)
			);
		if(!ComparaUtils.comparaCadenas(voEmplOld.getSecId(), voEmplNew.getSecId()))
			bitEmpl.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					4L,
					voEmplOld.getSecId().toString(),// info old
					voEmplNew.getSecId().toString(),// info new
					voEmplNew.getModificadoPor(),
					voEmplNew.getEmpId().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					)
			);
		if(!voEmplOld.getEmpApePaterno().trim().equals(voEmplNew.getEmpApePaterno().trim()))
			bitEmpl.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					5L,
					voEmplOld.getEmpApePaterno(),// info old
					voEmplNew.getEmpApePaterno(),// info new
					voEmplNew.getModificadoPor(),
					voEmplNew.getEmpId().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					)
			);
		if(!voEmplOld.getEmpApeMaterno().trim().equals(voEmplNew.getEmpApeMaterno().trim()))
			bitEmpl.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					6L,
					voEmplOld.getEmpApeMaterno(),// info old
					voEmplNew.getEmpApeMaterno(),// info new
					voEmplNew.getModificadoPor(),
					voEmplNew.getEmpId().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					)
			);
		if(!voEmplOld.getEmpNombre().trim().equals(voEmplNew.getEmpNombre().trim()))
			bitEmpl.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					7L,
					voEmplOld.getEmpNombre(),// info old
					voEmplNew.getEmpNombre(),// info new
					voEmplNew.getModificadoPor(),
					voEmplNew.getEmpId().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					)
			);
		if(!voEmplOld.getEmpStatus().trim().equals(voEmplNew.getEmpStatus().trim()))
			bitEmpl.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					8L,
					voEmplOld.getEmpStatus(),// info old
					voEmplNew.getEmpStatus(),// info new
					voEmplNew.getModificadoPor(),
					voEmplNew.getEmpId().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					)
			);
		
		
		return bitEmpl;
	}

	
}
