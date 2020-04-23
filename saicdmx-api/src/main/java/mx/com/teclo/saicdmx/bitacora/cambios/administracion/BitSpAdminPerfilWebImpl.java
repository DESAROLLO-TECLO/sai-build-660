package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminPerfilesParamVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpAdminPerfilWebImpl implements BitSpAdminPerfilWeb{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BitacoraCambiosVO nuevoPerfil(AdminPerfilesParamVO parametrosVO) {
		
		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(2L); 
		c.setConceptoId(6L); 
		c.setValorOriginal(""); 
		c.setValorFinal(parametrosVO.getPerfilNombre());
		c.setCreadoPor(new Long(parametrosVO.getEmpleadoId())); 
		c.setIdRegistro(parametrosVO.getPerfilId()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BitacoraCambiosVO eliminaPerfil(AdminPerfilesParamVO parametrosVO) {
		
		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(2L); 
		c.setConceptoId(9L); 
		c.setValorOriginal(parametrosVO.getPerfilNombre()); 
		c.setValorFinal(parametrosVO.getPerfilNombre());
		c.setCreadoPor(new Long(parametrosVO.getEmpleadoId())); 
		c.setIdRegistro(parametrosVO.getPerfilId()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BitacoraCambiosVO agregaServicioPerfil(AdminPerfilesParamVO parametrosVO) {
		
		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(2L); 
		c.setConceptoId(7L); 
		c.setValorOriginal(""); 
		c.setValorFinal(parametrosVO.getMenuId());
		c.setCreadoPor(new Long(parametrosVO.getEmpleadoId())); 
		c.setIdRegistro(parametrosVO.getPerfilId()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BitacoraCambiosVO desligarServicioPerfil(AdminPerfilesParamVO parametrosVO) {
		
		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(2L); 
		c.setConceptoId(8L); 
		c.setValorOriginal(parametrosVO.getMenuId()); 
		c.setValorFinal(parametrosVO.getMenuId());
		c.setCreadoPor(new Long(parametrosVO.getEmpleadoId())); 
		c.setIdRegistro(parametrosVO.getPerfilId()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
	}

}
